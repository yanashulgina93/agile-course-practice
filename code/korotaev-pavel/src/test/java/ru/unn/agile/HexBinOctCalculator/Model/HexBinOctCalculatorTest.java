package ru.unn.agile.HexBinOctCalculator.Model;

import org.junit.Test;
import static org.junit.Assert.*;

public class HexBinOctCalculatorTest {
    @Test
    public void canAddOneBinOneHexNumbersAndConvertResultToOct() {
        Number firstNumber = new Number("101", NumeralSystem.BIN);
        Number secondNumber = new Number("2", NumeralSystem.HEX);

        String result = HexBinOctCalculator.add(firstNumber, secondNumber, NumeralSystem.OCT);

        assertEquals(result, "7");
    }

    @Test
    public void canSubtractTwoBinNumbers() {
        Number firstNumber = new Number("111", NumeralSystem.BIN);
        Number secondNumber = new Number("11", NumeralSystem.BIN);

        String result = HexBinOctCalculator.subtract(firstNumber, secondNumber, NumeralSystem.BIN);

        assertEquals(result, "100");
    }

    @Test
    public void canMultiplyTwoBinNumbers() {
        Number firstNumber = new Number("11", NumeralSystem.BIN);
        Number secondNumber = new Number("11", NumeralSystem.BIN);

        String result = HexBinOctCalculator.multiply(firstNumber, secondNumber, NumeralSystem.BIN);

        assertEquals(result, "1001");
    }

    @Test
    public void canDivideTwoBinNumbers() {
        Number firstNumber = new Number("1111", NumeralSystem.BIN);
        Number secondNumber = new Number("10", NumeralSystem.BIN);

        String result = HexBinOctCalculator.divide(firstNumber, secondNumber, NumeralSystem.BIN);

        assertEquals(result, "111");
    }

    @Test
    public void canAddOneHexOneOctNumbersAndConvertResultToBin() {
        Number firstNumber = new Number("ab", NumeralSystem.HEX);
        Number secondNumber = new Number("315", NumeralSystem.OCT);

        String result = HexBinOctCalculator.add(firstNumber, secondNumber, NumeralSystem.BIN);

        assertEquals(result, "101111000");
    }

    @Test
    public void canSubtractTwoHexNumbers() {
        Number firstNumber = new Number("cde", NumeralSystem.HEX);
        Number secondNumber = new Number("5", NumeralSystem.HEX);

        String result = HexBinOctCalculator.subtract(firstNumber, secondNumber, NumeralSystem.HEX);

        assertEquals(result, "cd9");
    }

    @Test
    public void canMultiplyTwoHexNumbers() {
        Number firstNumber = new Number("4", NumeralSystem.HEX);
        Number secondNumber = new Number("be", NumeralSystem.HEX);

        String result = HexBinOctCalculator.multiply(firstNumber, secondNumber, NumeralSystem.HEX);

        assertEquals(result, "2f8");
    }

    @Test
    public void canDivideTwoHexNumbers() {
        Number firstNumber = new Number("bc", NumeralSystem.HEX);
        Number secondNumber = new Number("a", NumeralSystem.HEX);

        String result = HexBinOctCalculator.divide(firstNumber, secondNumber, NumeralSystem.HEX);

        assertEquals(result, "12");
    }

    @Test
    public void canAddOneOctOneBinNumbersAndConvertResultToHex() {
        Number firstNumber = new Number("7", NumeralSystem.OCT);
        Number secondNumber = new Number("1010", NumeralSystem.BIN);

        String result = HexBinOctCalculator.add(firstNumber, secondNumber, NumeralSystem.HEX);

        assertEquals(result, "11");
    }

    @Test
    public void cabSubtractTwoOctNumbers() {
        Number firstNumber = new Number("35", NumeralSystem.OCT);
        Number secondNumber = new Number("17", NumeralSystem.OCT);

        String result = HexBinOctCalculator.subtract(firstNumber, secondNumber, NumeralSystem.OCT);

        assertEquals(result, "16");
    }

    @Test
    public void canMultiplyTwoOctNumbers() {
        Number firstNumber = new Number("14", NumeralSystem.OCT);
        Number secondNumber = new Number("5", NumeralSystem.OCT);

        String result = HexBinOctCalculator.multiply(firstNumber, secondNumber, NumeralSystem.OCT);

        assertEquals(result, "74");
    }

    @Test
    public void canDivideTwoOctNumbers() {
        Number firstNumber = new Number("71", NumeralSystem.OCT);
        Number secondNumber = new Number("6", NumeralSystem.OCT);

        String result = HexBinOctCalculator.divide(firstNumber, secondNumber, NumeralSystem.OCT);

        assertEquals(result, "11");
    }

    @Test
    public void canMultiplyTwoNegativeBinNumbers() {
        Number firstNumber = new Number("11111111111111111111111111101111", NumeralSystem.BIN);
        Number secondNumber = new Number("11111111111111111111111111111110", NumeralSystem.BIN);

        String result = HexBinOctCalculator.multiply(firstNumber, secondNumber, NumeralSystem.BIN);

        assertEquals(result, "100010");
    }

    @Test
    public void canDivideFirstNegativeSecondPositiveBinNumbers() {
        Number firstNumber = new Number("11111111111111111111111111100101", NumeralSystem.BIN);
        Number secondNumber = new Number("11", NumeralSystem.BIN);

        String result = HexBinOctCalculator.divide(firstNumber, secondNumber, NumeralSystem.BIN);

        assertEquals(result, "11111111111111111111111111110111");
    }

    @Test
    public void canDivideTwoNegativeHexNumbers() {
        Number firstNumber = new Number("ffffffc1", NumeralSystem.HEX);
        Number secondNumber = new Number("fffffffd", NumeralSystem.HEX);

        String result = HexBinOctCalculator.divide(firstNumber, secondNumber, NumeralSystem.HEX);

        assertEquals(result, "15");
    }

    @Test
    public void canMultiplyFirstNegativeSecondPositiveHexNumbers() {
        Number firstNumber = new Number("fffffffa", NumeralSystem.HEX);
        Number secondNumber = new Number("16", NumeralSystem.HEX);

        String result = HexBinOctCalculator.multiply(firstNumber, secondNumber, NumeralSystem.HEX);

        assertEquals(result, "ffffff7c");
    }

    @Test
    public void canMultiplyTwoNegativeOctNumbers() {
        Number firstNumber = new Number("37777777724", NumeralSystem.OCT);
        Number secondNumber = new Number("37777777707", NumeralSystem.OCT);

        String result = HexBinOctCalculator.multiply(firstNumber, secondNumber, NumeralSystem.OCT);

        assertEquals(result, "4714");
    }

    @Test
    public void canDivideFirstPositiveSecondNegativeOctNumbers() {
        Number firstNumber = new Number("55", NumeralSystem.OCT);
        Number secondNumber = new Number("37777777761", NumeralSystem.OCT);

        String result = HexBinOctCalculator.divide(firstNumber, secondNumber, NumeralSystem.OCT);

        assertEquals(result, "37777777775");
    }

    @Test(expected = IllegalArgumentException.class)
    public void canNotDivideToZero() {
        Number firstNumber = new Number("23", NumeralSystem.HEX);
        Number secondNumber = new Number("0", NumeralSystem.HEX);

        String result = HexBinOctCalculator.divide(firstNumber, secondNumber, NumeralSystem.HEX);
    }
}
