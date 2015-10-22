package ru.unn.agile.LengthConvertor.Model;

public class LengthConvertor {
    final double INCH = 0.0253999998;
    final double FOOT = 0.3048;
    final double YARD = 0.914399999;
    final double MILE = 1609.34401;
    final double METER = 1;
    final double KMETER = 1000;
    final double CMETER = 0.01;

    public LengthConvertor() { }

    public Length convert(final Length length, final LengthUnit unitOutput) {
        double[] multiplierMeter = {INCH, FOOT, YARD, MILE, METER, KMETER, CMETER};
        if (length.getValue() >= 0) {
            length.setValue(multiplierMeter[length.getUnit().ordinal()]
                          / multiplierMeter[unitOutput.ordinal()] * length.getValue());
        } else {
            length.setValue(-1);
        }
        return length;
    }
}
