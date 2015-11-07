package test.java.ru.unn.agile.LongArithmetic;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import main.java.ru.unn.agile.LongArithmetic.LongNumber;

public class TestConstructorsLongNumber {

    private LongNumber lnNum;

    @Test
    public void chars11ToLongNumber() {
        char[] chars = {'1','1'};
        lnNum = new LongNumber(chars);

        boolean isEqual = lnNum.isEqual(11);
        assertEquals(true, isEqual);
    }

    @Test
    public void chars1aToLongNumber() {
        char[] chars = {'1','a'};
        lnNum = new LongNumber(chars);

        boolean isUndefined = lnNum.isUndefined();
        assertEquals(true, isUndefined);
    }

    @Test
    public void chars123456789ToLongNumber() {
        char[] chars = {'1','2','3','4','5','6','7','8','9'};
        lnNum = new LongNumber(chars);

        boolean isEqual = lnNum.isEqual(123456789);
        assertEquals(true, isEqual);
    }

}
