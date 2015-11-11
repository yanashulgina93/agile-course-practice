package ru.unn.agile.StatisticValueCalculator.viewmodel;

public enum StatisticNames {
    ENUMERATION("Enumeration"),
    VARIANCE("Variance"),
    PROBABILITY("Probability"),
    ROW_MOMENT("Row moment"),
    CENTRAL_MOMENT("Central moment");

    private final String name;
    StatisticNames(String name) {
        this.name = name;
    }

    @Override
    public String toString() {return this.name;}
}
