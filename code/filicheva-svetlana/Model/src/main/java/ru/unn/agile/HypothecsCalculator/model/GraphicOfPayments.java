package ru.unn.agile.HypothecsCalculator.model;

import javax.swing.table.DefaultTableModel;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

public class GraphicOfPayments {
    private final Hypothec hypothec;
    private final CreditCalculator creditCalculator;

    private static final String[] COLUMN_NAMES = {
            "№ платежа",
            "Дата платежа",
            "Сумма платежа",
            "Платеж по основному долгу",
            "Платеж по процентам",
            "Ежемесячная комиссия",
            "Остаток основной задолженности"
    };

    public GraphicOfPayments(final Hypothec hypothec) {
        this.hypothec = hypothec;
        creditCalculator = new CreditCalculator(hypothec);
    }

    public DefaultTableModel getTableModel() {

        Object[][] paymentsData = new Object[hypothec.getCountOfMonths()][COLUMN_NAMES.length];
        for (int i = 1; i <= hypothec.getCountOfMonths(); i++) {
            paymentsData[i - 1] = getTableRow(i);
        }

        return new DefaultTableModel(paymentsData, COLUMN_NAMES);
    }

    private Object[] getTableRow(final int rowNumber) {

        GregorianCalendar date = (GregorianCalendar) hypothec.getStartDate().clone();
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM.yyyy", Locale.ENGLISH);
        date.add(Calendar.MONTH, rowNumber - 1);

        double payment = creditCalculator.computeMonthlyPayment(rowNumber);
        double mainDebtPayment = creditCalculator.computeMainDebtPayment(rowNumber);
        double thisMonthFee = creditCalculator.computeMonthlyFee(rowNumber);
        double percentPayment =
                Rounder.roundMoneySum(payment - mainDebtPayment - thisMonthFee);
        double creditBalance =
                Rounder.roundMoneySum(creditCalculator.computeCreditBalance(rowNumber));

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
}
