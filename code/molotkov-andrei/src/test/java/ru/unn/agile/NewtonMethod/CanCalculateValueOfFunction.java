package ru.unn.agile.NewtonMethod;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class CanCalculateValueOfFunction {
    private final String testFunc;
    private final double testPoint;
    private final double expected;
    private double delta = 0.000001;

    public CanCalculateValueOfFunction(final String testFunc,
                                       final double testPoint,
                                       final double expected) {
        this.testFunc = testFunc;
        this.testPoint = testPoint;
        this.expected = expected;
    }

    @Parameterized.Parameters
    public static List<Object[]> testDataFunc() {
        return Arrays.asList(new Object[][]{
                {"=", 0.0, 0.0},
                {"3=", 0.0, 3},
                {"x=", 6.0, 6.0},
                {"3+x=", 4.0, 7.0},
                {"3*x=", 4.0, 12.0},
                {"x*x*x+3*x*x-2*x-5=", 1.0, -3.0},
                {"5/x=", 0.00000000001, 5.0E11},
                {"x/3=", 10.0, 3.333333}
        });
    }

    @Test
    public void canCalculateFunctionValue() {
        Function function = new Function(testFunc);
        double valueFunction = function.calculate(testPoint);
        assertEquals(expected, valueFunction, delta);
    }
}
