package ru.unn.agile.HypothecsCalculator.core;

import java.util.Arrays;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.Calendar;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class HypothecExceptionsTest {

    @Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(parametersOfTests);
    }

    private final double houseCost;
    private final double downPayment;
    private final double interestRate;
    private final int creditPeriod;
    private final double monthlyFee;
    private final double flatFee;
    private final GregorianCalendar startDate;

    @Test (expected = IllegalArgumentException.class)
    public void throwOnCreditWithIllegalParameters() {
        new Hypothec.Builder(houseCost, creditPeriod)
                .setDownPayment(downPayment)
                .setInterestRate(interestRate)
                .setMonthlyFee(monthlyFee)
                .setFlatFee(flatFee)
                .setStartDate(startDate)
                .build();
    }

    public HypothecExceptionsTest(final double houseCost,
                                  final int creditPeriod,
                                  final double downPayment,
                                  final double interestRate,
                                  final double monthlyFee,
                                  final double flatFee,
                                  final GregorianCalendar startDate) {
        this.houseCost = houseCost;
        this.creditPeriod = creditPeriod;
        this.downPayment = downPayment;
        this.interestRate = interestRate;
        this.monthlyFee = monthlyFee;
        this.flatFee = flatFee;
        this.startDate = startDate;
    }

    private static final GregorianCalendar RIGHT_DATE
            = new GregorianCalendar(1980, Calendar.MARCH, 10);
    private static final GregorianCalendar WRONG_DATE
            = new GregorianCalendar(2150, Calendar.MARCH, 10);

    private static Object[][] parametersOfTests = new Object[][]{
    //            houseCost,creditPeriod,downPayment,interestRate,monthlyFee,flatFee,startDate
            {
                    -18000.0,    18,        0.0,         0.0,        0.0,      0.0,   RIGHT_DATE
            },
            {
                    18000.0,      0,        0.0,         0.0,        0.0,      0.0,   RIGHT_DATE
            },
            {
                    18000.0,     -2,        0.0,         0.0,        0.0,      0.0,   RIGHT_DATE
            },
            {
                    18000.0,      2,    -1000.0,         0.0,        0.0,      0.0,   RIGHT_DATE
            },
            {
                    18000.0,      2,    20000.0,         0.0,        0.0,      0.0,   RIGHT_DATE
            },
            {
                    18000.0,      2,        0.0,       -10.0,        0.0,      0.0,   RIGHT_DATE
            },
            {
                    18000.0,      2,        0.0,        10.0,     -100.0,      0.0,   RIGHT_DATE
            },
            {
                    18000.0,      2,        0.0,        10.0,      100.0,   -110.0,   RIGHT_DATE
            },
            {
                    18000.0,      2,        0.0,        10.0,      100.0,    110.0,   RIGHT_DATE
            },
            {
                    18000.0,      2,        0.0,        10.0,      100.0,    110.0,   WRONG_DATE
            }
    };


}
