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
        if(processedData == null) {
            return ENUMERATION_OF_EMPTY_DATA;
        }

        double result = 0.0;
        for (double instance : processedData) {
            result += instance;
        }
        result /= processedData.length;

        return result;
    }

    public double probabilityOf(IStatisticDataInstance event) {
        if(processedData == null){
            return PROBABILITY_OF_EVENT_WITH_EMPTY_DATA;
        }

        double instanceOfEvent = event.getInstance();
        double probability = 0.0;

        for(double instanceOfData : processedData){
            if(instanceOfData == instanceOfEvent){
                probability++;
            }
        }
        probability /= processedData.length;
        return probability;
    }

    private double[] processedData;
    private final double ENUMERATION_OF_EMPTY_DATA = 0.0;
    private final double PROBABILITY_OF_EVENT_WITH_EMPTY_DATA = 0.0;
}
