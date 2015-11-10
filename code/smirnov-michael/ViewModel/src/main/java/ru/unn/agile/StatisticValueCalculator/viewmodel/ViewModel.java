package ru.unn.agile.StatisticValueCalculator.viewmodel;

public class ViewModel {
    private String descriptionOfCalculatedStatistic = "";
    private String valueOfCalculatedStatistic = "";
    private String errorOfAddRow = "";
    private String errorOfAddEvent = "";
    private StatisticNames selectedStatistic = StatisticNames.ENUMERATION;
    private boolean eventAddFieldVisible = false;
    private String rowAddValue = "1.0";

    public void setSelectedStatistic(StatisticNames selectedStatistic) {
        this.selectedStatistic = selectedStatistic;
    }

    public boolean isEventAddFieldVisible() {
        return eventAddFieldVisible;
    }

    public String getValueOfCalculatedStatistic() {
        return valueOfCalculatedStatistic;
    }

    public String getDescriptionOfCalculatedStatistic() {
        return descriptionOfCalculatedStatistic;
    }

    public String getErrorOfAddRow() {
        return errorOfAddRow;
    }

    public String getErrorOfAddEvent() {
        return errorOfAddEvent;
    }

    public StatisticNames getSelectedStatistic() {
        return selectedStatistic;
    }

    public String getRowAddValue() {
        return rowAddValue;
    }
}
