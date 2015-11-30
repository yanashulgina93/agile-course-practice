package ru.unn.agile.QuadraticEquationSolver.viewmodel;

import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.*;

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

    private final StringProperty a = new SimpleStringProperty();
    private final StringProperty b = new SimpleStringProperty();
    private final StringProperty c = new SimpleStringProperty();
    private final BooleanProperty solvingEquationDisabled = new SimpleBooleanProperty();

    private final StringProperty result = new SimpleStringProperty();
    private final StringProperty status = new SimpleStringProperty();

    private boolean solveButtonEnabled = false;

    public ViewModel() {
        a.set("");
        b.set("");
        c.set("");
        result.set("");
        status.set(Status.WAIT.toString());

        /*BooleanBinding couldSolveEquation = new BooleanBinding() {
            {
                super.bind(a, b, c);
            }
            @Override
            protected boolean computeValue() {
                return getInputStatus() == Status.READY;
            }
        };
        calculationDisabled.bind(couldCalculate.not());

        // Add listeners to the input text fields
        final List<StringProperty> fields = new ArrayList<StringProperty>() { {
            add(re1);
            add(im1);
            add(re2);
            add(im2);
        } };

        for (StringProperty field : fields) {
            final ValueChangeListener listener = new ValueChangeListener();
            field.addListener(listener);
            valueChangedListeners.add(listener);
        }*/
    }

    public void solveQuadraticEquation() {

    }

    public boolean isButtonSolveEnabled() {
        return solvingEquationDisabled.get();
    }

    private boolean checkAllCoefficientsWereEntered() {
        return false;
    }
}

enum Status {
    WAIT("Waiting enter coefficients"),
    READY("Ready to solve equation"),
    BAD_DATA("You entered wrong number"),
    OK("Equation was solved");

    private final String name;
    private Status(final String name) {
        this.name = name;
    }
    public String toString() {
        return name;
    }
}
