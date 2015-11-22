package ru.unn.NewtonMethod.viewModel;

import ru.unn.agile.NewtonMethod.NewtonMethod;
import ru.unn.agile.NewtonMethod.NoMonotonicFunctionException;
import ru.unn.agile.NewtonMethod.NoRootInRangeException;

class BadFunctionException extends Exception {
    public BadFunctionException(final String message) {
        super(message);
    }
}

public class NewtonMethodViewModel {
    private boolean isCalculateButtonEnabled;
    private String function;
    private String derivative;
    private String leftPointOfRange;
    private String rightPointOfRange;
    private String status;
    private double root;
    private ParserFunction parser;

    public NewtonMethodViewModel() {
        isCalculateButtonEnabled = false;
        function = "";
        derivative = "";
        leftPointOfRange = "";
        rightPointOfRange = "";
        root = 0;
        status = Status.WAITING;
    }

    public boolean isCalculateButtonEnabled() {
        return isCalculateButtonEnabled;
    }

    public void processKeyInTextField(final int keyCode) {
        parseInputRange();

        if (keyCode == KeyboardKeys.ENTER) {
            enterPressed();
        }
    }

    private void enterPressed() {
        if (isCalculateButtonEnabled()) {
            calculateRoot();
        }
    }

    private boolean parseInputRange() {
        try {
            if (!leftPointOfRange.isEmpty() && !rightPointOfRange.isEmpty() && !isCorrectRange()) {
                throw new Exception("Incorrect range");
            }
            if (!leftPointOfRange.isEmpty()) {
                Double.parseDouble(leftPointOfRange);
            }
            if (!rightPointOfRange.isEmpty()) {
                Double.parseDouble(rightPointOfRange);

            }
            if (!function.isEmpty()) {
                parser = new ParserFunction();
                if (!parser.isCorrectFunction(function)) {
                    throw new BadFunctionException("Incorrect function");
                }
            }
            if (!derivative.isEmpty()) {
                parser = new ParserFunction();
                if (!parser.isCorrectFunction(derivative)) {
                    throw new BadFunctionException("Incorrect derivative");
                }
            }
        } catch (BadFunctionException e) {
            status = Status.BAD_FORMAT_FUNCTION;
            isCalculateButtonEnabled = false;
            return false;
        } catch (Exception e) {
            status = Status.BAD_FORMAT_RANGE;
            isCalculateButtonEnabled = false;
            return false;
        }

        isCalculateButtonEnabled = isInputAvailable();
        if (isCalculateButtonEnabled) {
            status = Status.READY;
        } else {

            status = Status.WAITING;
        }
        return isCalculateButtonEnabled;
    }

    private boolean isCorrectRange() {
        return Double.parseDouble(leftPointOfRange) < Double.parseDouble(rightPointOfRange);
    }

    private boolean isInputAvailable() {
        return !function.isEmpty() && !derivative.isEmpty() && !leftPointOfRange.isEmpty()
                && !rightPointOfRange.isEmpty();
    }


    public void calculateRoot() {
        if (!parseInputRange()) {
            return;
        }

        NewtonMethod newtonMethod = new NewtonMethod(function, derivative);
        try {
            root = newtonMethod.searchRoot(Double.parseDouble(leftPointOfRange),
                    Double.parseDouble(rightPointOfRange));
        } catch (NoMonotonicFunctionException e) {
            status = Status.NO_MONOTONIC;
            return;
        } catch (NoRootInRangeException e) {
            status = Status.NO_ROOT;
            return;
        }
        status = Status.SUCCESS;
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
        return status;
    }

    public final class Status {
        public static final String WAITING = "Please provide input data";
        public static final String READY = "Press 'Calculate' or Enter";
        public static final String BAD_FORMAT_RANGE = "Bad format of range";
        public static final String BAD_FORMAT_FUNCTION = "Incorrect function";
        public static final String SUCCESS = "Success";
        public static final String NO_MONOTONIC = "Function is not monotonic in range";
        public static final String NO_ROOT = "Root is not in range";

        private Status() { }
    }
}
