package ru.unn.agile.Metrics.viewmodel;

import ru.unn.agile.Metrics.model.Metric;
import ru.unn.agile.Metrics.model.DistanceCalculator;

public class DistanceCalculatorViewModel {

    private String firstVec;
    private String secondVec;
    private String errorMessage;
    private String distance;
    private boolean calculateButtonEnabled;
    private Metric metric;
    private final DistanceCalculator calculator = new DistanceCalculator();

    public DistanceCalculatorViewModel() {
        firstVec = "";
        secondVec = "";
        errorMessage = "";
        distance = "";
        calculateButtonEnabled = false;
        metric = Metric.RHO_INF;
    }

    public boolean isCalculateButtonEnabled() {
        return calculateButtonEnabled;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public String getDistance() {
        return distance;
    }

    public void setVectors(final String firstVec, final String secondVec) {
        String pattern = "((-?\\d+\\.\\d+)*\\s)*(-?\\d+.\\d+)";
        boolean isBadInputFormat = !firstVec.matches(pattern) || !secondVec.matches(pattern);
        if (isBadInputFormat) {
            error("Bad vector format");
        } else {
            boolean haveDifferentSize = firstVec.split(" ").length != secondVec.split(" ").length;
            if (haveDifferentSize) {
                error("Vectors have different size");
            } else {
                calculateButtonEnabled = true;
                errorMessage = "";
                this.firstVec = firstVec;
                this.secondVec = secondVec;
            }
        }
    }

    public void setMetric(final Metric metric) {
        this.metric = metric;
    }

    public void calculate() {
        float[] firstVector = parseVector(firstVec);
        float[] secondVector = parseVector(secondVec);
        distance = Float.toString(calculator.calculateDistance(firstVector, secondVector, metric));
    }

    private void error(final String errorMessage) {
        calculateButtonEnabled = false;
        this.errorMessage = errorMessage;
    }

    private float[] parseVector(final String rawVector) {
        float[] vector;
        String[] parts = rawVector.split(" ");
        vector = new float[parts.length];
        for (int i = 0; i < parts.length; i++) {
            float component = Float.parseFloat(parts[i]);
            vector[i] = component;
        }
        return vector;
    }
}
