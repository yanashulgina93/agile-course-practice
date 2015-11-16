package ru.unn.agile.StatisticValueCalculator.viewmodel;

public enum StatisticValue {
    ENUMERATION("Enumeration"),
    VARIANCE("Variance"),
    PROBABILITY("Probability", StatisticParameter.EVENT),
    ROW_MOMENT("Row moment", StatisticParameter.ORDER),
    CENTRAL_MOMENT("Central moment", StatisticParameter.ORDER);

    private final String name;
    private final StatisticParameter parameterName;

    StatisticValue(final String name) {
        this(name, null);
    }

    StatisticValue(final String name, final StatisticParameter parameterName) {
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
