package ru.unn.agile.LengthConvertor.Model;

enum LengthUnit { Inch, Foot, Yard, Mile, Meter, KMeter, CMeter }

public class Length {
    private double value;
    private LengthUnit unitInput;

    Length(final double val, final LengthUnit un) {
        this.value = val;
        this.unitInput = un;
    }

    @Override
    public int hashCode() {
        final int shift = 32;

        long temp = Double.doubleToLongBits(value);
        return (int) (temp ^ (temp >>> shift));
    }

    @Override
    public boolean equals(final Object object) {
        Length length = (Length) object;
        return length.value == value
                && length.unitInput == unitInput;
    }

    public double getValue() { return value; }

    public LengthUnit getUnit() { return unitInput; }

    public void setValue(final double val) { value = val; }

    public void setUnitInput(final LengthUnit un) { unitInput = un; }
}
