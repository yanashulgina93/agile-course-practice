package ru.unn.agile.TreeStructure.viewmodel;

import ru.unn.agile.TreeStructure.Model.Node;
import ru.unn.agile.TreeStructure.Model.Tree;

public class TreeViewModel {
    private String key;
    private String data;
    private Operation operation;
    private Tree<Integer, String> tree;
    private String message;
    private String dataBySearchedElement;
    private Node tempNode;
    private boolean doButtonEnabled;
    private boolean textDataFieldEnabled;

    public TreeViewModel() {
        key = "";
        data = "";
        message = "";
        tempNode = null;
        dataBySearchedElement = "";
        operation = Operation.INSERT;
        doButtonEnabled = false;
        textDataFieldEnabled = false;
        tree = new Tree<>();
    }

    public boolean isDoButtonEnabled() {
        if (key.isEmpty()) {
            doButtonEnabled = false;
        } else {
            doButtonEnabled = true;
        }
        return doButtonEnabled;
    }

    public boolean isDataTextFieldEnabled() {
        if (operation == Operation.INSERT) {
            textDataFieldEnabled = true;
        } else {
            textDataFieldEnabled = false;
        }
        return textDataFieldEnabled;
    }

    public void setKey(final String key) {
        this.key = key;
        if (!key.isEmpty()) {
            doButtonEnabled = true;
        }
    }

    public void setData(final String data) {
        this.data = data;
    }

    public void setOperation(final Operation operation) {
        this.operation = operation;
    }

    public void doOperation() {
        try {
            switch (operation) {
                case INSERT:
                    if (tree.addNode(Integer.valueOf(key), data)) {
                        message = "Node was added successfully";
                    } else {
                        message = "Node with this key already exists.";
                    }
                    break;
                case SEARCH:
                    tempNode = tree.searchByKey(Integer.valueOf(key));
                    if (tempNode == null) {
                        message = "Element not found";
                    } else {
                        dataBySearchedElement = (String) (tempNode.getData());
                        message = "";
                    }
                    break;
                case TRUNCATE:
                    try {
                        tree.truncateByKey(Integer.valueOf(key));
                        message = "Truncate was successfully";
                    } catch (Exception e) {
                        message = "It is not possible, element not found";
                    }
                    break;
                default:
                    message = "Only INSERT, SEARCH and TRUNCATE";
                    break;
            }
        } catch (Exception e) {
            message = "Key is not correct";
        }
    }

    public Operation getOperation() {
        return operation;
    }

    public String getSearchedData() {
        return dataBySearchedElement;
    }

    public String getErrorMessage() {
        return message;
    }

    public enum Operation {
        INSERT("Insert"),
        SEARCH("Search_by_key"),
        TRUNCATE("Truncate"),
        OTHER("Other");
        private final String name;

        private Operation(final String name) {
            this.name = name;
        }

        public String toString() {
            return name;
        }
    }

}
