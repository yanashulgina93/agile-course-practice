package ru.unn.agile.IntegrationMethods.viewmodel;

public class ViewModel {
    private Function function;
    private String lowerLimit;
    private String upperLimit;
    private IntegrationMethod integrationMethod;
    private String result;
    private String status;

    public ViewModel() {
        function = Function.X;
        lowerLimit = "";
        upperLimit = "";
        integrationMethod = IntegrationMethod.LEFT_RECTANGLES;
        result = "";
        status = Status.WAITING;
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

    public enum Function {
        X("x"),
        COS("cos(x)"),
        EXP("exp(x)");
        private final String name;

        private Function(final String name) {
            this.name = name;
        }

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

        public String toString() {
            return name;
        }
    }
}
