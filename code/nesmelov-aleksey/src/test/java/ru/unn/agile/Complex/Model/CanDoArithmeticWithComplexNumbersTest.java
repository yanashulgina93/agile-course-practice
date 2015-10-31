package test.java.ru.unn.agile.Complex.Model;

import org.junit.Test;
import static org.junit.Assert.*;
import main.java.ru.unn.agile.Complex.Model.*;

public class CanDoArithmeticWithComplexNumbersTest {
    private final double epsil = 0.00000000000000001;
    private Complex complexNumber;
    private Complex first;
    private Complex second;

    private void setUpComplexNumberWithTwoParameters() {
        complexNumber = new Complex(1, 4);
    }

    private void setUpComplexNumberWithOneParameter() {
        complexNumber = new Complex(1);
    }

    private void setUpComplexNumberWithoutParameters() {
        complexNumber = new Complex();
    }

    private void setUpPositiveComplexNumbers() {
        first = new Complex(2, 3);
        second = new Complex(1, 1);
    }

    private void setUpNegativeComplexNumbers() {
        first = new Complex(-2, -3);
        second = new Complex(-1, -1);
    }

    private void setUpComplexNumberAndZero() {
        first = new Complex(2, 3);
        second = new Complex(0, 0);
    }

    @Test
    public void canCreateComplexNumberWithTwoParameters() {
        setUpComplexNumberWithTwoParameters();

        assertNotNull(complexNumber);
    }

    @Test
    public void canSetInitialRealWithTwoParameters() {
        setUpComplexNumberWithTwoParameters();

        assertEquals(1.0, complexNumber.getReal(), epsil);
    }

    @Test
    public void canSetInitialImaginaryWithTwoParameters() {
        setUpComplexNumberWithTwoParameters();

        assertEquals(4.0, complexNumber.getImaginary(), epsil);
    }

    @Test
    public void canCreateComplexNumberWithOneParameter() {
        setUpComplexNumberWithOneParameter();

        assertNotNull(complexNumber);
    }

    @Test
    public void canInitialImaginaryAsZeroWhenOneParameter() {
        setUpComplexNumberWithOneParameter();

        assertEquals(0, complexNumber.getImaginary(), epsil);
    }

    @Test
    public void canCheckThatComplexNumberWithOneParameterIsEqualComplexNumberWithTwoParameters() {
        setUpComplexNumberWithOneParameter();

        assertEquals(complexNumber, new Complex(1, 0));
    }

    @Test
    public void canCreateComplexNumberWitoutParameters() {
        setUpComplexNumberWithoutParameters();

        assertNotNull(complexNumber);
    }

    @Test
    public void canCheckThatComplexNumberWithoutParametersIsZero() {
        setUpComplexNumberWithoutParameters();

        assertEquals(complexNumber, new Complex(0, 0));
    }

    @Test
    public void canCheckThatNumbersAreEqual() {
        first = new Complex(3, 5);
        second = new Complex(3, 5);

        assertTrue(first.equals(second));
    }

    @Test
    public void canCheckThatNumbersWithDifferentRealPartAreNotEqual() {
        first = new Complex(3, 5);
        second = new Complex(5, 5);

        assertFalse(first.equals(second));
    }

    @Test
    public void canCheckThatNumbersWithDifferentImaginaryPartAreNotEqual() {
        first = new Complex(5, 8);
        second = new Complex(5, 5);

        assertFalse(first.equals(second));
    }

    @Test
    public void canSetPositiveReal() {
        setUpComplexNumberWithoutParameters();

        complexNumber.setReal(5);

        assertEquals(complexNumber, new Complex(5, 0));
    }

    @Test
    public void canSetNegativeReal() {
        setUpComplexNumberWithoutParameters();

        complexNumber.setReal(-5);

        assertEquals(complexNumber, new Complex(-5, 0));
    }

    @Test
    public void canSetZeroReal() {
       setUpComplexNumberWithTwoParameters();

        complexNumber.setReal(0);

        assertEquals(complexNumber, new Complex(0, 4));
    }

    @Test
    public void canSetPositiveImaginary() {
        setUpComplexNumberWithoutParameters();

        complexNumber.setImaginary(5);

        assertEquals(complexNumber, new Complex(0, 5));
    }

    @Test
    public void canSetZeroImaginary() {
        setUpComplexNumberWithTwoParameters();

        complexNumber.setImaginary(0);

        assertEquals(complexNumber, new Complex(1, 0));
    }

    @Test
    public void canGetReal() {
        setUpComplexNumberWithTwoParameters();

        assertEquals(1.0, complexNumber.getReal(), epsil);
    }

    @Test
    public void canGetImaginary() {
        setUpComplexNumberWithTwoParameters();

        assertEquals(4.0, complexNumber.getImaginary(), epsil);
    }

    @Test
    public void canCheckThatZeroNumberIsZero() {
        setUpComplexNumberWithoutParameters();

        assertTrue(complexNumber.isZero());
    }

    @Test
    public void canCheckThatNotZeroNumberIsNotZero() {
        setUpComplexNumberWithTwoParameters();

        assertFalse(complexNumber.isZero());
    }

    @Test
    public void canAddComplexNumbersWithPositiveParts() {
        setUpPositiveComplexNumbers();

        Complex result = first.add(second);

        assertEquals(result, new Complex(3, 4));
    }

    @Test
    public void canAddComplexNumbersWithNegativeParts() {
        setUpNegativeComplexNumbers();

        Complex result = first.add(second);

        assertEquals(result, new Complex(-3, -4));
    }

    @Test
    public void canAddComplexNumberWitZero() {
        setUpComplexNumberAndZero();

        Complex result = first.add(second);

        assertEquals(result, new Complex(2, 3));
    }

    @Test
    public void canSubtractComplexNumbersWithPositiveParts() {
        setUpPositiveComplexNumbers();

        Complex result = first.subtract(second);

        assertEquals(result, new Complex(1, 2));
    }

    @Test
    public void canSubtractComplexNumbersWithNegativeParts() {
        setUpNegativeComplexNumbers();

        Complex result = first.subtract(second);

        assertEquals(result, new Complex(-1, -2));
    }

    @Test
    public void canSubtractComplexNumberFromZero() {
        setUpComplexNumberAndZero();

        Complex result = second.subtract(first);

        assertEquals(result, new Complex(-2, -3));
    }

    @Test
    public void canMultiplyComplexNumbersWithPositiveParts() {
        setUpPositiveComplexNumbers();

        Complex result = first.multiply(second);

        assertEquals(result, new Complex(-1, 5));
    }

    @Test
    public void canMultiplyComplexNumbersWithNegativeParts() {
        setUpNegativeComplexNumbers();

        Complex result = first.multiply(second);

        assertEquals(result, new Complex(-1, 5));
    }

    @Test
    public void canMultiplyComplexNumberWithZero() {
        setUpComplexNumberAndZero();

        Complex result = first.multiply(second);

        assertEquals(result, new Complex(0, 0));
    }

    @Test
    public void canDivideComplexNumbersWithPositiveParts() {
        setUpPositiveComplexNumbers();

        Complex result = first.divide(second);

        assertEquals(result, new Complex(2.5, 0.5));
    }

    @Test
    public void canDivideComplexNumbersWithNegativeParts() {
        setUpNegativeComplexNumbers();

        Complex result = first.divide(second);

        assertEquals(result, new Complex(2.5, 0.5));
    }

    @Test(expected = Exception.class)
    public void canNotDivideComplexNumbersToZero() {
        setUpComplexNumberAndZero();

        Complex result = first.divide(second);
    }
}
