package ru.unn.agile.Triangle.Model;

import java.util.Arrays;
import java.util.List;

import static java.lang.Math.acos;
import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public class Triangle {
    private final List<Double> coordinatesOfPoint1;
    private final List<Double> coordinatesOfPoint2;
    private final List<Double> coordinatesOfPoint3;
    private static final double THE_HALF = 0.5;

    public Triangle(final List<Double> coordinatesOfPoint1,
                    final List<Double> coordinatesOfPoint2,
                    final List<Double> coordinatesOfPoint3,
                    final int inputDimension) {
        if (!hasEqualDimensions(coordinatesOfPoint1, coordinatesOfPoint1,
                coordinatesOfPoint3, inputDimension)) {
            throw new IllegalArgumentException();
        }
        this.coordinatesOfPoint1 = coordinatesOfPoint1;
        this.coordinatesOfPoint2 = coordinatesOfPoint2;
        this.coordinatesOfPoint3 = coordinatesOfPoint3;
    }

    public List<Double> getCoordinatesOfPoint1() {
        return coordinatesOfPoint1;
    }

    public List<Double> getCoordinatesOfPoint2() {
        return coordinatesOfPoint2;
    }

    public List<Double> getCoordinatesOfPoint3() {
        return coordinatesOfPoint3;
    }

    private boolean hasEqualDimensions(final List<Double> coordinatesOfPoint1,
                                      final List<Double> coordinatesOfPoint2,
                                      final List<Double> coordinatesOfPoint3,
                                      final int dimension) {
        return coordinatesOfPoint1.size() == dimension
                && coordinatesOfPoint2.size() == dimension
                && coordinatesOfPoint3.size() == dimension;
    }

    public boolean isPossibleToBuildNondegenerateTriangle() throws Exception {
        List<Double> lengthsOfEdges = this.getLengthsOfEdges();
        double lengthOfEdge1 = lengthsOfEdges.get(0);
        double lengthOfEdge2 = lengthsOfEdges.get(1);
        double lengthOfEdge3 = lengthsOfEdges.get(2);
        return lengthOfEdge1 < lengthOfEdge2 + lengthOfEdge3
                && lengthOfEdge2 < lengthOfEdge1 + lengthOfEdge3
                && lengthOfEdge3 < lengthOfEdge1 + lengthOfEdge2;
    }

    public double getLength(final List<Double> coordinatesOfPoint1,
                            final List<Double> coordinatesOfPoint2) throws Exception {
        double sum = 0.0;
        for (int i = 0; i < coordinatesOfPoint1.size(); i++) {
            if (sum > Double.MAX_VALUE) {
                throw new Exception("Overflow when counting length!");
            }
            sum += pow(coordinatesOfPoint1.get(i) - coordinatesOfPoint2.get(i), 2);
        }
        return sqrt(sum);
    }

    public List<Double> getLengthsOfEdges() throws Exception {
        double length1 = this.getLength(this.coordinatesOfPoint1, this.coordinatesOfPoint2);
        double length2 = this.getLength(this.coordinatesOfPoint2, this.coordinatesOfPoint3);
        double length3 = this.getLength(this.coordinatesOfPoint3, this.coordinatesOfPoint1);
        return Arrays.asList(length1, length2, length3);
    }

    public double getPerimeter() throws Exception {
        List<Double> lengths = this.getLengthsOfEdges();
        if (lengths.get(0) + lengths.get(1) > Double.MAX_VALUE - lengths.get(2)) {
            throw new Exception("Overflow when counting perimeter!");
        }
            return lengths.get(0) + lengths.get(1) + lengths.get(2);
    }

    public double getSquare() throws Exception {
        List<Double> lengths = this.getLengthsOfEdges();
        double halfPerimeter = this.getPerimeter() / 2;
        if (halfPerimeter * (halfPerimeter - lengths.get(0))
                * (halfPerimeter - lengths.get(1))
                * (halfPerimeter - lengths.get(2)) > Double.MAX_VALUE) {
            throw new Exception("Overflow when counting square!");
        }
        return sqrt(halfPerimeter * (halfPerimeter - lengths.get(0))
                * (halfPerimeter - lengths.get(1)) * (halfPerimeter - lengths.get(2)));
    }

    public List<Double> getMedians() throws Exception {
        List<Double> lengths = this.getLengthsOfEdges();
        double median1 = THE_HALF * sqrt(2 * pow(lengths.get(1), 2)
                + 2 * pow(lengths.get(2), 2) - pow(lengths.get(0), 2));
        double median2 = THE_HALF * sqrt(2 * pow(lengths.get(0), 2)
                + 2 * pow(lengths.get(2), 2) - pow(lengths.get(1), 2));
        double median3 = THE_HALF * sqrt(2 * pow(lengths.get(0), 2)
                + 2 * pow(lengths.get(1), 2) - pow(lengths.get(2), 2));
        return Arrays.asList(median1, median2, median3);
    }

    public List<Double> getAltitudes() throws Exception {
        List<Double> lengths = this.getLengthsOfEdges();
        if (lengths.get(0) == 0.0 || lengths.get(1) == 0.0 || lengths.get(2) == 0.0) {
            throw new ArithmeticException("/0 when counting altitudes!");
        }
        double altitude1 = 2 * this.getPerimeter() / lengths.get(0);
        double altitude2 = 2 * this.getPerimeter() / lengths.get(1);
        double altitude3 = 2 * this.getPerimeter() / lengths.get(2);
        return Arrays.asList(altitude1, altitude2, altitude3);
    }

    public List<Double> getBisectrices() throws Exception {
        List<Double> lengths = this.getLengthsOfEdges();
        if (lengths.get(1) + lengths.get(2) == 0.0 || lengths.get(0) + lengths.get(2) == 0.0
                || lengths.get(0) + lengths.get(1) == 0.0) {
            throw new ArithmeticException("/0 when counting bisectrices!");
        }
        double bisectrix1 = sqrt(lengths.get(1) * lengths.get(2) * this.getPerimeter()
                * (lengths.get(1) + lengths.get(2) - lengths.get(0)))
                / (lengths.get(1) + lengths.get(2));
        double bisectrix2 = sqrt(lengths.get(0) * lengths.get(2) * this.getPerimeter()
                * (lengths.get(0) + lengths.get(2) - lengths.get(1)))
                / (lengths.get(0) + lengths.get(2));
        double bisectrix3 = sqrt(lengths.get(0) * lengths.get(1) * this.getPerimeter()
                * (lengths.get(0) + lengths.get(1) - lengths.get(2)))
                / (lengths.get(0) + lengths.get(1));
        return Arrays.asList(bisectrix1, bisectrix2, bisectrix3);
    }

    public List<Double> getAngles() throws Exception {
        List<Double> lengths = this.getLengthsOfEdges();
        if ((lengths.get(1) * lengths.get(2) == 0.0) || (lengths.get(0) * lengths.get(2) == 0.0)
                || (lengths.get(0) * lengths.get(1) == 0.0)) {
            throw new ArithmeticException("/0 when counting angles!");
        }
        double angle1 = acos((pow(lengths.get(1), 2) + pow(lengths.get(2), 2)
                - pow(lengths.get(0), 2))
                / (2 * lengths.get(1) * lengths.get(2)));
        double angle2 = acos((pow(lengths.get(0), 2) + pow(lengths.get(2), 2)
                - pow(lengths.get(1), 2))
                / (2 * lengths.get(0) * lengths.get(2)));
        double angle3 = acos((pow(lengths.get(0), 2) + pow(lengths.get(1), 2)
                - pow(lengths.get(2), 2))
                / (2 * lengths.get(0) * lengths.get(1)));
        return Arrays.asList(angle1, angle2, angle3);
    }
}
