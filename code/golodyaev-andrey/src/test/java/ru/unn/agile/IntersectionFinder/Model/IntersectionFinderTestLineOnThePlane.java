package ru.unn.agile.IntersectionFinder.core;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import java.util.Arrays;
import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class IntersectionFinderTestLineOnThePlane {
    private Vector3D pointPlane;
    private Vector3D normalPlane;
    private Vector3D pointLine;
    private Vector3D vectorLine;
    private Line line;
    private Plane plane;
    private IntersectionFinder intersectionPlaneContainsLine;
    private enum TypeOfLineOnThePlane { PlaneXOYAndLineXY,
                                        PlaneXOZAndLineZ,
                                        PlaneAndLineOnThePlane };
    private TypeOfLineOnThePlane type;

    @Parameterized.Parameters
    public static Iterable<Object[]> typeOfLineOnThePlane() {
        return Arrays.asList(new Object[][] {
                        {TypeOfLineOnThePlane.PlaneXOYAndLineXY } ,
                        {TypeOfLineOnThePlane.PlaneXOZAndLineZ } ,
                        {TypeOfLineOnThePlane.PlaneAndLineOnThePlane }
                }
        );
    }

    public void setUpPlaneXOYAndLineXY() {
        pointLine = new Vector3D(0.0, 0.0, 0.0);
        vectorLine = new Vector3D(1.0, 1.0, 0.0);
        pointPlane = new Vector3D(0.0, 0.0, 0.0);
        normalPlane = new Vector3D(0.0, 0.0, 1.0);
    }

    public void setUpPlaneXOZAndLineZ() {
        pointLine = new Vector3D(0.0, 0.0, 0.0);
        vectorLine = new Vector3D(0.0, 0.0, 1.0);
        pointPlane = new Vector3D(0.0, 0.0, 0.0);
        normalPlane = new Vector3D(0.0, 1.0, 0.0);
    }

    public void setUpPlaneAndLineOnThePlane() {
        pointLine = new Vector3D(1.0, 2.0, 3.0);
        vectorLine = new Vector3D(1.0, 2.0, 3.0);
        pointPlane = new Vector3D(1.0, 2.0, 3.0);
        normalPlane = new Vector3D(-13.0, 2.0, 3.0);
    }

    @Before
    public void setUp() {
        switch (type) {
            case PlaneXOYAndLineXY:
                setUpPlaneXOYAndLineXY();
                break;
            case PlaneXOZAndLineZ:
                setUpPlaneXOZAndLineZ();
                break;
            case PlaneAndLineOnThePlane:
                setUpPlaneAndLineOnThePlane();
                break;
            default:
                break;
        }
        line = new Line(pointLine, vectorLine);
        plane = new Plane(pointPlane, normalPlane);
        intersectionPlaneContainsLine = new IntersectionFinder(line, plane);
    }

    public IntersectionFinderTestLineOnThePlane(final TypeOfLineOnThePlane type) {
        this.type = type;
    }

    @Test
    public void canFindThatLineOnThePlane() {
        IntersectionFinder.TypeOfIntersection t =
                intersectionPlaneContainsLine.getTypeOfIntersection();

        assertEquals(t, IntersectionFinder.TypeOfIntersection.LineOnThePlane);
    }

    @Test(expected = Exception.class)
    public void canCatchExceptionIfLineOnThePlane() {
        intersectionPlaneContainsLine.getIntersectionPoint();
    }
}
