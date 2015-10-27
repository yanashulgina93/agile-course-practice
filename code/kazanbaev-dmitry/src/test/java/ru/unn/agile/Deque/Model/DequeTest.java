package deque.Model;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class DequeTest {
    @Test
    public void createdDequeWithoutParametersIsNotNull() {
        Deque<Integer> deque = new Deque<>();

        assertNotNull(deque);
    }

    @Test
    public void createdDequeWithParametersIsNotNull() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(9);

        Deque<Integer> deque = new Deque<>(list);

        assertNotNull(deque);
    }

    @Test
    public void doesPushFrontWorkProperlyWithIntegers() {
        Deque<Integer> deque = new Deque<>();
        deque.pushFront(1);
        deque.pushFront(9);
        deque.pushFront(-1);

        assertEquals("[-1, 9, 1]", deque.toString());
    }

    @Test
    public void doesPushFrontWorkProperlyWithNull() {
        Deque<Integer> deque = new Deque<>();
        deque.pushFront(1);
        deque.pushFront(null);
        deque.pushFront(-1);

        assertEquals("[-1, 1]", deque.toString());
    }

    @Test
    public void doesPushBackWorkProperlyWithIntegers() {
        Deque<Integer> deque = new Deque<>();
        deque.pushBack(1);
        deque.pushBack(9);
        deque.pushBack(-1);

        assertEquals("[1, 9, -1]", deque.toString());
    }

    @Test
    public void doesPushBackWorkProperlyWithNull() {
        Deque<Integer> deque = new Deque<>();
        deque.pushBack(1);
        deque.pushBack(null);
        deque.pushBack(-1);

        assertEquals("[1, -1]", deque.toString());
    }
}
