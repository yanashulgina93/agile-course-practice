package ru.unn.agile.NewtonMethod;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CanConvertToPolishNotation {
    @Test
    public void canConvertEmptyStringToPolishNotation() {
        ConverterToPolishNotation converter = new ConverterToPolishNotation();
        String polishNotation = converter.convert("=");
        assertEquals("=", polishNotation);
    }

    @Test
    public void canConvertAddToPolishNotation() {
        ConverterToPolishNotation converter = new ConverterToPolishNotation();
        String polishNotation = converter.convert("x+x=");
        assertEquals("xx+=", polishNotation);
    }

    @Test
    public void canConvertMultiplyToPolishNotation() {
        ConverterToPolishNotation converter = new ConverterToPolishNotation();
        String polishNotation = converter.convert("x*x=");
        assertEquals("xx*=", polishNotation);
    }

    @Test
    public void canConvertDifferenceToPolishNotation() {
        ConverterToPolishNotation converter = new ConverterToPolishNotation();
        String polishNotation = converter.convert("x-x=");
        assertEquals("xx-=", polishNotation);
    }

    @Test
    public void canConvertDivisionToPolishNotation() {
        ConverterToPolishNotation converter = new ConverterToPolishNotation();
        String polishNotation = converter.convert("x/x=");
        assertEquals("xx/=", polishNotation);
    }

    @Test
    public void canConvertMultiplyAndAddToPolishNotation() {
        ConverterToPolishNotation converter = new ConverterToPolishNotation();
        String polishNotation = converter.convert("x*x+x=");
        assertEquals("xx*x+=", polishNotation);
    }

    @Test
    public void canConvertDigitAndAlphaToPolishNotation() {
        ConverterToPolishNotation converter = new ConverterToPolishNotation();
        String polishNotation = converter.convert("3-x=");
        assertEquals("3x-=", polishNotation);
    }

    @Test
    public void canConvertSomeDigitsAndAlphaToPolishNotation() {
        ConverterToPolishNotation converter = new ConverterToPolishNotation();
        String polishNotation = converter.convert("39*x=");
        assertEquals("39x*=", polishNotation);
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
