package ru.unn.agile.TreeStructure.Model;

public class TreeStructure<TypeKey extends Comparable<TypeKey>, TypeData> {
    private NodeStructure<TypeKey, TypeData> root;
    private static int firstValueMore = 1;
    private static int firstValueLess = -1;
    private static int valuesAreEqual = 0;

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

        if (currentNode.getKey().compareTo(newNode.getKey()) == firstValueMore) {
            if (currentNode.getLeftChild() == null) {
                currentNode.setLeftChild(newNode);
                return true;
            }
            return add(currentNode.getLeftChild(), newNode);
        }
        if (currentNode.getKey().compareTo(newNode.getKey()) == firstValueLess) {
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
        if (node.getKey().compareTo(key) == valuesAreEqual) {
            return node;
        }
        if (node.getKey().compareTo(key) == firstValueMore) {
            return search(node.getLeftChild(), key);
        } else {
            return search(node.getRightChild(), key);
        }
    }

    public boolean truncateByKey(final TypeKey key) {
        NodeStructure<TypeKey, TypeData> foundNode = searchByKey(key);
        if (foundNode == null) {
            return false;
        }
        deleteNode(foundNode);
        return true;
    }

    private void deleteNode(final NodeStructure<TypeKey, TypeData> foundNode) {
        if (foundNode != null) {
            deleteNode(foundNode.getLeftChild());
            deleteNode(foundNode.getRightChild());
            foundNode.setLeftChild(null);
            foundNode.setRightChild(null);
        }
    }
}
