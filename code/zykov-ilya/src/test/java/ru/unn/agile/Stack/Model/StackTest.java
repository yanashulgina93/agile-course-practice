package ru.unn.agile.Stack.Model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class StackTest {
    @Test
    public void initiatingStackFromIntArray() {
        Integer[] inputArray = {1, 2, 3};
        Stack myStack = new Stack(inputArray);
        assertEquals("1 2 3", myStack.toString());
    }

    @Test
     public void initiatingStackFromStringArray() {
        String[] inputArray = {"one", "two", "three"};
        Stack myStack = new Stack(inputArray);
        assertEquals("one two three", myStack.toString());
    }

    @Test
    public void initiatingEmptyStack() {
        Stack myStack = new Stack();
        assertEquals("", myStack.toString());
    }

    @Test
    public void pushElementToEmptyStack() {
        Stack myStack = new Stack();
        Integer element = 1;
        myStack.push(element);
        assertEquals("1", myStack.toString());
    }

    @Test
    public void pushTenIntegerElementsToEmptyStack() {
        Stack myStack = new Stack();
        Integer[] elements = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        for (int i = 0; i < elements.length; ++i) {
            myStack.push(elements[i]);
        }

        assertEquals("1 2 3 4 5 6 7 8 9 10", myStack.toString());
    }

    @Test
    public void pushTenStringElementsToEmptyStack() {
        Stack myStack = new Stack();
        String[] elements = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};

        for (int i = 0; i < elements.length; ++i) {
            myStack.push(elements[i]);
        }

        assertEquals("1 2 3 4 5 6 7 8 9 10", myStack.toString());
    }

    @Test
    public void popElementFrom123Stack() {
        Integer[] inputArray = {1, 2, 3};
        Stack myStack = new Stack(inputArray);

        assertEquals(3, myStack.pop());
    }

    @Test
    public void popElementFrom123StackAndWatchWhichWillStay() {
        Integer[] inputArray = {1, 2, 3};
        Stack myStack = new Stack(inputArray);
        myStack.pop();

        assertEquals("1 2", myStack.toString());
    }

    @Test
    public void popElementFromStringStack() {
        String[] inputArray = {"one", "two", "three"};
        Stack myStack = new Stack(inputArray);

        assertEquals("three", myStack.pop());
    }

    @Test
    public void popElementFromEmptyStackAndWatchWhichWillStay() {
        Stack myStack = new Stack();
        myStack.pop();

        assertEquals("", myStack.toString());
    }

    @Test
    public void popElementFromEmptyStack() {
        Stack myStack = new Stack();

        assertEquals(null, myStack.pop());
    }

    @Test
    public void pushAndPopToEmptyStack() {
        Stack myStack = new Stack();
        myStack.push(10);

        assertEquals(10, myStack.pop());
    }

    @Test
    public void popAndPushToEmptyStack() {
        Stack myStack = new Stack();
        myStack.pop();
        myStack.push(11);

        assertEquals("11", myStack.toString());
    }
}
