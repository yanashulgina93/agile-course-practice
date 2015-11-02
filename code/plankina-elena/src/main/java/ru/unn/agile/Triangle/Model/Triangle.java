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
    private int dimension;
    private static final double THEHALF = 0.5;

    public Triangle(final List<Double> inputCoordinatesOfPoint1,
                    final List<Double> inputCoordinatesOfPoint2,
                    final List<Double> inputCoordinatesOfPoint3,
                    final int inputDimension) {
        this.coordinatesOfPoint1 = inputCoordinatesOfPoint1;
        this.coordinatesOfPoint2 = inputCoordinatesOfPoint2;
        this.coordinatesOfPoint3 = inputCoordinatesOfPoint3;
        this.dimension = inputDimension;
    }

    public List<Double> getValueOfCoordinatesOfPoint1() {
        return coordinatesOfPoint1;
    }

    public List<Double> getValueOfCoordinatesOfPoint2() {
        return coordinatesOfPoint2;
    }

    public List<Double> getValueOfCoordinatesOfPoint3() {
        return coordinatesOfPoint3;
    }

    public int getDimension() { return dimension; }

    public boolean hasEqualDimensions(final List<Double> coordinatesOfPoint1,
                                               final List<Double> coordinatesOfPoint2) {
        return coordinatesOfPoint1.size() == this.getDimension()
                && coordinatesOfPoint2.size() == this.getDimension();
    }

    public boolean areNotEqual(final List<Double> coordinatesOfPoint1,
                               final List<Double> coordinatesOfPoint2) {
        return !(coordinatesOfPoint1.equals(coordinatesOfPoint2));
    }

    public boolean isPossibleToBuildTriangle() {
        boolean condition1 = hasEqualDimensions(this.coordinatesOfPoint1,
                this.coordinatesOfPoint2)
                && hasEqualDimensions(this.coordinatesOfPoint1, this.coordinatesOfPoint3)
                && hasEqualDimensions(this.coordinatesOfPoint2, this.coordinatesOfPoint3);
        boolean condition2 = areNotEqual(this.coordinatesOfPoint1, this.coordinatesOfPoint2)
                && areNotEqual(this.coordinatesOfPoint1, this.coordinatesOfPoint3)
                && areNotEqual(this.coordinatesOfPoint2, this.coordinatesOfPoint3);
        return condition1 && condition2;
    }

    public double getLength(final List<Double> coordinatesOfPoint1,
                            final List<Double> coordinatesOfPoint2) {
        double length;
        if (this.hasEqualDimensions(coordinatesOfPoint1, coordinatesOfPoint2)
                && this.areNotEqual(coordinatesOfPoint1, coordinatesOfPoint2)) {
            double sum = 0.0;
            for (int i = 0; i < coordinatesOfPoint1.size(); i++) {
                sum += pow(coordinatesOfPoint1.get(i) - coordinatesOfPoint2.get(i), 2);
            }
            length = sqrt(sum);
            return length;
        } else {
            System.out.println("Can't build a triangle!");
            return 0.0;
        }
    }

    public List<Double> getLengthsOfEdges() {
        double length1 = this.getLength(this.coordinatesOfPoint1, this.coordinatesOfPoint2);
        double length2 = this.getLength(this.coordinatesOfPoint2, this.coordinatesOfPoint3);
        double length3 = this.getLength(this.coordinatesOfPoint3, this.coordinatesOfPoint1);
        return Arrays.asList(length1, length2, length3);
    }

    public double getPerimeter3d() {
        List<Double> lengths = this.getLengthsOfEdges();
            return lengths.get(0) + lengths.get(1) + lengths.get(2);
    }

    public double getSquare3d() {
        List<Double> lengths = this.getLengthsOfEdges();
        double halfPerimeter = this.getPerimeter3d() / 2;
        return sqrt(halfPerimeter * (halfPerimeter - lengths.get(0))
                * (halfPerimeter - lengths.get(1)) * (halfPerimeter - lengths.get(2)));
    }

    public List<Double> getMedians3d() {
        List<Double> lengths = this.getLengthsOfEdges();
        double median1 = THEHALF * sqrt(2 * pow(lengths.get(1), 2)
                + 2 * pow(lengths.get(2), 2) - pow(lengths.get(0), 2));
        double median2 = THEHALF * sqrt(2 * pow(lengths.get(0), 2)
                + 2 * pow(lengths.get(2), 2) - pow(lengths.get(1), 2));
        double median3 = THEHALF * sqrt(2 * pow(lengths.get(0), 2)
                + 2 * pow(lengths.get(1), 2) - pow(lengths.get(2), 2));
        return Arrays.asList(median1, median2, median3);
    }

    public List<Double> getAltitudes3d() {
        List<Double> lengths = this.getLengthsOfEdges();
        double altitude1 = 2 * this.getPerimeter3d() / lengths.get(0);
        double altitude2 = 2 * this.getPerimeter3d() / lengths.get(1);
        double altitude3 = 2 * this.getPerimeter3d() / lengths.get(2);
        return Arrays.asList(altitude1, altitude2, altitude3);
    }

    public List<Double> getBisectrices3d() {
        List<Double> lengths = this.getLengthsOfEdges();
        double bisectrix1 = sqrt(lengths.get(1) * lengths.get(2) * this.getPerimeter3d()
                * (lengths.get(1) + lengths.get(2) - lengths.get(0)))
                / (lengths.get(1) + lengths.get(2));
        double bisectrix2 = sqrt(lengths.get(0) * lengths.get(2) * this.getPerimeter3d()
                * (lengths.get(0) + lengths.get(2) - lengths.get(1)))
                / (lengths.get(0) + lengths.get(2));
        double bisectrix3 = sqrt(lengths.get(0) * lengths.get(1) * this.getPerimeter3d()
                * (lengths.get(0) + lengths.get(1) - lengths.get(2)))
                / (lengths.get(0) + lengths.get(1));
        return Arrays.asList(bisectrix1, bisectrix2, bisectrix3);
    }

    public List<Double> getAngles3d() {
        List<Double> lengths = this.getLengthsOfEdges();
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
