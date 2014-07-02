/*
Copyright 2014 António Miranda, Caio Valente, Igor Topcin, Jorge Melegati, Thales Paiva, Victor Santos

This file is part of PhysUSP.

PhysUSP is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

PhysUSP is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with PhysUSP. If not, see <http://www.gnu.org/licenses/>.
*/

package com.gedaeusp.domain;

import java.util.Arrays;
import java.util.List;

import org.eclipse.jetty.util.log.Log;

import com.gedaeusp.models.BiexponentialFitData;
import com.gedaeusp.models.MonoexponentialFitData;

import br.com.caelum.vraptor.ioc.Component;

@Component
public class AnaerobicAlacticCalculator {
	
	private static double EPS = 0.05;
	private static double MAX_TIME_DELAY = 30;
	
	private final NonlinearCurveFitter fitter;
	private double[] consumptionArray;
	private double[] timesArray;
	private double[] normalizedTimesArray;
	private double timeDelay;
	
	
	public AnaerobicAlacticCalculator(NonlinearCurveFitter fitter) {
		this.fitter = fitter;
	}
	
	private <T extends Unit> double[] toArray(List<UnitValue<T>> list, T unit) {
		double[] array = new double[list.size()];
		int i = 0;

		for (UnitValue<T> element : list) {
			array[i++] = element.getValue(unit);
		}

		return array;
	}

	private <T extends Number> double[] toArray(List<T> list) {
		double[] array = new double[list.size()];
		int i = 0;

		for (T element : list) {
			array[i++] = element.doubleValue();
		}

		return array;
	}

	public void setExponentialInput(List<UnitValue<FlowUnit>> consumption, List<Integer> times,
			double time) {
		consumptionArray = toArray(consumption, FlowUnit.lPerSecond);
		timesArray = toArray(times);
		normalizedTimesArray = new double[timesArray.length];
		for(int i = 0; i < timesArray.length; i++)
		{
			normalizedTimesArray[i] = timesArray[i]-timesArray[0];
		}
		timeDelay = time;
	}
	
	private double estimateBaselineOxygenConsumption() {
		double[] best            = fitter.doExponentialFit(consumptionArray, normalizedTimesArray);
		Exponential exp          = new Exponential(best[Exponential.PARAM_v0], best[Exponential.PARAM_a], best[Exponential.PARAM_tau]);
		double[] normalizedTimes = normalizeTimesArray(timesArray);
		
		return exp.value(normalizedTimes[normalizedTimes.length - 1]);
	}
	
	public UnitValue<EnergyUnit> calculateEnergyWithBiexponential(BiexponentialFitData biexponentialFitData) {
		double   v0   = estimateBaselineOxygenConsumption();
		double[] init = fitter.guessBiexponentialInitialParameters(consumptionArray, normalizedTimesArray, v0);
		double[] best = fitter.doBiexponentialFitWithFixedV0(consumptionArray, normalizedTimesArray, init);
		
		int nOutliers = 0;
		double[] observedValues       = new double[consumptionArray.length];
		double[] normalizedTimesArray = normalizeTimesArray(timesArray);
		Biexponential exp             = new Biexponential(best[Biexponential.PARAM_v0], best[Biexponential.PARAM_a1], 
		                                                  best[Biexponential.PARAM_a2], best[Biexponential.PARAM_tau1],
		                                                  best[Biexponential.PARAM_tau2]);  
		
		for (int j = 0; j < consumptionArray.length; j++)
			observedValues[j] = exp.value(normalizedTimesArray[j]);
		double lastSumOfSquares = SquaredErrorsCalculator.calculate(observedValues, consumptionArray);
		
		for (int i = 1; i < consumptionArray.length && timesArray[i] - timesArray[0] <= MAX_TIME_DELAY; i++) {
			double[] consumptionSubArray     = Arrays.copyOfRange(consumptionArray, i, consumptionArray.length);
			double[] timesSubArray           = Arrays.copyOfRange(normalizedTimesArray, i, normalizedTimesArray.length);
			double[] normalizedTimesSubArray = normalizeTimesArray(timesSubArray);
			double[] bestCandidate           = fitter.doBiexponentialFitWithFixedV0(consumptionSubArray, normalizedTimesSubArray, init);
			
			observedValues = new double[consumptionSubArray.length];
			exp = new Biexponential(bestCandidate[Biexponential.PARAM_v0], bestCandidate[Biexponential.PARAM_a1], 
			                        bestCandidate[Biexponential.PARAM_a2], bestCandidate[Biexponential.PARAM_tau1], 
			                        bestCandidate[Biexponential.PARAM_tau2]);
			
			for (int j = 0; j < consumptionSubArray.length; j++)
				observedValues[j] = exp.value(normalizedTimesSubArray[j]);
			double sumOfErrors = SquaredErrorsCalculator.calculate(observedValues, consumptionSubArray);	
			
			if (sumOfErrors/lastSumOfSquares < 1 - EPS) {
				best = bestCandidate.clone();
				nOutliers = i;
				break;
			}
			lastSumOfSquares = sumOfErrors;
		}
		
		setBiexponentialFitData(biexponentialFitData, best, nOutliers);
		
		return calculateEnergy(best[Biexponential.PARAM_a1], best[Biexponential.PARAM_tau1]);
	}
	
