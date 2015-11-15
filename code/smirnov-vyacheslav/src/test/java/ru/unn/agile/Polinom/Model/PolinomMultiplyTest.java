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
public class PolinomMultiplyTest {
    private Polinom first;
    private Polinom second;
    private final double[] firstCoefficients;
    private final double[] secondCoefficients;
    private final double[] expectedCoefficients;

    public PolinomMultiplyTest(final double[] firstCoefficients,
        final double[] secondCoefficients, final double[] expectedCoefficients) {
        this.firstCoefficients = firstCoefficients;
        this.secondCoefficients = secondCoefficients;
        this.expectedCoefficients = expectedCoefficients;
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
        first = new Polinom(firstCoefficients);
        second = new Polinom(secondCoefficients);
    }

    @Test
    public void canMultiplyPolinoms() {
        first.multiply(second);

        Assert.assertTrue(Arrays.equals(first.getCoefficients(), expectedCoefficients));
    }
}
