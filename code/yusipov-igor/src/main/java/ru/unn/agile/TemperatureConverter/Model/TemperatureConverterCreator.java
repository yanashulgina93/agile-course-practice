package ru.unn.agile.TemperatureConverter.core;

public class TemperatureConverterCreator {

    public TemperatureConverter create(final TemperatureScaleName scaleName) {
        return new TemperatureConverter(scaleName.getBase(), scaleName.getScaling());
    }
}
