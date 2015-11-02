package ru.unn.agile.Triangle.Model;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static java.lang.Math.acos;
import static java.lang.Math.pow;
import static java.lang.Math.sqrt;
import static org.junit.Assert.assertEquals;

public class TriangleCorrectnessTests {
    private static final double DELTA = 0.00001;
    private static final double THEHALF = 0.5;
    private Triangle triangle = new Triangle(Arrays.asList(1.9, -2.3, 4.56),
            Arrays.asList(0.0, 3.5, 5.12), Arrays.asList(4.0, 5.0, 10.2), 3);
    private double length1 = sqrt(pow(1.9 - 0.0, 2) + pow(-2.3 - 3.5, 2) + pow(4.56 - 5.12, 2));
    private double length2 = sqrt(pow(4.0 - 0.0, 2) + pow(5.0 - 3.5, 2) + pow(10.2 - 5.12, 2));
    private double length3 = sqrt(pow(1.9 - 4.0, 2) + pow(-2.3 - 5.0, 2) + pow(4.56 - 10.2, 2));

    @Test
    public void canFindCorrectPerimeter3d() {
        double truePerimeter = length1 + length2 + length3;
        double resultPerimeter = triangle.getPerimeter3d();
        assertEquals(truePerimeter, resultPerimeter, DELTA);
    }

    @Test
    public void canFindCorrectLength() {
        List<Double> coordinatesOfPoint1 = triangle.getValueOfCoordinatesOfPoint1();
        List<Double> coordinatesOfPoint2 = triangle.getValueOfCoordinatesOfPoint2();
        double trueLength = sqrt(pow(1.9 - 0.0, 2) + pow(-2.3 - 3.5, 2) + pow(4.56 - 5.12, 2));
        double resultLength = triangle.getLength(coordinatesOfPoint1, coordinatesOfPoint2);
        assertEquals(trueLength, resultLength, DELTA);
    }

    @Test
    public void canFindCorrectSquare3d() {
        double halfPerimeter = triangle.getPerimeter3d() / 2;
        double trueSquare = sqrt(halfPerimeter * (halfPerimeter - length1)
                * (halfPerimeter - length2)
                * (halfPerimeter - length3));
        double resultSquare = triangle.getSquare3d();
        assertEquals(trueSquare, resultSquare, DELTA);
    }

    @Test
    public void canFindCorrectMedian3d() {
        double trueMedian = THEHALF * sqrt(2 * pow(length2, 2)
                + 2 * pow(length3, 2) - pow(length1, 2));
        List<Double> resultMedians = triangle.getMedians3d();
        double resultMedian = resultMedians.get(0);
        assertEquals(trueMedian, resultMedian, DELTA);
    }

    @Test
    public void canFindCorrectAltitude3d() {
        double perimeter = length1 + length2 + length3;
        double trueAltitude = 2 * perimeter / length1;
        List<Double> resultAltitudes = triangle.getAltitudes3d();
        double resultAltitude = resultAltitudes.get(0);
        assertEquals(trueAltitude, resultAltitude, DELTA);
    }

    @Test
    public void canFindCorrectBisectrix3d() {
        double perimeter = length1 + length2 + length3;
        double trueBisectrix = sqrt(length1 * length2 * perimeter * (length1 + length2 - length3))
                / (length1 + length2);
        List<Double> resultBisectrices = triangle.getBisectrices3d();
        double resultBisectrix = resultBisectrices.get(2);
        assertEquals(trueBisectrix, resultBisectrix, DELTA);
    }

    @Test
    public void canFindCorrectAngle3d() {
        double trueAngle = acos((pow(length1, 2) + pow(length2, 2) - pow(length3, 2))
                / (2 * length1 * length2));
        List<Double> resultAngles = triangle.getAngles3d();
        double resultAngle = resultAngles.get(2);
        assertEquals(trueAngle, resultAngle, DELTA);
    }

    @Test
    public void canComputeLargeNumberCorrectly() {
        Triangle triangleLarge = new Triangle(Arrays.asList(Double.MAX_VALUE, -2.3, 4.56),
                Arrays.asList(0.0, 3.5, 5.12), Arrays.asList(Double.MAX_VALUE, 5.0, 10.2), 3);
        double length1 = sqrt(pow(Double.MAX_VALUE - 0.0, 2) + pow(-2.3 - 3.5, 2)
                + pow(4.56 - 5.12, 2));
        double length2 = sqrt(pow(Double.MAX_VALUE - 0.0, 2) + pow(5.0 - 3.5, 2)
                + pow(10.2 - 5.12, 2));
        double length3 = sqrt(pow(Double.MAX_VALUE - Double.MAX_VALUE, 2) + pow(-2.3 - 5.0, 2)
                + pow(4.56 - 10.2, 2));
        double perimeter = length1 + length2 + length3;
        double trueBisectrix = sqrt(length1 * length2 * perimeter * (length1 + length2 - length3))
                / (length1 + length2);
        List<Double> resultBisectrices = triangleLarge.getBisectrices3d();
        double resultBisectrix = resultBisectrices.get(2);
        assertEquals(trueBisectrix, resultBisectrix, DELTA);
    }
}
