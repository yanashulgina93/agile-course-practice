package ru.unn.agile.TreeStructure.viewmodel;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
public class TreeViewModelTests {
    private TreeViewModel viewModel;

    @Before
    public void initializeViewModel() {
        viewModel = new TreeViewModel();
    }

    @Test
    public void byDefault_DoButtonIsDisabled() {
        assertFalse(viewModel.isDoButtonEnabled());
    }

    @Test
    public void byDefault_DataTextFieldIsDisabled() {
        assertFalse(viewModel.isDataTextFieldEnabled());
    }

    @Test
    public void whenSelectedTypeOperationsAndInputKey_DoButtonIsEnabled() {
        viewModel.setKey("100");
        assertTrue(viewModel.isDoButtonEnabled());
    }

    @Test
    public void whenKeyClear_DoButtonIsDisabled() {
        viewModel.setKey("100");
        viewModel.setKey("");
        assertFalse(viewModel.isDoButtonEnabled());
    }

    @Test
    public void canSelectTypeOperation() {
        viewModel.setOperation(TreeViewModel.Operation.TRUNCATE);
        assertEquals(TreeViewModel.Operation.TRUNCATE, viewModel.getOperation());
    }

    @Test
    public void canInsertNewNode() {
        viewModel.setKey("100");
        viewModel.doOperation();
        assertEquals("Node was added successfully", viewModel.getErrorMessage());
    }

    @Test
    public void canInsertAlreadyAddedNode() {
        viewModel.setKey("100");
        viewModel.doOperation();
        viewModel.doOperation();
        assertEquals("Node with this key already exists.", viewModel.getErrorMessage());
    }

    @Test
    public void canSearchExistElement() {
        viewModel.setKey("100");
        viewModel.setData("qwerty");
        viewModel.doOperation();
        viewModel.setOperation(TreeViewModel.Operation.SEARCH);
        viewModel.doOperation();
        assertEquals("qwerty", viewModel.getSearchedData());
    }

    @Test
    public void canSearchNotExistElement() {
        viewModel.setKey("100");
        viewModel.doOperation();
        viewModel.setKey("10");
        viewModel.setOperation(TreeViewModel.Operation.SEARCH);
        viewModel.doOperation();
        assertEquals("Element not found", viewModel.getErrorMessage());
    }

}
