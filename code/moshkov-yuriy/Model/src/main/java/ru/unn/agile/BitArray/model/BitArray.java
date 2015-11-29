package ru.unn.agile.BitArray.model;

import ru.unn.agile.BitArray.model.exception.BitArrayDifferentSizeException;

import java.util.Arrays;

public class BitArray {
    private final Boolean[] arrayBit;
    private final int size;

    public BitArray(final int size) {
        this.size = size;
        arrayBit = new Boolean[size];
        Arrays.fill(arrayBit, false);
    }

    public Boolean getBit(final int index) {
        return arrayBit[index];
    }

    public void setBit(final int index, final Boolean value) {
        if (value == null) {
            return;
        }
        arrayBit[index] = value;
    }

    public void setAll(final Boolean value) {
        Arrays.fill(arrayBit, value);
    }

    public BitArray and(final BitArray bitArray) {
        return doOperationWithArray(bitArray, new AndOperation());
    }

    public BitArray or(final BitArray bitArray) {
        return doOperationWithArray(bitArray, new OrOperation());
    }

    public BitArray xor(final BitArray bitArray) {
        return doOperationWithArray(bitArray, new XorOperation());

    }

    public BitArray not() {
        BitArray res = new BitArray(size);
        for (int i = 0; i < size; i++) {
            res.setBit(i, !getBit(i));
        }
        return res;
    }

    public Boolean[] getBitArray() {
        return arrayBit;
    }

    public int getSize() {
        return size;
    }

    private BitArray doOperationWithArray(final BitArray bitArray, final IOperation operation) {
        BitArray res;
        if (bitArray.getSize() == size) {
            res = new BitArray(size);
            for (int i = 0; i < size; i++) {
                res.setBit(i, operation.doOperation(getBit(i), bitArray.getBit(i)));
            }
        } else {
            throw new BitArrayDifferentSizeException("Different size of arrays");
        }
        return res;
    }

    private class XorOperation implements IOperation {
        @Override
        public Boolean doOperation(final Boolean first, final Boolean second) {
            return first ^ second;
        }
    }

    private class OrOperation implements IOperation {
        @Override
        public Boolean doOperation(final Boolean first, final Boolean second) {
            return first | second;
        }
    }

    private class AndOperation implements IOperation {
        @Override
        public Boolean doOperation(final Boolean first, final Boolean second) {
            return first & second;
        }
    }
}

