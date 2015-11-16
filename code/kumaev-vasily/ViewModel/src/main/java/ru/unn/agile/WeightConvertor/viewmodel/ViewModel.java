package ru.unn.agile.WeightConvertor.viewmodel;

import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import ru.unn.agile.WeightConvertor.Model.WeightUnit;

import java.util.ArrayList;
import java.util.List;

public class ViewModel {
    private final StringProperty value = new SimpleStringProperty();
    private final StringProperty status = new SimpleStringProperty();
    private final StringProperty result = new SimpleStringProperty();

    private final ObjectProperty<WeightUnit> inputUnit = new SimpleObjectProperty<>();
    private final ObjectProperty<WeightUnit> outputUnit = new SimpleObjectProperty<>();

    private final BooleanProperty convertationDisable = new SimpleBooleanProperty();

    private final ObjectProperty<ObservableList<WeightUnit>> units =
            new SimpleObjectProperty<>(FXCollections.observableArrayList(WeightUnit.values()));

    private final List<ValueChangeListener> valueChangedListeners = new ArrayList<>();

    public ViewModel() {
        inputUnit.set(WeightUnit.GRAM);
        outputUnit.set(WeightUnit.KILOGRAM);
        result.set("");
        value.set("");
        status.set(Status.WAITING.toString());

        BooleanBinding couldConvert = new BooleanBinding() {
            {
                super.bind(value);
            }
            @Override
            protected boolean computeValue() {
                return getInputStatus() == Status.READY;
            }
        };
        convertationDisable.bind(couldConvert.not());

        final List<StringProperty> fields = new ArrayList<StringProperty>() { {
            add(value);
        } };

        for (StringProperty field : fields) {
            final ValueChangeListener listener = new ValueChangeListener();
            field.addListener(listener);
            valueChangedListeners.add(listener);
        }
    }

    public void convert() {
        if (convertationDisable.get()) {
            return;
        }

        result.set(outputUnit.get().convert(value.get(), inputUnit.get(), outputUnit.get()));
        status.set(Status.SUCCESS.toString());
    }

    public StringProperty valueProperty() {
        return value;
    }
    public StringProperty resultProperty() {
        return result;
    }
    public final String getResult() {
        return result.get();
    }
    public final ObjectProperty<WeightUnit> inputUnitProperty() {
        return inputUnit;
    }
    public final ObjectProperty<WeightUnit> outputUnitProperty() {
        return outputUnit;
    }
    public ObjectProperty<ObservableList<WeightUnit>> unitsProperty() {
        return units;
    }
    public final ObservableList<WeightUnit> getUnits() {
        return units.get();
    }
    public BooleanProperty convertationDisableProperty() {
        return convertationDisable;
    }
    public final boolean getConvertationDisable() {
        return convertationDisable.get();
    }
    public StringProperty statusProperty() {
        return status;
    }
    public final String getStatus() {
        return status.get();
    }

    private Status getInputStatus() {
        Status inputStatus = Status.READY;
        if (value.get().isEmpty()) {
            inputStatus = Status.WAITING;
        }
        try {
            if (!value.get().isEmpty()) {
                double val = Double.parseDouble(value.get());
                if (val < 0) {
                    inputStatus = Status.BAD_FORMAT;
                }
            }
        } catch (NumberFormatException e) {
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
    WAITING("Please provide input data"),
    READY("Press 'Convert' or Enter"),
    BAD_FORMAT("Bad format"),
    SUCCESS("Success");

    private final String name;
    private Status(final String name) {
        this.name = name;
    }
    @Override
    public String toString() {
        return name;
    }
}
