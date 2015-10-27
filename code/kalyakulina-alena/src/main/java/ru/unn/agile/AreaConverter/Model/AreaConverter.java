package ru.unn.agile.AreaConverter.core;

public class AreaConverter {

    private final double convertCoeff;

    public AreaConverter(final double convertCoeff) {
        this.convertCoeff = convertCoeff;
    }

    public double convert(final double value) {

        if (value < 0.0) {
            throw new IllegalArgumentException("Negative input area");
        }

        if (value > Double.MAX_VALUE / convertCoeff) {
            throw new IllegalArgumentException("Too large input area");
        }

        return value * convertCoeff;
    }
}
