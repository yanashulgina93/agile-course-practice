package ru.unn.agile.Queue.Model;

import static org.junit.Assert.*;
import org.junit.Test;
import java.util.Random;

public class QueueTest {

    @Test
    public void canPushInteger() {
        final int value1 = 1;
        LabQueue<Integer> testQueue = new LabQueue<>();
        testQueue.push(value1);
        final int value2 = testQueue.getHead();
        assertEquals(value1, value2);
    }

    @Test
    public void canPopElement() {
        final int value1 = 1;
        LabQueue<Integer> testQueue = new LabQueue<>();
        testQueue.push(value1);
        final int value2 = testQueue.pop();
        assertEquals(value1, value2);
    }

    @Test
    public void canCheckEmptiness() {
        LabQueue<Integer> testQueue = new LabQueue<>();
        final boolean isQueueEmpty = testQueue.isEmpty();
        assertEquals(Boolean.TRUE, isQueueEmpty);
    }

    @Test
    public void canGetSize() {
        LabQueue<Integer> testQueue = new LabQueue<>();
        final int size = 10;
        for (int i = 0; i < size; i++) {
            testQueue.push(i);
        }
        assertEquals(size, testQueue.getSize());
    }

    @Test
    public void canFindElement() {
        LabQueue<Integer> testQueue = new LabQueue<>();
        final int size = 10;
        final int value1 = 9;
        for (int i = 0; i < size; i++) {
            testQueue.push(size - i);
        }
        testQueue.pop();
        final int result = testQueue.findElement(value1);
        assertEquals(size - value1 - 1, result);
    }

    @Test
    public void canPushString() {
        LabQueue<String> testQueue = new LabQueue<>();
        final String value1 = "first";
        testQueue.push(value1);
        final String value2 = testQueue.getHead();
        assertEquals(value1, value2);
    }

    @Test
    public  void checkPopFromEmptyQueue() {
        LabQueue<Integer> testQueue = new LabQueue<>();
        Integer value1 = testQueue.pop();
        assertEquals(null, value1);
    }

    @Test
    public void cantFindElement() {
        LabQueue<String> testQueue = new LabQueue<>();
        testQueue.push("first");
        final int value1 = testQueue.findElement("second");
        final int result = -1;
        assertEquals(result, value1);
    }

    @Test
    public void checkHeadFromEmptyQueue() {
        LabQueue<String> testQueue = new LabQueue<>();
        final String value1 = testQueue.getHead();
        assertEquals(null, value1);
    }

    @Test
    public void canFindRandomElement() {
        LabQueue<Integer> testQueue = new LabQueue<>();
        final int size = 10;
        Random rand = new Random();
        final int value1 = 1 + rand.nextInt(size);
        for (int i = 0; i < size; i++) {
            testQueue.push(size - i);
        }
        final int result = testQueue.findElement(value1);
        assertEquals(size - value1, result);
    }

    @Test
    public void canFindLastElement() {
        LabQueue<Integer> testQueue = new LabQueue<>();
        final int size = 10;
        for (int i = 0; i < size; i++) {
            testQueue.push(size - i);
        }
        final int result = testQueue.findElement(1);
        assertEquals(9, result);
    }

}
