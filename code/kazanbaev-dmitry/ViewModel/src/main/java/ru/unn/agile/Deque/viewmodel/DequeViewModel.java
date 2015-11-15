package ru.unn.agile.Deque.viewmodel;

import ru.unn.agile.Deque.model.Deque;

public class DequeViewModel {
    private final Deque<Integer> deque;

    private String inputNumber;
    private boolean isPushFrontButtonEnabled;
    private boolean isPushBackButtonEnabled;
    private boolean isPopFrontButtonEnabled;
    private boolean isPopBackButtonEnabled;

    public DequeViewModel() {
        deque = new Deque<>();
    }

    public void setInputNumber(final String inputNumber) {
        this.inputNumber = inputNumber;
        if ("".equals(inputNumber)) {
            isPushFrontButtonEnabled = false;
            isPushBackButtonEnabled = false;
        } else {
            isPushFrontButtonEnabled = true;
            isPushBackButtonEnabled = true;
        }
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

    public void pushFront() {
        deque.pushFront(Integer.parseInt(inputNumber));
        isPopFrontButtonEnabled = true;
        isPopBackButtonEnabled = true;
    }

    public void pushBack() {
        deque.pushBack(Integer.parseInt(inputNumber));
        isPopFrontButtonEnabled = true;
        isPopBackButtonEnabled = true;
    }
}
