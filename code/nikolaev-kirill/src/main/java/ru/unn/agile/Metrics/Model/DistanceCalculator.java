package ru.unn.agile.Metrics.Model;

class DistanceCalculator {

    private static final double RHO_TWO = 2.0;
    private static final double RHO_THREE = 3.0;
    private static final double RHO_FOUR = 4.0;

    public float calculateDistance(final float[] firstVector, final float[] secondVector,
                                   final Metric metric) {
        if (isIncorrectInput(firstVector, secondVector)) {
            throw new IllegalArgumentException("Input is incorrect: null or different size");
        }
        if (isTheSame(firstVector, secondVector)) {
            return 0.0f;
        }
        switch (metric) {
            case RHO_INF:
                return calculateDistanceInRhoInf(firstVector, secondVector);
            case RHO_ONE:
                return calculateDistanceInRhoOne(firstVector, secondVector);
            case RHO_TWO:
                return calculateMinkowskiDistance(firstVector, secondVector, RHO_TWO);
            case RHO_THREE:
                return calculateMinkowskiDistance(firstVector, secondVector, RHO_THREE);
            case RHO_FOUR:
                return calculateMinkowskiDistance(firstVector, secondVector, RHO_FOUR);
            default:
                return 0.0f;
        }
    }

    private float calculateDistanceInRhoInf(final float[] firstVector, final float[] secondVector) {
        float distance = 0.0f;
        for (int i = 0; i < firstVector.length; i++) {
            float diff = Math.abs(firstVector[i] - secondVector[i]);
            if (diff > distance) {
                distance = diff;
            }
        }
        return distance;
    }

    private float calculateDistanceInRhoOne(final float[] firstVector, final float[] secondVector) {
        float distance = 0.0f;
        for (int i = 0; i < firstVector.length; i++) {
            distance += Math.abs(firstVector[i] - secondVector[i]);
        }
        return distance;
    }

    private float calculateMinkowskiDistance(final float[] firstVector, final float[] secondVector,
                                             final double metricParameter) {
        float distance = 0.0f;
        for (int i = 0; i < firstVector.length; i++) {
            float diff = Math.abs(firstVector[i] - secondVector[i]);
            distance += ((float) Math.pow(((double) diff), metricParameter));
        }
        return ((float) Math.pow(((double) distance), 1.0 / metricParameter));
    }

    private boolean isIncorrectInput(final float[] firstVector, final float[] secondVector) {
        return isNull(firstVector, secondVector) || isDifferentSize(firstVector, secondVector);
    }

    private boolean isNull(final float[] firstVector, final float[] secondVector) {
        return firstVector == null || secondVector == null;
    }

    private boolean isDifferentSize(final float[] firstVector, final float[] secondVector) {
        return firstVector.length != secondVector.length;
    }

    private boolean isTheSame(final float[] firstVector, final float[] secondVector) {
        return firstVector == secondVector;
    }
}
