package ru.unn.agile.NewtonMethod;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class CanConvertToPolishNotation {
    private ConverterToPolishNotation converter;
    private final String infixFormula;
    private final String postfixFormula;

    public CanConvertToPolishNotation(final String infixFormula,
                                      final String postfixFormula) {
        this.infixFormula = infixFormula;
        this.postfixFormula = postfixFormula;
    }

    @Parameterized.Parameters
    public static List<Object[]> getFormulas() {
        return Arrays.asList(new Object[][] {
                {"=", "="},
                {"x+x=", "xx+="},
                {"x-x=", "xx-="},
                {"x*x=", "xx*="},
                {"x/x=", "xx/="},
                {"3+x=", "3x+="},
                {"48*x=", "48x*="},
                {"x*x+x=", "xx*x+="},
                {"5*x*x+2*x-3=", "5x*x*2x*+3-="},
                {"(3+x)*x=", "3x+x*="},
                {"x+(x-3)*y-35/(x+y)=", "xx3-y*+35xy+/-="}
        });
    }

    @Before
    public void initializeConverter() {
        converter = new ConverterToPolishNotation();
    }

    @Test
    public void canConvertPrimitiveOperationsToPolishNotation() {
        String polishNotation = converter.convert(infixFormula);
        assertEquals(postfixFormula, polishNotation);
    }
}
