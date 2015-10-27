package ru.unn.agile.AreaConverter.Model;

public class AreaConverterBuilder {

    public AreaConverter buildConverter(AreaMeasure from, AreaMeasure to) {

        double convertCoeff = from.getMeasureCoeff() / to.getMeasureCoeff();

        return new AreaConverter(convertCoeff);
    }
}
