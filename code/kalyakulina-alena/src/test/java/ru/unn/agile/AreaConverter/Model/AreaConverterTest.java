package ru.unn.agile.AreaConverter.Model;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class AreaConverterTest {

    private static final double DELTA = 1e-10;

    private AreaConverter converter = new AreaConverter();

    @Test
    public void isZeroConvertedToZero() {
        double resultArea = converter.fromTo(AreaMeasure.ARE, AreaMeasure.HECTARE, 0.0);
        assertEquals(resultArea, 0.0, DELTA);
    }

    @Test
    public void isCorrectConvertedFromMeterToMeter() {
        double resultArea = converter.fromTo(AreaMeasure.SQ_M, AreaMeasure.SQ_M, 1.0);
        assertEquals(resultArea, 1.0, DELTA);
    }

    @Test
    public void isCorrectConvertedFromMeterToKilometer() {
        double resultArea = converter.fromTo(AreaMeasure.SQ_M, AreaMeasure.SQ_KM, 152.0);
        assertEquals(resultArea, 0.000152, DELTA);
    }

    @Test
    public void isCorrectConvertedFromMeterToAre() {
        double resultArea = converter.fromTo(AreaMeasure.SQ_M, AreaMeasure.ARE, 10.0);
        assertEquals(resultArea, 0.1, DELTA);
    }

    @Test
    public void isCorrectConvertedFromMeterToHectare() {
        double resultArea = converter.fromTo(AreaMeasure.SQ_M, AreaMeasure.HECTARE, 0.5);
        assertEquals(resultArea, 0.00005, DELTA);
    }

    @Test
    public void isCorrectConvertedFromKilometerToMeter() {
        double resultArea = converter.fromTo(AreaMeasure.SQ_KM, AreaMeasure.SQ_M, 7.5);
        assertEquals(resultArea, 7500000.0, DELTA);
    }

    @Test
    public void isCorrectConvertedFromKilometerToKilometer() {
        double resultArea = converter.fromTo(AreaMeasure.SQ_KM, AreaMeasure.SQ_KM, 1.11);
        assertEquals(resultArea, 1.11, DELTA);
    }

    @Test
    public void isCorrectConvertedFromKilometerToAre() {
        double resultArea = converter.fromTo(AreaMeasure.SQ_KM, AreaMeasure.ARE, 25.0);
        assertEquals(resultArea, 250000.0, DELTA);
    }

    @Test
    public void isCorrectConvertedFromKilometerToHectare() {
        double resultArea = converter.fromTo(AreaMeasure.SQ_KM, AreaMeasure.HECTARE, 0.01);
        assertEquals(resultArea, 1.0, DELTA);
    }

    @Test
    public void isCorrectConvertedFromAreToMeter() {
        double resultArea = converter.fromTo(AreaMeasure.ARE, AreaMeasure.SQ_M, 500.0);
        assertEquals(resultArea, 50000.0, DELTA);
    }

    @Test
    public void isCorrectConvertedFromAreToKilometer() {
        double resultArea = converter.fromTo(AreaMeasure.ARE, AreaMeasure.SQ_KM, 6.4);
        assertEquals(resultArea, 0.00064, DELTA);
    }

    @Test
    public void isCorrectConvertedFromAreToAre() {
        double resultArea = converter.fromTo(AreaMeasure.ARE, AreaMeasure.ARE, 300.0);
        assertEquals(resultArea, 300.0, DELTA);
    }

    @Test
    public void isCorrectConvertedFromAreToHectare() {
        double resultArea = converter.fromTo(AreaMeasure.ARE, AreaMeasure.HECTARE, 12.3);
        assertEquals(resultArea, 0.123, DELTA);
    }

    @Test
    public void isCorrectConvertedFromHectareToMeter() {
        double resultArea = converter.fromTo(AreaMeasure.HECTARE, AreaMeasure.SQ_M, 0.2);
        assertEquals(resultArea, 2000.0, DELTA);
    }

    @Test
    public void isCorrectConvertedFromHectareToKilometer() {
        double resultArea = converter.fromTo(AreaMeasure.HECTARE, AreaMeasure.SQ_KM, 78.0);
        assertEquals(resultArea, 0.78, DELTA);
    }

    @Test
    public void isCorrectConvertedFromHectareToAre() {
        double resultArea = converter.fromTo(AreaMeasure.HECTARE, AreaMeasure.ARE, 0.01);
        assertEquals(resultArea, 1.0, DELTA);
    }

    @Test
    public void isCorrectConvertedFromHectareToHectare() {
        double resultArea = converter.fromTo(AreaMeasure.HECTARE, AreaMeasure.HECTARE, 0.62);
        assertEquals(resultArea, 0.62, DELTA);
    }

    @Test (expected = IllegalArgumentException.class)
    public void throwsExceptionWithNegativeArea() {
        double resultArea = converter.fromTo(AreaMeasure.HECTARE, AreaMeasure.SQ_M, -1.0);
    }

    @Test (expected = IllegalArgumentException.class)
    public void throwsExceptionWithLargeResultArea() {
        double resultArea = converter.fromTo(AreaMeasure.SQ_KM, AreaMeasure.SQ_M, Double.MAX_VALUE);
    }
}
