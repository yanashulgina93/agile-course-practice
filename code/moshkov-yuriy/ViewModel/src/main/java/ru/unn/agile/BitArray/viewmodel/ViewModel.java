package ru.unn.agile.BitArray.viewmodel;

import ru.unn.agile.BitArray.model.BitArray;

public class ViewModel {
    private boolean isDoOpeationButtonEnabled;
    private String sizeArray;
    private boolean initArrayButtonEnabled;

    public ViewModel() {
        isDoOpeationButtonEnabled = false;
    }


    public boolean isDoOperationEnabled() {
        return isDoOpeationButtonEnabled;
    }

    public void setSizeArray(String sizeArray) {
        this.sizeArray = sizeArray;
        int size = -1;
        try {
            size = Integer.parseInt(sizeArray);
        }
        catch (NumberFormatException exception) {
        }
        finally {
            if (size > 0) {
                initArrayButtonEnabled = true;
            } else {
                initArrayButtonEnabled = false;
            }
        }

    }

    public boolean isInitArrayEnabled() {
        return initArrayButtonEnabled;
    }
}
