package test.java.ru.unn.agile.Polinom.Model;

import org.junit.Assert; 
import org.junit.Test;
import org.junit.Before; 
import org.junit.runner.RunWith; 
import org.junit.runners.Parameterized; 

import java.util.Arrays;
import java.util.List;

import main.java.ru.unn.agile.Complex.Model.*;

@RunWith(Parameterized.class)
public class PolinomMultiplyTest {
	private Polinom first;
	private Polinom second;
	private final double[] firstCoefficients;
	private final double[] secondCoefficients;
	private final double[] expectedCoefficients;

	public PolinomMultiplyTest(final double[] firstCoefficients, final double[] secondCoefficients final double[] expectedCoefficients) {
		this.firstCoefficients = firstCoefficients;
		this.secondCoefficients = secondCoefficients;
		this.expectedCoefficients = expectedCoefficients;
	}

	@Parameterized.Parameters
	public static List<Object[]> MultiplyInputAndExpected() {
		return Arrays.asList(new Object[][] {
			{{0.0}, {0.0}, {0.0}},
			{{7.0, 15.0}, {0.0}, {0.0}},
			{{7.0, 15.0}, {4.0}, {28.0, 60.0}},
			{{7.0, 15.0}, {0.0, 4.0}, {0.0, 28.0, 60.0}},
			{{7.0, 15.0}, {4.0, 7.0}, {28.0, 154.0, 105.0}},
			{{5.0, 6.0, -7.0}, {-5.0, 7.0}, {25.0, -65.0, 77.0, -49.0}},
			{{2.0, 0.0, -6.0}, {1.25, 0.5}, {2.5, 1.0, -7.5, -3.0}}
		});
	}

	@Before
	public void initPolinoms() {
		first = new Polinom(firstCoefficients);
		second = new Polinom(secondCoefficients);
	}

	@Test
	public void canMultiplyPolinoms() {
		first.multiply(second);
		double[] resultCoefficients = first.getCoefficients();
		Assert.assertTrue(Arrays.equals(resultCoefficients, expectedCoefficients));
	}
}
