package ru.unn.agile.TreeStructure.Model;

import org.junit.Test;

import static org.junit.Assert.*;

public class NodeTest {
    private NodeStructure<Integer> node;

    @Test
    public void canSetNumber() {
        node = new NodeStructure<>();
        node.setData(5);
        assertEquals((Integer)5,node.getData());
    }
}
