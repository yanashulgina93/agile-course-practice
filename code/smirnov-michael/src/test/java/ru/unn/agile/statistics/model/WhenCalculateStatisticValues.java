package ru.unn.agile.statistics.model;

import org.junit.Before;
import org.junit.Test;

import java.util.Collection;

import static org.junit.Assert.*;

public class WhenCalculateStatisticValues {

    private StatisticValuesCalculator statisticsCalculator;
    private Collection<IStatisticDataInstance> dataInstances;
    private double deltaForDoubleAssertEquals = 1e-3;
    private StatisticsConverter numericalConverter;

    @Before
    public void preparing() {
        statisticsCalculator = new StatisticValuesCalculator();
        numericalConverter = new StatisticsConverter();
        dataInstances = null;
    }

    @Test
    public void enumerationIsZeroWhenStatisticDataIsEmpty() {
        statisticsCalculator.setStatisticData(null);

        assertEqualsForDoublesWithStandardDelta(0.0,
                statisticsCalculator.calculateEnumeration());
    }

    @Test
    public void statisticDataIsEmptyWhenConvertingIntArrayIsEmpty() {
        numericalConverter.setData(null);
        Collection<IStatisticDataInstance> dataInstances =
                numericalConverter.convertNumericalDataToStatistics();

        assertEquals(dataInstances, null);
    }

    @Test
    public void probabilityOfSingleEventEqualsToOne() {
        Integer[] data = {7};
        IStatisticDataInstance event = new NumericStatisticDataInstance(data[0]);
        formDataInstances(data);

        assertEqualsForDoublesWithStandardDelta(1,
                statisticsCalculator.calculateProbabilityOfEvent(event));
    }

    @Test
    public void probabilityOfImpossibleEventEqualsToZero() {
        Float[] data = {1.f, 2.f, 3.f, 4.1f, 5.f, 6.f, 7.f, 8.f, 9.f, 10.f};
        IStatisticDataInstance event = new NumericStatisticDataInstance(4);
        formDataInstances(data);

        assertEqualsForDoublesWithStandardDelta(0.0,
                statisticsCalculator.calculateProbabilityOfEvent(event));
    }

    @Test
    public void probabilityOfSomePossibleEventIsCorrect() {
        Double[] data = {-1., 3.14, 5., 7.13, 8.25, 3.14};
        IStatisticDataInstance event = new NumericStatisticDataInstance(3.14f);
        formDataInstances(data);

        assertEqualsForDoublesWithStandardDelta(1. / 3,
                statisticsCalculator.calculateProbabilityOfEvent(event));
    }

    @Test
    public void probabilityWithoutDataIsZero() {
        Double[] data = null;
        IStatisticDataInstance event = new NumericStatisticDataInstance(0);
        formDataInstances(data);

        assertEqualsForDoublesWithStandardDelta(0.0,
                statisticsCalculator.calculateProbabilityOfEvent(event));
    }

    @Test
    public void varianceWithoutDataIsZero() {
        Integer[] data = null;
        formDataInstances(data);

        assertEqualsForDoublesWithStandardDelta(0.0,
                statisticsCalculator.calculateVariance());
    }

    @Test
    public void rawMomentOfThirdOrderWithoutDataIsZero() {
        Integer[] data = null;
        formDataInstances(data);

        assertEqualsForDoublesWithStandardDelta(0.0,
                statisticsCalculator.calculateRawMoment(3));
    }

    @Test
    public void rawMomentOfFirstOrderIsEqualToEnumeration() {
        formDataInstances(formArrayWithBigVariance());

        assertEqualsForDoublesWithStandardDelta(
                statisticsCalculator.calculateRawMoment(1),
                statisticsCalculator.calculateEnumeration());
    }

    @Test
    public void rawMomentOfSecondOrderSmallerThanFourthOrderWhenDataHasBigVariance() {
        formDataInstances(formArrayWithBigVariance());

        assertTrue(
                statisticsCalculator.calculateRawMoment(2)
                        < statisticsCalculator.calculateRawMoment(4)
        );
    }

