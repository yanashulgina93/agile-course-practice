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

    @Test(expected = IllegalArgumentException.class)
    public void nullThrowsInRhoInf() {
        float[] firstVector = null;
        float[] secondVector = {1, -2, 3};
        float result = distanceCalculator.calculateDistance(firstVector, secondVector,
                Metric.RHO_INF);
    }

    @Test(expected = IllegalArgumentException.class)
    public void vectorsWithDifferentSizesThrowsInRhoInf() {
        float[] firstVector = {3, -4, 5};
        float[] secondVector = {-6, 7, -8, 9};
        float result = distanceCalculator.calculateDistance(firstVector, secondVector,
                Metric.RHO_INF);
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

    @Test(expected = IllegalArgumentException.class)
    public void nullThrowsInRhoOne() {
        float[] firstVector = null;
        float[] secondVector = {1, -2, 3};
        float result = distanceCalculator.calculateDistance(firstVector, secondVector,
            Metric.RHO_ONE);
    }

    @Test(expected = IllegalArgumentException.class)
    public void vectorsWithDifferentSizesThrowsInRhoOne() {
        float[] firstVector = {3, -4, 5};
        float[] secondVector = {-6, 7, -8, 9};
        float result = distanceCalculator.calculateDistance(firstVector, secondVector,
            Metric.RHO_ONE);
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

    @Test(expected = IllegalArgumentException.class)
    public void nullThrowsInRhoTwo() {
        float[] firstVector = null;
        float[] secondVector = {1, -2, 3};
        float result = distanceCalculator.calculateDistance(firstVector, secondVector,
            Metric.RHO_TWO);
    }

    @Test(expected = IllegalArgumentException.class)
    public void vectorsWithDifferentSizesThrowsInRhoTwo() {
        float[] firstVector = {3, -4, 5};
        float[] secondVector = {-6, 7, -8, 9};
        float result = distanceCalculator.calculateDistance(firstVector, secondVector,
            Metric.RHO_TWO);
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

    @Test(expected = IllegalArgumentException.class)
    public void nullThrowsInRhoThree() {
        float[] firstVector = null;
        float[] secondVector = {1, -2, 3};
        float result = distanceCalculator.calculateDistance(firstVector, secondVector,
                Metric.RHO_THREE);
    }

    @Test(expected = IllegalArgumentException.class)
    public void vectorsWithDifferentSizesThrowsInRhoThree() {
        float[] firstVector = {3, -4, 5};
        float[] secondVector = {-6, 7, -8, 9};
        float result = distanceCalculator.calculateDistance(firstVector, secondVector,
                Metric.RHO_THREE);
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

    @Test(expected = IllegalArgumentException.class)
    public void nullThrowsInRhoFour() {
        float[] firstVector = null;
        float[] secondVector = {1, -2, 3};
        float result = distanceCalculator.calculateDistance(firstVector, secondVector,
                Metric.RHO_FOUR);
    }

    @Test(expected = IllegalArgumentException.class)
    public void vectorsWithDifferentSizesThrowsInRhoFour() {
        float[] firstVector = {3, -4, 5};
        float[] secondVector = {-6, 7, -8, 9};
        float result = distanceCalculator.calculateDistance(firstVector, secondVector,
                Metric.RHO_FOUR);
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
