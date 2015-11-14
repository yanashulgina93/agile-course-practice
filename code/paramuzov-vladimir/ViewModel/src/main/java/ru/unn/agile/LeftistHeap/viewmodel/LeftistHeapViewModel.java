package ru.unn.agile.LeftistHeap.viewmodel;

import ru.unn.agile.LeftistHeap.model.LeftistHeap;
import ru.unn.agile.LeftistHeap.model.LeftistHeapNode;

import java.util.Arrays;

public class LeftistHeapViewModel {
    public enum Errors {
        INSERT_FIELD_BAD_FORMAT("Wrong value to insert. Only integers, please.\n"),
        DELETE_FIELD_BAD_FORMAT("Wrong value to delete. Only integers, please.\n"),
        VALUE_TO_DELETE_NOT_FOUND("Element to delete not found.\n");

        private String errorMessage;

        Errors(final String errorMessage) {
            this.errorMessage = errorMessage;
        }

        public String getMessage() {
            return errorMessage;
        }
    }

    private enum Fields { INSERT, DELETE }
    private final LeftistHeap<Integer> heap;
    private boolean insertButtonEnabled = false;
    private boolean deleteButtonEnabled = false;
    private String numberToInsert;
    private String heapContent = "[]";
    private String errorText = "";
    private String numberToDelete;

    public LeftistHeapViewModel() {
        heap = new LeftistHeap<>();
    }

    public boolean isInsertButtonEnabled() {
        return insertButtonEnabled;
    }

    public boolean isDeleteButtonEnabled() {
        return deleteButtonEnabled;
    }

    public void setNumberToInsert(final String numberToInsert) {
        isNumberInFieldCorrect(numberToInsert, Fields.INSERT);
    }

    public void setNumberToDelete(final String numberToDelete) {
        isNumberInFieldCorrect(numberToDelete, Fields.DELETE);
    }

    public String getHeapContent() {
        return heapContent;
    }

    public String getErrorText() {
        return errorText;
    }

    public void insertElement() {
            heap.insert(Integer.parseInt(numberToInsert));
            Object[] content = heap.toSortedArrayWithoutDelete();
            heapContent = Arrays.toString(content);
    }

    public void deleteElement() {
        LeftistHeapNode<Integer> nodeToDelete;
        try {
            nodeToDelete = heap.findNodeByKey(Integer.parseInt(numberToDelete));
        } catch (NullPointerException e) {
            addErrorMessage(Errors.VALUE_TO_DELETE_NOT_FOUND);
            return;
        }
        deleteErrorMessage(Errors.VALUE_TO_DELETE_NOT_FOUND);
        heap.delete(nodeToDelete);
        Object[] content = heap.toSortedArrayWithoutDelete();
        heapContent = Arrays.toString(content);
    }

    private void isNumberInFieldCorrect(final String numberInField,
                                        final Fields field) {
        Errors error = field.equals(Fields.DELETE) ? Errors.DELETE_FIELD_BAD_FORMAT
                                                   : Errors.INSERT_FIELD_BAD_FORMAT;
        if ("".equals(numberInField)) {
            deleteErrorMessage(error);
            setFieldState(field, false);
            return;
        }

        if (!numberInField.matches("\\d+|-\\d+")) {
            setFieldState(field, false);
            addErrorMessage(error);
            return;
        }

        deleteErrorMessage(error);
        deleteErrorMessage(Errors.VALUE_TO_DELETE_NOT_FOUND);
        setFieldState(field, true);
        setFieldValue(field, numberInField);
    }

    private void deleteErrorMessage(final Errors error) {
        if (errorText.contains(error.getMessage())) {
            errorText = errorText.replace(error.getMessage(), "");
        }
    }

    private void addErrorMessage(final Errors error) {
        if (!errorText.contains(error.getMessage())) {
            errorText += error.getMessage();
        }
    }

    private void setFieldState(final Fields field, final boolean state) {
        if (field.equals(Fields.INSERT)) {
            insertButtonEnabled = state;
        } else {
            deleteButtonEnabled = state;
        }
    }

    private void setFieldValue(final Fields field, final String value) {
        if (field.equals(Fields.INSERT)) {
            numberToInsert = value;
        } else {
            numberToDelete = value;
        }
    }
}
