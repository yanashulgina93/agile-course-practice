package ru.unn.agile.LengthConvertor.Model;

import java.util.Objects;

public class Length {
    private double value;
    private LengthUnit unitInput;

    Length(final double val, final LengthUnit un) {
        this.value = val;
        this.unitInput = un;
        if (val < 0) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, unitInput);
    }

    @Override
    public boolean equals(final Object object) {
        Length length = (Length) object;
        return length.value == value
                && length.unitInput == unitInput;
    }

    public double getValue() {
        return value;
    }

    public LengthUnit getUnit() {
        return unitInput;
    }

    public void setValue(final double val) {
        value = val;
    }

    public void setUnitInput(final LengthUnit un) {
        unitInput = un;
    }
}
