package ru.unn.agile.BitArray.core;

public class AndOperation implements IOperation {
    @Override
    public Boolean doOperation(final Boolean first, final Boolean second) {
        return first & second;
    }
}
