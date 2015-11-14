package ru.unn.agile.Deque.viewmodel;

public class DequeViewModel {
    //private String inputNumber;
    private boolean isPushFrontButtonEnabled;
    private boolean isPushBackButtonEnabled;
    private boolean isPopFrontButtonEnabled;
    private boolean isPopBackButtonEnabled;

    public void setInputNumber(final String inputNumber) {
        String ainputNumber = inputNumber;
        isPushFrontButtonEnabled = true;
        isPushBackButtonEnabled = true;
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
