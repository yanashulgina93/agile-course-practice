package ru.unn.agile.AreaConverter.core;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class AreaConverterTest {

    private static final double DELTA = 1e-10;

    private AreaConverterBuilder builder = new AreaConverterBuilder();

    @Test
    public void isZeroConvertedToZero() {
        AreaConverter converter = builder.buildConverter(AreaMeasure.ARE,
                                                         AreaMeasure.HECTARE);

        final double resultArea = converter.convert(0.0);

        assertEquals(resultArea, 0.0, DELTA);
    }

    @Test
    public void isCorrectConvertedFromMeterToMeter() {
        AreaConverter converter = builder.buildConverter(AreaMeasure.SQUARE_METER,
                                                         AreaMeasure.SQUARE_METER);

        final double resultArea = converter.convert(1.0);

        assertEquals(resultArea, 1.0, DELTA);
    }

    @Test
    public void isCorrectConvertedFromMeterToKilometer() {
        AreaConverter converter = builder.buildConverter(AreaMeasure.SQUARE_METER,
                                                         AreaMeasure.SQUARE_KILOMETER);

        final double resultArea = converter.convert(152.0);

        assertEquals(resultArea, 0.000152, DELTA);
    }

    @Test
    public void isCorrectConvertedFromMeterToAre() {
        AreaConverter converter = builder.buildConverter(AreaMeasure.SQUARE_METER,
                                                         AreaMeasure.ARE);

        final double resultArea = converter.convert(10.0);

        assertEquals(resultArea, 0.1, DELTA);
    }

    @Test
    public void isCorrectConvertedFromMeterToHectare() {
        AreaConverter converter = builder.buildConverter(AreaMeasure.SQUARE_METER,
                                                         AreaMeasure.HECTARE);

        final double resultArea = converter.convert(0.5);

        assertEquals(resultArea, 0.00005, DELTA);
    }

    @Test
    public void isCorrectConvertedFromKilometerToMeter() {
        AreaConverter converter = builder.buildConverter(AreaMeasure.SQUARE_KILOMETER,
                                                         AreaMeasure.SQUARE_METER);

        final double resultArea = converter.convert(7.5);

        assertEquals(resultArea, 7500000.0, DELTA);
    }

    @Test
    public void isCorrectConvertedFromKilometerToKilometer() {
        AreaConverter converter = builder.buildConverter(AreaMeasure.SQUARE_KILOMETER,
                                                         AreaMeasure.SQUARE_KILOMETER);

        final double resultArea = converter.convert(1.11);

        assertEquals(resultArea, 1.11, DELTA);
    }

    @Test
    public void isCorrectConvertedFromKilometerToAre() {
        AreaConverter converter = builder.buildConverter(AreaMeasure.SQUARE_KILOMETER,
                                                         AreaMeasure.ARE);

        final double resultArea = converter.convert(25.0);

        assertEquals(resultArea, 250000.0, DELTA);
    }

    @Test
    public void isCorrectConvertedFromKilometerToHectare() {
        AreaConverter converter = builder.buildConverter(AreaMeasure.SQUARE_KILOMETER,
                                                         AreaMeasure.HECTARE);

        final double resultArea = converter.convert(0.01);

        assertEquals(resultArea, 1.0, DELTA);
    }

    @Test
    public void isCorrectConvertedFromAreToMeter() {
        AreaConverter converter = builder.buildConverter(AreaMeasure.ARE,
                                                         AreaMeasure.SQUARE_METER);

        final double resultArea = converter.convert(500.0);

        assertEquals(resultArea, 50000.0, DELTA);
    }

    @Test
    public void isCorrectConvertedFromAreToKilometer() {
        AreaConverter converter = builder.buildConverter(AreaMeasure.ARE,
                                                         AreaMeasure.SQUARE_KILOMETER);

        final double resultArea = converter.convert(6.4);

        assertEquals(resultArea, 0.00064, DELTA);
    }

    @Test
    public void isCorrectConvertedFromAreToAre() {
        AreaConverter converter = builder.buildConverter(AreaMeasure.ARE,
                                                         AreaMeasure.ARE);

        final double resultArea = converter.convert(300.0);

        assertEquals(resultArea, 300.0, DELTA);
    }

    @Test
    public void isCorrectConvertedFromAreToHectare() {
        AreaConverter converter = builder.buildConverter(AreaMeasure.ARE,
                                                         AreaMeasure.HECTARE);

        final double resultArea = converter.convert(12.3);

        assertEquals(resultArea, 0.123, DELTA);
    }

    @Test
    public void isCorrectConvertedFromHectareToMeter() {
        AreaConverter converter = builder.buildConverter(AreaMeasure.HECTARE,
                                                         AreaMeasure.SQUARE_METER);

        final double resultArea = converter.convert(0.2);

        assertEquals(resultArea, 2000.0, DELTA);
    }

    @Test
    public void isCorrectConvertedFromHectareToKilometer() {
        AreaConverter converter = builder.buildConverter(AreaMeasure.HECTARE,
                                                         AreaMeasure.SQUARE_KILOMETER);

        final double resultArea = converter.convert(78.0);

        assertEquals(resultArea, 0.78, DELTA);
    }

    @Test
    public void isCorrectConvertedFromHectareToAre() {
        AreaConverter converter = builder.buildConverter(AreaMeasure.HECTARE,
                                                         AreaMeasure.ARE);

        final double resultArea = converter.convert(0.01);


        assertEquals(resultArea, 1.0, DELTA);
    }

    @Test
    public void isCorrectConvertedFromHectareToHectare() {
        AreaConverter converter = builder.buildConverter(AreaMeasure.HECTARE,
                                                         AreaMeasure.HECTARE);

        final double resultArea = converter.convert(0.62);

        assertEquals(resultArea, 0.62, DELTA);
    }

    @Test (expected = IllegalArgumentException.class)
    public void throwsExceptionWithNegativeArea() {
        AreaConverter converter = builder.buildConverter(AreaMeasure.HECTARE,
                                                         AreaMeasure.SQUARE_METER);

        final double resultArea = converter.convert(-1.0);
    }

    @Test (expected = IllegalArgumentException.class)
    public void throwsExceptionWithLargeResultArea() {
        AreaConverter converter = builder.buildConverter(AreaMeasure.SQUARE_KILOMETER,
                                                         AreaMeasure.SQUARE_METER);

        final double resultArea = converter.convert(Double.MAX_VALUE);
    }
}
