package ru.unn.agile.LeftistHeap.viewmodel;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class LeftistHeapViewModelTest {
    private LeftistHeapViewModel viewModel;

    @Before
    public void setUp() {
        viewModel = new LeftistHeapViewModel();
    }

    @Test
    public void byDefaultInsertButtonIsDisabled() {
        assertFalse(viewModel.isInsertButtonEnabled());
    }

    @Test
    public void whenInputIntegerToInsertField_InsertButtonIsEnabled() {
        viewModel.setNumberToInsert("1");

        assertTrue(viewModel.isInsertButtonEnabled());
    }

    @Test
    public void whenClearInsertField_InsertButtonIsDisabled() {
        viewModel.setNumberToInsert("1");
        viewModel.setNumberToInsert("");

        assertFalse(viewModel.isInsertButtonEnabled());
    }

    @Test
    public void whenInputIntegerToHeap_DisplayHeapContent() {
        viewModel.setNumberToInsert("1");
        viewModel.insert();
        viewModel.setNumberToInsert("5");
        viewModel.insert();
        viewModel.setNumberToInsert("11");
        viewModel.insert();
        viewModel.setNumberToInsert("5");
        viewModel.insert();
        viewModel.setNumberToInsert("11");
        viewModel.insert();

        assertEquals("[1, 5, 5, 11, 11]", viewModel.getHeapContent());
    }
}
