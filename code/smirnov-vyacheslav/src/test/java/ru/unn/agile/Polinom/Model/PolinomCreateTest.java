package test.java.ru.unn.agile.Polinom.Model;

import org.junit.Assert; 
import org.junit.Test; 
import static org.junit.Assert.*; 
import main.java.ru.unn.agile.Complex.Model.*;

public class TestPolinom {
	private Polinom polinom;

	@Test
	public void canCreatePolinomDefault() {
		polinom = new Polinom();

		assertEquals(polinom.value(), {0.0, 0.0});
	}

	@Test
	public void canCreateMonom() {
		polinom = new Polinom({1.0, 2.0});

		assertEquals(polinom.value(), {1.0, 2.0});
	}

	@Test
	public void canCreatePolinom() {
		polinom = new Polinom({3.0, 2.0, 0.0, 6.0});

		assertEquals(polinom.value(), {3.0, 2.0, 0.0, 6.0})
	}

	@Test
	public void canCreatePolinomWithNegativeRatio () {
		polinom = new Polinom({4.0, -1.0, 2.0, 6.0});

		assertEquals(polinom.value(), {4.0, -1.0, 2.0, 6.0});
	}

	@Test
	public void canCreatePolinomWithMixedInput () {
		polinom = new Polinom({2.0, 6.0, 4.0, 1.0});

		assertEquals(polinom.value(), {4.0, 1.0, 2.0, 6.0});
	}
}
