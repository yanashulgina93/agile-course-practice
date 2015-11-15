package ru.unn.agile.StatisticValueCalculator.view;

import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.Pair;
import ru.unn.agile.StatisticValueCalculator.viewmodel.StatisticValue;
import ru.unn.agile.StatisticValueCalculator.viewmodel.ViewModel;

public class CalculatorWindowController {
    @FXML
    private Label nameOfStatisticParameter;
    @FXML
    private ViewModel viewModel;
    @FXML
    private Button clearTableButton;
    @FXML
    private TableView<Pair<String, String>> tableData;
    @FXML
    private TableColumn<Pair<String, String>, String> columnNumber;
    @FXML
    private TableColumn<Pair<String, String>, String> columnValue;
    @FXML
    private ChoiceBox<StatisticValue> statisticsList;
    @FXML
    private Button calculateButton;
    @FXML
    private Label nameOfCalculatedStatistic;
    @FXML
    private Label valueOfCalculatedStatistic;
    @FXML
    private TextField textStatisticParameterValue;
    @FXML
    private Label statisticParameterInputInfo;
    @FXML
    private Button deleteRowButton;
    @FXML
    private Button addRowButton;
    @FXML
    private Label rowValueInputInfo;
    @FXML
    private TextField textRowValue;

    @FXML
    private void initialize() {
        textRowValue.textProperty()
                .bindBidirectional(viewModel.inputRowProperty());
        textStatisticParameterValue.textProperty()
                .bindBidirectional(viewModel.inputStatisticParameterProperty());

        columnNumber.setCellValueFactory(
                cell -> new SimpleStringProperty(cell.getValue().getKey())
        );
        columnValue.setCellValueFactory(
                cell -> new SimpleStringProperty(cell.getValue().getValue())
        );

        addRowButton.setOnAction(event -> viewModel.addRowToStatisticData());
        deleteRowButton.setOnAction(event -> viewModel.deleteSelectedRowInStatisticData());
        clearTableButton.setOnAction(event -> viewModel.clearStatisticData());
        calculateButton.setOnAction(event -> viewModel.calculateSelectedStatistic());

        tableData.setItems(viewModel.getStatisticData());
        tableData.getFocusModel()
                .focusedIndexProperty()
                .addListener(
                        (observable, oldValue, newValue) -> {
            viewModel.selectRowInStatisticData(newValue.intValue());
        });

        statisticsList.getSelectionModel()
                .selectedItemProperty()
                .addListener(
                        (observable, oldValue, newValue) -> {
                    viewModel.setSelectedStatistic(newValue);
                });
    }
}
