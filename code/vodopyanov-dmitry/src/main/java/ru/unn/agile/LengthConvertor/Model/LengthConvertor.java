package ru.unn.agile.LengthConvertor.Model;

public class LengthConvertor {
    static final double INCH = 0.0253999998;
    static final double FOOT = 0.3048;
    static final double YARD = 0.914399999;
    static final double MILE = 1609.34401;
    static final double METER = 1;
    static final double KMETER = 1000;
    static final double CMETER = 0.01;

    public Length convert(final Length length, final LengthUnit unitOutput) {
        double[] multiplierMeter = {INCH, FOOT, YARD, MILE, METER, KMETER, CMETER};
        if (length.getValue() >= 0) {
            length.setValue(multiplierMeter[length.getUnit().ordinal()]
                          / multiplierMeter[unitOutput.ordinal()] * length.getValue());
            length.setUnitInput(unitOutput);
        } else {
            throw new IllegalArgumentException();
        }
        return length;
    }
}
