package ru.unn.agile.statistics.model;

import java.util.ArrayList;
import java.util.Collection;

public class NumericStatisticsConverter {
    private ArrayList<IStatisticDataInstance> dataInstances;

    public NumericStatisticsConverter() {
        dataInstances = null;
    }

    public NumericStatisticsConverter(final Number[] data) {
        setData(data);
    }

    public void setData(final Number[] data) {
        if (data == null) {
            return;
        }

        dataInstances = new ArrayList<>();
        for (int i = 0; i < data.length; i++) {
            dataInstances.add(new NumericStatisticDataInstance(data[i].doubleValue()));
        }
    }
    public Collection<IStatisticDataInstance> convertDataToStatistics() {
        return dataInstances;
    }
}
