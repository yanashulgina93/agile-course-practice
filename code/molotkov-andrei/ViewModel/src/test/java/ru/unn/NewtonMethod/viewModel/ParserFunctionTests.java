package ru.unn.NewtonMethod.viewModel;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertTrue;


public class ParserFunctionTests {
    private ParserFunction parser;

    @Before
    public void initializeParser() {
        parser = new ParserFunction();
    }

    @Test
    public void canFindVariableAfterVariable() {
        assertFalse(parser.isCorrectFunction("x+xx="));
    }

    @Test
    public void canFindDigitAfterVariable() {
        assertFalse(parser.isCorrectFunction("x+x7="));
    }

    @Test
    public void canFindLeftBracketAfterVariable() {
        assertFalse(parser.isCorrectFunction("x+x(x-3)="));
    }

    @Test
    public void canFindVariableAfterDigit() {
        assertFalse(parser.isCorrectFunction("1+3x="));
    }

    @Test
    public void canFindLeftBracketAfterDigit() {
        assertFalse(parser.isCorrectFunction("x+3(5-x)="));
    }

    @Test
    public void canFindOperatorAfterOperator() {
        assertFalse(parser.isCorrectFunction("x++x="));
    }

    @Test
    public void canFindIRightBracketAfterOperator() {
        assertFalse(parser.isCorrectFunction("x*(x+7-)="));
    }

    @Test
    public void canFindOperatorAfterLeftBracket() {
        assertFalse(parser.isCorrectFunction("x+(/x+5)="));
    }

    @Test
    public void canFindVariableAfterRightBracket() {
        assertFalse(parser.isCorrectFunction("(x-5)x="));
    }

    @Test
    public void canFindDigitAfterRightBracket() {
        assertFalse(parser.isCorrectFunction("(x-5)5="));
    }

    @Test
    public void canFindIncorrectFirstCharacter() {
        assertFalse(parser.isCorrectFunction("*x-5="));
    }

    @Test
    public void canFindLeftBracketAfterRightBracket() {
        assertFalse(parser.isCorrectFunction("x+5)-3(="));
    }

    @Test
    public void canFindDifferentNumberBrackets() {
        assertFalse(parser.isCorrectFunction("x+x-((5-x)="));
    }

    @Test
    public void canFindRightBracketsMoreLeftBrackets() {
        assertFalse(parser.isCorrectFunction("x+x-((5-x)-5)+x*6)="));
    }

    @Test
    public void canFindIllegalCharacter() {
        assertFalse(parser.isCorrectFunction("x^x="));
    }

    @Test
    public void whenFunctionCorrect() {
        assertTrue(parser.isCorrectFunction("x*(x-(5-5)*10)*(x/(x-1)/(10*(5-x)))*1/(1-x)="));
    }
}
