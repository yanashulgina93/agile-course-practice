package ru.unn.agile.HypothecsCalculator.core;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class HypothecTest {

    private final double delta = 0.001;

    private final Hypothec hypothec;
    private final double rightMonthlyPayment;

    @Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {
                        new Hypothec.Builder(18000.0, 18).build(),
                        1000.0
                },
                {
                        new Hypothec.Builder(15000.0, 10).setDownPayment(5000.0).build(),
                        1000.0
                },
                {
                        new Hypothec.Builder(12000.0, 1).setPeriodType(Hypothec.PeriodType.YEAR).build(),
                        1000.0
                },
                {
                        new Hypothec.Builder(12000.0, 1).setPeriodType(Hypothec.PeriodType.YEAR)
                                .setInterestRate(0.83333).build(),
                        1055
                }
        });
    }

    public HypothecTest(final Hypothec hypothec, final double rightMonthlyPayment) {
        this.hypothec = hypothec;
        this.rightMonthlyPayment = rightMonthlyPayment;
    }

    @Test
    public void canGetMonthlyPayment() {
        final double monthlyPayment = hypothec.computeMonthlyPayment();

        assertEquals(rightMonthlyPayment, monthlyPayment, delta);
    }
}
