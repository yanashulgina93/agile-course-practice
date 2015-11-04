package ru.unn.agile.HypothecsCalculator.core;

import java.util.Arrays;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.Calendar;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class HypothecTest {

    private final double delta = 0.01;

    private final Hypothec hypothec;

    private final double rightLowestPayment;
    private final double rightHighestPayment;
    private final double rightOverpayment;
    private final double rightOverpaymentWithFees;

    @Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {
                        new Hypothec.Builder(18000.0, 18).build(),
                        1000.0,         1000.0,        0.0,         0.0
                //      HighestPayment, LowestPayment, Overpayment, OverpaymentWithFees
                },
                {
                        new Hypothec.Builder(15000.0, 10).setDownPayment(5000.0).build(),
                        1000.0,         1000.0,        0.0,         0.0
                //      HighestPayment, LowestPayment, Overpayment, OverpaymentWithFees
                },
                {
                        new Hypothec.Builder(12000.0, 1).setPeriodType(Hypothec.PeriodType.YEAR).build(),
                        1000.0,         1000.0,        0.0,         0.0
                //      HighestPayment, LowestPayment, Overpayment, OverpaymentWithFees
                },
                {
                        new Hypothec.Builder(12000.0, 1).setPeriodType(Hypothec.PeriodType.YEAR)
                                .setInterestRate(0.83333).build(),
                        1054.99,        1054.99,       659.89,      659.89
                //      HighestPayment, LowestPayment, Overpayment, OverpaymentWithFees
                },
                {
                        new Hypothec.Builder(12000.0, 12).setInterestRate(10)
                                .setInterestRateType(Hypothec.InterestRateType.YEARLY).build(),
                        1054.99,        1054.99,      659.89,    659.89
                //      HighestPayment, LowestPayment, Overpayment, OverpaymentWithFees
                },
                {
                        new Hypothec.Builder(12000.0, 12).setInterestRate(0.83333)
                                .setCreditType(Hypothec.CreditType.DIFFERENTIATED).build(),
                        1100.0,         1008.33,       650.0,       650.0
                //      HighestPayment, LowestPayment, Overpayment, OverpaymentWithFees
                },
                {
                        new Hypothec.Builder(12000.0, 12).setInterestRate(0.83333).
                                setMonthlyFee(1000.0).build(),
                        2054.99,        2054.99,       659.89,      12659.88
                //      HighestPayment, LowestPayment, Overpayment, OverpaymentWithFees
                },
                {
                        new Hypothec.Builder(12000.0, 12).setInterestRate(0.83333)
                                .setMonthlyFee(1.0)
                                .setMonthlyFeeType(Hypothec.MonthlyFeeType.CREDIT_SUM_PERCENT)
                                .build(),
                        1175.0,         1175.0,        659.89,      2099.88
                //      HighestPayment, LowestPayment, Overpayment, OverpaymentWithFees
                },
                {
                        new Hypothec.Builder(12000.0, 12).setInterestRate(0.83333)
                                .setMonthlyFee(1.0)
                                .setMonthlyFeeType(Hypothec.MonthlyFeeType.CREDIT_BALANCE_PERCENT)
                                .build(),
                        1165.44,        1055.0,        659.89,      1331.75
                //      HighestPayment, LowestPayment, Overpayment, OverpaymentWithFees
                },
                {
                        new Hypothec.Builder(12000.0, 12).setInterestRate(0.83333)
                                .setMonthlyFee(1.0)
                                .setMonthlyFeeType(Hypothec.MonthlyFeeType.CREDIT_BALANCE_PERCENT)
                                .setCreditType(Hypothec.CreditType.DIFFERENTIATED).build(),
                        1210.0,         1008.33,       650.0,       1310.0
                //      HighestPayment, LowestPayment, Overpayment, OverpaymentWithFees
                },
                {
                        new Hypothec.Builder(12000.0, 12).setInterestRate(0.83333).
                                setMonthlyFee(1000.0).setFlatFee(12000.0).build(),
                        2054.99,        2054.99,       659.89,      24659.88
                //      HighestPayment, LowestPayment, Overpayment, OverpaymentWithFees
                },
                {
                        new Hypothec.Builder(12000.0, 12).setInterestRate(0.83333).
                                setMonthlyFee(1000.0).setFlatFee(100.0)
                                .setFlatFeeType(Hypothec.FlatFeeType.PERCENT)
                                .build(),
                        2054.99,        2054.99,       659.89,      24659.88
                //      HighestPayment, LowestPayment, Overpayment, OverpaymentWithFees
                },
                {
                        new Hypothec.Builder(18000.0, 18).setCurrency(Hypothec.CurrencyType.DOLLAR).build(),
                        1000.0,         1000.0,        0.0,         0.0
                //      HighestPayment, LowestPayment, Overpayment, OverpaymentWithFees
                },
                {
                        new Hypothec.Builder(18000.0, 18)
                                .setStartDate(new GregorianCalendar(1992, Calendar.MARCH, 10)).build(),
                        1000.0,         1000.0,        0.0,         0.0
                        //      HighestPayment, LowestPayment, Overpayment, OverpaymentWithFees
                },



        });
    }

    public HypothecTest(final Hypothec hypothec,
                        final double rightHighestPayment,
                        final double rightLowestPayment,
                        final double rightOverpayment,
                        final double rightOverpaymentWithFees) {
        this.hypothec = hypothec;
        this.rightHighestPayment = rightHighestPayment;
        this.rightLowestPayment = rightLowestPayment;
        this.rightOverpayment = rightOverpayment;
        this.rightOverpaymentWithFees = rightOverpaymentWithFees;
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

    @Test
    public void canGetOverpayment() {
        final double overpayment = hypothec.computeOverpayment();

        assertEquals(rightOverpayment, overpayment, delta);
    }

    @Test
    public void canGetOverpaymentWithFees() {
        final double overpaymentWithFees = hypothec.computeOverpaymentWithFees();

        assertEquals(rightOverpaymentWithFees, overpaymentWithFees, delta);
    }
}