    @Test
    public void rawMomentOfSixOrderBiggerThanEighthOrderWhenDataHasSmallVariance() {
        formDataInstances(formArrayWithSmallVariance());

        assertTrue(
                statisticsCalculator.calculateRawMoment(6)
                        > statisticsCalculator.calculateRawMoment(8)
        );
    }

    @Test
    public void rawMomentOfZeroOrderIsEqualsToZero() {
        formDataInstances(formArrayWithSmallVariance());

        assertEqualsForDoublesWithStandardDelta(0.0,
                statisticsCalculator.calculateRawMoment(0));
    }

    @Test
    public void rawMomentOfNegativeOrderIsEqualsToZero() {
        formDataInstances(formArrayWithBigVariance());

        assertEqualsForDoublesWithStandardDelta(0.0,
                statisticsCalculator.calculateRawMoment(-5));
    }

    @Test
    public void centralMomentOfEmptyDataEqualsToZero() {
        Integer[] data = null;
        formDataInstances(data);

        assertEqualsForDoublesWithStandardDelta(0.0,
                statisticsCalculator.calculateCentralMoment(2));
    }

    @Test
    public void centralMomentOfConstantDataEqualsToZero() {
        formDataInstances(formArrayWithBigVariance());

        assertEqualsForDoublesWithStandardDelta(0.0,
                statisticsCalculator.calculateCentralMoment(1));
    }

    @Test
    public void centralMomentOfSecondOrderEqualsToUnbiasedVariance() {
        Double[] data = formArrayWithSmallVariance();
        formDataInstances(data);

        double unbiasedVariance = ((double) (data.length - 1)) / data.length
                * statisticsCalculator.calculateVariance();
        assertEqualsForDoublesWithStandardDelta(unbiasedVariance,
                statisticsCalculator.calculateCentralMoment(2));
    }

    @Test
    public void centralMomentOfSecondOrderSmallerThanFourthOrderWhenDataHasBigVariance() {
        formDataInstances(formArrayWithBigVariance());

        assertTrue(
                statisticsCalculator.calculateCentralMoment(2)
                        < statisticsCalculator.calculateCentralMoment(4)
        );
    }

    @Test
    public void centralMomentOfSixOrderBiggerThanEighthOrderWhenDataHasSmallVariance() {
        formDataInstances(formArrayWithSmallVariance());

        assertTrue(
                statisticsCalculator.calculateCentralMoment(6)
                        > statisticsCalculator.calculateCentralMoment(8));
    }

    @Test
    public void centralMomentOfNegativeOrderIsEqualsToZero() {
        formDataInstances(formArrayWithBigVariance());

        assertEqualsForDoublesWithStandardDelta(0.0,
                statisticsCalculator.calculateCentralMoment(-3));
    }

    @Test
    public void centralMomentOfZeroOrderIsEqualsToZero() {
        formDataInstances(formArrayWithSmallVariance());

        assertEqualsForDoublesWithStandardDelta(0.0,
                statisticsCalculator.calculateCentralMoment(0));
    }

    private Integer[] formArrayWithBigVariance() {
        Integer[] result = {-10, -15, 51, 82, 10, 4, 4, 8, 2, 0, 0, 14};
        return result;
    }

    private Double[] formArrayWithSmallVariance() {
        Double[] result = {-0.1, -0.1, 0.5, 0.8, 1.0, 0.4, 0.4, 0.8};
        return result;
    }

    private void assertEqualsForDoublesWithStandardDelta(final double firstValue,
                                                         final double secondValue) {
        assertEquals(firstValue, secondValue, deltaForDoubleAssertEquals);
    }

    private void formDataInstances(final Number[] data) {
        numericalConverter.setData(data);

        Collection<IStatisticDataInstance> dataInstances =
                numericalConverter.convertNumericalDataToStatistics();

        statisticsCalculator.setStatisticData(dataInstances);
    }
}
