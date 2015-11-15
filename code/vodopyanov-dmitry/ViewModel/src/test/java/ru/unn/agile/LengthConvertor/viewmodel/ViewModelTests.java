package ru.unn.agile.LengthConvertor.viewmodel;

import javafx.collections.ObservableList;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.unn.agile.LengthConvertor.Model.LengthUnit;

import static org.junit.Assert.*;

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
    public void canSetDefaultInputValue() {
        assertEquals("", viewModel.inputValueProperty().get());
    }

    @Test
    public void canSetDefaultInputUnit() {
        assertEquals(LengthUnit.INCH, viewModel.inputUnitProperty().get());
    }

    @Test
    public void canSetDefaultOutputValue() {
        assertEquals("", viewModel.outputValueProperty().get());
    }

    @Test
    public void canSetDefaultOutputUnit() {
        assertEquals(LengthUnit.FOOT, viewModel.outputUnitProperty().get());
    }

    @Test
    public void canSetDefaultStatus() {
        assertEquals(Status.WAITING.toString(), viewModel.hintMessageProperty().get());
    }

    @Test
    public void statusIsReadyWhenFieldIsFillByValidData() {
        setValidInputData();

        assertEquals(Status.READY.toString(), viewModel.hintMessageProperty().get());
    }

    @Test
    public void canStatusReportsBadFormat() {
        setBadInputData();

        assertEquals(Status.BAD_FORMAT.toString(), viewModel.hintMessageProperty().get());
    }

    @Test
    public void statusIsWaitingIfNotEnoughCorrectData() {
        assertEquals(Status.WAITING.toString(), viewModel.hintMessageProperty().get());
    }

    @Test
    public void calculateButtonIsDisabledWhenProgramStarts() {
        assertTrue(viewModel.calculationDisabledProperty().get());
    }

    @Test
    public void calculateButtonIsDisabledWhenFormatIsBad() {
        viewModel.inputValueProperty().set("smth");

        assertTrue(viewModel.calculationDisabledProperty().get());
    }

    @Test
    public void calculateButtonIsEnabledWithValidInput() {
        setValidInputData();

        assertFalse(viewModel.calculationDisabledProperty().get());
    }

    @Test
    public void canSetKMeterInputUnit() {
        viewModel.inputUnitProperty().set(LengthUnit.KMETER);

        assertEquals(LengthUnit.KMETER, viewModel.inputUnitProperty().get());
    }

    @Test
    public void canSetInchOutputUnit() {
        viewModel.outputUnitProperty().set(LengthUnit.INCH);

        assertEquals(LengthUnit.INCH, viewModel.outputUnitProperty().get());
    }

    @Test
    public void canSetBadFormatMessageWhenNumberIsNegative() {
        viewModel.inputValueProperty().set("-1");

        assertEquals(Status.BAD_FORMAT.toString(), viewModel.hintMessageProperty().get());
    }

    @Test
    public void canSetSuccessMessage() {
        setValidInputData();

        viewModel.calculate();

        assertEquals(Status.SUCCESS.toString(), viewModel.hintMessageProperty().get());
    }

    @Test
    public void isErrorMessegeEqualsErrorBadFormatWhenInputDataIncorrect() {
        setBadInputData();

        assertEquals(Status.BAD_FORMAT.toString(), viewModel.getHintMessage());
    }

    private void setValidInputData() {
        viewModel.inputValueProperty().set("1");
    }

    private void setBadInputData() {
        viewModel.inputValueProperty().set("a");
    }
}
