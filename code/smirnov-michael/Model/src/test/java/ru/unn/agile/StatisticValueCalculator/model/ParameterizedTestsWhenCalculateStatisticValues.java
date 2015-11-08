package ru.unn.agile.StatisticValueCalculator.model;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.util.ArrayList;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class ParameterizedTestsWhenCalculateStatisticValues {

    private EnumerationCalculator enumerationCalculator;
    private ProbabilityOfEventCalculator probabilityCalculator;
    private VarianceCalculator varianceCalculator;

    private double deltaForDoubleAssertEquals = 1e-3;

    private Collection<Double> currentTestingData;

    private double exactEnumeration;
    private double exactVariance;
    private double testingEvent;
    private double exactProbabilityOfTestingEvent;


    @Parameters
    public static Collection<Object[]> getDataForTesting() {

        ArrayList<Object[]> allTestingData = new ArrayList<>();
        allTestingData.add(formTestDataFromEmptyIntegerArray());
        allTestingData.add(formTestDataFromConstantIntegerArray());
        allTestingData.add(formTestDataFromShortIntegerArray());
        allTestingData.add(formTestDataFromMonotoneIntegerArray());
        allTestingData.add(formTestDataFromFloatArrayContainedOneNumber());
        allTestingData.add(formTestDataFromShortFloatArray());
        allTestingData.add(formTestDataFromDoubleArrayOfOneCosPeriod());

        return allTestingData;
    }

    public ParameterizedTestsWhenCalculateStatisticValues(final Number[] testingArray,
                                                          final double exactEnumeration,
                                                          final double exactVariance,
                                                          final double event,
                                                          final double exactProbabilityOfEvent) {
        currentTestingData = new ArrayList<>();
        for(Number item : testingArray) {
            currentTestingData.add(item.doubleValue());
        }

        this.exactEnumeration = exactEnumeration;
        this.exactVariance = exactVariance;
        this.testingEvent = event;
        this.exactProbabilityOfTestingEvent = exactProbabilityOfEvent;
    }

    @Test
    public void calculatedEnumerationOfNumericDataIsEqualToExactEnumeration() {
        setUpTest();

        double calculatedEnumeration = enumerationCalculator.calculate(currentTestingData);

        assertEquals(exactEnumeration, calculatedEnumeration, deltaForDoubleAssertEquals);
    }

    @Test
    public void calculatedVarianceOfNumericDataIsEqualToExactVariance() {
        setUpTest();

        double calculatedVariance = varianceCalculator.calculate(currentTestingData);

        assertEquals(exactVariance, calculatedVariance, deltaForDoubleAssertEquals);
    }

    @Test
    public void calculatedProbabilityOfTestingEventOnNumericDataIsEqualToExactProbability() {
        setUpTest();

        double calculatedProbability = probabilityCalculator.calculate(currentTestingData);

        assertEquals(calculatedProbability,
                exactProbabilityOfTestingEvent,
                deltaForDoubleAssertEquals);
    }



    private void setUpTest() {
        enumerationCalculator = new EnumerationCalculator();
        varianceCalculator = new VarianceCalculator();
        probabilityCalculator = new ProbabilityOfEventCalculator(testingEvent);
    }

    private static Object[] formTestData(final Number[] numericData,
                                         final double exactEnumeration,
                                         final double exactVariance,
                                         final double event,
                                         final double exactProbabilityOfEvent) {

        Object[] testData = new Object[5];
        testData[0] = numericData;
        testData[1] = exactEnumeration;
        testData[2] = exactVariance;
        testData[3] = event;
        testData[4] = exactProbabilityOfEvent;

        return testData;
    }

    private static Object[] formTestDataFromEmptyIntegerArray() {
        Integer[] emptyIntegerArray = {};
        double exactEnumerationOfEmptyIntegerArray = 0.0;
        double exactVarianceOfEmptyIntegerArray = 0.0;
        double testingEvent = 1;
        double probabilityOfTestingEvent = 0.0;

        return formTestData(emptyIntegerArray,
                exactEnumerationOfEmptyIntegerArray,
                exactVarianceOfEmptyIntegerArray,
                testingEvent,
                probabilityOfTestingEvent);
    }

    private static Object[] formTestDataFromConstantIntegerArray() {
        Integer[] constantIntegerArray = {5, 5, 5, 5};
        double exactEnumerationOfConstantIntegerArray = 5.0;
        double exactVarianceOfConstantIntegerArray = 0.0;
        double testingEvent = 5;
        double probabilityOfTestingEvent = 1.0;

        return formTestData(constantIntegerArray,
                exactEnumerationOfConstantIntegerArray,
                exactVarianceOfConstantIntegerArray,
                testingEvent,
                probabilityOfTestingEvent);
    }

    private static Object[] formTestDataFromShortIntegerArray() {
        Integer[] shortIntegerArray = {-1, -1, 5, 8, 10, 4, 4, 8};
        double exactEnumerationOfShortIntegerArray = 4.625;
        double exactVarianceOfShortIntegerArray = 16.553;
        double testingEvent = 2;
        double probabilityOfTestingEvent = 0.0;

        return formTestData(shortIntegerArray,
                exactEnumerationOfShortIntegerArray,
                exactVarianceOfShortIntegerArray,
                testingEvent,
                probabilityOfTestingEvent);
    }

    private static Object[] formTestDataFromMonotoneIntegerArray() {
        Integer[] monotoneIntegerArray = new Integer[100];
        for (int i = 0; i < monotoneIntegerArray.length; i++) {
            monotoneIntegerArray[i] = i;
        }
        double exactEnumerationOfMonotoneIntegerArray = 49.5;
        double exactVarianceOfMonotoneIntegerArray = 841.667;
        double testingEvent = 15;
        double probabilityOfTestingEvent = 0.01;

        return formTestData(monotoneIntegerArray,
                exactEnumerationOfMonotoneIntegerArray,
                exactVarianceOfMonotoneIntegerArray,
                testingEvent,
                probabilityOfTestingEvent);
    }

    private static Object[] formTestDataFromFloatArrayContainedOneNumber() {
        Float[] floatArrayContainedOneNumber = {3.14f};
        double exactEnumerationOfFloatArrayContainedOneNumber = 3.14;
        double exactVarianceOfFloatArrayContainedOneNumber = 0.0;
        double testingEvent = 3.13;
        double probabilityOfTestingEvent = 0.0;

        return formTestData(floatArrayContainedOneNumber,
                exactEnumerationOfFloatArrayContainedOneNumber,
                exactVarianceOfFloatArrayContainedOneNumber,
                testingEvent,
                probabilityOfTestingEvent);
    }

    private static Object[] formTestDataFromShortFloatArray() {
        Float[] shortFloatArray = {1.f, 2.25f, 1.f, 2.25f, 2.25f, 2.25f};
        double exactEnumerationOfShortFloatArray = 11.0 / 6.0;
        double exactVarianceOfShortFloatArray = 0.417;
        double testingEvent = 2.25f;
        double probabilityOfTestingEvent = 4.0 / 6.0;

        return formTestData(shortFloatArray,
                exactEnumerationOfShortFloatArray,
                exactVarianceOfShortFloatArray,
                testingEvent,
                probabilityOfTestingEvent);
    }

    private static Object[] formTestDataFromDoubleArrayOfOneCosPeriod() {
        Double[] doubleArrayOfOneCosPeriod = new Double[1000];
        for (int i = 0; i < doubleArrayOfOneCosPeriod.length; i++) {
            doubleArrayOfOneCosPeriod[i] = Math.cos(i * 2 * Math.PI
                    / doubleArrayOfOneCosPeriod.length);
        }
        double exactEnumerationOfDoubleArrayOfOneCosPeriod = 0.0;
        double exactVarianceOfDoubleArrayOfOneCosPeriod = 0.5;
        double testingEvent = -2.0;
        double probabilityOfTestingEvent = 0.0;

        return formTestData(doubleArrayOfOneCosPeriod,
                exactEnumerationOfDoubleArrayOfOneCosPeriod,
                exactVarianceOfDoubleArrayOfOneCosPeriod,
                testingEvent,
                probabilityOfTestingEvent);
    }
}
