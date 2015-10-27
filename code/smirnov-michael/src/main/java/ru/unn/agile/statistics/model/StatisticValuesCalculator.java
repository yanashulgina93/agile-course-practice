package ru.unn.agile.statistics.model;

import java.util.Collection;
import java.util.Iterator;

public class StatisticValuesCalculator {

    private double[] processedStatistics;

    private static final double EPS_FOR_DOUBLE_COMPARISON = 1e-3;

    private static final double ENUMERATION_OF_EMPTY_DATA = 0.0;
    private static final double PROBABILITY_OF_EVENT_WITH_EMPTY_DATA = 0.0;
    private static final double VARIANCE_OF_EMPTY_DATA = 0.0;
    private static final double VARIANCE_OF_DATA_WITH_SINGLE_INSTANCE = 0.0;
    private static final double RAW_MOMENT_OF_EMPTY_DATA = 0.0;
    private static final double RAW_MOMENT_WITH_NOT_POSITIVE_ORDER = 0.0;
    private static final double CENTRAL_MOMENT_OF_EMPTY_DATA = 0.0;
    private static final double CENTRAL_MOMENT_WITH_NOT_POSITIVE_ORDER = 0.0;

    public StatisticValuesCalculator(final Collection<IStatisticDataInstance> dataForStatistics) {
        setUsedStatistics(dataForStatistics);
    }

    public StatisticValuesCalculator() {
        processedStatistics = null;
    }

    public void setUsedStatistics(final Collection<IStatisticDataInstance> dataForStatistics) {
        if (dataForStatistics == null) {
            processedStatistics = null;
            return;
        }

        int dataSize = dataForStatistics.size();
        processedStatistics = new double[dataSize];
        Iterator<IStatisticDataInstance> dataIterator = dataForStatistics.iterator();
        for (int i = 0; i < dataSize; i++) {
            processedStatistics[i] = dataIterator.next().getInstance();
        }
    }

    public double calculateEnumerationOfStatistics() {
        if (processedStatistics == null) {
            return ENUMERATION_OF_EMPTY_DATA;
        }

        double enumeration = 0.0;
        for (double instance : processedStatistics) {
            enumeration += instance;
        }
        enumeration /= processedStatistics.length;

        return enumeration;
    }

    public double calculateProbabilityInStatisticsOfEvent(final IStatisticDataInstance event) {
        if (processedStatistics == null) {
            return PROBABILITY_OF_EVENT_WITH_EMPTY_DATA;
        }

        double instanceOfEvent = event.getInstance();
        double probability = 0.0;

        for (double instanceOfData : processedStatistics) {
            if (areTwoDoublesEqual(instanceOfData, instanceOfEvent)) {
                probability++;
            }
        }
        probability /= processedStatistics.length;
        return probability;
    }

    public double calculateVarianceOfStatistics() {
        if (processedStatistics == null) {
            return VARIANCE_OF_EMPTY_DATA;
        }

        if (processedStatistics.length == 1) {
            return VARIANCE_OF_DATA_WITH_SINGLE_INSTANCE;
        }

        double enumeration = calculateEnumerationOfStatistics();
        double variance = 0.0;

        for (double instanceOfData : processedStatistics) {
            variance += Math.pow(instanceOfData - enumeration, 2);
        }
        variance = variance / (processedStatistics.length - 1);

        return variance;
    }

    public double calculateRawMomentOfStatistics(final int order) {
        if (processedStatistics == null) {
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

    public double calculateCentralMomentOfStatistics(final int order) {
        if (processedStatistics == null) {
            return CENTRAL_MOMENT_OF_EMPTY_DATA;
        }
        if (order <= 0) {
            return CENTRAL_MOMENT_WITH_NOT_POSITIVE_ORDER;
        }

        double centralMoment = 0.0;
        double en = calculateEnumerationOfStatistics();
        for (double instance : processedStatistics) {
            centralMoment += Math.pow(instance - en, order);
        }
        centralMoment /= processedStatistics.length;
        return centralMoment;
    }

    private boolean areTwoDoublesEqual(final double firstNumber, final double secondNumber) {
        return Math.abs(firstNumber - secondNumber) <= EPS_FOR_DOUBLE_COMPARISON;
    }
}
