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
        nodeWithDataString = new NodeStructure<>(100, "");
        assertEquals((Integer) 100, nodeWithDataString.getKey());
    }

    @Test
    public void canSetString() {
        nodeWithDataString = new NodeStructure<>(1, "question");
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
        nodeWithDataString = new NodeStructure<>(10, "qwerty");
        assertEquals("qwerty", nodeWithDataString.getData());
    }

    @Test
    public void canSetLeftChild() {
        nodeWithDataString = new NodeStructure<>(100, "root");
        nodeChild = new NodeStructure<>(80, "child");
        nodeWithDataString.setLeftChild(nodeChild);
        assertEquals(nodeChild, nodeWithDataString.getLeftChild());
    }

    @Test
    public void canGetLeftChild() {
        nodeWithDataString = new NodeStructure<>(100, "root");
        nodeChild = new NodeStructure<>(80, "child");
        nodeWithDataString.setLeftChild(nodeChild);
        assertEquals(nodeChild, nodeWithDataString.getLeftChild());
    }

    @Test
    public void canSetRightChild() {
        nodeWithDataString = new NodeStructure<>(100, "root");
        nodeChild = new NodeStructure<>(80, "child");
        nodeWithDataString.setRightChild(nodeChild);
        assertEquals(nodeChild, nodeWithDataString.getRightChild());
    }

    @Test
    public void canGetRightChild() {
        nodeWithDataString = new NodeStructure<>(100, "root");
        nodeChild = new NodeStructure<>(80, "child");
        nodeWithDataString.setRightChild(nodeChild);
        assertEquals(nodeChild, nodeWithDataString.getRightChild());
    }
}
