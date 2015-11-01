package ru.unn.agile.NewtonMethod;

public class NewtonMethod {
    private final Function basicFunc;
    private final Function derivativeFunc;
    private double previousPoint;
    private double nextPoint;

    public NewtonMethod(final String basicFunc, final String derivativeFunc) {
        this.basicFunc = new Function(basicFunc);
        this.derivativeFunc = new Function(derivativeFunc);
    }

    private double calculateNextPoint(final double point) {
        return point - basicFunc.calculate(point) / derivativeFunc.calculate(point);
    }

    private boolean isStopAlgorithm() {
        final double eps = 0.000001;
        if (Math.abs(nextPoint - previousPoint) > eps) {
            return false;
        }
        return true;
    }

    private boolean rootIsInRange(final double leftPointRange, final double rightPointRange) {
        if (basicFunc.calculate(leftPointRange) * basicFunc.calculate(rightPointRange) > 0) {
            return false;
        }
        return true;
    }

    private int getSingDerivative(final double point) {
        double singDerivative = derivativeFunc.calculate(point);
        if (singDerivative > 0) {
            return 1;
        } else if (singDerivative < 0) {
            return -1;
        }
        return 0;
    }

    private boolean isMonotonicFunc(final double leftPointRange, final double rightPointRange) {
        final int countRangesForMonotonic = 10;
        final int countPointsForMonotonic = countRangesForMonotonic + 1;
        double stepLength = (rightPointRange - leftPointRange) / countRangesForMonotonic;
        boolean result = true;

        int singDerivativeFirstPoint = getSingDerivative(leftPointRange);
        int singDerivativeSecondPoint = getSingDerivative(leftPointRange + stepLength);

        if (singDerivativeFirstPoint != singDerivativeSecondPoint) {
            return false;
        }
        int singDerivative;
        double nextPoint;
        for (int numberPoint = 2; numberPoint < countPointsForMonotonic; numberPoint++) {
            nextPoint = leftPointRange + stepLength * numberPoint;
            singDerivative = getSingDerivative(nextPoint);
            if (singDerivative != singDerivativeFirstPoint) {
                result = false;
                break;
            }
        }
        return result;
    }

    private boolean canCalculateRoot(final double leftPointRange, final double rightPointRange) {
        if (!rootIsInRange(leftPointRange, rightPointRange)) {
            return false;
        }
        if (!isMonotonicFunc(leftPointRange, rightPointRange)) {
            return false;
        }
        return true;
    }

    public Double searchRoot(final double leftPointRange, final double rightPointRange) {
        if (!canCalculateRoot(leftPointRange, rightPointRange)) {
            return null;
        }
        previousPoint = rightPointRange;
        nextPoint = calculateNextPoint(previousPoint);
        while (!isStopAlgorithm()) {
            previousPoint = nextPoint;
            nextPoint = calculateNextPoint(previousPoint);
        }
        return nextPoint;
    }
}
