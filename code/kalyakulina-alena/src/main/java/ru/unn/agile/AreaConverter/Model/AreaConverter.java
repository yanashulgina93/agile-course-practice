package ru.unn.agile.AreaConverter.Model;

public class AreaConverter {

    public double convertAreaFromTo(AreaMeasure from, AreaMeasure to, double value) {
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
