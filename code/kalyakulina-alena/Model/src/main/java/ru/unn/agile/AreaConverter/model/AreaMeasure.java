package ru.unn.agile.AreaConverter.model;

public enum AreaMeasure {
    SQUARE_METER("Square Meter", 1.0),
    SQUARE_KILOMETER("Square Kilometer", 1000000.0),
    ARE("Are", 100.0),
    HECTARE("Hectare", 10000.0);

    private final String measureName;
    private final double measureCoeff;

    AreaMeasure(final String measureName, final double measureCoeff) {
        this.measureName = measureName;
        this.measureCoeff = measureCoeff;
    }

    public String toString() {
        return measureName;
    }

    public double getMeasureCoeff() {
        return measureCoeff;
    }
}
