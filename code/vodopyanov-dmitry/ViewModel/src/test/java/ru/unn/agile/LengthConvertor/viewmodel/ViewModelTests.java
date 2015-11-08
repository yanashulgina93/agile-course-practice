package ru.unn.agile.LengthConvertor.viewmodel;

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
    public void canSetDefaultValues() {
        assertEquals("", viewModel.inputValueProperty().get());
        assertEquals(LengthUnit.INCH, viewModel.inputUnitProperty().get());
        assertEquals("", viewModel.outputValueProperty().get());
        assertEquals(LengthUnit.FOOT, viewModel.outputUnitProperty().get());
        assertEquals(Status.WAITING.toString(), viewModel.errorMessageProperty().get());
    }

    @Test
    public void statusIsWaitingWhenCalculateWithEmptyFields() {
        viewModel.calculate();
        assertEquals(Status.WAITING.toString(), viewModel.errorMessageProperty().get());
    }

    @Test
    public void statusIsReadyWhenFieldsAreFill() {
        setInputData();

        assertEquals(Status.READY.toString(), viewModel.errorMessageProperty().get());
    }

    @Test
    public void canReportBadFormat() {
        viewModel.inputValueProperty().set("a");

        assertEquals(Status.BAD_FORMAT.toString(), viewModel.errorMessageProperty().get());
    }

    @Test
    public void statusIsWaitingIfNotEnoughCorrectData() {
        viewModel.inputValueProperty().set("1");

        assertEquals(Status.WAITING.toString(), viewModel.errorMessageProperty().get());
    }

    @Test
    public void canReportBadFormatWhenOneDigitAndOneLetter() {
        viewModel.inputValueProperty().set("1a");

        assertEquals(Status.BAD_FORMAT.toString(), viewModel.errorMessageProperty().get());
    }

    @Test
    public void calculateButtonIsDisabledInitially() {
        assertTrue(viewModel.calculationDisabledProperty().get());
    }

    @Test
    public void calculateButtonIsDisabledWhenFormatIsBad() {
        setInputData();
        viewModel.inputValueProperty().set("smth");

        assertTrue(viewModel.calculationDisabledProperty().get());
    }

    @Test
    public void calculateButtonIsEnabledWithCorrectInput() {
        setInputData();

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
    public void emptyStringIsDefaultInputValue() {
        assertEquals("", viewModel.inputValueProperty().get());
    }

    @Test
    public void inchIsDefaultInputUnit() {
        assertEquals(LengthUnit.INCH, viewModel.inputUnitProperty().get());
    }

    @Test
    public void footIsDefaultOutputUnit() {
        assertEquals(LengthUnit.FOOT, viewModel.outputUnitProperty().get());
    }

    @Test
    public void convertion1KmeterToMeterHasCorrectResult() {
        viewModel.inputValueProperty().set("1");
        viewModel.inputUnitProperty().set(LengthUnit.KMETER);
        viewModel.outputUnitProperty().set(LengthUnit.METER);

        viewModel.calculate();

        assertEquals("1000.0", viewModel.outputValueProperty().get());
    }

    @Test
    public void convertion100InchToFootHasCorrectResult() {
        viewModel.inputValueProperty().set("100");
        viewModel.inputUnitProperty().set(LengthUnit.INCH);
        viewModel.outputUnitProperty().set(LengthUnit.FOOT);

        viewModel.calculate();

        assertEquals("8.333333267716535", viewModel.outputValueProperty().get());
    }

    @Test
    public void canSetBadFormatMessage() {
        viewModel.inputValueProperty().set("rubbish");

        assertEquals(Status.BAD_FORMAT.toString(), viewModel.errorMessageProperty().get());
    }

    @Test
    public void canSetBadFormatMessageWhenNumberIsNegative() {
        viewModel.inputValueProperty().set("-1");

        assertEquals(Status.BAD_FORMAT.toString(), viewModel.errorMessageProperty().get());
    }

    private void setInputData() {
        viewModel.inputValueProperty().set("1");
    }
}
