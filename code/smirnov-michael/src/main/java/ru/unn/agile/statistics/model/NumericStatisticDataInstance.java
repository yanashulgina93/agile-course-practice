package ru.unn.agile.statistics.model;

public class NumericStatisticDataInstance implements IStatisticDataInstance {
    private final Double instance;

    public NumericStatisticDataInstance(final Integer number) {
        instance = Double.valueOf(number);
    }
    public NumericStatisticDataInstance(final Float number) {
        instance = Double.valueOf(number);
    }
    public NumericStatisticDataInstance(final Double number) {
        instance = number;
    }

    @Override
    public Double getInstance() {
        return instance;
    }
}
