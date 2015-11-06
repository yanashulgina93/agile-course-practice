package test.java.ru.unn.agile.LongMumber;

import static org.junit.Assert.*;
import org.junit.Test;
import main.java.ru.unn.agile.LongNumber.Matrix;

public class WhenWorkWithMatrix {
	
	@Test
	public void Multiply_Some_Matrix_And_E_Matrix () {
		Matrix someMatrix = new Matrix();
		Matrix eMatrix = new Matrix();
		Matrix resultMatrix = someMatrix.Multiply(eMatrix);
		
		assertEquals(someMatrix, resultMatrix);
	}
	
	//TODO Multiply @Test
	//TODO Multiply @Test
	
	//@Test
	/*public void Get_Width_Some_Matrix () {
		assertEquals(expected, actual);
	}*/
}
