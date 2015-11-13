package ru.unn.agile.LeftistHeap.viewmodel;

import ru.unn.agile.LeftistHeap.model.LeftistHeap;

import java.util.Arrays;

public class LeftistHeapViewModel {
    private LeftistHeap<Integer> heap;
    private boolean insertButtonEnabled = false;
    private String numberToInsert;
    private String heapContent;

    public LeftistHeapViewModel() {
        heap = new LeftistHeap<>();
    }

    public boolean isInsertButtonEnabled() {
        return insertButtonEnabled;
    }

    public void setNumberToInsert(String numberToInsert) {
        if(numberToInsert.equals("")) {
            insertButtonEnabled = false;
            return;
        }
        this.numberToInsert = numberToInsert;
        insertButtonEnabled = true;
    }

    public String getHeapContent() {
        return heapContent;
    }

    public void insert() {
        heap.insert(Integer.parseInt(numberToInsert));
        Object[] content = heap.toSortedArrayWithoutDelete();
        heapContent = Arrays.toString(content);
    }
}
