package ru.unn.agile.IntersectionFinder.Model;

import org.junit.Test;
import static org.junit.Assert.*;

public class LineTest {
    private final double delta = 0.001;

    @Test
    public void canCreateLine() {
        Vector3D point = new Vector3D(1.0, 2.0, 3.0);
        Vector3D vector = new Vector3D(1.0, 2.0, 3.0);

        Line line = new Line(point, vector);

        assertNotNull(line);
    }
}
