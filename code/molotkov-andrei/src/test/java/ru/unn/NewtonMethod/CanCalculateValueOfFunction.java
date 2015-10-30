package ru.unn.NewtonMethod;

import org.junit.Test;
import ru.unn.agile.NewtonMethod.Function;

import static org.junit.Assert.assertEquals;

public class CanCalculateValueOfFunction {
    private double delta = 0.000001;

    @Test
    public void CanCalculateEmptyFunction(){
        Function function = new Function("=");
        double valueFunction = function.calculate(0);
        assertEquals(0.0, valueFunction, delta);
    }

    @Test
    public void CanCalculateConstFunction(){
        Function function = new Function("3=");
        double valueFunction = function.calculate(0);
        assertEquals(3, valueFunction, delta);
    }

    @Test
    public void CanCalculateVariableFunction(){
        Function function = new Function("x=");
        double valueFunction = function.calculate(6);
        assertEquals(6, valueFunction, delta);
    }

    @Test
    public void CanCalculateAddFunction(){
        Function function = new Function("3+x=");
        double valueFunction = function.calculate(4);
        assertEquals(7, valueFunction, delta);
    }

    @Test
    public void CanCalculateMultFunction(){
        Function function = new Function("3*x=");
        double valueFunction = function.calculate(4);
        assertEquals(12, valueFunction, delta);
    }

    @Test
    public void CanCalculateCubePolinomeFunction(){
        Function function = new Function("x*x*x+3*x*x-2*x-5=");
        double valueFunction = function.calculate(1);
        assertEquals(-3, valueFunction, delta);
    }

    @Test
    public void CanCalculateDiving_0_Function(){
        Function function = new Function("x/0=");
        double valueFunction = function.calculate(1);
        assertEquals(0, valueFunction, delta);
    }

    @Test
    public void CanCalculateRealNumberFunction(){
        Function function = new Function("x/3=");
        double valueFunction = function.calculate(10);
        assertEquals(3.333333, valueFunction, delta);
    }
}
