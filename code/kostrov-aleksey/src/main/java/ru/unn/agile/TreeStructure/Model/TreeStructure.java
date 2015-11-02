package ru.unn.agile.TreeStructure.Model;

public class TreeStructure<TypeKey extends Comparable<TypeKey>, TypeData> {
    private NodeStructure<TypeKey, TypeData> root;
    static final int FIRST_VALUE_MORE = 1;
    static final int FIRST_VALUE_LESS = -1;
    static final int VALUES_ARE_EQUAL = 0;

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

        if (currentNode.getKey().compareTo(newNode.getKey()) == FIRST_VALUE_MORE) {
            if (currentNode.getLeftChild() == null) {
                currentNode.setLeftChild(newNode);
                newNode.setParent(currentNode);
                return true;
            }
            return add(currentNode.getLeftChild(), newNode);
        }
        if (currentNode.getKey().compareTo(newNode.getKey()) == FIRST_VALUE_LESS) {
            if (currentNode.getRightChild() == null) {
                currentNode.setRightChild(newNode);
                newNode.setParent(currentNode);
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
        if (node.getKey().compareTo(key) == VALUES_ARE_EQUAL) {
            return node;
        }
        if (node.getKey().compareTo(key) == FIRST_VALUE_MORE) {
            return search(node.getLeftChild(), key);
        } else {
            return search(node.getRightChild(), key);
        }
    }

    public boolean truncateByKey(final TypeKey key) {
        NodeStructure<TypeKey, TypeData> foundNode;
        foundNode = searchByKey(key);

        if (foundNode == null) {
            return false;
        }
        if (!root.equals(foundNode)) {
            if (foundNode.getParent().getRightChild().getKey().compareTo(key) == VALUES_ARE_EQUAL) {
                foundNode.getParent().setRightChild(null);
            } else {
                foundNode.getParent().setLeftChild(null);
            }
        }
        deleteNode(foundNode);
        if (root.equals(foundNode)) {
            root = null;
        }
        return true;
    }

    private void deleteNode(final NodeStructure<TypeKey, TypeData> foundNode) {
        if (foundNode != null) {
            deleteNode(foundNode.getLeftChild());
            deleteNode(foundNode.getRightChild());
            foundNode.setLeftChild(null);
            foundNode.setRightChild(null);
            foundNode.setParent(null);
        }
    }
}
