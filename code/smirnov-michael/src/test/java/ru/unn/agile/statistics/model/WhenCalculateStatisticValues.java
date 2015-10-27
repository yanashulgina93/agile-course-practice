package ru.unn.agile.statistics.model;

import org.junit.Before;
import org.junit.Test;

import java.util.Collection;

import static org.junit.Assert.*;

public class WhenCalculateStatisticValues {
    @Before
    public void preparing()
    {
        statisticsCalculator = new StatisticValuesCalculator();
        dataInstances = null;
    }

    @Test
    public void enumerationOfSimpleIntArrayIsCorrect(){
        int[] data = {5, 5, 5, 5};
        formDataInstances(data);

        assertEqualsForDoublesWithStandardDelta(5.0, statisticsCalculator.calculateEnumerationOfStatistics());
    }

    @Test
    public void enumerationOfIntArrayWithDifferentElementsIsCorrect()
    {
        formDataInstances(formBigIntArray());

        assertEqualsForDoublesWithStandardDelta(49.5, statisticsCalculator.calculateEnumerationOfStatistics());
    }

    @Test
    public void enumerationOfDoubleArrayWithDataOfOneCosPeriodIsCorrect()
    {
        formDataInstances(formBigDoubleArray());

        assertEqualsForDoublesWithStandardDelta(0.0, statisticsCalculator.calculateEnumerationOfStatistics());
    }

    @Test
    public void enumerationOfFloatArrayWithOneElementIsCorrect(){
        float[] data = {3.14f};
        formDataInstances(data);

        assertEqualsForDoublesWithStandardDelta(3.14f, statisticsCalculator.calculateEnumerationOfStatistics());
    }

    @Test
    public void enumerationIsZeroWhenStatisticDataIsEmpty() {
        statisticsCalculator.setUsedStatistics(null);

        assertEqualsForDoublesWithStandardDelta(0.0, statisticsCalculator.calculateEnumerationOfStatistics());
    }

    @Test
    public void statisticDataIsEmptyWhenConvertingIntArrayIsEmpty(){
        Collection<IStatisticDataInstance> dataInstances = StatisticDataConverter.convertFromIntArray(null);
        assertEquals(dataInstances, null);
    }

    @Test
    public void probabilityOfSingleEventEqualsToOne(){
        int[] data = {7};
        IStatisticDataInstance event = new NumericStatisticDataInstance(data[0]);
        formDataInstances(data);

        assertEqualsForDoublesWithStandardDelta(1, statisticsCalculator.calculateProbabilityInStatisticsOfEvent(event));
    }

    @Test
    public void probabilityOfImpossibleEventEqualsToZero(){
        float[] data = {1.f, 2.f, 3.f, 4.1f, 5.f, 6.f, 7.f, 8.f, 9.f, 10.f};
        IStatisticDataInstance event = new NumericStatisticDataInstance(4);
        formDataInstances(data);

        assertEqualsForDoublesWithStandardDelta(0.0, statisticsCalculator.calculateProbabilityInStatisticsOfEvent(event));
    }

    @Test
    public void probabilityOfSomePossibleEventIsCorrect(){
        double[] data = {-1., 3.14, 5., 7.13, 8.25, 3.14};
        IStatisticDataInstance event = new NumericStatisticDataInstance(3.14f);
        formDataInstances(data);

        assertEqualsForDoublesWithStandardDelta((1. / 3), statisticsCalculator.calculateProbabilityInStatisticsOfEvent(event));
    }

    @Test
    public void probabilityWithoutDataIsZero(){
        double[] data = null;
        IStatisticDataInstance event = new NumericStatisticDataInstance(0);
        formDataInstances(data);

        assertEqualsForDoublesWithStandardDelta(0.0, statisticsCalculator.calculateProbabilityInStatisticsOfEvent(event));
    }

    @Test
    public void varianceOfConstantDataIsZero(){
        int[] data = {1, 1, 1, 1, 1};
        formDataInstances(data);

        assertEqualsForDoublesWithStandardDelta(statisticsCalculator.calculateVarianceOfStatistics(), 0.0);
    }

    @Test
    public void varianceWithoutDataIsZero(){
        int[] data = null;
        formDataInstances(data);

        assertEqualsForDoublesWithStandardDelta(statisticsCalculator.calculateVarianceOfStatistics(), 0.0);
    }

    @Test
    public void varianceOfSingleDataInstanceIsZero(){
        double[] data = {1.72};
        formDataInstances(data);

        assertEqualsForDoublesWithStandardDelta(statisticsCalculator.calculateVarianceOfStatistics(), 0.0);
    }

    @Test
    public void varianceOfDataRepresentsOneCosPeriodIsEqualToOneHalf(){
        formDataInstances(formBigDoubleArray());

        assertEqualsForDoublesWithStandardDelta(statisticsCalculator.calculateVarianceOfStatistics(), (1. / 2));
    }

    @Test
    public void rawMomentOfThirdOrderWithoutDataIsZero(){
        int[] data = null;
        formDataInstances(data);

        assertEqualsForDoublesWithStandardDelta(statisticsCalculator.calculateRawMomentOfStatistics(3), 0.0);
    }

    @Test
    public void rawMomentOfFirstOrderIsEqualToEnumeration(){
        double[] data = new double[1000];
        for(int i = 0; i < data.length; i++){
            data[i] = Math.log10((i + 1) * Math.PI / 1000.0);
        }
        formDataInstances(data);

        assertEqualsForDoublesWithStandardDelta(statisticsCalculator.calculateRawMomentOfStatistics(1), statisticsCalculator.calculateEnumerationOfStatistics());
    }

