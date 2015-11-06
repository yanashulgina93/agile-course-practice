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
	
	public Matrix(Matrix copiedMatrix) {
		this.width = copiedMatrix.width;
		this.height = copiedMatrix.height;
		
		this.element = new LongNumber[this.height][this.width];
		for(int i = 0; i < this.height; ++i) {
			for(int j = 0; j < this.width; ++j) {
				this.element[i][j] = copiedMatrix.element[i][j];
			}
		}
	}
	
	public Matrix multiply(Matrix secondMultiplier) {
		Matrix resultMatrix;
		
		if(this.width != secondMultiplier.height) {
			resultMatrix = Matrix.UNDEFINED;
		} else {
			resultMatrix = new Matrix(this.height, secondMultiplier.width);
			
			for(int i = 0; i < resultMatrix.height; ++i) {
				for(int j = 0; j < resultMatrix.width; ++j) {
					resultMatrix.element[i][j] = countUpElement(i, j, this, secondMultiplier);
				}
			}
		}
		
		return resultMatrix;
	}
	
	public boolean isEqual(Matrix secondMatrix) {
		boolean result = true;
		
		if(this.height != secondMatrix.height || this.width != secondMatrix.width) {
			result = false;
		} else {
			for(int i = 0; i < this.height; ++i) {
				for(int j = 0; j < this.width; ++j) {
					if(!this.element[i][j].isEqual(secondMatrix.element[i][j])) {
						result = false;
					}
				}
			}
		}
		
		return result;
	}
	
	private LongNumber countUpElement(int i, int j, Matrix firstMultiplier, Matrix secondMultiplier) {
		LongNumber result = new LongNumber();
		LongNumber partSum;
		
		for(int w = 0; w < firstMultiplier.width; ++w) {
			partSum = firstMultiplier.element[i][w].multiply(secondMultiplier.element[w][j]);
			result = result.add(partSum);
		}
		
		return result;
	}
	
	public int getWidth() {
		return this.width;
	}
	
	public void setWidth(int newWidth) {
		this.width = newWidth;
	}
	
	public int getHeight() {
		return this.height;
	}
	
	public void getHeight(int newHeight) {
		this.height = newHeight;
	}
	
	

}
