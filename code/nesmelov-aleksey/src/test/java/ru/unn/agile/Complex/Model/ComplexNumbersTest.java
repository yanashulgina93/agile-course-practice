package test.java.ru.unn.agile.Complex.Model;

import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.junit.Assert.assertFalse;
import main.java.ru.unn.agile.Complex.Model.*;

public class ComplexNumbersTest {
    private final double epsil = 0.00000000000000001;
    private Complex complexNumber;
    private Complex first;
    private Complex second;

    @Test
    public void canCreateComplexNumberWithTwoParameters() {
        complexNumber = new Complex(1, 4);

        assertNotNull(complexNumber);
    }

    @Test
    public void canSetInitialRealWithTwoParameters() {
        complexNumber = new Complex(1, 4);

        Assert.assertEquals(1.0, complexNumber.getReal(), epsil);
    }

    @Test
    public void canSetInitialImaginaryWithTwoParameters() {
        complexNumber = new Complex(1, 4);

        Assert.assertEquals(4.0, complexNumber.getImaginary(), epsil);
    }

    @Test
    public void canCreateComplexNumberWithOneParameter() {
        complexNumber = new Complex(1);

        assertNotNull(complexNumber);
    }

    @Test
    public void canInitialImaginaryAsZeroWhenOneParameter() {
        complexNumber = new Complex(1);

        Assert.assertEquals(0, complexNumber.getImaginary(), epsil);
    }

    @Test
    public void canCheckThatComplexNumberWithOneParameterIsEqualComplexNumberWithTwoParameters() {
        complexNumber = new Complex(1);

        Assert.assertEquals(complexNumber, new Complex(1, 0));
    }

    @Test
    public void canCreateComplexNumberWitoutParameters() {
        complexNumber = new Complex();

        assertNotNull(complexNumber);
    }

    @Test
    public void canCheckThatComplexNumberWithoutParametersIsZero() {
        complexNumber = new Complex();

        Assert.assertEquals(complexNumber, new Complex(0, 0));
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
        complexNumber = new Complex();

        complexNumber.setReal(5);

        Assert.assertEquals(complexNumber, new Complex(5, 0));
    }

    @Test
    public void canSetNegativeReal() {
        complexNumber = new Complex();

        complexNumber.setReal(-5);

        Assert.assertEquals(complexNumber, new Complex(-5, 0));
    }

    @Test
    public void canSetZeroReal() {
        complexNumber = new Complex(1, 4);

        complexNumber.setReal(0);

        Assert.assertEquals(complexNumber, new Complex(0, 4));
    }

    @Test
    public void canSetPositiveImaginary() {
        complexNumber = new Complex();

        complexNumber.setImaginary(5);

        Assert.assertEquals(complexNumber, new Complex(0, 5));
    }

    @Test
    public void canSetZeroImaginary() {
        complexNumber = new Complex(1, 4);

        complexNumber.setImaginary(0);

        Assert.assertEquals(complexNumber, new Complex(1, 0));
    }

    @Test
    public void canGetReal() {
        complexNumber = new Complex(1, 4);

        Assert.assertEquals(1.0, complexNumber.getReal(), epsil);
    }

    @Test
    public void canGetImaginary() {
        complexNumber = new Complex(1, 4);

        Assert.assertEquals(4.0, complexNumber.getImaginary(), epsil);
    }

    @Test
    public void canCheckThatZeroNumberIsZero() {
        complexNumber = new Complex();

        assertTrue(complexNumber.isZero());
    }

    @Test
    public void canCheckThatNotZeroNumberIsNotZero() {
        complexNumber = new Complex(1, 4);

        assertFalse(complexNumber.isZero());
    }
}
