package ru.unn.agile.statistics.model;

import java.util.Collection;

public interface IStatisticValueCalculator {
    double calculate(final Collection<Double> dataForStatistics) throws NullPointerException;
}
