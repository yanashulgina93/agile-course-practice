package ru.unn.agile.PercentAccretion.viewmodel;

import ru.unn.agile.PercentAccretion.Model.Factory;
import ru.unn.agile.PercentAccretion.Model.PercentAccretion;
import ru.unn.agile.PercentAccretion.Model.PercentData;

public class PercentAccretionViewModel {
    public enum PercentAccretionErrors {
        FIELD_IS_EMPTY("Please fill fields!"),
        INCORRECT_VALUES("Please enter correct values!"),
        NO_ERRORS("");

        private String errorMessage;

        PercentAccretionErrors(final String errorMessage) {
            this.errorMessage = errorMessage;
        }

        public String getMessage() {
            return errorMessage;
        }
    }
    private boolean calculateButtonEnabled;
    private boolean initialSumIsCorrect;
    private boolean percentRateIsCorrect;
    private boolean countOfYearsIsCorrect;
    private String percentType;
    private final PercentData data;
    private String errorMessage;
    private String resultSum;
    private static final String EMPTY_STRING = "";

    public String getResultSum() {
        return resultSum;
    }

    public boolean isCalculateButtonEnabled() {
        if (checkFieldsHaveRightValues()) {
            calculateButtonEnabled = true;
            errorMessage = PercentAccretionErrors.NO_ERRORS.getMessage();
        } else {
            calculateButtonEnabled = false;
        }
        return calculateButtonEnabled;
    }

    public PercentData getData() {
        return data;
    }

    public void setPercentType(final String value) {
        percentType = value;
        resultSum = EMPTY_STRING;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setInitialSum(final String value) {
        resultSum = EMPTY_STRING;
        if (checkValue(value)) {
            initialSumIsCorrect = false;
            return;
        }
        data.setInitialSum(Double.valueOf(value));
        initialSumIsCorrect = true;
        clearErrorMessage();
    }

    public void setPercentRate(final String value) {
        resultSum = EMPTY_STRING;
        if (checkValue(value)) {
            percentRateIsCorrect = false;
            return;
        }
        data.setPercentRate(Double.valueOf(value));
        percentRateIsCorrect = true;
        clearErrorMessage();
    }

    public void setCountOfYears(final String value) {
        resultSum = EMPTY_STRING;
        if (checkValue(value)) {
            countOfYearsIsCorrect = false;
            return;
        }
        data.setCountOfYears(Integer.valueOf(value));
        countOfYearsIsCorrect = true;
        clearErrorMessage();
    }

    public void calculateResultSum() {
        Factory factory = new Factory();
        PercentAccretion percentCounter = factory.getPercentAccretion(percentType);
        resultSum = String.valueOf(percentCounter.calculate(data));
    }

    public PercentAccretionViewModel() {
        data = new PercentData();
        calculateButtonEnabled = false;
        initialSumIsCorrect = false;
        percentRateIsCorrect = false;
        countOfYearsIsCorrect = false;
        percentType = EMPTY_STRING;
        errorMessage = PercentAccretionErrors.FIELD_IS_EMPTY.getMessage();
    }

    private boolean checkValue(final String value) {
        if (EMPTY_STRING.equals(value)) {
            errorMessage = PercentAccretionErrors.FIELD_IS_EMPTY.getMessage();
            return true;
        }
        if (!value.matches("\\d+")) {
            errorMessage = PercentAccretionErrors.INCORRECT_VALUES.getMessage();
            return true;
        }
        return false;
    }

    private boolean checkFieldsHaveRightValues() {
        return initialSumIsCorrect && percentRateIsCorrect && countOfYearsIsCorrect;
    }

    private void clearErrorMessage() {
        if (checkFieldsHaveRightValues()) {
            errorMessage = PercentAccretionErrors.NO_ERRORS.getMessage();
        }
    }
}
