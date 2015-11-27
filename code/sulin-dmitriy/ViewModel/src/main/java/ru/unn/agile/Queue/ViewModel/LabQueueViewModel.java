package ru.unn.agile.Queue.ViewModel;

import ru.unn.agile.Queue.Model.LabQueue;

public class LabQueueViewModel {

    private final LabQueue<String> testQueue = new LabQueue<>();
    private int fieldForSize;
    private String fieldForHeadElement;
    private String fieldForDataInput;
    private String fieldForDataOutput;
    private boolean isFindButtonEnabled;
    private boolean isPopButtonEnabled;
    private final boolean isPushButtonEnabled;

    public LabQueueViewModel() {
        fieldForSize = 0;
        fieldForDataInput = "";
        fieldForDataOutput = "";
        fieldForHeadElement = "";
        isFindButtonEnabled = false;
        isPopButtonEnabled = false;
        isPushButtonEnabled = true;
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

    public int getFieldForSize() {
        return fieldForSize;
    }

    public void updateFieldForSize() {
        fieldForSize = testQueue.getSize();
    }

    public String getFieldForHeadElement() {
        return fieldForHeadElement;
    }

    public void updateFieldForHeadElement() {
        fieldForHeadElement = testQueue.getHead();
    }

    public String getFieldForDataInput() {
        return fieldForDataInput;
    }

    public void setFieldForDataInput(final String newValue) {
        fieldForDataInput = newValue;
    }

    public String getFieldForDataOutput() {
        return fieldForDataOutput;
    }

    public void setFieldForDataOutput(final String newValue) {
        fieldForDataOutput = newValue;
    }

    public void setFindButtonEnabled(final boolean newValue) {
        isFindButtonEnabled = newValue;
    }

    public void setPopButtonEnabled(final boolean newValue) {
        isPopButtonEnabled = newValue;
    }

    public void pushElement() {
        testQueue.push(fieldForDataInput);
        updateFieldForHeadElement();
        updateFieldForSize();
        setFindButtonEnabled(true);
        setPopButtonEnabled(true);
     }

    public void popElement() {
        String tempValue = testQueue.pop();
        updateFieldForHeadElement();
        updateFieldForSize();
        setFieldForDataOutput(tempValue);
        if (testQueue.isEmpty()) {
            setFindButtonEnabled(false);
            setPopButtonEnabled(false);
        }
    }

    public void findElement() {
        int answer;
        String value = getFieldForDataInput();
        try {
            answer = testQueue.findElement(value);
        } catch (Exception exception) {
            setFieldForDataOutput("Element Not Found");
            return;
        }
        setFieldForDataOutput(Integer.toString(answer));
    }
}
