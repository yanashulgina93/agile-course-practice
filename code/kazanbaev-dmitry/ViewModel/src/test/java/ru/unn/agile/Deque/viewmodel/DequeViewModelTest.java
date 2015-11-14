package ru.unn.agile.Deque.viewmodel;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DequeViewModelTest {
    private DequeViewModel viewModel;

    @Before
    public void initializeDequeViewModel() {
        viewModel = new DequeViewModel();
    }

    @Test
    public void byDefaultPushFrontButtonIsDisabled() {
        assertFalse(viewModel.isPushFrontButtonEnabled());
    }

    @Test
    public void byDefaultPushBackButtonIsDisabled() {
        assertFalse(viewModel.isPushBackButtonEnabled());
    }

    @Test
    public void byDefaultPopFrontButtonIsDisabled() {
        assertFalse(viewModel.isPopFrontButtonEnabled());
    }

    @Test
    public void byDefaultPopBackButtonIsDisabled() {
        assertFalse(viewModel.isPopBackButtonEnabled());
    }

    @Test
    public void whenEnteredNumberPushFrontButtonIsEnabled() {
        viewModel.setInputNumber("12");

        assertTrue(viewModel.isPushFrontButtonEnabled());
    }

    @Test
    public void whenEnteredNumberPushBackButtonIsDisabled() {
        viewModel.setInputNumber("12");

        assertTrue(viewModel.isPushBackButtonEnabled());
    }
}
