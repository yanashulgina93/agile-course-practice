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
        first = new Polinom(new double {8.0, 2.0, 0.0, 1.0});
        second = new Polinom(new double {10.0, 0.0, -6.0, 5.0, -2.0});
    }

    @Test(expected = Exception.class)
    public void canNotDivideByZero() {
        first.divide(new Polinom());
    }

    @Test(expected = Exception.class)
    public void canNotDivideByLargeDegree() {
        first.divide(second);
    }

    @Test
    public canDivideByOne() {
        Polinom one = new Polinom(new double {1.0});
        first.divide(one);

        Assert.assertTrue(Arrays.equals(first.getCoefficients(), {8.0, 2.0, 0.0, 1.0}));
    }

    @Test
    public canDivideBySimplePolinom() {
        Polinom simplePolinom = new Polinom(new double {0.0, 5.0});
        first.divide(simplePolinom);

        Assert.assertTrue(Arrays.equals(first.getCoefficients(), {0.4, 0.0, 0.2}));
    }

    @Test
    public canDividePolinoms() {
        second.divide(first);

        Assert.assertTrue(Arrays.equals(first.getCoefficients(), {5.0, -2.0}));
    }
}
