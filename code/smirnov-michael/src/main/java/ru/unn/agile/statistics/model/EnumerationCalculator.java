package ru.unn.agile.statistics.model;

import java.util.Collection;

public class EnumerationCalculator extends StatisticValueCalculator {
    private static final double ENUMERATION_OF_EMPTY_DATA = 0.0;

    public EnumerationCalculator(final Collection<IStatisticalResult> dataForStatistics) {
        setStatisticData(dataForStatistics);
    }

    @Override
    public double calculate() {
        if (isProcessedStatisticsEmpty()) {
            return ENUMERATION_OF_EMPTY_DATA;
        }

        double[] processedStatistics = getProcessedStatistics();

        double enumeration = 0.0;
        for (double instance : processedStatistics) {
            enumeration += instance;
        }
        enumeration /= processedStatistics.length;

        return enumeration;
    }
}
