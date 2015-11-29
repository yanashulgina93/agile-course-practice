package ru.unn.agile.BitArray.viewmodel;


import ru.unn.agile.BitArray.model.BitArray;

public class ViewModel {
    private BitArray firstBitArray;
    private BitArray secondBitArray;
    private BitArray resultBitArray;

    private boolean isDoOperationButtonEnabled;
    private String inputSizeArray;
    private boolean isInitializeArrayButtonEnabled;
    private Operation operation;

    public ViewModel() {
        isDoOperationButtonEnabled = false;
        firstBitArray = new BitArray(0);
        secondBitArray = new BitArray(0);
        resultBitArray = new BitArray(0);
        operation = Operation.OR;
    }


    public boolean isDoOperationEnabled() {
        return isDoOperationButtonEnabled;
    }

    public void setSizeArray(String sizeArray) {
        this.inputSizeArray = sizeArray;
        int size;
        try {
            size = Integer.parseInt(inputSizeArray);
            isInitializeArrayButtonEnabled = size > 0;
        }
        catch (NumberFormatException exception) {
            isInitializeArrayButtonEnabled = false;
        }
    }

    public boolean isInitArrayEnabled() {
        return isInitializeArrayButtonEnabled;
    }

    public BitArray gitFirstBitArray() {
        return firstBitArray;
    }

    public void initArray() {
        int size = Integer.parseInt(inputSizeArray);
        firstBitArray = new BitArray(size);
        secondBitArray = new BitArray(size);
        resultBitArray = new BitArray(size);

        isDoOperationButtonEnabled = true;
    }

    public void setOperation(Operation operation) {
        this.operation = operation;
    }

    public BitArray getFirstBitArray() {
        return firstBitArray;
    }

    public BitArray getSecondBitArray() {
        return secondBitArray;
    }

    public BitArray getResultBitArray() {
        return resultBitArray;
    }

    public Operation getOperation() {
        return operation;
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
