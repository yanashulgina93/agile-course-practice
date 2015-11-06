package ru.unn.agile.Stack.Model;

import java.util.ArrayList;

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

    public ArrayList<Object> toArrayList() {
        ArrayList<Object> res = new ArrayList<>();
        for (int i = 0; i < size; ++i) {
            res.add(values[i]);
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
        Object topElement = peak();
        if (topElement != null) {
            size--;
        }
        return topElement;
    }

    public Object peak() {
        if (size > 0) {
            return values[size - 1];
        } else {
            return null;
        }
    }
}
