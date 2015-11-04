package ru.unn.agile.HypothecsCalculator.core;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Hypothec {
    private final double creditSum;
    private final int countOfMonths;
    private final double monthlyPercent;
    private final CreditType creditType;
    private final double monthlyFee;

    public final double computeHighestMonthlyPayment() {

        return computeMonthlyPayment(1);
    }

    public final double computeLowestMonthlyPayment() {

        return computeMonthlyPayment(countOfMonths);
    }

    private double computeMonthlyPayment(final int numberOfMonth) {

        double monthlyPayment = creditSum * computePaymentCoefficient(numberOfMonth) +
                monthlyFee;

        return roundMoneySum(monthlyPayment);
    }


    private double computeAnnuityCoefficient() {
        if (monthlyPercent == 0.0) {
            return 1.0 / countOfMonths;
        }

        return monthlyPercent * (1.0 + 1.0 / (Math.pow(1 + monthlyPercent, countOfMonths) - 1));
    }

    private double computeDifferentiatedCoefficient(final int numberOfMonth) {
        return 1.0 / countOfMonths + monthlyPercent * (1.0 - (numberOfMonth - 1.0) / countOfMonths);
    }

    private double computePaymentCoefficient(final int numberOfMonth) {
        double paymentCoefficient = 0.0;

        switch (creditType) {
            case ANNUITY:
                paymentCoefficient = computeAnnuityCoefficient();
                break;
            case DIFFERENTIATED:
                paymentCoefficient = computeDifferentiatedCoefficient(numberOfMonth);
                break;
            default:
                break;
        }

        return paymentCoefficient;
    }

    private double roundMoneySum(final double sum) {
        return new BigDecimal(sum).setScale(0, RoundingMode.HALF_UP).doubleValue();
    }

    public static class Builder {

        private final double houseCost;
        private final int creditPeriod;

        private double downPayment = 0.0;
        private PeriodType periodType = PeriodType.MONTH;
        private double interestRate = 0.0;
        private InterestRateType interestRateType = InterestRateType.MONTHLY;
        private CreditType creditType = CreditType.ANNUITY;
        private double monthlyFee = 0.0;


        public Builder(final double houseCost, final int creditPeriod) {

            if (houseCost < 0) {
                throw new IllegalArgumentException("Negative house cost");
            }
            if (creditPeriod <= 0) {
                throw new IllegalArgumentException("Not positive credit period");
            }

            this.houseCost = houseCost;
            this.creditPeriod = creditPeriod;
        }

        public Hypothec build() {
            return new Hypothec(this);
        }

        public Builder setDownPayment(double downPayment) {
            if (downPayment < 0) {
                throw new IllegalArgumentException("Negative down payment");
            }
            if (downPayment > houseCost) {
                throw new IllegalArgumentException("Down payment is more then house cost");
            }
            this.downPayment = downPayment;
            return this;
        }

        public Builder setPeriodType(PeriodType periodType) {
            this.periodType = periodType;
            return this;
        }

        public Builder setInterestRate(double interestRate) {
            if (interestRate < 0) {
                throw new IllegalArgumentException("Negative interest rate");
            }
            this.interestRate = interestRate;
            return this;
        }

        public Builder setInterestRateType(InterestRateType interestRateType) {
            this.interestRateType = interestRateType;
            return this;
        }

        public Builder setCreditType(CreditType creditType) {
            this.creditType = creditType;
            return this;
        }

        public Builder setMonthlyFee(double monthlyFee) {
            if (monthlyFee < 0) {
                throw new IllegalArgumentException("Negative monthly fee");
            }
            this.monthlyFee = monthlyFee;
            return this;
        }
    }
    private Hypothec(Builder builder) {
        this.creditSum = builder.houseCost - builder.downPayment;

        switch (builder.periodType) {
            case MONTH:
                this.countOfMonths = builder.creditPeriod;
                break;
            case YEAR:
                this.countOfMonths = builder.creditPeriod * MONTHS_COUNT_IN_YEAR;
                break;
            default:
                this.countOfMonths = 0;
        }

        switch (builder.interestRateType) {
            case MONTHLY:
                this.monthlyPercent = builder.interestRate / MAX_NUMBER_OF_PERCENTS;
                break;
            case YEARLY:
                this.monthlyPercent = builder.interestRate
                        / (MONTHS_COUNT_IN_YEAR * MAX_NUMBER_OF_PERCENTS);
                break;
            default:
                this.monthlyPercent = 0.0;
        }

        this.creditType = builder.creditType;
        this.monthlyFee = builder.monthlyFee;
    }

    public static enum PeriodType {
        MONTH,
        YEAR
    }

    public static enum InterestRateType {
        MONTHLY,
        YEARLY
    }

    public static enum CreditType {
        DIFFERENTIATED,
        ANNUITY
    }

    private static final double MAX_NUMBER_OF_PERCENTS = 100.0;
    private static final int MONTHS_COUNT_IN_YEAR = 12;
}
