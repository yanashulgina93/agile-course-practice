package ru.unn.agile.StatisticValueCalculator.model;

import org.junit.Test;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Collection;
import static org.junit.Assert.*;

public class WhenCalculateStatisticValues {

    private EnumerationCalculator enumerationCalculator;
    private VarianceCalculator varianceCalculator;
    private ProbabilityOfEventCalculator probabilityCalculator;
    private RawMomentCalculator rawMomentCalculator;
    private CentralMomentCalculator centralMomentCalculator;
    private Collection<Double> testingData;

    private double deltaForDoubleAssertEquals = 1e-3;

    @Test
    public void enumerationIsZeroWhenStatisticDataIsEmpty() {
        enumerationCalculator = new EnumerationCalculator();
        ArrayList<Double> emptyData = new ArrayList<>();

        double enumeration = enumerationCalculator.calculate(emptyData);

        assertEquals(0.0, enumeration, deltaForDoubleAssertEquals);
    }

    @Test (expected = NullPointerException.class)
    public void throwsWhenCalculatingProbabilityOfEventOnNullData() {
        Double[] data = null;
        Double event = (double)0;
        formTestingData(data);

        probabilityCalculator = new ProbabilityOfEventCalculator(event);

        double probabilityOfEvent = probabilityCalculator.calculate(testingData);

        assertEquals(0.0, probabilityOfEvent, deltaForDoubleAssertEquals);
    }

    @Test (expected = NullPointerException.class)
    public void throwsWhenCalculatingVarianceOfNullData() {
        varianceCalculator = new VarianceCalculator();
        varianceCalculator.calculate(null);
    }

    @Test (expected = NullPointerException.class)
    public void throwsWhenCalculatingRawMomentOfNullData() {
        Integer[] data = null;
        formTestingData(data);

        rawMomentCalculator = new RawMomentCalculator(3);
        rawMomentCalculator.calculate(testingData);
    }

    @Test
    public void rawMomentOfFirstOrderOnIntegerArrayIsEqualToEnumeration() {
        formTestingData(formIntegerArrayWithBigVariance());

        rawMomentCalculator = new RawMomentCalculator(1);
        enumerationCalculator = new EnumerationCalculator();

        double rawMomentOfFirstOrder = rawMomentCalculator.calculate(testingData);
        double enumeration = enumerationCalculator.calculate(testingData);

        assertEquals(rawMomentOfFirstOrder, enumeration, deltaForDoubleAssertEquals);
    }

    @Test
    public void rawMomentOfSecondOrderSmallerThanFourthOrderWhenDataHasBigVariance() {
        formTestingData(formIntegerArrayWithBigVariance());

        rawMomentCalculator = new RawMomentCalculator(2);
        double rawMomentOfSecondOrder = rawMomentCalculator.calculate(testingData);

        rawMomentCalculator = new RawMomentCalculator(4);
        double rawMomentOfFourthOrder = rawMomentCalculator.calculate(testingData);

        assertTrue(rawMomentOfSecondOrder < rawMomentOfFourthOrder);
    }

    @Test
    public void rawMomentOfSixOrderBiggerThanEighthOrderWhenDataHasSmallVariance() {
        formTestingData(formDoubleArrayWithSmallVariance());

        rawMomentCalculator = new RawMomentCalculator(6);
        double rawMomentOfSixOrder = rawMomentCalculator.calculate(testingData);

        rawMomentCalculator = new RawMomentCalculator(8);
        double rawMomentOfEightOrder = rawMomentCalculator.calculate(testingData);

        assertTrue(rawMomentOfSixOrder > rawMomentOfEightOrder);
    }

    @Test (expected = InvalidParameterException.class)
    public void throwsWhenInstantiateRawMomentOfZeroOrder() {
        rawMomentCalculator = new RawMomentCalculator(0);
    }

    @Test (expected = InvalidParameterException.class)
    public void throwsWhenInstantiateRawMomentWithNegativeOrder() {
        rawMomentCalculator = new RawMomentCalculator(-5);
    }

