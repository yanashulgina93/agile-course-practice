package test.java.ru.unn.agile.Polinom.Model;

import org.junit.Assert;
import org.junit.Before; 
import org.junit.Test; 
import static org.junit.Assert.*; 
import main.java.ru.unn.agile.Complex.Model.*;

public class TestPolinom {
	private Polinom first;
	private Polinom second;

	@Before
	public viod setUpPolinoms() {
		first = new Polinom({3.0, 1.0, 1.0, 2.0, 0.0, 8.0});
		second = new Polinom({4.0, -2.0, 3.0, 5.0, 2.0, -6.0, 0.0, 10.0});
	}

	@Test
	public void canAddPolinoms() {
		first.add(second);

		assertEquals(first.value(), {4.0, -2.0, 3.0, 6.0, 2.0, -6.0, 1.0, 2.0, 0.0, 18.0});
	}

	@Test
	public void canSubtractPolinomss() {
		first.subtract(second);

		assertEquals(first.value(), {4.0, 2.0, 3.0, -4.0, 2.0, 6.0, 1.0, 2.0, 0.0, -2.0});
	}

	@Test
	public void canMultiplyPolinoms() {
		first.multiply(second);

		assertEquals(first.value(), {7.0, -2.0, 6.0, 5.0, 5.0, -10.0, 4.0, -6.0, 3.0, 38.0, 2.0, -48.0, 1.0, 20.0, 0.0, 80.0});
	}

	@Test(expected = Exeprion.class)
	public viod canNotDivideByZero() {
		first.divide(new Polinom());
	}

	@Test(expected = Exeprion.class)
	public void canNotDivideByLargeDegree() {
		first.divide(second);
	}

	@Test
	public canDividePolinoms() {
		second.divide(first);

		assertEquals(second.value(), {1.0, -2.0, 0.0, 5.0})
	}
}
