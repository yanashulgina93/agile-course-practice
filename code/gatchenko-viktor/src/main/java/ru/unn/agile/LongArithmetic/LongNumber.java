package main.java.ru.unn.agile.LongArithmetic;

public class LongNumber {
    
    public int value;
    
    public LongNumber() {
        this.value = 0;
    }
    
    public LongNumber(int number) {
        this.value = number;
    }
    
    public LongNumber(LongNumber copiedNum) {
        this.value = copiedNum.value;
    }
    
    public LongNumber add(LongNumber lnNum) {
        LongNumber result = new LongNumber();
        result.value = this.value + lnNum.value;
        
        return result;
    }
    
    public LongNumber multiply(LongNumber lnNum) {
        LongNumber result = new LongNumber();
        result.value = this.value * lnNum.value;
        
        return result;
    }
    
    public boolean isEqual(LongNumber lnNum) {
        boolean result = true;
        
        if(this.value != lnNum.value) {
            result = false;
        }
        
        return result;
    }
}
