package ru.unn.agile.statistics.model;

import java.util.ArrayList;
import java.util.Collection;

public class CentralMomentCalculator extends RawMomentCalculator {

    private static final double CENTRAL_MOMENT_OF_EMPTY_DATA = 0.0;

    private final EnumerationCalculator enumerationCalculator;

    public CentralMomentCalculator(final int order) {
        super(order);
        enumerationCalculator = new EnumerationCalculator();
    }

    @Override
    public double calculate(final Collection<Double> dataForStatistics) throws NullPointerException {
        if (dataForStatistics == null) {
            throw new NullPointerException("Parameter dataForStatistics must not be null");
        }

        if (dataForStatistics.isEmpty()) {
            return CENTRAL_MOMENT_OF_EMPTY_DATA;
        }

        double enumeration = enumerationCalculator.calculate(dataForStatistics);
        ArrayList<Double> centeredData = new ArrayList<>();
        for (Double instanceOfData : dataForStatistics) {
            centeredData.add(instanceOfData - enumeration);
        }

        return super.calculate(centeredData);
    }
}
