package ru.unn.agile.IntegrationMethods.core;

public class Function {
    private String stringFunction;

    public Function(final String stringFunction) {
        this.stringFunction = stringFunction;
    }

    public double getValue(final double x) {
        double y = 0.0;

          if ("x".equals(stringFunction)) {
            y = x;
        } else if ("exp(x)".equals(stringFunction)) {
            y = Math.exp(x);
        } else if ("cos(x)".equals(stringFunction)) {
            y = Math.cos(x);
        } else {
              throw new IllegalArgumentException("Function can be only x, cos(x), or exp(x)");
          }

        return y;
    }

    public void setStringFunction(final String stringFuncrion) {
        this.stringFunction = stringFunction;
    }

    public String getStringFunction() {
        return stringFunction;
    }
}
