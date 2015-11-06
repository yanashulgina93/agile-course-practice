package ru.unn.agile.statistics.model;

import org.junit.Test;
import java.util.Collection;
import static org.junit.Assert.*;

public class WhenCalculateStatisticValues {

    private EnumerationCalculator enumerationCalculator;
    private VarianceCalculator varianceCalculator;
    private ProbabilityOfEventCalculator probabilityCalculator;
    private RawMomentCalculator rawMomentCalculator;
    private CentralMomentCalculator centralMomentCalculator;

    private double deltaForDoubleAssertEquals = 1e-3;
    private NumericalStatisticConverter numericalStatisticConverter;


    @Test
    public void enumerationIsZeroWhenStatisticDataIsEmpty() {
        enumerationCalculator = new EnumerationCalculator(null);

        double enumeration = enumerationCalculator.calculate();

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

        probabilityCalculator.setEvent(event);
        double probabilityOfEvent = probabilityCalculator.calculate();

        assertEquals(0.0, probabilityOfEvent, deltaForDoubleAssertEquals);
    }

    @Test
    public void probabilityOfNullEventOnIntegerDataArrayIsZero() {
        formDataInstances(formIntegerArrayWithBigVariance());

        probabilityCalculator.setEvent(null);
        double probabilityOfEvent = probabilityCalculator.calculate();

        assertEquals(0.0, probabilityOfEvent, deltaForDoubleAssertEquals);
    }

    @Test
    public void varianceWithoutDataIsZero() {
        Integer[] data = null;
        formDataInstances(data);

        double variance = varianceCalculator.calculate();

        assertEquals(0.0, variance, deltaForDoubleAssertEquals);
    }

    @Test
    public void rawMomentOfThirdOrderWithoutDataIsZero() {
        Integer[] data = null;
        formDataInstances(data);

        rawMomentCalculator.setOrder(3);
        double rawMomentOfThirdOrder = rawMomentCalculator.calculate();

        assertEquals(0.0, rawMomentOfThirdOrder, deltaForDoubleAssertEquals);
    }

    @Test
    public void rawMomentOfFirstOrderOnIntegerArrayIsEqualToEnumeration() {
        formDataInstances(formIntegerArrayWithBigVariance());

        rawMomentCalculator.setOrder(1);
        double rawMomentOfFirstOrder = rawMomentCalculator.calculate();
        double enumeration = enumerationCalculator.calculate();

        assertEquals(rawMomentOfFirstOrder, enumeration, deltaForDoubleAssertEquals);
    }

    @Test
    public void rawMomentOfSecondOrderSmallerThanFourthOrderWhenDataHasBigVariance() {
        formDataInstances(formIntegerArrayWithBigVariance());

        rawMomentCalculator.setOrder(2);
        double rawMomentOfSecondOrder = rawMomentCalculator.calculate();

        rawMomentCalculator.setOrder(4);
        double rawMomentOfFourthOrder = rawMomentCalculator.calculate();

        assertTrue(rawMomentOfSecondOrder < rawMomentOfFourthOrder);
    }

    @Test
    public void rawMomentOfSixOrderBiggerThanEighthOrderWhenDataHasSmallVariance() {
        formDataInstances(formDoubleArrayWithSmallVariance());

        rawMomentCalculator.setOrder(6);
        double rawMomentOfSixOrder = rawMomentCalculator.calculate();

        rawMomentCalculator.setOrder(8);
        double rawMomentOfEightOrder = rawMomentCalculator.calculate();

        assertTrue(rawMomentOfSixOrder > rawMomentOfEightOrder);
    }

    @Test
    public void rawMomentOfZeroOrderOnDoubleArrayIsEqualsToZero() {
        formDataInstances(formDoubleArrayWithSmallVariance());

        rawMomentCalculator.setOrder(0);
        double rawMomentOfZeroOrder = rawMomentCalculator.calculate();

        assertEquals(0.0, rawMomentOfZeroOrder, deltaForDoubleAssertEquals);
    }

