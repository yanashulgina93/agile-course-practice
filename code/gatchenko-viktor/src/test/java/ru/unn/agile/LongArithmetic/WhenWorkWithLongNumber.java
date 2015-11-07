package test.java.ru.unn.agile.LongArithmetic;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import main.java.ru.unn.agile.LongArithmetic.LongNumber;

public class WhenWorkWithLongNumber {

    private LongNumber lnNum1;
    private LongNumber lnNum2;
    private LongNumber result;

    @Test
    public void add1To0() {
        initializePairLongNumber(1, 0);
        result = lnNum1.add(lnNum2);

        boolean isEqual = result.isEqual(1);
        assertEquals(true, isEqual);
    }

    @Test
    public void add122To78() {
        initializePairLongNumber(122, 78);
        result = lnNum1.add(lnNum2);

        boolean isEqual = result.isEqual(200);
        assertEquals(true, isEqual);
    }

    @Test
    public void longAdd() {
        lnNum1 = new LongNumber("10000000000011111111111");
        lnNum2 = new LongNumber("12345678998799999999999");
        result = lnNum1.add(lnNum2);

        String strNum = result.convertToString();
        assertEquals("22345678998811111111110", strNum);
    }

    @Test
    public void multiply0And5() {
        initializePairLongNumber(0, 5);
        result = lnNum1.multiply(lnNum2);

        boolean isEqual = result.isEqual(0);
        assertEquals(true, isEqual);
    }

    @Test
    public void multiply23000And43() {
        initializePairLongNumber(23099, 43);
        result = lnNum1.multiply(lnNum2);

        boolean isEqual = result.isEqual(993257);
        assertEquals(true, isEqual);
    }

    @Test
    public void multiply23And135() {
        initializePairLongNumber(23, 135);
        result = lnNum1.multiply(lnNum2);

        boolean isEqual = result.isEqual(3105);
        assertEquals(true, isEqual);
    }

    @Test
    public void longMultiply() {
        lnNum1 = new LongNumber("1000000000001");
        lnNum2 = new LongNumber("123456789987");
        result = lnNum1.multiply(lnNum2);

        boolean isEqual = result.isEqual(new LongNumber("123456789987123456789987"));
        assertEquals(true, isEqual);
    }

    @Test
    public void compare0And1() {
        initializePairLongNumber(0, 1);
        boolean isEqual = lnNum1.isEqual(lnNum2);

        assertEquals(false, isEqual);
    }

    @Test
    public void compare11And111() {
        initializePairLongNumber(11, 111);
        boolean isEqual = lnNum1.isEqual(lnNum2);

        assertEquals(false, isEqual);
    }

    @Test
    public void compare2222222And2222222() {
        initializePairLongNumber(2222222, 2222222);
        boolean isEqual = lnNum1.isEqual(lnNum2);

        assertEquals(true, isEqual);
    }

    private void initializePairLongNumber(final int number1, final int number2) {
        lnNum1 = new LongNumber(number1);
        lnNum2 = new LongNumber(number2);
    }
}
