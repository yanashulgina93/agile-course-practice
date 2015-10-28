package ru.unn.agile.LeftistHeap;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LeftistHeapNodeTest {
    @Test
    public void canSwapChildren(){
        LeftistHeapNode<Integer> node = new LeftistHeapNode<>(5);
        LeftistHeapNode<Integer> leftChild = new LeftistHeapNode<>(8);
        LeftistHeapNode<Integer> rightChild = new LeftistHeapNode<>(9);
        node.leftChild = leftChild;
        node.rightChild = rightChild;

        node.swapChildren();

        assertEquals(leftChild, node.rightChild);
        assertEquals(rightChild, node.leftChild);
    }
    @Test
    public void canClearNode(){
        LeftistHeapNode<Integer> node = new LeftistHeapNode<>(5);
        LeftistHeapNode<Integer> leftChild = new LeftistHeapNode<>(8);
        node.leftChild = leftChild;

        node.clear();

        assertEmptyNode(node);
    }
    private void assertEmptyNode(LeftistHeapNode node){
        assertEquals(null, node.element);
        assertEquals(null, node.leftChild);
        assertEquals(null, node.rightChild);
        assertEquals(null, node.parent);
        assertEquals(0, node.nullPathLength);
    }
}
