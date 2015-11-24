package ru.unn.agile.Deque.viewmodel;

import ru.unn.agile.Deque.model.Deque;

public class DequeViewModel {
    private final Deque<Integer> deque;
    private final OperationsWithDeque operationsWithDeque;

    private String inputNumber;
    private String output;
    private boolean isPushActionEnabled;
    private boolean isPopActionEnabled;
    private boolean isClearActionEnabled;
    private boolean isCheckActionEnabled;
    private boolean isDoActionButtonEnabled;

    private Action action;

    public class OperationsWithDeque {
        private void pushFront() {
            deque.pushFront(Integer.valueOf(inputNumber));
            setPopClearCheckActionsEnabled(true);
        }

        private void pushBack() {
            deque.pushBack(Integer.valueOf(inputNumber));
            setPopClearCheckActionsEnabled(true);
        }

        private Integer popFront() {
            Integer value = deque.popFront();
            if (deque.isEmpty()) {
                setPopClearCheckActionsEnabled(false);
            }
            output = value.toString();
            return value;
        }

        private Integer popBack() {
            Integer value = deque.popBack();
            if (deque.isEmpty()) {
                setPopClearCheckActionsEnabled(false);
            }
            output = value.toString();
            return value;
        }

        private void clear() {
            deque.clear();
            setPopClearCheckActionsEnabled(false);
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
        PushFront {
            @Override
            public void doAction() {
                operations.pushFront();
            }

            @Override
            public boolean getEnabled() {
                return viewModel.isPushActionEnabled();
            }
        },
        PushBack {
            @Override
            public void doAction() {
                operations.pushBack();
            }

            @Override
            public boolean getEnabled() {
                return viewModel.isPushActionEnabled();
            }
        },
        PopFront {
            @Override
            public void doAction() {
                operations.popFront();
            }

            @Override
            public boolean getEnabled() {
                return viewModel.isPopActionEnabled();
            }
        },
        PopBack {
            @Override
            public void doAction() {
                operations.popBack();
            }

            @Override
            public boolean getEnabled() {
                return viewModel.isPopActionEnabled();
            }
        },
        Clear {
            @Override
            public void doAction() {
                operations.clear();
            }

            @Override
            public boolean getEnabled() {
                return viewModel.isClearActionEnabled();
            }
        },
        Check_if_contains {
            @Override
            public void doAction() {
                operations.contains();
            }

            @Override
            public boolean getEnabled() {
                return viewModel.isCheckActionEnabled();
            }
        };

        private static OperationsWithDeque operations;
        private static DequeViewModel viewModel;

        public void setOperations(final OperationsWithDeque operations) {
            Action.operations = operations;
        }

        public void setViewModel(final DequeViewModel viewModel) {
            Action.viewModel = viewModel;
        }

        public abstract void doAction();
        public abstract boolean getEnabled();
    }

    public DequeViewModel() {
        deque = new Deque<>();
        operationsWithDeque = new OperationsWithDeque();
        action = Action.PushFront;
        action.setOperations(operationsWithDeque);
        action.setViewModel(this);
    }

    public OperationsWithDeque getOperationsWithDeque() {
        return operationsWithDeque;
    }

    private void setPopClearCheckActionsEnabled(final boolean isEnabled) {
        isPopActionEnabled = isEnabled;
        isClearActionEnabled = isEnabled;
        isCheckActionEnabled = isEnabled;

        updateDoActionButtonEnabled();
    }

    private void updateDoActionButtonEnabled() {
        isDoActionButtonEnabled = action.getEnabled();
    }

    public void setInputNumber(final String inputNumber) {
        this.inputNumber = inputNumber;
        try {
            Integer.parseInt(inputNumber);
            isPushActionEnabled = true;
            if (!deque.isEmpty()) {
                isCheckActionEnabled = true;
            }
        } catch (NumberFormatException e) {
            isPushActionEnabled = false;
            isCheckActionEnabled = false;
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

    public boolean isPushActionEnabled() {
        return isPushActionEnabled;
    }

    public boolean isPopActionEnabled() {
        return isPopActionEnabled;
    }

    public boolean isClearActionEnabled() {
        return isClearActionEnabled;
    }

    public boolean isCheckActionEnabled() {
        return isCheckActionEnabled;
    }

    public boolean isDoActionButtonEnabled() {
        return isDoActionButtonEnabled;
    }

    public void doAction() {
        action.doAction();
    }
}
