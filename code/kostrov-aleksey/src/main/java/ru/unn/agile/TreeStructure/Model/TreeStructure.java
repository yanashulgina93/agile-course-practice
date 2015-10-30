package ru.unn.agile.TreeStructure.Model;


public class TreeStructure<TypeKey extends Comparable, TypeData> {
    private NodeStructure root;

    public TreeStructure() {
        root = null;
    }

    public TreeStructure(final TypeKey key, final TypeData data) {
        root = new NodeStructure<>(key, data);

    }

    public boolean addNode(final TypeKey key, final TypeData data) {
        if (root == null) {
            root = new NodeStructure<>(key, data);
            return true;
        }
        return false;
    }
}
