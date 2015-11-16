package ru.unn.agile.HexBinOctCalculator.viewmodel;

import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import ru.unn.agile.HexBinOctCalculator.Model.Number;
import ru.unn.agile.HexBinOctCalculator.Model.NumeralSystem;
import ru.unn.agile.HexBinOctCalculator.Model.HexBinOctCalculator.Operation;

import java.util.ArrayList;
import java.util.List;

public class ViewModel {
    private final StringProperty value1 = new SimpleStringProperty();
    private final StringProperty value2 = new SimpleStringProperty();

    private final ObjectProperty<ObservableList<NumeralSystem>> systems =
            new SimpleObjectProperty<>(FXCollections.observableArrayList(NumeralSystem.values()));
    private final ObjectProperty<NumeralSystem> system1 = new SimpleObjectProperty<>();
    private final ObjectProperty<NumeralSystem> system2 = new SimpleObjectProperty<>();
    private final ObjectProperty<NumeralSystem> finalSystem = new SimpleObjectProperty<>();
    private final ObjectProperty<ObservableList<Operation>> operations =
            new SimpleObjectProperty<>(FXCollections.observableArrayList(Operation.values()));
    private final ObjectProperty<Operation> operation = new SimpleObjectProperty<>();
    private final BooleanProperty calculationDisabled = new SimpleBooleanProperty();

    private final StringProperty result = new SimpleStringProperty();
    private final StringProperty status = new SimpleStringProperty();

    private final List<ValueChangeListener> valueChangedListeners = new ArrayList<>();

    public ViewModel() {
        value1.set("");
        system1.set(NumeralSystem.BIN);
        value2.set("");
        system2.set(NumeralSystem.BIN);
        finalSystem.set(NumeralSystem.HEX);
        operation.set(Operation.ADD);
        result.set("");
        status.set(Status.WAITING.toString());

        BooleanBinding couldCalculate = new BooleanBinding() {
            {
                super.bind(value1, value2);
            }
            @Override
            protected boolean computeValue() {
                return getInputStatus() == Status.READY;
            }
        };
        calculationDisabled.bind(couldCalculate.not());

        final List<StringProperty> fields = new ArrayList<StringProperty>() { {
            add(value1);
            add(value2);
        } };

        for (StringProperty field : fields) {
            final ValueChangeListener listener = new ValueChangeListener();
            field.addListener(listener);
            valueChangedListeners.add(listener);
        }
    }

    public void calculate() {
        if (calculationDisabled.get()) {
            return;
        }

        Number first = new Number(value1.get(), system1.get());
        Number second = new Number(value2.get(), system2.get());

        result.set(operation.get().apply(first, second, finalSystem.get()).getValue().toString());
        status.set(Status.SUCCESS.toString());
    }

    public StringProperty value1Property() {
        return value1;
    }

    public StringProperty value2Property() {
        return value2;
    }

    public ObjectProperty<NumeralSystem> system1Property() {
        return system1;
    }

    public ObjectProperty<NumeralSystem> system2Property() {
        return system2;
    }

    public ObjectProperty<NumeralSystem> finalSystemProperty() {
        return finalSystem;
    }

    public ObjectProperty<Operation> operationProperty() {
        return operation;
    }

    public BooleanProperty calculationDisabledProperty() {
        return calculationDisabled;
    }

    public StringProperty resultProperty() {
        return result;
    }

    public StringProperty statusProperty() {
        return status;
    }

    private Status getInputStatus() {
        Status inputStatus = Status.READY;
        if (value1.get().isEmpty() || value2.get().isEmpty()) {
            inputStatus = Status.WAITING;
        }
        try {
            if (!value1.get().isEmpty() && system1.get() != NumeralSystem.HEX) {
                Double.parseDouble(value1.get());
            }
            if (!value2.get().isEmpty() && system2.get() != NumeralSystem.HEX) {
                Double.parseDouble(value2.get());
            }
        } catch (NumberFormatException nfe) {
            inputStatus = Status.BAD_FORMAT;
        }

        return inputStatus;
    }

    private class ValueChangeListener implements ChangeListener<String> {
        @Override
        public void changed(final ObservableValue<? extends String> observable,
                            final String oldValue, final String newValue) {
            status.set(getInputStatus().toString());
        }
    }
}

enum Status {
    WAITING("Enter values and choose numeral systems"),
    READY("Press 'Calculate'"),
    BAD_FORMAT("Bad format"),
    SUCCESS("Success");

    private final String name;
    private Status(final String name) {
        this.name = name;
    }
    public String toString() {
        return name;
    }
}
