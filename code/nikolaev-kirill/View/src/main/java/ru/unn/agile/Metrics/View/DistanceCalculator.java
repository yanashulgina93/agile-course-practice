package ru.unn.agile.Metrics.view;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import ru.unn.agile.Metrics.viewmodel.DistanceCalculatorViewModel;


public class DistanceCalculator {

    @FXML
    private DistanceCalculatorViewModel viewModel;
    @FXML
    private TextField txt1stVector;
    @FXML
    private TextField txt2ndVector;
    @FXML
    private Button btnCalc;
    @FXML
    private ToggleGroup tgMetrics;
    @FXML
    private RadioButton rbRhoInf;
    private RadioButton selected;

    @FXML
    public void initialize() {
        selected = rbRhoInf;

        txt1stVector.textProperty().bindBidirectional(viewModel.firstVecProperty());
        txt2ndVector.textProperty().bindBidirectional(viewModel.secondVecProperty());

        btnCalc.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent event) {
                viewModel.calculate();
            }
        });

        tgMetrics.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(final ObservableValue<? extends Toggle> observable,
                                final Toggle oldValue, final Toggle newValue) {
                selected = (RadioButton) newValue.getToggleGroup().getSelectedToggle();
                viewModel.setMetric(selected.getText());
            }
        });
    }
}
