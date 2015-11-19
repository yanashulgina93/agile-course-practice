package ru.unn.agile.IntegrationMethods.viewmodel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class ViewModelTests {
    private ViewModel viewModel;

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
        assertEquals(ViewModel.Function.X, viewModel.getFunction());
        assertEquals("", viewModel.getLowerLimit());
        assertEquals("", viewModel.getUpperLimit());
        assertEquals(ViewModel.IntegrationMethod.LEFT_RECTANGLES, viewModel.getIntegrationMethod());
        assertEquals("", viewModel.getResult());
        assertEquals(Status.WAITING, viewModel.getStatus());
    }

    @Test
    public void canSetXFunction() {
        viewModel.setFunction(ViewModel.Function.X);

        assertEquals(ViewModel.Function.X, viewModel.getFunction());
    }

    @Test
    public void canSetCosFunction() {
        viewModel.setFunction(ViewModel.Function.COS);

        assertEquals(ViewModel.Function.COS, viewModel.getFunction());
    }

    @Test
    public void canSetExpFunction() {
        viewModel.setFunction(ViewModel.Function.EXP);

        assertEquals(ViewModel.Function.EXP, viewModel.getFunction());
    }

    @Test
    public void canSetLeftRectanglesMethod() {
        viewModel.setIntegrationMethod(ViewModel.IntegrationMethod.LEFT_RECTANGLES);

        assertEquals(ViewModel.IntegrationMethod.LEFT_RECTANGLES, viewModel.getIntegrationMethod());
    }

    @Test
    public void canSetRightRectanglesMethod() {
        viewModel.setIntegrationMethod(ViewModel.IntegrationMethod.RIGHT_RECTANGLES);

        assertEquals(ViewModel.IntegrationMethod.RIGHT_RECTANGLES,
                viewModel.getIntegrationMethod());
    }

    @Test
    public void canSetMidpointRectanglesMethod() {
        viewModel.setIntegrationMethod(ViewModel.IntegrationMethod.MIDPOINT_RECTANGLES);

        assertEquals(ViewModel.IntegrationMethod.MIDPOINT_RECTANGLES,
                viewModel.getIntegrationMethod());
    }

    @Test
    public void canSetTrapezesMethod() {
        viewModel.setIntegrationMethod(ViewModel.IntegrationMethod.TRAPEZES);

        assertEquals(ViewModel.IntegrationMethod.TRAPEZES, viewModel.getIntegrationMethod());
    }

    @Test
    public void canSetSimpsonMethod() {
        viewModel.setIntegrationMethod(ViewModel.IntegrationMethod.SIMPSON);

        assertEquals(ViewModel.IntegrationMethod.SIMPSON, viewModel.getIntegrationMethod());
    }

    @Test
    public void isIntegrateButtonDisableInTheBeginning() {
        assertEquals(false, viewModel.isIntegrateButtonEnabled());
    }

    @Test
    public void canIntegrate() {
        viewModel.setFunction(ViewModel.Function.X);
        viewModel.setIntegrationMethod(ViewModel.IntegrationMethod.LEFT_RECTANGLES);
        setTextFields();

        viewModel.integrate();

        assertEquals("2.0000999999998115", viewModel.getResult());
    }

    @Test
    public void isStatusWaitingInTheBeginning() {
        assertEquals(Status.WAITING, viewModel.getStatus());
    }

    @Test
    public void isStatusWaitingWhenIntegrateWithEmptyTextFields() {
        viewModel.integrate();

        assertEquals(Status.WAITING, viewModel.getStatus());
    }

    @Test
    public void isStatusReadyWhenTextFieldsAreFilled() {
        setTextFields();

        viewModel.processKeyInTextField(KeyboardKeys.ANY);

        assertEquals(Status.READY, viewModel.getStatus());
    }

    @Test
    public void isStatusBadFormatWhenInputIsIncorrect() {
        viewModel.setLowerLimit("abc");
        viewModel.processKeyInTextField(KeyboardKeys.ANY);

        assertEquals(Status.BAD_FORMAT, viewModel.getStatus());
    }

    @Test
    public void canCleanStatusIfParseIsOK() {
        viewModel.setLowerLimit("abc");
        viewModel.processKeyInTextField(KeyboardKeys.ANY);
        viewModel.setLowerLimit("0.0");
        viewModel.processKeyInTextField(KeyboardKeys.ANY);

        assertEquals(Status.WAITING, viewModel.getStatus());
    }

    @Test
    public void isStatusSuccessWhenIntegrateWithCorrectInput() {
        setTextFields();

        viewModel.integrate();

        assertEquals(Status.SUCCESS, viewModel.getStatus());
    }

    @Test
    public void isStatusBadFormatWhenIntegrateWithIncorrectInput() {
        viewModel.setLowerLimit("abc");

        viewModel.integrate();

        assertEquals(Status.BAD_FORMAT, viewModel.getStatus());
    }

    @Test
    public void isStatusReadyWhenAnyKeyIsPressed() {
        setTextFields();

        viewModel.processKeyInTextField(KeyboardKeys.ANY);

        assertEquals(Status.READY, viewModel.getStatus());
    }

    @Test
    public void isStatusSuccessWhenEnterIsPressed() {
        setTextFields();

        viewModel.processKeyInTextField(KeyboardKeys.ENTER);

        assertEquals(Status.SUCCESS, viewModel.getStatus());
    }

    @Test
    public void isIntegrateButtonDisabledWhenFormatIsBad() {
        setTextFields();
        viewModel.processKeyInTextField(KeyboardKeys.ANY);
        assertEquals(true, viewModel.isIntegrateButtonEnabled());

        viewModel.setLowerLimit("abc");
        viewModel.processKeyInTextField(KeyboardKeys.ANY);

        assertEquals(false, viewModel.isIntegrateButtonEnabled());
    }

    @Test
    public void isIntegrateButtonDisabledWithIncompleteInput() {
        viewModel.setLowerLimit("0.0");

        viewModel.processKeyInTextField(KeyboardKeys.ANY);

        assertEquals(false, viewModel.isIntegrateButtonEnabled());
    }

    @Test
    public void isIntegrateButtonEnabledWhenInputIsCorrect() {
        setTextFields();

        viewModel.processKeyInTextField(KeyboardKeys.ANY);

        assertEquals(true, viewModel.isIntegrateButtonEnabled());
    }

    @Test
    public void canGetFunctionName() {
        String xName = ViewModel.Function.X.toString();
        assertEquals("x", xName);
    }

    @Test
    public void canGetNumberOfFunctions() {
        int nFunctions = ViewModel.Function.values().length;
        assertEquals(3, nFunctions);
    }

    @Test
    public void canGetListOfFunctions() {
        ViewModel.Function[] functions = ViewModel.Function.values();
        ViewModel.Function[] currentFunctions = new ViewModel.Function[]{
                ViewModel.Function.X,
                ViewModel.Function.COS,
                ViewModel.Function.EXP};

        assertArrayEquals(currentFunctions, functions);
    }

    @Test
    public void canGetIntegrationMethodName() {
        String leftRectanglesName = ViewModel.IntegrationMethod.LEFT_RECTANGLES.toString();
        assertEquals("left rectangles method", leftRectanglesName);
    }

    @Test
    public void canGetNumberOfIntegrationMethods() {
        int nMethods = ViewModel.IntegrationMethod.values().length;
        assertEquals(5, nMethods);
    }

    @Test
    public void canGetListOfIntegrationMethods() {
        ViewModel.IntegrationMethod[] methods = ViewModel.IntegrationMethod.values();
        ViewModel.IntegrationMethod[] currentMethods = new ViewModel.IntegrationMethod[]{
                ViewModel.IntegrationMethod.LEFT_RECTANGLES,
                ViewModel.IntegrationMethod.RIGHT_RECTANGLES,
                ViewModel.IntegrationMethod.MIDPOINT_RECTANGLES,
                ViewModel.IntegrationMethod.TRAPEZES,
                ViewModel.IntegrationMethod.SIMPSON};

        assertArrayEquals(currentMethods, methods);
    }

    private void setTextFields() {
        viewModel.setLowerLimit("0.0");
        viewModel.setUpperLimit("2.0");
    }
}
