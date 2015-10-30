package ru.unn.agile.arabicroman;

import org.junit.Test;
import static org.junit.Assert.*;

public class numeralConverterTest {

    @Test
    public void canConvertZeroToEmptyString(){
        numeralConverter conv = new numeralConverter();
        String romanNumber = conv.arabicToRoman(0);
        assertEquals("", romanNumber);
    }

    @Test
    public void canConvertEmptyStringToZero(){
        numeralConverter conv = new numeralConverter();
        assertEquals(0, conv.romanToArabic(""));
    }

    @Test
    public void canConvertOneToI(){
        numeralConverter conv = new numeralConverter();
        assertEquals("I", conv.arabicToRoman(1));
    }


    @Test
    public void canConvertThirtyToXXX(){
        numeralConverter conv = new numeralConverter();
        assertEquals("XXX", conv.arabicToRoman(30));
    }

    @Test
    public void canConvertTwelveToXII(){
        numeralConverter conv = new numeralConverter();
        assertEquals("XII", conv.arabicToRoman(12));
    }

     @Test
    public void primarySymbolsArrayCreatesCorrectly(){
        primarySymbols testSymb = new primarySymbols();
        assertTrue(testSymb.pairs.get(3).getKey()==50 &&  testSymb.pairs.get(3).getValue() =="L");

    }

    @Test
    public void canConvertEightToVIII() {
        numeralConverter conv = new numeralConverter();
        assertEquals("VIII", conv.arabicToRoman(8));
    }

    @Test
    public void canConvertFirtyToL() {
        numeralConverter conv = new numeralConverter();
        assertEquals("L", conv.arabicToRoman(50));
    }

    @Test
    public void canConvertNinetyToXC() {
        numeralConverter conv = new numeralConverter();
        assertEquals("XC", conv.arabicToRoman(90));
    }

    @Test
    public void canConvert2014ToMMXIV() {
        numeralConverter conv = new numeralConverter();
        assertEquals("MMXIV", conv.arabicToRoman(2014));
    }









}
