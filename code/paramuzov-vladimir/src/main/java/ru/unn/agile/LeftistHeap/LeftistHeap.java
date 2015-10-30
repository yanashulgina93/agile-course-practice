package ru.unn.agile.LeftistHeap;

import java.util.ArrayList;

public class LeftistHeap <Type extends Comparable<Type>> {
    LeftistHeap() {
        root = null;
        size = 0;
    }

    public Object[] toSortedArray(){
        ArrayList<Type> sortedList = new ArrayList<>();
        while(size > 0) {
            sortedList.add(extractMin());
        }

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
        if(key.compareTo(heapNode.getElement()) > 0) {
            return;
        }

        if(heapNode == root) {
            heapNode.setElement(key);
            return;
        }

        cut(heapNode);
        heapNode.setElement(key);
        root = merge(root, heapNode);
    }

    public boolean isEmpty() {
        return root == null;
    }

    public void merge(LeftistHeap<Type> rhsHeap) {
        if (this == rhsHeap) {
            return;
        }

        root = merge(root, rhsHeap.getRoot());
        size += rhsHeap.getSize();
    }

    public void insert(final Type newElement) {
        root = merge(root, new LeftistHeapNode<>(newElement));
        size++;
    }

    public Type extractMin() {
        if(isEmpty()) {
            return null;
        }

        Type minElement = root.getElement();
        root = merge(root.getLeftChild(), root.getRightChild());
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
        boolean left = heapNode.getParent().getLeftChild() == heapNode;
        LeftistHeapNode<Type> replacement = merge(heapNode.getLeftChild(), heapNode.getRightChild());
        LeftistHeapNode<Type> parent = heapNode.getParent();
        if(left) {
            parent.setLeftChild(replacement);
        } else {
            parent.setRightChild(replacement);
        }

        if(replacement != null) {
            replacement.setParent(parent);
        }

        if(parent.hasLeftChild() && !parent.hasRightChild()) {
            parent.swapChildren();
            parent.setRank(0);
        } else {
            if (parent.haveChildren() && parent.isRightChildHasLargerRank()) {
                parent.swapChildren();
                parent.setRank(parent.getRightChild().getRank() + 1);
            } else {
                parent.setRank(0);
            }
        }

        heapNode.clear();
    }

    private LeftistHeapNode<Type> merge(LeftistHeapNode<Type> firstRoot, LeftistHeapNode<Type> secondRoot) {
        if(firstRoot == null) {
            return secondRoot;
        }

        if(secondRoot == null) {
            return firstRoot;
        }

        if(firstRoot.compareTo(secondRoot) > 0) {
            LeftistHeapNode<Type> temp = firstRoot;
            firstRoot = secondRoot;
            secondRoot = temp;
        }

        firstRoot.setRightChild(merge(firstRoot.getRightChild(), secondRoot));
        firstRoot.getRightChild().setParent(firstRoot);

        if(!firstRoot.hasLeftChild()) {
            firstRoot.swapChildren();
        } else {
            if(firstRoot.isRightChildHasLargerRank()) {
                firstRoot.swapChildren();
            }

            firstRoot.setRank(firstRoot.getRightChild().getRank() + 1);
        }

        return firstRoot;
    }

    private LeftistHeapNode<Type> root;
    private int size;
}
