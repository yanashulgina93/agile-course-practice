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
    public void whenSelectedTypeOperationsAndInputKey() {
        viewModel.setKey("100");
        assertTrue(viewModel.isDoButtonEnabled());
    }
}
