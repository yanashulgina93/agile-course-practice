package ru.unn.agile.StatisticValueCalculator.viewmodel;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ViewModel {
    private final StringProperty nameOfCalculatedStatistic = new SimpleStringProperty("");
    private final StringProperty valueOfCalculatedStatistic = new SimpleStringProperty("");
    private final StringProperty errorOfAddRow = new SimpleStringProperty("");
    private final StringProperty errorOfAddStatisticParameter = new SimpleStringProperty("");

    private final ObjectProperty<ObservableList<StatisticNames>> listOfStatistics =
            new SimpleObjectProperty<>(FXCollections.observableArrayList(StatisticNames.values()));

    private final ObjectProperty<StatisticNames> selectedStatistic =
            new SimpleObjectProperty<>(StatisticNames.ENUMERATION);

    private BooleanProperty statisticParameterAddFieldVisible = new SimpleBooleanProperty(false);
    private String rowAddValue = "1.0";

    public ViewModel() {
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
    public String getErrorOfAddStatisticParameter() {
        return errorOfAddStatisticParameter.get();
    }

    public ObservableList<StatisticNames> getListOfStatistics() {
        return listOfStatistics.get();
    }

    public void setSelectedStatistic(StatisticNames selectedStatisticName) {
        selectedStatistic.set(selectedStatisticName);

        if(selectedStatisticName == StatisticNames.PROBABILITY ||
                selectedStatisticName == StatisticNames.ROW_MOMENT ||
                selectedStatisticName == StatisticNames.CENTRAL_MOMENT) {
            statisticParameterAddFieldVisible.set(true);
        }
        else {
            statisticParameterAddFieldVisible.set(false);
        }
    }
    public StatisticNames getSelectedStatistic() {
        return selectedStatistic.get();
    }
    public ObjectProperty<StatisticNames> selectedStatisticProperty() {return selectedStatistic;}

    public boolean getStatisticParameterAddFieldVisible() {
        return statisticParameterAddFieldVisible.get();
    }
    public void setStatisticParameterAddFieldVisible(boolean isVisible) { statisticParameterAddFieldVisible.set(isVisible);}
    public BooleanProperty statisticParameterAddFieldVisibleProperty() { return statisticParameterAddFieldVisible; }

    public String getRowAddValue() {
        return rowAddValue;
    }
}
