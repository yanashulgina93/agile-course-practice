package ru.unn.agile.statistics.model;

import java.util.Collection;

public class RawMomentCalculator extends StatisticValueCalculator {

    private static final double RAW_MOMENT_OF_EMPTY_DATA = 0.0;
    private static final double RAW_MOMENT_WITH_NOT_POSITIVE_ORDER = 0.0;

    private int order;

    public RawMomentCalculator(final Collection<IStatisticalResult> dataForStatistics,
                               final int order) {
        setStatisticData(dataForStatistics);
        setOrder(order);
    }

    public void setOrder(final int order) {
        this.order = order;
    }

    @Override
    public double calculate() {
        if (isProcessedStatisticsEmpty()) {
            return RAW_MOMENT_OF_EMPTY_DATA;
        }

        if (order <= 0) {
            return RAW_MOMENT_WITH_NOT_POSITIVE_ORDER;
        }

        double[] processedStatistics = getProcessedStatistics();

        double rawMoment = 0.0;
        for (double instance : processedStatistics) {
            rawMoment += Math.pow(instance, order);
        }
        rawMoment /= processedStatistics.length;

        return rawMoment;
    }
}
