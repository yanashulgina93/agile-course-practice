package ru.unn.agile.BitArray.core;

import java.util.Arrays;

public class BitArray {
    private final Boolean[] arrBit;
    private final int size;

    public BitArray(final int size) {
        this.arrBit = new Boolean[size];
        Arrays.fill(this.arrBit, false);
        this.size = size;
    }

    public Boolean getBit(final int index) {
        return arrBit[index];
    }

    public void setBit(final int index, final Boolean value) {
        if (value == null) {
            return;
        }
        arrBit[index] = value;
    }

    public void setAll(final Boolean value) {
        Arrays.fill(this.arrBit, value);
    }

    public BitArray and(final BitArray arr) {
        return this.doOperationWithArr(arr, new BitArrayAndOperation());
    }

    public BitArray or(final BitArray arr) {
        return this.doOperationWithArr(arr, new BitArrayOrOperation());
    }

    public BitArray xor(final BitArray arr) {
        return this.doOperationWithArr(arr, new BitArrayAndOperation());
    }

    public BitArray not() {
        BitArray res = new BitArray(this.size);
        for (int i = 0; i < this.size; i++) {
            res.setBit(i, !this.getBit(i));
        }
        return res;
    }

    public Boolean[] getArrBit() {
        return this.arrBit;
    }

    public int getSize() {
        return this.size;
    }

    private BitArray doOperationWithArr(final BitArray arr, final IBitArrayOperation operation) {
        BitArray res;
        if (arr.getSize() == this.size) {
            // for (int i = 0; i < size; i++) {
            //     switch (op) {
            //         case '^':
            //             res.setBit(i, arr.getBit(i) ^ this.getBit(i));
            //             break;
            //         case '*':
            //             res.setBit(i, arr.getBit(i) & this.getBit(i));
            //             break;
            //         case '|':
            //             res.setBit(i, arr.getBit(i) | this.getBit(i));
            //             break;
            //         default:
            //             throw new BitArrayUnsupportedOperationException("Unsupported operation");
            //     }
            // }
            res = operation.doOperation(this, arr);
        } else {
            throw new BitArrayDifferentSizeException("Different size of arrays");
        }
        return res;
    }
}
