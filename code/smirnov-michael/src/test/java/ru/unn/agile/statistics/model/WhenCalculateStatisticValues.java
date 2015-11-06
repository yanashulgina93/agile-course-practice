package ru.unn.agile.statistics.model;

import org.junit.Test;
import java.util.Collection;
import static org.junit.Assert.*;

public class WhenCalculateStatisticValues {

    private StatisticValuesCalculator statisticsCalculator;
    private double deltaForDoubleAssertEquals = 1e-3;
    private NumericalStatisticConverter numericalStatisticConverter;


    @Test
    public void enumerationIsZeroWhenStatisticDataIsEmpty() {
        statisticsCalculator = new StatisticValuesCalculator(null);

        double enumeration = statisticsCalculator.calculateEnumeration();

        assertEquals(0.0, enumeration, deltaForDoubleAssertEquals);
    }

    @Test
    public void statisticDataIsEmptyWhenConvertingIntArrayIsEmpty() {
        numericalStatisticConverter = new NumericalStatisticConverter(null);
        Collection<IStatisticalResult> dataInstances = numericalStatisticConverter.convert();

        assertEquals(dataInstances, null);
    }

    @Test
    public void probabilityOfZeroIntegerEventWithoutDataIsZero() {
        Double[] data = null;
        IStatisticalResult event = new NumericStatisticalResult(0);
        formDataInstances(data);

        double probabilityOfEvent = statisticsCalculator.calculateProbabilityOfEvent(event);

        assertEquals(0.0, probabilityOfEvent, deltaForDoubleAssertEquals);
    }

    @Test
    public void probabilityOfNullEventOnIntegerDataArrayIsZero() {
        formDataInstances(formIntegerArrayWithBigVariance());

        double probabilityOfEvent = statisticsCalculator.calculateProbabilityOfEvent(null);

        assertEquals(0.0, probabilityOfEvent, deltaForDoubleAssertEquals);
    }

    @Test
    public void varianceWithoutDataIsZero() {
        Integer[] data = null;
        formDataInstances(data);

        double variance = statisticsCalculator.calculateVariance();

        assertEquals(0.0, variance, deltaForDoubleAssertEquals);
    }

    @Test
    public void rawMomentOfThirdOrderWithoutDataIsZero() {
        Integer[] data = null;
        formDataInstances(data);

        double rawMomentOfThirdOrder = statisticsCalculator.calculateRawMoment(3);

        assertEquals(0.0, rawMomentOfThirdOrder, deltaForDoubleAssertEquals);
    }

    @Test
    public void rawMomentOfFirstOrderOnIntegerArrayIsEqualToEnumeration() {
        formDataInstances(formIntegerArrayWithBigVariance());

        double rawMomentOfFirstOrder = statisticsCalculator.calculateRawMoment(1);
        double enumeration = statisticsCalculator.calculateEnumeration();

        assertEquals(rawMomentOfFirstOrder, enumeration, deltaForDoubleAssertEquals);
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

        assertEquals(0.0, rawMomentOfZeroOrder, deltaForDoubleAssertEquals);
    }

    @Test
    public void rawMomentOfNegativeOrderOnIntegerArrayIsEqualsToZero() {
        formDataInstances(formIntegerArrayWithBigVariance());

        double rawMomentOfMinusFithOrder = statisticsCalculator.calculateRawMoment(-5);

        assertEquals(0.0, rawMomentOfMinusFithOrder, deltaForDoubleAssertEquals);
    }

    @Test
    public void centralMomentOfEmptyDataEqualsToZero() {
        Integer[] data = null;
        formDataInstances(data);

        double centralMomentOfSecondOrder = statisticsCalculator.calculateCentralMoment(2);

        assertEquals(0.0, centralMomentOfSecondOrder, deltaForDoubleAssertEquals);
    }

    @Test
    public void centralMomentOfConstantIntegerDataEqualsToZero() {
        Integer[] constantData = {1, 1, 1};
        formDataInstances(constantData);

        double centralMomentOfFirstOrder = statisticsCalculator.calculateCentralMoment(1);

        assertEquals(0.0, centralMomentOfFirstOrder, deltaForDoubleAssertEquals);
    }

    @Test
    public void centralMomentOfSecondOrderEqualsToUnbiasedVariance() {
        Double[] data = formDoubleArrayWithSmallVariance();
        formDataInstances(data);

        double unbiasedVariance = ((double) (data.length - 1)) / data.length
                * statisticsCalculator.calculateVariance();
        double centralMomentOfSecondOrder = statisticsCalculator.calculateCentralMoment(2);

        assertEquals(unbiasedVariance, centralMomentOfSecondOrder, deltaForDoubleAssertEquals);
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

        assertEquals(0.0, centralMomentOfMinusThirdOrder, deltaForDoubleAssertEquals);
    }

    @Test
    public void centralMomentOfZeroOrderWithSmallVariancedDoubleDataArrayIsEqualsToZero() {
        formDataInstances(formDoubleArrayWithSmallVariance());

        double centralMomentOfZeroOrder = statisticsCalculator.calculateCentralMoment(0);

        assertEquals(0.0, centralMomentOfZeroOrder, deltaForDoubleAssertEquals);
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
        numericalStatisticConverter = new NumericalStatisticConverter(data);

        Collection<IStatisticalResult> dataInstances = numericalStatisticConverter.convert();

        statisticsCalculator = new StatisticValuesCalculator(dataInstances);
    }
}
