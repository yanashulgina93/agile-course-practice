package ru.unn.agile.Stack.Model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class StackTest {
    @Test
    public void Initiating_Stack_From_1_2_3_Array() {
        int[] inputArray = {1,2,3};
        Stack myStack = new Stack(inputArray);
        assertEquals("1 2 3", myStack.toString());
    }
}
