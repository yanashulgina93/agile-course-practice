package ru.unn.agile.Queue.ViewModel;

import ru.unn.agile.Queue.Model.LabQueue;

public class LabQueueViewModel {

    private final LabQueue<String> testQueue = new LabQueue<>();
    private int sizeField;
    private String headElementField;
    private String dataInputField;
    private String dataOutputField;
    private boolean isFindButtonEnabled;
    private boolean isPopButtonEnabled;
    private final boolean isPushButtonEnabled;
    private final String errorMessage;

    public LabQueueViewModel() {
        sizeField = 0;
        dataInputField = "";
        dataOutputField = "";
        headElementField = "";
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

    public int getSizeField() {
        return sizeField;
    }

    public void updateSizeField() {
        sizeField = testQueue.getSize();
    }

    public String getHeadElementField() {
        return headElementField;
    }

    public void updateHeadElementField() {
        headElementField = testQueue.getHead();
    }

    public String getDataInputField() {
        return dataInputField;
    }

    public void setDataInputField(final String newValue) {
        dataInputField = newValue;
    }

    public String getDataOutputField() {
        return dataOutputField;
    }

    public void setDataOutputField(final String newValue) {
        dataOutputField = newValue;
    }

    public void setFindButtonEnabled(final boolean newValue) {
        isFindButtonEnabled = newValue;
    }

    public void setPopButtonEnabled(final boolean newValue) {
        isPopButtonEnabled = newValue;
    }

    public void pushElement() {
        testQueue.push(dataInputField);
        updateHeadElementField();
        updateSizeField();
        setFindButtonEnabled(true);
        setPopButtonEnabled(true);
     }

    public void popElement() {
        String tempValue = testQueue.pop();
        updateHeadElementField();
        updateSizeField();
        setDataOutputField(tempValue);
        if (testQueue.isEmpty()) {
            setFindButtonEnabled(false);
            setPopButtonEnabled(false);
        }
    }

    public void findElement() {
		String outputMessage;
        String value = getDataInputField();
        try {
			outputMessage = Integer.toString(testQueue.findElement(value) + 1);
		} catch (Exception exception) {
		outputMessage = errorMessage;
		}
		setDataOutputField(outputMessage);
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
