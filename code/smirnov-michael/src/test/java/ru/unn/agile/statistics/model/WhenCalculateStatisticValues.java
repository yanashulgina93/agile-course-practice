package ru.unn.agile.statistics.model;

import org.junit.Before;
import org.junit.Test;

import java.util.Collection;

import static org.junit.Assert.*;

public class WhenCalculateStatisticValues {

    private StatisticValuesCalculator statisticsCalculator;
    private double deltaForDoubleAssertEquals = 1e-3;
    private StatisticsConverter statisticsConverter;

    @Before
    public void preparing() {
        statisticsCalculator = new StatisticValuesCalculator();
        statisticsConverter = new StatisticsConverter();
    }

    @Test
    public void enumerationIsZeroWhenStatisticDataIsEmpty() {
        statisticsCalculator.setStatisticData(null);

        double enumeration = statisticsCalculator.calculateEnumeration();

        assertEqualsForDoublesWithStandardDelta(0.0, enumeration);
    }

    @Test
    public void statisticDataIsEmptyWhenConvertingIntArrayIsEmpty() {
        statisticsConverter.setData(null);
        Collection<IStatisticDataInstance> dataInstances =
                statisticsConverter.convertNumericalDataToStatistics();

        assertEquals(dataInstances, null);
    }

    @Test
    public void probabilityOfZeroIntegerEventWithoutDataIsZero() {
        Double[] data = null;
        IStatisticDataInstance event = new NumericStatisticDataInstance(0);
        formDataInstances(data);

        double probabilityOfEvent = statisticsCalculator.calculateProbabilityOfEvent(event);

        assertEqualsForDoublesWithStandardDelta(0.0, probabilityOfEvent);
    }

    @Test
    public void probabilityOfNullEventOnIntegerDataArrayIsZero() {
        formDataInstances(formIntegerArrayWithBigVariance());

        double probabilityOfEvent = statisticsCalculator.calculateProbabilityOfEvent(null);

        assertEqualsForDoublesWithStandardDelta(0.0, probabilityOfEvent);
    }

    @Test
    public void varianceWithoutDataIsZero() {
        Integer[] data = null;
        formDataInstances(data);

        double variance = statisticsCalculator.calculateVariance();

        assertEqualsForDoublesWithStandardDelta(0.0, variance);
    }

    @Test
    public void rawMomentOfThirdOrderWithoutDataIsZero() {
        Integer[] data = null;
        formDataInstances(data);

        double rawMomentOfThirdOrder = statisticsCalculator.calculateRawMoment(3);

        assertEqualsForDoublesWithStandardDelta(0.0, rawMomentOfThirdOrder);
    }

    @Test
    public void rawMomentOfFirstOrderOnIntegerArrayIsEqualToEnumeration() {
        formDataInstances(formIntegerArrayWithBigVariance());

        double rawMomentOfFirstOrder = statisticsCalculator.calculateRawMoment(1);
        double enumeration = statisticsCalculator.calculateEnumeration();

        assertEqualsForDoublesWithStandardDelta(rawMomentOfFirstOrder, enumeration);
    }

    @Test
    public void rawMomentOfSecondOrderSmallerThanFourthOrderWhenDataHasBigVariance() {
        formDataInstances(formIntegerArrayWithBigVariance());

        double rawMomentOfSecondOrder = statisticsCalculator.calculateRawMoment(2);
        double rawMomentOfFourthOrder = statisticsCalculator.calculateRawMoment(4);

        assertTrue(rawMomentOfSecondOrder < rawMomentOfFourthOrder);
    }

    @Test
    public void rawMomentOfSixOrderBiggerThanEighthOrderWhenDataHasSmallVariance() {
        formDataInstances(formDoubleArrayWithSmallVariance());

        double rawMomentOfSixOrder = statisticsCalculator.calculateRawMoment(6);
        double rawMomentOfEightOrder = statisticsCalculator.calculateRawMoment(8);

        assertTrue(rawMomentOfSixOrder > rawMomentOfEightOrder);
    }

    @Test
    public void rawMomentOfZeroOrderOnDoubleArrayIsEqualsToZero() {
        formDataInstances(formDoubleArrayWithSmallVariance());

        double rawMomentOfZeroOrder = statisticsCalculator.calculateRawMoment(0);
        assertEqualsForDoublesWithStandardDelta(0.0, rawMomentOfZeroOrder);
    }

