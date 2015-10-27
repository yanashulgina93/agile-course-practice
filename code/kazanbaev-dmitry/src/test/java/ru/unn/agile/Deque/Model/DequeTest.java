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
}
