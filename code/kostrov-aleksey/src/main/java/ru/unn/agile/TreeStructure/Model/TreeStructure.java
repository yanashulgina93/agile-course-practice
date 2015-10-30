package ru.unn.agile.TreeStructure.Model;


public class TreeStructure<TypeKey extends Comparable<TypeKey>, TypeData> {
    private NodeStructure<TypeKey, TypeData> root;

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
        NodeStructure<TypeKey, TypeData> newNode = new NodeStructure<>(key, data);
        return add(root, newNode);
    }

    private boolean add(final NodeStructure<TypeKey, TypeData> currentNode,
                        final NodeStructure<TypeKey, TypeData> newNode) {

        if (currentNode.getKey().compareTo(newNode.getKey()) == 1) {
            if (currentNode.getLeftChild() == null) {
                currentNode.setLeftChild(newNode);
                return true;
            }
            return add(currentNode.getLeftChild(), newNode);
        }
        if (currentNode.getKey().compareTo(newNode.getKey()) == -1) {
            if (currentNode.getRightChild() == null) {
                currentNode.setRightChild(newNode);
                return true;
            }
            return add(currentNode.getRightChild(), newNode);
        }
        return false;
    }

    public NodeStructure<TypeKey, TypeData> searchByKey(final TypeKey key) {
        return search(root, key);
    }

    private NodeStructure<TypeKey, TypeData> search(final NodeStructure<TypeKey, TypeData> node,
                                                    final TypeKey key) {
        if (node == null) {
            return null;
        }
        if (node.getKey().compareTo(key) == 0) {
            return node;
        }
        if (node.getKey().compareTo(key) == 1) {
            return search(node.getLeftChild(), key);
        } else {
            return search(node.getRightChild(), key);
        }
    }
}
