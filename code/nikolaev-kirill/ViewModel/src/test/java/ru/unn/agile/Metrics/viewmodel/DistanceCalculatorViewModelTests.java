package ru.unn.agile.Metrics.viewmodel;

import org.junit.Before;
import org.junit.Test;
import ru.unn.agile.Metrics.model.Metric;


import static org.junit.Assert.*;

public class DistanceCalculatorViewModelTests {

    private DistanceCalculatorViewModel viewModel;

    @Before
    public void setUp() {
        viewModel = new DistanceCalculatorViewModel();
    }

    @Test
    public void calculateButtonIsDisabledByDefault() {
        assertFalse(viewModel.isCalculateButtonEnabled());
    }

    @Test
    public void calculateButtonIsDisabledWhenBadInputFormat() {
        viewModel.setVectors("1.0 -2.0 3.0", "@trash 1.0$");
        assertFalse(viewModel.isCalculateButtonEnabled());
    }

    @Test
    public void calculateButtonIsDisabledWhenIncompleteInput() {
        viewModel.setVectors("", "1.0 -2.0 3.0");
        assertFalse(viewModel.isCalculateButtonEnabled());
    }

    @Test
    public void calculateButtonIsDisabledWhenDifferentSize() {
        viewModel.setVectors("4.0 5.0", "1.0 -2.0 3.0");
        assertFalse(viewModel.isCalculateButtonEnabled());
    }

    @Test
    public void calculateButtonIsEnabledWhenCorrectInput() {
        viewModel.setVectors("1.0 -2.0 3.0", "-4.0 5.0 -6.0");
        assertTrue(viewModel.isCalculateButtonEnabled());
    }

    @Test
    public void calculateButtonIsDisabledWhenClearVector() {
        viewModel.setVectors("1.0 -2.0 3.0", "-4.0 5.0 -6.0");
        viewModel.setVectors("1.0 -2.0 3.0", "");
        assertFalse(viewModel.isCalculateButtonEnabled());
    }

    @Test
    public void calculateButtonIsDisabledWhenClearVectorComponent() {
        viewModel.setVectors("1.0 -2.0 3.0", "-4.0 5.0 -6.0");
        viewModel.setVectors("1.0 -2.0", "-4.0 5.0 -6.0");
        assertFalse(viewModel.isCalculateButtonEnabled());
    }

    @Test
    public void calculateButtonIsEnabledWhenEditVectorFormat() {
        viewModel.setVectors("1.@0 -2.0$  ", "-3.0 4.0");
        viewModel.setVectors("1.0 -2.0", "-3.0 4.0");
        assertTrue(viewModel.isCalculateButtonEnabled());
    }

    @Test
    public void calculateButtonIsEnabledWhenAddMissingVectorComponent() {
        viewModel.setVectors("1.0 -2.0 3.0", "5.0 -6.0");
        viewModel.setVectors("1.0 -2.0 3.0", "-4.0 5.0 -6.0");
        assertTrue(viewModel.isCalculateButtonEnabled());
    }

    @Test
    public void showErrorMessageWhenBadInputFormat() {
        viewModel.setVectors("1.0 -2.0 3.0", "@trash 1.0$");
        assertEquals("Bad vector format", viewModel.getErrorMessage());
    }

    @Test
    public void showErrorMessageWhenDifferentSize() {
        viewModel.setVectors("4.0 5.0", "1.0 -2.0 3.0");
        assertEquals("Vectors have different size", viewModel.getErrorMessage());
    }

    @Test
    public void noErrorMessageWhenCorrectInput() {
        viewModel.setVectors("1.0 -2.0 3.0", "-4.0 5.0 -6.0");
        assertEquals("", viewModel.getErrorMessage());
    }

    @Test
    public void showErrorMessageWhenClearVector() {
        viewModel.setVectors("1.0 -2.0 3.0", "-4.0 5.0 -6.0");
        viewModel.setVectors("1.0 -2.0 3.0", "");
        assertEquals("Bad vector format", viewModel.getErrorMessage());
    }

    @Test
    public void showErrorMessageWhenClearVectorComponent() {
        viewModel.setVectors("1.0 -2.0 3.0", "-4.0 5.0 -6.0");
        viewModel.setVectors("1.0 -2.0", "-4.0 5.0 -6.0");
        assertEquals("Vectors have different size", viewModel.getErrorMessage());
    }

    @Test
    public void hideErrorMessageWhenEditVectorFormat() {
        viewModel.setVectors("1.@0 -2.0$  ", "-3.0 4.0");
        viewModel.setVectors("1.0 -2.0", "-3.0 4.0");
        assertEquals("", viewModel.getErrorMessage());
    }

    @Test
    public void hideErrorMessageWhenAddMissingVectorComponent() {
        viewModel.setVectors("1.0 -2.0 3.0", "5.0 -6.0");
        viewModel.setVectors("1.0 -2.0 3.0", "-4.0 5.0 -6.0");
        assertEquals("", viewModel.getErrorMessage());
    }

    @Test
    public void whenCalculateDistanceInRhoInf() {
        viewModel.setVectors("4.1 -5.2 6.3 -7.4", "3.1 -9.2 8.3 -2.4");
        viewModel.setMetric(Metric.RHO_INF);
        viewModel.calculate();
        assertEquals("5.0", viewModel.getDistance());
    }

    @Test
    public void whenCalculateDistanceInRhoOne() {
        viewModel.setVectors("4.1 -5.2 6.3 -7.4", "3.1 -9.2 8.3 -2.4");
        viewModel.setMetric(Metric.RHO_ONE);
        viewModel.calculate();
        assertEquals("12.0", viewModel.getDistance());
    }

    @Test
    public void whenCalculateDistanceInRhoTwo() {
        viewModel.setVectors("-4.1 6.3 -7.4 0.5", "-3.1 9.3 -2.4 1.5");
        viewModel.setMetric(Metric.RHO_TWO);
        viewModel.calculate();
        assertEquals("6.0", viewModel.getDistance());
    }

    @Test
    public void whenCalculateDistanceInRhoThree() {
        viewModel.setVectors("-4.1 6.3 -7.4 5.5 -1.6", "-3.1 8.3 -4.4 2.5 -0.6");
        viewModel.setMetric(Metric.RHO_THREE);
        viewModel.calculate();
        assertEquals("4.0", viewModel.getDistance());
    }

    @Test
    public void whenCalculateDistanceInRhoFour() {
        viewModel.setVectors("-4.1 6.3 -7.4 9.5 -1.6 0.7",
                "-5.1 4.3 -5.4 7.5 -3.6 2.7");
        viewModel.setMetric(Metric.RHO_FOUR);
        viewModel.calculate();
        assertEquals("3.0", viewModel.getDistance());
    }
}
