package ru.unn.agile.arabicroman;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class NumeralConverterParametrizedTest {
    private int inputNumber;
    private String expectedRomanNumber;

    public NumeralConverterParametrizedTest(final int inputNumber,
                                            final String expectedRomanNumber) {
        this.inputNumber = inputNumber;
        this.expectedRomanNumber = expectedRomanNumber;
    }

@Parameterized.Parameters
    public static Collection testedNumbers() {
        return Arrays.asList(new Object[][] {
                {19, "XIX"},
                {49, "XLIX"},
                {99, "XCIX"},
                {499, "CDXCIX"}
        });
    }
    @Test
    public void canConvertGivenNumbersToRoman() {
        assertEquals(expectedRomanNumber, NumeralConverter.convert(inputNumber));
    }

}


