package ru.unn.agile.TemperatureConverter.viewmodel;

import ru.unn.agile.TemperatureConverter.Model.TemperatureScaleName;

public class ViewModel {
    private final String inputTemperature;
    private final String resultTemperature;
    private final String status;
    private final TemperatureScaleName scale;
    private final boolean isConvertButtonEnable;

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

    public String getStatus() {
        return status;
    }

    public TemperatureScaleName getScale() {
        return scale;
    }

    public boolean isConvertButtonEnabled() {
        return isConvertButtonEnable;
    }


}
