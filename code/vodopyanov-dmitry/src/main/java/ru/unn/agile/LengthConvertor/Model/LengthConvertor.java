package ru.unn.agile.LengthConvertor.Model;

public class LengthConvertor {
    public Length convert(final Length length, final LengthUnit unitOutput) {
        if (length.getValue() >= 0) {
            length.setValue(length.getUnit().getMultiplierMeter()
                    / unitOutput.getMultiplierMeter() * length.getValue());
            length.setUnitInput(unitOutput);
        } else {
            throw new IllegalArgumentException();
        }
        return length;
    }
}
