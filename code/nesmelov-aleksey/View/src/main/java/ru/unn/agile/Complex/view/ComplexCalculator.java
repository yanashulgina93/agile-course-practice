package ru.unn.agile.Complex.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class ComplexCalculator {
    @FXML
    private ViewModel viewModel;
    @FXML
    private TextField firstRealTextField;
    @FXML
    private TextField firstImaginaryTextField;
    @FXML
    private TextField secondRealTextField;
    @FXML
    private TextField secondImaginaryTextField;
    @FXML
    private ComboBox<Operation> operationsComboBox;
    @FXML
    private Button calculateButton;

    @FXML
    void initialize() {
        firstRealTextField.textProperty().bindBidirectional(viewModel.getFirstRealProperty());
        firstImaginaryTextField.textProperty().bindBidirectional(viewModel.getFirstImaginaryProperty());
        secondRealTextField.textProperty().bindBidirectional(viewModel.getSecondRealProperty());
        secondImaginaryTextField.textProperty().bindBidirectional(viewModel.getSecondImaginaryProperty());

        operationsComboBox.valueProperty().bindBidirectional(viewModel.getOperationProperty());
        calculateButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent actionEvent) {
                viewModel.calculate();
            }
        });
    }
}
