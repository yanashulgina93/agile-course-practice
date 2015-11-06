package ru.unn.agile.statistics.model;

public class NumericStatisticDataInstance implements IStatisticDataInstance {
    private final Double instance;

    public NumericStatisticDataInstance(final Integer number) {
        instance = number.doubleValue();
    }
    public NumericStatisticDataInstance(final Float number) {
        instance = number.doubleValue();
    }
    public NumericStatisticDataInstance(final Double number) {
        instance = number;
    }

    @Override
    public Double get() {
        return new Double(instance);
    }
}
