package ru.unn.agile.TemperatureConverter.core;

public enum TemperatureScaleName {
    FAHRENHEIT(32.0, 1.8),
    KELVIN(273.0, 1.0),
    NEWTON(0.0, 0.33);

    private final double base;
    private final double scaling;

    TemperatureScaleName(final double base, final double scaling) {
        this.base = base;
        this.scaling = scaling;
    }

    public double getBase() {
        return base;
    }

    public double getScaling() {
        return scaling;
    }
}

