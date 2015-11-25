package ru.unn.agile.HypothecCalculator.viewmodel;

import ru.unn.agile.HypothecsCalculator.model;

public class ViewModel {

    private String status;
    private boolean isButtonEnabled;

    private String houseCost;
    private String downPayment;
    private String countOfPeriods;
    private String interestRate;
    private String flatFee;
    private String monthlyFee;

    private CurrencyType currencyType;
    private PeriodType periodType;
    private InterestRateType interestRateType;
    private FlatFeeType flatFeeType;
    private MonthlyFeeType monthlyFeeType;
    private CreditType creditType;

    private String startMonth;
    private String startYear;

    public ViewModel() {
        status = Status.READY;
        isButtonEnabled = true;

        houseCost = "";
        downPayment = "";
        countOfPeriods = "";
        interestRate = "";
        flatFee = "";
        monthlyFee = "";

        currencyType = CurrencyType.DOLLARS;
        periodType = PeriodType.MONTH;
        interestRateType = InterestRateType.MONTHLY;
        flatFeeType = FlatFeeType.PERCENT;
        monthlyFeeType = MonthlyFeeType.CREDIT_SUM_PERCENT;
        creditType = CreditType.DIFFERENTIATED;

        startMonth = "";
        startYear = "";

    }

    public void loadExample() {
        status = Status.READY;
        isButtonEnabled = true;

        houseCost = "2500000";
        downPayment = "0";
        countOfPeriods = "15";
        interestRate = "16.5";
        flatFee = "10";
        monthlyFee = "2";

        currencyType = CurrencyType.RUBLE;
        periodType = PeriodType.YEAR;
        interestRateType = InterestRateType.YEARLY;
        flatFeeType = FlatFeeType.CONSTANT_SUM;
        monthlyFeeType = MonthlyFeeType.CONSTANT_SUM;
        creditType = CreditType.ANNUITY;

        startMonth = "1";
        startYear = "1992";
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(final String status) {
        this.status = status;
    }

    public boolean isButtonEnabled() {
        return isButtonEnabled;
    }

    public String getHouseCost() {
        return houseCost;
    }

    public void setHouseCost(String houseCost) {
        this.houseCost = houseCost;
    }

    public String getDownPayment() {
        return downPayment;
    }

    public void setDownPayment(String downPayment) {
        this.downPayment = downPayment;
    }

    public String getCountOfPeriods() {
        return countOfPeriods;
    }

    public void setCountOfPeriods(String countOfPeriods) {
        this.countOfPeriods = countOfPeriods;
    }

    public String getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(String interestRate) {
        this.interestRate = interestRate;
    }

    public String getFlatFee() {
        return flatFee;
    }

    public void setFlatFee(String flatFee) {
        this.flatFee = flatFee;
    }

    public String getMonthlyFee() {
        return monthlyFee;
    }

    public void setMonthlyFee(String monthlyFee) {
        this.monthlyFee = monthlyFee;
    }

    public CurrencyType getCurrencyType() {
        return currencyType;
    }

    public void setCurrencyType(CurrencyType currencyType) {
        this.currencyType = currencyType;
    }

    public PeriodType getPeriodType() {
        return periodType;
    }

    public void setPeriodType(PeriodType periodType) {
        this.periodType = periodType;
    }

    public InterestRateType getInterestRateType() {
        return interestRateType;
    }

    public void setInterestRateType(InterestRateType interestRateType) {
        this.interestRateType = interestRateType;
    }

    public FlatFeeType getFlatFeeType() {
        return flatFeeType;
    }

    public void setFlatFeeType(FlatFeeType flatFeeType) {
        this.flatFeeType = flatFeeType;
    }

    public MonthlyFeeType getMonthlyFeeType() {
        return monthlyFeeType;
    }

    public void setMonthlyFeeType(MonthlyFeeType monthlyFeeType) {
        this.monthlyFeeType = monthlyFeeType;
    }

    public CreditType getCreditType() {
        return creditType;
    }

    public void setCreditType(CreditType creditType) {
        this.creditType = creditType;
    }

    public String getStartMonth() {
        return startMonth;
    }

    public void setStartMonth(String startMonth) {
        this.startMonth = startMonth;
    }

    public String getStartYear() {
        return startYear;
    }

    public void setStartYear(String startYear) {
        this.startYear = startYear;
    }

    public void parseInput() {
        status = Status.READY;

        if (houseCost == "") status = Status.WAITING;
        else try {
            Double.parseDouble(houseCost);

        } catch (Hypothec e) {
            status = Status.BAD_FORMAT;
            isButtonEnabled = false;
            return;
        }


    }


    public enum CurrencyType {
        DOLLARS("$"),
        EURO("€"),
        RUBLE("руб.");

        private final String name;

        CurrencyType(final String name) {
            this.name = name;
        }

        public String toString() {
            return name;
        }
    }

    public enum PeriodType {
        MONTH("месяцев"),
        YEAR("лет");

        private final String name;

        PeriodType(final String name) {
            this.name = name;
        }

        public String toString() {
            return name;
        }
    }

    public enum InterestRateType {
        MONTHLY("% ежемесячно"),
        YEARLY("% ежегодно");

        private final String name;

        InterestRateType(final String name) {
            this.name = name;
        }

        public String toString() {
            return name;
        }
    }

    public enum FlatFeeType {
        PERCENT("% от суммы кредита"),
        CONSTANT_SUM("фиксированная сумма");

        private final String name;

        FlatFeeType(final String name) {
            this.name = name;
        }

        public String toString() {
            return name;
        }
    }

    public enum MonthlyFeeType {
        CREDIT_SUM_PERCENT("% от суммы кредита"),
        CREDIT_BALANCE_PERCENT("% от остатка долга"),
        CONSTANT_SUM("фиксированная сумма");

        private final String name;

        MonthlyFeeType(final String name) {
            this.name = name;
        }

        public String toString() {
            return name;
        }
    }

    public enum CreditType {
        DIFFERENTIATED("дифференцированный"),
        ANNUITY("аннуитетный");

        private final String name;

        CreditType(final String name) {
            this.name = name;
        }

        public String toString() {
            return name;
        }
    }

    public final class Status {
        public static final String WAITING = "Введите параметры кредита";
        public static final String READY = "Нажмите кнопку \"Рассчитать\"";
        public static final String BAD_FORMAT = "Bad format";
        public static final String SUCCESS = "Success";

        private Status() { }
    }
}
