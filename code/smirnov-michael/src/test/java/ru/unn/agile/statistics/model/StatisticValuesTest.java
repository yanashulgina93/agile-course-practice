package ru.unn.agile.statistics.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class StatisticValuesTest {
    @Test
    public void EnumerationOfOneIsEqualsToOne() {
        int number = 1;
        StatisticValues statVal = new StatisticValues(number);

        double enumOfNumber = statVal.enumeration();

        assertEquals(1.0, enumOfNumber, deltaForDoubleAssertEquals);
    }

    @Test
    public void enumerationOfSimpleIntArrayIsCorrect(){
        Integer[] data = {5, 5, 5, 5};
        StatisticValues statVal = new StatisticValues(data);

        double enumOfData = statVal.enumeration();

        assertEquals(5.0, enumOfData, deltaForDoubleAssertEquals);
    }

    @Test
    public void enumerationOfIntegerArrayWithDifferentElements()
    {
        int[] data = new int[100];
        for(int i = 0; i < 100; i++){
            data[i] = i;
        }
        StatisticValues statVal = new StatisticValues(data);

        double enumOfData = statVal.enumeration();

        assertEquals(49.5, enumOfData, deltaForDoubleAssertEquals);
    }

    private double deltaForDoubleAssertEquals = 1e-8;
}
