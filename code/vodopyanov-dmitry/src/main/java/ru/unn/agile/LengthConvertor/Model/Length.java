package ru.unn.agile.LengthConvertor.core;

enum LengthUnit { Inch, Foot, Yard, Mile, Meter, KMeter, CMeter }

public class Length {
    private double value;
    private LengthUnit unitInput;

    Length(double val, LengthUnit un) {
        this.value = val;
        this.unitInput = un;
    }

    @Override
    public int hashCode() {
        final int shift = 32;

        long temp = Double.doubleToLongBits(value);
        int result = (int) (temp ^ (temp >>> shift));
        return result;
    }

    public boolean equals(final Length length) {
        return length.value == value
                && length.unitInput == unitInput;
    }

    public double getValue() { return value; }

    public LengthUnit getUnit() { return unitInput; }
}
