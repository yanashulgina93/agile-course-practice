package ru.unn.agile.Triangle.Model;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static java.lang.Math.acos;
import static java.lang.Math.pow;
import static java.lang.Math.sqrt;
import static org.junit.Assert.assertEquals;

public class TriangleCorrectnessTests {
    private static final double DELTA = 0.00001;
    private static final double THE_HALF = 0.5;
    private Triangle triangle;
    private double length1;
    private double length2;
    private double length3;

    @Before
    public void setUpTriangles() {
        triangle = new Triangle(Arrays.asList(1.9, -2.3, 4.56),
                Arrays.asList(0.0, 3.5, 5.12), Arrays.asList(4.0, 5.0, 10.2), 3);
        length1 = sqrt(pow(1.9 - 0.0, 2) + pow(-2.3 - 3.5, 2) + pow(4.56 - 5.12, 2));
        length2 = sqrt(pow(4.0 - 0.0, 2) + pow(5.0 - 3.5, 2) + pow(10.2 - 5.12, 2));
        length3 = sqrt(pow(1.9 - 4.0, 2) + pow(-2.3 - 5.0, 2) + pow(4.56 - 10.2, 2));
    }

    @Test
    public void canFindCorrectPerimeter() throws Exception {
        double truePerimeter = length1 + length2 + length3;
        double resultPerimeter = triangle.getPerimeter();
        assertEquals(truePerimeter, resultPerimeter, DELTA);
    }

    @Test
    public void canFindCorrectLength() throws Exception {
        List<Double> coordinatesOfPoint1 = triangle.getCoordinatesOfPoint1();
        List<Double> coordinatesOfPoint2 = triangle.getCoordinatesOfPoint2();
        double trueLength = sqrt(pow(1.9 - 0.0, 2) + pow(-2.3 - 3.5, 2) + pow(4.56 - 5.12, 2));
        double resultLength = triangle.getLength(coordinatesOfPoint1, coordinatesOfPoint2);
        assertEquals(trueLength, resultLength, DELTA);
    }

    @Test
    public void canFindCorrectSquare() throws Exception {
        double halfPerimeter = triangle.getPerimeter() / 2;
        double trueSquare = sqrt(halfPerimeter * (halfPerimeter - length1)
                * (halfPerimeter - length2)
                * (halfPerimeter - length3));
        double resultSquare = triangle.getSquare();
        assertEquals(trueSquare, resultSquare, DELTA);
    }

    @Test
    public void canFindCorrectMedian() throws Exception {
        double trueMedian = THE_HALF * sqrt(2 * pow(length2, 2)
                + 2 * pow(length3, 2) - pow(length1, 2));
        List<Double> resultMedians = triangle.getMedians();
        double resultMedian = resultMedians.get(0);
        assertEquals(trueMedian, resultMedian, DELTA);
    }

    @Test
    public void canFindCorrectAltitude() throws Exception {
        double perimeter = length1 + length2 + length3;
        double trueAltitude = 2 * perimeter / length1;
        List<Double> resultAltitudes = triangle.getAltitudes();
        double resultAltitude = resultAltitudes.get(0);
        assertEquals(trueAltitude, resultAltitude, DELTA);
    }

    @Test
    public void canFindCorrectBisectrix() throws Exception {
        double perimeter = length1 + length2 + length3;
        double trueBisectrix = sqrt(length1 * length2 * perimeter * (length1 + length2 - length3))
                / (length1 + length2);
        List<Double> resultBisectrices = triangle.getBisectrices();
        double resultBisectrix = resultBisectrices.get(2);
        assertEquals(trueBisectrix, resultBisectrix, DELTA);
    }

    @Test
    public void canFindCorrectAngle() throws Exception {
        double trueAngle = acos((pow(length1, 2) + pow(length2, 2) - pow(length3, 2))
                / (2 * length1 * length2));
        List<Double> resultAngles = triangle.getAngles();
        double resultAngle = resultAngles.get(2);
        assertEquals(trueAngle, resultAngle, DELTA);
    }
}
