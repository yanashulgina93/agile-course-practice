package ru.unn.agile.PercentAccretion.viewmodel;

import ru.unn.agile.PercentAccretion.Model.Factory;
import ru.unn.agile.PercentAccretion.Model.PercentAccretion;
import ru.unn.agile.PercentAccretion.Model.PercentData;

public class PercentAccretionViewModel {
    private boolean calculateButtonEnabled;
    private boolean initialSumIsCorrect;
    private boolean percentRateIsCorrect;
    private boolean countOfYearsIsCorrect;
    private String percentType;
    private PercentData data;
    private String errorMessage;
    private String resultSum;


    public String getResultSum() {
        return resultSum;
    }

    public boolean isCalculateButtonEnabled() {
        if (initialSumIsCorrect && percentRateIsCorrect && countOfYearsIsCorrect) {
            calculateButtonEnabled = true;
        } else {
            calculateButtonEnabled = false;
        }
        return calculateButtonEnabled;
    }

    public PercentData getData() {
        return data;
    }

    public void setPercentType(String value) {
        percentType = value;
        resultSum = "";
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setInitialSum(String value) {
        errorMessage = "";
        resultSum = "";
        if (value.equals("")) {
            initialSumIsCorrect = false;
            errorMessage = "Please fill fields!";
            return;
        }
        if (!value.matches("\\d+"))
        {
            initialSumIsCorrect = false;
            errorMessage = "Please enter correct values!";
            return;
        }
        data.setInitialSum(Double.valueOf(value));
        initialSumIsCorrect = true;
    }

    public void setPercentRate(String value) {
        errorMessage = "";
        resultSum = "";
        if (value.equals("")) {
            percentRateIsCorrect = false;
            errorMessage = "Please fill fields!";
            return;
        }
        if (!value.matches("\\d+"))
        {
            percentRateIsCorrect = false;
            errorMessage = "Please enter correct values!";
            return;
        }
        data.setPercentRate(Double.valueOf(value));
        percentRateIsCorrect = true;
    }

    public void setCountOfYears(String value) {
        errorMessage = "";
        resultSum = "";
        if (value.equals("")) {
            countOfYearsIsCorrect = false;
            errorMessage = "Please fill fields!";
            return;
        }
        if (!value.matches("\\d+"))
        {
            countOfYearsIsCorrect = false;
            errorMessage = "Please enter correct values!";
            return;
        }
        data.setCountOfYears(Integer.valueOf(value));
        countOfYearsIsCorrect = true;
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
        percentType = "";
        errorMessage = "Please fill fields!";
    }
}
