package main.java.ru.unn.agile.LongNumber;

public class LongNumber {
	
	public int value;
	
	
	public LongNumber(int number) {
		value = number;
	}
	
	public void Add(LongNumber lnNum) {
		this.value += lnNum.value;
	}
	
	public void Multiply(LongNumber lnNum) {
		this.value *= lnNum.value;
	}
	

}
