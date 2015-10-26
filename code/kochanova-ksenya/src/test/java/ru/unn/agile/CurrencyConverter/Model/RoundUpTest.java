package ru.unn.agile.CurrencyConverter.Model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by ksenyako on 26.10.2015.
 */
public class RoundUpTest {
    @Test
    public void canCorrectRoundUpFractionalNumber() {
        RoundUp value = new RoundUp();
        double actualValueAfterRound = value.rounding(12.455555);
        double expectedValueAfterRound = 12.46;
        assertEquals(expectedValueAfterRound, actualValueAfterRound, 0.001);
    }
    @Test
    public void canCorrectRoundUpIntegerNumber() {
        RoundUp value = new RoundUp();
        double actualValueAfterRound = value.rounding(12);
        double expectedValueAfterRound = 12;
        assertEquals(expectedValueAfterRound, actualValueAfterRound, 0.001);
    }
}
