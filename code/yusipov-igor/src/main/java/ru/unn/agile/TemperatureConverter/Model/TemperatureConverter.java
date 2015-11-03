package ru.unn.agile.TemperatureConverter.core;

public class TemperatureConverter {

    private final double base;
    private final double scaling;

    public TemperatureConverter(final double base, final double scale) {
        this.base = base;
        this.scaling = scale;
    }

    public double convert(final double temperatureInCelsius) {
        double temperatureInOtherScale = scaling * temperatureInCelsius + base;

        if (temperatureInOtherScale >= Double.MAX_VALUE) {
            throw new IllegalArgumentException();
        }

        return temperatureInOtherScale;
    }
}
