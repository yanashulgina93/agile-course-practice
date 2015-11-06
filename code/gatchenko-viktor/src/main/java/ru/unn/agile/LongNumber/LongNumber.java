package main.java.ru.unn.agile.LongNumber;

public class LongNumber {
	
	public int value;
	
	public LongNumber() {
		this.value = 0;
	}
	
	public LongNumber(int number) {
		this.value = number;
	}
	
	public LongNumber Add(LongNumber lnNum) {
		LongNumber result = new LongNumber();
		result.value = this.value + lnNum.value;
		
		return result;
	}
	
	public LongNumber Multiply(LongNumber lnNum) {
		LongNumber result = new LongNumber();
		result.value = this.value * lnNum.value;
		
		return result;
	}
	
	public boolean IsEqual(LongNumber lnNum) {
		boolean result = true;
		
		if(this.value != lnNum.value) {
			result = false;
		}
		
		return result;
	}
	

}
