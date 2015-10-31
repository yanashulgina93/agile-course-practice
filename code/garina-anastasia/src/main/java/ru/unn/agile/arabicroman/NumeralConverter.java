package ru.unn.agile.arabicroman;

public class NumeralConverter {

    private final String [] primaryRomanSymbols = {"I", "V", "X", "L", "C", "D", "M"};
    private final Integer [] numbersRelatedToRomanSymbols = {1, 5, 10, 50, 100, 500, 1000};
     String convertArabicToRoman(final int arabicNumber) {
        final int maxValidNumber = 3999;
        if (arabicNumber < 0 || arabicNumber > maxValidNumber) {
            throw new IllegalArgumentException();
        } else {
            String romanNumber = "";
            if (arabicNumber != 0) {
                int divisionResult;
                int tempNumber = arabicNumber;
                int positionInNumbersArrays = primaryRomanSymbols.length - 1;
                while (positionInNumbersArrays >= 0) {
                    divisionResult = tempNumber
                            / numbersRelatedToRomanSymbols[positionInNumbersArrays];
                    romanNumber += simpleRomanNumber(divisionResult, positionInNumbersArrays);
                    tempNumber -= numbersRelatedToRomanSymbols[positionInNumbersArrays]
                            * divisionResult;
                    positionInNumbersArrays -= 2;
                }
            }
            return romanNumber;
        }
    }

    private String simpleRomanNumber(final int divisionResult, final int pos) {
        final int four = 4;
        final int five = 5;
        final int nine = 9;
        switch (divisionResult) {
            case 0: return "";
            case four: return primaryRomanSymbols[pos]
                    + primaryRomanSymbols[pos + 1];
            case nine: return primaryRomanSymbols[pos]
                    + primaryRomanSymbols[pos + 2];
            default:
                String simpleRoman = "";
                int tempNumber = divisionResult;
                if (tempNumber >= five) {
                    simpleRoman = primaryRomanSymbols[pos + 1];
                    tempNumber -= five;
                }
                while (tempNumber > 0) {
                    simpleRoman += primaryRomanSymbols[pos];
                    tempNumber--;
                }
                return simpleRoman;
        }
    }

    int convertRomanToArabic(final String romanNumber) {
        if (romanNumber == null || romanNumber.isEmpty()) {
            return 0;
        }
        int arabicNumber = 0;
        int currentPositionOfSymbolInArray =
                getPosition(romanNumber.charAt(0));
        arabicNumber += numbersRelatedToRomanSymbols[currentPositionOfSymbolInArray];
        for (int stringIndex = 1; stringIndex < romanNumber.length(); stringIndex++) {
            int positionOfNewSymbolInArray =
                    getPosition(romanNumber.charAt(stringIndex));
            if (newPositionSurpassOld(currentPositionOfSymbolInArray,
                    positionOfNewSymbolInArray)) {
                arabicNumber +=
                        numbersRelatedToRomanSymbols[positionOfNewSymbolInArray]
                        - 2 * numbersRelatedToRomanSymbols[currentPositionOfSymbolInArray];
            } else {
                if (positionOfNewSymbolInArray <= currentPositionOfSymbolInArray) {
                    arabicNumber += numbersRelatedToRomanSymbols[positionOfNewSymbolInArray];
                } else {
                    throw new IllegalArgumentException();
                }
            }
            currentPositionOfSymbolInArray = positionOfNewSymbolInArray;
        }
        return arabicNumber;
    }

    private int getPosition(final char romanSymbol) {
        for (int pos = 0; pos < primaryRomanSymbols.length; pos++) {
            if (primaryRomanSymbols[pos].equals(Character.toString(romanSymbol))) {
                return pos;
            }
        }
        throw new IllegalArgumentException();

    }

    private boolean newPositionSurpassOld(final int oldPos, final int newPos) {
        if (newPos == (oldPos + 1) || newPos == (oldPos + 2)) {
            if (oldPos % 2 == 1) {
                throw new IllegalArgumentException();
            }
            return true;
        }
        return false;
    }
}
