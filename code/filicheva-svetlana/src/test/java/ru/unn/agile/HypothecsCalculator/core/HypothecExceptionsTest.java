package ru.unn.agile.HypothecsCalculator.core;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class HypothecExceptionsTest {
    private final double delta = 0.001;

    private final double creditSum;
    private final int creditPeriod;

    @Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {
                        -1800000.0, 18
                },
                {
                        1800000.0, 0
                },
                {
                        1800000.0, -2
                },
        });
    }

    public HypothecExceptionsTest(final double creditSum, final int creditPeriod) {
        this.creditSum = creditSum;
        this.creditPeriod = creditPeriod;
    }

    @Test (expected = IllegalArgumentException.class)
    public void throwOnCreditWithIllegalParameters() {
        new Hypothec.Builder(creditSum, creditPeriod).build();
    }
}
