package ru.unn.agile.IntegrationMethods.viewmodel;

import ru.unn.agile.IntegrationMethods.Model.*;

public class ViewModel {
    private Function function;
    private String lowerLimit;
    private String upperLimit;
    private IntegrationMethod integrationMethod;
    private String result;
    private String status;
    private boolean isIntegrateButtonEnabled;

    public ViewModel() {
        function = Function.X;
        lowerLimit = "";
        upperLimit = "";
        integrationMethod = IntegrationMethod.LEFT_RECTANGLES;
        result = "";
        status = Status.WAITING;
        isIntegrateButtonEnabled = false;
    }

    private boolean areLimitsTextFieldsNotEmpty() {
        return !lowerLimit.isEmpty() && !upperLimit.isEmpty();
    }

    private boolean parseLimitsInput() {
        try {
            if (!lowerLimit.isEmpty()) {
                Double.parseDouble(lowerLimit);
            }
            if (!upperLimit.isEmpty()) {
                Double.parseDouble(upperLimit);
            }
        } catch (Exception e) {
            status = Status.BAD_FORMAT;
            isIntegrateButtonEnabled = false;
            return false;
        }

        isIntegrateButtonEnabled = areLimitsTextFieldsNotEmpty();
        if (isIntegrateButtonEnabled) {
            status = Status.READY;
        } else {
            status = Status.WAITING;
        }

        return isIntegrateButtonEnabled;
    }

    private  IFunction createIFunctionObject() {
        IFunction iFunction;
        switch (function) {
            case X:
                iFunction = new XFunction();
                break;
            case COS:
                iFunction = new CosFunction();
                break;
            case EXP:
                iFunction = new ExpFunction();
                break;
            default:
                throw new IllegalArgumentException("Only x, cos(x) and exp(x) are supported");
        }
        return iFunction;
    }

    public void integrate() {
        if (!parseLimitsInput()) {
            return;
        }
        IFunction iFunction = createIFunctionObject();
        Integrator integrator = new Integrator(Double.parseDouble(lowerLimit),
                Double.parseDouble(upperLimit), iFunction);

        switch (integrationMethod) {
            case LEFT_RECTANGLES:
                integrator.leftRectangles();
                break;
            case RIGHT_RECTANGLES:
                integrator.rightRectangles();
                break;
            case MIDPOINT_RECTANGLES:
                integrator.midpointRectangles();
                break;
            case TRAPEZES:
                integrator.trapezes();
                break;
            case SIMPSON:
                integrator.simpson();
                break;
            default:
                throw new IllegalArgumentException("This method is not supported");
        }

        result = Double.toString(integrator.getIntegralValue());
        status = Status.SUCCESS;
    }

    public void processKeyInTextField(final int keyCode) {
        parseLimitsInput();

        if (keyCode == KeyboardKeys.ENTER) {
            pressEnter();
        }
    }

    private void pressEnter() {

        if (isIntegrateButtonEnabled()) {
            integrate();
        }
    }

    public Function getFunction() {
        return function;
    }

    public void setFunction(final Function function) {
        this.function = function;
    }

    public String getLowerLimit() {
        return lowerLimit;
    }

    public void setLowerLimit(final String lowerLimit) {
        this.lowerLimit = lowerLimit;
    }

    public String getUpperLimit() {
        return upperLimit;
    }

    public void setUpperLimit(final String upperLimit) {
        this.upperLimit = upperLimit;
    }

    public IntegrationMethod getIntegrationMethod() {
        return integrationMethod;
    }

    public void setIntegrationMethod(final IntegrationMethod integrationMethod) {
        this.integrationMethod = integrationMethod;
    }

    public String getResult() {
        return result;
    }

    public String getStatus() {
        return status;
    }

    public boolean isIntegrateButtonEnabled() {
        return isIntegrateButtonEnabled;
    }

    public enum Function {
        X("x"),
        COS("cos(x)"),
        EXP("exp(x)");
        private final String name;

        private Function(final String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return name;
        }
    }

    public enum IntegrationMethod {
        LEFT_RECTANGLES("left rectangles method"),
        RIGHT_RECTANGLES("right rectangles method"),
        MIDPOINT_RECTANGLES("midpoint rectangles method"),
        TRAPEZES("trapezes method"),
        SIMPSON("Simpson method");
        private final String name;

        private IntegrationMethod(final String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return name;
        }
    }
}
