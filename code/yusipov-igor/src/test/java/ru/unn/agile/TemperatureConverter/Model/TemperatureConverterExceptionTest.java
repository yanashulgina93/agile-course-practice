package ru.unn.agile.TemperatureConverter.core;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class TemperatureConverterExceptionTest {

    private TemperatureConverterCreator converterCreator = new TemperatureConverterCreator();
    TemperatureConverter converter;

    @Test (expected = IllegalArgumentException.class)
    public void canConvertCelsiusToFahrenheitLargeNumber() {
        converter = converterCreator.create(TemperatureScaleName.FAHRENHEIT);

        double temperatureInFahrenheit = converter.convert(Double.MAX_VALUE);
    }

    @Test (expected = IllegalArgumentException.class)
    public void canConvertCelsiusToKelvinLargeNumber() {
        converter = converterCreator.create(TemperatureScaleName.KELVIN);

        double temperatureInKelvin = converter.convert(Double.MAX_VALUE);
    }

    @Test
    public void convertCelsiusToNewtonNotThrowsOnLargeDouble() {
        converter = converterCreator.create(TemperatureScaleName.NEWTON);

        double temperatureInKelvin = converter.convert(Double.MAX_VALUE);
    }
}
