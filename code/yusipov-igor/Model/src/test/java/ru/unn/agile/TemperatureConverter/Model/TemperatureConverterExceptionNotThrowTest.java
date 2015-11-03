package ru.unn.agile.TemperatureConverter.Model;

import org.junit.Test;

public class TemperatureConverterExceptionNotThrowTest {
    private TemperatureConverter converter;

    @Test
    public void convertCelsiusToNewtonNotThrowsOnLargeDouble() {
        converter = new TemperatureConverter(TemperatureScaleName.NEWTON);

        double temperatureInKelvin = converter.convert(Double.MAX_VALUE);
    }
}