    @Test
    public void rawMomentOfSecondOrderSmallerThanFourthOrderWhenDataHasBigVariance(){
        formDataInstances(formShortIntArray());

        assertTrue(statisticsCalculator.calculateRawMomentOfStatistics(2) < statisticsCalculator.calculateRawMomentOfStatistics(4));
    }

    @Test
    public void rawMomentOfSixOrderBiggerThanEighthOrderWhenDataHasSmallVariance(){
        formDataInstances(formShortDoubleArray());

        assertTrue(statisticsCalculator.calculateRawMomentOfStatistics(6) > statisticsCalculator.calculateRawMomentOfStatistics(8));
    }

    @Test
    public void rawMomentOfZeroOrderIsEqualsToZero(){
        formDataInstances(formShortDoubleArray());

        assertEqualsForDoublesWithStandardDelta(statisticsCalculator.calculateRawMomentOfStatistics(0), 0.0);
    }

    @Test
    public void rawMomentOfNegativeOrderIsEqualsToZero(){
        formDataInstances(formShortFloatArray());

        assertEqualsForDoublesWithStandardDelta(statisticsCalculator.calculateRawMomentOfStatistics(-5), 0.0);
    }

    @Test
    public void centralMomentOfEmptyDataEqualsToZero(){
        int[] data = null;
        formDataInstances(data);

        assertEqualsForDoublesWithStandardDelta(statisticsCalculator.calculateCentralMomentOfStatistics(2), 0.0);
    }

    @Test
    public void centralMomentOfConstantDataEqualsToZero(){
        formDataInstances(formShortFloatArray());

        assertEqualsForDoublesWithStandardDelta(statisticsCalculator.calculateCentralMomentOfStatistics(1), 0.0);
    }

    @Test
    public void centralMomentOfSecondOrderEqualsToUnbiasedVariance(){
        double[] data = formShortDoubleArray();
        formDataInstances(data);

        double unbiasedVariance = ((double)(data.length-1))/data.length * statisticsCalculator.calculateVarianceOfStatistics();
        assertEqualsForDoublesWithStandardDelta(statisticsCalculator.calculateCentralMomentOfStatistics(2), unbiasedVariance);
    }

    @Test
    public void centralMomentOfSecondOrderSmallerThanFourthOrderWhenDataHasBigVariance(){
        formDataInstances(formShortIntArray());

        assertTrue(statisticsCalculator.calculateCentralMomentOfStatistics(2) < statisticsCalculator.calculateCentralMomentOfStatistics(4));
    }

    @Test
    public void centralMomentOfSixOrderBiggerThanEighthOrderWhenDataHasSmallVariance(){
        formDataInstances(formShortDoubleArray());

        assertTrue(statisticsCalculator.calculateCentralMomentOfStatistics(6) > statisticsCalculator.calculateCentralMomentOfStatistics(8));
    }

    @Test
    public void centralMomentOfNegativeOrderIsEqualsToZero(){
        formDataInstances(formShortIntArray());

        assertEqualsForDoublesWithStandardDelta(statisticsCalculator.calculateCentralMomentOfStatistics(-3), 0.0);
    }

    @Test
    public void centralMomentOfZeroOrderIsEqualsToZero(){
        formDataInstances(formShortDoubleArray());

        assertEqualsForDoublesWithStandardDelta(statisticsCalculator.calculateCentralMomentOfStatistics(0), 0.0);
    }

    private int[] formShortIntArray(){
        int[] result = {-1, -1, 5, 8, 10, 4, 4, 8};
        return result;
    }

    private int[] formBigIntArray(){
        int[] result = new int[100];
        for(int i = 0; i < result.length; i++){
            result[i] = i;
        }
        return result;
    }

    private float[] formShortFloatArray(){
        float[] result = {1.f, 2.f, 3.14f, 5.25f, 6.37f};
        return result;
    }

    private double[] formShortDoubleArray() {
        double[] result = {-0.1, -0.1, 0.5, 0.8, 1.0, 0.4, 0.4, 0.8};
        return result;
    }

    private double[] formBigDoubleArray() {
        double[] result = new double[1000];
        for(int i = 0; i < result.length; i++){
            result[i] = Math.cos(i * 2 * Math.PI / 1000.0);
        }
        return result;
    }

    private void assertEqualsForDoublesWithStandardDelta(double firstValue, double secondValue){
        assertEquals(firstValue, secondValue, deltaForDoubleAssertEquals);
    }

    private void formDataInstances(double[] data){
        Collection<IStatisticDataInstance> dataInstances = StatisticDataConverter.convertFromDoubleArray(data);
        statisticsCalculator.setUsedStatistics(dataInstances);
    }

    private void formDataInstances(float[] data){
        Collection<IStatisticDataInstance> dataInstances = StatisticDataConverter.convertFromFloatArray(data);
        statisticsCalculator.setUsedStatistics(dataInstances);
    }

    private void formDataInstances(int[] data){
        Collection<IStatisticDataInstance> dataInstances = StatisticDataConverter.convertFromIntArray(data);
        statisticsCalculator.setUsedStatistics(dataInstances);
    }

    private StatisticValuesCalculator statisticsCalculator;
    private Collection<IStatisticDataInstance> dataInstances;
    private double deltaForDoubleAssertEquals = 1e-3;
}
