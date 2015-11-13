package ru.unn.agile.LeftistHeap.model;

import org.junit.Test;


import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertTrue;

public class LeftistHeapNodeTest {
    @Test
    public void canSwapChildren() {
        LeftistHeapNode<Integer> node = new LeftistHeapNode<>(5);
        LeftistHeapNode<Integer> leftChild = new LeftistHeapNode<>(8);
        LeftistHeapNode<Integer> rightChild = new LeftistHeapNode<>(9);
        node.setLeftChild(leftChild);
        node.setRightChild(rightChild);

        node.swapChildren();

        assertArrayEquals(new Object[]{leftChild, rightChild},
                new Object[]{node.getRightChild(), node.getLeftChild()});
    }

    @Test
    public void canClearNode() {
        LeftistHeapNode<Integer> node = new LeftistHeapNode<>(5);
        LeftistHeapNode<Integer> leftChild = new LeftistHeapNode<>(8);
        node.setLeftChild(leftChild);

        node.clear();

        assertTrue(node.isEmpty());
    }
}
