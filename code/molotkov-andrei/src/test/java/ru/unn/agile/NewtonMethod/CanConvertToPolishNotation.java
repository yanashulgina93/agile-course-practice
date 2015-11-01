package ru.unn.agile.NewtonMethod;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class CanConvertToPolishNotation {
    private final String testData;
    private final String expected;

    public CanConvertToPolishNotation(final String testData, final String expected) {
        this.testData = testData;
        this.expected = expected;
    }

    @Parameterized.Parameters
    public static List<Object[]> primitiveOperations() {
        return Arrays.asList(new Object[][] {
                {"x+x=", "xx+="},
                {"x-x=", "xx-="},
                {"x*x=", "xx*="},
                {"x/x=", "xx/="},
                {"3+x=", "3x+="},
                {"48*x=", "48x*="}
        });
    }

    @Test
    public void canConvertEmptyStringToPolishNotation() {
        ConverterToPolishNotation converter = new ConverterToPolishNotation();
        String polishNotation = converter.convert("=");
        assertEquals("=", polishNotation);
    }

    @Test
    public void canConvertPrimitiveOperationsToPolishNotation() {
        ConverterToPolishNotation converter = new ConverterToPolishNotation();
        String polishNotation = converter.convert(testData);
        assertEquals(expected, polishNotation);
    }

    @Test
    public void canConvertMultiplyAndAddToPolishNotation() {
        ConverterToPolishNotation converter = new ConverterToPolishNotation();
        String polishNotation = converter.convert("x*x+x=");
        assertEquals("xx*x+=", polishNotation);
    }

    @Test
    public void canConvertPolinomToPolishNotation() {
        ConverterToPolishNotation converter = new ConverterToPolishNotation();
        String polishNotation = converter.convert("5*x*x+2*x-3=");
        assertEquals("5x*x*2x*+3-=", polishNotation);
    }

    @Test
    public void canConvertWithBracketToPolishNotation() {
        ConverterToPolishNotation converter = new ConverterToPolishNotation();
        String polishNotation = converter.convert("(3+x)*x=");
        assertEquals("3x+x*=", polishNotation);
    }

    @Test
    public void canConvertAllOperationsToPolishNotation() {
        ConverterToPolishNotation converter = new ConverterToPolishNotation();
        String polishNotation = converter.convert("x+(x-3)*y-35/(x+y)=");
        assertEquals("xx3-y*+35xy+/-=", polishNotation);
    }
}
