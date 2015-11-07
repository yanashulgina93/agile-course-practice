package test.java.ru.unn.agile.LongArithmetic;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import main.java.ru.unn.agile.LongArithmetic.LongNumber;

public class TestConstructorsLongNumber {

    private LongNumber lnNum;

    @Test
    public void defaultLongNumber() {
        lnNum = new LongNumber();

        boolean isEqual = lnNum.isEqual(0);
        assertEquals(true, isEqual);
    }

    @Test
    public void int0ToLongNumber() {
        lnNum = new LongNumber(0);

        boolean isEqual = lnNum.isEqual(0);
        assertEquals(true, isEqual);
    }

    @Test
    public void int987654321ToLongNumber() {
        lnNum = new LongNumber(987654321);

        boolean isEqual = lnNum.isEqual(987654321);
        assertEquals(true, isEqual);
    }

    @Test
    public void int1000000000ToLongNumber() {
        lnNum = new LongNumber(1000000000);

        boolean isEqual = lnNum.isEqual(1000000000);
        assertEquals(true, isEqual);
    }

    @Test
    public void chars11ToLongNumber() {
        char[] chars = { '1', '1' };
        lnNum = new LongNumber(chars);

        boolean isEqual = lnNum.isEqual(11);
        assertEquals(true, isEqual);
    }

    @Test
    public void chars1aToLongNumber() {
        char[] chars = { '1', 'a' };
        lnNum = new LongNumber(chars);

        boolean isUndefined = lnNum.isUndefined();
        assertEquals(true, isUndefined);
    }

    @Test
    public void chars123456789ToLongNumber() {
        char[] chars = { '1', '2', '3', '4', '5', '6', '7', '8', '9' };
        lnNum = new LongNumber(chars);

        boolean isEqual = lnNum.isEqual(123456789);
        assertEquals(true, isEqual);
    }

    @Test
    public void copyDefaultToLongNumber() {
        LongNumber copied = new LongNumber();
        lnNum = new LongNumber(copied);

        boolean isEqual = lnNum.isEqual(copied);
        assertEquals(true, isEqual);
    }

    @Test
    public void copyToLongNumberToLongNumber() {
        LongNumber copied = new LongNumber(12345);
        lnNum = new LongNumber(copied);

        boolean isEqual = lnNum.isEqual(copied);
        assertEquals(true, isEqual);
    }

    @Test
    public void copyUndefinedLongNumberToLongNumber() {
        char[] chars = { '1', 'a' };
        LongNumber copied = new LongNumber(chars);
        lnNum = new LongNumber(copied);

        boolean isEqual = lnNum.isEqual(copied);
        assertEquals(true, isEqual);
    }

    @Test
    public void string101ToLongNumber() {
        String string = "101";
        lnNum = new LongNumber(string);

        boolean isEqual = lnNum.isEqual(101);
        assertEquals(true, isEqual);
    }

    @Test
    public void longStringToLongNumber() {
        String string = "111222333444555666777888999";
        char[] chars = { '1', '1', '1', '2', '2', '2', '3', '3', '3', '4', '4', '4', '5', '5', '5', '6', '6', '6', '7',
                '7', '7', '8', '8', '8', '9', '9', '9' };
        lnNum = new LongNumber(string);
        LongNumber charNumber = new LongNumber(chars);

        boolean isEqual = lnNum.isEqual(charNumber);
        assertEquals(true, isEqual);
    }

    @Test
    public void string0ToLongNumber() {
        String string = "0";
        lnNum = new LongNumber(string);

        boolean isEqual = lnNum.isEqual(0);
        assertEquals(true, isEqual);
    }
}
