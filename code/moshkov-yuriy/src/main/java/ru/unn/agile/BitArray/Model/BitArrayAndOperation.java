package ru.unn.agile.BitArray.core;

public class BitArrayAndOperation implements IBitArrayOperation {
    @Override
    public BitArray doOperation(final BitArray firstArr, final BitArray secondArr) {
        int size = firstArr.getSize();
        BitArray res = new BitArray(size);
        for (int i = 0; i < size; i++) {
            res.setBit(i, firstArr.getBit(i) & secondArr.getBit(i));
        }
        return res;
    }
}
