package ru.unn.agile.arabicroman.viewmodel;

public class ArabicRomanConverterViewModel {

    private boolean reverseButtonEnabled = true;
    private boolean convertButtonEnabled = false;
    private String arabicNumber;


    public boolean isReverseButtonEnabled() {
        return reverseButtonEnabled;
    }

    public boolean isConvertButtonEnabled() {
        return convertButtonEnabled;
    }

    public void setInputNumber(final String arabicNumber) {
        this.arabicNumber = arabicNumber;
        convertButtonEnabled = true;
    }
}
