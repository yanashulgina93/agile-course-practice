package ru.unn.agile.HypothecsCalculator.core;

import org.junit.Test;
import static org.junit.Assert.*;

import javax.swing.*;
import java.util.Calendar;
import java.util.GregorianCalendar;


public class HypothecTableTest {

    JTable rightTableForAnnuity = new JTable(new Object[][]{
                    {1},
                    {2},
                    {3},
                    {4}},
            new String[]{"№ платежа"});


    @Test
    public void canCreateTable() {
        Hypothec credit = new Hypothec.Builder(4000.0, 4)
                .setInterestRate(2.0)
                .setInterestRateType(Hypothec.InterestRateType.MONTHLY)
                .setMonthlyFee(1.0)
                .setMonthlyFeeType(Hypothec.MonthlyFeeType.CREDIT_BALANCE_PERCENT)
                .setStartDate(new GregorianCalendar(2015, Calendar.NOVEMBER, 4))
                .setCurrency(Hypothec.CurrencyType.RUBLE)
                .build();

        JTable graphic = credit.getGraphicOfPayments();

        for (int i = 0; i < rightTableForAnnuity.getRowCount(); i++) {
            for (int j = 0; j < rightTableForAnnuity.getColumnCount(); j++) {
                assertEquals(rightTableForAnnuity.getValueAt(i,j), graphic.getValueAt(i,j));
            }
        }


    }
}