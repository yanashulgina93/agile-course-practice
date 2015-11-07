package test.java.ru.unn.agile.Complex.Model;

import org.junit.Test;
import main.java.ru.unn.agile.Complex.Model.*;
import static org.junit.Assert.*;

public class EquivalenceOfComplexNumbersTest {
    private final double epsil = 0.00000000000000001;
    private Complex first;
    private Complex second;

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
}
