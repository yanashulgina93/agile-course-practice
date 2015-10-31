package ru.unn.agile.IntegrationMethods.core;

public class Integrator {
    static final double MAX_STEP = 0.0001;
    private double lowerLimit;
    private double upperLimit;
    private double step;
    private Function func;
    private double integralValue;

    static final double ODD_TERMS_SYMPSON_COEF = 4.0;
    static final double EVEN_TERMS_SYMPSON_COEF = 2.0;
    static final double FINAL_SYMPSON_COEF = 3.0;

    public Integrator(final double lowerLim, final double upperLim, final String strFunc) {
        this.lowerLimit = lowerLim;
        this.upperLimit = upperLim;
        int segmentsNumber = (int) ((upperLimit - lowerLimit) / MAX_STEP) + 1;
        step = (upperLimit - lowerLimit) / segmentsNumber;
        func = new Function(strFunc);
        integralValue = 0.0;
    }

    public double leftRectangles() {
        for (double x = lowerLimit; x < upperLimit; x += step) {
            integralValue += func.getValue(x);
        }
        integralValue *= step;

        return integralValue;
    }

    public double rightRectangles() {
        for (double x = lowerLimit + step; x <= upperLimit; x += step) {
            integralValue += func.getValue(x);
        }
        integralValue *= step;

        return integralValue;
    }

    public double midpointRectangles() {
        for (double x = lowerLimit + step / 2.0; x < upperLimit; x += step) {
            integralValue += func.getValue(x);
        }
        integralValue *= step;

        return integralValue;
    }

    public double trapezes() {
        for (double x = lowerLimit; x < upperLimit; x += step) {
            integralValue += (func.getValue(x) + func.getValue(x + step)) / 2.0;
        }
        integralValue *= step;

        return integralValue;
    }

    public double simpson() {
        double smallStep = step / 2.0;

        integralValue += func.getValue(lowerLimit) + func.getValue(upperLimit);
        for (double x = lowerLimit + smallStep; x < upperLimit; x += step) {
            integralValue += ODD_TERMS_SYMPSON_COEF * func.getValue(x);
        }
        for (double x = lowerLimit + step; x < upperLimit; x += step) {
            integralValue += EVEN_TERMS_SYMPSON_COEF * func.getValue(x);
        }
        integralValue *= smallStep / FINAL_SYMPSON_COEF;

        return integralValue;
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

    public void setIntegralValue(final double value) {
        this.integralValue = value;
    }

    public double getIntegralValue() {
        return integralValue;
    }
}
