package ru.unn.agile.IntegrationMethods.core;

public class Integrator {
    static final double MAX_STEP = 0.0001;
    private double lowerLimit;
    private double upperLimit;
    private double step;
    private Function func;

    public Integrator(final double lowerLim, final double upperLim, final String strFunc) {
        this.lowerLimit = lowerLim;
        this.upperLimit = upperLim;
        int segmentsNumber = (int) ((upperLimit - lowerLimit) / MAX_STEP) + 1;
        step = (upperLimit - lowerLimit) / segmentsNumber;
        func = new Function(strFunc);
    }
    public double leftRectangles() {
        double result = 0.0;
        for (double x = lowerLimit; x < upperLimit; x += step) {
            result += func.getValue(x);
        }
        result *= step;
        return result;
    }
    public double rightRectangles() {
        double result = 0.0;
        for (double x = lowerLimit + step; x <= upperLimit; x += step) {
            result += func.getValue(x);
        }
        result *= step;
        return result;
    }
    public double midpointRectangles() {
        double result = 0.0;
        for (double x = lowerLimit + (step / 2.0); x < upperLimit; x += step) {
            result += func.getValue(x);
        }
        result *= step;
        return result;
    }
    public void setLowerLimit(final double lowerLim) {
        this.lowerLimit = lowerLim;
    }

    public double getLowerLimit() {
        return lowerLimit;
    }

    public void setUpperLimit(final double upperLim) {
        this.upperLimit = upperLim;
    }

    public double getUpperLimit() {
        return upperLimit;
    }

    public void setStep(final double step) {
        this.step = step;
    }

    public double getStep() {
        return step;
    }
    public void setFunc(final Function func) {
        this.func = func;
    }

    public Function getFunc() {
        return func;
    }

}

