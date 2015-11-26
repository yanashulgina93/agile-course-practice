package ru.unn.agile.HypothecCalculator.viewmodel;

import ru.unn.agile.HypothecsCalculator.model.*;

import javax.swing.table.DefaultTableModel;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class ViewModel {

    private String status;
    private boolean isButtonEnabled;

    private String houseCost;
    private String downPayment;
    private String countOfPeriods;
    private String interestRate;
    private String flatFee;
    private String monthlyFee;

    private Hypothec.CurrencyType currencyType;
    private Hypothec.PeriodType periodType;
    private Hypothec.InterestRateType interestRateType;
    private Hypothec.FlatFeeType flatFeeType;
    private Hypothec.MonthlyFeeType monthlyFeeType;
    private Hypothec.CreditType creditType;

    private String startMonth;
    private String startYear;

    private String monthlyPayment;
    private String overpaymentWithFees;
    private String overpayment;
    private DefaultTableModel graphicOfPayments;

    private Hypothec.Builder hypothecForParsing;
    private CreditCalculator creditCalculator;

    public ViewModel() {
        status = Status.WAITING;
        isButtonEnabled = false;

        houseCost = "";
        downPayment = "";
        countOfPeriods = "";
        interestRate = "";
        flatFee = "";
        monthlyFee = "";

        currencyType = Hypothec.CurrencyType.DOLLAR;
        periodType = Hypothec.PeriodType.MONTH;
        interestRateType = Hypothec.InterestRateType.MONTHLY;
        flatFeeType = Hypothec.FlatFeeType.PERCENT;
        monthlyFeeType = Hypothec.MonthlyFeeType.CREDIT_SUM_PERCENT;
        creditType = Hypothec.CreditType.DIFFERENTIATED;

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

    public Hypothec.CurrencyType getCurrencyType() {
        return currencyType;
    }

    public void setCurrencyType(final Hypothec.CurrencyType currencyType) {
        this.currencyType = currencyType;
    }

    public Hypothec.PeriodType getPeriodType() {
        return periodType;
    }

    public void setPeriodType(final Hypothec.PeriodType periodType) {
        this.periodType = periodType;
    }

    public Hypothec.InterestRateType getInterestRateType() {
        return interestRateType;
    }

    public void setInterestRateType(final Hypothec.InterestRateType interestRateType) {
        this.interestRateType = interestRateType;
    }

    public Hypothec.FlatFeeType getFlatFeeType() {
        return flatFeeType;
    }

    public void setFlatFeeType(final Hypothec.FlatFeeType flatFeeType) {
        this.flatFeeType = flatFeeType;
    }

    public Hypothec.MonthlyFeeType getMonthlyFeeType() {
        return monthlyFeeType;
    }

    public void setMonthlyFeeType(final Hypothec.MonthlyFeeType monthlyFeeType) {
        this.monthlyFeeType = monthlyFeeType;
    }

    public Hypothec.CreditType getCreditType() {
        return creditType;
    }

    public void setCreditType(final Hypothec.CreditType creditType) {
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

    public boolean checkInput() {
        status = Status.READY;
        isButtonEnabled = false;

        hypothecForParsing = new Hypothec.Builder();

        if (!houseCostAndCountOfPeriodsIsOK()) {
            return false;
        }
        if (!downPaymentIsOK()) {
            return false;
        }
        if (!interestRateIsOK()) {
            return false;
        }
        if (!flatFeeIsOK()) {
            return false;
        }
        if (!monthlyFeeIsOK()) {
            return false;
        }
        if (!startDateIsOK()) {
            return false;
        }

        if (status == Status.READY) {
            isButtonEnabled = true;
            return true;
        }
        return false;
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
        } else {
            try {
                month = Integer.parseInt(startMonth) - 1;
                year = Integer.parseInt(startYear);
            } catch (Exception e) {
                status = Status.BAD_FORMAT;
                return false;
            }

            try {
                if (month < Calendar.JANUARY || month > Calendar.DECEMBER) {
                    throw new HypothecInputException(HypothecInputException.BAD_MONTH);
                }
            } catch (HypothecInputException e) {
                status = e.getMessage();
                return false;
            }

            try {
                GregorianCalendar date = new GregorianCalendar(year, month, 1);
                hypothecForParsing.startDate(date);
            } catch (HypothecInputException e) {
                status = e.getMessage();
                return false;
            }
        }
        return true;
    }

    public void compute() {

        if (checkInput()) {
            creditCalculator = new CreditCalculator(createHypothec());

            computeMonthlyPayment();
        }
    }

    private Hypothec createHypothec() {
        Hypothec hypothec;
        try {
            hypothec = new Hypothec
                    .Builder(Double.parseDouble(houseCost), Integer.parseInt(countOfPeriods))
                    .periodType(periodType)
                    .downPayment(Double.parseDouble(downPayment))
                    .interestRate(Double.parseDouble(interestRate))
                    .interestRateType(interestRateType)
                    .monthlyFee(Double.parseDouble(monthlyFee))
                    .monthlyFeeType(monthlyFeeType)
                    .creditType(creditType)
                    .build();
        } catch (HypothecInputException e) {
            hypothec = new Hypothec.Builder().build();
        }
        return hypothec;
    }

    private void computeMonthlyPayment() {
        double highestMonthlyPayment =
                creditCalculator.computeHighestMonthlyPayment();
        double lowestMonthlyPayment =
                creditCalculator.computeLowestMonthlyPayment();

        if (highestMonthlyPayment - lowestMonthlyPayment < 0.001) {
            monthlyPayment = Double.toString(highestMonthlyPayment);
        } else {
            monthlyPayment = Double.toString(highestMonthlyPayment)
                    + " ... "
                    + Double.toString(lowestMonthlyPayment);
        }

    }

    public String getMonthlyPayment() {
        return monthlyPayment;
    }


    public final class Status {
        public static final String WAITING = "Введите параметры кредита";
        public static final String READY = "Нажмите кнопку \"Рассчитать\"";
        public static final String BAD_FORMAT = "Введены даннные неверного формата ";
        public static final String SUCCESS = "Success";

        private Status() { }
    }
}
