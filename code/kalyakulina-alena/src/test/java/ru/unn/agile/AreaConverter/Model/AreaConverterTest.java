package ru.unn.agile.AreaConverter.Model;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class AreaConverterTest {

    private static final double DELTA = 1e-10;

    AreaConverter converter = new AreaConverter();

    @Test
    public void isZeroConvertedToZero() {
        double convertedArea = converter.fromTo(AreaMeasure.ARE, AreaMeasure.HECTARE, 0.0);
        assertEquals(convertedArea, 0.0, DELTA);
    }

    @Test
    public void isCorrectConvertedFromMeterToMeter() {
        double convertedArea = converter.fromTo(AreaMeasure.SQ_M, AreaMeasure.SQ_M, 1.0);
        assertEquals(convertedArea, 1.0, DELTA);
    }

    @Test
    public void isCorrectConvertedFromMeterToKilometer() {
        double convertedArea = converter.fromTo(AreaMeasure.SQ_M, AreaMeasure.SQ_KM, 152.0);
        assertEquals(convertedArea, 0.000152, DELTA);
    }

    @Test
    public void isCorrectConvertedFromMeterToAre() {
        double convertedArea = converter.fromTo(AreaMeasure.SQ_M, AreaMeasure.ARE, 10.0);
        assertEquals(convertedArea, 0.1, DELTA);
    }

    @Test
    public void isCorrectConvertedFromMeterToHectare() {
        double convertedArea = converter.fromTo(AreaMeasure.SQ_M, AreaMeasure.HECTARE, 0.5);
        assertEquals(convertedArea, 0.00005, DELTA);
    }

    @Test
    public void isCorrectConvertedFromKilometerToMeter() {
        double convertedArea = converter.fromTo(AreaMeasure.SQ_KM, AreaMeasure.SQ_M, 7.5);
        assertEquals(convertedArea, 7500000.0, DELTA);
    }

    @Test
    public void isCorrectConvertedFromKilometerToKilometer() {
        double convertedArea = converter.fromTo(AreaMeasure.SQ_KM, AreaMeasure.SQ_KM, 1.11);
        assertEquals(convertedArea, 1.11, DELTA);
    }

    @Test
    public void isCorrectConvertedFromKilometerToAre() {
        double convertedArea = converter.fromTo(AreaMeasure.SQ_KM, AreaMeasure.ARE, 25.0);
        assertEquals(convertedArea, 250000.0, DELTA);
    }

    @Test
    public void isCorrectConvertedFromKilometerToHectare() {
        double convertedArea = converter.fromTo(AreaMeasure.SQ_KM, AreaMeasure.HECTARE, 0.01);
        assertEquals(convertedArea, 1.0, DELTA);
    }

    @Test
    public void isCorrectConvertedFromAreToMeter() {
        double convertedArea = converter.fromTo(AreaMeasure.ARE, AreaMeasure.SQ_M, 500.0);
        assertEquals(convertedArea, 50000.0, DELTA);
    }

    @Test
    public void isCorrectConvertedFromAreToKilometer() {
        double convertedArea = converter.fromTo(AreaMeasure.ARE, AreaMeasure.SQ_KM, 6.4);
        assertEquals(convertedArea, 0.00064, DELTA);
    }

    @Test
    public void isCorrectConvertedFromAreToAre() {
        double convertedArea = converter.fromTo(AreaMeasure.ARE, AreaMeasure.ARE, 300.0);
        assertEquals(convertedArea, 300.0, DELTA);
    }

    @Test
    public void isCorrectConvertedFromAreToHectare() {
        double convertedArea = converter.fromTo(AreaMeasure.ARE, AreaMeasure.HECTARE, 12.3);
        assertEquals(convertedArea, 0.123, DELTA);
    }

    @Test
    public void isCorrectConvertedFromHectareToMeter() {
        double convertedArea = converter.fromTo(AreaMeasure.HECTARE, AreaMeasure.SQ_M, 0.2);
        assertEquals(convertedArea, 2000.0, DELTA);
    }

    @Test
    public void isCorrectConvertedFromHectareToKilometer() {
        double convertedArea = converter.fromTo(AreaMeasure.HECTARE, AreaMeasure.SQ_KM, 78.0);
        assertEquals(convertedArea, 0.78, DELTA);
    }

    @Test
    public void isCorrectConvertedFromHectareToAre() {
        double convertedArea = converter.fromTo(AreaMeasure.HECTARE, AreaMeasure.ARE, 0.01);
        assertEquals(convertedArea, 1.0, DELTA);
    }

    @Test
    public void isCorrectConvertedFromHectareToHectare() {
        double convertedArea = converter.fromTo(AreaMeasure.HECTARE, AreaMeasure.HECTARE, 0.62);
        assertEquals(convertedArea, 0.62, DELTA);
    }

    @Test (expected = IllegalArgumentException.class)
    public void throwsExceptionWithNegativeArea() {
        double convertedArea = converter.fromTo(AreaMeasure.HECTARE, AreaMeasure.SQ_M, -1.0);
    }

    @Test (expected = IllegalArgumentException.class)
    public void throwsExceptionWithLargeResultArea() {
        double convertedArea = converter.fromTo(AreaMeasure.SQ_KM, AreaMeasure.SQ_M, Double.MAX_VALUE);
    }
}
