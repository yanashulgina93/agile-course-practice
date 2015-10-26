package deque.Model;

import org.junit.Test;

import static org.junit.Assert.*;

public class DequeTest {
    @Test
    public void createdDequeIsNotNull() {
        Deque<Integer> deque = new Deque<>();

        assertNotNull(deque);
    }
}
