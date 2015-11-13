package test.java.ru.unn.agile.Polinom.Model;

import org.junit.Assert; 
import org.junit.Test;
import org.junit.Before; 

import java.util.Arrays;

import main.java.ru.unn.agile.Complex.Model.*;

public class PolinomDivideTest {
    private Polinom first;
    private Polinom second;

    @Before
    public viod initPolinoms() {
        double[] firstCoefficients = {8.0, 2.0, 0.0, 1.0};
        double[] secondCoefficients = {10.0, 0.0, -6.0, 5.0, -2.0};
        first = new Polinom(firstCoefficients);
        second = new Polinom(secondCoefficients);
    }

    @Test(expected = Exception.class)
    public void canNotDivideByZero() {
        Polinom zero = new Polinom();

        first.divide(zero);
    }

    @Test(expected = Exception.class)
    public void canNotDivideByLargeDegree() {
        first.divide(second);
    }

    @Test
    public void canDivideByOne() {
        double[] oneCoefficients = {1.0};
        double[] resultCoefficients = {8.0, 2.0, 0.0, 1.0};
        Polinom one = new Polinom(oneCoefficients);

        first.divide(one);

        Assert.assertTrue(Arrays.equals(first.getCoefficients(), resultCoefficients));
    }

    @Test
    public void canDivideBySimplePolinom() {
        double[] simplePolinomCoefficients = {0.0, 5.0};
        double[] resultCoefficients = {0.4, 0.0, 0.2};
        Polinom simplePolinom = new Polinom(simplePolinomCoefficients);

        first.divide(simplePolinom);

        Assert.assertTrue(Arrays.equals(first.getCoefficients(), resultCoefficients));
    }

    @Test
    public void canDividePolinoms() {
        double[] resultCoefficients = {5.0, -2.0};
        second.divide(first);

        Assert.assertTrue(Arrays.equals(first.getCoefficients(), resultCoefficients));
    }
}
