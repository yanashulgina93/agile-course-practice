package ru.unn.agile.HypothecCalculator.viewmodel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.unn.agile.HypothecsCalculator.model.HypothecInputException;

import static org.junit.Assert.assertEquals;

public class ViewModelTextFieldsTest {
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

    private void loadExample() {
        viewModel.setHouseCost("2500000");
        viewModel.setCountOfPeriods("15");
        viewModel.setDownPayment("0");
        viewModel.setInterestRate("10.0");
        viewModel.setFlatFee("0");
        viewModel.setMonthlyFee("2");
        viewModel.setStartMonth("1");
        viewModel.setStartYear("1992");
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
        loadExample();
        viewModel.parseInput();

        assertEquals(ViewModel.Status.READY, viewModel.getStatus());
        assertEquals(true, viewModel.isButtonEnabled());
    }

    @Test
    public void isStatusWaitingWhenHouseCostFieldIsEmpty() {
        loadExample();
        viewModel.setHouseCost("");

        viewModel.parseInput();

        assertEquals(ViewModel.Status.WAITING, viewModel.getStatus());
    }

    @Test
    public void isStatusBadFormatWhenHouseCostHasBadFormat() {
        loadExample();
        viewModel.setHouseCost("qw");

        viewModel.parseInput();

        assertEquals(ViewModel.Status.BAD_FORMAT, viewModel.getStatus());
    }

    @Test
    public void isStatusErrorWhenHouseCostHasWrongValue() {
        loadExample();
        viewModel.setHouseCost("-100");

        viewModel.parseInput();

        assertEquals(HypothecInputException.NEGATIVE_HOUSE_COST, viewModel.getStatus());
    }

    @Test
    public void isStatusReadyWhenHouseCostIsRight() {
        loadExample();
        viewModel.setHouseCost("100");

        viewModel.parseInput();

        assertEquals(ViewModel.Status.READY, viewModel.getStatus());
    }

    @Test
    public void isButtonDisabledWhenHouseCostFieldIsEmpty() {
        loadExample();
        viewModel.setHouseCost("");

        viewModel.parseInput();

        assertEquals(false, viewModel.isButtonEnabled());
    }

    @Test
    public void isButtonDisabledWhenHouseCostHasBadFormat() {
        loadExample();
        viewModel.setHouseCost("qw");

        viewModel.parseInput();

        assertEquals(false, viewModel.isButtonEnabled());
    }

    @Test
    public void isButtonDisabledWhenHouseCostHasWrongValue() {
        loadExample();
        viewModel.setHouseCost("-100");

        viewModel.parseInput();

        assertEquals(false, viewModel.isButtonEnabled());
    }

    @Test
    public void isStatusWaitingWhenCountOfPeriodsFieldIsEmpty() {
        loadExample();
        viewModel.setCountOfPeriods("");

        viewModel.parseInput();

        assertEquals(ViewModel.Status.WAITING, viewModel.getStatus());
    }

    @Test
    public void isStatusBadFormatWhenCountOfPeriodsHasBadFormat() {
        loadExample();
        viewModel.setCountOfPeriods("pgffff");

        viewModel.parseInput();

        assertEquals(ViewModel.Status.BAD_FORMAT, viewModel.getStatus());
    }

    @Test
    public void isStatusErrorWhenCountOfPeriodsHasWrongValue() {
        loadExample();
        viewModel.setCountOfPeriods("0");

        viewModel.parseInput();

        assertEquals(HypothecInputException.NOT_POSITIVE_CREDIT_PERIOD, viewModel.getStatus());
    }

    @Test
    public void isStatusReadyWhenCountOfPeriodsIsRight() {
        loadExample();
        viewModel.setCountOfPeriods("1");

        viewModel.parseInput();

        assertEquals(ViewModel.Status.READY, viewModel.getStatus());
    }

    @Test
    public void isButtonDisabledWhenCountOfPeriodsFieldIsEmpty() {
        loadExample();
        viewModel.setCountOfPeriods("");

        viewModel.parseInput();

        assertEquals(false, viewModel.isButtonEnabled());
    }

