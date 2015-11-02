package ru.unn.agile.TreeStructure.Model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class NodeTest {
    private NodeStructure<Integer, java.lang.String> nodeWithDataString, nodeChild;
    private NodeStructure<Integer, Integer> nodeWithDataNumber;

    @Before
    public void initializeNode() {
        nodeWithDataString = new NodeStructure<>(1, "initialString");
    }

    @Test
    public void canCreateNode() {
        assertNotNull(nodeWithDataString);
    }

    @Test
    public void canGetKey() {
        Integer resultGetKey = nodeWithDataString.getKey();
        assertEquals((Integer) 1, resultGetKey);
    }

    @Test
    public void canSetString() {
        nodeWithDataString.setData("answer");
        assertEquals("answer", nodeWithDataString.getData());
    }

    @Test
    public void canSetNumber() {
        nodeWithDataNumber = new NodeStructure<>(1, 10);
        nodeWithDataNumber.setData(20);
        assertEquals((Integer) 20, nodeWithDataNumber.getData());
    }

    @Test
    public void canGetNumber() {
        nodeWithDataNumber = new NodeStructure<>(10, 10);
        assertEquals((Integer) 10, nodeWithDataNumber.getData());
    }

    @Test
    public void canGetString() {
        assertEquals("initialString", nodeWithDataString.getData());
    }

    @Test
    public void canSetLeftChild() {
        nodeChild = new NodeStructure<>(80, "child");
        nodeWithDataString.setLeftChild(nodeChild);
        assertNotNull(nodeWithDataString.getLeftChild());
    }

    @Test
    public void canGetLeftChild() {
        nodeChild = new NodeStructure<>(80, "child");
        nodeWithDataString.setLeftChild(nodeChild);
        assertEquals(nodeChild, nodeWithDataString.getLeftChild());
    }

    @Test
    public void canSetRightChild() {
        nodeChild = new NodeStructure<>(80, "child");
        nodeWithDataString.setRightChild(nodeChild);
        assertNotNull(nodeWithDataString.getRightChild());
    }

    @Test
    public void canGetRightChild() {
        nodeChild = new NodeStructure<>(80, "child");
        nodeWithDataString.setRightChild(nodeChild);
        assertEquals(nodeChild, nodeWithDataString.getRightChild());
    }
}
