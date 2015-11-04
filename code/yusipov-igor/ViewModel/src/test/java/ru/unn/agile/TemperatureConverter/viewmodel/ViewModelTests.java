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
}