    @Test
    public void isButtonDisabledWhenCountOfPeriodsHasBadFormat() {
        loadExample();
        viewModel.setCountOfPeriods("fdddddw");

        viewModel.parseInput();

        assertEquals(false, viewModel.isButtonEnabled());
    }

    @Test
    public void isButtonDisabledCountOfPeriodsHasWrongValue() {
        loadExample();
        viewModel.setCountOfPeriods("0");

        viewModel.parseInput();

        assertEquals(false, viewModel.isButtonEnabled());
    }

    @Test
    public void isStatusWaitingWhenDownPaymentFieldIsEmpty() {
        loadExample();
        viewModel.setDownPayment("");

        viewModel.parseInput();

        assertEquals(ViewModel.Status.WAITING, viewModel.getStatus());
    }

    @Test
    public void isStatusBadFormatWhenDownPaymentHasBadFormat() {
        loadExample();
        viewModel.setDownPayment("eee");

        viewModel.parseInput();

        assertEquals(ViewModel.Status.BAD_FORMAT, viewModel.getStatus());
    }

    @Test
    public void isStatusErrorWhenDownPaymentHasWrongValue() {
        loadExample();
        viewModel.setDownPayment("-400");

        viewModel.parseInput();

        assertEquals(HypothecInputException.NEGATIVE_DOWN_PAYMENT, viewModel.getStatus());
    }

    @Test
    public void isStatusReadyWhenDownPaymentIsRight() {
        loadExample();
        viewModel.setDownPayment("10");

        viewModel.parseInput();

        assertEquals(ViewModel.Status.READY, viewModel.getStatus());
    }

    @Test
    public void isStatusErrorWhenDownPaymentIsMoreThanHouseCost() {
        loadExample();
        viewModel.setHouseCost("100");
        viewModel.setDownPayment("120");

        viewModel.parseInput();

        assertEquals(
                HypothecInputException.DOWN_PAYMENT_IS_MORE_THAN_HOUSE_COST,
                viewModel.getStatus());
    }

    @Test
    public void isButtonDisabledWhenDownPaymentFieldIsEmpty() {
        loadExample();
        viewModel.setDownPayment("");

        viewModel.parseInput();

        assertEquals(false, viewModel.isButtonEnabled());
    }

    @Test
    public void isButtonDisabledWhenDownPaymentHasBadFormat() {
        loadExample();
        viewModel.setDownPayment("eeee");

        viewModel.parseInput();

        assertEquals(false, viewModel.isButtonEnabled());
    }

    @Test
    public void isButtonDisabledWhenDownPaymentHasWrongValue() {
        loadExample();
        viewModel.setDownPayment("-100");

        viewModel.parseInput();

        assertEquals(false, viewModel.isButtonEnabled());
    }

    @Test
    public void isButtonDisabledWhenDownPaymentIsMoreThanHouseCost() {
        loadExample();
        viewModel.setHouseCost("100");
        viewModel.setDownPayment("120");

        viewModel.parseInput();

        assertEquals(false, viewModel.isButtonEnabled());
    }

    @Test
    public void isStatusWaitingWhenInterestRateFieldIsEmpty() {
        loadExample();
        viewModel.setInterestRate("");

        viewModel.parseInput();

        assertEquals(ViewModel.Status.WAITING, viewModel.getStatus());
    }

    @Test
    public void isStatusBadFormatWhenInterestRateHasBadFormat() {
        loadExample();
        viewModel.setInterestRate("qqffw");

        viewModel.parseInput();

        assertEquals(ViewModel.Status.BAD_FORMAT, viewModel.getStatus());
    }

    @Test
    public void isStatusErrorWhenInterestRateHasWrongValue() {
        loadExample();
        viewModel.setInterestRate("-10");

        viewModel.parseInput();

        assertEquals(HypothecInputException.NEGATIVE_INTEREST_RATE, viewModel.getStatus());
    }

    @Test
    public void isStatusReadyWhenInterestRateIsRight() {
        loadExample();
        viewModel.setInterestRate("150");

        viewModel.parseInput();

        assertEquals(ViewModel.Status.READY, viewModel.getStatus());
    }

