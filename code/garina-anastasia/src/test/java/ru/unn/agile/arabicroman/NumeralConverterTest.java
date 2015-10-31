package ru.unn.agile.arabicroman;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class NumeralConverterTest {
    private NumeralConverter conv;

    @Before public void setUp() {
       conv = new NumeralConverter();
    }

    @Test
    public void canConvertZeroToEmptyString() {
        String romanNumber = conv.convertArabicToRoman(0);
        assertEquals("", romanNumber);
    }

    @Test
    public void canConvertEmptyStringToZero() {
        assertEquals(0, conv.convertRomanToArabic(""));
    }

    @Test
    public void canConvertOneToI() {
        assertEquals("I", conv.convertArabicToRoman(1));
    }

    @Test
    public void canConvertThirtyToXXX() {
        assertEquals("XXX", conv.convertArabicToRoman(30));
    }

    @Test
    public void canConvertTwelveToXII() {
        assertEquals("XII", conv.convertArabicToRoman(12));
    }

    @Test
    public void canConvertEightToVIII() {
        assertEquals("VIII", conv.convertArabicToRoman(8));
    }

    @Test
    public void canConvertFirtyToL() {
        assertEquals("L", conv.convertArabicToRoman(50));
    }

    @Test
    public void canConvertNinetyToXC() {
        assertEquals("XC", conv.convertArabicToRoman(90));
    }

    @Test
    public void canConvert2014ToMMXIV() {
        assertEquals("MMXIV", conv.convertArabicToRoman(2014));
    }

    @Test
    public void canConvertIToOne() {
        assertEquals(1, conv.convertRomanToArabic("I"));
    }

    @Test
    public void canConvertMTo1000() {
        assertEquals(1000, conv.convertRomanToArabic("M"));
    }
    @Test
    public void canConvertCCCXXXIIITo333() {
        assertEquals(333, conv.convertRomanToArabic("CCCXXXIII"));
    }

    @Test
    public void canConvertIXTo9() {
        assertEquals(9, conv.convertRomanToArabic("IX"));
    }

    @Test
    public void canConvertCMXIVTo914() {
        assertEquals(914, conv.convertRomanToArabic("CMXIV"));
    }

    @Test (expected = IllegalArgumentException.class)
    public void throwOnNumberBiggerThanMax() {
        conv.convertArabicToRoman(5000);
    }

    @Test (expected = IllegalArgumentException.class)
    public void throwOnNegativeNumber() {
        conv.convertArabicToRoman(-1);
    }

    @Test (expected = IllegalArgumentException.class)
    public void throwOnInvalidSymbolInRomanNumber() {
        conv.convertRomanToArabic("KLN");
    }

    @Test (expected = IllegalArgumentException.class)
    public void throwOnIncorrectOrderOfSymbolsInRomanNumber() {
        conv.convertRomanToArabic("IMC");
    }

    @Test (expected = IllegalArgumentException.class)
    public void throwOnIncorrectWritingOfRomanNumber() {
        conv.convertRomanToArabic("VXVI");
    }

    @Test
    public void canConvertWithNullAsArgument() {
        assertEquals(0, conv.convertRomanToArabic(null));
    }

}
