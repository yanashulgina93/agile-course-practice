package ru.unn.agile.BitArray.viewmodel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ru.unn.agile.BitArray.model.BitArray;
import ru.unn.agile.BitArray.viewmodel.ViewModel.Operation;

import static org.junit.Assert.*;

public class ViewModelTests {
    private ViewModel viewModel;
    private BitArray testBitArray;

    @Before
    public void initializeViewModel() {
        viewModel = new ViewModel();
        testBitArray = new BitArray(5);
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

    @Test
    public void canSetFirstBitArray() {
        viewModel.setFirstBitArray(testBitArray);
        assertEquals(testBitArray, viewModel.getFirstBitArray());
    }

    @Test
    public void canSetSecondBitArray() {
        viewModel.setSecondBitArray(testBitArray);
        assertEquals(testBitArray, viewModel.getSecondBitArray());
    }

    @Test
    public void canSetResultBitArray() {
        viewModel.setResultBitArray(testBitArray);
        assertEquals(testBitArray, viewModel.getResultBitArray());
    }

    @Test
    public void canDoOrOperation() {
        initArraysForOperations();
        viewModel.doOperation();
        assertNotNull(viewModel.getResultBitArray());
    }

    @Test
    public void canDoAndOperation() {
        viewModel.setOperation(Operation.AND);
        initArraysForOperations();
        viewModel.doOperation();
        assertNotNull(viewModel.getResultBitArray());
    }

    @Test
    public void canDoNotOperation() {
        viewModel.setOperation(Operation.NOT);
        initArraysForOperations();
        viewModel.doOperation();
        assertNotNull(viewModel.getResultBitArray());
    }

    @Test
    public void canDoXorOperation() {
        viewModel.setOperation(Operation.XOR);
        initArraysForOperations();
        viewModel.doOperation();
        assertNotNull(viewModel.getResultBitArray());
    }

    private void initArraysForOperations() {
        viewModel.setSizeArray("5");
        viewModel.initArray();
        BitArray firstBitArray = viewModel.getFirstBitArray();
        BitArray secondBitArray = viewModel.getSecondBitArray();
        firstBitArray.setAll(true);
        secondBitArray.setAll(false);
        viewModel.setFirstBitArray(firstBitArray);
        viewModel.setSecondBitArray(secondBitArray);
    }
}
