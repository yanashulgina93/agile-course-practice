package ru.unn.agile.TemperatureConverter.viewmodel;

import ru.unn.agile.TemperatureConverter.Model.TemperatureConverter;
import ru.unn.agile.TemperatureConverter.Model.TemperatureScaleName;

import java.util.DoubleSummaryStatistics;

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

    public boolean parse() {
        if (inputTemperature.isEmpty()) {
            status = Status.WAITING;
            isConvertButtonEnable = false;
            return isConvertButtonEnable;
        }
        try {
            Double.parseDouble(inputTemperature);
            status = Status.READY;
            isConvertButtonEnable = true;
            return isConvertButtonEnable;
        }
        catch (Exception exception) {
            status = Status.BAD_FORMAT;
            isConvertButtonEnable = false;
            return isConvertButtonEnable;
        }
    }

    public void convert() {
        if (parse()) {
            TemperatureConverter converter = new TemperatureConverter(scale);
            double temperatureInOtherScale = converter.convert(Double.parseDouble(inputTemperature));
            resultTemperature = Double.toString(temperatureInOtherScale);
            status = Status.SUCCESS;
        }
    }


}
