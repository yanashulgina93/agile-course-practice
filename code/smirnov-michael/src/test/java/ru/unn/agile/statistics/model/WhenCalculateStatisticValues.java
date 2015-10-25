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

    private void checkEnumerationWith(final double destination)
    {
        double enumOfData = statVal.enumeration();
        assertEquals(destination, enumOfData, deltaForDoubleAssertEquals);
    }

    private StatisticValues statVal;
    private double deltaForDoubleAssertEquals = 1e-8;
}
