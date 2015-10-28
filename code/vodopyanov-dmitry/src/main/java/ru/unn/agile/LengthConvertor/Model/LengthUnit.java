package ru.unn.agile.LengthConvertor.Model;

public enum LengthUnit {
    Inch, Foot, Yard, Mile, Meter, KMeter, CMeter;

    static final double INCH = 0.0253999998;
    static final double FOOT = 0.3048;
    static final double YARD = 0.914399999;
    static final double MILE = 1609.34401;
    static final double METER = 1;
    static final double KMETER = 1000;
    static final double CMETER = 0.01;

    private double[] multiplierMeter = {INCH, FOOT, YARD, MILE, METER, KMETER, CMETER};

    public double getMultiplierMeter(final int index) {
        return multiplierMeter[index];
    }
}