    @Test
    public void rawMomentOfNegativeOrderOnIntegerArrayIsEqualsToZero() {
        formDataInstances(formIntegerArrayWithBigVariance());

        double rawMomentOfMinusFithOrder = statisticsCalculator.calculateRawMoment(-5);

        assertEqualsForDoublesWithStandardDelta(0.0, rawMomentOfMinusFithOrder);
    }

    @Test
    public void centralMomentOfEmptyDataEqualsToZero() {
        Integer[] data = null;
        formDataInstances(data);

        double centralMomentOfSecondOrder = statisticsCalculator.calculateCentralMoment(2);

        assertEqualsForDoublesWithStandardDelta(0.0, centralMomentOfSecondOrder);
    }

    @Test
    public void centralMomentOfConstantIntegerDataEqualsToZero() {
        Integer[] constantData = {1, 1, 1};
        formDataInstances(constantData);

        double centralMomentOfFirstOrder = statisticsCalculator.calculateCentralMoment(1);

        assertEqualsForDoublesWithStandardDelta(0.0, centralMomentOfFirstOrder);
    }

    @Test
    public void centralMomentOfSecondOrderEqualsToUnbiasedVariance() {
        Double[] data = formDoubleArrayWithSmallVariance();
        formDataInstances(data);

        double unbiasedVariance = ((double) (data.length - 1)) / data.length
                * statisticsCalculator.calculateVariance();
        double centralMomentOfSecondOrder = statisticsCalculator.calculateCentralMoment(2);

        assertEqualsForDoublesWithStandardDelta(unbiasedVariance, centralMomentOfSecondOrder);
    }

    @Test
    public void centralMomentOfSecondOrderSmallerThanFourthOrderWhenDataHasBigVariance() {
        formDataInstances(formIntegerArrayWithBigVariance());

        double centralMomentOfSecondOrder = statisticsCalculator.calculateCentralMoment(2);
        double centralMomentOfFourthOrder = statisticsCalculator.calculateCentralMoment(4);

        assertTrue(centralMomentOfSecondOrder < centralMomentOfFourthOrder);
    }

    @Test
    public void centralMomentOfSixOrderBiggerThanEighthOrderWhenDoubleDataArrayHasSmallVariance() {
        formDataInstances(formDoubleArrayWithSmallVariance());

        double centralMomentOfSixOrder = statisticsCalculator.calculateCentralMoment(6);
        double centralMomentOfEightOrder = statisticsCalculator.calculateCentralMoment(8);

        assertTrue(centralMomentOfSixOrder > centralMomentOfEightOrder);
    }

    @Test
    public void centralMomentOfNegativeOrderWithBigVariancedIntegerDataArrayIsEqualsToZero() {
        formDataInstances(formIntegerArrayWithBigVariance());

        double centralMomentOfMinusThirdOrder = statisticsCalculator.calculateCentralMoment(-3);

        assertEqualsForDoublesWithStandardDelta(0.0, centralMomentOfMinusThirdOrder);
    }

    @Test
    public void centralMomentOfZeroOrderWithSmallVariancedDoubleDataArrayIsEqualsToZero() {
        formDataInstances(formDoubleArrayWithSmallVariance());

        double centralMomentOfZeroOrder = statisticsCalculator.calculateCentralMoment(0);

        assertEqualsForDoublesWithStandardDelta(0.0, centralMomentOfZeroOrder);
    }

    private Integer[] formIntegerArrayWithBigVariance() {
        Integer[] result = {-10, -15, 51, 82, 10, 4, 4, 8, 2, 0, 0, 14};
        return result;
    }

    private Double[] formDoubleArrayWithSmallVariance() {
        Double[] result = {-0.1, -0.1, 0.5, 0.8, 1.0, 0.4, 0.4, 0.8};
        return result;
    }

    private void formDataInstances(final Number[] data) {
        statisticsConverter.setData(data);

        Collection<IStatisticDataInstance> dataInstances =
                statisticsConverter.convertNumericalDataToStatistics();

        statisticsCalculator.setStatisticData(dataInstances);
    }

    private void assertEqualsForDoublesWithStandardDelta(final double firstValue,
                                                         final double secondValue) {
        assertEquals(firstValue, secondValue, deltaForDoubleAssertEquals);
    }
}