    @Test
    public void rawMomentOfNegativeOrderOnIntegerArrayIsEqualsToZero() {
        formDataInstances(formIntegerArrayWithBigVariance());

        rawMomentCalculator.setOrder(-5);
        double rawMomentOfMinusFithOrder = rawMomentCalculator.calculate();

        assertEquals(0.0, rawMomentOfMinusFithOrder, deltaForDoubleAssertEquals);
    }

    @Test
    public void centralMomentOfEmptyDataEqualsToZero() {
        Integer[] data = null;
        formDataInstances(data);

        centralMomentCalculator.setOrder(2);
        double centralMomentOfSecondOrder = centralMomentCalculator.calculate();

        assertEquals(0.0, centralMomentOfSecondOrder, deltaForDoubleAssertEquals);
    }

    @Test
    public void centralMomentOfConstantIntegerDataEqualsToZero() {
        Integer[] constantData = {1, 1, 1};
        formDataInstances(constantData);

        centralMomentCalculator.setOrder(1);
        double centralMomentOfFirstOrder = centralMomentCalculator.calculate();

        assertEquals(0.0, centralMomentOfFirstOrder, deltaForDoubleAssertEquals);
    }

    @Test
    public void centralMomentOfSecondOrderEqualsToOfsetVariance() {
        Double[] data = formDoubleArrayWithSmallVariance();
        formDataInstances(data);

        double unbiasedVariance = ((double) (data.length - 1)) / data.length
                * varianceCalculator.calculate();

        centralMomentCalculator.setOrder(2);
        double centralMomentOfSecondOrder = centralMomentCalculator.calculate();

        assertEquals(unbiasedVariance, centralMomentOfSecondOrder, deltaForDoubleAssertEquals);
    }

    @Test
    public void centralMomentOfSecondOrderSmallerThanFourthOrderWhenDataHasBigVariance() {
        formDataInstances(formIntegerArrayWithBigVariance());

        centralMomentCalculator.setOrder(2);
        double centralMomentOfSecondOrder = centralMomentCalculator.calculate();

        centralMomentCalculator.setOrder(4);
        double centralMomentOfFourthOrder = centralMomentCalculator.calculate();

        assertTrue(centralMomentOfSecondOrder < centralMomentOfFourthOrder);
    }

    @Test
    public void centralMomentOfSixOrderBiggerThanEighthOrderWhenDoubleDataArrayHasSmallVariance() {
        formDataInstances(formDoubleArrayWithSmallVariance());

        centralMomentCalculator.setOrder(6);
        double centralMomentOfSixOrder = centralMomentCalculator.calculate();

        centralMomentCalculator.setOrder(8);
        double centralMomentOfEightOrder = centralMomentCalculator.calculate();

        assertTrue(centralMomentOfSixOrder > centralMomentOfEightOrder);
    }

    @Test
    public void centralMomentOfNegativeOrderWithBigVariancedIntegerDataArrayIsEqualsToZero() {
        formDataInstances(formIntegerArrayWithBigVariance());

        centralMomentCalculator.setOrder(-3);
        double centralMomentOfMinusThirdOrder = centralMomentCalculator.calculate();

        assertEquals(0.0, centralMomentOfMinusThirdOrder, deltaForDoubleAssertEquals);
    }

    @Test
    public void centralMomentOfZeroOrderWithSmallVariancedDoubleDataArrayIsEqualsToZero() {
        formDataInstances(formDoubleArrayWithSmallVariance());

        centralMomentCalculator.setOrder(0);
        double centralMomentOfZeroOrder = centralMomentCalculator.calculate();

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

        enumerationCalculator = new EnumerationCalculator(dataInstances);
        varianceCalculator = new VarianceCalculator(dataInstances);
        probabilityCalculator = new ProbabilityOfEventCalculator(dataInstances, null);
        rawMomentCalculator = new RawMomentCalculator(dataInstances, 0);
        centralMomentCalculator = new CentralMomentCalculator(dataInstances, 0);
    }
}
