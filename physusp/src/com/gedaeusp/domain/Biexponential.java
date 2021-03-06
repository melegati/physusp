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

import org.apache.commons.math3.analysis.differentiation.DerivativeStructure;
import org.apache.commons.math3.analysis.differentiation.UnivariateDifferentiableFunction;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.util.FastMath;


/**
 * f(t) = v0 + a1 * exp( -(t)/tau1 ) + a2 * exp( -(t)/tau2 ) 
**/
public class Biexponential implements UnivariateDifferentiableFunction {

	public static final int PARAM_v0 = 0;
	public static final int PARAM_a1 = 1;
	public static final int PARAM_a2 = 2;
	public static final int PARAM_tau1 = 3;
	public static final int PARAM_tau2 = 4;

	private final double v0;
	private final double a1;
	private final double a2;
	private final double tau1;
	private final double tau2;

	public Biexponential(double v0, double a1, double a2, double tau1, double tau2) {
		this.v0 = v0;
		this.a1 = a1;
		this.a2 = a2;
		this.tau1 = tau1;
		this.tau2 = tau2;
	}

	@Override
	public double value(double t) {
		return value(t, v0, a1, a2, tau1, tau2);
	}

	public static double value(double t, double v0, double a1,
			double a2, double tau1, double tau2) {
		return v0 + a1 * FastMath.exp(-t / tau1) + 
		            a2 * FastMath.exp(-t / tau2);
	}

	@Override
	public DerivativeStructure value(DerivativeStructure t) throws MathIllegalArgumentException {
		return t.exp();
	}


}
