package ru.unn.agile.NewtonMethod;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CanCalculateValueOfFunction {
    private double delta = 0.000001;

    @Test
    public void canCalculateEmptyFunction() {
        Function function = new Function("=");
        double valueFunction = function.calculate(0);
        assertEquals(0.0, valueFunction, delta);
    }

    @Test
    public void canCalculateConstFunction() {
        Function function = new Function("3=");
        double valueFunction = function.calculate(0);
        assertEquals(3, valueFunction, delta);
    }

    @Test
    public void canCalculateVariableFunction() {
        Function function = new Function("x=");
        double valueFunction = function.calculate(6);
        assertEquals(6, valueFunction, delta);
    }

    @Test
    public void canCalculateAddFunction() {
        Function function = new Function("3+x=");
        double valueFunction = function.calculate(4);
        assertEquals(7, valueFunction, delta);
    }

    @Test
    public void canCalculateMultFunction() {
        Function function = new Function("3*x=");
        double valueFunction = function.calculate(4);
        assertEquals(12, valueFunction, delta);
    }

    @Test
    public void canCalculateCubePolinomeFunction() {
        Function function = new Function("x*x*x+3*x*x-2*x-5=");
        double valueFunction = function.calculate(1);
        assertEquals(-3, valueFunction, delta);
    }

    @Test
    public void canCalculateDiving0Function() {
        Function function = new Function("5/x=");
        double valueFunction = function.calculate(0.00000000001);
        assertEquals(5.0E11, valueFunction, delta);
    }

    @Test
    public void canCalculateRealNumberFunction() {
        Function function = new Function("x/3=");
        double valueFunction = function.calculate(10);
        assertEquals(3.333333, valueFunction, delta);
    }
}
