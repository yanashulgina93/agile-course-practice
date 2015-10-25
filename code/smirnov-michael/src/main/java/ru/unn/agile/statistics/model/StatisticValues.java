package ru.unn.agile.statistics.model;

import java.util.Collection;
import java.util.Iterator;

public class StatisticValues
{
    public StatisticValues(Collection<IStatisticDataInstance> dataForStatistics) {
        getData(dataForStatistics);
    }

    public StatisticValues() {
        processedData = null;
    }

    public void getData(Collection<IStatisticDataInstance> dataForStatistics){
        if(dataForStatistics == null)
        {
            processedData = null;
            return;
        }

        int dataSize = dataForStatistics.size();
        processedData = new double[dataSize];
        Iterator<IStatisticDataInstance> dataIterator = dataForStatistics.iterator();
        for(int i = 0; i < dataSize; i++){
            processedData[i] = dataIterator.next().getInstance();
        }
    }

    public double enumeration() {
        double result = 0.0;

        if(processedData != null) {
            for (double instance : processedData) {
                result += instance;
            }
            result /= processedData.length;
        }

        return result;
    }

    private double[] processedData;
}
