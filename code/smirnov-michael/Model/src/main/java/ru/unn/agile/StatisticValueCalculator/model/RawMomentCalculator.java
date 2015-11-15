package ru.unn.agile.StatisticValueCalculator.model;

import java.security.InvalidParameterException;
import java.util.Collection;

public class RawMomentCalculator implements IStatisticValueCalculator {

    private static final double RAW_MOMENT_OF_EMPTY_DATA = 0.0;
    private static final double RAW_MOMENT_WITH_NOT_POSITIVE_ORDER = 0.0;

    private final int order;

    public RawMomentCalculator(final int order) throws InvalidParameterException {
        if (order <= 0) {
            throw new InvalidParameterException("Order must be >= 0");
        }
        this.order = order;
    }

    @Override
    public double calculate(final Collection<Double> dataForStatistics) throws NullPointerException {
        if (dataForStatistics == null) {
            throw new NullPointerException("Parameter dataForStatistics must not be null");
        }

        if (dataForStatistics.isEmpty()) {
            return RAW_MOMENT_OF_EMPTY_DATA;
        }

        int sizeOfData = dataForStatistics.size();
        if (order <= 0) {
            return RAW_MOMENT_WITH_NOT_POSITIVE_ORDER;
        }

        double rawMoment = 0.0;
        for (Double instanceOfData : dataForStatistics) {
            rawMoment += Math.pow(instanceOfData, order);
        }
        rawMoment /= sizeOfData;

        return rawMoment;
    }
}
