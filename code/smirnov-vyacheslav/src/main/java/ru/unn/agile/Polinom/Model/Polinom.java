package main.java.ru.unn.agile.Polinom.Model;

import java.math.*;

public class Polinom {
    private double[] coefficients;
    private static final int PRECISION = 4;

    public Polinom() {
        coefficients = new double[1];
    }

    public Polinom(final double[] inputCoefficients) {
        coefficients = new double[inputCoefficients.length];

        for (int i = 0; i < inputCoefficients.length; i++) {
            coefficients[i] = inputCoefficients[i];
        }
        reduceIfHighDegreeIsNull();
    }

    public double[] getCoefficients() {
        return coefficients;
    }

    public void add(final Polinom operand) {
        double[] operandCoefficients = operand.getCoefficients();
        extendPolinom(max(coefficients.length, operandCoefficients.length));

        for (int i = 0; i < operandCoefficients.length; i++) {
            coefficients[i] += operandCoefficients[i];
            coefficients[i] = round(coefficients[i], PRECISION);
        }
        reduceIfHighDegreeIsNull();
    }

    public void subtract(final Polinom operand) {
        double[] operandCoefficients = operand.getCoefficients();
        extendPolinom(max(coefficients.length, operandCoefficients.length));

        for (int i = 0; i < operandCoefficients.length; i++) {
            coefficients[i] -= operandCoefficients[i];
            coefficients[i] = round(coefficients[i], PRECISION);
        }
        reduceIfHighDegreeIsNull();
    }

    public void multiply(final Polinom operand) {
        double[] firstCoefficients = getCoefficients();
        double[] secondCoefficients = operand.getCoefficients();
        coefficients = new double[1];
        extendPolinom(firstCoefficients.length + secondCoefficients.length);

        for (int i = 0; i < firstCoefficients.length; i++) {
            for (int j = 0; j < secondCoefficients.length; j++) {
                coefficients[i + j] += firstCoefficients[i] * secondCoefficients[j];
            }
        }
        reduceIfHighDegreeIsNull();
    }

    public void divide(final Polinom operand) {
        double[] dividerCoefficients = operand.getCoefficients();
        Polinom quotient = new Polinom();

        int dividendHighDegree = getHighDegree();
        int dividerHighDegree = operand.getHighDegree();

        if (dividerHighDegree == 0 && dividerCoefficients[0] == 0) {
            throw new IllegalArgumentException("Divider can't be zero!");
        }

        if (dividerHighDegree > dividendHighDegree) {
            throw new IllegalArgumentException(
                "Divider's degree can't be large than dividend's!");
        }

        boolean lastTime = false;
        while (dividendHighDegree >= dividerHighDegree && !lastTime) {
            if (dividendHighDegree == 0) {
                 lastTime = true;
            }

            int stepCoefficient = dividendHighDegree - dividerHighDegree;
            double[] stepQuotientCoefficients = new double[stepCoefficient + 1];
            stepQuotientCoefficients[stepCoefficient] = coefficients[dividendHighDegree]
            / dividerCoefficients[dividerHighDegree];
            Polinom stepQuotient = new Polinom(stepQuotientCoefficients);

            quotient.add(stepQuotient);
            stepQuotient.multiply(operand);
            subtract(stepQuotient);
            reduceIfHighDegreeIsNull();
            dividendHighDegree = getHighDegree();
        }

        coefficients = quotient.getCoefficients();
    }

    private double round(final double value, final int presicion) {
        if (presicion < 0) {
            throw new IllegalArgumentException();
        }

        BigDecimal bd = new BigDecimal(Double.toString(value));
        bd = bd.setScale(presicion, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    private int max(final int first, final int second) {
        return first > second ? first : second;
    }

    private int getHighDegree() {
        return coefficients.length - 1;
    }

    private void extendPolinom(final int newSize) {
        if (newSize == coefficients.length) {
            return;
        }

        double[] newCoefficients = new double[newSize];

        for (int i = 0; i < coefficients.length; i++) {
            newCoefficients[i] = coefficients[i];
        }

        coefficients = newCoefficients;
    }

    private void reduceIfHighDegreeIsNull() {
        int highNotNullCoefficient = getHighDegree();

        while (coefficients[highNotNullCoefficient] == 0
            && highNotNullCoefficient > 0) {
            highNotNullCoefficient--;
        }

        double[] reducedCoefficient = new double[++highNotNullCoefficient];

        for (int i = 0; i < highNotNullCoefficient; i++) {
            reducedCoefficient[i] = coefficients[i];
        }
        coefficients = reducedCoefficient;
    }
}
