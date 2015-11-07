package ru.unn.agile.statistics.model;

public class NumericStatisticalResult implements IStatisticalResult {
    private final Double instance;

    public NumericStatisticalResult(final Integer number) {
        instance = number.doubleValue();
    }
    public NumericStatisticalResult(final Float number) {
        instance = number.doubleValue();
    }
    public NumericStatisticalResult(final Double number) {
        instance = number;
    }

    @Override
    public Double get() {
        return new Double(instance);
    }
}