	private void setBiexponentialFitData(BiexponentialFitData biexponentialFitData, double[] best, int nOutliers) {
		double v0 = best[Biexponential.PARAM_v0];
		UnitValue<FlowUnit> _v0 = new UnitValue<FlowUnit>(v0, FlowUnit.lPerSecond);
		double a1 = best[Biexponential.PARAM_a1];
		UnitValue<FlowUnit> _a1 = new UnitValue<FlowUnit>(a1, FlowUnit.lPerSecond);
		double a2 = best[Biexponential.PARAM_a2];
		UnitValue<FlowUnit> _a2 = new UnitValue<FlowUnit>(a2, FlowUnit.lPerSecond);
		double tau1 = best[Biexponential.PARAM_tau1];
		double tau2 = best[Biexponential.PARAM_tau2];
		
		biexponentialFitData.setV0(_v0);
		biexponentialFitData.setT0(nOutliers);
		biexponentialFitData.setA1(_a1);
		biexponentialFitData.setA2(_a2);
		biexponentialFitData.setTau1(tau1);
		biexponentialFitData.setTau2(tau2);
		
		Biexponential biexponentialCalculator    = new Biexponential(v0, a1, a2, tau1, tau2);
		double[] expectedOxygenConsumptionValues = new double[normalizedTimesArray.length - nOutliers];
		
		@SuppressWarnings("unchecked")
		UnitValue<FlowUnit>[] expectedOxygenConsumption = new UnitValue[normalizedTimesArray.length - nOutliers];

		double[] normalizedTimesSubArray = normalizeTimesArray(Arrays.copyOfRange(timesArray, nOutliers, timesArray.length));
		for (int i = 0; i < expectedOxygenConsumption.length; i++) {
			expectedOxygenConsumptionValues[i] = biexponentialCalculator.value(normalizedTimesSubArray[i]);
			expectedOxygenConsumption[i] = new UnitValue<FlowUnit>(expectedOxygenConsumptionValues[i], FlowUnit.lPerSecond);
		}
		
		biexponentialFitData.setExpectedOxygenConsumptions(expectedOxygenConsumption);
		
		RSquaredCalculator rSquaredCalculator = new RSquaredCalculator();
		double rSquared = rSquaredCalculator.calculate(consumptionArray, expectedOxygenConsumptionValues);
		biexponentialFitData.setRSquared(rSquared);
	}
	
	private double[] normalizeTimesArray(double[] times) {
		double[] normalizedTimes = new double[times.length];
		
		for (int i = 0; i < times.length; i++)
			normalizedTimes[i] = times[i] - times[0];
		return normalizedTimes;
	}
	
