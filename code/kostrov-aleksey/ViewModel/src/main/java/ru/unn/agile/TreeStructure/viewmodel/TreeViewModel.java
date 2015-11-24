package ru.unn.agile.TreeStructure.viewmodel;

import ru.unn.agile.TreeStructure.Model.Node;
import ru.unn.agile.TreeStructure.Model.Tree;

public class TreeViewModel {
    private String key;
    private String data;
    private Operation operation;
    private Tree tree;
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
        tree = new Tree();
    }

    public boolean isDoButtonEnabled() {
        if (!key.isEmpty())
            doButtonEnabled = true;
        else
            doButtonEnabled = false;
        return doButtonEnabled;
    }

    public boolean isDataTextFieldEnabled() {
        if (operation == Operation.INSERT)
            textDataFieldEnabled = true;
        else
            textDataFieldEnabled = false;
        return textDataFieldEnabled;
    }

    public void setKey(String key) {
        this.key = key;
        if(!key.isEmpty())
            doButtonEnabled = true;
    }

    public void setData(String data) {
        this.data = data;
    }

    public void setOperation(Operation operation) {
        this.operation = operation;
    }

    public void doOperation() {
        switch (operation) {
            case INSERT:
                if(!tree.addNode(Integer.valueOf(key), data))
                    message = "Node with this key already exists.";
                else
                    message = "Node was added successfully";
                break;
            case SEARCH:
                tempNode = tree.searchByKey(Integer.valueOf(key));
                if(tempNode != null) {
                    dataBySearchedElement = (String) (tempNode.getData());
                    message = "";
                } else {
                    message = "Element not found";
                }
                break;
            case TRUNCATE:
                tree.truncateByKey(key);
                break;
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
