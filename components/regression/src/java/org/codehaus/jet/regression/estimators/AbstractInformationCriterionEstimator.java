package org.codehaus.jet.regression.estimators;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.codehaus.jet.regression.InformationCriterionEstimator;
import org.codehaus.jet.regression.MultipleLinearRegressionEstimator;

/**
 * Abstract base class for implementations of InformationCriterionEstimator
 * 
 * @author Mauro Talevi
 */
public abstract class AbstractInformationCriterionEstimator implements
        InformationCriterionEstimator {

    private MultipleLinearRegressionEstimator regression;
    
    private double[] y;

    /**
     * Creates an AbstractInformationCriterionEstimator with a defaultregression estimator
     */
    protected AbstractInformationCriterionEstimator() {
        this(createDefaultRegressionEstimator());
    }

    /**
     * Creates an AbstractInformationCriterionEstimator with a given regression estimator
     * @param regression the GeneralLinearRegression
     */
    protected AbstractInformationCriterionEstimator(MultipleLinearRegressionEstimator regression) {
        this.regression = regression;
    }

    public void addData(double[] y) {
        this.y = y;
    }

    @SuppressWarnings("unchecked")
    public int minimiseIC(int minLag, int maxLag) {
        // calculate IC for the given lag interval
        Map<Integer,Double> map = new HashMap<Integer,Double>();
        for (int lag = minLag; lag < maxLag; lag++) {
            map.put(lag, estimateIC(lag));
        }

        // sort map entries in ascending order
        Set set = map.entrySet();
        Map.Entry[] entries = (Map.Entry[]) set.toArray(new Map.Entry[set
                .size()]);
        Arrays.sort(entries, new Comparator() {
            public int compare(final Object o1, final Object o2) {
                final Object v1 = ((Map.Entry) o1).getValue();
                final Object v2 = ((Map.Entry) o2).getValue();
                return ((Comparable) v1).compareTo(v2);
            }
        });
        // get first entry key as the lag minimising the ICs
        int min = (Integer)entries[0].getKey();
        return min;
    }

    protected int getSampleSize(){
        return y.length;
    }
    
    /**
     * Calculates the variance on the sample for a given lag order
     * @param p the lag order
     * @return The variance obtained from the regression
     */
    protected double calculateYVariance(int p){      
        regression.addData(toRegressands(y, p), toRegressors(y, p), null);
        return regression.estimateRegressandVariance();
    }

    /**
     * Converts sample to regression regressand
     * 
     * @param y the sample
     * @param p the lag order
     * @return The [n-p,1] array of regressands
     */
    protected double[] toRegressands(double[] y, int p) {
        int n = y.length;
        double[] regressands = new double[n-p];
        System.arraycopy(y, p, regressands, 0, n-p);
        return regressands;
    }

    /**
     * Converts sample to regression regressors
     * 
     * @param y the sample
     * @param p the lag order
     * @return The [n-p,p] array of regressors
     */
    protected double[][] toRegressors(double[] y, int p) {
        int n = y.length;
        double[][] regressors = new double[n-p][p];
        for (int pos = 0; pos < n - p; pos++) {
            double[] row = new double[p];
            System.arraycopy(y, pos, row, 0, p);
            regressors[pos] = reverse(row);
        }               
        return regressors;
    }

    private double[] reverse(double[] row) {
    	int length = row.length;
    	double[] reversed = new double[length];
		for (int i = 0; i < row.length; i++) {
			reversed[i] = row[length - i - 1];
		}    	
		return reversed;
	}

	public double estimateIC(int p) {
        int T = getSampleSize();
        double var = calculateYVariance(p);
        double value = calculateIC(p, T, var);
        return value;
    }

    protected abstract double calculateIC(int p, int t, double var) ;

    protected static MultipleLinearRegressionEstimator createDefaultRegressionEstimator(){
        return new OLSMultipleLinearRegressionEstimator();
    }
}
