package ru.unn.agile.BitArray.core;

public class BitArrayOrOperation implements IBitArrayOperation {
    @Override
    public BitArray doOperation(final BitArray firstArr, final BitArray secondArr) {
        int size1 = firstArr.getSize();
        BitArray resArr = new BitArray(size1);
        for (int i = 0; i < size1; i++) {
            resArr.setBit(i, firstArr.getBit(i) | secondArr.getBit(i));
        }
        return resArr;
    }
}
