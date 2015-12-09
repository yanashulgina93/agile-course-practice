package ru.unn.agile.AreaConverter.viewmodel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.unn.agile.AreaConverter.model.AreaMeasure;

import java.util.List;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.containsString;

public class ViewModelLoggerTest {

    private ViewModel viewModel;

    @Before
    public void setUp() {
        AreaConverterFakeLogger logger = new AreaConverterFakeLogger();
        viewModel = new ViewModel(logger);
    }

    @After
    public void tearDown() {
        viewModel = null;
    }

    @Test
    public void canCreateViewModelWithLogger() {
        assertNotNull(viewModel);
    }

    @Test (expected = IllegalArgumentException.class)
    public void canThrowExceptionWhenLoggerIsNull() {
        ViewModel viewModelLogged = new ViewModel(null);
    }

    @Test
    public void isLogEmptyAtTheStart() {
        List<String> log = viewModel.getLog();

        assertTrue(log.isEmpty());
    }

    @Test
    public void canWriteMessageToLogWhenFromScaleChanged() {
        viewModel.setFrom(AreaMeasure.ARE);
        List<String> log = viewModel.getLog();

        assertThat(log.get(0), containsString(LogMessage.SCALE_FROM_CHANGED.toString()));
    }

    @Test
    public void canWriteMessageToLogWhenToScaleChanged() {
        viewModel.setTo(AreaMeasure.HECTARE);
        List<String> log = viewModel.getLog();

        assertThat(log.get(0), containsString(LogMessage.SCALE_TO_CHANGED.toString()));
    }

    @Test
    public void doNotWriteMessageToLogWhenFromScaleIsNotChanged() {
        viewModel.setFrom(AreaMeasure.SQUARE_METER);
        List<String> log = viewModel.getLog();

        assertTrue(log.isEmpty());
    }

    @Test
    public void doNotWriteMessageToLogWhenToScaleIsNotChanged() {
        viewModel.setTo(AreaMeasure.SQUARE_KILOMETER);
        List<String> log = viewModel.getLog();

        assertTrue(log.isEmpty());
    }

    @Test
    public void canWriteMessageToLogWhenInputChanged() {
        viewModel.setInputArea("0.0");
        List<String> log = viewModel.getLog();

        assertThat(log.get(0), containsString(LogMessage.INPUT_AREA_CHANGED.toString()));
    }
}
