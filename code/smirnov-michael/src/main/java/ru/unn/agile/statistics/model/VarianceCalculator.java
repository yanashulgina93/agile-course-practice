package ru.unn.agile.statistics.model;

import java.util.Collection;

public class VarianceCalculator extends StatisticValueCalculator {

    private static final double VARIANCE_OF_EMPTY_DATA = 0.0;
    private static final double VARIANCE_OF_DATA_WITH_SINGLE_INSTANCE = 0.0;

    private EnumerationCalculator enumerationCalculator;

    public VarianceCalculator(final Collection<IStatisticalResult> dataForStatistics) {
        setStatisticData(dataForStatistics);
    }

    @Override
    public void setStatisticData(final Collection<IStatisticalResult> dataForStatistics) {
        super.setStatisticData(dataForStatistics);
        enumerationCalculator = new EnumerationCalculator(dataForStatistics);
    }

    @Override
    public double calculate() {
        if (isProcessedStatisticsEmpty()) {
            return VARIANCE_OF_EMPTY_DATA;
        }

        double[] processedStatistics = getProcessedStatistics();

        if (processedStatistics.length == 1) {
            return VARIANCE_OF_DATA_WITH_SINGLE_INSTANCE;
        }

        double enumeration = enumerationCalculator.calculate();

        double variance = 0.0;

        for (double instanceOfData : processedStatistics) {
            variance += Math.pow(instanceOfData - enumeration, 2);
        }
        variance = variance / (processedStatistics.length - 1);

        return variance;
    }
}
