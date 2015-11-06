package main.java.ru.unn.agile.LongArithmetic;

public class LongNumber {

    public int value;

    public LongNumber() {
        this.value = 0;
    }

    public LongNumber(final int number) {
        this.value = number;
    }

    public LongNumber(final LongNumber copiedNum) {
        this.value = copiedNum.value;
    }

    public LongNumber add(final LongNumber lnNum) {
        LongNumber result = new LongNumber();
        result.value = this.value + lnNum.value;

        return result;
    }

    public LongNumber multiply(final LongNumber lnNum) {
        LongNumber result = new LongNumber();
        result.value = this.value * lnNum.value;

        return result;
    }

    public boolean isEqual(final LongNumber lnNum) {
        boolean result = true;

        if (this.value != lnNum.value) {
            result = false;
        }

        return result;
    }
}
