package ru.unn.agile.StatisticValueCalculator.model;

import java.util.Collection;

public class EnumerationCalculator implements IStatisticValueCalculator {
    private static final double ENUMERATION_OF_EMPTY_DATA = 0.0;

    @Override
    public double calculate(final Collection<Double> dataForStatistics) throws NullPointerException {
        if (dataForStatistics == null) {
            throw new NullPointerException("Parameter dataForStatistics must not be null");
        }

        if (dataForStatistics == null || dataForStatistics.isEmpty()) {
            return ENUMERATION_OF_EMPTY_DATA;
        }

        int sizeOfData = dataForStatistics.size();

        double enumeration = 0.0;
        for (Double instanceOfData : dataForStatistics) {
            enumeration += instanceOfData;
        }
        enumeration /= sizeOfData;

        return enumeration;
    }
}
