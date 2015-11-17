package ru.unn.agile.HexBinOctCalculator.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import ru.unn.agile.HexBinOctCalculator.Model.HexBinOctCalculator.Operation;
import ru.unn.agile.HexBinOctCalculator.Model.NumeralSystem;
import ru.unn.agile.HexBinOctCalculator.viewmodel.ViewModel;

public class Calculator {
    @FXML
    private ViewModel viewModel;
    @FXML
    private TextField txtValue1;
    @FXML
    private TextField txtValue2;
    @FXML
    private ComboBox<Operation> cbOperation;
    @FXML
    private ComboBox<NumeralSystem> cbSystem1;
    @FXML
    private ComboBox<NumeralSystem> cbSystem2;
    @FXML
    private ComboBox<NumeralSystem> cbSystemRes;
    @FXML
    private Button btnCalc;

    @FXML
    void initialize() {
        txtValue1.textProperty().bindBidirectional(viewModel.value1Property());
        txtValue2.textProperty().bindBidirectional(viewModel.value2Property());
        cbSystem1.valueProperty().bindBidirectional(viewModel.system1Property());
        cbSystem2.valueProperty().bindBidirectional(viewModel.system2Property());
        cbSystemRes.valueProperty().bindBidirectional(viewModel.finalSystemProperty());

        cbOperation.valueProperty().bindBidirectional(viewModel.operationProperty());

        btnCalc.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent event) {
                viewModel.calculate();
            }
        });

        cbSystemRes.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent event) {
                viewModel.calculate();
            }
        });
    }
}
