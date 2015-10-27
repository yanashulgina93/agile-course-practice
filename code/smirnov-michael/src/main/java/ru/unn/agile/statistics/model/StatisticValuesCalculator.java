package ru.unn.agile.statistics.model;

import java.util.Collection;
import java.util.Iterator;

public class StatisticValuesCalculator
{
    public StatisticValuesCalculator(Collection<IStatisticDataInstance> dataForStatistics) {
        setUsedStatistics(dataForStatistics);
    }

    public StatisticValuesCalculator() {
        processedStatistics = null;
    }

    public void setUsedStatistics(Collection<IStatisticDataInstance> dataForStatistics){
        if(dataForStatistics == null)
        {
            processedStatistics = null;
            return;
        }

        int dataSize = dataForStatistics.size();
        processedStatistics = new double[dataSize];
        Iterator<IStatisticDataInstance> dataIterator = dataForStatistics.iterator();
        for(int i = 0; i < dataSize; i++){
            processedStatistics[i] = dataIterator.next().getInstance();
        }
    }

    public double calculateEnumerationOfStatistics() {
        if(processedStatistics == null) {
            return ENUMERATION_OF_EMPTY_DATA;
        }

        double enumeration = 0.0;
        for (double instance : processedStatistics) {
            enumeration += instance;
        }
        enumeration /= processedStatistics.length;

        return enumeration;
    }

    public double calculateProbabilityInStatisticsOfEvent(IStatisticDataInstance event) {
        if(processedStatistics == null){
            return PROBABILITY_OF_EVENT_WITH_EMPTY_DATA;
        }

        double instanceOfEvent = event.getInstance();
        double probability = 0.0;

        for(double instanceOfData : processedStatistics){
            if(areTwoDoublesEqual(instanceOfData, instanceOfEvent)){
                probability++;
            }
        }
        probability /= processedStatistics.length;
        return probability;
    }

    public double calculateVarianceOfStatistics() {
        if(processedStatistics == null){
            return VARIANCE_OF_EMPTY_DATA;
        }

        if(processedStatistics.length == 1){
            return VARIANCE_OF_DATA_WITH_SIGLE_INSTANCE;
        }

        double en = calculateEnumerationOfStatistics();
        double var = 0.0;

        for(double instanceOfData : processedStatistics){
            var += Math.pow(instanceOfData - en, 2);
        }
        var /= (processedStatistics.length - 1);

        return var;
    }

    public double calculateRawMomentOfStatistics(int order) {
        if(processedStatistics == null) {
            return RAW_MOMENT_OF_EMPTY_DATA;
        }

        if(order <= 0){
            return RAW_MOMENT_WITH_NOT_POSITIVE_ORDER;
        }

        double rawMom = 0.0;
        for (double instance : processedStatistics) {
            rawMom += Math.pow(instance, order);
        }
        rawMom /= processedStatistics.length;

        return rawMom;
    }

    public double calculateCentralMomentOfStatistics(int order) {
        if(processedStatistics == null){
            return CENTRAL_MOMENT_OF_EMPTY_DATA;
        }
        if(order <= 0){
            return CENTRAL_MOMENT_WITH_NOT_POSITIVE_ORDER;
        }

        double centralMom = 0.0;
        double en = calculateEnumerationOfStatistics();
        for(double instance : processedStatistics){
            centralMom += Math.pow(instance - en, order);
        }
        centralMom /= processedStatistics.length;
        return centralMom;
    }

    private boolean areTwoDoublesEqual(double firstNumber, double secondNumber){
        return (Math.abs(firstNumber - secondNumber) <= EPS_FOR_DOUBLE_COMPARISON);
    }

    private double[] processedStatistics;

    private final double EPS_FOR_DOUBLE_COMPARISON = 1e-3;

    private final double ENUMERATION_OF_EMPTY_DATA = 0.0;
    private final double PROBABILITY_OF_EVENT_WITH_EMPTY_DATA = 0.0;
    private final double VARIANCE_OF_EMPTY_DATA = 0.0;
    private final double VARIANCE_OF_DATA_WITH_SIGLE_INSTANCE = 0.0;
    private final double RAW_MOMENT_OF_EMPTY_DATA = 0.0;
    private final double RAW_MOMENT_WITH_NOT_POSITIVE_ORDER = 0.0;
    private final double CENTRAL_MOMENT_OF_EMPTY_DATA = 0.0;
    private final double CENTRAL_MOMENT_WITH_NOT_POSITIVE_ORDER = 0.0;
}
