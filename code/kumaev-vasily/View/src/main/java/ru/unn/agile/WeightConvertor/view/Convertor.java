package ru.unn.agile.WeightConvertor.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import ru.unn.agile.WeightConvertor.Model.WeightUnit;
import ru.unn.agile.WeightConvertor.viewmodel.ViewModel;

public class Convertor {
    @FXML
    private ViewModel viewModel;
    @FXML
    private TextField txtInputValue;
    @FXML
    private ComboBox<WeightUnit> cbInputUnit;
    @FXML
    private ComboBox<WeightUnit> cbOutputUnit;
    @FXML
    private Button btnConv;

    @FXML
    void initialize() {
        txtInputValue.textProperty().bindBidirectional(viewModel.valueProperty());

        cbInputUnit.valueProperty().bindBidirectional(viewModel.inputUnitProperty());
        cbOutputUnit.valueProperty().bindBidirectional(viewModel.outputUnitProperty());

        btnConv.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent event) {
                viewModel.convert();
            }
        });
    }
}
