package ru.unn.agile.Deque.viewmodel;

public class DequeViewModel {
    private String inputNumber;
    private boolean isPushFrontButtonEnabled;
    private boolean isPushBackButtonEnabled;
    private boolean isPopFrontButtonEnabled;
    private boolean isPopBackButtonEnabled;

    public void setInputNumber(final String inputNumber) {
        this.inputNumber = inputNumber;
        isPushFrontButtonEnabled = true;
        isPushBackButtonEnabled = true;
    }

    public String getInputNumber() {
        return inputNumber;
    }

    public boolean isPushFrontButtonEnabled() {
        return isPushFrontButtonEnabled;
    }

    public boolean isPushBackButtonEnabled() {
        return isPushBackButtonEnabled;
    }

    public boolean isPopFrontButtonEnabled() {
        return isPopFrontButtonEnabled;
    }

    public boolean isPopBackButtonEnabled() {
        return isPopBackButtonEnabled;
    }

}
