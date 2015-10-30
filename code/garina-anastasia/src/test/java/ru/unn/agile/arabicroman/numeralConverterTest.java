package ru.unn.agile.arabicroman;

import org.junit.Test;
import static org.junit.Assert.*;

public class NumeralConverterTest {

    @Test
    public void canConvertZeroToEmptyString() {
        NumeralConverter conv = new NumeralConverter();
        String romanNumber = conv.arabicToRoman(0);
        assertEquals("", romanNumber);
    }

    @Test
    public void canConvertEmptyStringToZero() {
        NumeralConverter conv = new NumeralConverter();
        assertEquals(0, conv.romanToArabic(""));
    }

    @Test
    public void canConvertOneToI() {
        NumeralConverter conv = new NumeralConverter();
        assertEquals("I", conv.arabicToRoman(1));
    }


    @Test
    public void canConvertThirtyToXXX() {
        NumeralConverter conv = new NumeralConverter();
        assertEquals("XXX", conv.arabicToRoman(30));
    }

    @Test
    public void canConvertTwelveToXII() {
        NumeralConverter conv = new NumeralConverter();
        assertEquals("XII", conv.arabicToRoman(12));
    }

    @Test
    public void canConvertEightToVIII() {
        NumeralConverter conv = new NumeralConverter();
        assertEquals("VIII", conv.arabicToRoman(8));
    }

    @Test
    public void canConvertFirtyToL() {
        NumeralConverter conv = new NumeralConverter();
        assertEquals("L", conv.arabicToRoman(50));
    }

    @Test
    public void canConvertNinetyToXC() {
        NumeralConverter conv = new NumeralConverter();
        assertEquals("XC", conv.arabicToRoman(90));
    }

    @Test
    public void canConvert2014ToMMXIV() {
        NumeralConverter conv = new NumeralConverter();
        assertEquals("MMXIV", conv.arabicToRoman(2014));
    }

    @Test
    public void canConvertIToOne() {
        NumeralConverter conv = new NumeralConverter();
        assertEquals(1, conv.romanToArabic("I"));
    }

    @Test
    public void canConvertMTo1000() {
        NumeralConverter conv = new NumeralConverter();
        assertEquals(1000, conv.romanToArabic("M"));
    }
    @Test
    public void canConvertCCCXXXIIITo333() {
        NumeralConverter conv = new NumeralConverter();
        assertEquals(333, conv.romanToArabic("CCCXXXIII"));
    }

    @Test
    public void canConvertIXTo9() {
        NumeralConverter conv = new NumeralConverter();
        assertEquals(9, conv.romanToArabic("IX"));
    }

    @Test
    public void canConvertCMXIVTo914() {
        NumeralConverter conv = new NumeralConverter();
        assertEquals(914, conv.romanToArabic("CMXIV"));
    }

    @Test (expected = IllegalArgumentException.class)
    public void throwOnIncorrectNumberForArabicToRomanConverter() {
        NumeralConverter conv = new NumeralConverter();
        conv.arabicToRoman(5000);
    }

    @Test (expected = IllegalArgumentException.class)
    public void throwOnInvalidSymbolInRomanNumber() {
        NumeralConverter conv = new NumeralConverter();
        conv.romanToArabic("KLN");
    }


    @Test (expected = IllegalArgumentException.class)
    public void throwOnIncorrectOrderOfSymbolsInRomanNumber() {
        NumeralConverter conv = new NumeralConverter();
        conv.romanToArabic("IMC");
    }

    @Test (expected = IllegalArgumentException.class)
    public void throwOnIncorrectWritingOfRomanNumber() {
        NumeralConverter conv = new NumeralConverter();
        conv.romanToArabic("VXVI");
    }

}
