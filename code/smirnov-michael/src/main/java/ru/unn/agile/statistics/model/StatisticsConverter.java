package ru.unn.agile.statistics.model;

import java.util.ArrayList;
import java.util.Collection;

public class StatisticsConverter {
    private ArrayList<IStatisticDataInstance> dataInstances;

    public StatisticsConverter() {
        dataInstances = null;
    }

    public StatisticsConverter(final Number[] data) {
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
    public Collection<IStatisticDataInstance> convertNumericalDataToStatistics() {
        return dataInstances;
    }
}
