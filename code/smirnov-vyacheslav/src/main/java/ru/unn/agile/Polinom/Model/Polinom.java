package main.java.ru.unn.agile.Polinom.Model;

public class Polinom {
    private double[] coefficients;
    private final Polinom residue;

    public Polinom() {
        coefficients = new double[1];
        residue = new Polinom();
    }

    public Polinom(final double[] inputCoefficients) {
        coefficients = new double[inputCoefficients.length];

        for (int i = 0; i < inputCoefficients.length; i++) {
            coefficients[i] = inputCoefficients[i];
        }
        reduceIfHighDegreeIsNull();

        residue = new Polinom();
    }

    public double[] getCoefficients() {
        return coefficients;
    }

    public void add(final Polinom operand) {
        double[] operandCoefficients = operand.getCoefficients();
        extendPolinom(max(coefficients.length, operandCoefficients.length));

        for (int i = 0; i < operandCoefficients.length; i++) {
            coefficients[i] += operandCoefficients[i];
        }
        reduceIfHighDegreeIsNull();
    }

    public void subtract(final Polinom operand) {
        double[] operandCoefficients = operand.getCoefficients();
        extendPolinom(max(coefficients.length, operandCoefficients.length));

        for (int i = 0; i < operandCoefficients.length; i++) {
            coefficients[i] -= operandCoefficients[i];
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

        while (dividendHighDegree >= dividerHighDegree) {
            int stepCoefficient = dividendHighDegree - dividerHighDegree + 1;
            double[] stepQuotientCoefficients = new double[stepCoefficient];
            stepQuotientCoefficients[stepCoefficient] = coefficients[dividendHighDegree]
            / dividerCoefficients[dividerHighDegree];
            Polinom stepQuotient = new Polinom(stepQuotientCoefficients);

            quotient.add(stepQuotient);
            stepQuotient.multiply(operand);
            subtract(stepQuotient);

            reduceIfHighDegreeIsNull();
            dividendHighDegree = getHighDegree();
        }

        residue.add(this);
        coefficients = quotient.getCoefficients();
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

        if (coefficients[highNotNullCoefficient] == 0) {
            double[] reducedCoefficient = new double[highNotNullCoefficient + 1];

            for (int i = 0; i < highNotNullCoefficient; i++) {
                reducedCoefficient[i] = coefficients[i];
            }

            coefficients = reducedCoefficient;
        }
    }
}