    @Test
    public void isButtonDisabledWhenInterestRateFieldIsEmpty() {
        loadExample();
        viewModel.setInterestRate("");

        viewModel.parseInput();

        assertEquals(false, viewModel.isButtonEnabled());
    }

    @Test
    public void isButtonDisabledWhenInterestRateHasBadFormat() {
        loadExample();
        viewModel.setInterestRate("kgh");

        viewModel.parseInput();

        assertEquals(false, viewModel.isButtonEnabled());
    }

    @Test
    public void isButtonDisabledWhenInterestRateHasWrongValue() {
        loadExample();
        viewModel.setInterestRate("-8");

        viewModel.parseInput();

        assertEquals(false, viewModel.isButtonEnabled());
    }

    @Test
    public void isStatusWaitingWhenFlatFeeFieldIsEmpty() {
        loadExample();
        viewModel.setFlatFee("");

        viewModel.parseInput();

        assertEquals(ViewModel.Status.WAITING, viewModel.getStatus());
    }

    @Test
    public void isStatusBadFormatWhenFlatFeeHasBadFormat() {
        loadExample();
        viewModel.setFlatFee("wwwzzzz");

        viewModel.parseInput();

        assertEquals(ViewModel.Status.BAD_FORMAT, viewModel.getStatus());
    }

    @Test
    public void isStatusErrorWhenFlatFeeHasWrongValue() {
        loadExample();
        viewModel.setFlatFee("-1.0");

        viewModel.parseInput();

        assertEquals(HypothecInputException.NEGATIVE_FLAT_FEE, viewModel.getStatus());
    }

    @Test
    public void isStatusReadyWhenFlatFeeIsRight() {
        loadExample();
        viewModel.setFlatFee("150.0");

        viewModel.parseInput();

        assertEquals(ViewModel.Status.READY, viewModel.getStatus());
    }

    @Test
    public void isButtonDisabledWhenFlatFeeFieldIsEmpty() {
        loadExample();
        viewModel.setFlatFee("");

        viewModel.parseInput();

        assertEquals(false, viewModel.isButtonEnabled());
    }

    @Test
    public void isButtonDisabledWhenFlatFeeHasBadFormat() {
        loadExample();
        viewModel.setFlatFee("yyy");

        viewModel.parseInput();

        assertEquals(false, viewModel.isButtonEnabled());
    }

    @Test
    public void isButtonDisabledWhenFlatFeeHasWrongValue() {
        loadExample();
        viewModel.setFlatFee("-34.9");

        viewModel.parseInput();

        assertEquals(false, viewModel.isButtonEnabled());
    }

    @Test
    public void isStatusWaitingWhenMonthlyFeeFieldIsEmpty() {
        loadExample();
        viewModel.setMonthlyFee("");

        viewModel.parseInput();

        assertEquals(ViewModel.Status.WAITING, viewModel.getStatus());
    }

    @Test
    public void isStatusBadFormatWhenMonthlyFeeHasBadFormat() {
        loadExample();
        viewModel.setMonthlyFee("tutuh");

        viewModel.parseInput();

        assertEquals(ViewModel.Status.BAD_FORMAT, viewModel.getStatus());
    }

    @Test
    public void isStatusErrorWhenMonthlyFeeHasWrongValue() {
        loadExample();
        viewModel.setMonthlyFee("-1.20");

        viewModel.parseInput();

        assertEquals(HypothecInputException.NEGATIVE_MONTHLY_FEE, viewModel.getStatus());
    }

    @Test
    public void isStatusReadyWhenMonthlyFeeIsRight() {
        loadExample();
        viewModel.setMonthlyFee("1330.0");

        viewModel.parseInput();

        assertEquals(ViewModel.Status.READY, viewModel.getStatus());
    }

    @Test
    public void isButtonDisabledWhenMonthlyFeeFieldIsEmpty() {
        loadExample();
        viewModel.setMonthlyFee("");

        viewModel.parseInput();

        assertEquals(false, viewModel.isButtonEnabled());
    }

