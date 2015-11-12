package ru.unn.agile.StatisticValueCalculator.viewmodel;

import javafx.beans.property.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ViewModel {
    private final StringProperty nameOfCalculatedStatistic = new SimpleStringProperty("");
    private final StringProperty valueOfCalculatedStatistic = new SimpleStringProperty("");
    private final StringProperty errorOfAddRow = new SimpleStringProperty("");
    private final StringProperty errorOfAddStatisticParameter = new SimpleStringProperty("");

    private final ObjectProperty<ObservableList<StatisticInfo>> listOfStatistics =
            new SimpleObjectProperty<>(FXCollections.observableArrayList(StatisticInfo.values()));

    private final ObjectProperty<StatisticInfo> selectedStatistic;
    private final ObjectProperty<StatisticParameterName> parameterNameOfSelectedStatistic
            = new SimpleObjectProperty<>();
    private final BooleanProperty statisticParameterAddFieldVisible
            = new SimpleBooleanProperty();

    private StringProperty rowAddValue = new SimpleStringProperty("1.0");
    private StringProperty statisticParameterAddValue = new SimpleStringProperty("0.0");

    private final AddValueChangeListener valueChangeListener
            = new AddValueChangeListener();
    private final AddStatisticParameterChangeListener parameterChangeListener
            = new AddStatisticParameterChangeListener();

    public ViewModel() {
        selectedStatistic =
                new SimpleObjectProperty<>(StatisticInfo.ENUMERATION);
        setSelectedStatistic(selectedStatistic.get());

        rowAddValue.addListener(valueChangeListener);
        statisticParameterAddValue.addListener(parameterChangeListener);
    }

    public String getNameOfCalculatedStatistic() {
        return nameOfCalculatedStatistic.get();
    }
    public String getValueOfCalculatedStatistic() {
        return valueOfCalculatedStatistic.get();
    }
    public String getErrorOfAddRow() {
        return errorOfAddRow.get();
    }
    public StringProperty errorOfAddRowProperty() {
        return errorOfAddRow;
    }
    public String getErrorOfAddStatisticParameter() {
        return errorOfAddStatisticParameter.get();
    }
    public StringProperty errorOfAddStatisticParameterProperty() {
        return errorOfAddStatisticParameter;
    }

    public ObservableList<StatisticInfo> getListOfStatistics() {
        return listOfStatistics.get();
    }

    public void setSelectedStatistic(StatisticInfo selectedStatisticInfo) {
        selectedStatistic.set(selectedStatisticInfo);
        StatisticParameterName parameterName = selectedStatisticInfo.getParameterName();
        parameterNameOfSelectedStatistic.set(parameterName);

        if(parameterName != null) {
            statisticParameterAddFieldVisible.set(true);
        }
        else {
            statisticParameterAddFieldVisible.set(false);
        }
    }
    public StatisticInfo getSelectedStatistic() {
        return selectedStatistic.get();
    }
    public ObjectProperty<StatisticInfo> selectedStatisticProperty() { return selectedStatistic; }

    public void setParameterNameOfSelectedStatistic(StatisticParameterName parameterName) {
        parameterNameOfSelectedStatistic.set(parameterName);
    }
    public StatisticParameterName getParameterNameOfSelectedStatistic() {
        return parameterNameOfSelectedStatistic.get();
    }
    public ObjectProperty<StatisticParameterName> parameterNameOfSelectedStatisticProperty() {
        return parameterNameOfSelectedStatistic;
    }

    public boolean getStatisticParameterAddFieldVisible() {
        return statisticParameterAddFieldVisible.get();
    }
    public void setStatisticParameterAddFieldVisible(boolean isVisible) { statisticParameterAddFieldVisible.set(isVisible);}
    public BooleanProperty statisticParameterAddFieldVisibleProperty() { return statisticParameterAddFieldVisible; }

    public void setRowAddValue(String value)
    {
        rowAddValue.set(value);
    }
    public StringProperty rowAddValueProperty() { return rowAddValue; }
    public String getRowAddValue() {
        return rowAddValue.get();
    }

    public String getStatisticParameterAddValue() {
        return statisticParameterAddValue.get();
    }
    public StringProperty statisticParameterAddValueProperty() {
        return statisticParameterAddValue;
    }
    public void setStatisticParameterAddValue(String statisticParameterAddValue) {
        this.statisticParameterAddValue.set(statisticParameterAddValue);
    }

    private class AddValueChangeListener implements ChangeListener<String> {
        @Override
        public void changed(final ObservableValue<? extends String> observable,
                            final String oldValue, final String newValue) {
            errorOfAddRow.set("");
            if(!newValue.isEmpty()) {
                try {
                    Double.parseDouble(newValue);
                }
                catch (NumberFormatException exception) {
                    errorOfAddRow.set("The adding value must be a number");
                }
            }
        }
    }

    private class AddStatisticParameterChangeListener implements ChangeListener<String> {
        @Override
        public void changed(final ObservableValue<? extends String> observable,
                            final String oldValue, final String newValue) {
            errorOfAddStatisticParameter.set("");
            if(!newValue.isEmpty()) {
                try {
                    Double.parseDouble(newValue);
                }
                catch (NumberFormatException exception) {
                    errorOfAddStatisticParameter.set("The adding value must be a number");
                }
            }
        }
    }
}
