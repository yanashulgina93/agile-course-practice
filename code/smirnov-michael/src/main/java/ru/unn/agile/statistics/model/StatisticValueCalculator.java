package ru.unn.agile.statistics.model;

import java.util.Collection;

public abstract class StatisticValueCalculator {
    private double[] processedStatistics;

    public void setStatisticData(final Collection<IStatisticalResult> dataForStatistics) {
        if (dataForStatistics == null) {
            processedStatistics = null;
            return;
        }

        int dataSize = dataForStatistics.size();
        processedStatistics = new double[dataSize];

        int currentInstanceIndex = 0;
        for (IStatisticalResult instance : dataForStatistics) {
            processedStatistics[currentInstanceIndex] = instance.get();
            currentInstanceIndex++;
        }
    }

    public double[] getProcessedStatistics() {
        return processedStatistics.clone();
    }

    public abstract double calculate();

    protected boolean isProcessedStatisticsEmpty() {
        return processedStatistics == null
                || processedStatistics.length == 0;
    }
}
