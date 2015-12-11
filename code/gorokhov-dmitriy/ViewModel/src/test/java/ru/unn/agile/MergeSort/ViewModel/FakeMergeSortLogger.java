package ru.unn.agile.MergeSort.ViewModel;

import java.util.ArrayList;

public class FakeMergeSortLogger implements IMergeSortLogger {
    @Override
    public void writeRecord(final String record) {
        recordsList.add(record);
    }

    @Override
    public String readRecord(final int recordNumber) {
        if (recordNumber >= getRecordsCount()) {
            return null;
        }

        return recordsList.get(recordNumber);
    }

    @Override
    public int getRecordsCount() {
        return recordsList.size();
    }

    @Override
    public ArrayList<String> getRecordsList() {
        return recordsList;
    }

    private ArrayList<String> recordsList = new ArrayList<>();
}

