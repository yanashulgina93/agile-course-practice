package ru.unn.agile.arabicroman;

public class numeralConverter {

    private String primaryRomanSymbols [] = {"I", "V", "X", "L", "C", "D", "M"};
    private Integer numbersRelatedToRomanSymbols [] = {1, 5, 10, 50, 100, 500, 1000};

    String arabicToRoman(int arabicNumber){
        String romanNumber = "";
        if (arabicNumber==0) return "";

        Integer divisionResult = 0;
        Integer positionInNumbersArrays = primaryRomanSymbols.length -1;

        while (positionInNumbersArrays >= 0) {
            divisionResult = arabicNumber / numbersRelatedToRomanSymbols[positionInNumbersArrays];
            romanNumber += simpleRomanNumber(divisionResult, positionInNumbersArrays);
            arabicNumber -= numbersRelatedToRomanSymbols[positionInNumbersArrays]*divisionResult;
            positionInNumbersArrays-=2;
        } 
         return romanNumber;
    }

    String simpleRomanNumber(Integer divisionResult, Integer pos)
    {
        String simpleRoman = "";
        switch(divisionResult)
        {
            case 0: return "";
            case 4: return primaryRomanSymbols[pos]+primaryRomanSymbols[pos+1];
            case 9: return primaryRomanSymbols[pos]+primaryRomanSymbols[pos+2];
        }
        
        if (divisionResult>=5){
            simpleRoman = primaryRomanSymbols[pos+1]; divisionResult -= 5;
        }

        while (divisionResult > 0)
        {
            simpleRoman += primaryRomanSymbols[pos];
            divisionResult--;
        }
        return simpleRoman;
    }


    int romanToArabic(String romanNumber){
        int arabicNumber = 0;
        if (romanNumber.isEmpty()) return 0;
        return 1;
    }


}
