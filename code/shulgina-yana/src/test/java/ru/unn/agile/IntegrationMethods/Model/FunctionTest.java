package ru.unn.agile.IntegrationMethods.core;

import org.junit.Test;
import static org.junit.Assert.*;

public class FunctionTest {
    private final double delta = 0.001;

    @Test
    public void canCreateFunction() {
       Function function = new Function("x");

       assertNotNull(function);
    }

    @Test
    public void canGetValueWhenLinearFunction() {
        Function function = new Function("x");
        double x = 1.5;
        double analyticalFunctionValue = 1.5;

        assertEquals(analyticalFunctionValue, function.getValue(x), delta);
    }

    @Test
    public void canGetValueWhenCosinus() {
        Function function = new Function("cos(x)");
        double x = Math.PI / 3;
        double analyticalFunctionValue = 0.5;

        assertEquals(analyticalFunctionValue, function.getValue(x), delta);
    }

    @Test
    public void canGetValueWhenExponenta() {
        Function function = new Function("exp(x)");
        double x = 1.5;
        double standartFunctionValue = Math.exp(1.5);

        assertEquals(standartFunctionValue, function.getValue(x), delta);
    }
}
