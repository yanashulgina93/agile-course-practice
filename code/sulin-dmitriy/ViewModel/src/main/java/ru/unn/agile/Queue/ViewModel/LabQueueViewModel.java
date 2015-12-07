package ru.unn.agile.Queue.ViewModel;

import ru.unn.agile.Queue.Model.LabQueue;

import java.util.Arrays;

public class LabQueueViewModel {

    private final LabQueue<String> queue = new LabQueue<>();
    private int size;
    private String headElement;
    private String dataInput;
    private String result;
    private boolean isFindButtonEnabled;
    private boolean isPopButtonEnabled;
    private final boolean isPushButtonEnabled;
    private final String errorMessage;

    public LabQueueViewModel() {
        size = 0;
        dataInput = "";
        result = "";
        headElement = "";
        isFindButtonEnabled = false;
        isPopButtonEnabled = false;
        isPushButtonEnabled = true;
        errorMessage = "Element not found.";
    }

    public boolean isFindButtonEnabled() {
        return isFindButtonEnabled;
    }

    public boolean isPopButtonEnabled() {
        return isPopButtonEnabled;
    }

    public boolean isPushButtonEnabled() {
        return isPushButtonEnabled;
    }

    public int getSize() {
        return size;
    }

    public void updateSize() {
        size = queue.getSize();
    }

    public String getHeadElement() {
        return headElement;
    }

    public void updateHeadElement() {
        headElement = queue.getHead();
    }

    public String getDataInput() {
        return dataInput;
    }

    public String getResult() {
        return result;
    }

    public void setDataInput(final String newDataInput) {
        dataInput = newDataInput;
    }

    public void setResult(final String newDataOutput) {
        result = newDataOutput;
    }

    public void setFindButtonEnabled(final boolean newValue) {
        isFindButtonEnabled = newValue;
    }

    public void setPopButtonEnabled(final boolean newValue) {
        isPopButtonEnabled = newValue;
    }

    public void pushElement() {
        queue.push(dataInput);
        updateHeadElement();
        updateSize();
        setFindButtonEnabled(true);
        setPopButtonEnabled(true);
     }

    public void popElement() {
        String tempValue = queue.pop();
        updateHeadElement();
        updateSize();
        setResult(tempValue);
        if (queue.isEmpty()) {
            setFindButtonEnabled(false);
            setPopButtonEnabled(false);
        }
    }

    public void findElement() {
        String outputMessage;
        String value = getDataInput();
        try {
            outputMessage = Integer.toString(queue.findElement(value) + 1);
        } catch (Exception exception) {
            outputMessage = errorMessage;
        }
        setResult(outputMessage);
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public String[] getQueueAsArray() {
        String[] array = queue.convertToStringArray();
        return Arrays.copyOf(array, array.length, String[].class);
    }
}
