package ru.unn.agile.HypothecsCalculator.core;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.GregorianCalendar;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import java.util.Locale;
import javax.swing.table.DefaultTableModel;

public final class CreditCalculator {

    private final Hypothec hypothec;

    private static final String[] COLUMN_NAMES = {
            "№ платежа",
            "Дата платежа",
            "Сумма платежа",
            "Платеж по основному долгу",
            "Платеж по процентам",
            "Ежемесячная комиссия",
            "Остаток основной задолженности"
    };
    private static final int COLUMN_COUNT = 7;

    public CreditCalculator(final Hypothec hypothec) {
        this.hypothec = hypothec;
    }

    public double computeHighestMonthlyPayment() {

        return computeMonthlyPayment(1);
    }

    public double computeLowestMonthlyPayment() {

        return computeMonthlyPayment(hypothec.getCountOfMonths());
    }

    public double computeOverpayment() {
        double creditCoefficient = 0.0;

        for (int i = 1; i <= hypothec.getCountOfMonths(); i++) {
            creditCoefficient += computePaymentCoefficient(i);
        }

        double overpayment = (creditCoefficient - 1.0) * hypothec.getCreditSum();

        return roundMoneySum(overpayment);
    }

    public double computeOverpaymentWithFees() {
        double allPayments = 0.0;

        for (int i = 1; i <= hypothec.getCountOfMonths(); i++) {
            allPayments += computeMonthlyPayment(i);
        }

        double overpaymentWithFees = allPayments - hypothec.getCreditSum() + hypothec.getFlatFee();

        return roundMoneySum(overpaymentWithFees);
    }

    public DefaultTableModel getGraphicOfPayments() {

        Object[][] paymentsData = new Object[hypothec.getCountOfMonths()][COLUMN_COUNT];
        for (int i = 1; i <= hypothec.getCountOfMonths(); i++) {
            paymentsData[i - 1] = getTableRow(i);
        }

        return new DefaultTableModel(paymentsData, COLUMN_NAMES);
    }

    private double computeMonthlyPayment(final int numberOfMonth) {

        double monthlyPayment = hypothec.getCreditSum() * computePaymentCoefficient(numberOfMonth)
                + computeMonthlyFee(numberOfMonth);

        return roundMoneySum(monthlyPayment);
    }

    private double computePaymentCoefficient(final int numberOfMonth) {
        double paymentCoefficient = 0.0;

        switch (hypothec.getCreditType()) {
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

    private double computeAnnuityCoefficient() {
        double monthlyPercent = hypothec.getMonthlyPercent();

        if (monthlyPercent == 0.0) {
            return 1.0 / hypothec.getCountOfMonths();
        }

        return monthlyPercent
                * (1.0 + 1.0 / (Math.pow(1 + monthlyPercent, hypothec.getCountOfMonths()) - 1));
    }

    private double computeDifferentiatedCoefficient(final int numberOfMonth) {
        int monthsCount = hypothec.getCountOfMonths();

        return 1.0 / monthsCount
                + hypothec.getMonthlyPercent() * (1.0 - (numberOfMonth - 1.0) / monthsCount);
    }

    private double computeMonthlyFee(final int numberOfMonth) {

        double fee = 0.0;

        switch (hypothec.getMonthlyFeeType()) {
            case CONSTANT_SUM:
                fee = hypothec.getMonthlyFee();
                break;
            case CREDIT_BALANCE_PERCENT:
                fee = computeCreditBalance(numberOfMonth) * hypothec.getMonthlyFee()
                        / hypothec.MAX_NUMBER_OF_PERCENTS;
                break;
            case CREDIT_SUM_PERCENT:
                fee = hypothec.getCreditSum() * hypothec.getMonthlyFee()
                        / hypothec.MAX_NUMBER_OF_PERCENTS;
                break;
            default:
                break;
        }

        return roundMoneySum(fee);
    }

    private double computeCreditBalance(final int numberOfMonth) {
        double balance = 0.0;

        switch (hypothec.getCreditType()) {
            case DIFFERENTIATED:
                balance = hypothec.getCreditSum() * (1.0 - (double) numberOfMonth
                        / hypothec.getCountOfMonths());
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
        double balance = hypothec.getCreditSum();
        double monthlyPayment = hypothec.getCreditSum() * computeAnnuityCoefficient();

        for (int i = 1; i <= numberOfMonth; i++) {
            balance -= monthlyPayment - balance * hypothec.getMonthlyPercent();
        }

        return balance;
    }

    private double roundMoneySum(final double sum) {

        return new BigDecimal("" + sum).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }

    private Object[] getTableRow(final int rowNumber) {

        GregorianCalendar date = (GregorianCalendar) hypothec.getStartDate().clone();
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM.yyyy", Locale.ENGLISH);
        date.add(Calendar.MONTH, rowNumber - 1);

        double payment = computeMonthlyPayment(rowNumber);
        double mainDebtPayment = computeMainDebtPayment(rowNumber);
        double thisMonthFee = computeMonthlyFee(rowNumber);
        double percentPayment = roundMoneySum(payment - mainDebtPayment - thisMonthFee);
        double creditBalance = roundMoneySum(computeCreditBalance(rowNumber));

        Object[] row = new Object[] {
                rowNumber,
                dateFormat.format(date.getTime()),
                payment,
                mainDebtPayment,
                percentPayment,
                thisMonthFee,
                creditBalance
        };

        return row;
    }

    private double computeMainDebtPayment(final int numberOfMonth) {
        double sum = 0.0;

        switch (hypothec.getCreditType()) {
            case DIFFERENTIATED:
                sum = hypothec.getCreditSum() / hypothec.getCountOfMonths();
                break;
            case ANNUITY:
                sum = hypothec.getCreditSum() * computeAnnuityCoefficient()
                        - annuityCreditBalance(numberOfMonth - 1) * hypothec.getMonthlyPercent();
                break;
            default:
                break;
        }

        return roundMoneySum(sum);
    }


}
