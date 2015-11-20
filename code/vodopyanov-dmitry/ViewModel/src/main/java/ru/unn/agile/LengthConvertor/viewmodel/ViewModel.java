package ru.unn.agile.LengthConvertor.viewmodel;

import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import ru.unn.agile.LengthConvertor.Model.LengthUnit;

import java.util.ArrayList;
import java.util.List;

public class ViewModel {
    private final StringProperty inputValue = new SimpleStringProperty();

    private final ObjectProperty<ObservableList<LengthUnit>> units =
            new SimpleObjectProperty<>(FXCollections.observableArrayList(LengthUnit.values()));
    private final ObjectProperty<LengthUnit> inputUnit = new SimpleObjectProperty<>();
    private final ObjectProperty<LengthUnit> outputUnit = new SimpleObjectProperty<>();
    private final BooleanProperty calculationDisabled = new SimpleBooleanProperty();

    private final StringProperty outputValue = new SimpleStringProperty();
    private final StringProperty hintMessage = new SimpleStringProperty();

    private final List<InputValueListener> valueChangedListeners = new ArrayList<>();

    public ViewModel() {
        inputValue.set("");
        inputUnit.set(LengthUnit.INCH);
        outputValue.set("");
        outputUnit.set(LengthUnit.FOOT);
        hintMessage.set(Status.WAITING.toString());

        BooleanBinding couldCalculate = new BooleanBinding() {
            {
                super.bind(inputValue);
            }
            @Override
            protected boolean computeValue() {
                return getHint() == Status.READY;
            }
        };
        calculationDisabled.bind(couldCalculate.not());

        final List<StringProperty> fields = new ArrayList<StringProperty>() { {
            add(inputValue);
        } };

        for (StringProperty field : fields) {
            final InputValueListener listener = new InputValueListener();
            field.addListener(listener);
            valueChangedListeners.add(listener);
        }
    }

    public void calculate() {
        outputValue.set(outputUnit.get().convert(inputValue.get(), inputUnit.get()));
        hintMessage.set(Status.SUCCESS.toString());
    }

    public StringProperty inputValueProperty() {
        return inputValue;
    }

    public ObjectProperty<ObservableList<LengthUnit>> unitsProperty() {
        return units;
    }

    public final ObservableList<LengthUnit> getUnits() {
        return units.get();
    }

    public ObjectProperty<LengthUnit> inputUnitProperty() {
        return inputUnit;
    }

    public ObjectProperty<LengthUnit> outputUnitProperty() {
        return outputUnit;
    }

    public BooleanProperty calculationDisabledProperty() {
        return calculationDisabled;
    }

    public StringProperty outputValueProperty() {
        return outputValue;
    }

    public final String getOutputValue() {
        return outputValue.get();
    }

    public StringProperty hintMessageProperty() {
        return hintMessage;
    }

    public final String getHintMessage() {
        return hintMessage.get();
    }

    private Status getHint() {
        Status hintStatus = Status.READY;
        if (inputValue.get().isEmpty()) {
            hintStatus = Status.WAITING;
        }
        try {
            if (!inputValue.get().isEmpty()) {
                double value = Double.parseDouble(inputValue.get());
                if (value < 0) {
                    hintStatus = Status.BAD_FORMAT;
                }
            }
        } catch (NumberFormatException nfe) {
            hintStatus = Status.BAD_FORMAT;
        }

        return hintStatus;
    }

    private class InputValueListener implements ChangeListener<String> {
        @Override
        public void changed(final ObservableValue<? extends String> observable,
                            final String oldValue, final String newValue) {
            hintMessage.set(getHint().toString());
        }
    }
}

enum Status {
    WAITING("Enter value and choose units"),
    READY("Press \"Calculate\""),
    BAD_FORMAT("Error: Bad format"),
    SUCCESS("Converted successfully");

    private final String name;
    private Status(final String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
