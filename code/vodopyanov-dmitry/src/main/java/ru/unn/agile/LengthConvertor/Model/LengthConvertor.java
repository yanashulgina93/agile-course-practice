package ru.unn.agile.LengthConvertor.core;

public class LengthConvertor {
    public LengthConvertor() { }

    public Length convert(final Length length, final LengthUnit unitOutput) {
        double multiplierMeter[] = { 0.0253999998, 0.3048, 0.914399999, 1609.34401, 1, 1000, 0.01 };
        if (length.getValue() >= 0) {
            length.value = multiplierMeter[length.getUnit().ordinal()] /
                           multiplierMeter[unitOutput.ordinal()] * length.value;
        } else {
            length.value = -1;
        }
        return length;
    }
}
