package test.java.ru.unn.agile.LongMumber;

import static org.junit.Assert.*;
import org.junit.Test;

import main.java.ru.unn.agile.LongNumber.LongNumber;
import main.java.ru.unn.agile.LongNumber.Matrix;

public class WhenWorkWithMatrix {
	
	@Test
	public void Multiply_Matrix_2x2_And_Matrix_2x2 () {
		Matrix firstMatrix = new Matrix(2,2);
			firstMatrix.element[0][0] = new LongNumber(1);
			firstMatrix.element[0][1] = new LongNumber(1);
			firstMatrix.element[1][0] = new LongNumber(2);
			firstMatrix.element[1][1] = new LongNumber(1);
		Matrix secondMatrix = new Matrix(2,2);
			secondMatrix.element[0][0] = new LongNumber(2);
			secondMatrix.element[0][1] = new LongNumber(2);
			secondMatrix.element[1][0] = new LongNumber(1);
			secondMatrix.element[1][1] = new LongNumber(2);
		Matrix gageMatrix = new Matrix(2,2);
			gageMatrix.element[0][0] = new LongNumber(3);
			gageMatrix.element[0][1] = new LongNumber(4);
			gageMatrix.element[1][0] = new LongNumber(5);
			gageMatrix.element[1][1] = new LongNumber(6);
			
		Matrix resultMatrix = firstMatrix.Multiply(secondMatrix);
		
		boolean resultCompare = gageMatrix.IsEqual(resultMatrix);
		assertEquals(true, resultCompare);
	}
	
	@Test
	public void Multiply_Some_Matrix_5x3_And_E_Matrix_3x3() {
		Matrix someMatrix = generateMatrix(5, 3);
		Matrix eMatrix = new Matrix(3,3);
		eMatrix.element[0][0] = new LongNumber(1);
		eMatrix.element[1][1] = new LongNumber(1);
		eMatrix.element[2][2] = new LongNumber(1);
		
		Matrix resultMatrix = someMatrix.Multiply(eMatrix);
		
		boolean resultCompare = someMatrix.IsEqual(resultMatrix);
		assertEquals(true, resultCompare);
	}
	
	//TODO Multiply @Test
	
	private Matrix generateMatrix(int height, int width) {
		Matrix resultMatrix = new Matrix(height, width);
		
		for(int i = 0; i < height; ++i) {
			for(int j = 0; j < width; ++j) {
				int value = (int)(Math.random()*10000);
				resultMatrix.element[i][j] = new LongNumber(value);
			}
		}
		
		return resultMatrix;
	}
	
	//@Test
	/*public void Get_Width_Some_Matrix () {
		assertEquals(expected, actual);
	}*/
}
