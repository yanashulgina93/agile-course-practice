package ru.unn.agile.LengthConvertor.core;

enum LengthUnit {Inch, Foot, Yard, Mile, Meter, KMeter, CMeter}

public class Length {
    public double value;
    public LengthUnit UnitInput;

    @Override
    public boolean equals(final Length length) {
        ComplexNumber number = (ComplexNumber) object;
        return length.value == value
                && length.UnitInput == UnitInput;
    }
}