    @Test (expected = NullPointerException.class)
    public void throwsWhenCalculatingCentralMomentOfSecondOrderOnNullData() {
        centralMomentCalculator = new CentralMomentCalculator(2);
        centralMomentCalculator.calculate(null);
    }

    @Test
    public void centralMomentOfConstantIntegerDataEqualsToZero() {
        Integer[] constantData = {1, 1, 1};
        formTestingData(constantData);

        centralMomentCalculator = new CentralMomentCalculator(1);
        double centralMomentOfFirstOrder = centralMomentCalculator.calculate(testingData);

        assertEquals(0.0, centralMomentOfFirstOrder, deltaForDoubleAssertEquals);
    }

    @Test
    public void centralMomentOfSecondOrderEqualsToOffsetVariance() {
        Double[] data = formDoubleArrayWithSmallVariance();
        formTestingData(data);

        varianceCalculator = new VarianceCalculator();
        double unbiasedVariance = ((double) (testingData.size() - 1)) / testingData.size()
                * varianceCalculator.calculate(testingData);

        centralMomentCalculator = new CentralMomentCalculator(2);
        double centralMomentOfSecondOrder = centralMomentCalculator.calculate(testingData);

        assertEquals(unbiasedVariance, centralMomentOfSecondOrder, deltaForDoubleAssertEquals);
    }

    @Test
    public void centralMomentOfSecondOrderSmallerThanFourthOrderWhenDataHasBigVariance() {
        formTestingData(formIntegerArrayWithBigVariance());

        centralMomentCalculator = new CentralMomentCalculator(2);
        double centralMomentOfSecondOrder = centralMomentCalculator.calculate(testingData);

        centralMomentCalculator = new CentralMomentCalculator(4);
        double centralMomentOfFourthOrder = centralMomentCalculator.calculate(testingData);

        assertTrue(centralMomentOfSecondOrder < centralMomentOfFourthOrder);
    }

    @Test
    public void centralMomentOfSixOrderBiggerThanEighthOrderWhenDoubleDataArrayHasSmallVariance() {
        formTestingData(formDoubleArrayWithSmallVariance());

        centralMomentCalculator = new CentralMomentCalculator(6);
        double centralMomentOfSixOrder = centralMomentCalculator.calculate(testingData);

        centralMomentCalculator = new CentralMomentCalculator(8);
        double centralMomentOfEightOrder = centralMomentCalculator.calculate(testingData);

        assertTrue(centralMomentOfSixOrder > centralMomentOfEightOrder);
    }

    @Test (expected = InvalidParameterException.class)
    public void throwsWhenInstantiateCentralMomentWithNegativeOrder() {
        centralMomentCalculator = new CentralMomentCalculator(-3);
    }

    @Test (expected = InvalidParameterException.class)
    public void throwsWhenInstantiateCentralMomentOfZeroOrder() {
        centralMomentCalculator = new CentralMomentCalculator(0);
    }

    @Test
    public  void centralMomentOfThirdOrderOnEmptyDataEqualsToZero() {
        centralMomentCalculator = new CentralMomentCalculator(3);
        ArrayList<Double> emptyData = new ArrayList<>();

        double centralMomentOfThirdOrder = centralMomentCalculator.calculate(emptyData);
        assertEquals(centralMomentOfThirdOrder, 0.0, deltaForDoubleAssertEquals);
    }

    private Integer[] formIntegerArrayWithBigVariance() {
        return new Integer[]{-10, -15, 51, 82, 10, 4, 4, 8, 2, 0, 0, 14};
    }

    private Double[] formDoubleArrayWithSmallVariance() {
        return new Double[]{-0.1, -0.1, 0.5, 0.8, 1.0, 0.4, 0.4, 0.8};
    }

    private void formTestingData(final Number[] data) {
        if (data== null) {
            testingData = null;
            return;
        }

        testingData = new ArrayList<>();
        for(Number item : data) {
            testingData.add(item.doubleValue());
        }
    }
}
