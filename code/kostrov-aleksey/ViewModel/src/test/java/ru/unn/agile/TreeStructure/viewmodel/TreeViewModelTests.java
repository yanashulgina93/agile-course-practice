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
        viewModel.setDataFromNode("qwerty");

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
    public void whenKeyTextFieldClearDoButtonIsDisabled() {
        viewModel.setKey("");

        assertFalse(viewModel.isDoButtonEnabled());
    }

    @Test
    public void canSelectTypeOperation() {
        viewModel.setOperation("Truncate");

        assertEquals(TreeViewModel.Operation.TRUNCATE, viewModel.getOperation());
    }

    @Test
    public void canInsertNewNode() {
        assertEquals("Success", viewModel.getErrorMessage());
    }

    @Test
    public void displayErrorMessageWhenTryingToAddExistingElement() {
        viewModel.doOperation();

        assertEquals("Node with this key already exists.", viewModel.getErrorMessage());
    }

    @Test
    public void canSearchExistElement() {
        viewModel.setOperation("Search");

        viewModel.doOperation();

        assertEquals("qwerty", viewModel.getSearchedData());
    }

    @Test
    public void displayErrorMessageWhenTryingToSearchNotExistElement() {
        viewModel.setKey("10");
        viewModel.setOperation("Search");

        viewModel.doOperation();

        assertEquals("Element not found", viewModel.getErrorMessage());
    }

    @Test
    public void canTruncateAnExistKey() {
        viewModel.setOperation("Truncate");
        viewModel.doOperation();

        viewModel.setOperation("Search");
        viewModel.doOperation();

        assertEquals("Element not found", viewModel.getErrorMessage());
    }

    @Test
    public void displayErrorMessageWhenTryingToTruncateAnNotExistKey() {
        viewModel.setKey("101");
        viewModel.setOperation("Truncate");

        viewModel.doOperation();

        assertEquals("Element not found", viewModel.getErrorMessage());
    }

    @Test
    public void displayErrorMessageWhenKeyNotComparable() {
        viewModel.setKey("qwe");

        viewModel.doOperation();

        assertEquals("Key is not comparable", viewModel.getErrorMessage());
    }

    @Test
    public void canReturnNameCurrentOperation() {
        assertEquals("Insert", viewModel.getOperation().toString());
    }
}