	public UnitValue<EnergyUnit> calculateEnergyWithMonoexponential(MonoexponentialFitData monoexponentialFitData){
		int nOutliers = 0;
		double   v0   = estimateBaselineOxygenConsumption();
		
		double[] observedValues       = new double[consumptionArray.length];
		double[] normalizedTimesArray = normalizeTimesArray(timesArray);
		double[] best                 = fitter.doExponentialFitWithFixedV0(consumptionArray, normalizedTimesArray, v0);
		Exponential exp               = new Exponential(best[Exponential.PARAM_v0], best[Exponential.PARAM_a], best[Exponential.PARAM_tau]);  
		
		for (int j = 0; j < consumptionArray.length; j++)
			observedValues[j] = exp.value(normalizedTimesArray[j]);
		double lastSumOfSquares = SquaredErrorsCalculator.calculate(observedValues, consumptionArray);
		
		for (int i = 1; i < consumptionArray.length && timesArray[i] - timesArray[0] <= MAX_TIME_DELAY; i++) {
			double[] consumptionSubArray     = Arrays.copyOfRange(consumptionArray, i, consumptionArray.length);
			double[] timesSubArray           = Arrays.copyOfRange(normalizedTimesArray, i, normalizedTimesArray.length);
			double[] normalizedTimesSubArray = normalizeTimesArray(timesSubArray);
			double[] bestCandidate           = fitter.doExponentialFitWithFixedV0(consumptionSubArray, normalizedTimesSubArray, v0);
			
			observedValues = new double[consumptionSubArray.length];
			exp = new Exponential(bestCandidate[Exponential.PARAM_v0], bestCandidate[Exponential.PARAM_a], bestCandidate[Exponential.PARAM_tau]);
			
			for (int j = 0; j < consumptionSubArray.length; j++)
				observedValues[j] = exp.value(normalizedTimesSubArray[j]);
			double sumOfErrors = SquaredErrorsCalculator.calculate(observedValues, consumptionSubArray);	
			
			if (sumOfErrors/lastSumOfSquares < 1 - EPS) {
				best = bestCandidate.clone();
				nOutliers = i;
				break;
			}
			lastSumOfSquares = sumOfErrors;
		}
		setMonoexponentialFitData(monoexponentialFitData, best, nOutliers);
		
		return calculateEnergy(best[Exponential.PARAM_a], best[Exponential.PARAM_tau]);
	}
	
	private void setMonoexponentialFitData(MonoexponentialFitData monoexponentialFitData, double[] best, int nOutliers) {
		double v0  = best[Exponential.PARAM_v0];
		UnitValue<FlowUnit> _v0 = new UnitValue<FlowUnit>(v0, FlowUnit.lPerSecond);
		double a   = best[Exponential.PARAM_a];
		UnitValue<FlowUnit> _a = new UnitValue<FlowUnit>(a, FlowUnit.lPerSecond);
		double tau = best[Exponential.PARAM_tau];

		monoexponentialFitData.setV0(_v0);
		monoexponentialFitData.setT0(nOutliers);
		monoexponentialFitData.setA(_a);
		monoexponentialFitData.setTau(tau);
		
		Exponential monoexponentialCalculator = new Exponential(v0, a, tau);
		double[] expectedOxygenConsumptionValues = new double[normalizedTimesArray.length - nOutliers];
		
		@SuppressWarnings("unchecked")
		UnitValue<FlowUnit>[] expectedOxygenConsumption = new UnitValue[normalizedTimesArray.length - nOutliers];
		
		double[] normalizedTimesSubArray = normalizeTimesArray(Arrays.copyOfRange(timesArray, nOutliers, timesArray.length));
		for (int i = 0; i < expectedOxygenConsumption.length; i++) {
			expectedOxygenConsumptionValues[i] = monoexponentialCalculator.value(normalizedTimesSubArray[i]);
			expectedOxygenConsumption[i] = new UnitValue<FlowUnit>(expectedOxygenConsumptionValues[i], FlowUnit.lPerSecond);
		}
		
		monoexponentialFitData.setExpectedOxygenConsumptions(expectedOxygenConsumption);
		
		RSquaredCalculator rSquaredCalculator = new RSquaredCalculator();
		double rSquared = rSquaredCalculator.calculate(consumptionArray, expectedOxygenConsumptionValues);
		monoexponentialFitData.setRSquared(rSquared);
	}

	public UnitValue<EnergyUnit> calculateEnergy(double a, double tau) {
		double energy = a * tau * Constants.OXYGEN_TO_KCAL;
		return new UnitValue<EnergyUnit>(energy, EnergyUnit.Kcal);
	}
}
