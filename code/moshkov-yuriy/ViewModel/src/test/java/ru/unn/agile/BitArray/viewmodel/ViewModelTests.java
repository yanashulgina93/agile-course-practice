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
}
