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
    public void whenInputIntegerToInsertFieldInsertButtonIsEnabled() {
        viewModel.setNumberToInsert("1");

        assertTrue(viewModel.isInsertButtonEnabled());
    }

    @Test
    public void whenClearInsertFieldInsertButtonIsDisabled() {
        viewModel.setNumberToInsert("1");
        viewModel.setNumberToInsert("");

        assertFalse(viewModel.isInsertButtonEnabled());
    }

    @Test
    public void whenInsertOneIntegerToHeapContentDisplayed() {
        viewModel.setNumberToInsert("1");
        viewModel.insertElement();

        assertEquals("[1]", viewModel.getHeapContent());
    }

    @Test
    public void whenInsertTwoIntegersToHeapContentDisplayed() {
        viewModel.setNumberToInsert("1");
        viewModel.insertElement();
        viewModel.setNumberToInsert("2");
        viewModel.insertElement();

        assertEquals("[1, 2]", viewModel.getHeapContent());
    }

    @Test
    public void whenInputWrongValueToInsertFieldErrorDisplayed() {
        viewModel.setNumberToInsert("afc");

        assertEquals(LeftistHeapViewModel.ERROR_INSERT_WRONG_VALUE, viewModel.getErrorText());
    }

    @Test
    public void whenInputWrongValueToInsertFieldInsertButtonIsDisabled() {
        viewModel.setNumberToInsert("afc");

        assertFalse(viewModel.isInsertButtonEnabled());
    }

    @Test
    public void byDefaultDeleteButtonIsDisabled() {
        assertFalse(viewModel.isDeleteButtonEnabled());
    }

    @Test
    public void whenInputIntegerToDeleteFieldDeleteButtonIsEnabled() {
        viewModel.setNumberToDelete("1");

        assertTrue(viewModel.isDeleteButtonEnabled());
    }

    @Test
    public void whenClearDeleteFieldDeleteButtonIsDisabled() {
        viewModel.setNumberToDelete("1");
        viewModel.setNumberToDelete("");

        assertFalse(viewModel.isDeleteButtonEnabled());
    }

    @Test
    public void whenTryingToDeleteNotExistingElementErrorDisplayed() {
        viewModel.setNumberToDelete("1");
        viewModel.deleteElement();

        assertEquals(LeftistHeapViewModel.ERROR_VALUE_NOT_FIND, viewModel.getErrorText());
    }

    @Test
    public void canDeleteExistingElementFromHeap() {
        viewModel.setNumberToInsert("1");
        viewModel.setNumberToDelete("1");

        viewModel.insertElement();
        viewModel.deleteElement();

        assertEquals("[]", viewModel.getHeapContent());
    }

    @Test
    public void whenInputWrongValueToDeleteFieldDeleteButtonIsDisabled() {
        viewModel.setNumberToDelete("afc");

        assertFalse(viewModel.isDeleteButtonEnabled());
    }

    @Test
    public void whenInputWrongValueToDeleteFieldErrorDisplayed() {
        viewModel.setNumberToDelete("afc");

        assertEquals(LeftistHeapViewModel.ERROR_DELETE_WRONG_VALUE,
                        viewModel.getErrorText());
    }


}
