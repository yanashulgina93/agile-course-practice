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
        this.rank = this.getIntRank(number);
        this.value = new int[this.rank];
        this.convetIntToLong(number);
    }

    private int getIntRank(final int number) {
        int rank = 0;
        if (number == 0) {
            rank = 1;
        } else {
            // TODO подправить формулу для разных scale
            rank = (int) (Math.log10(number) + 1);
        }

        return rank;
    }

    private void convetIntToLong(final int number) {
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
        int maxRank = this.getMaxRank(this, lnNum);
        result.rank = this.getSumRank(lnNum, maxRank);
        result.value = new int[result.rank];

        for (int i = 0; i < result.rank; ++i) {
            result.value[i] = 0;
        }
        result.summarize(this, lnNum);

        return result;
    }

    private int getMaxRank(final LongNumber lnNum1, final LongNumber lnNum2) {
        int maxRank = 0;
        if (lnNum1.rank > lnNum2.rank) {
            maxRank = lnNum1.rank;
        } else {
            maxRank = lnNum2.rank;
        }

        return maxRank;
    }

    private int getSumRank(final LongNumber lnNum, final int maxRank) {
        int sumRank = maxRank;
        if (this.rank == lnNum.rank && this.value[this.rank - 1] + lnNum.value[this.rank - 1] >= SCALE) {
            sumRank++;
        }

        return sumRank;
    }

    private void summarize(final LongNumber addendum1, final LongNumber addendum2) {
        int maxRank = this.getMaxRank(addendum1, addendum2);

        int smallSum = 0;
        for (int i = 0; i < maxRank; ++i) {
            if (addendum1.rank > i && addendum2.rank > i) {
                smallSum = addendum1.value[i] + addendum2.value[i] + this.value[i];
            } else if (addendum1.rank > i) {
                smallSum = addendum1.value[i] + this.value[i];
            } else if (addendum2.rank > i) {
                smallSum = addendum2.value[i] + this.value[i];
            } else {
                smallSum = this.value[i];
            }

            if (smallSum >= SCALE) {
                this.value[i + 1] = 1;
                this.value[i] = smallSum % SCALE;
            } else {
                this.value[i] = smallSum;
            }
        }
    }

    public LongNumber multiply(final LongNumber lnNum) {
        LongNumber result = new LongNumber();
        int count = lnNum.convertLongToInt();
        for(int i = 0; i < count; ++i) {
            result = result.add(this);
        }

        return result;
    }

    private int convertLongToInt() {
        int intNum = 0;

        for(int i = 0; i < this.rank; ++i) {
            intNum += this.value[i] * Math.pow(SCALE, i);
        }

        return intNum;
    }

    public boolean isEqual(final LongNumber lnNum) {
        boolean result = true;

        if (this.rank == lnNum.rank) {
            for (int i = 0; i < this.rank; ++i) {
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

    public void setValue(final int newValue) {
        LongNumber newNum = new LongNumber(newValue);
        this.rank = newNum.rank;
        this.value = newNum.value;
    }
}
