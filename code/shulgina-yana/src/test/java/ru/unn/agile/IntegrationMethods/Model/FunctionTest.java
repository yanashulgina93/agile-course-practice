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
    public void canGetValue() {
        Function function = new Function("exp(x)");
        double x = 1.5;
        double correctValue = 4.481689;

        assertEquals(correctValue, function.getValue(x), delta);
    }
}
