package ru.unn.agile.TemperatureConverter.viewmodel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.unn.agile.TemperatureConverter.model.TemperatureScaleName;

import java.util.List;

import static org.junit.Assert.*;

public class ViewModelLoggingTests {

    private ViewModel viewModel;

    @Before
    public void setUp() {
        TemperatureConverterFakeLogger logger = new TemperatureConverterFakeLogger();
        viewModel = new ViewModel(logger);
    }

    @After
    public void tearDown() {
        viewModel = null;
    }

    @Test
    public void canCreateWithLogger() {
        TemperatureConverterFakeLogger logger = new TemperatureConverterFakeLogger();
        ViewModel viewModelLogged = new ViewModel(logger);

        assertNotNull(viewModelLogged);
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructorThrowsExceptionWithNullLogger() {
        ViewModel viewModelWithNullLogger = new ViewModel(null);
    }

    @Test
    public void logIsEmptyAfterConstruction() {
        List<String> log = viewModel.getLog();

        assertTrue(log.isEmpty());
    }

    @Test
    public void logContainsMessageWhenInputIsChanged() {
        viewModel.setInputTemperature("0.0");
        viewModel.onInputValueFocusLost();

        List<String> log = viewModel.getLog();

        assertEquals(1, log.size());
    }

    @Test
    public void logContainsErrorMessageWhenNewInputHasBadFormat() {
        final String incorrectInput = "SomethingWicked";
        viewModel.setInputTemperature(incorrectInput);
        viewModel.onInputValueFocusLost();
        List<String> log = viewModel.getLog();
        final String expected = LogMessage.INCORRECT_INPUT.toString() + incorrectInput;
        assertEquals(expected, log.get(0));
    }

    @Test
    public void logContainsNormalMessageWhenNewInputIsCorrect() {
        final String correctInput = "0.0";
        viewModel.setInputTemperature(correctInput);
        viewModel.onInputValueFocusLost();
        List<String> log = viewModel.getLog();
        final String expected = LogMessage.INPUT_EDITED.toString() + correctInput;
        assertEquals(expected, log.get(0));
    }


    @Test
    public void logContainsErrorMessageWhenNewInputIsNonPhysical() {
        final String correctInput = "-300.0";
        viewModel.setInputTemperature(correctInput);
        viewModel.onInputValueFocusLost();
        List<String> log = viewModel.getLog();
        final String expected = LogMessage.NON_PHYSICAL_INPUT.toString() + correctInput;
        assertEquals(expected, log.get(0));
    }

    @Test
    public void canAddMultipleMessagesInLogWhenInputIsSameWithLostFocus() {
        final String input = "0.0";
        viewModel.setInputTemperature(input);
        viewModel.onInputValueFocusLost();
        viewModel.setInputTemperature(input);
        viewModel.onInputValueFocusLost();
        List<String> log = viewModel.getLog();
        assertEquals(2, log.size());
    }

    @Test
    public void doNotAddMultipleMessagesInLogWhenFocusHoldOn() {
        final String input = "0.0";
        viewModel.setInputTemperature(input);
        viewModel.setInputTemperature(input);
        viewModel.onInputValueFocusLost();
        viewModel.onInputValueFocusLost();
        List<String> log = viewModel.getLog();
        assertEquals(1, log.size());
    }

    @Test
    public void canAddMessageToLogWhenScaleIsChanged() {
        viewModel.setScale(TemperatureScaleName.NEWTON);
        List<String> log = viewModel.getLog();
        final String expected = LogMessage.SCALE_CHANGED.toString() + TemperatureScaleName.NEWTON.toString();
        assertEquals(expected, log.get(0));

    }

}
