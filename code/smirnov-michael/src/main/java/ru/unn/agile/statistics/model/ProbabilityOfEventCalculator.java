package ru.unn.agile.statistics.model;

import java.util.Collection;

public class ProbabilityOfEventCalculator extends StatisticValueCalculator {

    private IStatisticalResult event;

    private static final double EPS_FOR_DOUBLE_COMPARISON = 1e-3;

    private static final double PROBABILITY_OF_EVENT_WITH_EMPTY_DATA = 0.0;
    private static final double PROBABILITY_OF_NULL_EVENT = 0.0;

    public ProbabilityOfEventCalculator(final Collection<IStatisticalResult> dataForStatistics,
                                        final IStatisticalResult event) {
        setStatisticData(dataForStatistics);
        setEvent(event);
    }

    public void setEvent(final IStatisticalResult event) {
        this.event = event;
    }

    @Override
    public double calculate() {
        if (isProcessedStatisticsEmpty()) {
            return PROBABILITY_OF_EVENT_WITH_EMPTY_DATA;
        }

        if (event == null) {
            return PROBABILITY_OF_NULL_EVENT;
        }

        double instanceOfEvent = event.get();
        double probability = 0.0;

        double[] processedStatistics = getProcessedStatistics();

        for (double instanceOfData : processedStatistics) {
            if (areTwoDoublesEqual(instanceOfData, instanceOfEvent)) {
                probability++;
            }
        }
        probability /= processedStatistics.length;
        return probability;
    }

    private boolean areTwoDoublesEqual(final double firstNumber, final double secondNumber) {
        return Math.abs(firstNumber - secondNumber) <= EPS_FOR_DOUBLE_COMPARISON;
    }
}
