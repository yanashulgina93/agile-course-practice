package ru.unn.agile.Polinom.viewmodel;

import org.junit.After;
import org.junit.Test;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.experimental.runners.Enclosed;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

@RunWith(Enclosed.class)
public class PolinomViewModelTests {
    public static class ArithmeticOperationsTest {

        @Before
        public void setUp() {
            PolinomViewModelTests.viewModel = new PolinomViewModel();
        }

        @After
        public void tearDown() {
            PolinomViewModelTests.viewModel = null;
        }

        @Test
        public void canSetDefaultValues() {
            assertEquals("", PolinomViewModelTests.viewModel.firstOperandProperty().get());
            assertEquals("", PolinomViewModelTests.viewModel.secondOperandProperty().get());
            assertEquals("", PolinomViewModelTests.viewModel.resultProperty().get());
        }

        @Test
        public void operationAddHasCorrectResult() {
            PolinomViewModelTests.viewModel.firstOperandProperty().set("2x^1 + 2.5");
            PolinomViewModelTests.viewModel.secondOperandProperty().set("1");

            PolinomViewModelTests.viewModel.operation("ADD");

            assertEquals("2.0x^1+3.5", PolinomViewModelTests.viewModel.resultProperty().get());
        }

        @Test
        public void operationSubstractHasCorrectResult() {
            PolinomViewModelTests.viewModel.firstOperandProperty().set("2.5x^1 + 1");
            PolinomViewModelTests.viewModel.secondOperandProperty().set("2");

            PolinomViewModelTests.viewModel.operation("SUBSTRACT");

            assertEquals("2.5x^1-1.0", PolinomViewModelTests.viewModel.resultProperty().get());
        }

        @Test
        public void operationMultiplyHasCorrectResult() {
            PolinomViewModelTests.viewModel.firstOperandProperty().set("2x^1 + 2");
            PolinomViewModelTests.viewModel.secondOperandProperty().set("-1");

            PolinomViewModelTests.viewModel.operation("MULTIPLY");

            assertEquals("-2.0x^1-2.0", PolinomViewModelTests.viewModel.resultProperty().get());
        }

        @Test
        public void operationDivideHasCorrectResult() {
            PolinomViewModelTests.viewModel.firstOperandProperty().set("2x^1 + 2");
            PolinomViewModelTests.viewModel.secondOperandProperty().set("1");

            PolinomViewModelTests.viewModel.operation("DIVIDE");

            assertEquals("2.0x^1+2.0", PolinomViewModelTests.viewModel.resultProperty().get());
        }

        @Test
        public void canSetCorrectMessageWhenDivideByZero() {
            PolinomViewModelTests.viewModel.firstOperandProperty().set("2x^1 + 2");
            PolinomViewModelTests.viewModel.secondOperandProperty().set("0");

            PolinomViewModelTests.viewModel.operation("DIVIDE");

            assertEquals("Divider can't be zero!", PolinomViewModelTests.viewModel.
                resultProperty().get());
        }

        @Test
        public void canSetCorrectMessageWhenDivideLargeDegreePolinom() {
            PolinomViewModelTests.viewModel.firstOperandProperty().set("2x^1 + 2");
            PolinomViewModelTests.viewModel.secondOperandProperty().set("3x^4 + 1");

            PolinomViewModelTests.viewModel.operation("DIVIDE");

            assertEquals("Divider's degree can't be large than dividend's!",
                    PolinomViewModelTests.viewModel.resultProperty().get());
        }
    }

    @RunWith(Parameterized.class)
    public static class IncorrectInputTest {
        public IncorrectInputTest(final String firstOperand, final String secondOperand,
            final String output) {
            this.firstOperand = firstOperand;
            this.secondOperand = secondOperand;
            this.output = output;
        }

        @Parameterized.Parameters
        public static List<Object[]> inputAndExpectedOutput() {
            return Arrays.asList(new Object[][] {
                {"", "NoPolinom", "Set Polinoms"},
                {"NoPolinom", "", "Set Polinoms"},
                {"", "", "Set Polinoms"},
                {"NoPolinom", "NoPolinom", "Incorrect Input"},
                {"1", "NoPolinom", "Incorrect Input"},
                {"NoPolinom", "1", "Incorrect Input"},
                {"1x^ 1", "2", "Incorrect Input"},
                {"1 x^1", "2", "Incorrect Input"},
                {"1y^1", "2", "Incorrect Input"},
                {"1x^1 * 2", "2", "Incorrect Input"},
                {"1x^2.0", "2", "Incorrect Input"}
            });
        }

        @Before
        public void setUp() {
            PolinomViewModelTests.viewModel = new PolinomViewModel();
            PolinomViewModelTests.viewModel.firstOperandProperty().set(firstOperand);
            PolinomViewModelTests.viewModel.secondOperandProperty().set(secondOperand);
        }

        @After
        public void tearDown() {
            PolinomViewModelTests.viewModel = null;
        }

        @Test
        public void canRecognizeIncorrectInput() {
            PolinomViewModelTests.viewModel.operation("DIVIDE");

            assertEquals(output, PolinomViewModelTests.viewModel.resultProperty().get());
        }

        private String firstOperand;
        private String secondOperand;
        private String output;
    }

    private static PolinomViewModel viewModel;
}
