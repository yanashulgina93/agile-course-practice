package ru.unn.agile.IntersectionFinder.core;

import org.junit.Test;
import static org.junit.Assert.*;

public class PlaneTest {
    private final double delta = 0.001;
    private Vector3D point;
    private Vector3D normal;
    private Plane plane;

    private void setUp() {
        point = new Vector3D(1.0, 2.0, 3.0);
        normal = new Vector3D(1.0, 2.0, 3.0);
        plane = new Plane(point, normal);
    }

    @Test
    public void canCreatePlane() {
        setUp();

        assertNotNull(plane);
    }

    @Test
    public void canCheckIfPointOnPlane() {
        setUp();
        Vector3D pointToCheck = new Vector3D(7.0, 2.0, 1.0);

        boolean isPointOnPlane = plane.isPointOnPlane(pointToCheck);

        assertTrue(isPointOnPlane);
    }

    @Test
    public void canCheckIfPointNotOnPlane() {
        setUp();
        Vector3D pointToCheck = new Vector3D(8.0, 2.0, 1.0);

        boolean isPointOnPlane = plane.isPointOnPlane(pointToCheck);

        assertTrue(!isPointOnPlane);
    }
}
