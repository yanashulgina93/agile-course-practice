package ru.unn.agile.AreaConverter.viewmodel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

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
        AreaConverterFakeLogger logger = new AreaConverterFakeLogger();
        ViewModel viewModelLogged = new ViewModel(logger);

        assertNotNull(viewModelLogged);
    }
}
