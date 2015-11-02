package ru.unn.agile.Triangle.Model;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@RunWith(Parameterized.class)
public class TriangleParametrizedTest {

    private Triangle triangle;
    public TriangleParametrizedTest(final Triangle triangle) {
        this.triangle = triangle;
    }

    @Parameters
    public static Collection<Object[]> setOfTriangles() {
        return Arrays.asList(new Object[][]{
                new Object[]{new Triangle(Arrays.asList(1.9, -2.3, 4.56),
                        Arrays.asList(0.0, 3.5, 5.12),
                        Arrays.asList(4.0, 5.0, 10.2), 3)},
                new Object[]{new Triangle(Arrays.asList(-134.9, -211.32, -400.56),
                        Arrays.asList(2.0, 3.578293, -9955.12),
                        Arrays.asList(4.0, 5.0, 105.2), 3)},
                new Object[]{new Triangle(Arrays.asList(1.9, -24.3, 4.56),
                        Arrays.asList(0.0, 3.5, 5.12),
                        Arrays.asList(42.0, 54.0, 10.2), 3)},
                new Object[]{new Triangle(Arrays.asList(1.9, -2.3, 4.56),
                        Arrays.asList(0.0, 3.5, 5.12),
                        Arrays.asList(34.0, 35.0, 36.2), 3)},
                new Object[]{new Triangle(Arrays.asList(-14.9, -11.32, -700.56),
                        Arrays.asList(2.0, 323.8293, -55.12),
                        Arrays.asList(-4.0, -5.0, 5.2), 3)},
        });
    }

    @Test
    public void canFindPerimeterCorrectly() {
        double perimeter = triangle.getPerimeter3d();
        assert perimeter >= 0.0;
    }

    @Test
    public void canFindLengthCorrectly() {
        List<Double> lengths = triangle.getLengthsOfEdges();
        double length1 = lengths.get(0);
        assert length1 >= 0.0;
    }
}

