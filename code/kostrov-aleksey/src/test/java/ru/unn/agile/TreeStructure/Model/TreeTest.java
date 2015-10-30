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
        assertEquals(false, tree.addNode(100, "child"));
    }

    @Test
    public void canSearchByKeyword() {
        tree = new TreeStructure<>(100, "root");
        tree.addNode(50, "leftChild");
        tree.addNode(150, "rightChild");
        assertEquals("leftChild", tree.searchByKey(50).getData());
    }

    @Test
    public void canSearchNotAddElement() {
        tree = new TreeStructure<>(100, "root");
        tree.addNode(50, "leftChild");
        assertEquals(null, tree.searchByKey(20));
    }
}
