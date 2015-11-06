package ru.unn.agile.Stack.Model;

public class Stack {
    private Object[] values;
    private int size;

    private static final int FIRST_ARRAY_ELEMENT = 0;

    private void doubleArraySize() {
        Object[] newValues = new Object[size * 2];
        System.arraycopy(values, FIRST_ARRAY_ELEMENT,
                         newValues, FIRST_ARRAY_ELEMENT, values.length);
        values = newValues;
    }

    public Stack() {
        values = new Object[0];
        size = 0;
    }

    public Stack(final Object[] inputArray) {
        values = inputArray.clone();
        size = inputArray.length;
    }

    public String toString() {
        String res = "";
        for (int i = 0; i < size - 1; ++i) {
            res += values[i];
            res += " ";
        }
        if (size > 0) {
            res += values[size - 1];
        }
        return res;
    }

    public void push(final Object element) {
        size++;
        if (values.length < size) {
            doubleArraySize();
        }
        values[size - 1] = element;
    }

    public Object pop() {
        if (size > 0) {
            size--;
            return values[size];
        } else {
            return null;
        }
    }

    public Object peak() {
        if (size > 0) {
            return values[size - 1];
        } else {
            return null;
        }
    }
}
