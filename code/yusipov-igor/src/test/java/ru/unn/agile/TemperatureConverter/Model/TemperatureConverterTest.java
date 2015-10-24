package ru.unn.agile.TemperatureConverter.Model;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class TemperatureConverterTest {

    private static final double DELTA = 1e-16;
    private TemperatureConverterCreator converterCreator = new TemperatureConverterCreator();

    @Test
    public void canConvertCelsiusToFahrenheitZero() {
        TemperatureConverter converter = converterCreator.create(TemperatureScaleName.FAHRENHEIT);
        double temperatureInFahrenheit = converter.convert(0.0);
        assertEquals(temperatureInFahrenheit, 32.0, DELTA);
    }

    @Test
    public void canConvertCelsiusToFahrenheitUnit() {
        TemperatureConverter converter = converterCreator.create(TemperatureScaleName.FAHRENHEIT);
        double temperatureInFahrenheit = converter.convert(1.0);
        assertEquals(temperatureInFahrenheit, 33.8, DELTA);
    }

    @Test (expected = IllegalArgumentException.class)
    public void canConvertCelsiusToFahrenheitLargeNumber() {
        TemperatureConverter converter = converterCreator.create(TemperatureScaleName.FAHRENHEIT);
        double temperatureInFahrenheit = converter.convert(Double.MAX_VALUE);
    }

    @Test
    public void canConvertCelsiusToFahrenheitNegativeUnit() {
        TemperatureConverter converter = converterCreator.create(TemperatureScaleName.FAHRENHEIT);
        double temperatureInFahrenheit = converter.convert(-1.0);
        assertEquals(temperatureInFahrenheit, 30.2, DELTA);
    }

    @Test
    public void canConvertCelsiusToKelvinZero() {
        TemperatureConverter converter = converterCreator.create(TemperatureScaleName.KELVIN);
        double temperatureInKelvin = converter.convert(0.0);
        assertEquals(temperatureInKelvin, 273.0, DELTA);
    }

    @Test
    public void canConvertCelsiusToKelvinUnit() {
        TemperatureConverter converter = converterCreator.create(TemperatureScaleName.KELVIN);
        double temperatureInKelvin = converter.convert(1.0);
        assertEquals(temperatureInKelvin, 274.0, DELTA);
    }

    @Test (expected = IllegalArgumentException.class)
    public void canConvertCelsiusToKelvinLargeNumber() {
        TemperatureConverter converter = converterCreator.create(TemperatureScaleName.KELVIN);
        double temperatureInKelvin = converter.convert(Double.MAX_VALUE);
    }

    @Test
    public void canConvertCelsiusToKelvinNegativeUnit() {
        TemperatureConverter converter = converterCreator.create(TemperatureScaleName.KELVIN);
        double temperatureInKelvin = converter.convert(-1.0);
        assertEquals(temperatureInKelvin, 272.0, DELTA);
    }

    @Test
    public void canConvertCelsiusToNewtonZero() {
        TemperatureConverter converter = converterCreator.create(TemperatureScaleName.NEWTON);
        double temperatureInKelvin = converter.convert(0.0);
        assertEquals(temperatureInKelvin, 0.0, DELTA);
    }

    @Test
    public void canConvertCelsiusToNewtonUnit() {
        TemperatureConverter converter = converterCreator.create(TemperatureScaleName.NEWTON);
        double temperatureInKelvin = converter.convert(1.0);
        assertEquals(temperatureInKelvin, 0.33, DELTA);
    }

    @Test
    public void ConvertCelsiusToNewtonNotThrowsOnLargeDouble() {
        TemperatureConverter converter = converterCreator.create(TemperatureScaleName.NEWTON);
        double temperatureInKelvin = converter.convert(Double.MAX_VALUE);
    }

    @Test
    public void canConvertCelsiusToNewtonNegativeUnit() {
        TemperatureConverter converter = converterCreator.create(TemperatureScaleName.NEWTON);
        double temperatureInKelvin = converter.convert(-1.0);
        assertEquals(temperatureInKelvin, -0.33, DELTA);
    }
}
