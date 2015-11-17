package ru.unn.agile.arabicroman.viewmodel;

import ru.unn.agile.arabicroman.model.NumeralConverter;

public class ArabicRomanConverterViewModel {

    private boolean convertButtonEnabled = false;
    private boolean isConvertedNumberArabic = true;
    private boolean hasErrorOccurred = false;
    private String inputNumber;
    private String outputNumber;


    public boolean isConvertButtonEnabled() {
        return convertButtonEnabled;
    }

    public void setInputNumber(final String inputNumber) {
        hasErrorOccurred = false;
        outputNumber = "";
        this.inputNumber = inputNumber;
        if (inputNumber.isEmpty()) {
            convertButtonEnabled = false;
        } else {
            if (isConvertedNumberArabic) {
                try {
                    Integer.parseInt(inputNumber);
                    convertButtonEnabled = true;
                } catch (NumberFormatException e) {
                    convertButtonEnabled = false;
                }
            } else {
                convertButtonEnabled = true;
            }
        }
    }

    public String getOutputNumber() {
        return outputNumber;
    }

    public void convert() {
        try {
            if (isConvertedNumberArabic) {
                outputNumber = NumeralConverter.convert(Integer.parseInt(inputNumber));
            } else {
                outputNumber = String.valueOf(NumeralConverter.convert(inputNumber));
            }
        } catch (Exception e) {
            hasErrorOccurred = true;
        }
    }



    public void reverseConvertingDirection() {
        if (isConvertedNumberArabic) {
            isConvertedNumberArabic = false;
        } else {
            isConvertedNumberArabic = true;
        }
    }

    public boolean isConvertedNumberArabic(){
        return isConvertedNumberArabic;
    }

    public boolean hasErrorOccurred(){
        return hasErrorOccurred;
    }
}
