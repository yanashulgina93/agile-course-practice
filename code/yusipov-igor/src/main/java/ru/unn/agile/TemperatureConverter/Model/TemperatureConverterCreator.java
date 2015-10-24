package ru.unn.agile.TemperatureConverter.Model;

public class TemperatureConverterCreator {

    private static final double FAHRENHEIT_BASE = 32.0;
    private static final double FAHRENHEIT_SCALE = 1.8;
    private static final double KELVIN_BASE = 273.0;
    private static final double KELVIN_SCALE = 1.0;
    private static final double NEWTON_BASE = 0.0;
    private static final double NEWTON_SCALE = 0.33;

    public TemperatureConverter createTemperatureConverter(final TemperatureScaleName scaleName) {
        switch (scaleName) {
            case FAHRENHEIT:
                return new TemperatureConverter(FAHRENHEIT_BASE, FAHRENHEIT_SCALE);
            case KELVIN:
                return new TemperatureConverter(KELVIN_BASE, KELVIN_SCALE);
            case NEWTON:
                return new TemperatureConverter(NEWTON_BASE, NEWTON_SCALE);
            default:
                throw new IllegalArgumentException();
        }
    }
}
