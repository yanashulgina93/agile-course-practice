package ru.unn.agile.QuadraticEquationSolver.viewmodel;

import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import ru.unn.agile.QuadraticEquationSolver.Model.QuadraticEquationSolver;

import java.util.ArrayList;
import java.util.List;

public class ViewModel {
    public StringProperty coeffAProperty() {
        return a;
    }
    public StringProperty coeffBProperty() {
        return b;
    }
    public StringProperty coeffCProperty() {
        return c;
    }
    public BooleanProperty solvingEquationDisabledProperty() {
        return solvingEquationDisabled;
    }
    public StringProperty statusProperty() {
        return status;
    }
    public StringProperty resultProperty() {
        return result;
    }
    public final String getResult() {
        return result.get();
    }
    public final String getStatus() {
        return status.get();
    }
    public final boolean getSolvingEquationDisabled() {
        return solvingEquationDisabled.get();
    }

    private final StringProperty a = new SimpleStringProperty();
    private final StringProperty b = new SimpleStringProperty();
    private final StringProperty c = new SimpleStringProperty();
    private final BooleanProperty solvingEquationDisabled = new SimpleBooleanProperty();
    private final StringProperty result = new SimpleStringProperty();
    private final StringProperty status = new SimpleStringProperty();
    private final List<ValueChangeListener> valueChangedListeners = new ArrayList<>();

    public ViewModel() {
        a.set("");
        b.set("");
        c.set("");
        result.set("");
        status.set(Status.WAIT.toString());

        BooleanBinding canSolveEquation = new BooleanBinding() {
            {
                super.bind(a, b, c);
            }
            @Override
            protected boolean computeValue() {
                return getInputStatus() == Status.READY;
            }
        };
        solvingEquationDisabled.bind(canSolveEquation.not());

        final List<StringProperty> fields = new ArrayList<StringProperty>() { {
            add(a);
            add(b);
            add(c);
        } };

        for (StringProperty field : fields) {
            final ValueChangeListener listener = new ValueChangeListener();
            field.addListener(listener);
            valueChangedListeners.add(listener);
        }
    }

    public void solveQuadraticEquation() {
        if (solvingEquationDisabled.get()) {
            return;
        }
        float coeffA = Float.parseFloat(a.get());
        float coeffB = Float.parseFloat(b.get());
        float coeffC = Float.parseFloat(c.get());
        float [] roots = trySolveQuadraticEquation(coeffA, coeffB, coeffC);
        if (roots == null) {
            status.set(Status.NO_ROOTS.toString());
        } else {
            status.set(Status.SOLVED.toString());
        }
        result.set(createAnswer(roots));
    }

    private String createAnswer(float [] roots) {
        String outAnswer = "empty";
        if (roots != null) {
            ArrayList<String> answer = new ArrayList<>();
            for (float root : roots) {
                answer.add(String.valueOf(root));
            }
            outAnswer = String.join(" ", answer);
        }
        return outAnswer;
    }

    private float [] trySolveQuadraticEquation(final float a, final float b, final float c) {
        try {
            return QuadraticEquationSolver.solve(a, b, c);
        } catch (Exception ex) {
            return null;
        }
    }

    private Status getInputStatus() {
        Status inputStatus = Status.READY;
        if (a.get().isEmpty() || b.get().isEmpty() || c.get().isEmpty()) {
            inputStatus = Status.WAIT;
        }
        try {
            if (!a.get().isEmpty()) {
                Float.parseFloat(a.get());
            }
            if (!b.get().isEmpty()) {
                Float.parseFloat(b.get());
            }
            if (!c.get().isEmpty()) {
                Float.parseFloat(c.get());
            }
        } catch (NumberFormatException nfe) {
            inputStatus = Status.BAD_DATA;
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
    WAIT("Waiting enter coefficients"),
    READY("Ready to solve equation"),
    BAD_DATA("You entered wrong number"),
    NO_ROOTS("Equation has no roots"),
    SOLVED("Equation was solved");

    private final String name;
    private Status(final String name) {
        this.name = name;
    }
    public String toString() {
        return name;
    }
}
