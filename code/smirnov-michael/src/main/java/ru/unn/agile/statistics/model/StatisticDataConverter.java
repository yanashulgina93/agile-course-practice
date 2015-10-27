package ru.unn.agile.statistics.model;

import java.util.ArrayList;
import java.util.Collection;

public final class StatisticDataConverter {
    public static Collection<IStatisticDataInstance> convertFromIntArray(int[] data){
        if(data == null) {
            return null;
        }

        ArrayList<IStatisticDataInstance> dataInstances = new ArrayList<>();
        for(int i = 0; i < data.length; i++){
            dataInstances.add(new NumericStatisticDataInstance(data[i]));
        }
        return dataInstances;
    }

    public static Collection<IStatisticDataInstance> convertFromFloatArray(float[] data){
        if(data == null) {
            return null;
        }

        ArrayList<IStatisticDataInstance> dataInstances = new ArrayList<>();
        for(int i = 0; i < data.length; i++){
            dataInstances.add(new NumericStatisticDataInstance(data[i]));
        }
        return dataInstances;
    }

    public static Collection<IStatisticDataInstance> convertFromDoubleArray(double[] data){
        if(data == null) {
            return null;
        }

        ArrayList<IStatisticDataInstance> dataInstances = new ArrayList<>();
        for(int i = 0; i < data.length; i++){
            dataInstances.add(new NumericStatisticDataInstance(data[i]));
        }
        return dataInstances;
    }
}
