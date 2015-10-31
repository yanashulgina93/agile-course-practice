package ru.unn.agile.IntegrationMethods.core;

public class Function {
    private String stringFunc;

    public Function(final String str) {
        stringFunc = str;
    }

    public double getValue(final double x) {
        double y = 0.0;

        if (stringFunc == "x") {
            y = x;
        } else if (stringFunc == "exp(x)") {
            y = Math.exp(x);
        } else if (stringFunc == "cos(x)") {
            y = Math.cos(x);
        }

        return y;
    }

    public void setStringFunc(final String str) {
        this.stringFunc = str;
    }

    public String getStringFunc() {
        return stringFunc;
    }
}
