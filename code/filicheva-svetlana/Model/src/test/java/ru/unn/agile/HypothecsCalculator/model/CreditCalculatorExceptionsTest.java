package ru.unn.agile.HypothecsCalculator.model;

import org.junit.Test;
import java.util.GregorianCalendar;
import java.util.Calendar;

public class CreditCalculatorExceptionsTest {
    private final double creditSum = 18000.0;
    private final int creditPeriod  = 18;

    @Test (expected = IllegalArgumentException.class)
    public void throwOnNegativeCreditSum() {
        final double negativeCreditSum = -18000.0;

        new CreditCalculator(new Hypothec.Builder(negativeCreditSum, creditPeriod).build());
    }

    @Test (expected = IllegalArgumentException.class)
    public void throwOnNegativeCreditPeriod() {
        final int negativeCreditPeriod  = -18;

        new CreditCalculator(new Hypothec.Builder(creditSum, negativeCreditPeriod).build());
    }

    @Test (expected = IllegalArgumentException.class)
    public void throwOnZeroCreditPeriod() {
        final int zeroCreditPeriod  = 0;

        new CreditCalculator(new Hypothec.Builder(creditSum, zeroCreditPeriod).build());
    }

    @Test (expected = IllegalArgumentException.class)
    public void throwOnNegativeDownPayment() {
        final double negativeDownPayment = -1000.0;

        new CreditCalculator(new Hypothec.Builder(creditSum, creditPeriod)
                .downPayment(negativeDownPayment).build());
    }

    @Test (expected = IllegalArgumentException.class)
    public void throwOnTooLargeDownPayment() {
        final double tooLargeDownPayment = 20000.0;

        new CreditCalculator(new Hypothec.Builder(creditSum, creditPeriod)
                .downPayment(tooLargeDownPayment).build());
    }

    @Test (expected = IllegalArgumentException.class)
    public void throwOnNegativeInterestRate() {
        final double negativeInterestRate = -100.0;

        new CreditCalculator(new Hypothec.Builder(creditSum, creditPeriod)
                .interestRate(negativeInterestRate).build());
    }

    @Test (expected = IllegalArgumentException.class)
    public void throwOnNegativeMonthlyFee() {
        final double negativeMonthlyFee = -100.0;

        new CreditCalculator(new Hypothec.Builder(creditSum, creditPeriod)
                .monthlyFee(negativeMonthlyFee).build());
    }

    @Test (expected = IllegalArgumentException.class)
    public void throwOnNegativeFlatFee() {
        final double negativeFlatFee = -100.0;

        new CreditCalculator(new Hypothec.Builder(creditSum, creditPeriod)
                .monthlyFee(negativeFlatFee).build());
    }

    @Test (expected = IllegalArgumentException.class)
    public void throwOnTooEarlyStartDate() {
        final GregorianCalendar tooEarlyDate = new GregorianCalendar(1980, Calendar.MARCH, 10);

        new CreditCalculator(new Hypothec.Builder(creditSum, creditPeriod)
                .startDate(tooEarlyDate).build());
    }

    @Test (expected = IllegalArgumentException.class)
    public void throwOnTooLargeStartDate() {
        final GregorianCalendar tooLargeDate = new GregorianCalendar(2150, Calendar.MARCH, 10);

        new CreditCalculator(new Hypothec.Builder(creditSum, creditPeriod)
                .startDate(tooLargeDate).build());
    }
}
