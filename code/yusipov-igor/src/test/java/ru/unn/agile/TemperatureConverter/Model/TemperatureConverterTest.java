package ru.unn.agile.TemperatureConverter.Model;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class TemperatureConverterTest {

    private static final double DELTA = 1e-16;
    private TemperatureConverterCreator converterCreator = new TemperatureConverterCreator();

    @Test
    public void canConvertCelsiusToFahrenheitZero() {
        TemperatureConverter converterToFahrenheit = converterCreator.createTemperatureConverter(TemperatureScaleName.FAHRENHEIT);
        double temperatureInFahrenheit = converterToFahrenheit.convert(0.0);
        assertEquals(temperatureInFahrenheit, 32.0, DELTA);
    }

    @Test
    public void canConvertCelsiusToFahrenheitUnit() {
        TemperatureConverter converterToFahrenheit = converterCreator.createTemperatureConverter(TemperatureScaleName.FAHRENHEIT);
        double temperatureInFahrenheit = converterToFahrenheit.convert(1.0);
        assertEquals(temperatureInFahrenheit, 33.8, DELTA);
    }

    @Test (expected = IllegalArgumentException.class)
    public void canConvertCelsiusToFahrenheitLargeNumber() {
        TemperatureConverter converterToFahrenheit = converterCreator.createTemperatureConverter(TemperatureScaleName.FAHRENHEIT);
        double temperatureInFahrenheit = converterToFahrenheit.convert(Double.MAX_VALUE);
    }

    @Test
    public void canConvertCelsiusToFahrenheitNegativeUnit() {
        TemperatureConverter converterToFahrenheit = converterCreator.createTemperatureConverter(TemperatureScaleName.FAHRENHEIT);
        double temperatureInFahrenheit = converterToFahrenheit.convert(-1.0);
        assertEquals(temperatureInFahrenheit, 30.2, DELTA);
    }

    @Test
    public void canConvertCelsiusToKelvinZero() {
        TemperatureConverter converterToKelvin = converterCreator.createTemperatureConverter(TemperatureScaleName.KELVIN);
        double temperatureInKelvin = converterToKelvin.convert(0.0);
        assertEquals(temperatureInKelvin, 273.0, DELTA);
    }

    @Test
    public void canConvertCelsiusToKelvinUnit() {
        TemperatureConverter converterToKelvin = converterCreator.createTemperatureConverter(TemperatureScaleName.KELVIN);
        double temperatureInKelvin = converterToKelvin.convert(1.0);
        assertEquals(temperatureInKelvin, 274.0, DELTA);
    }

    @Test (expected = IllegalArgumentException.class)
    public void canConvertCelsiusToKelvinLargeNumber() {
        TemperatureConverter converterToKelvin = converterCreator.createTemperatureConverter(TemperatureScaleName.KELVIN);
        double temperatureInKelvin = converterToKelvin.convert(Double.MAX_VALUE);
    }

    @Test
    public void canConvertCelsiusToKelvinNegativeUnit() {
        TemperatureConverter converterToKelvin = converterCreator.createTemperatureConverter(TemperatureScaleName.KELVIN);
        double temperatureInKelvin = converterToKelvin.convert(-1.0);
        assertEquals(temperatureInKelvin, 272.0, DELTA);
    }

    @Test
    public void canConvertCelsiusToNewtonZero() {
        TemperatureConverter converterToNewton = converterCreator.createTemperatureConverter(TemperatureScaleName.NEWTON);
        double temperatureInKelvin = converterToNewton.convert(0.0);
        assertEquals(temperatureInKelvin, 0.0, DELTA);
    }

    @Test
    public void canConvertCelsiusToNewtonUnit() {
        TemperatureConverter converterToNewton = converterCreator.createTemperatureConverter(TemperatureScaleName.NEWTON);
        double temperatureInKelvin = converterToNewton.convert(1.0);
        assertEquals(temperatureInKelvin, 0.33, DELTA);
    }

    @Test
    public void ConvertCelsiusToNewtonNotThrowsOnLargeDouble() {
        TemperatureConverter converterToNewton = converterCreator.createTemperatureConverter(TemperatureScaleName.NEWTON);
        double temperatureInKelvin = converterToNewton.convert(Double.MAX_VALUE);
    }

    @Test
    public void canConvertCelsiusToNewtonNegativeUnit() {
        TemperatureConverter converterToNewton = converterCreator.createTemperatureConverter(TemperatureScaleName.NEWTON);
        double temperatureInKelvin = converterToNewton.convert(-1.0);
        assertEquals(temperatureInKelvin, -0.33, DELTA);
    }
}
