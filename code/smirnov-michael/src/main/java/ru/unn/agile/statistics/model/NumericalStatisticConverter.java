package ru.unn.agile.statistics.model;

import java.util.ArrayList;
import java.util.Collection;

public class NumericalStatisticConverter implements IStatisticConverter {
    private ArrayList<IStatisticalResult> dataInstances;

    public NumericalStatisticConverter(final Number[] data) {
        setData(data);
    }

    public void setData(final Number[] data) {
        if (data == null) {
            dataInstances = null;
            return;
        }

        dataInstances = new ArrayList<>();
        for (int i = 0; i < data.length; i++) {
            dataInstances.add(new NumericStatisticalResult(data[i].doubleValue()));
        }
    }

    @Override
    public Collection<IStatisticalResult> convert() {
        return dataInstances;
    }
}
