package ru.unn.agile.deque.Model;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class DequeTest {
    @Test
    public void createdDequeWithoutParameterIsNotNull() {
        Deque<Integer> deque = new Deque<>();

        assertNotNull(deque);
    }

    @Test
    public void createdDequeWithParameterIsNotNull() {
        List<Integer> list = new ArrayList<>();
        list.add(2);
        list.add(-5);

        Deque<Integer> deque = new Deque<>(list);

        assertNotNull(deque);
    }

    @Test
    public void createdDequeWithParameterContainsProperInformation() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(9);

        Deque<Integer> deque = new Deque<>(list);

        assertTrue(Arrays.equals(deque.toArray(), list.toArray()));
    }

    @Test
    public void doesPushFrontWorkProperlyWithIntegers() {
        Deque<Integer> deque = new Deque<>();
        deque.pushFront(1);
        deque.pushFront(9);
        deque.pushFront(-1);

        assertArrayEquals(new Integer[]{-1, 9, 1}, deque.toArray());
    }

    @Test
    public void doesPushFrontWorkProperlyWithDates() {
        Deque<Date> deque = new Deque<>();
        deque.pushFront(new Date(750384000));
        deque.pushFront(new Date(1444608000));

        assertArrayEquals(new Date[]{new Date(1444608000), new Date(750384000)}, deque.toArray());
    }

    @Test
    public void doesPushFrontWorkProperlyWithNull() {
        Deque<Integer> deque = new Deque<>();
        deque.pushFront(1);
        deque.pushFront(null);
        deque.pushFront(-1);

        assertArrayEquals(new Integer[]{-1, 1}, deque.toArray());
    }

    @Test
    public void doesPushBackWorkProperlyWithIntegers() {
        Deque<Integer> deque = new Deque<>();
        deque.pushBack(1);
        deque.pushBack(9);
        deque.pushBack(-1);

        assertArrayEquals(new Integer[]{1, 9, -1}, deque.toArray());
    }

    @Test
    public void doesPushBackWorkProperlyWithDates() {
        Deque<Date> deque = new Deque<>();
        deque.pushBack(new Date(750384000));
        deque.pushBack(new Date(1444608000));

        assertArrayEquals(new Date[]{new Date(750384000), new Date(1444608000)}, deque.toArray());
    }

    @Test
    public void doesPushBackWorkProperlyWithNull() {
        Deque<Integer> deque = new Deque<>();
        deque.pushBack(1);
        deque.pushBack(null);
        deque.pushBack(-1);

        assertArrayEquals(new Integer[]{1, -1}, deque.toArray());
    }
}
