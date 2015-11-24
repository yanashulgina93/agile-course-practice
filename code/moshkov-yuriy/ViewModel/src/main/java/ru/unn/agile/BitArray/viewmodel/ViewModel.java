package ru.unn.agile.BitArray.viewmodel;

import ru.unn.agile.BitArray.model.BitArray;

public class ViewModel {
    private BitArray firstBitArray;

    private boolean isDoOpeationButtonEnabled;
    private String sizeArray;
    private boolean initArrayButtonEnabled;
    private Operation operation;

    public ViewModel() {
        isDoOpeationButtonEnabled = false;
    }


    public boolean isDoOperationEnabled() {
        return isDoOpeationButtonEnabled;
    }

    public void setSizeArray(String sizeArray) {
        this.sizeArray = sizeArray;
        int size = -1;
        try {
            size = Integer.parseInt(sizeArray);
        }
        catch (NumberFormatException exception) {
        }
        finally {
            if (size > 0) {
                initArrayButtonEnabled = true;
            } else {
                initArrayButtonEnabled = false;
            }
        }

    }

    public boolean isInitArrayEnabled() {
        return initArrayButtonEnabled;
    }

    public BitArray gitFirstBitArray() {
        return firstBitArray;
    }

    public void initArray() {
        int size = Integer.parseInt(sizeArray);
        firstBitArray = new BitArray(size);
    }

    public void setOperation(Operation operation) {
        this.operation = operation;
    }

    public enum Operation {
        OR("OR"),
        AND("AND"),
        NOT("NOT"),
        XOR("XOR");
        private final String name;

        private Operation(final String name) {
            this.name = name;
        }

        public String toString() {
            return name;
        }
    }
}
