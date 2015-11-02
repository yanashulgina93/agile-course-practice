package ru.unn.agile.BitArray.core;

import org.junit.Test;
import org.junit.Before;
import java.util.Arrays;
import static org.junit.Assert.*;

public class BitArrayTest {

    private BitArray bitArr;

    @Before
    public void initializeBitArray() {
        bitArr = new BitArray(5);
    }

    @Test
    public void canCreateBitArrayWithSize() {
        assertNotNull(bitArr);
    }

    @Test
    public void canGetValueBit() {
        assertNotNull(bitArr.getBit(0));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void failWhenGetValueBitWithIndexOutOfRangeSize() {
        bitArr.getBit(6);
    }

    @Test()
    public void canSetValueBit() {
        bitArr.setBit(1, true);
        assertTrue(bitArr.getBit(1));
    }

    @Test()
    public void canNotSetNullValueBit() {
        bitArr.setBit(1, null);
        assertNotNull(bitArr.getBit(1));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void failWhenSetValueBitWithIndexOutOfRangeSize() {
        bitArr.setBit(-1, true);
    }

    @Test
    public void canSetAllValuesInArr() {
        Boolean[] allFalseArr = new Boolean[bitArr.getSize()];
        Arrays.fill(allFalseArr, false);
        bitArr.setAll(false);
        assertTrue(Arrays.equals(bitArr.getArrBit(), allFalseArr));
    }

    @Test
    public void canAndArrsWithSameSize() {
        BitArray arr2 = new BitArray(5);
        BitArray resArr;
        Boolean[] allFalseArr = new Boolean[bitArr.getSize()];
        Arrays.fill(allFalseArr, false);
        bitArr.setAll(false);
        arr2.setAll(true);
        resArr = bitArr.and(arr2);
        assertTrue(Arrays.equals(resArr.getArrBit(), allFalseArr));
    }

    @Test(expected = BitArrayDifferentSizeException.class)
    public void failWhenAndArrsWithDifferentSize() {
        BitArray arr2 = new BitArray(6);
        BitArray resArr;
        resArr = bitArr.and(arr2);
    }

    @Test
    public void canOrArrsWithSameSize() {
        BitArray arr2 = new BitArray(5);
        BitArray resArr;
        Boolean[] allTrueArr = new Boolean[bitArr.getSize()];
        Arrays.fill(allTrueArr, true);
        bitArr.setAll(false);
        arr2.setAll(true);
        resArr = bitArr.or(arr2);
        assertTrue(Arrays.equals(resArr.getArrBit(), allTrueArr));
    }

    @Test(expected = BitArrayDifferentSizeException.class)
    public void failWhenOrArrsWithDifferentSize() {
        BitArray arr2 = new BitArray(6);
        BitArray resArr;
        resArr = bitArr.or(arr2);
    }

    @Test
    public void canXorArrsWithSameSize() {
        BitArray arr2 = new BitArray(5);
        BitArray resArr;
        Boolean[] allFalseArr = new Boolean[bitArr.getSize()];
        Arrays.fill(allFalseArr, false);
        bitArr.setAll(true);
        arr2.setAll(true);
        resArr = bitArr.xor(arr2);
        assertTrue(Arrays.equals(resArr.getArrBit(), allFalseArr));
    }

    @Test(expected = BitArrayDifferentSizeException.class)
    public void failWhenXorArrsWithDifferentSize() {
        BitArray arr2 = new BitArray(6);
        BitArray resArr;
        resArr = bitArr.xor(arr2);
    }

    @Test
    public void canNotArr() {
        BitArray resArr;
        Boolean[] allTrueArr = new Boolean[bitArr.getSize()];
        Arrays.fill(allTrueArr, true);
        bitArr.setAll(false);
        resArr = bitArr.not();
        assertTrue(Arrays.equals(resArr.getArrBit(), allTrueArr));
    }
}
