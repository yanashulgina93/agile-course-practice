package ru.unn.agile.BitArray.viewmodel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ru.unn.agile.BitArray.viewmodel.ViewModel;

import static org.junit.Assert.*;

public class ViewModelTests {
    private ViewModel viewModel;

    @Before
    public void initializeViewModel() {
        viewModel = new ViewModel();
    }

    @Test
    public void isDoOperationNotEnabledInBeggining() {
        assertTrue(viewModel.isDoOperationEnabled() == false);
    }

    @Test
    public void isInitArrayBtnNotEnabledInBeggining() {
        assertTrue(viewModel.isInitArrayEnabled() == false);
    }

    @Test
    public void isSizeArrayNotEnabledWhenInputEmptyString() {
        viewModel.setSizeArray("");
        assertTrue(viewModel.isInitArrayEnabled() == false);
    }

    @Test
    public void isSizeArrayNotEnabledWhenInputInvalidNumber() {
        viewModel.setSizeArray("aaaa");
        assertTrue(viewModel.isInitArrayEnabled() == false);
    }

    @Test
    public void isSizeArrayEnabledWhenInputValidNumber() {
        viewModel.setSizeArray("11");
        assertTrue(viewModel.isInitArrayEnabled());
    }

    @Test
    public void isFirstBitArrayNullAtBeggining() {
        assertNull(viewModel.gitFirstBitArray());
    }

    @Test
    public void isFirstBitArrayNotNullWhenInitArray() {
        viewModel.setSizeArray("5");
        viewModel.initArray();
        assertNotNull(viewModel.gitFirstBitArray());
    }
}
