package ru.unn.agile.statistics.model;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.util.ArrayList;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class ParameterizedTestsWhenCalculateStatisticValues {

    private StatisticValuesCalculator statisticsCalculator;
    private Collection<IStatisticDataInstance> dataInstances;
    private double deltaForDoubleAssertEquals = 1e-3;
    private StatisticsConverter statisticsConverter;

    private Number[] currentTestingData;
    private double exactEnumeration;
    private double exactVariance;

    @Before
    public void preparing() {
        statisticsCalculator = new StatisticValuesCalculator();
        statisticsConverter = new StatisticsConverter();
    }

    @Parameters
    public static Collection<Object[]> getDataForTesting() {

        ArrayList<Object[]> allTestingData = new ArrayList<>();
        allTestingData.add(formTestDataInstanceFromEmptyIntegerArray());
        allTestingData.add(formTestDataInstanceFromConstantIntegerArray());
        allTestingData.add(formTestDataInstanceFromShortIntegerArray());
        allTestingData.add(formTestDataInstanceFromMonotoneIntegerArray());
        allTestingData.add(formTestDataInstanceFromFloatArrayContainedOneNumber());
        allTestingData.add(formTestDataInstanceFromShortFloatArray());
        allTestingData.add(formTestDataInstanceFromDoubleArrayOfOneCosPeriod());

        return allTestingData;
    }

    public ParameterizedTestsWhenCalculateStatisticValues(final Number[] currentTestingData,
                                                          final double exactEnumeration,
                                                          final double exactVariance) {
        this.currentTestingData = currentTestingData;
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

    private void setUpTest() {
        statisticsConverter.setData(currentTestingData);
        dataInstances = statisticsConverter.convertNumericalDataToStatistics();
        statisticsCalculator.setStatisticData(dataInstances);
    }

    private static Object[] formTestDataInstance(final Number[] numericData,
                                                 final double exactEnumeration,
                                                 final double exactVariance) {
        Object[] testDataInstance = new Object[3];
        testDataInstance[0] = numericData;
        testDataInstance[1] = exactEnumeration;
        testDataInstance[2] = exactVariance;

        return testDataInstance;
    }

    private static Object[] formTestDataInstanceFromEmptyIntegerArray() {
        Integer[] emptyIntegerArray = {};
        double exactEnumerationOfEmptyIntegerArray = 0.0;
        double exactVarianceOfEmptyIntegerArray = 0.0;

        return formTestDataInstance(emptyIntegerArray,
                exactEnumerationOfEmptyIntegerArray,
                exactVarianceOfEmptyIntegerArray);
    }

    private static Object[] formTestDataInstanceFromConstantIntegerArray() {
        Integer[] constantIntegerArray = {5, 5, 5, 5};
        double exactEnumerationOfConstantIntegerArray = 5.0;
        double exactVarianceOfConstantIntegerArray = 0.0;

        return formTestDataInstance(constantIntegerArray,
                exactEnumerationOfConstantIntegerArray,
                exactVarianceOfConstantIntegerArray);
    }

    private static Object[] formTestDataInstanceFromShortIntegerArray() {
        Integer[] shortIntegerArray = {-1, -1, 5, 8, 10, 4, 4, 8};
        double exactEnumerationOfShortIntegerArray = 4.625;
        double exactVarianceOfShortIntegerArray = 16.553;

        return formTestDataInstance(shortIntegerArray,
                exactEnumerationOfShortIntegerArray,
                exactVarianceOfShortIntegerArray);
    }

    private static Object[] formTestDataInstanceFromMonotoneIntegerArray() {
        Integer[] monotoneIntegerArray = new Integer[100];
        for (int i = 0; i < monotoneIntegerArray.length; i++) {
            monotoneIntegerArray[i] = i;
        }
        double exactEnumerationOfMonotoneIntegerArray = 49.5;
        double exactVarianceOfMonotoneIntegerArray = 841.667;

        return formTestDataInstance(monotoneIntegerArray,
                exactEnumerationOfMonotoneIntegerArray,
                exactVarianceOfMonotoneIntegerArray);
    }

    private static Object[] formTestDataInstanceFromFloatArrayContainedOneNumber() {
        Float[] floatArrayContainedOneNumber = {3.14f};
        double exactEnumerationOfFloatArrayContainedOneNumber = 3.14;
        double exactVarianceOfFloatArrayContainedOneNumber = 0.0;

        return formTestDataInstance(floatArrayContainedOneNumber,
                exactEnumerationOfFloatArrayContainedOneNumber,
                exactVarianceOfFloatArrayContainedOneNumber);
    }

    private static Object[] formTestDataInstanceFromShortFloatArray() {
        Float[] shortFloatArray = {1.f, 2.25f, 1.f, 2.25f, 2.25f, 2.25f};
        double exactEnumerationOfShortFloatArray = 11.0 / 6.0;
        double exactVarianceOfShortFloatArray = 0.417;

        return formTestDataInstance(shortFloatArray,
                exactEnumerationOfShortFloatArray,
                exactVarianceOfShortFloatArray);
    }

    private static Object[] formTestDataInstanceFromDoubleArrayOfOneCosPeriod() {
        Double[] doubleArrayOfOneCosPeriod = new Double[1000];
        for (int i = 0; i < doubleArrayOfOneCosPeriod.length; i++) {
            doubleArrayOfOneCosPeriod[i] = Math.cos(i * 2 * Math.PI
                    / doubleArrayOfOneCosPeriod.length);
        }
        double exactEnumerationOfDoubleArrayOfOneCosPeriod = 0.0;
        double exactVarianceOfDoubleArrayOfOneCosPeriod = 0.5;

        return formTestDataInstance(doubleArrayOfOneCosPeriod,
                exactEnumerationOfDoubleArrayOfOneCosPeriod,
                exactVarianceOfDoubleArrayOfOneCosPeriod);
    }

    private void assertEqualsForDoublesWithStandardDelta(final double firstValue,
                                                         final double secondValue) {
        assertEquals(firstValue, secondValue, deltaForDoubleAssertEquals);
    }
}
