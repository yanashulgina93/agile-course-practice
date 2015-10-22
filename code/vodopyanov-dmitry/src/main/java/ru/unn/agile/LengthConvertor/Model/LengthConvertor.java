package ru.unn.agile.LengthConvertor.Model;

public class LengthConvertor {
    static final double inch = 0.0253999998;
    static final double foot = 0.3048;
    static final double yard = 0.914399999;
    static final double mile = 1609.34401;
    static final double meter = 1;
    static final double kmeter = 1000;
    static final double cmeter = 0.01;

    public LengthConvertor() { }

    public Length convert(final Length length, final LengthUnit unitOutput) {
        double[] multiplierMeter = {inch, foot, yard, mile, meter, kmeter, cmeter};
        if (length.getValue() >= 0) {
            length.setValue(multiplierMeter[length.getUnit().ordinal()]
                          / multiplierMeter[unitOutput.ordinal()] * length.getValue());
        } else {
            length.setValue(-1);
        }
        return length;
    }

    public final double getInchToM() { return inch; }

    public final double getFootToM() { return foot; }

    public final double getYardToM() { return yard; }

    public final double getMileToM() { return mile; }

    public final double getMeterToM() { return meter; }

    public final double getKMeterToM() { return kmeter; }

    public final double getCMeterToM() { return cmeter; }
}
