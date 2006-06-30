package org.codehaus.jet.hypothesis.rejection.evaluators;

/**
 * Evaluator of response surface for specified betas and sample size.
 * <pre>
 * q(T)=b0 + b1/T + b2/T^2 + b3/T^3 + e
 * </pre>
 * @author Mauro Talevi
 */
public class URCResponseSurfaceEvaluator extends AbstractResponseSurfaceEvaluator {

    public double evaluate(double[] beta, int sampleSize, int[] params) {
        double value = 0;
        int model = params[0];
        int nreg = params[1];
        if (sampleSize == 0) {
            value = beta[0];
            return value;
        }
        switch (model) {
        case 2:
            value = powerSeries(beta, calculateX(sampleSize, 0), 3);
            break;
        case 3:
            value = powerSeries(beta, calculateX(sampleSize, 0), 4);
            break;
        case 4:
            value = powerSeries(beta, calculateX(sampleSize, nreg), 3);
            break;
        case 5:
            value = powerSeries(beta, calculateX(sampleSize, nreg), 4);
            break;
        default:
            throw new IllegalArgumentException("Invalid model type "+model);
        }
        return value;

    }
}