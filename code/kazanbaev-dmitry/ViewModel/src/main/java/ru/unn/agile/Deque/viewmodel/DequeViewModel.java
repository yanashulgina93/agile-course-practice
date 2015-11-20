package ru.unn.agile.Deque.viewmodel;

import ru.unn.agile.Deque.model.Deque;

public class DequeViewModel {
    private final Deque<Integer> deque;

    private String inputNumber;
    private String output;
    private boolean isPushFrontButtonEnabled;
    private boolean isPushBackButtonEnabled;
    private boolean isPopFrontButtonEnabled;
    private boolean isPopBackButtonEnabled;
    private boolean isClearButtonEnabled;
    private boolean isCheckButtonEnabled;

    public DequeViewModel() {
        deque = new Deque<>();
    }

    private void setPopBackPopFrontClearCheckButtonsEnabled(final boolean isEnabled) {
        isPopFrontButtonEnabled = isEnabled;
        isPopBackButtonEnabled = isEnabled;
        isClearButtonEnabled = isEnabled;
        isCheckButtonEnabled = isEnabled;
    }

    public void setInputNumber(final String inputNumber) {
        this.inputNumber = inputNumber;
        try {
            Integer.parseInt(inputNumber);
            isPushFrontButtonEnabled = true;
            isPushBackButtonEnabled = true;
            if (!deque.isEmpty()) {
                isCheckButtonEnabled = true;
            }
        } catch (NumberFormatException e) {
            isPushFrontButtonEnabled = false;
            isPushBackButtonEnabled = false;
            isCheckButtonEnabled = false;
        }
    }

    public String getOutput() {
        return output;
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

    public boolean isCheckButtonEnabled() {
        return isCheckButtonEnabled;
    }

    public void pushFront() {
        deque.pushFront(Integer.valueOf(inputNumber));
        setPopBackPopFrontClearCheckButtonsEnabled(true);
    }

    public void pushBack() {
        deque.pushBack(Integer.valueOf(inputNumber));
        setPopBackPopFrontClearCheckButtonsEnabled(true);
    }

    public Integer popFront() {
        Integer value = deque.popFront();
        if (deque.isEmpty()) {
            setPopBackPopFrontClearCheckButtonsEnabled(false);
        }
        output = value.toString();
        return value;
    }

    public Integer popBack() {
        Integer value = deque.popBack();
        if (deque.isEmpty()) {
            setPopBackPopFrontClearCheckButtonsEnabled(false);
        }
        output = value.toString();
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
        setPopBackPopFrontClearCheckButtonsEnabled(false);
    }

    public void doesDequeContain() {
        output = String.valueOf(deque.contains(Integer.valueOf(inputNumber)));
    }
}
