package ru.unn.agile.Queue.Model;
import static org.junit.Assert.*;
import org.junit.Test;
public class CanCreateQueueTest {
    @Test
    public void canPushInteger() {
        int value1 = 1;
        LabQueue<Integer> testQueue = new LabQueue<>();
        testQueue.push(value1);
        int value2 = testQueue.getHead();
        assertEquals(value1, value2);
    }
    @Test
    public void canPopElement() {
        int value1 = 1;
        LabQueue<Integer> testQueue = new LabQueue<>();
        testQueue.push(value1);
        int value2 = testQueue.pop();
        assertEquals(value1, value2);
    }
    @Test
    public void canCheckEmptiness() {
        LabQueue<Integer> testQueue = new LabQueue<>();
        boolean isQueueEmpty = testQueue.isEmpty();
        assertEquals(Boolean.TRUE, isQueueEmpty);
    }
    @Test
    public void canGetSize() {
        LabQueue<Integer> testQueue = new LabQueue<>();
        int magicNumber = 10;
        for (int i = 0; i < magicNumber; i++) {
            testQueue.push(i);
        }
        assertEquals(magicNumber, testQueue.getSize());
    }
    @Test
    public void canFindElement() {
        LabQueue<Integer> testQueue = new LabQueue<>();
        int magicNumber = 10;
        int value1 = 9;
        for (int i = 0; i < magicNumber; i++) {
            testQueue.push(magicNumber - i);
        }
        testQueue.pop();
        int result = testQueue.findElement(value1);
        assertEquals(magicNumber - value1 - 1, result);
    }
    @Test
    public void canPushString() {
        LabQueue<String> testQueue = new LabQueue<>();
        String value1 = "first";
        testQueue.push(value1);
        String value2 = testQueue.getHead();
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
        Integer value1 = testQueue.findElement("second");
        Integer answer = -1;
        assertEquals(answer, value1);
    }
    @Test
    public void checkHeadFromEmptyQueue() {
        LabQueue<String> testQueue = new LabQueue<>();
        String value1 = testQueue.getHead();
        assertEquals(null, value1);
    }
    @Test
    public void canUseAllFunctions() {
        LabQueue<Integer> testQueue = new LabQueue<>();
        int magicNumber = 10;
        for (int i = 0; i < magicNumber; i++) {
            testQueue.push(magicNumber - i);
        }
        int position1 = testQueue.findElement(5);
        for (int i = 0; i < position1; i++) {
            testQueue.pop();
        }
        int result = testQueue.findElement(2);
        assertEquals(3, result);
    }
}
