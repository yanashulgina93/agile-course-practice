package ru.unn.agile.PercentAccretion.Model;

import org.junit.Test;
import static org.junit.Assert.*;

public class PercentAccretionTest {

    @Test
    public void canCalculateSumWithSimplePercent() {
        double expectedSum = 150.0;
        double actualSum = PercentAccretion.calculateSumWithSimplePercent(100, 50, 1);
        double delta = 0.00001;
        assertEquals(expectedSum, actualSum, delta);
    }

    @Test (expected = IllegalArgumentException.class)
    public void catchExceptionWhenWrongArgInSimplePercent() {
        PercentAccretion.calculateSumWithSimplePercent(-1, 1, 1);
    }

    @Test (expected = IllegalArgumentException.class)
    public void catchExceptionWhenWrongArgInComplexPercent() {
        PercentAccretion.calculateSumWithComplexPercent(1, -1, 1);
    }

    @Test (expected = IllegalArgumentException.class)
    public void catchExceptionWhenWrongArgsInNominalPercent() {
        PercentAccretion.calculateSumWithNominalPercentRate(100, 100, -1, 1);
    }

    @Test (expected = IllegalArgumentException.class)
    public void catchExceptionWhenWrongArgsInEffectivePercentRate() {
        PercentAccretion.calculateEffectivePercentRate(-1, 90);
    }

    @Test (expected = IllegalArgumentException.class)
    public void catchExceptionWhenWrongArgsEffectivePercentSum() {
        PercentAccretion.calculateSumWithEffectivePercentRate(1, 1, 1, -1);
    }

    @Test
    public void canCalculateSumWithComplexPercent() {
        double expectedValue = 337.5;
        double actualValue = PercentAccretion.calculateSumWithComplexPercent(100, 50, 3);
        double delta = 0.00001;
        assertEquals(expectedValue, actualValue, delta);
    }

    @Test
    public void canCalculateSumWithNominalRate() {
        double expectedValue = 234331.8;
        double actualValue = PercentAccretion.calculateSumWithNominalPercentRate(200000, 8, 4, 2);
        double delta = 0.1;
        assertEquals(expectedValue, actualValue, delta);
    }

    @Test
    public void canCalculateEffectiveRate() {
        double expectedValue = 19.56;
        double actualValue = PercentAccretion.calculateEffectivePercentRate(18, 12);
        double delta = 0.01;
        assertEquals(expectedValue, actualValue, delta);
    }

    @Test
    public void canCalculateSumWithEffectiveRate() {
        double expectedValue = 234331.8;
        double actualValue = PercentAccretion.calculateSumWithEffectivePercentRate(200000, 8, 4, 2);
        double delta = 0.1;
        assertEquals(expectedValue, actualValue, delta);
    }


    @Test
    public void compareNominalAndEffectiveSum() {
        double delta = 0.0001;
        assertEquals(PercentAccretion.calculateSumWithNominalPercentRate(1000, 10, 12, 3),
                PercentAccretion.calculateSumWithEffectivePercentRate(1000, 10, 12, 3), delta);
    }
}
