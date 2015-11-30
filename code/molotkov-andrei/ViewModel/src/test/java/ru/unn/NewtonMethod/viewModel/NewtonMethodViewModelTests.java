package ru.unn.NewtonMethod.viewModel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class NewtonMethodViewModelTests {
    private NewtonMethodViewModel viewModel;

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
        assertEquals(NewtonMethodViewModel.Status.WAITING.getMessage(), viewModel.getStatus());
    }

    private void inputData() {
        viewModel.setFunction("(x+3)*(x+3)-2");
        viewModel.setDerivative("2*(x+3)");
        viewModel.setLeftPointOfRange("-2");
        viewModel.setRightPointOfRange("0");
    }

    @Test
    public void isStatusReadyWhenFieldsAreFill() {
        inputData();

        viewModel.processKeyInTextField(NewtonMethodViewModel.KeyboardKeys.ANY.getKey());

        assertEquals(NewtonMethodViewModel.Status.READY.getMessage(), viewModel.getStatus());
    }

    @Test
    public void canReportBadFormatRange() {
        viewModel.setLeftPointOfRange("a");

        viewModel.processKeyInTextField(NewtonMethodViewModel.KeyboardKeys.ANY.getKey());

        assertEquals(NewtonMethodViewModel.Status.BAD_FORMAT_RANGE.getMessage(),
                    viewModel.getStatus());
    }

    @Test
    public void canReportBadFormatFunction() {
        viewModel.setFunction("x+x(");

        viewModel.processKeyInTextField(NewtonMethodViewModel.KeyboardKeys.ANY.getKey());

        assertEquals(NewtonMethodViewModel.Status.BAD_FORMAT_FUNCTION.getMessage(),
                    viewModel.getStatus());
    }

    @Test
    public void canCleanStatusIfParseIsOK() {
        viewModel.setRightPointOfRange("a");
        viewModel.processKeyInTextField(NewtonMethodViewModel.KeyboardKeys.ANY.getKey());

        viewModel.setRightPointOfRange("1.0");
        viewModel.processKeyInTextField(NewtonMethodViewModel.KeyboardKeys.ANY.getKey());

        assertEquals(NewtonMethodViewModel.Status.WAITING.getMessage(), viewModel.getStatus());
    }

    @Test
    public void byDefaultCalculateButtonIsDisabled() {
        assertFalse(viewModel.isCalculateButtonEnabled());
    }

    @Test
    public void isCalculateButtonDisabledWhenFormatIsBad() {
        viewModel.setRightPointOfRange("trash");

        viewModel.processKeyInTextField(NewtonMethodViewModel.KeyboardKeys.ANY.getKey());

        assertFalse(viewModel.isCalculateButtonEnabled());
    }

    @Test
    public void isCalculateButtonDisabledWhenIncorrectFunction() {
        viewModel.setFunction("x*x-2)");

        viewModel.processKeyInTextField(NewtonMethodViewModel.KeyboardKeys.ANY.getKey());

        assertFalse(viewModel.isCalculateButtonEnabled());
    }

    @Test
    public void isCalculateButtonDisabledWithIncompleteInput() {
        viewModel.setRightPointOfRange("1");
        viewModel.setLeftPointOfRange("1");

        viewModel.processKeyInTextField(NewtonMethodViewModel.KeyboardKeys.ANY.getKey());

        assertFalse(viewModel.isCalculateButtonEnabled());
    }

    @Test
    public void canSetSuccessMessage() {
        inputData();

        viewModel.processKeyInTextField(10);

        assertEquals(NewtonMethodViewModel.Status.SUCCESS.getMessage(), viewModel.getStatus());
    }

    @Test
    public void canSetNoRootMessage() {
        inputData();
        viewModel.setLeftPointOfRange("-1");

        viewModel.processKeyInTextField(10);

        assertEquals(NewtonMethodViewModel.Status.NO_ROOT.getMessage(), viewModel.getStatus());
    }

    @Test
    public void canSetBadFormatMessage() {
        viewModel.setLeftPointOfRange("a");

        viewModel.processKeyInTextField(10);

        assertEquals(NewtonMethodViewModel.Status.BAD_FORMAT_RANGE.getMessage(),
                    viewModel.getStatus());
    }

    @Test
    public void isStatusReadyWhenKeyIsNotEnter() {
        inputData();

        viewModel.processKeyInTextField(NewtonMethodViewModel.KeyboardKeys.ANY.getKey());

        assertEquals(NewtonMethodViewModel.Status.READY.getMessage(), viewModel.getStatus());
    }

    @Test
    public void isStatusSuccessWhenKeyIsEnter() {
        inputData();

        viewModel.processKeyInTextField(NewtonMethodViewModel.KeyboardKeys.ENTER.getKey());

        assertEquals(NewtonMethodViewModel.Status.SUCCESS.getMessage(), viewModel.getStatus());
    }

    @Test
    public void whenEnterFunctionAndRangeCalculateButtonIsEnabled() {
        inputData();

        viewModel.processKeyInTextField(NewtonMethodViewModel.KeyboardKeys.ANY.getKey());

        assertTrue(viewModel.isCalculateButtonEnabled());
    }

    @Test
    public void whenCalculateRootDisplayResult() {
        inputData();

        viewModel.processKeyInTextField(10);

        assertEquals("-1.586", viewModel.getRoot());
    }
}
