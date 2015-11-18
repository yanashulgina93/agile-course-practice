package test.java.ru.unn.agile.Polinom.Model;

import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.experimental.runners.Enclosed;

import java.util.Arrays;
import java.util.List;

import main.java.ru.unn.agile.Polinom.Model.*;

@RunWith(Enclosed.class)
public class PolinomArithmeticOperationsTest {
    @RunWith(Parameterized.class)
    public static class PolinomAddTest {
        public PolinomAddTest(final double[] firstCoefficients,
            final double[] secondCoefficients, final double[] expectedCoefficients) {
            PolinomArithmeticOperationsTest.firstCoefficients = firstCoefficients;
            PolinomArithmeticOperationsTest.secondCoefficients = secondCoefficients;
            PolinomArithmeticOperationsTest.expectedCoefficients = expectedCoefficients;
        }

        @Parameterized.Parameters
        public static List<Object[]> addInputAndExpected() {
            return Arrays.asList(new Object[][] {
                {new double[]{0.0}, new double[]{0.0}, new double[]{0.0}},
                {new double[]{5.0, 15.0}, new double[]{7.0, 1.0}, new double[]{12.0, 16.0}},
                {new double[]{15.0, -8.0}, new double[]{-2.0, 7.0}, new double[]{13.0, -1.0}},
                {new double[]{2.0, 9.0, 0.0}, new double[]{0.0, 1.0, 4.0},
                new double[]{2.0, 10.0, 4.0}},
                {new double[]{12.64, 0.15, 1007.0009}, new double[]{-12.64, 60.85, 1002.101},
                new double[]{0.0, 61.0, 2009.1019}},
                {new double[]{5.0, 6.0, 7.0}, new double[]{5.0, 6.0, -7.0},
                new double[]{10.0, 12.0}},
                {new double[]{5.0, -6.0, -7.0}, new double[]{-5.0, 6.0, 7.0}, new double[]{0.0}}
            });
        }

        @Before
        public void initPolinoms() {
            PolinomArithmeticOperationsTest.first = new Polinom(firstCoefficients);
            PolinomArithmeticOperationsTest.second = new Polinom(secondCoefficients);
        }

        @Test
        public void canAddPolinoms() {
            PolinomArithmeticOperationsTest.first.add(PolinomArithmeticOperationsTest.second);

            Assert.assertTrue(Arrays.equals(PolinomArithmeticOperationsTest.first.getCoefficients(),
            expectedCoefficients));
        }
    }

    @RunWith(Parameterized.class)
    public static class PolinomSubtractTest {
        public PolinomSubtractTest(final double[] firstCoefficients,
            final double[] secondCoefficients, final double[] expectedCoefficients) {
            PolinomArithmeticOperationsTest.firstCoefficients = firstCoefficients;
            PolinomArithmeticOperationsTest.secondCoefficients = secondCoefficients;
            PolinomArithmeticOperationsTest.expectedCoefficients = expectedCoefficients;
        }

        @Parameterized.Parameters
        public static List<Object[]> subtractInputAndExpected() {
            return Arrays.asList(new Object[][] {
                {new double[]{0.0}, new double[]{0.0}, new double[]{0.0}},
                {new double[]{7.0, 15.0}, new double[]{5.0, 1.0}, new double[]{2.0, 14.0}},
                {new double[]{15.0, -8.0}, new double[]{-2.0, 7.0}, new double[]{17.0, -15.0}},
                {new double[]{2.0, 9.0, 0.0}, new double[]{0.0, 1.0, 4.0},
                new double[]{2.0, 8.0, -4.0}},
                {new double[]{12.64, 0.15, 1007.0009}, new double[]{12.64, 60.85, 1002.0001},
                new double[]{0.0, -60.70, 5.0008}},
                {new double[]{5.0, 6.0, -7.0}, new double[]{5.0, 7.0, 7.0},
                new double[]{0.0, -1.0, -14.0}},
                {new double[]{-5.0, 6.0, -7.0}, new double[]{-5.0, 6.0, -7.0}, new double[]{0.0}}
            });
        }

        @Before
        public void initPolinoms() {
            PolinomArithmeticOperationsTest.first = new Polinom(firstCoefficients);
            PolinomArithmeticOperationsTest.second = new Polinom(secondCoefficients);
        }

        @Test
        public void canSubtractPolinoms() {
            PolinomArithmeticOperationsTest.first.subtract(PolinomArithmeticOperationsTest.second);

            Assert.assertTrue(Arrays.equals(PolinomArithmeticOperationsTest.first.getCoefficients(),
                expectedCoefficients));
        }
    }

    @RunWith(Parameterized.class)
    public static class PolinomMultiplyTest {
        public PolinomMultiplyTest(final double[] firstCoefficients,
            final double[] secondCoefficients, final double[] expectedCoefficients) {
            PolinomArithmeticOperationsTest.firstCoefficients = firstCoefficients;
            PolinomArithmeticOperationsTest.secondCoefficients = secondCoefficients;
            PolinomArithmeticOperationsTest.expectedCoefficients = expectedCoefficients;
        }

        @Parameterized.Parameters
        public static List<Object[]> multiplyInputAndExpected() {
            return Arrays.asList(new Object[][] {
                {new double[]{7.0, 15.0}, new double[]{0.0}, new double[]{0.0}},
                {new double[]{7.0, 15.0}, new double[]{4.0}, new double[]{28.0, 60.0}},
                {new double[]{7.0, 15.0}, new double[]{0.0, 4.0}, new double[]{0.0, 28.0, 60.0}},
                {new double[]{7.0, 15.0}, new double[]{4.0, 7.0}, new double[]{28.0, 109.0, 105.0}},
                {new double[]{0.0}, new double[]{0.0}, new double[]{0.0}},
                {new double[]{5.0, 6.0, -7.0}, new double[]{-5.0, 7.0},
                new double[]{-25.0, 5.0, 77.0, -49.0}},
                {new double[]{2.0, 0.0, -6.0}, new double[]{1.25, 0.5},
                new double[]{2.5, 1.0, -7.5, -3.0}}
            });
        }

        @Before
        public void initPolinoms() {
            PolinomArithmeticOperationsTest.first = new Polinom(firstCoefficients);
            PolinomArithmeticOperationsTest.second = new Polinom(secondCoefficients);
        }

        @Test
        public void canMultiplyPolinoms() {
            PolinomArithmeticOperationsTest.first.multiply(PolinomArithmeticOperationsTest.second);

            Assert.assertTrue(Arrays.equals(first.getCoefficients(), expectedCoefficients));
        }
    }

    private static Polinom first;
    private static Polinom second;
    private static double[] firstCoefficients;
    private static double[] secondCoefficients;
    private static double[] expectedCoefficients;
}
