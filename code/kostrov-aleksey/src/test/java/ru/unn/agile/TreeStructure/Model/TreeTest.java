package ru.unn.agile.TreeStructure.Model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TreeTest {
    private TreeStructure<Integer, java.lang.String> tree = null;

    @Before
    public void initializeTree() {
        tree = new TreeStructure<>();
      }

    @Test
    public void createTreeWithoutParameterIsNotNull() {
        assertNotNull(tree);
    }

    @Test
    public void createTreeWithParameterIsNotNull() {
        tree = new TreeStructure<>(50, "root");
        assertNotNull(tree);
    }

    @Test
    public void canAddNewNodeRoot() {
        tree = new TreeStructure<>();
        assertTrue(tree.addNode(100, "root"));
    }

    @Test
    public void canAddNewNodeNotRoot() {
        tree = new TreeStructure<>(100, "root");
        assertTrue(tree.addNode(80, "child"));
    }

    @Test
    public void canAddNodeWithDuplicateKey() {
        tree = new TreeStructure<>(100, "root");
        boolean resultAdd = tree.addNode(100, "child");
        assertFalse(resultAdd);
    }

    @Test
    public void canSearchByKeyword() {
        tree = new TreeStructure<>(100, "root");
        tree.addNode(50, "leftChild");
        tree.addNode(150, "rightChild");
        NodeStructure searchedNode = tree.searchByKey(50);
        assertEquals("leftChild", searchedNode.getData());
    }

    @Test
    public void canSearchNotAddedElement() {
        tree = new TreeStructure<>(100, "root");
        tree.addNode(50, "leftChild");
        assertNull(tree.searchByKey(20));
    }

    @Test
    public void canDeleteSubTree() {
        tree = new TreeStructure<>(100, "root");
        tree.addNode(50, "1");
        tree.addNode(150, "2");
        tree.addNode(40, "3");
        tree.addNode(60, "4");
        assertTrue(tree.truncateByKey(50));
    }

    @Test
    public void canDeleteAllTree() {
        tree = new TreeStructure<>(200, "root");
        tree.addNode(100, "Child#1");
        tree.addNode(150, "Child#2");
        tree.addNode(210, "Child#3");
        tree.addNode(220, "Child#4");
        assertTrue(tree.truncateByKey(200));
    }
}
