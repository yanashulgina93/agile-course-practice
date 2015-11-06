package ru.unn.agile.statistics.model;

import java.util.Collection;

public class StatisticValuesCalculator {

    private double[] processedStatistics;

    private static final double EPS_FOR_DOUBLE_COMPARISON = 1e-3;

    private static final double ENUMERATION_OF_EMPTY_DATA = 0.0;
    private static final double PROBABILITY_OF_EVENT_WITH_EMPTY_DATA = 0.0;
    private static final double PROBABILITY_OF_NULL_EVENT = 0.0;
    private static final double VARIANCE_OF_EMPTY_DATA = 0.0;
    private static final double VARIANCE_OF_DATA_WITH_SINGLE_INSTANCE = 0.0;
    private static final double RAW_MOMENT_OF_EMPTY_DATA = 0.0;
    private static final double RAW_MOMENT_WITH_NOT_POSITIVE_ORDER = 0.0;
    private static final double CENTRAL_MOMENT_OF_EMPTY_DATA = 0.0;
    private static final double CENTRAL_MOMENT_WITH_NOT_POSITIVE_ORDER = 0.0;

    public StatisticValuesCalculator(final Collection<IStatisticalResult> dataForStatistics) {
        setStatisticData(dataForStatistics);
    }

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

    public double calculateEnumeration() {
        if (isProcessedStatisticsEmpty()) {
            return ENUMERATION_OF_EMPTY_DATA;
        }

        double enumeration = 0.0;
        for (double instance : processedStatistics) {
            enumeration += instance;
        }
        enumeration /= processedStatistics.length;

        return enumeration;
    }

    public double calculateProbabilityOfEvent(final IStatisticalResult event) {
        if (isProcessedStatisticsEmpty()) {
            return PROBABILITY_OF_EVENT_WITH_EMPTY_DATA;
        }

        if (event == null) {
            return PROBABILITY_OF_NULL_EVENT;
        }

        double instanceOfEvent = event.get();
        double probability = 0.0;

        for (double instanceOfData : processedStatistics) {
            if (areTwoDoublesEqual(instanceOfData, instanceOfEvent)) {
                probability++;
            }
        }
        probability /= processedStatistics.length;
        return probability;
    }

    public double calculateVariance() {
        if (isProcessedStatisticsEmpty()) {
            return VARIANCE_OF_EMPTY_DATA;
        }

        if (processedStatistics.length == 1) {
            return VARIANCE_OF_DATA_WITH_SINGLE_INSTANCE;
        }

        double enumeration = calculateEnumeration();
        double variance = 0.0;

        for (double instanceOfData : processedStatistics) {
            variance += Math.pow(instanceOfData - enumeration, 2);
        }
        variance = variance / (processedStatistics.length - 1);

        return variance;
    }

    public double calculateRawMoment(final int order) {
        if (isProcessedStatisticsEmpty()) {
            return RAW_MOMENT_OF_EMPTY_DATA;
        }

        if (order <= 0) {
            return RAW_MOMENT_WITH_NOT_POSITIVE_ORDER;
        }

        double rawMoment = 0.0;
        for (double instance : processedStatistics) {
            rawMoment += Math.pow(instance, order);
        }
        rawMoment /= processedStatistics.length;

        return rawMoment;
    }

    public double calculateCentralMoment(final int order) {
        if (isProcessedStatisticsEmpty()) {
            return CENTRAL_MOMENT_OF_EMPTY_DATA;
        }
        if (order <= 0) {
            return CENTRAL_MOMENT_WITH_NOT_POSITIVE_ORDER;
        }

        double centralMoment = 0.0;
        double en = calculateEnumeration();
        for (double instance : processedStatistics) {
            centralMoment += Math.pow(instance - en, order);
        }
        centralMoment /= processedStatistics.length;
        return centralMoment;
    }

    private boolean isProcessedStatisticsEmpty() {
        return processedStatistics == null
                || processedStatistics.length == 0;
    }
    private boolean areTwoDoublesEqual(final double firstNumber, final double secondNumber) {
        return Math.abs(firstNumber - secondNumber) <= EPS_FOR_DOUBLE_COMPARISON;
    }
}
