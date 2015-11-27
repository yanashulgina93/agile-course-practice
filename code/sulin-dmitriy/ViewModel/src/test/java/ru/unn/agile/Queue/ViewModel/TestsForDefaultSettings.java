package ru.unn.agile.Queue.ViewModel;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;



public class TestsForDefaultSettings {

    private LabQueueViewModel viewModel;
    @Before
    public void setUpViewModel() {
        viewModel = new LabQueueViewModel();
    }

    @Test
    public void byDefaultFindButtonIsDisabled() {
        assertFalse(viewModel.isFindButtonEnabled());
    }

    @Test
    public void byDefaultPopButtonIsDisabled() {
        assertFalse(viewModel.isPopButtonEnabled());
    }

    @Test
    public void byDefaultPushButtonIsEnabled() {
        assertTrue(viewModel.isPushButtonEnabled());
    }

    @Test
    public void byDefaultSizeIsZero() {
        assertEquals(0, viewModel.getFieldForSize());
    }

    @Test
    public void byDefaultHeadElementFieldIsEmpty() {
        assertEquals("", viewModel.getFieldForHeadElement());
    }

    @Test
    public void byDefaultInputFieldIsEmpty() {
        assertEquals("", viewModel.getFieldForDataInput());
    }

    @Test
    public void byDefaultOutputFieldIsEmpty() {
        assertEquals("", viewModel.getFieldForDataOutput());
    }
}
