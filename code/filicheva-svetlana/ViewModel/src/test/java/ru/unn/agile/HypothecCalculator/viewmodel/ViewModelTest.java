package ru.unn.agile.HypothecCalculator.viewmodel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

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

        assertEquals("", viewModel.getHouseCost());
        assertEquals("", viewModel.getDownPayment());
        assertEquals("", viewModel.getCountOfPeriods());
        assertEquals("", viewModel.getInterestRate());
        assertEquals("", viewModel.getFlatFee());
        assertEquals("", viewModel.getMonthlyFee());

        assertEquals(ViewModel.CurrencyType.DOLLARS, viewModel.getCurrencyType());
        assertEquals(ViewModel.PeriodType.MONTH, viewModel.getPeriodType());
        assertEquals(ViewModel.InterestRateType.MONTHLY, viewModel.getInterestRateType());
        assertEquals(ViewModel.FlatFeeType.PERCENT, viewModel.getFlatFeeType());
        assertEquals(ViewModel.MonthlyFeeType.CREDIT_SUM_PERCENT, viewModel.getMonthlyFeeType());
        assertEquals(ViewModel.CreditType.DIFFERENTIATED, viewModel.getCreditType());

        assertEquals("", viewModel.getStartMonth());
        assertEquals("", viewModel.getStartYear());
    }

    @Test
    public void canLoadExample() {
        viewModel.loadExample();

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

    @Test
    public void isStatusWaitingWhenHouseCostFieldIsEmpty() {
        viewModel.loadExample();
        viewModel.setHouseCost("");

        viewModel.parseInput();

        assertEquals(ViewModel.Status.WAITING, viewModel.getStatus());
    }

    @Test
    public void isStatusBadFormatWhenHouseCostHasBadFormat() {
        viewModel.loadExample();
        viewModel.setHouseCost("qw");

        viewModel.parseInput();

        assertEquals(ViewModel.Status.BAD_FORMAT, viewModel.getStatus());
    }

//    @Test
//    public void isStatusErrorWhenHouseCostIsWrong() {
//        viewModel.loadExample();
//        viewModel.setHouseCost("-100");
//
//        viewModel.parseInput();
//
//        assertEquals("Negative house cost", viewModel.getStatus());
//    }

}
