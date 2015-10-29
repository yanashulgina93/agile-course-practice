package ru.unn.agile.statistics.model;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class ParameterizedTestsWhenCalculateStatisticValues {

    private Number[] currentTestingData;
    private double exactEnumeration;
    private double exactVariance;

    private StatisticValuesCalculator statisticsCalculator;
    private Collection<IStatisticDataInstance> dataInstances;
    private double deltaForDoubleAssertEquals = 1e-3;
    private StatisticsConverter numericalConverter;

    @Before
    public void preparing() {
        statisticsCalculator = new StatisticValuesCalculator();
        numericalConverter = new StatisticsConverter();
    }

    @Parameters
    public static Collection<Object[]> getDataForTesting() {

        Integer[] emptyIntegerArray = {};
        double exactEnumerationOfEmptyIntegerArray = 0.0;
        double exactVarianceOfEmptyIntegerArray = 0.0;

        Integer[] constantIntegerArray = {5, 5, 5, 5};
        double exactEnumerationOfConstantIntegerArray = 5.0;
        double exactVarianceOfConstantIntegerArray = 0.0;

        Integer[] shortIntegerArray = {-1, -1, 5, 8, 10, 4, 4, 8};
        double exactEnumerationOfShortIntegerArray = 4.625;
        double exactVarianceOfShortIntegerArray = 16.553;

        Integer[] monotoneIntegerArray = new Integer[100];
        for (int i = 0; i < monotoneIntegerArray.length; i++) {
            monotoneIntegerArray[i] = i;
        }
        double exactEnumerationOfMonotoneIntegerArray = 49.5;
        double exactVarianceOfMonotoneIntegerArray = 841.667;

        Float[] floatArrayContainedOneNumber = {3.14f};
        double exactEnumerationOfFloatArrayContainedOneNumber = 3.14;
        double exactVarianceOfFloatArrayContainedOneNumber = 0.0;

        Float[] shortFloatArray = {1.f, 2.25f, 1.f, 2.25f, 2.25f, 2.25f};
        double exactEnumerationOfShortFloatArray = 11.0/6.0;
        double exactVarianceOfShortFloatArray = 0.417;

        Double[] doubleArrayOfOneCosPeriod = new Double[1000];
        for (int i = 0; i < doubleArrayOfOneCosPeriod.length; i++) {
            doubleArrayOfOneCosPeriod[i] = Math.cos(i * 2 * Math.PI
                    / doubleArrayOfOneCosPeriod.length);
        }
        double exactEnumerationOfDoubleArrayOfOneCosPeriod = 0.0;
        double exactVarianceOfDoubleArrayOfOneCosPeriod = 0.5;

        Object[][] allTestingData = {
                {emptyIntegerArray,
                        exactEnumerationOfEmptyIntegerArray,
                        exactVarianceOfEmptyIntegerArray},
                {constantIntegerArray,
                        exactEnumerationOfConstantIntegerArray,
                        exactVarianceOfConstantIntegerArray},
                {shortIntegerArray,
                        exactEnumerationOfShortIntegerArray,
                        exactVarianceOfShortIntegerArray},
                {monotoneIntegerArray,
                        exactEnumerationOfMonotoneIntegerArray,
                        exactVarianceOfMonotoneIntegerArray},
                {floatArrayContainedOneNumber,
                        exactEnumerationOfFloatArrayContainedOneNumber,
                        exactVarianceOfFloatArrayContainedOneNumber},
                {shortFloatArray,
                        exactEnumerationOfShortFloatArray,
                        exactVarianceOfShortFloatArray},
                {doubleArrayOfOneCosPeriod,
                        exactEnumerationOfDoubleArrayOfOneCosPeriod,
                        exactVarianceOfDoubleArrayOfOneCosPeriod}};

        return Arrays.asList(allTestingData);
    }

    public ParameterizedTestsWhenCalculateStatisticValues(Number[] currentTestingData,
                                                          double exactEnumeration,
                                                          double exactVariance) {
        this.currentTestingData =currentTestingData;
        this.exactEnumeration = exactEnumeration;
        this.exactVariance = exactVariance;
    }

    @Test
    public void calculatedEnumerationOfNumericDataIsEqualToExactEnumeration() {
        setUpTest();

        double calculatedEnumeration = statisticsCalculator.calculateEnumeration();

        assertEqualsForDoublesWithStandardDelta(exactEnumeration, calculatedEnumeration);
    }

    @Test
    public void calculatedVarianceOfNumericDataIsEqualToExactVariance() {
        setUpTest();

        double calculatedVariance = statisticsCalculator.calculateVariance();

        assertEqualsForDoublesWithStandardDelta(exactVariance, calculatedVariance);
    }

    private void assertEqualsForDoublesWithStandardDelta(final double firstValue,
                                                         final double secondValue) {
        assertEquals(firstValue, secondValue, deltaForDoubleAssertEquals);
    }

    private void setUpTest() {
        numericalConverter.setData(currentTestingData);
        dataInstances = numericalConverter.convertNumericalDataToStatistics();
        statisticsCalculator.setStatisticData(dataInstances);
    }
}
