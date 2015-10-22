package ru.unn.agile.LengthConvertor.Model;

import org.junit.Test;
import static org.junit.Assert.*;

public class FormatterTest {

    @Test (expected = IllegalArgumentException.class)
    public void throwsOnNegativeDouble() {
        double x = -1.0;
        Formatter.formatPositiveDouble(x);
    }

    @Test
    public void canFormatToIntegerWith6Digits() {
        double x = 123456.123;
        assertEquals("123456.12", Formatter.formatPositiveDouble(x));
    }
}
