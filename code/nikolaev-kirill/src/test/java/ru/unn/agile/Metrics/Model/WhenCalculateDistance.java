package ru.unn.agile.Metrics.Model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class WhenCalculateDistance {

    private final float delta = 0.0001f;

    private DistanceCalculator distanceCalculator;

    @Before
    public void setUp() {
        distanceCalculator = new DistanceCalculator();
    }

    @Test
    public void nullGivesMinusOneInRhoInf() {
        float[] firstVector = null;
        float[] secondVector = {1, -2, 3};
        float result = distanceCalculator.calculateDistance(firstVector, secondVector,
                Metric.RHO_INF);
        assertEquals(-1.0f, result, delta);
    }

    @Test
    public void vectorsWithDifferentSizesGivesMinusOneInRhoInf() {
        float[] firstVector = {3, -4, 5};
        float[] secondVector = {-6, 7, -8, 9};
        float result = distanceCalculator.calculateDistance(firstVector, secondVector,
                Metric.RHO_INF);
        assertEquals(-1.0f, result, delta);
    }


    @Test
    public void sameVectorsGivesZeroInRhoInf() {
        float[] someVector = {1, -2 , 3};
        float result = distanceCalculator.calculateDistance(someVector, someVector,
                Metric.RHO_INF);
        assertEquals(0.0f, result, delta);
    }

    @Test
    public void canCalculateDistanceInRhoInf() {
        float[] firstVector = {4.1f, -5.2f, 6.3f, -7.4f};
        float[] secondVector = {3.1f, -9.2f, 8.3f, -2.4f};
        float result = distanceCalculator.calculateDistance(firstVector, secondVector,
            Metric.RHO_INF);
        assertEquals(5.0f, result, delta);
    }

    @Test
    public void nullGivesMinusOneInRhoOne() {
        float[] firstVector = null;
        float[] secondVector = {1, -2, 3};
        float result = distanceCalculator.calculateDistance(firstVector, secondVector,
            Metric.RHO_ONE);
        assertEquals(-1.0f, result, delta);
    }

    @Test
    public void vectorsWithDifferentSizesGivesMinusOneInRhoOne() {
        float[] firstVector = {3, -4, 5};
        float[] secondVector = {-6, 7, -8, 9};
        float result = distanceCalculator.calculateDistance(firstVector, secondVector,
            Metric.RHO_ONE);
        assertEquals(-1.0f, result, delta);
    }

    @Test
    public void sameVectorsGivesZeroInRhoOne() {
        float[] someVector = {1, -2, 3};
        float result = distanceCalculator.calculateDistance(someVector, someVector,
            Metric.RHO_ONE);
        assertEquals(0.0f, result, delta);
    }

    @Test
    public void canCalculateDistanceInRhoOne() {
        float[] firstVector = {4.1f, -5.2f, 6.3f, -7.4f};
        float[] secondVector = {3.1f, -9.2f, 8.3f, -2.4f};
        float result = distanceCalculator.calculateDistance(firstVector, secondVector,
            Metric.RHO_ONE);
        assertEquals(12.0f, result, delta);
    }

    @Test
    public void nullGivesMinusOneInRhoTwo() {
        float[] firstVector = null;
        float[] secondVector = {1, -2, 3};
        float result = distanceCalculator.calculateDistance(firstVector, secondVector,
            Metric.RHO_TWO);
        assertEquals(-1.0f, result, delta);
    }

    @Test
    public void vectorsWithDifferentSizesGivesMinusOneInRhoTwo() {
        float[] firstVector = {3, -4, 5};
        float[] secondVector = {-6, 7, -8, 9};
        float result = distanceCalculator.calculateDistance(firstVector, secondVector,
            Metric.RHO_TWO);
        assertEquals(-1.0f, result, delta);
    }

    @Test
    public void sameVectorsGivesZeroInRhoTwo() {
        float[] someVector = {1, -2, 3};
        float result = distanceCalculator.calculateDistance(someVector, someVector,
            Metric.RHO_TWO);
        assertEquals(0.0f, result, delta);
    }

    @Test
    public void canCalculateDistanceInRhoTwo() {
        float[] firstVector = {-4.1f, 6.3f, -7.4f, 0.5f};
        float[] secondVector = {-3.1f, 9.3f, -2.4f, 1.5f};
        float result = distanceCalculator.calculateDistance(firstVector, secondVector,
            Metric.RHO_TWO);
        assertEquals(6.0f, result, delta);
    }

    @Test
    public void nullGivesMinusOneInRhoThree() {
        float[] firstVector = null;
        float[] secondVector = {1, -2, 3};
        float result = distanceCalculator.calculateDistance(firstVector, secondVector,
                Metric.RHO_THREE);
        assertEquals(-1.0f, result, delta);
    }

    @Test
    public void vectorsWithDifferentSizesGivesMinusOneInRhoThree() {
        float[] firstVector = {3, -4, 5};
        float[] secondVector = {-6, 7, -8, 9};
        float result = distanceCalculator.calculateDistance(firstVector, secondVector,
                Metric.RHO_THREE);
        assertEquals(-1.0f, result, delta);
    }

    @Test
    public void sameVectorsGivesZeroInRhoThree() {
        float[] someVector = {1, -2, 3};
        float result = distanceCalculator.calculateDistance(someVector, someVector,
                Metric.RHO_THREE);
        assertEquals(0.0f, result, delta);
    }

    @Test
    public void canCalculateDistanceInRhoThree() {
        float[] firstVector = {-4.1f, 6.3f, -7.4f, 5.5f, -1.6f};
        float[] secondVector = {-3.1f, 8.3f, -4.4f, 2.5f, -0.6f};
        float result = distanceCalculator.calculateDistance(firstVector, secondVector,
                Metric.RHO_THREE);
        assertEquals(4.0f, result, delta);
    }

    @Test
    public void nullGivesMinusOneInRhoFour() {
        float[] firstVector = null;
        float[] secondVector = {1, -2, 3};
        float result = distanceCalculator.calculateDistance(firstVector, secondVector,
                Metric.RHO_FOUR);
        assertEquals(-1.0f, result, delta);
    }

    @Test
    public void vectorsWithDifferentSizesGivesMinusOneInRhoFour() {
        float[] firstVector = {3, -4, 5};
        float[] secondVector = {-6, 7, -8, 9};
        float result = distanceCalculator.calculateDistance(firstVector, secondVector,
                Metric.RHO_FOUR);
        assertEquals(-1.0f, result, delta);
    }

    @Test
    public void sameVectorsGivesZeroInRhoFour() {
        float[] someVector = {1, -2, 3};
        float result = distanceCalculator.calculateDistance(someVector, someVector,
                Metric.RHO_FOUR);
        assertEquals(0.0f, result, delta);
    }

    @Test
    public void canCalculateDistanceInRhoFour() {
        float[] firstVector = {-4.1f, 6.3f, -7.4f, 9.5f, -1.6f, 0.7f};
        float[] secondVector = {-5.1f, 4.3f, -5.4f, 7.5f, -3.6f, 2.7f};
        float result = distanceCalculator.calculateDistance(firstVector, secondVector,
                Metric.RHO_FOUR);
        assertEquals(3.0f, result, delta);
    }
}
