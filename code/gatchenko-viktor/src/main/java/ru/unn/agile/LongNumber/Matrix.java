package main.java.ru.unn.agile.LongNumber;

public class Matrix {
	
	public LongNumber element[][];
	
	private int width;
	private int height;
	
	public Matrix() {
		this.width = 0;
		this.height = 0;
	}
	
	public Matrix(int width, int height) {
		this.width = width;
		this.height = height;
	}
	
	public Matrix Multiply(Matrix secondMultiplier) {
		return this;
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
	
	public void setHeight(int newHeight) {
		this.height = newHeight;
	}
	
	

}
