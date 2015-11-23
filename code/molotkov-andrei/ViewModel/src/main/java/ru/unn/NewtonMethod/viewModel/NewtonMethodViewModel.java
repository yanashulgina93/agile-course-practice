package ru.unn.NewtonMethod.viewModel;

import ru.unn.agile.NewtonMethod.ConverterToPolishNotation;
import ru.unn.agile.NewtonMethod.NewtonMethod;

public class NewtonMethodViewModel {
    private boolean isCalculateButtonEnabled;
    private String function;
    private String derivative;
    private String leftPointOfRange;
    private String rightPointOfRange;
    private Status status;
    private double root;
    private ConverterToPolishNotation converter;

    public NewtonMethodViewModel() {
        isCalculateButtonEnabled = false;
        function = "";
        derivative = "";
        leftPointOfRange = "";
        rightPointOfRange = "";
        root = 0;
        status = Status.WAITING;
    }

    public enum KeyboardKeys {
        ENTER(10),
        ANY(777);
        private int key;

        KeyboardKeys(final int key) {
            this.key = key;
        }

        public int getKey() {
            return key;
        }
    }

    public enum Status {
        WAITING("Please provide input data"),
        READY("Press 'Calculate' or Enter"),
        BAD_FORMAT_RANGE("Bad format of range"),
        BAD_FORMAT_FUNCTION("Incorrect function"),
        SUCCESS("Success"),
        NO_ROOT("Root is not in range");
        private String message;

        Status(final String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }
    }

    public boolean isCalculateButtonEnabled() {
        return isCalculateButtonEnabled;
    }

    public void processKeyInTextField(final int keyCode) {
        parseInput();

        if (keyCode == KeyboardKeys.ENTER.getKey()) {
            enterPressed();
        }
    }

    public void setFunction(final String function) {
        if (function.equals(this.function)) {
            return;
        }
        this.function = function + "=";
    }

    public void setDerivative(final String derivative) {
        if (derivative.equals(this.derivative)) {
            return;
        }
        this.derivative = derivative + "=";
    }

    public void setLeftPointOfRange(final String leftPointOfRange) {
        if (leftPointOfRange.equals(this.leftPointOfRange)) {
            return;
        }
        this.leftPointOfRange = leftPointOfRange;
    }

    public void setRightPointOfRange(final String rightPointOfRange) {
        if (rightPointOfRange.equals(this.rightPointOfRange)) {
            return;
        }
        this.rightPointOfRange = rightPointOfRange;
    }

    public double getRoot() {
        return root;
    }

    public String getStatus() {
        return status.getMessage();
    }

    private void enterPressed() {
        if (isCalculateButtonEnabled()) {
            calculateRoot();
        }
    }

    private void parseInput() {
        try {
            if (!leftPointOfRange.isEmpty()) {
                Double.parseDouble(leftPointOfRange);
            }
            if (!rightPointOfRange.isEmpty()) {
                Double.parseDouble(rightPointOfRange);

            }
            if (!function.isEmpty()) {
                converter = new ConverterToPolishNotation();
                converter.convert(function);
            }
            if (!derivative.isEmpty()) {
                converter = new ConverterToPolishNotation();
                converter.convert(derivative);
            }
        } catch (ArithmeticException e) {
            status = Status.BAD_FORMAT_FUNCTION;
            isCalculateButtonEnabled = false;
            return;
        } catch (Exception e) {
            status = Status.BAD_FORMAT_RANGE;
            isCalculateButtonEnabled = false;
            return;
        }

        isCalculateButtonEnabled = isInputAvailable();
        if (isCalculateButtonEnabled) {
            if (!isRangeCorrect()) {
                status = Status.BAD_FORMAT_RANGE;
                isCalculateButtonEnabled = false;
                return;
            }
            status = Status.READY;
        } else {
            status = Status.WAITING;
        }
    }

    private boolean isRangeCorrect() {
        return Double.parseDouble(leftPointOfRange) < Double.parseDouble(rightPointOfRange);
    }

    private boolean isInputAvailable() {
        return !function.isEmpty() && !derivative.isEmpty() && !leftPointOfRange.isEmpty()
                && !rightPointOfRange.isEmpty();
    }


    private void calculateRoot() {
        NewtonMethod newtonMethod = new NewtonMethod(function, derivative);
        try {
            root = newtonMethod.searchRoot(Double.parseDouble(leftPointOfRange),
                                           Double.parseDouble(rightPointOfRange));
        } catch (IllegalArgumentException e) {
            status = Status.NO_ROOT;
            return;
        }
        status = Status.SUCCESS;
    }
}