    @Test
    public void isButtonDisabledWhenMonthlyFeeHasBadFormat() {
        loadExample();
        viewModel.setMonthlyFee("veryverybad");

        viewModel.parseInput();

        assertEquals(false, viewModel.isButtonEnabled());
    }

    @Test
    public void isButtonDisabledWhenMonthlyFeeHasWrongValue() {
        loadExample();
        viewModel.setMonthlyFee("-4.9");

        viewModel.parseInput();

        assertEquals(false, viewModel.isButtonEnabled());
    }

    @Test
    public void isStatusWaitingWhenStartMonthFieldIsEmpty() {
        loadExample();
        viewModel.setStartMonth("");

        viewModel.parseInput();

        assertEquals(ViewModel.Status.WAITING, viewModel.getStatus());
    }

    @Test
    public void isStatusBadFormatWhenStartMonthHasBadFormat() {
        loadExample();
        viewModel.setStartMonth("12.9");

        viewModel.parseInput();

        assertEquals(ViewModel.Status.BAD_FORMAT, viewModel.getStatus());
    }

    @Test
    public void isStatusErrorWhenStartMonthHasWrongValue() {
        loadExample();
        viewModel.setStartMonth("13");

        viewModel.parseInput();

        assertEquals(HypothecInputException.BAD_MONTH, viewModel.getStatus());
    }

    @Test
    public void isStatusReadyWhenStartMonthIsRight() {
        loadExample();
        viewModel.setStartMonth("3");

        viewModel.parseInput();

        assertEquals(ViewModel.Status.READY, viewModel.getStatus());
    }

    @Test
    public void isButtonDisabledWhenStartMonthFieldIsEmpty() {
        loadExample();
        viewModel.setStartMonth("");

        viewModel.parseInput();

        assertEquals(false, viewModel.isButtonEnabled());
    }

    @Test
    public void isButtonDisabledWhenStartMonthHasBadFormat() {
        loadExample();
        viewModel.setStartMonth("ufff");

        viewModel.parseInput();

        assertEquals(false, viewModel.isButtonEnabled());
    }

    @Test
    public void isButtonDisabledWhenStartMonthHasWrongValue() {
        loadExample();
        viewModel.setStartMonth("-4");

        viewModel.parseInput();

        assertEquals(false, viewModel.isButtonEnabled());
    }

    @Test
    public void isStatusWaitingWhenStartYearFieldIsEmpty() {
        loadExample();
        viewModel.setStartYear("");

        viewModel.parseInput();

        assertEquals(ViewModel.Status.WAITING, viewModel.getStatus());
    }

    @Test
    public void isStatusBadFormatWhenStartYearHasBadFormat() {
        loadExample();
        viewModel.setStartYear("hg17.3");

        viewModel.parseInput();

        assertEquals(ViewModel.Status.BAD_FORMAT, viewModel.getStatus());
    }

    @Test
    public void isStatusErrorWhenStartYearHasWrongValue() {
        loadExample();
        viewModel.setStartYear("1313");

        viewModel.parseInput();

        assertEquals(HypothecInputException.BAD_DATA, viewModel.getStatus());
    }

    @Test
    public void isStatusReadyWhenStartYearIsRight() {
        loadExample();
        viewModel.setStartYear("1993");

        viewModel.parseInput();

        assertEquals(ViewModel.Status.READY, viewModel.getStatus());
    }

    @Test
    public void isButtonDisabledWhenStartYearFieldIsEmpty() {
        loadExample();
        viewModel.setStartYear("");

        viewModel.parseInput();

        assertEquals(false, viewModel.isButtonEnabled());
    }

    @Test
    public void isButtonDisabledWhenStartYearHasBadFormat() {
        loadExample();
        viewModel.setStartYear("15.2");

        viewModel.parseInput();

        assertEquals(false, viewModel.isButtonEnabled());
    }

    @Test
    public void isButtonDisabledWhenStartYearHasWrongValue() {
        loadExample();
        viewModel.setStartYear("1990");

        viewModel.parseInput();

        assertEquals(false, viewModel.isButtonEnabled());
    }

}
