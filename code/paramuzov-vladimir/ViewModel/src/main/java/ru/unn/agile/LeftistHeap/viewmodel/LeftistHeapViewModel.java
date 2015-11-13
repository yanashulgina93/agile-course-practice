package ru.unn.agile.LeftistHeap.viewmodel;

import ru.unn.agile.LeftistHeap.model.LeftistHeap;
import ru.unn.agile.LeftistHeap.model.LeftistHeapNode;

import java.util.Arrays;

public class LeftistHeapViewModel {
    public static final String ERROR_INSERT_WRONG_VALUE = "Wrong value to insert.\n";
    public static final String ERROR_DELETE_WRONG_VALUE = "Wrong value to delete.\n";
    public static final String ERROR_VALUE_NOT_FIND = "Element to delete not found.\n";

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
            addErrorMessage(ERROR_VALUE_NOT_FIND);
            return;
        }
        deleteErrorMessage(ERROR_VALUE_NOT_FIND);
        heap.delete(nodeToDelete);
        Object[] content = heap.toSortedArrayWithoutDelete();
        heapContent = Arrays.toString(content);
    }

    private enum Fields { INSERT, DELETE }

    private void isNumberInFieldCorrect(final String numberInField,
                                        final Fields field) {
        String error = field.equals(Fields.DELETE) ? ERROR_DELETE_WRONG_VALUE
                                                   : ERROR_INSERT_WRONG_VALUE;
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
        deleteErrorMessage(ERROR_VALUE_NOT_FIND);
        setFieldState(field, true);
        setFieldValue(field, numberInField);
    }

    private void deleteErrorMessage(final String error) {
        if (errorText.contains(error)) {
            errorText = errorText.replace(error, "");
        }
    }

    private void addErrorMessage(final String error) {
        if (!errorText.contains(error)) {
            errorText += error;
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
