package ru.unn.agile.TreeStructure.viewmodel;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
public class TreeViewModelTests {
    private TreeViewModel viewModel, viewDefaultModel;

    @Before
    public void initializeViewModel() {
        viewModel = new TreeViewModel();
        viewModel.setKey("100");
        viewModel.setData("qwerty");
        viewModel.doOperation();
    }

    @Test
    public void byDefaultDoButtonIsDisabled() {
        viewDefaultModel = new TreeViewModel();
        assertFalse(viewDefaultModel.isDoButtonEnabled());
    }

    @Test
    public void byDefaultDataTextFieldIsEnabled() {
        assertTrue(viewModel.isDataTextFieldEnabled());
    }

    @Test
    public void whenSelectedTypeOperationsAndInputKeyDoButtonIsEnabled() {
        assertTrue(viewModel.isDoButtonEnabled());
    }

    @Test
    public void whenKeyClearDoButtonIsDisabled() {
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
        assertEquals("Node was added successfully", viewModel.getErrorMessage());
    }

    @Test
    public void canInsertAlreadyAddedNode() {
        viewModel.doOperation();
        assertEquals("Node with this key already exists.", viewModel.getErrorMessage());
    }

    @Test
    public void canSearchExistElement() {
        viewModel.setOperation(TreeViewModel.Operation.SEARCH);
        viewModel.doOperation();
        assertEquals("qwerty", viewModel.getSearchedData());
    }

    @Test
    public void canSearchNotExistElement() {
        viewModel.setKey("10");
        viewModel.setOperation(TreeViewModel.Operation.SEARCH);
        viewModel.doOperation();
        assertEquals("Element not found", viewModel.getErrorMessage());
    }

    @Test
    public void canTruncateAnExistKey() {
        viewModel.setOperation(TreeViewModel.Operation.TRUNCATE);
        viewModel.doOperation();
        viewModel.setOperation(TreeViewModel.Operation.SEARCH);
        viewModel.doOperation();
        assertEquals("Element not found", viewModel.getErrorMessage());
    }

    @Test
    public void canTruncateAnNotExistKey() {
        viewModel.setKey("101");
        viewModel.setOperation(TreeViewModel.Operation.TRUNCATE);
        viewModel.doOperation();
        assertEquals("It is not possible, element not found", viewModel.getErrorMessage());
    }

    @Test
    public void canDoOperationWhenInputKeyNotCorrect() {
        viewModel.setKey("qwe");
        viewModel.doOperation();
        assertEquals("Key is not correct", viewModel.getErrorMessage());
    }

}
