package ru.unn.agile.HypothecsCalculator.core;

import org.junit.Test;
import java.util.GregorianCalendar;
import java.util.Calendar;

public class HypothecExceptionsTest {
    final double creditSum = 18000.0;
    final int creditPeriod  = 18;

    @Test (expected = IllegalArgumentException.class)
    public void trowOnNegativeCreditSum(){
        final double negativeCreditSum = -18000.0;
        new Hypothec.Builder(negativeCreditSum, creditPeriod).build();
    }

    @Test (expected = IllegalArgumentException.class)
    public void trowOnNegativeCreditPeriod(){
        final int negativeCreditPeriod  = -18;
        new Hypothec.Builder(creditSum, negativeCreditPeriod).build();
    }

    @Test (expected = IllegalArgumentException.class)
    public void trowOnZeroCreditPeriod(){
        final int zeroCreditPeriod  = 0;
        new Hypothec.Builder(creditSum, zeroCreditPeriod).build();
    }

    @Test (expected = IllegalArgumentException.class)
    public void trowOnNegativeDownPayment(){
        final double negativeDownPayment = -1000.0;
        new Hypothec.Builder(creditSum, creditPeriod).setDownPayment(negativeDownPayment).build();
    }

    @Test (expected = IllegalArgumentException.class)
    public void trowOnTooLargeDownPayment(){
        final double tooLargeDownPayment = 20000.0;
        new Hypothec.Builder(creditSum, creditPeriod).setDownPayment(tooLargeDownPayment).build();
    }

    @Test (expected = IllegalArgumentException.class)
    public void trowOnNegativeInterestRate(){
        final double negativeInterestRate = -100.0;
        new Hypothec.Builder(creditSum, creditPeriod).setInterestRate(negativeInterestRate).build();
    }

    @Test (expected = IllegalArgumentException.class)
    public void trowOnNegativeMonthlyFee(){
        final double negativeMonthlyFee = -100.0;
        new Hypothec.Builder(creditSum, creditPeriod).setMonthlyFee(negativeMonthlyFee).build();
    }

    @Test (expected = IllegalArgumentException.class)
    public void trowOnNegativeFlatFee(){
        final double negativeFlatFee = -100.0;
        new Hypothec.Builder(creditSum, creditPeriod).setMonthlyFee(negativeFlatFee).build();
    }

    @Test (expected = IllegalArgumentException.class)
    public void trowOnTooEarlyStartDate(){
        final GregorianCalendar tooEarlyDate = new GregorianCalendar(1980, Calendar.MARCH, 10);
        new Hypothec.Builder(creditSum, creditPeriod).setStartDate(tooEarlyDate).build();
    }

    @Test (expected = IllegalArgumentException.class)
    public void trowOnTooLargeStartDate(){
        final GregorianCalendar tooLargeDate = new GregorianCalendar(2150, Calendar.MARCH, 10);
        new Hypothec.Builder(creditSum, creditPeriod).setStartDate(tooLargeDate).build();
    }
}
