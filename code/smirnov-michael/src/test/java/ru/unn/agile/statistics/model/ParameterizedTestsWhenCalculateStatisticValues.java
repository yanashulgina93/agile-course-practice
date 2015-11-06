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
    private Collection<IStatisticalResult> dataInstances;
    private double deltaForDoubleAssertEquals = 1e-3;
    private NumericalStatisticConverter numericalStatisticConverter;

    private Number[] currentTestingData;

    private double exactEnumeration;
    private double exactVariance;
    private IStatisticalResult testingEvent;
    private double exactProbabilityOfTestingEvent;


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
                                                          final double exactVariance,
                                                          final IStatisticalResult event,
                                                          final double exactProbabilityOfEvent) {
        this.currentTestingData = currentTestingData;
        this.exactEnumeration = exactEnumeration;
        this.exactVariance = exactVariance;
        this.testingEvent = event;
        this.exactProbabilityOfTestingEvent = exactProbabilityOfEvent;
    }

    @Test
    public void calculatedEnumerationOfNumericDataIsEqualToExactEnumeration() {
        setUpTest();

        double calculatedEnumeration = statisticsCalculator.calculateEnumeration();

        assertEquals(exactEnumeration, calculatedEnumeration, deltaForDoubleAssertEquals);
    }

    @Test
    public void calculatedVarianceOfNumericDataIsEqualToExactVariance() {
        setUpTest();

        double calculatedVariance = statisticsCalculator.calculateVariance();

        assertEquals(exactVariance, calculatedVariance, deltaForDoubleAssertEquals);
    }

    @Test
    public void calculatedProbabilityOfTestingEventOnNumericDataIsEqualToExactProbability() {
        setUpTest();

        double calculatedProbability =
                statisticsCalculator.calculateProbabilityOfEvent(testingEvent);

        assertEquals(calculatedProbability,
                exactProbabilityOfTestingEvent,
                deltaForDoubleAssertEquals);
    }



    private void setUpTest() {
        numericalStatisticConverter = new NumericalStatisticConverter(currentTestingData);
        dataInstances = numericalStatisticConverter.convert();
        statisticsCalculator = new StatisticValuesCalculator(dataInstances);
    }

    private static Object[] formTestDataInstance(final Number[] numericData,
                                                 final double exactEnumeration,
                                                 final double exactVariance,
                                                 final IStatisticalResult event,
                                                 final double exactProbabilityOfEvent) {

        Object[] testDataInstance = new Object[5];
        testDataInstance[0] = numericData;
        testDataInstance[1] = exactEnumeration;
        testDataInstance[2] = exactVariance;
        testDataInstance[3] = event;
        testDataInstance[4] = exactProbabilityOfEvent;

        return testDataInstance;
    }

    private static Object[] formTestDataInstanceFromEmptyIntegerArray() {
        Integer[] emptyIntegerArray = {};
        double exactEnumerationOfEmptyIntegerArray = 0.0;
        double exactVarianceOfEmptyIntegerArray = 0.0;
        IStatisticalResult testingEvent = new NumericStatisticalResult(1);
        double probabilityOfTestingEvent = 0.0;

        return formTestDataInstance(emptyIntegerArray,
                exactEnumerationOfEmptyIntegerArray,
                exactVarianceOfEmptyIntegerArray,
                testingEvent,
                probabilityOfTestingEvent);
    }

    private static Object[] formTestDataInstanceFromConstantIntegerArray() {
        Integer[] constantIntegerArray = {5, 5, 5, 5};
        double exactEnumerationOfConstantIntegerArray = 5.0;
        double exactVarianceOfConstantIntegerArray = 0.0;
        IStatisticalResult testingEvent = new NumericStatisticalResult(5);
        double probabilityOfTestingEvent = 1.0;

        return formTestDataInstance(constantIntegerArray,
                exactEnumerationOfConstantIntegerArray,
                exactVarianceOfConstantIntegerArray,
                testingEvent,
                probabilityOfTestingEvent);
    }

    private static Object[] formTestDataInstanceFromShortIntegerArray() {
        Integer[] shortIntegerArray = {-1, -1, 5, 8, 10, 4, 4, 8};
        double exactEnumerationOfShortIntegerArray = 4.625;
        double exactVarianceOfShortIntegerArray = 16.553;
        IStatisticalResult testingEvent = new NumericStatisticalResult(2);
        double probabilityOfTestingEvent = 0.0;

        return formTestDataInstance(shortIntegerArray,
                exactEnumerationOfShortIntegerArray,
                exactVarianceOfShortIntegerArray,
                testingEvent,
                probabilityOfTestingEvent);
    }

    private static Object[] formTestDataInstanceFromMonotoneIntegerArray() {
        Integer[] monotoneIntegerArray = new Integer[100];
        for (int i = 0; i < monotoneIntegerArray.length; i++) {
            monotoneIntegerArray[i] = i;
        }
        double exactEnumerationOfMonotoneIntegerArray = 49.5;
        double exactVarianceOfMonotoneIntegerArray = 841.667;
        IStatisticalResult testingEvent = new NumericStatisticalResult(15);
        double probabilityOfTestingEvent = 0.01;

        return formTestDataInstance(monotoneIntegerArray,
                exactEnumerationOfMonotoneIntegerArray,
                exactVarianceOfMonotoneIntegerArray,
                testingEvent,
                probabilityOfTestingEvent);
    }

    private static Object[] formTestDataInstanceFromFloatArrayContainedOneNumber() {
        Float[] floatArrayContainedOneNumber = {3.14f};
        double exactEnumerationOfFloatArrayContainedOneNumber = 3.14;
        double exactVarianceOfFloatArrayContainedOneNumber = 0.0;
        IStatisticalResult testingEvent = new NumericStatisticalResult(3.13);
        double probabilityOfTestingEvent = 0.0;

        return formTestDataInstance(floatArrayContainedOneNumber,
                exactEnumerationOfFloatArrayContainedOneNumber,
                exactVarianceOfFloatArrayContainedOneNumber,
                testingEvent,
                probabilityOfTestingEvent);
    }

    private static Object[] formTestDataInstanceFromShortFloatArray() {
        Float[] shortFloatArray = {1.f, 2.25f, 1.f, 2.25f, 2.25f, 2.25f};
        double exactEnumerationOfShortFloatArray = 11.0 / 6.0;
        double exactVarianceOfShortFloatArray = 0.417;
        IStatisticalResult testingEvent = new NumericStatisticalResult(2.25f);
        double probabilityOfTestingEvent = 4.0 / 6.0;

        return formTestDataInstance(shortFloatArray,
                exactEnumerationOfShortFloatArray,
                exactVarianceOfShortFloatArray,
                testingEvent,
                probabilityOfTestingEvent);
    }

    private static Object[] formTestDataInstanceFromDoubleArrayOfOneCosPeriod() {
        Double[] doubleArrayOfOneCosPeriod = new Double[1000];
        for (int i = 0; i < doubleArrayOfOneCosPeriod.length; i++) {
            doubleArrayOfOneCosPeriod[i] = Math.cos(i * 2 * Math.PI
                    / doubleArrayOfOneCosPeriod.length);
        }
        double exactEnumerationOfDoubleArrayOfOneCosPeriod = 0.0;
        double exactVarianceOfDoubleArrayOfOneCosPeriod = 0.5;
        IStatisticalResult testingEvent = new NumericStatisticalResult(-2.0);
        double probabilityOfTestingEvent = 0.0;

        return formTestDataInstance(doubleArrayOfOneCosPeriod,
                exactEnumerationOfDoubleArrayOfOneCosPeriod,
                exactVarianceOfDoubleArrayOfOneCosPeriod,
                testingEvent,
                probabilityOfTestingEvent);
    }
}
