package ru.unn.agile.StatisticValueCalculator.model;

import java.util.Collection;

public class VarianceCalculator extends CentralMomentCalculator {

    private static final double VARIANCE_OF_EMPTY_DATA = 0.0;
    private static final double VARIANCE_OF_DATA_WITH_SINGLE_INSTANCE = 0.0;

    public VarianceCalculator() {
        super(2);
    }

    @Override
    public double calculate(final Collection<Double> dataForStatistics) {
        if (dataForStatistics == null) {
            throw new NullPointerException("Parameter dataForStatistics must not be null");
        }

        if (dataForStatistics.isEmpty()) {
            return VARIANCE_OF_EMPTY_DATA;
        }

        int sizeOfData = dataForStatistics.size();
        if (sizeOfData == 1) {
            return VARIANCE_OF_DATA_WITH_SINGLE_INSTANCE;
        }

        double offsetCoefficient = (double) sizeOfData / (sizeOfData - 1.0);
        return offsetCoefficient * super.calculate(dataForStatistics);
    }
}
