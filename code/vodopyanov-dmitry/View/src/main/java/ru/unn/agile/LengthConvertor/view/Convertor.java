package ru.unn.agile.LengthConvertor.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import ru.unn.agile.LengthConvertor.Model.LengthUnit;
import ru.unn.agile.LengthConvertor.viewmodel.ViewModel;

public class Convertor {
    @FXML
    private ViewModel viewModel;
    @FXML
    private TextField inputValueTextBox;
    @FXML
    private ComboBox<LengthUnit> inputUnitComboBox;
    @FXML
    private ComboBox<LengthUnit> outputUnitComboBox;
    @FXML
    private Button calculateButton;

    @FXML
    void initialize() {
        inputValueTextBox.textProperty().bindBidirectional(viewModel.inputValueProperty());

        inputUnitComboBox.valueProperty().bindBidirectional(viewModel.inputUnitProperty());
        outputUnitComboBox.valueProperty().bindBidirectional(viewModel.outputUnitProperty());

        calculateButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent event) {
                viewModel.calculate();
            }
        });
    }
}
