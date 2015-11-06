package main.java.ru.unn.agile.LongArithmetic;

public class LongNumber {

    private static final int SCALE = 10;
    private int rank;
    private int[] value;

    public LongNumber() {
        this.rank = 1;
        this.value = new int[this.rank];
        this.value[0] = 0;
    }

    public LongNumber(final int number) {
        // TODO подправить формулу для разных scale
        if(number == 0) {
            this.rank = 1;
        } else {
            this.rank = (int) (Math.log10(number) + 1);
        }
        this.value = new int[this.rank];

        int intermediateNum = number;
        int newElement = -1;
        int divider = 1;
        int remain = -1;
        for (int j = this.rank - 1; j > -1; --j) {
            divider = (int) (Math.pow(SCALE, j));
            newElement = intermediateNum / divider;
            this.value[j] = newElement;
            remain = intermediateNum % divider;
            intermediateNum = remain;
        }
    }

    public LongNumber(final LongNumber copiedNum) {
        this.rank = copiedNum.rank;
        this.value = new int[this.rank];
        for (int i = 0; i < this.rank; ++i) {
            this.value[i] = copiedNum.value[i];
        }
    }

    public LongNumber add(final LongNumber lnNum) {
        LongNumber result = new LongNumber();
        int maxRank = 0;
        int newRank = 0;
        if (this.rank > lnNum.rank) {
            maxRank = this.rank;
        } else {
            maxRank = lnNum.rank;
        }
        newRank = maxRank;
        if (this.rank == lnNum.rank && this.value[this.rank - 1] + lnNum.value[this.rank - 1] >= SCALE) {
            newRank++;
        }
        result.rank = newRank;
        result.value = new int[result.rank];
        for(int i = 0; i < result.rank; ++i) {
            result.value[i] = 0;
        }

        int smallSum = 0;
        for (int i = 0; i < maxRank; ++i) {
            if(this.rank > i && lnNum.rank > i) {
                smallSum = this.value[i] + lnNum.value[i] + result.value[i];
            } else if(this.rank > i) {
                smallSum = this.value[i] + result.value[i];
            } else if(lnNum.rank > i) {
                smallSum = lnNum.value[i] + result.value[i];
            } else {
                smallSum = result.value[i];
            }
            if (smallSum >= SCALE) {
                result.value[i + 1] = 1;
                result.value[i] = smallSum % SCALE;
            } else {
                result.value[i] = smallSum;
            }
        }

        return result;
    }

    public LongNumber multiply(final LongNumber lnNum) {
        LongNumber result = new LongNumber();
        // result.value = this.value * lnNum.value;

        return result;
    }

    public boolean isEqual(final LongNumber lnNum) {
        boolean result = true;

        if(this.rank == lnNum.rank) {
            for(int i = 0; i < this.rank; ++i) {
                if (this.value[i] != lnNum.value[i]) {
                    result = false;
                }
            }
        } else {
            result = false;
        }

        return result;
    }

    public boolean isEqual(final int lnNum) {
        boolean result = true;
        LongNumber newNum = new LongNumber(lnNum);

        result = this.isEqual(newNum);

        return result;
    }

    public int getValue() {
        return 0;// this.value;
    }

    public void setValue(final int newValue) {
        // this.value = newValue;
    }
}
