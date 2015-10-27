package ru.unn.agile.AreaConverter.core;

public class AreaConverterBuilder {

    public AreaConverter buildConverter(final AreaMeasure from, final AreaMeasure to) {

        double convertCoeff = from.getMeasureCoeff() / to.getMeasureCoeff();

        return new AreaConverter(convertCoeff);
    }
}
