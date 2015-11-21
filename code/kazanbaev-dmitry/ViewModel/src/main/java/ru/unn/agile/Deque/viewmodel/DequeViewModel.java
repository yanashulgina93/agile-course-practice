package ru.unn.agile.Deque.viewmodel;

import ru.unn.agile.Deque.model.Deque;

public class DequeViewModel {
    private final Deque<Integer> deque;
    private final OperationsWithDeque operationsWithDeque;

    private String inputNumber;
    private String output;
    private boolean isPushFrontButtonEnabled;
    private boolean isPushBackButtonEnabled;
    private boolean isPopFrontButtonEnabled;
    private boolean isPopBackButtonEnabled;
    private boolean isClearButtonEnabled;
    private boolean isCheckButtonEnabled;
    private boolean isDoActionButtonEnabled;

    private Action action;

    public class OperationsWithDeque {
        private void pushFront() {
            deque.pushFront(Integer.valueOf(inputNumber));
            setPopBackPopFrontClearCheckButtonsEnabled(true);
        }

        private void pushBack() {
            deque.pushBack(Integer.valueOf(inputNumber));
            setPopBackPopFrontClearCheckButtonsEnabled(true);
        }

        private Integer popFront() {
            Integer value = deque.popFront();
            if (deque.isEmpty()) {
                setPopBackPopFrontClearCheckButtonsEnabled(false);
            }
            output = value.toString();
            return value;
        }

        private Integer popBack() {
            Integer value = deque.popBack();
            if (deque.isEmpty()) {
                setPopBackPopFrontClearCheckButtonsEnabled(false);
            }
            output = value.toString();
            return value;
        }

        private void clear() {
            deque.clear();
            setPopBackPopFrontClearCheckButtonsEnabled(false);
        }

        private void contains() {
            output = String.valueOf(deque.contains(Integer.valueOf(inputNumber)));
        }


        public Object[] toArray() {
            return deque.toArray();
        }

        public boolean isEmpty() {
            return deque.isEmpty();
        }

        public int getSize() {
            return deque.getSize();
        }
    }

    public enum Action {
        PushFront, PushBack, PopFront, PopBack, Clear, Check_if_contains
    }

    public DequeViewModel() {
        deque = new Deque<>();
        operationsWithDeque = new OperationsWithDeque();
        action = Action.PushFront;
    }

    public OperationsWithDeque getOperationsWithDeque() {
        return operationsWithDeque;
    }

    private void setPopBackPopFrontClearCheckButtonsEnabled(final boolean isEnabled) {
        isPopFrontButtonEnabled = isEnabled;
        isPopBackButtonEnabled = isEnabled;
        isClearButtonEnabled = isEnabled;
        isCheckButtonEnabled = isEnabled;

        updateDoActionButtonEnabled();
    }

    private void updateDoActionButtonEnabled() {
        switch (action) {
            case PushFront:
                isDoActionButtonEnabled = isPushFrontButtonEnabled;
                break;
            case PushBack:
                isDoActionButtonEnabled = isPushBackButtonEnabled;
                break;
            case PopFront:
                isDoActionButtonEnabled = isPopFrontButtonEnabled;
                break;
            case PopBack:
                isDoActionButtonEnabled = isPopBackButtonEnabled;
                break;
            case Clear:
                isDoActionButtonEnabled = isClearButtonEnabled;
                break;
            case Check_if_contains:
                isDoActionButtonEnabled = isCheckButtonEnabled;
                break;
            default:
                break;
        }
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
        updateDoActionButtonEnabled();
    }

    public void setAction(final int actionIndex) {
        action = Action.values()[actionIndex];

        setAction(action);
    }

    public void setAction(final Action action) {
        this.action = action;

        updateDoActionButtonEnabled();
    }

    public Action getAction() {
        return action;
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

    public boolean isDoActionButtonEnabled() {
        return isDoActionButtonEnabled;
    }

    public void doAction() {
        switch (action) {
            case PushFront:
                operationsWithDeque.pushFront();
                break;
            case PushBack:
                operationsWithDeque.pushBack();
                break;
            case PopFront:
                operationsWithDeque.popFront();
                break;
            case PopBack:
                operationsWithDeque.popBack();
                break;
            case Clear:
                operationsWithDeque.clear();
                break;
            case Check_if_contains:
                operationsWithDeque.contains();
                break;
            default:
                break;
        }
    }
}
