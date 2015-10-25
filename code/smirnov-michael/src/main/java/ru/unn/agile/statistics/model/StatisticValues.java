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

        double en = 0.0;
        for (double instance : processedData) {
            en += instance;
        }
        en /= processedData.length;

        return en;
    }

    public double probabilityOf(IStatisticDataInstance event) {
        if(processedData == null){
            return PROBABILITY_OF_EVENT_WITH_EMPTY_DATA;
        }

        double instanceOfEvent = event.getInstance();
        double probability = 0.0;

        for(double instanceOfData : processedData){
            if(areTwoDoublesEqual(instanceOfData, instanceOfEvent)){
                probability++;
            }
        }
        probability /= processedData.length;
        return probability;
    }

    public double variance() {
        if(processedData == null){
            return VARIANCE_OF_EMPTY_DATA;
        }

        double en = enumeration();
        double var = 0.0;

        for(double instanceOfData : processedData){
            var += Math.pow(instanceOfData - en, 2);
        }
        var /= (processedData.length - 1);

        return var;
    }

    public double rawMoment(int order) {
        if(processedData == null) {
            return RAW_MOMENT_OF_EMPTY_DATA;
        }

        double rawMom = 0.0;
        for (double instance : processedData) {
            rawMom += Math.pow(instance, order);
        }
        rawMom /= processedData.length;

        return rawMom;
    }

    private boolean areTwoDoublesEqual(double firstNumber, double secondNumber){
        return Math.abs(firstNumber - secondNumber) <= EPS_FOR_DOUBLE_COMPARISON;
    }

    private double[] processedData;

    private final double EPS_FOR_DOUBLE_COMPARISON = 1e-3;

    private final double ENUMERATION_OF_EMPTY_DATA = 0.0;
    private final double PROBABILITY_OF_EVENT_WITH_EMPTY_DATA = 0.0;
    private final double VARIANCE_OF_EMPTY_DATA = 0.0;
    private final double RAW_MOMENT_OF_EMPTY_DATA = 0.0;
}
