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
public class PolinomAddTest {
	private Polinom first;
	private Polinom second;
	private final double[] firstCoefficients;
	private final double[] secondCoefficients;
	private final double[] expectedCoefficients;

	public PolinomAddTest(final double[] firstCoefficients, final double[] secondCoefficients, final double[] expectedCoefficients) {
		this.inputCoefficients = inputCoefficients;
		this.secondCoefficients = secondCoefficients;
		this.expectedCoefficients = expectedCoefficients;
	}

	@Parameterized.Parameters
	public static List<Object[]> inputAndExpectedOutput() {
		return Arrays.asList(new Object[][] {
			{{0.0}, {0.0}, {0.0}},
			{{5.0, 15.0}, {7.0, 1.0}, {12.0, 16.0}},
			{{15.0, -8.0}, {-2.0, 7.0}, {13.0, -1.0}},
			{{2.0, 9.0, 0.0}, {0.0, 1.0, 4.0}, {2.0, 10.0, 4.0}},
			{{12.64, 0.15, 1007.0009}, {-12.64, 60.85, 1002.101}, {0.0, 61.0, 2009.1019}},
			{{5.0, 6.0, 7.0}, {5.0, 6.0, -7.0}, {10.0, 12.0}},
			{{5.0, -6.0, -7.0}, {-5.0, 6.0, 7.0}, {0.0}}
		});
	}

	@Before
	public void initPolinoms() {
		first = new Polinom(firstCoefficients);
		second = new Polinom(secondCoefficients);
	}

	@Test
	public void canAddPolinoms() {
		first.add(second);
		double[] resultCoefficients = first.getCoefficients();
		Assert.assertTrue(Arrays.equals(resultCoefficients, expectedCoefficients));
	}
}
