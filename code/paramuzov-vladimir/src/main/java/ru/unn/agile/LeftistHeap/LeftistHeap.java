package ru.unn.agile.LeftistHeap;

import java.util.ArrayList;

public class LeftistHeap <Type extends Comparable<Type>> {
    LeftistHeap() {
        root = null;
        size = 0;
    }

    public Object[] toSortedArray(){
        ArrayList<Type> sortedList = new ArrayList<>();
        while(size > 0)
            sortedList.add(extractMin());
        return sortedList.toArray(new Object[sortedList.size()]);
    }

    public void clear() {
        root = null;
        size = 0;
    }

    public void delete(LeftistHeapNode<Type> heapNode) {
        if(heapNode == root) {
            extractMin();
            return;
        }
        cut(heapNode);
        size--;
        heapNode.clear();
    }

    public void decreaseKey(LeftistHeapNode<Type> heapNode, Type key) {
        if(key.compareTo(heapNode.element) > 0)
            return;
        if(heapNode == root){
            heapNode.element = key;
            return;
        }
        cut(heapNode);
        heapNode.element = key;
        root = merge(root,heapNode);
    }

    public boolean isEmpty() {
        return root == null;
    }

    public void merge(LeftistHeap<Type> rhsHeap) {
        if (this == rhsHeap)
            return;
        root = merge(root, rhsHeap.getRoot());
        size += rhsHeap.getSize();
    }

    public void insert(final Type newElement) {
        root = merge(root, new LeftistHeapNode<>(newElement));
        size++;
    }

    public Type extractMin() {
        if(isEmpty())
            return null;
        Type minElement = root.element;
        root = merge(root.leftChild, root.rightChild);
        size--;
        return minElement;
    }

    public final int getSize() {
        return size;
    }

    public final LeftistHeapNode<Type> getRoot() {
        return root;
    }

    private void cut(final LeftistHeapNode<Type> heapNode) {
        boolean left = heapNode.parent.leftChild == heapNode;
        LeftistHeapNode<Type> replacement = merge(heapNode.leftChild,heapNode.rightChild);
        LeftistHeapNode<Type> parent = heapNode.parent;
        if(left)
            parent.leftChild = replacement;
        else
            parent.rightChild = replacement;

        if(replacement != null)
            replacement.parent = parent;
        if(parent.hasLeftChild() && !parent.hasRightChild()) {
            parent.swapChildren();
            parent.nullPathLength = 0;
        }
        else if(parent.haveChildren() && parent.rightChild.nullPathLength > parent.leftChild.nullPathLength) {
            parent.swapChildren();
            parent.nullPathLength = parent.rightChild.nullPathLength + 1;
        }
        else {
            parent.nullPathLength = 0;
        }
        heapNode.clear();
    }

    private LeftistHeapNode<Type> merge(LeftistHeapNode<Type> firstRoot, LeftistHeapNode<Type> secondRoot) {
        if(firstRoot == null)
            return secondRoot;
        if(secondRoot == null)
            return firstRoot;
        if(firstRoot.compareTo(secondRoot) > 0) {
            LeftistHeapNode<Type> temp = firstRoot;
            firstRoot = secondRoot;
            secondRoot = temp;
        }

        firstRoot.rightChild = merge(firstRoot.rightChild, secondRoot);
        firstRoot.rightChild.parent = firstRoot;

        if(!firstRoot.hasLeftChild()) {
            firstRoot.swapChildren();
        }
        else {
            if(firstRoot.leftChild.nullPathLength < firstRoot.rightChild.nullPathLength)
                firstRoot.swapChildren();
            firstRoot.nullPathLength = firstRoot.rightChild.nullPathLength + 1;
        }
        return firstRoot;
    }

    private LeftistHeapNode<Type> root;
    private int size;

}
