package ru.unn.agile.QuadraticEquationSolver.viewmodel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.unn.agile.QuadraticEquationSolver.Model.QuadraticEquationSolver;

import static org.junit.Assert.*;

public class ViewModelTests {
    private ViewModel viewModel;

    @Before
    public void setUp() {
        viewModel = new ViewModel();
    }

    @After
    public void tearDown() {
        viewModel = null;
    }

    @Test
    public void byDefaultSolveButtonIsDesabled() {
        assertFalse(viewModel.isButtonSolveEnabled());
    }
    @Test
    public void whenEnteredAllCoefficients_SolveButtonIsEnabled() {
        setCoefficients("1", "2", "3");
        assertTrue(viewModel.isButtonSolveEnabled());
    }

    private void setCoefficients(String a, String b, String c) {
        viewModel.coeffAProperty().set(a);
        viewModel.coeffBProperty().set(b);
        viewModel.coeffCProperty().set(c);
    }

}
