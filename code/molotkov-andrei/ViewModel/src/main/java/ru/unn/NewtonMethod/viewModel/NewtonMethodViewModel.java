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
    private static final int NUMBER_FIELDS = 4;
    private static final int NUMBER_DECIMAL_PLACES = 1000;
    private int numberFillFields = 0;

    public NewtonMethodViewModel() {
        isCalculateButtonEnabled = false;
        function = "";
        derivative = "";
        leftPointOfRange = "";
        rightPointOfRange = "";
        root = 0;
        status = Status.WAITING;
        converter = new ConverterToPolishNotation();
    }

    public enum KeyboardKeys {
        ENTER(10),
        ANY(777);

        KeyboardKeys(final int key) {
            this.key = key;
        }

        public int getKey() {
            return key;
        }

        private int key;
    }

    public enum Status {
        WAITING("Please provide input data"),
        READY("Press 'Calculate' or Enter"),
        BAD_FORMAT_RANGE("Bad format of range"),
        BAD_FORMAT_FUNCTION("Incorrect function"),
        SUCCESS("Success"),
        NO_ROOT("Root is not in range");

        Status(final String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }

        private String message;
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
        this.function = function;
    }

    public void setDerivative(final String derivative) {
        if (derivative.equals(this.derivative)) {
            return;
        }
        this.derivative = derivative;
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

    public String  getLeftPoint() {
        return leftPointOfRange;
    }

    public String getRightPoint() {
        return rightPointOfRange;
    }

    public String getRoot() {
        return String.valueOf(root);
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
            checkInputValues();
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
            checkRangeCorrect();
            status = Status.READY;
        } else {
            status = Status.WAITING;
        }
    }

    private void checkInputValues() {
        numberFillFields = 0;
        if (!leftPointOfRange.isEmpty()) {
            Double.parseDouble(leftPointOfRange);
            numberFillFields++;
        }
        if (!rightPointOfRange.isEmpty()) {
            Double.parseDouble(rightPointOfRange);
            numberFillFields++;
        }
        if (!function.isEmpty()) {
            function += "=";
            converter.convert(function);
            numberFillFields++;
        }
        if (!derivative.isEmpty()) {
            derivative += "=";
            converter.convert(derivative);
            numberFillFields++;
        }
    }

    private void checkRangeCorrect() {
        if (Double.parseDouble(leftPointOfRange) >= Double.parseDouble(rightPointOfRange)) {
            String temp = leftPointOfRange;
            leftPointOfRange = rightPointOfRange;
            rightPointOfRange = temp;
        }
    }

    private boolean isInputAvailable() {
        return numberFillFields == NUMBER_FIELDS;
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
        roundResult(NUMBER_DECIMAL_PLACES);
        status = Status.SUCCESS;
    }

    private void roundResult(final int numberDecimalPlaces) {
        root *= numberDecimalPlaces;
        root = Math.round(root);
        root /= numberDecimalPlaces;
    }
}
