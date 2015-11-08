package ru.unn.agile.LongArithmetic;

import java.math.BigDecimal;

public class LongNumber {

    public static final int[] UNDEFINED_VALUE = null;
    public static final LongNumber UNDEFINED = null;

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

    public LongNumber(final char[] chars) {
        this.rank = chars.length;
        this.value = new int[rank];
        this.fillValue(chars, false);
    }

    public LongNumber(final String string) {
        this.rank = string.length();
        this.value = new int[rank];
        this.fillValue(string, true);
    }

    private void fillValue(final Object str, final boolean isString) {
        char charElement;
        int newElement;

        for (int i = 0, j = this.rank - 1; i < this.rank; ++i, --j) {
            if (isString) {
                charElement = ((String) str).charAt(i);
            } else {
                charElement = ((char[]) str)[i];
            }
            newElement = Character.getNumericValue(charElement);
            int maxCharNumberIndex = 9;
            if (newElement <= maxCharNumberIndex) {
                this.value[j] = Character.getNumericValue(charElement);
            } else {
                this.value = null;
                this.rank = 0;
                break;
            }
        }
    }

    public LongNumber(final LongNumber copiedNum) {
        this.rank = copiedNum.rank;
        this.value = new int[this.rank];
        if (copiedNum.value == LongNumber.UNDEFINED_VALUE) {
            this.value = LongNumber.UNDEFINED_VALUE;
        } else {
            for (int i = 0; i < this.rank; ++i) {
                this.value[i] = copiedNum.value[i];
            }
        }
    }

    private int getIntRank(final int number) {
        int rank = 0;
        if (number == 0) {
            rank = 1;
        } else {
            rank = (int) (Math.log(number) / Math.log(SCALE) + 1);
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

    public LongNumber add(final LongNumber lnNum) {
        LongNumber result = new LongNumber();
        int maxRank = this.getMaxRank(this, lnNum);
        result.rank = maxRank + 1;
        result.value = new int[result.rank];

        for (int i = 0; i < result.rank; ++i) {
            result.value[i] = 0;
        }
        result.summarize(this, lnNum);
        result.deleteZero();

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

    private void deleteZero() {
        int lastElement = this.rank - 1;
        if (this.value[lastElement] == 0) {
            int[] newValue = new int[lastElement];
            for (int i = 0; i < lastElement; ++i) {
                newValue[i] = this.value[i];
            }
            this.value = newValue;
            this.rank -= 1;
        }
    }

    public LongNumber multiply(final LongNumber lnNum) {
        LongNumber result;

        String strNum = this.convertToString();
        BigDecimal firstMultiplier = new BigDecimal(strNum);
        strNum = lnNum.convertToString();
        BigDecimal secondMultiplier = new BigDecimal(strNum);

        BigDecimal bigResult = firstMultiplier.multiply(secondMultiplier);
        strNum = bigResult.toString();
        result = new LongNumber(strNum);

        return result;
    }

    public String convertToString() {
        String strNum = "";
        for (int i = 0, j = this.rank - 1; i < this.rank; ++i, --j) {
            int element = this.value[j];
            strNum += Integer.toString(element);
        }

        return strNum;
    }

    private int convertLongToInt() {
        int intNum = 0;

        for (int i = 0; i < this.rank; ++i) {
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

    public boolean isUndefined() {
        boolean result = false;
        if (this.value == LongNumber.UNDEFINED_VALUE) {
            result = true;
        }

        return result;
    }

    public void setValue(final int newValue) {
        LongNumber newNum = new LongNumber(newValue);
        this.rank = newNum.rank;
        this.value = newNum.value;
    }
}
