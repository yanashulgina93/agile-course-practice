package test.java.ru.unn.agile.Polinom.Model;

import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.List;

import main.java.ru.unn.agile.Polinom.Model.*;

@RunWith(Parameterized.class)
public class PolinomSubtractTest {
    private Polinom first;
    private Polinom second;
    private final double[] firstCoefficients;
    private final double[] secondCoefficients;
    private final double[] expectedCoefficients;

    public PolinomSubtractTest(final double[] firstCoefficients,
        final double[] secondCoefficients, final double[] expectedCoefficients) {
        this.firstCoefficients = firstCoefficients;
        this.secondCoefficients = secondCoefficients;
        this.expectedCoefficients = expectedCoefficients;
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
            {new double[]{5.0, 6.0, -7.0}, new double[]{5.0, 7.0, 7.0}, new double[]{0.0, -1.0}},
            {new double[]{-5.0, 6.0, -7.0}, new double[]{-5.0, 6.0, -7.0}, new double[]{0.0}}
        });
    }

    @Before
    public void initPolinoms() {
        first = new Polinom(firstCoefficients);
        second = new Polinom(secondCoefficients);
    }

    @Test
    public void canSubtractPolinoms() {
        first.subtract(second);

        Assert.assertTrue(Arrays.equals(first.getCoefficients(), expectedCoefficients));
    }
}
