package ru.unn.agile.IntegrationMethods.viewmodel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

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
}
