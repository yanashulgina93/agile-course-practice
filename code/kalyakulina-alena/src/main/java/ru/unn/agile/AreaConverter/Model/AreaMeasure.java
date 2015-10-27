package ru.unn.agile.AreaConverter.Model;

public enum AreaMeasure {
    SQUARE_METER(1.0),
    SQUARE_KILOMETER(1000000.0),
    ARE(100.0),
    HECTARE(10000.0);

    private final double measureCoeff;

    AreaMeasure(final double measureCoeff) {
        this.measureCoeff = measureCoeff;
    }

    public double getMeasureCoeff() {
        return measureCoeff;
    }
}
