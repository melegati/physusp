package com.gedaeusp.domain;

import org.apache.commons.math3.analysis.ParametricUnivariateFunction;
import org.apache.commons.math3.analysis.differentiation.DerivativeStructure;
import org.apache.commons.math3.analysis.differentiation.UnivariateDifferentiableFunction;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.util.FastMath;

/**
 * A function of the form: {@code V(t) = V0 + A * exp(-t/tau)}. {@code V(t)} is
 * the oxygen uptake at time {@code t}. {@code V0}, {@code A} and {@code tau}
 * are the oxygen uptake at baseline, the amplitude and the time constant,
 * respectively.
 * 
 * As proposed in the article
 * "Energy system contributions in indoor rock climbing", equations [1] and [2].
 * 
 * @see "R. C. deM. Bertuzzi et al., Energy system contributions in indoor rock climbing, 2007, Springer-Verlag"
 * @author igortopcin
 */
public class Exponential implements UnivariateDifferentiableFunction {

	public static final int PARAM_v0 = 0;
	public static final int PARAM_a = 1;
	public static final int PARAM_tau = 2;

	/** The oxygen uptake at baseline */
	private final double v0;

	/** The amplitude */
	private final double a;

	/** Time constant */
	private final double tau;

	public Exponential(double v0, double a, double tau) {
		this.v0 = v0;
		this.a = a;
		this.tau = tau;
	}

	@Override
	public double value(double t) {
		return value(t, v0, a, tau);
	}

	private static double value(double t, double v0, double a, double tau) {
		return v0 + a * FastMath.exp(-t / tau);
	}

	@Override
	public DerivativeStructure value(DerivativeStructure t)
			throws MathIllegalArgumentException {
		return t.exp();
	}

	/**
	 * A class representing a mono-exponential function that depends on one
	 * independent variable plus some extra parameters.
	 * 
	 * This is meant to be used by a curve fitter.
	 * 
	 * Use {@link Exponential.ParametricBuilder} to create
	 * {@link Exponential.Parametric} instances.
	 * 
	 * @see {@link Exponential.ParametricBuilder}
	 */
	public static class Parametric implements ParametricUnivariateFunction {

		private boolean fixedV0;

		/**
		 * Constructor that takes a builder as parameter in order to create a
		 * {@link Exponential.Parametric} instance.
		 */
		public Parametric(Exponential.ParametricBuilder builder) {
			fixedV0 = builder.fixedV0;
		}

		/**
		 * Compute the value of the function.
		 */
		@Override
		public double value(double t, double... params) {
			double v0 = params[0];
			double a = params[1];
			double tau = params[2];

			return Exponential.value(t, v0, a, tau);
		}

		/**
		 * Compute the gradient of the function with respect to its parameters.
		 */
		@Override
		public double[] gradient(double t, double... params) {
			double a = params[PARAM_a];
			double tau = params[PARAM_tau];

			// We do not optimize v0 if fixedV0 is true
			double dv0 = fixedV0 ? 0 : 1;

			// We optimize the rest of the parameters, that is, A and Tau
			double da = FastMath.exp(-t / tau);
			double dTau = a * t * FastMath.exp(-t / tau) / FastMath.pow(tau, 2);
			return new double[] { dv0, da, dTau };
		}

	}

	/**
	 * A Builder class for {@link Exponential.Parametric}.
	 */
	public static class ParametricBuilder {

		private boolean fixedV0;

		/**
		 * Tell the curve fitter not to optimize the {@code v0} parameter, that
		 * is, the passed in initial value for {@code v0} will remain untouched.
		 */
		public ParametricBuilder fixedV0(boolean fixedV0) {
			this.fixedV0 = fixedV0;
			return this;
		}

		/**
		 * Creates a {@link Exponential.Parametric}
		 */
		public Exponential.Parametric build() {
			return new Exponential.Parametric(this);
		}

	}

}
