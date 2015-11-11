package ru.unn.agile.StatisticValueCalculator.view;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import ru.unn.agile.StatisticValueCalculator.viewmodel.StatisticNames;
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
    private ChoiceBox<StatisticNames> choiceBoxStatisticValue;
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
        choiceBoxStatisticValue.getSelectionModel()
                .selectedItemProperty()
                .addListener(new ChangeListener<StatisticNames>() {
            @Override
            public void changed(ObservableValue<? extends StatisticNames> observable,
                                StatisticNames oldValue, StatisticNames newValue) {
                viewModel.setSelectedStatistic(newValue);
            }
        });
    }
}
