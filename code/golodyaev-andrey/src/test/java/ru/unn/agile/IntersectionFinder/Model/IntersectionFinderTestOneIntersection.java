package ru.unn.agile.IntersectionFinder.core;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import java.util.Arrays;
import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class IntersectionFinderTestOneIntersection {
    private Vector3D pointLine;
    private Vector3D vectorLine;
    private Vector3D pointPlane;
    private Vector3D normalPlane;
    private Vector3D intersectionResult;
    private Line line;
    private Plane plane;
    private IntersectionFinder intersectionPlaneAndIntersectingLine;
    private enum TypeOfOneIntersection { PlaneXOYAndLineZ,
                                         PlaneXOZAndLineXY,
                                         PlaneAndLine };
    private TypeOfOneIntersection type;

    @Parameterized.Parameters
    public static Iterable<Object[]> typeOfNoIntersection() {
        return Arrays.asList(new Object[][]{
                        {TypeOfOneIntersection.PlaneXOYAndLineZ},
                        {TypeOfOneIntersection.PlaneXOZAndLineXY},
                        {TypeOfOneIntersection.PlaneAndLine}
                }
        );
    }

    public void setUpPlaneXOYAndLineZ() {
        pointLine = new Vector3D(0.0, 0.0, 0.0);
        vectorLine = new Vector3D(0.0, 0.0, 1.0);
        pointPlane = new Vector3D(0.0, 0.0, 0.0);
        normalPlane = new Vector3D(0.0, 0.0, 1.0);
        intersectionResult = new Vector3D(0.0, 0.0, 0.0);
    }

    public void setUpPlaneXOZAndLineXY() {
        pointLine = new Vector3D(0.0, 0.0, 0.0);
        vectorLine = new Vector3D(1.0, 1.0, 0.0);
        pointPlane = new Vector3D(0.0, 0.0, 0.0);
        normalPlane = new Vector3D(0.0, 1.0, 0.0);
        intersectionResult = new Vector3D(0.0, 0.0, 0.0);
    }

    public void setUpPlaneAndLine() {
        pointLine = new Vector3D(-1.0, 7.0, 2.0);
        vectorLine = new Vector3D(4.0, -7.0, -3.0);
        pointPlane = new Vector3D(2.0, 1.0, -4.0);
        normalPlane = new Vector3D(1.0, 4.0, 1.0);
        intersectionResult = new Vector3D(3.0, 0.0, -1.0);
    }

    @Before
    public void setUp() {
        switch (type) {
            case PlaneAndLine:
                setUpPlaneAndLine();
                break;
            case PlaneXOYAndLineZ:
                setUpPlaneXOYAndLineZ();
                break;
            case PlaneXOZAndLineXY:
                setUpPlaneXOZAndLineXY();
                break;
            default:
                break;
        }
        line = new Line(pointLine, vectorLine);
        plane = new Plane(pointPlane, normalPlane);
        intersectionPlaneAndIntersectingLine = new IntersectionFinder(line, plane);
    }

    public IntersectionFinderTestOneIntersection(final TypeOfOneIntersection type) {
        this.type = type;
    }

    @Test
    public void canFindThatOneIntersection() {
        IntersectionFinder.TypeOfIntersection t =
                intersectionPlaneAndIntersectingLine.getTypeOfIntersection();

        assertEquals(t, IntersectionFinder.TypeOfIntersection.OneIntersection);
    }

    @Test
    public void canFindIntersectionIfItExists() {
        assertEquals(intersectionPlaneAndIntersectingLine.getIntersectionPoint(),
                intersectionResult);
    }
}
