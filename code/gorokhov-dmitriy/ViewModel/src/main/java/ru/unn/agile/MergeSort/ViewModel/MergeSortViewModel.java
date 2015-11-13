package ru.unn.agile.MergeSort.ViewModel;

public class MergeSortViewModel {
    private boolean sortButtonEnabled;
    private String sourceArray;

    public MergeSortViewModel() {
        sortButtonEnabled = false;
        sourceArray = "";
    }

    public String getSortingArray() {
        return sourceArray;
    }

    public void setSortingArray(String sourceArray) {
        this.sourceArray = sourceArray;
        sortButtonEnabled = true;
    }

    public boolean isSortButtonEnabled() {
        return sortButtonEnabled;
    }
}
