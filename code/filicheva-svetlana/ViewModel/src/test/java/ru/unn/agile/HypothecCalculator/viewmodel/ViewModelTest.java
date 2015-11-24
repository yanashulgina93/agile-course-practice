package ru.unn.agile.HypothecCalculator.viewmodel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class ViewModelTest {
    private ViewModel viewModel;

    private final double delta = 0.01;

    @Before
    public void setUp() {
        viewModel = new ViewModel();
    }

    @After
    public void tearDown() {
        viewModel = null;
    }

    @Test
    public void canSetDefaultValues() {
        assertEquals(ViewModel.Status.READY, viewModel.getStatus());
        assertEquals(true, viewModel.isButtonEnabled());

        assertEquals("2500000", viewModel.getHouseCost());
        assertEquals("0", viewModel.getDownPayment());
        assertEquals("15", viewModel.getCountOfPeriods());
        assertEquals("16.5", viewModel.getInterestRate());
        assertEquals("10", viewModel.getFlatFee());
        assertEquals("2", viewModel.getMonthlyFee());

        assertEquals(ViewModel.CurrencyType.RUBLE, viewModel.getCurrencyType());
        assertEquals(ViewModel.PeriodType.YEAR, viewModel.getPeriodType());
        assertEquals(ViewModel.InterestRateType.YEARLY, viewModel.getInterestRateType());
        assertEquals(ViewModel.FlatFeeType.CONSTANT_SUM, viewModel.getFlatFeeType());
        assertEquals(ViewModel.MonthlyFeeType.CONSTANT_SUM, viewModel.getMonthlyFeeType());
        assertEquals(ViewModel.CreditType.ANNUITY, viewModel.getCreditType());

        assertEquals("1", viewModel.getStartMonth());
        assertEquals("1992", viewModel.getStartYear());
    }



}
