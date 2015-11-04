package ru.unn.agile.HypothecsCalculator.core;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.GregorianCalendar;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import javax.swing.JTable;

public class Hypothec {
    private final double creditSum;
    private final int countOfMonths;
    private final double monthlyPercent;
    private final CreditType creditType;
    private final double monthlyFee;
    private final MonthlyFeeType monthlyFeeType;
    private final double flatFee;
    private final CurrencyType currencyType;
    private final GregorianCalendar startDate;


    public final double computeHighestMonthlyPayment() {

        return computeMonthlyPayment(1);
    }

    public final double computeLowestMonthlyPayment() {

        return computeMonthlyPayment(countOfMonths);
    }

    private double computeMonthlyPayment(final int numberOfMonth) {

        double monthlyPayment = creditSum * computePaymentCoefficient(numberOfMonth)
                + computeMonthlyFee(numberOfMonth);

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

    private double computeMonthlyFee(final int numberOfMonth) {

        double fee = 0.0;

        switch (monthlyFeeType) {
            case CONSTANT_SUM:
                fee = monthlyFee;
                break;
            case CREDIT_BALANCE_PERCENT:
                fee = computeCreditBalance(numberOfMonth) * monthlyFee / MAX_NUMBER_OF_PERCENTS;
                break;
            case CREDIT_SUM_PERCENT:
                fee = creditSum * monthlyFee / MAX_NUMBER_OF_PERCENTS;
                break;
            default:
                break;
        }

        return fee;
    }

    private double computeCreditBalance(final int numberOfMonth) {
        double balance = 0.0;

        switch (creditType) {
            case DIFFERENTIATED:
                balance = creditSum * (1.0 - (double) numberOfMonth / countOfMonths);
                break;
            case ANNUITY:
                balance = annuityCreditBalance(numberOfMonth);
                break;
            default:
                break;
        }

        return balance;
    }

    private double annuityCreditBalance(final int numberOfMonth) {
        double balance = creditSum;
        double monthlyPayment = creditSum * computeAnnuityCoefficient();

        for (int i = 1; i<=numberOfMonth; i++) {
            balance -= monthlyPayment - balance * monthlyPercent;
        }

        return balance;
    }

    private double roundMoneySum(final double sum) {
        return new BigDecimal(sum).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }

    public double computeOverpayment() {
        double creditCoefficient = 0.0;

        for (int i = 1; i <= countOfMonths; i++) {
            creditCoefficient += computePaymentCoefficient(i);
        }

        double overpayment = (creditCoefficient - 1.0) * creditSum;

        return roundMoneySum(overpayment);
    }

    public double computeOverpaymentWithFees() {
        double allPayments = 0.0;

        for (int i = 1; i <= countOfMonths; i++) {
            allPayments += computeMonthlyPayment(i);
        }

        double overpaymentWithFees = allPayments - creditSum + flatFee;

        return roundMoneySum(overpaymentWithFees);
    }

    public JTable getGraphicOfPayments() {
        GregorianCalendar date = (GregorianCalendar) startDate.clone();
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM.yyyy");

        Object[][] paymentsData = new Object[countOfMonths][COLUMN_COUNT];
        for (int i = 0; i < countOfMonths; i++) {
            paymentsData[i][0] = i + 1;

            paymentsData[i][1] = dateFormat.format(date.getTime());
            date.add(Calendar.MONTH, 1);

            paymentsData[i][2] = computeMonthlyPayment(i + 1);

        }


        return new JTable(paymentsData, COLUMN_NAMES);
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
        private MonthlyFeeType monthlyFeeType = MonthlyFeeType.CONSTANT_SUM;
        private double flatFee = 0.0;
        private FlatFeeType flatFeeType = FlatFeeType.CONSTANT_SUM;
        private CurrencyType currencyType = CurrencyType.RUBLE;
        private GregorianCalendar startDate
                = new GregorianCalendar(EARLIEST_VALID_YEAR, Calendar.JANUARY, 1);


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
    private Hypothec(final Builder builder) {
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
        this.monthlyFeeType = builder.monthlyFeeType;

        switch (builder.flatFeeType) {
            case PERCENT:
                this.flatFee = this.creditSum * builder.flatFee / MAX_NUMBER_OF_PERCENTS;
                break;
            case CONSTANT_SUM:
                this.flatFee = builder.flatFee;
                break;
            default:
                this.flatFee = 0.0;
        }

        this.currencyType = builder.currencyType;
        this.startDate = builder.startDate;

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

    public static enum MonthlyFeeType {
        CREDIT_BALANCE_PERCENT,
        CREDIT_SUM_PERCENT,
        CONSTANT_SUM
    }

    public static enum FlatFeeType {
        PERCENT,
        CONSTANT_SUM
    }

    public static enum CurrencyType {
        RUBLE,
        DOLLAR,
        EURO
    }

    private static final String[] COLUMN_NAMES = {
            "№ платежа",
            "Дата платежа",
            "Сумма платежа"
    };
    private static final int COLUMN_COUNT = 3;

    private static final double MAX_NUMBER_OF_PERCENTS = 100.0;
    private static final int MONTHS_COUNT_IN_YEAR = 12;

    private static final int EARLIEST_VALID_YEAR = 1991;
    private static final int LATEST_VALID_YEAR = 2100;
}
