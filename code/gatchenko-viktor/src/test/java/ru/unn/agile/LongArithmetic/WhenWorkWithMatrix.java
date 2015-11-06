package test.java.ru.unn.agile.LongArithmetic;

import static org.junit.Assert.*;
import org.junit.Test;

import main.java.ru.unn.agile.LongArithmetic.LongNumber;
import main.java.ru.unn.agile.LongArithmetic.Matrix;

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
            
        Matrix resultMatrix = firstMatrix.multiply(secondMatrix);
        
        boolean resultCompare = gageMatrix.isEqual(resultMatrix);
        assertEquals(true, resultCompare);
    }
    
    @Test
    public void Multiply_Some_Matrix_5x3_And_E_Matrix_3x3() {
        Matrix someMatrix = generateMatrix(5, 3);
        Matrix eMatrix = new Matrix(3,3);
        eMatrix.element[0][0] = new LongNumber(1);
        eMatrix.element[1][1] = new LongNumber(1);
        eMatrix.element[2][2] = new LongNumber(1);
        
        Matrix resultMatrix = someMatrix.multiply(eMatrix);
        
        boolean resultCompare = someMatrix.isEqual(resultMatrix);
        assertEquals(true, resultCompare);
    }
    
    @Test
    public void Multiply_Some_Matrix_5x3_And_Some_Matrix_3x5() {
        Matrix firstMatrix = generateMatrix(5, 3);
        Matrix secondMatrix = generateMatrix(3, 5);
        
        Matrix resultMatrix = firstMatrix.multiply(secondMatrix);
        
        boolean success = true;
        if(resultMatrix.getHeight() != 5 || resultMatrix.getWidth() != 5) {
            success = false;
        }
        assertEquals(true, success);
    }
    
    @Test
    public void Compare_Equal_Matrixs_4x3() {
        Matrix firstMatrix = generateMatrix(4, 3);
        Matrix secondMatrix = new Matrix(firstMatrix);
        
        boolean isEqual = firstMatrix.isEqual(secondMatrix);
        assertEquals(true, isEqual);
    }
    
    @Test
    public void Compare_Some_Matrix_4x3_And_Some_Matrix_3x3() {
        Matrix firstMatrix = generateMatrix(4, 3);
        Matrix secondMatrix = generateMatrix(3, 3);
        
        boolean isEqual = firstMatrix.isEqual(secondMatrix);
        assertEquals(false, isEqual);
    }
    
    @Test
    public void Compare_Matrix_2x2_And_E_Matrix_2x2() {
        Matrix matrix = new Matrix(2, 2);
            matrix.element[0][0] = new LongNumber(1);
            matrix.element[0][1] = new LongNumber(1);
            matrix.element[1][0] = new LongNumber(2);
            matrix.element[1][1] = new LongNumber(1);
        Matrix eMatrix = new Matrix(2, 2);
            eMatrix.element[0][0] = new LongNumber(1);
            eMatrix.element[1][1] = new LongNumber(1);
        
        boolean isEqual = matrix.isEqual(eMatrix);
        assertEquals(false, isEqual);
    }
    
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
}
