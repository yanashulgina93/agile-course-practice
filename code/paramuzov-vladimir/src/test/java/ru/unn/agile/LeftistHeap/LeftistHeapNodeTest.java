package ru.unn.agile.LeftistHeap;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class LeftistHeapNodeTest {
    @Test
    public void canSwapChildren() {
        LeftistHeapNode<Integer> node = new LeftistHeapNode<>(5);
        LeftistHeapNode<Integer> leftChild = new LeftistHeapNode<>(8);
        LeftistHeapNode<Integer> rightChild = new LeftistHeapNode<>(9);
        node.leftChild = leftChild;
        node.rightChild = rightChild;

        node.swapChildren();

        assertTrue(Arrays.equals(new Object[]{leftChild, rightChild}, new Object[]{node.rightChild, node.leftChild}));
    }

    @Test
    public void canClearNode() {
        LeftistHeapNode<Integer> node = new LeftistHeapNode<>(5);
        LeftistHeapNode<Integer> leftChild = new LeftistHeapNode<>(8);
        node.leftChild = leftChild;

        node.clear();

        assertTrue(node.isEmpty());
    }
}
