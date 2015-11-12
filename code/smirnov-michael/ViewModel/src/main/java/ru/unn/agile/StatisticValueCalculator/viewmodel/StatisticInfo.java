package ru.unn.agile.StatisticValueCalculator.viewmodel;

public enum StatisticInfo {
    ENUMERATION("Enumeration"),
    VARIANCE("Variance"),
    PROBABILITY("Probability", StatisticParameterName.EVENT),
    ROW_MOMENT("Row moment", StatisticParameterName.ORDER),
    CENTRAL_MOMENT("Central moment", StatisticParameterName.ORDER);

    private final String name;
    private final StatisticParameterName parameterName;

    StatisticInfo(String name) {
        this.name = name;
        this.parameterName = null;
    }

    StatisticInfo(String name, StatisticParameterName parameterName) {
        this.name = name;
        this.parameterName = parameterName;
    }

    @Override
    public String toString() {return this.name;}

    public StatisticParameterName getParameterName() {
        return parameterName;
    }
}
