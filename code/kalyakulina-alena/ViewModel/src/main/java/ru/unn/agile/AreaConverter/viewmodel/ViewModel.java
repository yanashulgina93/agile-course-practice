package ru.unn.agile.AreaConverter.viewmodel;

import ru.unn.agile.AreaConverter.model.AreaConverter;
import ru.unn.agile.AreaConverter.model.AreaMeasure;

import java.util.List;

public class ViewModel {
    private String inputArea;
    private String resultArea;
    private String status;
    private AreaMeasure from;
    private AreaMeasure to;
    private boolean isConvertButtonEnable;
    private AreaConverterLogger logger;

    public ViewModel(AreaConverterLogger logger) {
        if (logger == null) {
            throw new IllegalArgumentException("Logger parameter can't be null");
        }

        this.logger = logger;
        inputArea = "";
        resultArea = "";
        status = Status.WAITING;
        from = AreaMeasure.SQUARE_METER;
        to = AreaMeasure.SQUARE_KILOMETER;
        isConvertButtonEnable = false;
    }

    public String getInputArea() {
        return inputArea;
    }

    public String getResultArea() {
        return resultArea;
    }

    public String getStatus() {
        return status;
    }

    public AreaMeasure getFrom() {
        return from;
    }

    public AreaMeasure getTo() {
        return to;
    }

    public boolean isConvertButtonEnabled() {
        return isConvertButtonEnable;
    }

    public void setInputArea(final String inputArea) {
        if (this.inputArea != inputArea) {
            this.inputArea = inputArea;
            logger.logMessage(LogMessage.INPUT_AREA_CHANGED.toString() + inputArea.toString());
        }
    }

    public void setFrom(final AreaMeasure from) {
        if (this.from != from) {
            this.from = from;
            logger.logMessage(LogMessage.SCALE_FROM_CHANGED.toString() + from.toString());
        }
    }

    public void setTo(final AreaMeasure to) {
        if (this.to != to) {
            this.to = to;
            logger.logMessage(LogMessage.SCALE_TO_CHANGED.toString() + to.toString());
        }
    }

    public boolean parseInput() {
        if (inputArea.isEmpty()) {
            status = Status.WAITING;
            resultArea = "";
            isConvertButtonEnable = false;
        } else {
            double parsedInputArea;

            try {
                parsedInputArea = Double.parseDouble(inputArea);
            } catch (Exception exception) {
                status = Status.WRONG_FORMAT;
                isConvertButtonEnable = false;
                return false;
            }

            if (parsedInputArea < 0.0) {
                status = Status.NEGATIVE_AREA;
                isConvertButtonEnable = false;
            } else {
                status = Status.READY;
                resultArea = "";
                isConvertButtonEnable = true;
            }
        }
        return isConvertButtonEnable;
    }

    public void convert() {
        if (parseInput()) {
            AreaConverter converter = new AreaConverter(from, to);
            double convertedArea = converter.convert(Double.parseDouble(inputArea));
            resultArea = Double.toString(convertedArea);
            status = Status.SUCCESS;
        }
    }

    public List<String> getLog() {
        return logger.getLog();
    }
}
