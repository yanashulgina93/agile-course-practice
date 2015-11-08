package ru.unn.agile.LongArithmetic;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class WhenWorkWithLongNumber {

    private LongNumber lnNum1;
    private LongNumber lnNum2;
    private LongNumber result;
    private String strNum;

    @Test
    public void add1To0() {
        initializePairLongNumber(1, 0);
        result = lnNum1.add(lnNum2);

        strNum = result.convertToString();
        assertEquals("1", strNum);
    }

    @Test
    public void add122To78() {
        initializePairLongNumber(122, 78);
        result = lnNum1.add(lnNum2);

        strNum = result.convertToString();
        assertEquals("200", strNum);
    }

    @Test
    public void addMinus1To2() {
        initializePairLongNumber(-1, 2);
        result = lnNum1.add(lnNum2);

        strNum = result.convertToString();
        assertEquals("1", strNum);
    }

    @Test
    public void longAdd() {
        lnNum1 = new LongNumber("10000000000011111111111");
        lnNum2 = new LongNumber("12345678998799999999999");
        result = lnNum1.add(lnNum2);

        strNum = result.convertToString();
        assertEquals("22345678998811111111110", strNum);
    }

    @Test
    public void multiply0And5() {
        initializePairLongNumber(0, 5);
        result = lnNum1.multiply(lnNum2);

        strNum = result.convertToString();
        assertEquals("0", strNum);
    }

    @Test
    public void multiply23000And43() {
        initializePairLongNumber(23099, 43);
        result = lnNum1.multiply(lnNum2);

        strNum = result.convertToString();
        assertEquals("993257", strNum);
    }

    @Test
    public void multiply23And135() {
        initializePairLongNumber(23, 135);
        result = lnNum1.multiply(lnNum2);

        strNum = result.convertToString();
        assertEquals("3105", strNum);
    }

    @Test
    public void longMultiply() {
        lnNum1 = new LongNumber("1000000000001");
        lnNum2 = new LongNumber("123456789987");
        result = lnNum1.multiply(lnNum2);

        strNum = result.convertToString();
        assertEquals("123456789987123456789987", strNum);
    }

    @Test
    public void compare0And1() {
        initializePairLongNumber(0, 1);
        boolean isEqual = lnNum1.equals(lnNum2);

        assertEquals(false, isEqual);
    }

    @Test
    public void compare11And111() {
        initializePairLongNumber(11, 111);
        boolean isEqual = lnNum1.equals(lnNum2);

        assertEquals(false, isEqual);
    }

    @Test
    public void compare2222222And2222222() {
        initializePairLongNumber(2222222, 2222222);
        boolean isEqual = lnNum1.equals(lnNum2);

        assertEquals(true, isEqual);
    }

    @Test
    public void convert0ToString() {
        lnNum1 = new LongNumber();
        strNum = lnNum1.convertToString();

        assertEquals("0", strNum);
    }

    @Test
    public void convert98123476ToString() {
        lnNum1 = new LongNumber(98123476);
        strNum = lnNum1.convertToString();

        assertEquals("98123476", strNum);
    }

    @Test
    public void convertLongToString() {
        lnNum1 = new LongNumber("19283746519283746519283746519283745619");
        strNum = lnNum1.convertToString();

        assertEquals("19283746519283746519283746519283745619", strNum);
    }

    @Test
    public void defaultNumberIsNotUndefined() {
        lnNum1 = new LongNumber();

        boolean isUndefined = lnNum1.isUndefined();
        assertEquals(false, isUndefined);
    }

    @Test
    public void longNumberIsNotUndefined() {
        lnNum1 = new LongNumber("123456789987654321");

        boolean isUndefined = lnNum1.isUndefined();
        assertEquals(false, isUndefined);
    }

    private void initializePairLongNumber(final int number1, final int number2) {
        lnNum1 = new LongNumber(number1);
        lnNum2 = new LongNumber(number2);
    }
}
