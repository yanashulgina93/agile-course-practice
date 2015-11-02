package ru.unn.agile.TreeStructure.Model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TreeTest {
    private TreeStructure<Integer, java.lang.String> tree;

    @Before
    public void initializeTree() {
        tree = new TreeStructure<>(200, "root");
        tree.addNode(100, "Child#1");
        tree.addNode(150, "Child#2");
        tree.addNode(210, "Child#3");
        tree.addNode(220, "Child#4");
        tree.addNode(80, "Child#5");
      }

    @Test
    public void createTreeWithoutParameterIsNotNull() {
        tree = new TreeStructure<>();
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
        assertTrue(tree.addNode(80, "root"));
    }

    @Test
    public void canAddNewNodeNotRoot() {
        boolean resultAddNotRoot = tree.addNode(280, "Child#6");
        assertTrue(resultAddNotRoot);
    }

    @Test
    public void canAddNodeWithDuplicateKey() {
        boolean resultAdd = tree.addNode(100, "child");
        assertFalse(resultAdd);
    }

    @Test
    public void canSearchByKeyword() {
        NodeStructure searchedNode = tree.searchByKey(150);
        assertEquals("Child#2", searchedNode.getData());
    }

    @Test
    public void canSearchNotAddedElement() {
        NodeStructure searchedNode = tree.searchByKey(20);
        assertNull(searchedNode);
    }

    @Test
    public void canDeleteSubTree() {
        boolean resultDelete = tree.truncateByKey(100);
        assertTrue(resultDelete);
    }

    @Test
    public void canDeleteAllTree() {
        boolean resultDeleteTree = tree.truncateByKey(200);
        assertTrue(resultDeleteTree);
    }
}
