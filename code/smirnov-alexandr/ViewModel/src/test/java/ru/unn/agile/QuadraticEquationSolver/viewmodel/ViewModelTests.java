package ru.unn.agile.QuadraticEquationSolver.viewmodel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

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
    public void areDefaultValuesCorrect() {
        assertEquals("", viewModel.coeffAProperty().get());
        assertEquals("", viewModel.coeffAProperty().get());
        assertEquals("", viewModel.coeffAProperty().get());
        assertEquals("", viewModel.resultProperty().get());
        assertEquals(Status.WAIT.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void whenEnteredAllCoefficientsStatusIsReady() {
        setCoefficients("1", "2", "3");
        assertEquals(Status.READY.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void canChangeStatusToBadDataWhenEnteredNotNumber() {
        viewModel.coeffAProperty().set("blabla");
        assertEquals(Status.BAD_DATA.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void buttonSolveEquationIsDisabledByDefault() {
        assertTrue(viewModel.solvingEquationDisabledProperty().get());
    }

    @Test
    public void whenNotAllCoefficientsEnteredStatusIsWait() {
        viewModel.coeffAProperty().set("4");
        viewModel.coeffCProperty().set("2");
        assertEquals(Status.WAIT.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void whenEnteredBadDataButtonSolveEquationIsEnabled() {
        setCoefficients("1", "2", "3");
        assertFalse(viewModel.solvingEquationDisabledProperty().get());
    }

    @Test
    public void whenEnteredBadDataButtonSolveEquationIsDisabled() {
        setCoefficients("1", "2", "dich");
        assertTrue(viewModel.solvingEquationDisabledProperty().get());
    }

    @Test
    public void whenQuadraticEquationHasTwoRootsStatusIsSolved() {
        setCoefficients("1", "-5", "6");
        viewModel.solveQuadraticEquation();
        assertEquals(Status.SOLVED.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void whenQuadraticEquationHasOneRootStatusIsSolved() {
        setCoefficients("1", "-2", "1");
        viewModel.solveQuadraticEquation();
        assertEquals(Status.SOLVED.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void whenDiscriminantOfQuadraticEquationLessThenZeroStatusIsNoRoots() {
        setCoefficients("1", "2", "3");
        viewModel.solveQuadraticEquation();
        assertEquals(Status.NO_ROOTS.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void canGetCorrectSolutionOfQuadraticEquationWithTwoRoots() {
        setCoefficients("1", "-7", "6");
        viewModel.solveQuadraticEquation();
        assertEquals("1.0 6.0", viewModel.resultProperty().get());
    }

    @Test
    public void canGetCorrectSolutionOfQuadraticEquationWithOneRoot() {
        setCoefficients("1", "-4", "4");
        viewModel.solveQuadraticEquation();
        assertEquals("4.0", viewModel.resultProperty().get());
    }

    @Test
    public void whenDiscriminantOfQuadraticEquationLessThenZeroResultIsEmpty() {
        setCoefficients("1", "3", "5");
        viewModel.solveQuadraticEquation();
        assertEquals("empty", viewModel.resultProperty().get());
    }

    private void setCoefficients(String a, String b, String c) {
        viewModel.coeffAProperty().set(a);
        viewModel.coeffBProperty().set(b);
        viewModel.coeffCProperty().set(c);
    }
}
