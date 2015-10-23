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

    @Test
    public void canConvertToString() {
        Length length = new Length(1, LengthUnit.Meter);
        assertEquals("1.0 Meter", length.toString());
    }

    @Test
    public void canConvertFloatingNumberToString() {
        Length length = new Length(2.45, LengthUnit.Yard);
        assertEquals("2.45 Yard", length.toString());
    }

    @Test
    public void canConvertScientificFormatToString() {
        Length length = new Length(1.2456e-2, LengthUnit.KMeter);
        assertEquals("0.01 KMeter", length.toString());
    }
}
