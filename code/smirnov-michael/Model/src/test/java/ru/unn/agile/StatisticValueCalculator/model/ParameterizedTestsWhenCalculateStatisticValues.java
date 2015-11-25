package ru.unn.agile.StatisticValueCalculator.model;

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

    private MeanCalculator meanCalculator;
    private ProbabilityOfEventCalculator probabilityCalculator;
    private VarianceCalculator varianceCalculator;

    private double deltaForDoubleAssertEquals = 1e-3;

    private Collection<Double> currentTestingData;

    private double exactMean;
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
                                                          final double exactMean,
                                                          final double exactVariance,
                                                          final double event,
                                                          final double exactProbabilityOfEvent) {
        currentTestingData = new ArrayList<>();
        for (Number item : testingArray) {
            currentTestingData.add(item.doubleValue());
        }

        this.exactMean = exactMean;
        this.exactVariance = exactVariance;
        this.testingEvent = event;
        this.exactProbabilityOfTestingEvent = exactProbabilityOfEvent;
    }

    @Before
    public void setUpTest() {
        meanCalculator = new MeanCalculator();
        varianceCalculator = new VarianceCalculator();
        probabilityCalculator = new ProbabilityOfEventCalculator(testingEvent);
    }

    @Test
    public void calculatedMeanOfNumericDataIsEqualToExactMean() {
        double calculatedMean = meanCalculator.calculate(currentTestingData);

        assertEquals(exactMean, calculatedMean, deltaForDoubleAssertEquals);
    }

    @Test
    public void calculatedVarianceOfNumericDataIsEqualToExactVariance() {
        double calculatedVariance = varianceCalculator.calculate(currentTestingData);

        assertEquals(exactVariance, calculatedVariance, deltaForDoubleAssertEquals);
    }

    @Test
    public void calculatedProbabilityOfTestingEventOnNumericDataIsEqualToExactProbability() {
        double calculatedProbability = probabilityCalculator.calculate(currentTestingData);

        assertEquals(calculatedProbability,
                exactProbabilityOfTestingEvent,
                deltaForDoubleAssertEquals);
    }

    private static Object[] formTestData(final Number[] numericData,
                                         final double exactMean,
                                         final double exactVariance,
                                         final double event,
                                         final double exactProbabilityOfEvent) {

        Object[] testData = new Object[5];
        testData[0] = numericData;
        testData[1] = exactMean;
        testData[2] = exactVariance;
        testData[3] = event;
        testData[4] = exactProbabilityOfEvent;

        return testData;
    }

    private static Object[] formTestDataFromEmptyIntegerArray() {
        return formTestData(new Integer[]{}, 0.0, 0.0, 1.0, 0.0);
    }

    private static Object[] formTestDataFromConstantIntegerArray() {
        return formTestData(new Integer[] {5, 5, 5, 5}, 5.0, 0.0, 5.0, 1.0);
    }

    private static Object[] formTestDataFromShortIntegerArray() {
        return formTestData(new Integer[] {-1, -1, 5, 8, 10, 4, 4, 8}, 4.625, 16.553, 2.0, 0.0);
    }

    private static Object[] formTestDataFromMonotoneIntegerArray() {
        Integer[] monotoneIntegerArray = new Integer[100];
        for (int i = 0; i < monotoneIntegerArray.length; i++) {
            monotoneIntegerArray[i] = i;
        }

        return formTestData(monotoneIntegerArray, 49.5, 841.667, 15, 0.01);
    }

    private static Object[] formTestDataFromFloatArrayContainedOneNumber() {
        return formTestData(new Float[] {3.14f}, 3.14, 0.0, 3.13, 0.0);
    }

    private static Object[] formTestDataFromShortFloatArray() {
        return formTestData(new Float[] {1.f, 2.25f, 1.f, 2.25f, 2.25f, 2.25f},
                11.0 / 6.0, 0.417, 2.25f, 4.0 / 6.0);
    }

    private static Object[] formTestDataFromDoubleArrayOfOneCosPeriod() {
        Double[] doubleArrayOfOneCosPeriod = new Double[1000];
        for (int i = 0; i < doubleArrayOfOneCosPeriod.length; i++) {
            doubleArrayOfOneCosPeriod[i] = Math.cos(i * 2 * Math.PI
                    / doubleArrayOfOneCosPeriod.length);
        }

        return formTestData(doubleArrayOfOneCosPeriod, 0.0, 0.5, -2.0, 0.0);
    }
}
