package ru.unn.agile.AreaConverter.Model;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class AreaConverterTest {

    private static final double DELTA = 1e-10;

    AreaConverter converter = new AreaConverter();

    @Test
    public void isZeroConvertedToZero() {
        double convertedArea = converter.convertAreaFromTo(AreaMeasure.ARE, AreaMeasure.HECTARE, 0.0);
        assertEquals(convertedArea, 0.0, DELTA);
    }

    @Test
    public void isCorrectConvertedFromMeterToMeter() {
        double convertedArea = converter.convertAreaFromTo(AreaMeasure.SQUARE_METER, AreaMeasure.SQUARE_METER, 1.0);
        assertEquals(convertedArea, 1.0, DELTA);
    }

    @Test
    public void isCorrectConvertedFromMeterToKilometer() {
        double convertedArea = converter.convertAreaFromTo(AreaMeasure.SQUARE_METER, AreaMeasure.SQUARE_KILOMETER, 152.0);
        assertEquals(convertedArea, 0.000152, DELTA);
    }

    @Test
    public void isCorrectConvertedFromMeterToAre() {
        double convertedArea = converter.convertAreaFromTo(AreaMeasure.SQUARE_METER, AreaMeasure.ARE, 10.0);
        assertEquals(convertedArea, 0.1, DELTA);
    }

    @Test
    public void isCorrectConvertedFromMeterToHectare() {
        double convertedArea = converter.convertAreaFromTo(AreaMeasure.SQUARE_METER, AreaMeasure.HECTARE, 0.5);
        assertEquals(convertedArea, 0.00005, DELTA);
    }

    @Test
    public void isCorrectConvertedFromKilometerToMeter() {
        double convertedArea = converter.convertAreaFromTo(AreaMeasure.SQUARE_KILOMETER, AreaMeasure.SQUARE_METER, 7.5);
        assertEquals(convertedArea, 7500000.0, DELTA);
    }

    @Test
    public void isCorrectConvertedFromKilometerToKilometer() {
        double convertedArea = converter.convertAreaFromTo(AreaMeasure.SQUARE_KILOMETER, AreaMeasure.SQUARE_KILOMETER, 1.11);
        assertEquals(convertedArea, 1.11, DELTA);
    }

    @Test
    public void isCorrectConvertedFromKilometerToAre() {
        double convertedArea = converter.convertAreaFromTo(AreaMeasure.SQUARE_KILOMETER, AreaMeasure.ARE, 25.0);
        assertEquals(convertedArea, 250000.0, DELTA);
    }

    @Test
    public void isCorrectConvertedFromKilometerToHectare() {
        double convertedArea = converter.convertAreaFromTo(AreaMeasure.SQUARE_KILOMETER, AreaMeasure.HECTARE, 0.01);
        assertEquals(convertedArea, 1.0, DELTA);
    }

    @Test
    public void isCorrectConvertedFromAreToMeter() {
        double convertedArea = converter.convertAreaFromTo(AreaMeasure.ARE, AreaMeasure.SQUARE_METER, 500.0);
        assertEquals(convertedArea, 50000.0, DELTA);
    }

    @Test
    public void isCorrectConvertedFromAreToKilometer() {
        double convertedArea = converter.convertAreaFromTo(AreaMeasure.ARE, AreaMeasure.SQUARE_KILOMETER, 6.4);
        assertEquals(convertedArea, 0.00064, DELTA);
    }

    @Test
    public void isCorrectConvertedFromAreToAre() {
        double convertedArea = converter.convertAreaFromTo(AreaMeasure.ARE, AreaMeasure.ARE, 300.0);
        assertEquals(convertedArea, 300.0, DELTA);
    }

    @Test
    public void isCorrectConvertedFromAreToHectare() {
        double convertedArea = converter.convertAreaFromTo(AreaMeasure.ARE, AreaMeasure.HECTARE, 12.3);
        assertEquals(convertedArea, 0.123, DELTA);
    }

    @Test
    public void isCorrectConvertedFromHectareToMeter() {
        double convertedArea = converter.convertAreaFromTo(AreaMeasure.HECTARE, AreaMeasure.SQUARE_METER, 0.2);
        assertEquals(convertedArea, 2000.0, DELTA);
    }

    @Test
    public void isCorrectConvertedFromHectareToKilometer() {
        double convertedArea = converter.convertAreaFromTo(AreaMeasure.HECTARE, AreaMeasure.SQUARE_KILOMETER, 78.0);
        assertEquals(convertedArea, 0.78, DELTA);
    }

    @Test
    public void isCorrectConvertedFromHectareToAre() {
        double convertedArea = converter.convertAreaFromTo(AreaMeasure.HECTARE, AreaMeasure.ARE, 0.01);
        assertEquals(convertedArea, 1.0, DELTA);
    }

    @Test
    public void isCorrectConvertedFromHectareToHectare() {
        double convertedArea = converter.convertAreaFromTo(AreaMeasure.HECTARE, AreaMeasure.HECTARE, 0.62);
        assertEquals(convertedArea, 0.62, DELTA);
    }

    @Test (expected = IllegalArgumentException.class)
    public void throwsExceptionWithNegativeArea() {
        double convertedArea = converter.convertAreaFromTo(AreaMeasure.HECTARE, AreaMeasure.SQUARE_METER, -1.0);
    }

    @Test (expected = IllegalArgumentException.class)
    public void throwsExceptionWithLargeResultArea() {
        double convertedArea = converter.convertAreaFromTo(AreaMeasure.SQUARE_KILOMETER, AreaMeasure.SQUARE_METER, Double.MAX_VALUE);
    }
}
