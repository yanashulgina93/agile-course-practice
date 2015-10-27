package ru.unn.agile.AreaConverter.Model;

public class AreaConverter {

    public double fromTo(final AreaMeasure from, final AreaMeasure to, final double value) {
        if (value < 0.0) {
            throw new IllegalArgumentException("Negative input area");
        }

        double convertCoeff = from.getMeasureCoeff() / to.getMeasureCoeff();

        if (value > Double.MAX_VALUE / convertCoeff) {
            throw new IllegalArgumentException("Too large input area");
        }

        return value * convertCoeff;
    }
}
