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

    private final double houseCost;
    private final double downPayment;
    private final int creditPeriod;

    @Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {
                        -1800000.0, 18, 0.0
                },
                {
                        1800000.0, 0, 0.0
                },
                {
                        1800000.0, -2, 0.0
                },
                {
                        1800000.0, 2, -1000.0
                },
                {
                        1000.0, 2, 10000.0
                },
        });
    }

    public HypothecExceptionsTest(final double houseCost,
                                  final int creditPeriod,
                                  final double downPayment) {
        this.houseCost = houseCost;
        this.creditPeriod = creditPeriod;
        this.downPayment = downPayment;
    }

    @Test (expected = IllegalArgumentException.class)
    public void throwOnCreditWithIllegalParameters() {
        new Hypothec.Builder(houseCost, creditPeriod).setDownPayment(downPayment).build();
    }
}
