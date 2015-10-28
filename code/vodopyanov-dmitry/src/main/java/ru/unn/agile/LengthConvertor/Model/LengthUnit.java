package ru.unn.agile.LengthConvertor.Model;

public enum LengthUnit {
    INCH(0.0253999998),
    FOOT(0.3048),
    YARD(0.914399999),
    MILE(1609.34401),
    METER(1),
    KMETER(1000),
    CMETER(0.01);

    private final double multiplierMeter;

    LengthUnit(final double multiplierMeter) {
        this.multiplierMeter = multiplierMeter;
    }

    public double getMultiplierMeter() {
        return multiplierMeter;
    }
}
