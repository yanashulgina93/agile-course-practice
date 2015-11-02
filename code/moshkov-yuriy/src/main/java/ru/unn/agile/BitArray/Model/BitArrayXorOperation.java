package ru.unn.agile.BitArray.core;

public class BitArrayXorOperation implements IBitArrayOperation {
    @Override
    public BitArray doOperation(final BitArray firstArr, final BitArray secondArr) {
        int size2 = firstArr.getSize();
        BitArray res1 = new BitArray(size2);
        for (int i = 0; i < size2; i++) {
            res1.setBit(i, firstArr.getBit(i) ^ secondArr.getBit(i));
        }
        return res1;
    }
}
