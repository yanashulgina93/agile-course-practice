package ru.unn.agile.StatisticValueCalculator.view;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import ru.unn.agile.StatisticValueCalculator.viewmodel.StatisticInfo;
import ru.unn.agile.StatisticValueCalculator.viewmodel.ViewModel;

public class CalculatorWindowController {
    @FXML
    private Label labelStatisticParameterName;
    @FXML
    private ViewModel viewModel;
    @FXML
    private Button buttonClearTable;
    @FXML
    private TableView tableData;
    @FXML
    private TableColumn columnNumber;
    @FXML
    private TableColumn columnValue;
    @FXML
    private ChoiceBox<StatisticInfo> choiceBoxStatisticValue;
    @FXML
    private Button buttonCalculate;
    @FXML
    private Label labelStatisticsName;
    @FXML
    private Label labelStatisticsValue;
    @FXML
    private TextField textStatisticParameterValue;
    @FXML
    private Label labelStatisticParameterInputError;
    @FXML
    private Button buttonDeleteRow;
    @FXML
    private Button buttonAddRow;
    @FXML
    private Label labelRowValueError;
    @FXML
    private TextField textRowValue;
    @FXML
    private Button buttonLoadData;

    @FXML
    private void initialize() {
        textRowValue.textProperty()
                .bindBidirectional(viewModel.rowAddValueProperty());
        textStatisticParameterValue.textProperty()
                .bindBidirectional(viewModel.statisticParameterAddValueProperty());

        choiceBoxStatisticValue.getSelectionModel()
                .selectedItemProperty()
                .addListener(new ChangeListener<StatisticInfo>() {
            @Override
            public void changed(ObservableValue<? extends StatisticInfo> observable,
                                StatisticInfo oldValue, StatisticInfo newValue) {
                viewModel.setSelectedStatistic(newValue);
            }
        });
    }
}
