package ru.unn.agile.Metrics.viewmodel;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DistanceCalculatorViewModelTests {

    private DistanceCalculatorViewModel viewModel;

    @Before
    public void setUp() {
        viewModel = new DistanceCalculatorViewModel();
    }

    @Test
    public void calculateButtonIsDisabledByDefault() {
        assertTrue(viewModel.isCalculateButtonDisabled());
    }

    @Test
    public void calculateButtonIsDisabledWhenBadInputFormat() {
        viewModel.setFirstVec("1 -2.0 3");
        viewModel.setSecondVec("@trash 1.0$");
        assertTrue(viewModel.isCalculateButtonDisabled());
    }

    @Test
    public void calculateButtonIsDisabledWhenIncompleteInput() {
        viewModel.setFirstVec("");
        viewModel.setSecondVec("1 -2.0 3");
        assertTrue(viewModel.isCalculateButtonDisabled());
    }

    @Test
    public void calculateButtonIsDisabledWhenDifferentSize() {
        viewModel.setFirstVec("4.0 5.0");
        viewModel.setSecondVec("1 -2.0 3");
        assertTrue(viewModel.isCalculateButtonDisabled());
    }

    @Test
    public void calculateButtonIsEnabledWhenCorrectInput() {
        viewModel.setFirstVec("1 -2.0 3");
        viewModel.setSecondVec("-4.0 5 -6.0");
        assertFalse(viewModel.isCalculateButtonDisabled());
    }

    @Test
    public void calculateButtonIsDisabledWhenClearVector() {
        viewModel.setFirstVec("1 -2.0 3");
        viewModel.setSecondVec("-4.0 5 -6.0");
        viewModel.setSecondVec("");
        assertTrue(viewModel.isCalculateButtonDisabled());
    }

    @Test
    public void calculateButtonIsDisabledWhenClearVectorComponent() {
        viewModel.setFirstVec("1 -2.0 3");
        viewModel.setSecondVec("-4.0 5 -6.0");
        viewModel.setFirstVec("1 -2.0");
        assertTrue(viewModel.isCalculateButtonDisabled());
    }

    @Test
    public void calculateButtonIsEnabledWhenEditVectorFormat() {
        viewModel.setFirstVec("1.@0 -2.0$  ");
        viewModel.setSecondVec("-3.0 4.0");
        viewModel.setFirstVec("1.0 -2.0");
        assertFalse(viewModel.isCalculateButtonDisabled());
    }

    @Test
    public void calculateButtonIsEnabledWhenAddMissingVectorComponent() {
        viewModel.setFirstVec("1 -2.0 3");
        viewModel.setSecondVec("5 -6.0");
        viewModel.setSecondVec("-4.0 5 -6.0");
        assertFalse(viewModel.isCalculateButtonDisabled());
    }

    @Test
    public void showHelpMessageByDefault() {
        assertEquals(viewModel.HELP_MESSAGE, viewModel.getInputStatus());
    }

    @Test
    public void showErrorMessageWhenBadInputFormat() {
        viewModel.setFirstVec("1 -2.0 3");
        viewModel.setSecondVec("@trash 1.0$");
        assertEquals("Bad vector format", viewModel.getInputStatus());
    }

    @Test
    public void showErrorMessageWhenDifferentSize() {
        viewModel.setFirstVec("4.0 5.0");
        viewModel.setSecondVec("1 -2.0 3");
        assertEquals("Vectors have different size", viewModel.getInputStatus());
    }

    @Test
    public void noMessageWhenCorrectInput() {
        viewModel.setFirstVec("1 -2.0 3");
        viewModel.setSecondVec("-4.0 5 -6.0");
        assertEquals("", viewModel.getInputStatus());
    }

    @Test
    public void showHelpMessageWhenClearVector() {
        viewModel.setFirstVec("1 -2.0 3");
        viewModel.setSecondVec("-4.0 5 -6.0");
        viewModel.setSecondVec("");
        assertEquals(viewModel.HELP_MESSAGE, viewModel.getInputStatus());
    }

    @Test
    public void showErrorMessageWhenClearVectorComponent() {
        viewModel.setFirstVec("1 -2.0 3");
        viewModel.setSecondVec("-4.0 5 -6.0");
        viewModel.setFirstVec("1 -2.0");
        assertEquals("Vectors have different size", viewModel.getInputStatus());
    }

    @Test
    public void hideErrorMessageWhenEditVectorFormat() {
        viewModel.setFirstVec("1.@0 -2.0$  ");
        viewModel.setSecondVec("-3.0 4.0");
        viewModel.setFirstVec("1.0 -2.0");
        assertEquals("", viewModel.getInputStatus());
    }

    @Test
    public void hideErrorMessageWhenAddMissingVectorComponent() {
        viewModel.setFirstVec("1 -2.0 3");
        viewModel.setSecondVec("5 -6.0");
        viewModel.setSecondVec("-4.0 5 -6.0");
        assertEquals("", viewModel.getInputStatus());
    }

    @Test
    public void showErrorMessageWhenEmptyAndBadInputFormat() {
        viewModel.setFirstVec("@trash 1.0$");
        viewModel.setSecondVec("");
        assertEquals("Bad vector format", viewModel.getInputStatus());
    }

    @Test
    public void showHelpMessageWhenIncompleteInput() {
        viewModel.setFirstVec("1 -2.0 3");
        viewModel.setSecondVec("");
        assertEquals(viewModel.HELP_MESSAGE, viewModel.getInputStatus());
    }

    @Test(expected = IllegalArgumentException.class)
    public void calculateThrowsWhenInvalidMetricName() {
        viewModel.setFirstVec("1.0 -2.0 3.0");
        viewModel.setSecondVec("-4.0 5.0 -6.0");
        viewModel.setMetric("RHO ZERO");
        viewModel.calculate();
    }

    @Test(expected = IllegalStateException.class)
    public void calculateThrowsWhenIncompleteInput() {
        viewModel.setFirstVec("");
        viewModel.setSecondVec("1 -2.0 3");
        viewModel.setMetric("RHO ONE");
        viewModel.calculate();
    }

    @Test(expected = IllegalStateException.class)
    public void calculateThrowsWhenDifferentSize() {
        viewModel.setFirstVec("4.0 5.0");
        viewModel.setSecondVec("1 -2.0 3");
        viewModel.setMetric("RHO TWO");
        viewModel.calculate();
    }

    @Test(expected = IllegalStateException.class)
    public void calculateThrowsWhenBadInputFormat() {
        viewModel.setFirstVec("1 -2.0 3");
        viewModel.setSecondVec("@trash 1.0$");
        viewModel.setMetric("RHO THREE");
        viewModel.calculate();
    }

    @Test(expected = IllegalStateException.class)
    public void calculateThrowsWhenNoInput() {
        viewModel.setFirstVec("");
        viewModel.setSecondVec("");
        viewModel.setMetric("RHO FOUR");
        viewModel.calculate();
    }

    @Test
    public void whenCalculateDistanceInRhoInf() {
        viewModel.setFirstVec("4.1 -5.2 6.3 -7.4");
        viewModel.setSecondVec("3.1 -9.2 8.3 -2.4");
        viewModel.setMetric("RHO INF");
        viewModel.calculate();
        assertEquals("5.0", viewModel.getResult());
    }

    @Test
    public void whenCalculateDistanceInRhoOne() {
        viewModel.setFirstVec("4.1 -5.2 6.3 -7.4");
        viewModel.setSecondVec("3.1 -9.2 8.3 -2.4");
        viewModel.setMetric("RHO ONE");
        viewModel.calculate();
        assertEquals("12.0", viewModel.getResult());
    }

    @Test
    public void whenCalculateDistanceInRhoTwo() {
        viewModel.setFirstVec("-4.1 6.3 -7.4 0.5");
        viewModel.setSecondVec("-3.1 9.3 -2.4 1.5");
        viewModel.setMetric("RHO TWO");
        viewModel.calculate();
        assertEquals("6.0", viewModel.getResult());
    }

    @Test
    public void whenCalculateDistanceInRhoThree() {
        viewModel.setFirstVec("-4.1 6.3 -7.4 5.5 -1.6");
        viewModel.setSecondVec("-3.1 8.3 -4.4 2.5 -0.6");
        viewModel.setMetric("RHO THREE");
        viewModel.calculate();
        assertEquals("4.0", viewModel.getResult());
    }

    @Test
    public void whenCalculateDistanceInRhoFour() {
        viewModel.setFirstVec("-4.1 6.3 -7.4 9.5 -1.6 0.7");
        viewModel.setSecondVec("-5.1 4.3 -5.4 7.5 -3.6 2.7");
        viewModel.setMetric("RHO FOUR");
        viewModel.calculate();
        assertEquals("3.0", viewModel.getResult());
    }
}
