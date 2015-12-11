package ru.unn.agile.MergeSort.ViewModel;

import java.util.ArrayList;

public interface IMergeSortLogger {
    void writeRecord(final String record);

    String readRecord(final int recordNumber);

    int getRecordsCount();

    ArrayList<String> getRecordsList();
}
