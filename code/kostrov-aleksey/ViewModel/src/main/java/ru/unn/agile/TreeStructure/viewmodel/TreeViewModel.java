package ru.unn.agile.TreeStructure.viewmodel;

public class TreeViewModel {
    private String key;
    private String data;
    private Operation operation;
    private boolean doButtonEnabled;

    TreeViewModel() {
        key = "";
        data = "";
        operation = Operation.INSERT;
        doButtonEnabled = false;
    }
    public boolean isDoButtonEnabled() {
        if (key != "")
            doButtonEnabled = true;
        return doButtonEnabled;
    }
    public void setKey(String key) {
        this.key = key;
    }
    public enum Operation {
        INSERT("Insert"),
        SEARCH("Search_by_key"),
        TRUNCATE("Truncate");

        private final String name;

        private Operation(final String name) {
            this.name = name;
        }

        public String toString() {
            return name;
        }
    }
}
