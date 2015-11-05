package ru.unn.agile.TemperatureConverter.viewmodel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.unn.agile.TemperatureConverter.Model.TemperatureScaleName;

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
    public void canSetAllDefaultValues() {
        assertEquals("", viewModel.getInputTemperature());
        assertEquals("", viewModel.getResultTemperature());
        assertEquals(Status.WAITING, viewModel.getStatus());
        assertEquals(TemperatureScaleName.FAHRENHEIT, viewModel.getScale());
        assertEquals(false, viewModel.isConvertButtonEnabled());
    }

    @Test
    public void isStatusWaitingAtStart() {
        assertEquals(Status.WAITING, viewModel.getStatus());
    }

    @Test
    public void canSetInputTemperature() {
        viewModel.setInputTemperature("0.0");
        assertEquals("0.0", viewModel.getInputTemperature());
    }

    @Test
    public void canSetScale() {
        viewModel.setScale(TemperatureScaleName.NEWTON);
        assertEquals(viewModel.getScale(), TemperatureScaleName.NEWTON);
    }

    @Test
    public void canSetStatusReadyWhenInputIsCorrect() {
        viewModel.setInputTemperature("1.0");
        viewModel.parse();
        assertEquals(viewModel.getStatus(), Status.READY);
    }

    @Test
    public void canSetStatusBadFormatWhenInputIsIncorrect() {
        viewModel.setInputTemperature("SomethingWicked");
        viewModel.parse();
        assertEquals(viewModel.getStatus(), Status.BAD_FORMAT);
    }

    @Test
    public void canSetStatusWaitingWhenInputIsEmpty() {
        viewModel.setInputTemperature("");
        viewModel.parse();
        assertEquals(viewModel.getStatus(), Status.WAITING);
    }

    @Test
    public void isConvertButtonEnabledWhenInputIsCorrect() {
        viewModel.setInputTemperature("1.0");
        viewModel.parse();
        assertEquals(true, viewModel.isConvertButtonEnabled());
    }

    @Test
    public void isConvertButtonDisabledWhenInputIsIncorrect() {
        viewModel.setInputTemperature("1.0");
        viewModel.parse();
        viewModel.setInputTemperature("SomethingWicked");
        viewModel.parse();
        assertEquals(false, viewModel.isConvertButtonEnabled());
    }

    @Test
    public void isConvertButtonDisabledWhenInputIsEmpty() {
        viewModel.setInputTemperature("1.0");
        viewModel.parse();
        viewModel.setInputTemperature("");
        viewModel.parse();
        assertEquals(false, viewModel.isConvertButtonEnabled());
    }

    @Test
    public void canConvertToFahrenheit() {
        viewModel.setInputTemperature("1.0");
        viewModel.setScale(TemperatureScaleName.FAHRENHEIT);
        viewModel.convert();
        assertEquals("33.8", viewModel.getResultTemperature());
    }

    @Test
       public void canConvertToKelvin() {
        viewModel.setInputTemperature("1.0");
        viewModel.setScale(TemperatureScaleName.KELVIN);
        viewModel.convert();
        assertEquals("274.0", viewModel.getResultTemperature());
    }

    @Test
    public void canConvertToNewton() {
        viewModel.setInputTemperature("1.0");
        viewModel.setScale(TemperatureScaleName.KELVIN);
        viewModel.convert();
        assertEquals("274.0", viewModel.getResultTemperature());
    }

    @Test
    public void canSetStatusSuccesWhenConvertingIsSucces() {
        viewModel.setInputTemperature("1.0");
        viewModel.setScale(TemperatureScaleName.KELVIN);
        viewModel.convert();
        assertEquals(Status.SUCCESS, viewModel.getStatus());
    }

    @Test
    public void isStatusBadFormatWhenConvertIncorrectInput() {
        viewModel.setInputTemperature("SomethingWicked");
        viewModel.setScale(TemperatureScaleName.KELVIN);
        viewModel.convert();
        assertEquals(Status.BAD_FORMAT, viewModel.getStatus());
    }

    @Test
    public void isStatusWaitingWhenConvertEmptyInput() {
        viewModel.setInputTemperature("");
        viewModel.setScale(TemperatureScaleName.KELVIN);
        viewModel.convert();
        assertEquals(Status.WAITING, viewModel.getStatus());
    }
}
