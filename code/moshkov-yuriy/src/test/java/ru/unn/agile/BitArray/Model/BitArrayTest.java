package ru.unn.agile.BitArray.core;

import org.junit.Test;
import org.junit.Before;
import java.util.Arrays;
import static org.junit.Assert.*;

public class BitArrayTest {

    private BitArray bitArray;

    @Before
    public void initializeBitArray() {
        bitArray = new BitArray(5);
    }

    @Test
    public void canCreateBitArrayWithSize() {
        assertNotNull(bitArray);
    }

    @Test
    public void canGetValueBit() {
        assertNotNull(bitArray.getBit(0));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void failWhenGetValueBitWithIndexOutOfRangeSize() {
        bitArray.getBit(6);
    }

    @Test()
    public void canSetValueBit() {
        bitArray.setBit(1, true);
        assertTrue(bitArray.getBit(1));
    }

    @Test()
    public void canNotSetNullValueBit() {
        bitArray.setBit(1, null);
        assertNotNull(bitArray.getBit(1));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void failWhenSetValueBitWithIndexOutOfRangeSize() {
        bitArray.setBit(-1, true);
    }

    @Test
    public void canSetAllValuesInArr() {
        Boolean[] allFalseArray = new Boolean[bitArray.getSize()];
        Arrays.fill(allFalseArray, false);
        bitArray.setAll(false);
        assertTrue(Arrays.equals(bitArray.getArrBit(), allFalseArray));
    }

    @Test
    public void canAndArrsWithSameSize() {
        BitArray arr2 = new BitArray(5);
        BitArray resArr;
        Boolean[] allFalseArray = new Boolean[bitArray.getSize()];
        Arrays.fill(allFalseArray, false);
        bitArray.setAll(false);
        arr2.setAll(true);
        resArr = bitArray.and(arr2);
        assertTrue(Arrays.equals(resArr.getArrBit(), allFalseArray));
    }

    @Test(expected = BitArrayDifferentSizeException.class)
    public void failWhenAndArrsWithDifferentSize() {
        BitArray arr2 = new BitArray(6);
        BitArray resArr;
        resArr = bitArray.and(arr2);
    }

    @Test
    public void canOrArrsWithSameSize() {
        BitArray arr2 = new BitArray(5);
        BitArray resArr;
        Boolean[] allTrueArr = new Boolean[bitArray.getSize()];
        Arrays.fill(allTrueArr, true);
        bitArray.setAll(false);
        arr2.setAll(true);
        resArr = bitArray.or(arr2);
        assertTrue(Arrays.equals(resArr.getArrBit(), allTrueArr));
    }

    @Test(expected = BitArrayDifferentSizeException.class)
    public void failWhenOrArrsWithDifferentSize() {
        BitArray arr2 = new BitArray(6);
        BitArray resArr;
        resArr = bitArray.or(arr2);
    }

    @Test
    public void canXorArrsWithSameSize() {
        BitArray arr2 = new BitArray(5);
        BitArray resArr;
        Boolean[] allFalseArray = new Boolean[bitArray.getSize()];
        Arrays.fill(allFalseArray, false);
        bitArray.setAll(true);
        arr2.setAll(true);
        resArr = bitArray.xor(arr2);
        assertTrue(Arrays.equals(resArr.getArrBit(), allFalseArray));
    }

    @Test(expected = BitArrayDifferentSizeException.class)
    public void failWhenXorArrsWithDifferentSize() {
        BitArray arr2 = new BitArray(6);
        BitArray resArr;
        resArr = bitArray.xor(arr2);
    }

    @Test
    public void canNotArr() {
        BitArray resArr;
        Boolean[] allTrueArr = new Boolean[bitArray.getSize()];
        Arrays.fill(allTrueArr, true);
        bitArray.setAll(false);
        resArr = bitArray.not();
        assertTrue(Arrays.equals(resArr.getArrBit(), allTrueArr));
    }
}
