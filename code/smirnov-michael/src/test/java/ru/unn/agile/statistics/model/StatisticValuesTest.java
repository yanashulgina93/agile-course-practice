package ru.unn.agile.statistics.model;

import org.junit.Test;
import static org.junit.Assert.*;

public class StatisticValuesTest {
    @Test
    public void isEnumerationOfOneEqualsToOne()
    {
        double delta = 1e-8;
        double number = 1.0;
        StatisticValues statVal = new StatisticValues();
        double enumOfNumber = statVal.enumeration(number);

        assertEquals(1.0, enumOfNumber, delta);
    }
}
