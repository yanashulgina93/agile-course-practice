package ru.unn.agile.HypothecCalculator.viewmodel;

import ru.unn.agile.HypothecsCalculator.model.*;

import java.util.GregorianCalendar;
import java.util.Calendar;

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

    private Hypothec.Builder hypothecForParsing;

    public ViewModel() {
        status = Status.WAITING;
        isButtonEnabled = false;

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

    public void setHouseCost(final String houseCost) {
        this.houseCost = houseCost;
    }

    public String getDownPayment() {
        return downPayment;
    }

    public void setDownPayment(final String downPayment) {
        this.downPayment = downPayment;
    }

    public String getCountOfPeriods() {
        return countOfPeriods;
    }

    public void setCountOfPeriods(final String countOfPeriods) {
        this.countOfPeriods = countOfPeriods;
    }

    public String getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(final String interestRate) {
        this.interestRate = interestRate;
    }

    public String getFlatFee() {
        return flatFee;
    }

    public void setFlatFee(final String flatFee) {
        this.flatFee = flatFee;
    }

    public String getMonthlyFee() {
        return monthlyFee;
    }

    public void setMonthlyFee(final String monthlyFee) {
        this.monthlyFee = monthlyFee;
    }

    public CurrencyType getCurrencyType() {
        return currencyType;
    }

    public void setCurrencyType(final CurrencyType currencyType) {
        this.currencyType = currencyType;
    }

    public PeriodType getPeriodType() {
        return periodType;
    }

    public void setPeriodType(final PeriodType periodType) {
        this.periodType = periodType;
    }

    public InterestRateType getInterestRateType() {
        return interestRateType;
    }

    public void setInterestRateType(final InterestRateType interestRateType) {
        this.interestRateType = interestRateType;
    }

    public FlatFeeType getFlatFeeType() {
        return flatFeeType;
    }

    public void setFlatFeeType(final FlatFeeType flatFeeType) {
        this.flatFeeType = flatFeeType;
    }

    public MonthlyFeeType getMonthlyFeeType() {
        return monthlyFeeType;
    }

    public void setMonthlyFeeType(final MonthlyFeeType monthlyFeeType) {
        this.monthlyFeeType = monthlyFeeType;
    }

    public CreditType getCreditType() {
        return creditType;
    }

    public void setCreditType(final CreditType creditType) {
        this.creditType = creditType;
    }

    public String getStartMonth() {
        return startMonth;
    }

    public void setStartMonth(final String startMonth) {
        this.startMonth = startMonth;
    }

    public String getStartYear() {
        return startYear;
    }

    public void setStartYear(final String startYear) {
        this.startYear = startYear;
    }

    public void parseInput() {
        status = Status.READY;
        isButtonEnabled = false;

        hypothecForParsing = new Hypothec.Builder();

        if (!houseCostAndCountOfPeriodsIsOK()) {
            return;
        }
        if (!downPaymentIsOK()) {
            return;
        }
        if (!interestRateIsOK()) {
            return;
        }
        if (!flatFeeIsOK()) {
            return;
        }
        if (!monthlyFeeIsOK()) {
            return;
        }
        if (!startDateIsOK()){
            return;
        }

        if (status == Status.READY) {
            isButtonEnabled = true;
        }


    }

    private boolean houseCostAndCountOfPeriodsIsOK() {
        double houseCostDouble;
        int countOfPeriodsDouble;

        if (houseCost.isEmpty()) {
            status = Status.WAITING;
            houseCostDouble = 0.0;
        } else {
            try {
                houseCostDouble = Double.parseDouble(houseCost);
            }  catch (Exception e) {
                status = Status.BAD_FORMAT;
                return false;
            }
        }

        if (countOfPeriods.isEmpty()) {
            status = Status.WAITING;
            countOfPeriodsDouble = 1;
        } else {
            try {
                countOfPeriodsDouble = Integer.parseInt(countOfPeriods);
            } catch (Exception e) {
                status = Status.BAD_FORMAT;
                return false;
            }
        }

        try {
            hypothecForParsing = new Hypothec.Builder(houseCostDouble, countOfPeriodsDouble);
        } catch (HypothecInputException e) {
            status = e.getMessage();
            return false;
        }
        return true;
    }

    private boolean downPaymentIsOK() {
        if (downPayment.isEmpty()) {
            status = Status.WAITING;
        } else {
            try {
                double downPaymentDouble = Double.parseDouble(downPayment);
                hypothecForParsing.downPayment(downPaymentDouble);
            } catch (HypothecInputException e) {
                status = e.getMessage();
                return false;
            } catch (Exception e) {
                status = Status.BAD_FORMAT;
                return false;
            }
        }
        return true;
    }

    private boolean interestRateIsOK() {
        if (interestRate.isEmpty()) {
            status = Status.WAITING;
        } else {
            try {
                double interestRateDouble = Double.parseDouble(interestRate);
                hypothecForParsing.interestRate(interestRateDouble);
            } catch (HypothecInputException e) {
                status = e.getMessage();
                return false;
            } catch (Exception e) {
                status = Status.BAD_FORMAT;
                return false;
            }
        }
        return true;
    }

    private boolean flatFeeIsOK() {
        if (flatFee.isEmpty()) {
            status = Status.WAITING;
        } else {
            try {
                double flatFeeDouble = Double.parseDouble(flatFee);
                hypothecForParsing.flatFee(flatFeeDouble);
            } catch (HypothecInputException e) {
                status = e.getMessage();
                return false;
            } catch (Exception e) {
                status = Status.BAD_FORMAT;
                return false;
            }
        }
        return true;
    }

    private boolean monthlyFeeIsOK() {
        if (monthlyFee.isEmpty()) {
            status = Status.WAITING;
        } else {
            try {
                double monthlyFeeDouble = Double.parseDouble(monthlyFee);
                hypothecForParsing.monthlyFee(monthlyFeeDouble);
            } catch (HypothecInputException e) {
                status = e.getMessage();
                return false;
            } catch (Exception e) {
                status = Status.BAD_FORMAT;
                return false;
            }
        }
        return true;
    }

    private boolean startDateIsOK() {
        int month;
        int year;

        if (startMonth.isEmpty() || startYear.isEmpty()) {
            status = Status.WAITING;
        }
        else {
            try {
                month = Integer.parseInt(startMonth);
                year = Integer.parseInt(startYear);
            } catch (Exception e) {
                status = Status.BAD_FORMAT;
                return false;
            }

            try {
                if (month < 0 || month > 12) {
                    throw new HypothecInputException(HypothecInputException.BAD_MONTH);
                }
            } catch (HypothecInputException e) {
                status = e.getMessage();
                return false;
            }

            try {
                GregorianCalendar date = new GregorianCalendar(year, month - 1, 1);
                hypothecForParsing.startDate(date);
            } catch (HypothecInputException e) {
                status = e.getMessage();
                return false;
            }

        }
        return true;
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
        public static final String BAD_FORMAT = "Введены даннные неверного формата ";
        public static final String SUCCESS = "Success";

        private Status() { }
    }
}
