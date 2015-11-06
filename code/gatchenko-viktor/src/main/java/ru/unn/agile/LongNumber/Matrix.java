package main.java.ru.unn.agile.LongNumber;

public class Matrix {
	
	static final Matrix UNDEFINED = null;
	
	public LongNumber element[][];
	
	private int width;
	private int height;
	
	public Matrix() {
		this.width = 0;
		this.height = 0;
		this.element = null;
	}
	
	public Matrix(int height, int width) {
		this.width = width;
		this.height = height;
		
		this.element = new LongNumber[height][width];
		for(int i = 0; i < height; ++i) {
			for(int j = 0; j < width; ++j) {
				this.element[i][j] = new LongNumber(0);
			}
		}
	}
	
	public Matrix Multiply(Matrix secondMultiplier) {
		Matrix resultMatrix;
		
		if(this.width != secondMultiplier.height) {
			resultMatrix = Matrix.UNDEFINED;
		} else {
			resultMatrix = new Matrix(this.height, secondMultiplier.width);
			
			for(int i = 0; i < resultMatrix.height; ++i) {
				for(int j = 0; j < resultMatrix.width; ++j) {
					resultMatrix.element[i][j] = CountUpElement(i, j, this, secondMultiplier);
				}
			}
		}
		
		return resultMatrix;
	}
	
	public boolean IsEqual(Matrix secondMatrix) {
		boolean result = true;
		
		if(this.height != secondMatrix.height || this.width != secondMatrix.width) {
			result = false;
		} else {
			for(int i = 0; i < this.height; ++i) {
				for(int j = 0; j < this.width; ++j) {
					if(!this.element[i][j].IsEqual(secondMatrix.element[i][j])) {
						result = false;
					}
				}
			}
		}
		
		return result;
	}
	
	private LongNumber CountUpElement(int i, int j, Matrix firstMultiplier, Matrix secondMultiplier) {
		LongNumber result = new LongNumber();
		LongNumber partSum;
		
		for(int w = 0; w < firstMultiplier.width; ++w) {
			partSum = firstMultiplier.element[i][w].Multiply(secondMultiplier.element[w][j]);
			result = result.Add(partSum);
		}
		
		return result;
	}
	
	public int GetWidth() {
		return this.width;
	}
	
	public void SetWidth(int newWidth) {
		this.width = newWidth;
	}
	
	public int GetHeight() {
		return this.height;
	}
	
	public void SetHeight(int newHeight) {
		this.height = newHeight;
	}
	
	

}
