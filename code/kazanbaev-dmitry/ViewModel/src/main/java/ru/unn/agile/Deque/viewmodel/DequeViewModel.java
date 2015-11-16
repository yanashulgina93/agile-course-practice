package ru.unn.agile.Deque.viewmodel;

import ru.unn.agile.Deque.model.Deque;

public class DequeViewModel {
    private final Deque<Integer> deque;

    private String inputNumber;
    private String outputNumber;
    private boolean isPushFrontButtonEnabled;
    private boolean isPushBackButtonEnabled;
    private boolean isPopFrontButtonEnabled;
    private boolean isPopBackButtonEnabled;
    private boolean isClearButtonEnabled;

    public DequeViewModel() {
        deque = new Deque<>();
    }

    public void setInputNumber(final String inputNumber) {
        this.inputNumber = inputNumber;
        try {
            Integer.parseInt(inputNumber);
            isPushFrontButtonEnabled = true;
            isPushBackButtonEnabled = true;
        } catch (NumberFormatException e) {
            isPushFrontButtonEnabled = false;
            isPushBackButtonEnabled = false;
        }
    }

    public String getOutputNumber() {
        return outputNumber;
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

    public boolean isClearButtonEnabled() {
        return isClearButtonEnabled;
    }

    public void pushFront() {
        deque.pushFront(Integer.valueOf(inputNumber));
        isPopFrontButtonEnabled = true;
        isPopBackButtonEnabled = true;
        isClearButtonEnabled = true;
    }

    public void pushBack() {
        deque.pushBack(Integer.valueOf(inputNumber));
        isPopFrontButtonEnabled = true;
        isPopBackButtonEnabled = true;
        isClearButtonEnabled = true;
    }

    public Integer popFront() {
        Integer value = deque.popFront();
        if (deque.isEmpty()) {
            isPopFrontButtonEnabled = false;
            isPopBackButtonEnabled = false;
            isClearButtonEnabled = false;
        }
        outputNumber = value.toString();
        return value;
    }

    public Integer popBack() {
        Integer value = deque.popBack();
        if (deque.isEmpty()) {
            isPopFrontButtonEnabled = false;
            isPopBackButtonEnabled = false;
            isClearButtonEnabled = false;
        }
        outputNumber = value.toString();
        return value;
    }

    public Object[] dequeToArray() {
        return deque.toArray();
    }

    public boolean isDequeEmpty() {
        return deque.isEmpty();
    }

    public int getDequeSize() {
        return deque.getSize();
    }

    public void clearDeque() {
        deque.clear();
        isClearButtonEnabled = false;
    }
}
