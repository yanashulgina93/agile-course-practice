package ru.unn.agile.Triangle.Model;

import java.util.Arrays;
import java.util.List;

import static java.lang.Math.acos;
import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public class Triangle {
    private List<Double> coordinates1;
    private List<Double> coordinates2;
    private List<Double> coordinates3;

    public Triangle(List<Double> inputCoordinates1,List<Double> inputCoordinates2, List<Double> inputCoordinates3) {
        this.coordinates1 = inputCoordinates1;
        this.coordinates2 = inputCoordinates2;
        this.coordinates3 = inputCoordinates3;
    }

    public List<Double> getValueOfCoordinates1() {
        return coordinates1;
    }

    public List<Double> getValueOfCoordinates2() {
        return coordinates2;
    }

    public List<Double> getValueOfCoordinates3() {
        return coordinates3;
    }

    public boolean hasEqualNumberOfCoordinates(List<Double> coordinates1, List<Double> coordinates2) {
        return (coordinates1.size() == coordinates2.size());
    }

    public boolean areNotEqual(List<Double> coordinates1, List<Double> coordinates2) {
        return !(coordinates1.equals(coordinates2));
    }

    public boolean isPossible() {
        boolean condition1 = hasEqualNumberOfCoordinates(this.coordinates1, this.coordinates2) &&
                            hasEqualNumberOfCoordinates(this.coordinates1, this.coordinates3) &&
                            hasEqualNumberOfCoordinates(this.coordinates2, this.coordinates3);
        boolean condition2 = areNotEqual(this.coordinates1, this.coordinates2) &&
                            areNotEqual(this.coordinates1, this.coordinates3) &&
                            areNotEqual(this.coordinates2, this.coordinates3);
        return condition1 && condition2;
    }

    public double getLength(List<Double> coordinates1, List<Double> coordinates2) {
        double length;
        if (this.hasEqualNumberOfCoordinates(coordinates1,coordinates2)
                && this.areNotEqual(coordinates1, coordinates2)) {
            double sum = 0.0;
            for (int i = 0; i < coordinates1.size(); i++) {
                sum += pow(coordinates1.get(i) - coordinates2.get(i), 2);
            }
            length = sqrt(sum);
            return length;
        }
        else {
            System.out.println("Two points have the same set of coordinates!");
            return 0.0;
        }
    }

    public List<Double> getLengths3d() {
        double length1 = this.getLength(this.coordinates1, this.coordinates2);
        double length2 = this.getLength(this.coordinates2, this.coordinates3);
        double length3 = this.getLength(this.coordinates3, this.coordinates1);
        return Arrays.asList(length1, length2, length3);
    }

    public double getPerimeter3d() {
        List<Double> lengths = this.getLengths3d();
            return lengths.get(0) + lengths.get(1) + lengths.get(2);
    }

    public double getSquare3d() {
        List<Double> lengths = this.getLengths3d();
        double halfPerimeter = this.getPerimeter3d() / 2;
        return sqrt(halfPerimeter * (halfPerimeter - lengths.get(0)) * (halfPerimeter - lengths.get(1))
                * (halfPerimeter - lengths.get(2)));
    }

    public List<Double> getMedians3d() {
        List<Double> lengths = this.getLengths3d();
        double median1 = 0.5 * sqrt(2 * pow(lengths.get(1), 2) + 2 * pow(lengths.get(2), 2) - pow(lengths.get(0),2));
        double median2 = 0.5 * sqrt(2 * pow(lengths.get(0), 2) + 2 * pow(lengths.get(2), 2) - pow(lengths.get(1),2));
        double median3 = 0.5 * sqrt(2 * pow(lengths.get(0), 2) + 2 * pow(lengths.get(1), 2) - pow(lengths.get(2),2));
        List<Double> medians = Arrays.asList(median1, median2, median3);
        return medians;
    }

    public List<Double> getAltitudes3d() {
        List<Double> lengths = this.getLengths3d();
        double altitude1 = 2 * this.getPerimeter3d() / lengths.get(0);
        double altitude2 = 2 * this.getPerimeter3d() / lengths.get(1);
        double altitude3 = 2 * this.getPerimeter3d() / lengths.get(2);
        List<Double> altitudes = Arrays.asList(altitude1, altitude2, altitude3);
        return altitudes;
    }

    public List<Double> getBisectrices3d() {
        List<Double> lengths = this.getLengths3d();
        double bisectrix1 = sqrt(lengths.get(1) * lengths.get(2) * this.getPerimeter3d() *
                            (lengths.get(1) + lengths.get(2) - lengths.get(0)))
                            / (lengths.get(1) + lengths.get(2));
        double bisectrix2 = sqrt(lengths.get(0) * lengths.get(2) * this.getPerimeter3d() *
                (lengths.get(0) + lengths.get(2) - lengths.get(1)))
                / (lengths.get(0) + lengths.get(2));
        double bisectrix3 = sqrt(lengths.get(0) * lengths.get(1) * this.getPerimeter3d() *
                (lengths.get(0) + lengths.get(1) - lengths.get(2)))
                / (lengths.get(0) + lengths.get(1));
        List<Double> bisectrices = Arrays.asList(bisectrix1, bisectrix2, bisectrix3);
        return bisectrices;
    }

    public List<Double> getAngles3d() {
        List<Double> lengths = this.getLengths3d();
        double angle1 = acos((pow(lengths.get(1),2) + pow(lengths.get(2),2) - pow(lengths.get(0),2))
                        / (2 * lengths.get(1) * lengths.get(2)));
        double angle2 = acos((pow(lengths.get(0),2) + pow(lengths.get(2),2) - pow(lengths.get(1),2))
                / (2 * lengths.get(0) * lengths.get(2)));
        double angle3 = acos((pow(lengths.get(0),2) + pow(lengths.get(1),2) - pow(lengths.get(2),2))
                / (2 * lengths.get(0) * lengths.get(1)));
        List<Double> angles = Arrays.asList(angle1, angle2, angle3);
        return angles;
    }
}
