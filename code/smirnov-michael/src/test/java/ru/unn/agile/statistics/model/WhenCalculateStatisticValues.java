package ru.unn.agile.statistics.model;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;

import static org.junit.Assert.*;

public class WhenCalculateStatisticValues {
    @Before
    public void Preparing()
    {
        statVal = new StatisticValues();
        dataInstances = null;
    }

    @Test
    public void enumerationOfSimpleIntArrayIsCorrect(){
        int[] data = {5, 5, 5, 5};
        Collection<IStatisticDataInstance> dataInstances = StatisticDataConverter.ConvertFromIntArray(data);
        statVal.getData(dataInstances);

        checkEnumerationWith(5.0);
    }

    @Test
    public void enumerationOfIntegerArrayWithDifferentElementsIsCorrect()
    {
        int[] data = new int[100];
        for(int i = 0; i < data.length; i++){
            data[i] = i;
        }
        Collection<IStatisticDataInstance> dataInstances = StatisticDataConverter.ConvertFromIntArray(data);
        statVal.getData(dataInstances);

        checkEnumerationWith(49.5);
    }

    @Test
    public void enumerationOfDoubleArrayWithDataOfOneSinPeriodIsCorrect()
    {
        double[] data = new double[1000];
        for(int i = 0; i < data.length; i++){
            data[i] = Math.sin(i*2*Math.PI/1000.0);
        }
        Collection<IStatisticDataInstance> dataInstances = StatisticDataConverter.ConvertFromDoubleArray(data);
        statVal.getData(dataInstances);

       checkEnumerationWith(0.0);
    }

    @Test
    public void enumerationOfFloatArrayWithOneElementIsCorrect(){
        float[] data = {3.14f};
        Collection<IStatisticDataInstance> dataInstances = StatisticDataConverter.ConvertFromFloatArray(data);
        statVal.getData(dataInstances);

        checkEnumerationWith(3.14f);
    }

    @Test
    public void enumerationIsZeroWhenStatisticDataIsEmpty()
    {
        statVal.getData(null);
        checkEnumerationWith(0.0);
    }

    @Test
    public void statisticDataIsEmptyWhenConvertingIntArrayIsEmpty(){
        Collection<IStatisticDataInstance> dataInstances = StatisticDataConverter.ConvertFromIntArray(null);
        assertEquals(dataInstances, null);
    }

    @Test
    public void probabilityOfSingleEventEqualsToOne(){
        int[] data = {7};
        IStatisticDataInstance event = new NumericStatisticDataInstance(data[0]);
        formDataInstances(data);

        checkProbabilityWith(1, event);
    }

    @Test
    public void probabilityOfImpossibleEventEqualsToZero(){
        float[] data = {1.f,2.f,3.f,4.1f,5.f,6.f,7.f,8.f,9.f,10.f};
        IStatisticDataInstance event = new NumericStatisticDataInstance(4);
        formDataInstances(data);

        checkProbabilityWith(0.0, event);
    }

    @Test
    public void probabilityOfSomePossibleEventIsCorrect(){
        double[] data = {-1., 3.14, 5., 7.13, 8.25, 3.14};
        IStatisticDataInstance event = new NumericStatisticDataInstance(3.14f);
        formDataInstances(data);

        checkProbabilityWith(1/3, event);
    }

    @Test
    public void probabilityWithoutDataIsNull(){
        double[] data = null;
        IStatisticDataInstance event = new NumericStatisticDataInstance(0);
        formDataInstances(data);

        checkProbabilityWith(0.0, event);
    }

    private void checkEnumerationWith(final double destination)
    {
        double enumOfData = statVal.enumeration();
        assertEquals(destination, enumOfData, deltaForDoubleAssertEquals);
    }

    private void checkProbabilityWith(final double destination, final IStatisticDataInstance event)
    {
        double p = statVal.probabilityOf(event);
        assertEquals(destination, p, deltaForDoubleAssertEquals);
    }

    private void formDataInstances(double[] data){
        Collection<IStatisticDataInstance> dataInstances = StatisticDataConverter.ConvertFromDoubleArray(data);
        statVal.getData(dataInstances);
    }

    private void formDataInstances(float[] data){
        Collection<IStatisticDataInstance> dataInstances = StatisticDataConverter.ConvertFromFloatArray(data);
        statVal.getData(dataInstances);
    }

    private void formDataInstances(int[] data){
        Collection<IStatisticDataInstance> dataInstances = StatisticDataConverter.ConvertFromIntArray(data);
        statVal.getData(dataInstances);
    }

    private StatisticValues statVal;
    private Collection<IStatisticDataInstance> dataInstances;
    private double deltaForDoubleAssertEquals = 1e-8;
}
