package ru.unn.agile.BitArray.core;

import org.junit.Test;
import java.util.Arrays;
import static org.junit.Assert.*;

public class BitArrayTest {

//    @Test
//    public void failingTest() {
//        fail();
//    }

    @Test
    public void canCreateBitArrayWithSize() {
        BitArray bitArr = new BitArray(5);
        assertNotNull(bitArr);
    }

    @Test
    public void canGetValueBit() {
        BitArray bitArr = new BitArray(5);
        assertNotNull(bitArr.getBit(0));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void failWhenGetValueBitWithIndexOutOfRangeSize() {
        BitArray bitArr = new BitArray(5);
        bitArr.getBit(6);
    }

    @Test()
    public void canSetValueBit() {
        BitArray bitArr = new BitArray(5);
        bitArr.setBit(1, true);
        assertTrue(bitArr.getBit(1));
    }

    @Test()
    public void canNotSetNullValueBit() {
        BitArray bitArr = new BitArray(5);
        bitArr.setBit(1, null);
        assertNotNull(bitArr.getBit(1));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void failWhenSetValueBitWithIndexOutOfRangeSize() {
        BitArray bitArr = new BitArray(5);
        bitArr.setBit(-1, true);
    }

    @Test
    public void canSetAllValuesInArr() {
        BitArray bitArr = new BitArray(5);
        Boolean[] allFalseArr = new Boolean[bitArr.getSize()];
        Arrays.fill(allFalseArr, false);
        bitArr.setAll(false);
        assertTrue(Arrays.equals(bitArr.getArrBit(), allFalseArr));
    }

    @Test
    public void canAndArrsWithSameSize() {
        BitArray arr1 = new BitArray(5);
        BitArray arr2 = new BitArray(5);
        BitArray resArr;
        Boolean[] allFalseArr = new Boolean[arr1.getSize()];
        Arrays.fill(allFalseArr, false);
        arr1.setAll(false);
        arr2.setAll(true);
        resArr = arr1.and(arr2);
        assertTrue(Arrays.equals(resArr.getArrBit(), allFalseArr));
    }

    @Test(expected = BitArrayDifferentSizeException.class)
    public void failWhenAndArrsWithDifferentSize() {
        BitArray arr1 = new BitArray(5);
        BitArray arr2 = new BitArray(6);
        BitArray resArr;
        resArr = arr1.and(arr2);
    }

    @Test
    public void canOrArrsWithSameSize() {
        BitArray arr1 = new BitArray(5);
        BitArray arr2 = new BitArray(5);
        BitArray resArr;
        Boolean[] allTrueArr = new Boolean[arr1.getSize()];
        Arrays.fill(allTrueArr, true);
        arr1.setAll(false);
        arr2.setAll(true);
        resArr = arr1.or(arr2);
        assertTrue(Arrays.equals(resArr.getArrBit(), allTrueArr));
    }

    @Test(expected = BitArrayDifferentSizeException.class)
    public void failWhenOrArrsWithDifferentSize() {
        BitArray arr1 = new BitArray(5);
        BitArray arr2 = new BitArray(6);
        BitArray resArr;
        resArr = arr1.or(arr2);
    }

    @Test
    public void canXorArrsWithSameSize() {
        BitArray arr1 = new BitArray(5);
        BitArray arr2 = new BitArray(5);
        BitArray resArr;
        Boolean[] allFalseArr = new Boolean[arr1.getSize()];
        Arrays.fill(allFalseArr, false);
        arr1.setAll(true);
        arr2.setAll(true);
        resArr = arr1.xor(arr2);
        assertTrue(Arrays.equals(resArr.getArrBit(), allFalseArr));
    }

    @Test(expected = BitArrayDifferentSizeException.class)
    public void failWhenXorArrsWithDifferentSize() {
        BitArray arr1 = new BitArray(5);
        BitArray arr2 = new BitArray(6);
        BitArray resArr;
        resArr = arr1.xor(arr2);
    }

    @Test
    public void canNotArr() {
        BitArray arr = new BitArray(5);
        BitArray resArr;
        Boolean[] allTrueArr = new Boolean[arr.getSize()];
        Arrays.fill(allTrueArr, true);
        arr.setAll(false);
        resArr = arr.not();
        assertTrue(Arrays.equals(resArr.getArrBit(), allTrueArr));
    }
}
