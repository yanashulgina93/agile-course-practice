package ru.unn.agile.statistics.model;

import java.util.ArrayList;
import java.util.Collection;

public final class StatisticDataConverter {
    public static Collection<IStatisticDataInstance> ConvertFromIntArray(int[] data){
        if(data == null) {
            return null;
        }

        ArrayList<IStatisticDataInstance> dataInstances = new ArrayList<>();
        for(int i = 0; i < data.length; i++){
            dataInstances.add(new NumericStatisticDataInstance(data[i]));
        }
        return dataInstances;
    }

    public static Collection<IStatisticDataInstance> ConvertFromFloatArray(float[] data){
        if(data == null) {
            return null;
        }

        ArrayList<IStatisticDataInstance> dataInstances = new ArrayList<>();
        for(int i = 0; i < data.length; i++){
            dataInstances.add(new NumericStatisticDataInstance(data[i]));
        }
        return dataInstances;
    }

    public static Collection<IStatisticDataInstance> ConvertFromDoubleArray(double[] data){
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
