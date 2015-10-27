package ru.unn.agile.statistics.model;

import java.util.ArrayList;
import java.util.Collection;

public interface IStatisticsInstanceArrayConverterFromNumericData {
     static Collection<IStatisticDataInstance> convert(final Number[] data) {
        if (data == null) {
            return null;
        }

        ArrayList<IStatisticDataInstance> dataInstances = new ArrayList<>();
        for (int i = 0; i < data.length; i++) {
            dataInstances.add(new NumericStatisticDataInstance(data[i].doubleValue()));
        }
        return dataInstances;
    }
}
