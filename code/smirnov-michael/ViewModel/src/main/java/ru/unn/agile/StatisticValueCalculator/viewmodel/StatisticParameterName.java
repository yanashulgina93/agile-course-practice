package ru.unn.agile.StatisticValueCalculator.viewmodel;

public enum StatisticParameterName {
    ORDER("Order"),
    EVENT("Event");

    private final String name;

    StatisticParameterName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
