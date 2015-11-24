package ru.unn.agile.VolumesComputer.ViewModel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ComputerViewModelTest {
    private ComputerViewModel m_viewModel;

    @Before
    public void initialize() {
        m_viewModel = new ComputerViewModel();
    }
    @After
    public void free() {
        m_viewModel = null;
    }
    @Test
    public void failTest() {
        fail("turumburumtururum");
    }
}
