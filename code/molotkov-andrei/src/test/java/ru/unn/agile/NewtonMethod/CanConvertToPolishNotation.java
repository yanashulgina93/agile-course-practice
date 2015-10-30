package ru.unn.agile.NewtonMethod;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CanConvertToPolishNotation {

    @Test
    public void canConvertEmptyStringToPolishNotation()
    {
        InfixExpressionToPolishExpressionConverter converter = new InfixExpressionToPolishExpressionConverter();
        String polishNotation = converter.convert("=");
        assertEquals("=", polishNotation);
    }

    @Test
    public void canConvertAddToPolishNotation()
    {
        InfixExpressionToPolishExpressionConverter converter = new InfixExpressionToPolishExpressionConverter();
        String polishNotation = converter.convert("x+x=");
        assertEquals("xx+=", polishNotation);
    }

    @Test
    public void canConvertMultiplyToPolishNotation()
    {
        InfixExpressionToPolishExpressionConverter converter = new InfixExpressionToPolishExpressionConverter();
        String polishNotation = converter.convert("x*x=");
        assertEquals("xx*=", polishNotation);
    }

    @Test
    public void canConvertDifferenceToPolishNotation()
    {
        InfixExpressionToPolishExpressionConverter converter = new InfixExpressionToPolishExpressionConverter();
        String polishNotation = converter.convert("x-x=");
        assertEquals("xx-=", polishNotation);
    }

    @Test
    public void canConvertDivisionToPolishNotation()
    {
        InfixExpressionToPolishExpressionConverter converter = new InfixExpressionToPolishExpressionConverter();
        String polishNotation = converter.convert("x/x=");
        assertEquals("xx/=", polishNotation);
    }

    @Test
    public void canConvertMultiplyAndAddToPolishNotation()
    {
        InfixExpressionToPolishExpressionConverter converter = new InfixExpressionToPolishExpressionConverter();
        String polishNotation = converter.convert("x*x+x=");
        assertEquals("xx*x+=", polishNotation);
    }

    @Test
    public void canConvertDigitAndAlphaToPolishNotation()
    {
        InfixExpressionToPolishExpressionConverter converter = new InfixExpressionToPolishExpressionConverter();
        String polishNotation = converter.convert("3-x=");
        assertEquals("3x-=", polishNotation);
    }

    @Test
    public void canConvertSomeDigitsAndAlphaToPolishNotation()
    {
        InfixExpressionToPolishExpressionConverter converter = new InfixExpressionToPolishExpressionConverter();
        String polishNotation = converter.convert("39*x=");
        assertEquals("39x*=", polishNotation);
    }

    @Test
    public void canConvertPolinomToPolishNotation()
    {
        InfixExpressionToPolishExpressionConverter converter = new InfixExpressionToPolishExpressionConverter();
        String polishNotation = converter.convert("5*x*x+2*x-3=");
        assertEquals("5x*x*2x*+3-=", polishNotation);
    }
}
