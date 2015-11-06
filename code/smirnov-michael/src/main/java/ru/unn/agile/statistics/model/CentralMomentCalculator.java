package ru.unn.agile.statistics.model;

import java.util.Collection;

public class CentralMomentCalculator extends StatisticValueCalculator {

    private static final double CENTRAL_MOMENT_OF_EMPTY_DATA = 0.0;
    private static final double CENTRAL_MOMENT_WITH_NOT_POSITIVE_ORDER = 0.0;

    private int order;
    private EnumerationCalculator enumerationCalculator;

    public CentralMomentCalculator(final Collection<IStatisticalResult> dataForStatistics,
                                   final int order) {
        setStatisticData(dataForStatistics);
        setOrder(order);
    }

    public void setOrder(final int order) {
        this.order = order;
    }

    @Override
    public void setStatisticData(final Collection<IStatisticalResult> dataForStatistics) {
        super.setStatisticData(dataForStatistics);
        enumerationCalculator = new EnumerationCalculator(dataForStatistics);
    }

    @Override
    public double calculate() {
        if (isProcessedStatisticsEmpty()) {
            return CENTRAL_MOMENT_OF_EMPTY_DATA;
        }
        if (order <= 0) {
            return CENTRAL_MOMENT_WITH_NOT_POSITIVE_ORDER;
        }

        double[] processedStatistics = getProcessedStatistics();

        double centralMoment = 0.0;

        double enumeration = enumerationCalculator.calculate();
        for (double instance : processedStatistics) {
            centralMoment += Math.pow(instance - enumeration, order);
        }
        centralMoment /= processedStatistics.length;
        return centralMoment;
    }
}
