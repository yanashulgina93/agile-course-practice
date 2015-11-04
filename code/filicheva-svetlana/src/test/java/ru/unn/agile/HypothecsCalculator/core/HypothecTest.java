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

    private final double rightLowestPayment;
    private final double rightHighestPayment;

    @Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {
                        new Hypothec.Builder(18000.0, 18).build(),
                        1000.0, 1000.0
                },
                {
                        new Hypothec.Builder(15000.0, 10).setDownPayment(5000.0).build(),
                        1000.0, 1000.0
                },
                {
                        new Hypothec.Builder(12000.0, 1).setPeriodType(Hypothec.PeriodType.YEAR).build(),
                        1000.0, 1000.0
                },
                {
                        new Hypothec.Builder(12000.0, 1).setPeriodType(Hypothec.PeriodType.YEAR)
                                .setInterestRate(0.83333).build(),
                        1055.0, 1055.0
                },
                {
                        new Hypothec.Builder(12000.0, 12).setInterestRate(10)
                                .setInterestRateType(Hypothec.InterestRateType.YEARLY).build(),
                        1055.0, 1055.0
                },
                {
                        new Hypothec.Builder(12000.0, 12).setInterestRate(0.83333)
                                .setCreditType(Hypothec.CreditType.DIFFERENTIATED).build(),
                        1100.0, 1008.0
                },
                {
                        new Hypothec.Builder(12000.0, 12).setInterestRate(0.83333).
                                setMonthlyFee(1000.0).build(),
                        2055.0, 2055.0
                },
                {
                        new Hypothec.Builder(12000.0, 12).setInterestRate(0.83333)
                                .setMonthlyFee(1.0)
                                .setMonthlyFeeType(Hypothec.MonthlyFeeType.CREDIT_SUM_PERCENT)
                                .build(),
                        1175.0, 1175.0
                },
                {
                        new Hypothec.Builder(12000.0, 12).setInterestRate(0.83333)
                                .setMonthlyFee(1.0)
                                .setMonthlyFeeType(Hypothec.MonthlyFeeType.CREDIT_BALANCE_PERCENT)
                                .build(),
                        1165.0, 1055.0
                }
        });
    }

    public HypothecTest(final Hypothec hypothec,
                        final double rightHighestPayment,
                        final double rightLowestPayment) {
        this.hypothec = hypothec;
        this.rightHighestPayment = rightHighestPayment;
        this.rightLowestPayment = rightLowestPayment;
    }

    @Test
    public void canGetLowestPayment() {
        final double monthlyPayment = hypothec.computeLowestMonthlyPayment();

        assertEquals(rightLowestPayment, monthlyPayment, delta);
    }

    @Test
    public void canGetHighestPayment() {
        final double monthlyPayment = hypothec.computeHighestMonthlyPayment();

        assertEquals(rightHighestPayment, monthlyPayment, delta);
    }
}
