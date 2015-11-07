package ru.unn.agile.HypothecsCalculator.core;

import java.util.GregorianCalendar;
import java.util.Calendar;

public class Hypothec {

    private final double creditSum;
    private final double monthlyPercent;
    private final double monthlyFee;
    private final double flatFee;

    private final int countOfMonths;

    private final MonthlyFeeType monthlyFeeType;
    private final CreditType creditType;
    private final CurrencyType currencyType;
    private final GregorianCalendar startDate;

    public static final double MAX_NUMBER_OF_PERCENTS = 100.0;
    public static final int MONTHS_COUNT_IN_YEAR = 12;

    public Hypothec(final Builder builder) {
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
                break;
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
                break;
        }

        switch (builder.flatFeeType) {
            case PERCENT:
                this.flatFee = this.creditSum * builder.flatFee / MAX_NUMBER_OF_PERCENTS;
                break;
            case CONSTANT_SUM:
                this.flatFee = builder.flatFee;
                break;
            default:
                this.flatFee = 0.0;
                break;
        }

        this.creditType = builder.creditType;
        this.monthlyFee = builder.monthlyFee;
        this.monthlyFeeType = builder.monthlyFeeType;
        this.currencyType = builder.currencyType;
        this.startDate = builder.startDate;

    }

    public double getCreditSum() {
        return creditSum;
    }

    public double getMonthlyPercent() {
        return monthlyPercent;
    }

    public double getMonthlyFee() {
        return monthlyFee;
    }

    public double getFlatFee() {
        return flatFee;
    }

    public int getCountOfMonths() {
        return countOfMonths;
    }

    public MonthlyFeeType getMonthlyFeeType() {
        return monthlyFeeType;
    }

    public CreditType getCreditType() {
        return creditType;
    }

    public CurrencyType getCurrencyType() {
        return currencyType;
    }

    public GregorianCalendar getStartDate() {
        return startDate;
    }

    public static class Builder {

        private final double houseCost;
        private final int creditPeriod;

        private double downPayment = 0.0;
        private double interestRate = 0.0;
        private double monthlyFee = 0.0;
        private double flatFee = 0.0;

        private PeriodType periodType = PeriodType.MONTH;
        private InterestRateType interestRateType = InterestRateType.MONTHLY;
        private CreditType creditType = CreditType.ANNUITY;
        private MonthlyFeeType monthlyFeeType = MonthlyFeeType.CONSTANT_SUM;
        private FlatFeeType flatFeeType = FlatFeeType.CONSTANT_SUM;
        private CurrencyType currencyType = CurrencyType.RUBLE;

        private GregorianCalendar startDate
                = new GregorianCalendar(EARLIEST_VALID_YEAR, Calendar.JANUARY, 1);

        private static final int EARLIEST_VALID_YEAR = 1991;
        private static final int LATEST_VALID_YEAR = 2100;

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

        public Builder setDownPayment(final double downPayment) {
            if (downPayment < 0) {
                throw new IllegalArgumentException("Negative down payment");
            }
            if (downPayment > houseCost) {
                throw new IllegalArgumentException("Down payment is more then house cost");
            }
            this.downPayment = downPayment;
            return this;
        }

        public Builder setPeriodType(final PeriodType periodType) {
            this.periodType = periodType;
            return this;
        }

        public Builder setInterestRate(final double interestRate) {
            if (interestRate < 0) {
                throw new IllegalArgumentException("Negative interest rate");
            }
            this.interestRate = interestRate;
            return this;
        }

        public Builder setInterestRateType(final InterestRateType interestRateType) {
            this.interestRateType = interestRateType;
            return this;
        }

        public Builder setCreditType(final CreditType creditType) {
            this.creditType = creditType;
            return this;
        }

        public Builder setMonthlyFee(final double monthlyFee) {
            if (monthlyFee < 0) {
                throw new IllegalArgumentException("Negative monthly fee");
            }
            this.monthlyFee = monthlyFee;
            return this;
        }

        public Builder setMonthlyFeeType(final MonthlyFeeType monthlyFeeType) {
            this.monthlyFeeType = monthlyFeeType;
            return this;
        }

        public Builder setFlatFee(final double flatFee) {
            if (flatFee < 0) {
                throw new IllegalArgumentException("Negative flat fee");
            }
            this.flatFee = flatFee;
            return this;
        }

        public Builder setFlatFeeType(final FlatFeeType flatFeeType) {
            this.flatFeeType = flatFeeType;
            return this;
        }

        public Builder setCurrency(final CurrencyType currencyType) {
            this.currencyType = currencyType;
            return this;
        }

        public Builder setStartDate(final GregorianCalendar startDate) {
            if (startDate.get(Calendar.YEAR) < EARLIEST_VALID_YEAR) {
                throw new IllegalArgumentException("Too early start date");
            }
            if (startDate.get(Calendar.YEAR) > LATEST_VALID_YEAR) {
                throw new IllegalArgumentException("Too late start date");
            }
            this.startDate = startDate;
            return this;
        }
    }

    public enum PeriodType {
        MONTH,
        YEAR
    }

    public enum InterestRateType {
        MONTHLY,
        YEARLY
    }

    public enum CreditType {
        DIFFERENTIATED,
        ANNUITY
    }

    public enum MonthlyFeeType {
        CREDIT_BALANCE_PERCENT,
        CREDIT_SUM_PERCENT,
        CONSTANT_SUM
    }

    public enum FlatFeeType {
        PERCENT,
        CONSTANT_SUM
    }

    public enum CurrencyType {
        RUBLE,
        DOLLAR,
        EURO
    }

}

