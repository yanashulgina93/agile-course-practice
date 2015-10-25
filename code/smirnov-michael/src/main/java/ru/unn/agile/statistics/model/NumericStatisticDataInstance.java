package ru.unn.agile.statistics.model;

public class NumericStatisticDataInstance implements IStatisticDataInstance {
    public NumericStatisticDataInstance(Integer number){
        instance = Double.valueOf(number);
    }
    public NumericStatisticDataInstance(Float number){
        instance = Double.valueOf(number);
    }
    public NumericStatisticDataInstance(Double number){
        instance = number;
    }

    @Override
    public Double getInstance() {
        return instance;
    }

    private Double instance;
}
