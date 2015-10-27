package ru.unn.agile.BitArray.core;

import java.util.Arrays;

public class BitArray {
    private final boolean[] arrBit;
    private final int size;

    public BitArray(final int size) {
        this.arrBit = new boolean[size];
        this.size = size;
    }

    public boolean getBit(final int index) {
        return arrBit[index];
    }

    public void setBit(final int index, final boolean value) {
        arrBit[index] = value;
    }

    public void setAll(final boolean value) {
        Arrays.fill(this.arrBit, value);
    }

    public BitArray and(final BitArray arr) {
        BitArray res = new BitArray(this.size);
        if (arr.getSize() == this.size) {
            for (int i = 0; i < size; i++) {
                res.setBit(i, arr.getBit(i) & this.getBit(i));
            }
        } else {
            throw new BitArrayDifferentSizeException("Different size of arrays");
        }
        return res;
    }

    public BitArray or(final BitArray arr) {
        BitArray res = new BitArray(this.size);
        if (arr.getSize() == this.size) {
            for (int i = 0; i < size; i++) {
                res.setBit(i, arr.getBit(i) | this.getBit(i));
            }
        } else {
            throw new BitArrayDifferentSizeException("Different size of arrays");
        }
        return res;
    }

    public BitArray xor(final BitArray arr) {
        BitArray res = new BitArray(this.size);
        if (arr.getSize() == this.size) {
            for (int i = 0; i < size; i++) {
                res.setBit(i, arr.getBit(i) ^ this.getBit(i));
            }
        } else {
            throw new BitArrayDifferentSizeException("Different size of arrays");
        }
        return res;
    }

    public BitArray not() {
        BitArray res = new BitArray(this.size);
        for (int i = 0; i < this.size; i++) {
            res.setBit(i, !this.getBit(i));
        }
        return res;
    }

    public boolean[] getArrBit() {
        return this.arrBit;
    }

    public int getSize() {
        return this.size;
    }
}
