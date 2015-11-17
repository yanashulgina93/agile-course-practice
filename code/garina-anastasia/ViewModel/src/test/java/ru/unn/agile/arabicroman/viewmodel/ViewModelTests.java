package ru.unn.agile.arabicroman.viewmodel;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

public class ViewModelTests {
    private ArabicRomanConverterViewModel viewModel;


    @Before
    public void setUp() {
        viewModel = new ArabicRomanConverterViewModel();
    }

    @Test
    public void canSetDefaultValues() {
        assertTrue(viewModel.isReverseButtonEnabled());
        assertFalse(viewModel.isConvertButtonEnabled());
    }

    @Test
    public void whenNumberEnteredConvertButtonEnabled() {
        viewModel.setInputNumber("1");

        assertTrue(viewModel.isConvertButtonEnabled());
    }
}
