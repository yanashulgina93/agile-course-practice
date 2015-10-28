package ru.unn.agile.LeftistHeap;

public class LeftistHeapNode <Type extends Comparable<Type>>{
    LeftistHeapNode(Type element){
        this(element,null,null,null);
    }
    public void swapChildren(){
        LeftistHeapNode<Type> temp = leftChild;
        leftChild = rightChild;
        rightChild = temp;
    }
    public void clear() {
        this.element = null;
        this.leftChild = null;
        this.rightChild = null;
        this.parent = null;
        this.nullPathLength = 0;
    }
    public boolean hasLeftChild(){
        return leftChild != null;
    }
    public boolean hasRightChild(){
        return rightChild != null;
    }
    public boolean haveChildren(){
        return hasLeftChild() && hasRightChild();
    }
    public int compareTo(LeftistHeapNode<Type> rhsNode){
        return this.element.compareTo(rhsNode.element);
    }
    public Type element;
    public LeftistHeapNode<Type> leftChild;
    public LeftistHeapNode<Type> rightChild;
    public LeftistHeapNode<Type> parent;
    public int nullPathLength;

    private LeftistHeapNode(Type element, LeftistHeapNode<Type> leftChild, LeftistHeapNode<Type> rightChild, LeftistHeapNode<Type> parent){
        this.element = element;
        this.leftChild = leftChild;
        this.rightChild = rightChild;
        this.parent = parent;
        this.nullPathLength = 0;
    }
}
