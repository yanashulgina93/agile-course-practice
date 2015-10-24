package ru.unn.agile.statistics.model;

public class StatisticValues
{
    public StatisticValues(Integer number) {
        processedData = new double[1];
        processedData[0] = number;
    }

    public StatisticValues(Integer[] dataForStatistics) {
        processedData = new double[dataForStatistics.length];
        for(int i = 0; i < dataForStatistics.length; i++){
            processedData[i] = dataForStatistics[i];
        }
    }

    public StatisticValues(int[] dataForStatistics) {
        processedData = new double[dataForStatistics.length];
        for(int i = 0; i < dataForStatistics.length; i++){
            processedData[i] = dataForStatistics[i];
        }
    }

    public double enumeration() {
        double result = 0.0;
        for(double instance : processedData)
        {
            result += instance;
        }
        result /= processedData.length;

        return result;
    }

    private double[] processedData;
}
