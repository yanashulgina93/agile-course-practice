package ru.unn.agile.StatisticValueCalculator.viewmodel;

public enum StatisticValue {
    ENUMERATION("Enumeration"),
    VARIANCE("Variance"),
    PROBABILITY("Probability", StatisticParameter.EVENT),
    ROW_MOMENT("Row moment", StatisticParameter.ORDER),
    CENTRAL_MOMENT("Central moment", StatisticParameter.ORDER);

    private final String name;
    private final StatisticParameter parameterName;

    StatisticValue(String name) {
        this(name, null);
    }

    StatisticValue(String name, StatisticParameter parameterName) {
        this.name = name;
        this.parameterName = parameterName;
    }

    @Override
    public String toString() {
        return this.name;
    }

    public StatisticParameter getParameterName() {
        return parameterName;
    }
}
