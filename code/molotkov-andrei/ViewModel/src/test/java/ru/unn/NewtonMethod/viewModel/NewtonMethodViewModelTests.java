package ru.unn.NewtonMethod.viewModel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class NewtonMethodViewModelTests {
    private NewtonMethodViewModel viewModel;
    private final double delta = 0.001;

    @Before
    public void setUp() {
        viewModel = new NewtonMethodViewModel();
    }

    @After
    public void tearDown() {
        viewModel = null;
    }

    @Test
    public void isStatusWaitingInTheBeginning() {
        assertEquals(NewtonMethodViewModel.Status.WAITING, viewModel.getStatus());
    }

    private void inpitDate() {
        viewModel.setFunction("(x+3)*(x+3)-2=");
        viewModel.setDerivative("2*(x+3)=");
        viewModel.setLeftPointOfRange("-2");
        viewModel.setRightPointOfRange("0");
    }

    @Test
    public void isStatusReadyWhenFieldsAreFill() {
        inpitDate();
        viewModel.processKeyInTextField(KeyboardKeys.ANY);
        assertEquals(NewtonMethodViewModel.Status.READY, viewModel.getStatus());
    }

    @Test
    public void canReportBadFormatRange() {
        viewModel.setLeftPointOfRange("a");
        viewModel.processKeyInTextField(KeyboardKeys.ANY);
        assertEquals(NewtonMethodViewModel.Status.BAD_FORMAT_RANGE, viewModel.getStatus());
    }

    @Test
    public void canReportBadFormatFunction() {
        viewModel.setFunction("x+x(");
        viewModel.processKeyInTextField(KeyboardKeys.ANY);
        assertEquals(NewtonMethodViewModel.Status.BAD_FORMAT_FUNCTION, viewModel.getStatus());
    }

    @Test
    public void canCleanStatusIfParseIsOK() {
        viewModel.setRightPointOfRange("a");
        viewModel.processKeyInTextField(KeyboardKeys.ANY);
        viewModel.setRightPointOfRange("1.0");
        viewModel.processKeyInTextField(KeyboardKeys.ANY);
        assertEquals(NewtonMethodViewModel.Status.WAITING, viewModel.getStatus());
    }

    @Test
    public void byDefaultCalculateButtonIsDisabled() {
        assertFalse(viewModel.isCalculateButtonEnabled());
    }

    @Test
    public void isCalculateButtonDisabledWhenFormatIsBad() {
        inpitDate();
        viewModel.processKeyInTextField(KeyboardKeys.ANY);
        assertEquals(true, viewModel.isCalculateButtonEnabled());

        viewModel.setRightPointOfRange("trash");
        viewModel.processKeyInTextField(KeyboardKeys.ANY);
        assertEquals(false, viewModel.isCalculateButtonEnabled());
    }

    @Test
    public void isCalculateButtonDisabledWhenIncorrectFunction() {
        inpitDate();
        viewModel.processKeyInTextField(KeyboardKeys.ANY);
        assertEquals(true, viewModel.isCalculateButtonEnabled());

        viewModel.setFunction("x*x-2)");
        viewModel.processKeyInTextField(KeyboardKeys.ANY);
        assertEquals(false, viewModel.isCalculateButtonEnabled());
    }

    @Test
    public void isCalculateButtonDisabledWithIncompleteInput() {
        viewModel.setRightPointOfRange("1");
        viewModel.setLeftPointOfRange("1");

        viewModel.processKeyInTextField(KeyboardKeys.ANY);

        assertEquals(false, viewModel.isCalculateButtonEnabled());
    }

    @Test
    public void canSetSuccessMessage() {
        inpitDate();
        viewModel.calculateRoot();
        assertEquals(NewtonMethodViewModel.Status.SUCCESS, viewModel.getStatus());
    }

    @Test
    public void canSetNoMonotonicMessage() {
        inpitDate();
        viewModel.setLeftPointOfRange("-4");
        viewModel.calculateRoot();
        assertEquals(NewtonMethodViewModel.Status.NO_MONOTONIC, viewModel.getStatus());
    }

    @Test
    public void canSetNoRootMessage() {
        inpitDate();
        viewModel.setLeftPointOfRange("-1");
        viewModel.calculateRoot();
        assertEquals(NewtonMethodViewModel.Status.NO_ROOT, viewModel.getStatus());
    }

    @Test
    public void canSetBadFormatMessage() {
        viewModel.setLeftPointOfRange("a");
        viewModel.calculateRoot();
        assertEquals(NewtonMethodViewModel.Status.BAD_FORMAT_RANGE, viewModel.getStatus());
    }

    @Test
    public void isStatusReadyWhenKeyIsNotEnter() {
        inpitDate();
        viewModel.processKeyInTextField(KeyboardKeys.ANY);
        assertEquals(NewtonMethodViewModel.Status.READY, viewModel.getStatus());
    }

    @Test
    public void isStatusSuccessWhenKeyIsEnter() {
        inpitDate();
        viewModel.processKeyInTextField(KeyboardKeys.ENTER);
        assertEquals(NewtonMethodViewModel.Status.SUCCESS, viewModel.getStatus());
    }

    @Test
    public void whenEnterFunctionAndRangeCalculateButtonIsEnabled() {
        inpitDate();
        viewModel.processKeyInTextField(KeyboardKeys.ANY);
        assertTrue(viewModel.isCalculateButtonEnabled());
    }

    @Test
    public void whenCalculateRootDisplayResult() {
        inpitDate();
        viewModel.calculateRoot();
        assertEquals(-1.585, viewModel.getRoot(), delta);
    }
}
