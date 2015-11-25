package ru.unn.agile.HypothecCalculator.viewmodel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.unn.agile.HypothecsCalculator.model.HypothecInputException;

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
        assertEquals(ViewModel.Status.WAITING, viewModel.getStatus());
        assertEquals(false, viewModel.isButtonEnabled());
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

    @Test
    public void isStatusErrorWhenHouseCostHasWrongValue() {
        viewModel.loadExample();
        viewModel.setHouseCost("-100");

        viewModel.parseInput();

        assertEquals(HypothecInputException.NEGATIVE_HOUSE_COST, viewModel.getStatus());
    }

    @Test
    public void isStatusReadyWhenHouseCostIsRight() {
        viewModel.loadExample();
        viewModel.setHouseCost("100");

        viewModel.parseInput();

        assertEquals(ViewModel.Status.READY, viewModel.getStatus());
    }

    @Test
    public void isButtonDisabledWhenHouseCostFieldIsEmpty() {
        viewModel.loadExample();
        viewModel.setHouseCost("");

        viewModel.parseInput();

        assertEquals(false, viewModel.isButtonEnabled());
    }

    @Test
    public void isButtonDisabledWhenHouseCostHasBadFormat() {
        viewModel.loadExample();
        viewModel.setHouseCost("qw");

        viewModel.parseInput();

        assertEquals(false, viewModel.isButtonEnabled());
    }

    @Test
    public void isButtonDisabledWhenHouseCostHasWrongValue() {
        viewModel.loadExample();
        viewModel.setHouseCost("-100");

        viewModel.parseInput();

        assertEquals(false, viewModel.isButtonEnabled());
    }

    @Test
    public void isStatusWaitingWhenCountOfPeriodsFieldIsEmpty() {
        viewModel.loadExample();
        viewModel.setCountOfPeriods("");

        viewModel.parseInput();

        assertEquals(ViewModel.Status.WAITING, viewModel.getStatus());
    }

    @Test
    public void isStatusBadFormatWhenCountOfPeriodsHasBadFormat() {
        viewModel.loadExample();
        viewModel.setCountOfPeriods("pgffff");

        viewModel.parseInput();

        assertEquals(ViewModel.Status.BAD_FORMAT, viewModel.getStatus());
    }

    @Test
    public void isStatusErrorWhenCountOfPeriodsHasWrongValue() {
        viewModel.loadExample();
        viewModel.setCountOfPeriods("0");

        viewModel.parseInput();

        assertEquals(HypothecInputException.NOT_POSITIVE_CREDIT_PERIOD, viewModel.getStatus());
    }

    @Test
    public void isStatusReadyWhenCountOfPeriodsIsRight() {
        viewModel.loadExample();
        viewModel.setCountOfPeriods("1");

        viewModel.parseInput();

        assertEquals(ViewModel.Status.READY, viewModel.getStatus());
    }

    @Test
    public void isButtonDisabledWhenCountOfPeriodsFieldIsEmpty() {
        viewModel.loadExample();
        viewModel.setCountOfPeriods("");

        viewModel.parseInput();

        assertEquals(false, viewModel.isButtonEnabled());
    }

    @Test
    public void isButtonDisabledWhenCountOfPeriodsHasBadFormat() {
        viewModel.loadExample();
        viewModel.setCountOfPeriods("fdddddw");

        viewModel.parseInput();

        assertEquals(false, viewModel.isButtonEnabled());
    }

    @Test
    public void isButtonDisabledCountOfPeriodsHasWrongValue() {
        viewModel.loadExample();
        viewModel.setCountOfPeriods("0");

        viewModel.parseInput();

        assertEquals(false, viewModel.isButtonEnabled());
    }

    @Test
    public void isStatusWaitingWhenDownPaymentFieldIsEmpty() {
        viewModel.loadExample();
        viewModel.setDownPayment("");

        viewModel.parseInput();

        assertEquals(ViewModel.Status.WAITING, viewModel.getStatus());
    }

    @Test
    public void isStatusBadFormatWhenDownPaymentHasBadFormat() {
        viewModel.loadExample();
        viewModel.setDownPayment("eee");

        viewModel.parseInput();

        assertEquals(ViewModel.Status.BAD_FORMAT, viewModel.getStatus());
    }

    @Test
    public void isStatusErrorWhenDownPaymentHasWrongValue() {
        viewModel.loadExample();
        viewModel.setDownPayment("-400");

        viewModel.parseInput();

        assertEquals(HypothecInputException.NEGATIVE_DOWN_PAYMENT, viewModel.getStatus());
    }

    @Test
    public void isStatusReadyWhenDownPaymentIsRight() {
        viewModel.loadExample();
        viewModel.setDownPayment("10");

        viewModel.parseInput();

        assertEquals(ViewModel.Status.READY, viewModel.getStatus());
    }

    @Test
    public void isStatusErrorWhenDownPaymentIsMoreThanHouseCost() {
        viewModel.loadExample();
        viewModel.setHouseCost("100");
        viewModel.setDownPayment("120");

        viewModel.parseInput();

        assertEquals(
                HypothecInputException.DOWN_PAYMENT_IS_MORE_THAN_HOUSE_COST,
                viewModel.getStatus());
    }

    @Test
    public void isButtonDisabledWhenDownPaymentFieldIsEmpty() {
        viewModel.loadExample();
        viewModel.setDownPayment("");

        viewModel.parseInput();

        assertEquals(false, viewModel.isButtonEnabled());
    }

    @Test
    public void isButtonDisabledWhenDownPaymentHasBadFormat() {
        viewModel.loadExample();
        viewModel.setDownPayment("eeee");

        viewModel.parseInput();

        assertEquals(false, viewModel.isButtonEnabled());
    }

    @Test
    public void isButtonDisabledWhenDownPaymentHasWrongValue() {
        viewModel.loadExample();
        viewModel.setDownPayment("-100");

        viewModel.parseInput();

        assertEquals(false, viewModel.isButtonEnabled());
    }

    @Test
    public void isButtonDisabledWhenDownPaymentIsMoreThanHouseCost() {
        viewModel.loadExample();
        viewModel.setHouseCost("100");
        viewModel.setDownPayment("120");

        viewModel.parseInput();

        assertEquals(false, viewModel.isButtonEnabled());
    }

    @Test
    public void isStatusWaitingWhenInterestRateFieldIsEmpty() {
        viewModel.loadExample();
        viewModel.setInterestRate("");

        viewModel.parseInput();

        assertEquals(ViewModel.Status.WAITING, viewModel.getStatus());
    }

    @Test
    public void isStatusBadFormatWhenInterestRateHasBadFormat() {
        viewModel.loadExample();
        viewModel.setInterestRate("qqffw");

        viewModel.parseInput();

        assertEquals(ViewModel.Status.BAD_FORMAT, viewModel.getStatus());
    }

    @Test
    public void isStatusErrorWhenInterestRateHasWrongValue() {
        viewModel.loadExample();
        viewModel.setInterestRate("-10");

        viewModel.parseInput();

        assertEquals(HypothecInputException.NEGATIVE_INTEREST_RATE, viewModel.getStatus());
    }

    @Test
    public void isStatusReadyWhenInterestRateIsRight() {
        viewModel.loadExample();
        viewModel.setInterestRate("150");

        viewModel.parseInput();

        assertEquals(ViewModel.Status.READY, viewModel.getStatus());
    }

    @Test
    public void isButtonDisabledWhenInterestRateFieldIsEmpty() {
        viewModel.loadExample();
        viewModel.setInterestRate("");

        viewModel.parseInput();

        assertEquals(false, viewModel.isButtonEnabled());
    }

    @Test
    public void isButtonDisabledWhenInterestRateHasBadFormat() {
        viewModel.loadExample();
        viewModel.setInterestRate("kgh");

        viewModel.parseInput();

        assertEquals(false, viewModel.isButtonEnabled());
    }

    @Test
    public void isButtonDisabledWhenInterestRateHasWrongValue() {
        viewModel.loadExample();
        viewModel.setInterestRate("-8");

        viewModel.parseInput();

        assertEquals(false, viewModel.isButtonEnabled());
    }

}
