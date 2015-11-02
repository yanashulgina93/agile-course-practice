package ru.unn.agile.Triangle.Model;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

public class TriangleExceptionsTests {
    private Triangle triangle = new Triangle(Arrays.asList(1.9, -2.3, 4.56),
            Arrays.asList(0.0, 3.5, 5.12), Arrays.asList(4.0, 5.0, 10.2), 3);
    @Test
    public void canCreateNewTriangle() {
        assertNotNull(triangle);
    }

    @Test
    public void numberOfCoordinatesOfFirstAndSecondPointsIsEqual() {
        List<Double> coordinates1 = triangle.getValueOfCoordinatesOfPoint1();
        List<Double> coordinates2 = triangle.getValueOfCoordinatesOfPoint2();
        assertEquals(triangle.hasEqualDimensions(coordinates1, coordinates2), true);
    }

    @Test
    public void numberOfCoordinatesOfFirstAndThirdPointsIsEqual() {
        List<Double> coordinates1 = triangle.getValueOfCoordinatesOfPoint1();
        List<Double> coordinates3 = triangle.getValueOfCoordinatesOfPoint3();
        assertEquals(triangle.hasEqualDimensions(coordinates1, coordinates3), true);
    }

    @Test
    public void areFirstAndSecondPointsNotEqual() {
        List<Double> coordinates1 = triangle.getValueOfCoordinatesOfPoint1();
        List<Double> coordinates2 = triangle.getValueOfCoordinatesOfPoint2();
        assertEquals(triangle.areNotEqual(coordinates1, coordinates2), true);
    }

    @Test
    public void areFirstAndThirdPointsNotEqual() {
        List<Double> coordinates1 = triangle.getValueOfCoordinatesOfPoint1();
        List<Double> coordinates3 = triangle.getValueOfCoordinatesOfPoint3();
        assertEquals(triangle.areNotEqual(coordinates1, coordinates3), true);
    }

    @Test
    public void isTriangle() {
        assertEquals(triangle.isPossibleToBuildTriangle(), true);
    }

    @Test
    public void canFindLength() {
        List<Double> coordinates1 = triangle.getValueOfCoordinatesOfPoint1();
        List<Double> coordinates2 = triangle.getValueOfCoordinatesOfPoint2();
        double length = triangle.getLength(coordinates1, coordinates2);
        assertNotEquals(length, 0.0);
    }

    @Test
    public void canFindLengths() {
        List<Double> lengths = triangle.getLengthsOfEdges();
        assertNotNull(lengths);
    }

    @Test
    public void canFindPerimeter3d() {
        double perimeter = triangle.getPerimeter3d();
        assertNotNull(perimeter);
    }

    @Test
    public void canFindSquare3d() {
        double square = triangle.getSquare3d();
        assertNotNull(square);
    }

    @Test
    public void canFindAltitudes3d() {
        List<Double> altitudes = triangle.getAltitudes3d();
        assertNotNull(altitudes);
    }

    @Test
    public void canFindAngles3d() {
        List<Double> angles = triangle.getAngles3d();
        assertNotNull(angles);
    }

    @Test
    public void canUseLargeNumber() {
        Triangle triangleLarge = new Triangle(Arrays.asList(Double.MAX_VALUE, -2.3, 4.56),
                Arrays.asList(0.0, 3.5, 5.12), Arrays.asList(Double.MAX_VALUE, 5.0, 10.2), 3);
        List<Double> coordinates1 = triangleLarge.getValueOfCoordinatesOfPoint1();
        List<Double> coordinates2 = triangleLarge.getValueOfCoordinatesOfPoint2();
        double length = triangleLarge.getLength(coordinates1, coordinates2);
        assertNotEquals(length, 0.0);
    }
}
