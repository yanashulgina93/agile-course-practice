package ru.unn.agile.TemperatureConverter.viewmodel;

import ru.unn.agile.TemperatureConverter.Model.TemperatureScaleName;

public class ViewModel {
    private String inputTemperature;
    private String resultTemperature;
    private Status status;
    private TemperatureScaleName scale;
    private boolean isConvertButtonEnable;

    public ViewModel() {
        inputTemperature = "";
        resultTemperature = "";
        status = Status.WAITING;
        scale = TemperatureScaleName.FAHRENHEIT;
        isConvertButtonEnable = false;
    }

    public String getInputTemperature() {
        return inputTemperature;
    }

    public String getResultTemperature() {
        return resultTemperature;
    }

    public Status getStatus() {
        return status;
    }

    public TemperatureScaleName getScale() {
        return scale;
    }

    public boolean isConvertButtonEnabled() {
        return isConvertButtonEnable;
    }

    public void setInputTemperature(String inputTemperature) {
        this.inputTemperature = inputTemperature;
    }

    public void setScale(TemperatureScaleName scale) {
        this.scale = scale;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    void parse() {
        Double.parseDouble(inputTemperature);
        setStatus(Status.READY);
    }
}
