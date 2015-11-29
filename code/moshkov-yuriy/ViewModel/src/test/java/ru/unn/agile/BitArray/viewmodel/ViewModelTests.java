package ru.unn.agile.BitArray.viewmodel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ru.unn.agile.BitArray.viewmodel.ViewModel.Operation;

import static org.junit.Assert.*;

public class ViewModelTests {
    private ViewModel viewModel;

    @Before
    public void initializeViewModel() {
        viewModel = new ViewModel();
    }

    @Test
    public void isDoOperationNotEnabledInBeggining() {
        assertFalse(viewModel.isDoOperationEnabled());
    }

    @Test
    public void isInitArrayBtnNotEnabledInBeggining() {
        assertFalse(viewModel.isInitArrayEnabled());
    }

    @Test
    public void isInitArrayNotEnabledWhenInputEmptyString() {
        viewModel.setSizeArray("");
        assertFalse(viewModel.isInitArrayEnabled());
    }

    @Test
    public void isInitArrayNotEnabledWhenInputInvalidNumber() {
        viewModel.setSizeArray("aaaa");
        assertFalse(viewModel.isInitArrayEnabled());
    }

    @Test
    public void isInitArrayEnabledWhenInputValidNumber() {
        viewModel.setSizeArray("11");
        assertTrue(viewModel.isInitArrayEnabled());
    }

    @Test
    public void isFirstBitArrayNotNullWhenInitArray() {
        viewModel.setSizeArray("5");
        viewModel.initArray();
        assertNotNull(viewModel.gitFirstBitArray());
    }

    @Test
    public void isFirstBitArraySizeEquals5WhenInputSize5() {
        viewModel.setSizeArray("5");
        viewModel.initArray();
        assertTrue(viewModel.gitFirstBitArray().getSize() == 5);
    }

    @Test
    public void isSecondBitArraySizeEquals5WhenInputSize5() {
        viewModel.setSizeArray("5");
        viewModel.initArray();
        assertTrue(viewModel.getSecondBitArray().getSize() == 5);
    }

    @Test
    public void isResultBitArraySizeEquals5WhenInputSize5() {
        viewModel.setSizeArray("5");
        viewModel.initArray();
        assertTrue(viewModel.getResultBitArray().getSize() == 5);
    }

    @Test
    public void isDoOperationEnabledWhenInitArrays() {
        viewModel.setSizeArray("5");
        viewModel.initArray();
        assertTrue(viewModel.isDoOperationEnabled());
    }

    @Test
    public void isDefaultOperationOr() {
        assertEquals(Operation.OR, viewModel.getOperation());
    }

    @Test
    public void canSetOperation() {
        viewModel.setOperation(Operation.AND);
        assertEquals(Operation.AND, viewModel.getOperation());
    }
}
