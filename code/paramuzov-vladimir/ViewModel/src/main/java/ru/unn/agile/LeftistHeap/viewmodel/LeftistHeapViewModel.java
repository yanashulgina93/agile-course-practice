package ru.unn.agile.LeftistHeap.viewmodel;

import ru.unn.agile.LeftistHeap.model.LeftistHeap;
import ru.unn.agile.LeftistHeap.model.LeftistHeapNode;

import java.util.Arrays;
import java.util.List;

public class LeftistHeapViewModel {
    public void valueFieldFocusLost() {
        logger.addMessage(LogMessages.KEY_VALUE_FIELD_CHANGED.getMessage()
                            + keyValue);
    }

    public enum Errors {
        FIELD_BAD_FORMAT("Wrong value in text field. Only integers, please.\n"),
        VALUE_TO_DELETE_NOT_FOUND("Element to delete not found.\n");

        private String errorMessage;

        Errors(final String errorMessage) {
            this.errorMessage = errorMessage;
        }

        public String getMessage() {
            return errorMessage;
        }
    }

    public enum Operations {
        INSERT("Insert number"),
        DELETE("Delete number");

        private String operation;

        Operations(final String operation) {
            this.operation = operation;
        }

        @Override
        public String toString() {
            return operation;
        }
    }

    public enum LogMessages {
        KEY_VALUE_FIELD_CHANGED("Key value changed to "),
        OPERATION_CHANGED("Operation was changed to "),
        BUTTON_PRESSED("ApplyButton was pressed. ");

        private String logMessage;

        LogMessages(String logMessage) {
            this.logMessage = logMessage;
        }

        public String getMessage() {
            return logMessage;
        }
    }

    private final LeftistHeap<Integer> heap;
    private boolean applyButtonEnabled = false;
    private String keyValue;
    private String heapContent = "[]";
    private String errorText = "";
    private Operations operation = Operations.INSERT;
    private ILeftistHeapLogger logger;

    public LeftistHeapViewModel(ILeftistHeapLogger logger) {
        if (logger == null) {
            throw new NullPointerException("Logger is null");
        }

        heap = new LeftistHeap<>();
        this.logger = logger;
    }

    public boolean isApplyButtonEnabled() {
        return applyButtonEnabled;
    }

    public void setKeyValue(final String keyValue) {
        if ("".equals(keyValue)) {
            errorText = "";
            applyButtonEnabled = false;
            return;
        }

        if (!keyValue.matches("\\d+|-\\d+")) {
            errorText = Errors.FIELD_BAD_FORMAT.getMessage();
            applyButtonEnabled = false;
            return;
        }

        errorText = "";
        applyButtonEnabled = true;
        this.keyValue = keyValue;
    }

    public void setOperation(final Operations operation) {
        this.operation = operation;

        logger.addMessage(LogMessages.OPERATION_CHANGED.getMessage()
                          + "`" + operation.toString() + "`");
    }

    public Operations getOperation() {
        return operation;
    }

    public void applyOperation() {
        logger.addMessage(getApplyOperationLogMessage());

        if (operation == Operations.INSERT) {
            insertElement();
        } else {
            deleteElement();
        }
    }

    public String getHeapContent() {
        return heapContent;
    }

    public String getErrorText() {
        return errorText;
    }

    public ILeftistHeapLogger getLogger() {
        return logger;
    }

    private void insertElement() {
            heap.insert(Integer.parseInt(keyValue));
            Object[] content = heap.toSortedArray();
            heapContent = Arrays.toString(content);
    }

    private void deleteElement() {
        LeftistHeapNode<Integer> nodeToDelete;
        try {
            nodeToDelete = heap.findNodeByKey(Integer.parseInt(keyValue));
        } catch (NullPointerException e) {
            errorText =  Errors.VALUE_TO_DELETE_NOT_FOUND.getMessage();
            return;
        }
        errorText = "";
        heap.delete(nodeToDelete);
        Object[] content = heap.toSortedArray();
        heapContent = Arrays.toString(content);
    }

    private String getApplyOperationLogMessage() {
        return LogMessages.BUTTON_PRESSED.getMessage()
               + "Operation: " + operation.toString()
               + "; Value: " + keyValue;
    }
}
