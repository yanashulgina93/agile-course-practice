package ru.unn.agile.PercentAccretion.Model;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class PercentAccretionTest {
    private PercentData data;
    private Factory factory;

    @Before
    public void initializePercentData() {
        data = new PercentData();
        data.setInitialSum(100);
        data.setPercentRate(50);
        data.setCountOfYears(1);
        factory = new Factory();
    }

    @Test
    public void canCalculateSumWithSimplePercent() {
        double expectedSum = 150.0;
        PercentAccretion simplePercentCounter = factory.getPercentAccretion(
                Factory.PercentAccretionOperations.SIMPLE_PERCENT_SUM.toString());
        double actualSum = simplePercentCounter.calculate(data);
        double delta = 0.00001;

        assertEquals(expectedSum, actualSum, delta);
    }

    @Test (expected = IllegalArgumentException.class)
    public void catchExceptionWhenWrongArgInSimplePercent() {
        data.setPercentRate(-1);
        PercentAccretion simplePercentCounter = factory.getPercentAccretion(
                Factory.PercentAccretionOperations.SIMPLE_PERCENT_SUM.toString());
        simplePercentCounter.calculate(data);
    }

    @Test (expected = IllegalArgumentException.class)
    public void catchExceptionWhenWrongArgInComplexPercent() {
        data.setPercentRate(-1);
        PercentAccretion complexPercentCounter = factory.getPercentAccretion(
                Factory.PercentAccretionOperations.COMPLEX_PERCENT_SUM.toString());
        complexPercentCounter.calculate(data);
    }

    @Test
    public void canCalculateSumWithComplexPercent() {
        double expectedValue = 150.0;
        PercentAccretion complexPercentCounter = factory.getPercentAccretion(
                Factory.PercentAccretionOperations.COMPLEX_PERCENT_SUM.toString());
        double actualValue = complexPercentCounter.calculate(data);
        double delta = 0.00001;

        assertEquals(expectedValue, actualValue, delta);
    }

    @Test
    public void canGetSimplePercentAccretionFromFactory() {
        assertEquals(SimplePercentAccretion.class,
                     factory.getPercentAccretion(Factory.
                             PercentAccretionOperations.SIMPLE_PERCENT_SUM.toString()).getClass());
    }

    @Test
    public void canGetComplexPercentAccretionFromFactory() {
        assertEquals(ComplexPercentAccretion.class,
                     factory.getPercentAccretion(Factory.
                             PercentAccretionOperations.COMPLEX_PERCENT_SUM.toString()).getClass());
    }

    @Test
    public void canGetNullWhenWrongArgumentFromFactory() {
        assertEquals(null, factory.getPercentAccretion("sda"));
    }
}
