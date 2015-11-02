package test.java.ru.unn.agile.Complex.Model;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import main.java.ru.unn.agile.Complex.Model.*;

public class CanDoArithmeticWithComplexNumberAndZeroTest {
    private Complex first;
    private Complex second;

    @Before
    public void setUpComplexNumberAndZero() {
        first = new Complex(2, 3);
        second = new Complex(0, 0);
    }

    @Test
    public void canAddComplexNumberWitZero() {
        setUpComplexNumberAndZero();

        Complex result = first.add(second);

        assertEquals(result, new Complex(2, 3));
    }

    @Test
    public void canSubtractComplexNumberFromZero() {
        setUpComplexNumberAndZero();

        Complex result = second.subtract(first);

        assertEquals(result, new Complex(-2, -3));
    }

    @Test
    public void canMultiplyComplexNumberWithZero() {
        setUpComplexNumberAndZero();

        Complex result = first.multiply(second);

        assertEquals(result, new Complex(0, 0));
    }

    @Test(expected = Exception.class)
    public void canNotDivideComplexNumbersToZero() {
        setUpComplexNumberAndZero();

        Complex result = first.divide(second);
    }
}
