package ru.unn.agile.Queue.Model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import java.util.Random;

public class QueueTest {

    private LabQueue<Integer> testIntQueue;
    private LabQueue<String> testStrQueue;
    private final int size = 10;

    @Before
    public void setUpQueueForTests() {
        testIntQueue = new LabQueue<>();
        testStrQueue = new LabQueue<>();
    }
    @Test
    public void canPushInteger() {
        final int value1 = 1;
        testIntQueue.push(value1);

        final int value2 = testIntQueue.getHead();

        assertEquals(value1, value2);
    }

    @Test
    public void canPopElement() {
        final int value1 = 1;
        testIntQueue.push(value1);

        final int value2 = testIntQueue.pop();

        assertEquals(value1, value2);
    }

    @Test
    public void canCheckEmptiness() {
        final boolean isQueueEmpty = testIntQueue.isEmpty();

        assertEquals(Boolean.TRUE, isQueueEmpty);
    }

    @Test
    public void canGetSize() {
        for (int i = 0; i < size; i++) {
            testIntQueue.push(i);
        }

        assertEquals(size, testIntQueue.getSize());
    }

    @Test
    public void canFindElement() {
        final int value1 = 9;
        for (int i = 0; i < size; i++) {
            testIntQueue.push(size - i);
        }

        testIntQueue.pop();
        final int result = testIntQueue.findElement(value1);

        assertEquals(size - value1 - 1, result);
    }

    @Test
    public void canPushString() {
        final String value1 = "first";
        testStrQueue.push(value1);

        final String value2 = testStrQueue.getHead();

        assertEquals(value1, value2);
    }

    @Test
    public  void checkPopFromEmptyQueue() {
        Integer value1 = testIntQueue.pop();

        assertEquals(null, value1);
    }

    @Test
    public void cantFindElement() {
        testStrQueue.push("first");

        final int value1 = testStrQueue.findElement("second");
        final int result = -1;

        assertEquals(result, value1);
    }

    @Test
    public void checkHeadFromEmptyQueue() {

        final String value1 = testStrQueue.getHead();

        assertEquals(null, value1);
    }

    @Test
    public void canFindRandomElement() {
        for (int i = 0; i < size; i++) {
            testIntQueue.push(size - i);
        }

        Random rand = new Random();
        final int value1 = 1 + rand.nextInt(size);
        final int result = testIntQueue.findElement(value1);

        assertEquals(size - value1, result);
    }

    @Test
    public void canFindLastElement() {
        for (int i = 0; i < size; i++) {
            testIntQueue.push(size - i);
        }

        final int result = testIntQueue.findElement(1);

        assertEquals(9, result);
    }

    @Test
    public void checkSizeAfterPop() {
        for (int i = 0; i < size; i++) {
            testIntQueue.push(size - i);
        }

        testIntQueue.pop();
        final int newSize = testIntQueue.getSize();

        assertEquals(size - 1, newSize);
    }

    @Test
    public void checkSizeAfterGetHead() {
        for (int i = 0; i < size; i++) {
            testIntQueue.push(size - i);
        }

        testIntQueue.getHead();
        final int newSize = testIntQueue.getSize();

        assertEquals(size, newSize);
    }
}
