package ru.unn.agile.TreeStructure.Model;


public class NodeStructure<TypeKey extends Comparable<TypeKey>, TypeData> {
    private TypeData data;
    private final TypeKey key;
    private NodeStructure<TypeKey, TypeData> leftChild, rightChild;

    public NodeStructure(final TypeKey key, final TypeData data) {
        leftChild = null;
        rightChild = null;
        this.key = key;
        this.data = data;
    }

    public TypeKey getKey() {
        return key;
    }

    public TypeData getData() {
        return this.data;
    }

    public void setData(final TypeData data) {
        this.data = data;
    }

    public void setLeftChild(final NodeStructure<TypeKey, TypeData> node) {
       leftChild = node;
    }

    public NodeStructure<TypeKey, TypeData> getLeftChild() {
        return leftChild;
    }

    public void setRightChild(final NodeStructure<TypeKey, TypeData> node) {
        rightChild = node;
    }

    public NodeStructure<TypeKey, TypeData> getRightChild() {
        return rightChild;
    }
}
