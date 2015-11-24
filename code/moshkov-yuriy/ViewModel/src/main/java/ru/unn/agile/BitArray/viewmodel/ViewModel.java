package ru.unn.agile.BitArray.viewmodel;

import ru.unn.agile.BitArray.model.BitArray;

public class ViewModel {
    private boolean isDoOpeationButtonEnabled;

    public ViewModel() {
        isDoOpeationButtonEnabled = false;
    }


    public boolean isDoOperationEnabled() {
        return isDoOpeationButtonEnabled;
    }
}
