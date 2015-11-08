package ru.unn.agile.LongArithmetic;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class TestConstructorsLongNumber {

    private LongNumber lnNum;

    @Test
    public void defaultLongNumber() {
        lnNum = new LongNumber();

        boolean isEqual = lnNum.equals(0);
        assertTrue(isEqual);
    }

    @Test
    public void int0ToLongNumber() {
        lnNum = new LongNumber(0);

        boolean isEqual = lnNum.equals(0);
        assertTrue(isEqual);
    }

    @Test
    public void int987654321ToLongNumber() {
        lnNum = new LongNumber(987654321);

        boolean isEqual = lnNum.equals(987654321);
        assertTrue(isEqual);
    }

    @Test
    public void int1000000000ToLongNumber() {
        lnNum = new LongNumber(1000000000);

        boolean isEqual = lnNum.equals(1000000000);
        assertTrue(isEqual);
    }

    @Test
    public void intMinus1ToLongNumber() {
        lnNum = new LongNumber(-1);

        boolean isEqual = lnNum.equals(-1);
        assertTrue(isEqual);
    }

    @Test
    public void intMinus112345ToLongNumber() {
        lnNum = new LongNumber(-112345);

        boolean isEqual = lnNum.equals(-112345);
        assertTrue(isEqual);
    }

    @Test
    public void chars1aToLongNumber() {
        char[] chars = { '1', 'a' };
        lnNum = new LongNumber(chars);

        boolean isUndefined = lnNum.isUndefined();
        assertTrue(isUndefined);
    }

    @Test
    public void chars123456789ToLongNumber() {
        char[] chars = { '1', '2', '3', '4', '5', '6', '7', '8', '9' };
        lnNum = new LongNumber(chars);

        boolean isEqual = lnNum.equals(123456789);
        assertTrue(isEqual);
    }

    @Test
    public void charsMinus123ToLongNumber() {
        char[] chars = { '-', '1', '2', '3'};
        lnNum = new LongNumber(chars);

        boolean isEqual = lnNum.equals(-123);
        assertTrue(isEqual);
    }


    @Test
    public void copyDefaultToLongNumber() {
        LongNumber copied = new LongNumber();
        lnNum = new LongNumber(copied);

        boolean isEqual = lnNum.equals(copied);
        assertTrue(isEqual);
    }

    @Test
    public void copyToLongNumberToLongNumber() {
        LongNumber copied = new LongNumber(12345);
        lnNum = new LongNumber(copied);

        boolean isEqual = lnNum.equals(copied);
        assertTrue(isEqual);
    }

    @Test
    public void copyUndefinedLongNumberToLongNumber() {
        char[] chars = { '1', 'a' };
        LongNumber copied = new LongNumber(chars);
        lnNum = new LongNumber(copied);

        boolean isEqual = lnNum.equals(copied);
        assertTrue(isEqual);
    }

    @Test
    public void string101ToLongNumber() {
        String string = "101";
        lnNum = new LongNumber(string);

        boolean isEqual = lnNum.equals(101);
        assertTrue(isEqual);
    }

    @Test
    public void stringMinus101ToLongNumber() {
        String string = "-101";
        lnNum = new LongNumber(string);

        boolean isEqual = lnNum.equals(-101);
        assertTrue(isEqual);
    }

    @Test
    public void longStringToLongNumber() {
        String string = "111222333444555666777888999";
        char[] chars = { '1', '1', '1', '2', '2', '2', '3', '3', '3', '4', '4', '4', '5', '5', '5', '6', '6', '6', '7',
                '7', '7', '8', '8', '8', '9', '9', '9' };
        lnNum = new LongNumber(string);
        LongNumber charNumber = new LongNumber(chars);

        boolean isEqual = lnNum.equals(charNumber);
        assertTrue(isEqual);
    }

    @Test
    public void string0ToLongNumber() {
        String string = "0";
        lnNum = new LongNumber(string);

        boolean isEqual = lnNum.equals(0);
        assertTrue(isEqual);
    }
}
