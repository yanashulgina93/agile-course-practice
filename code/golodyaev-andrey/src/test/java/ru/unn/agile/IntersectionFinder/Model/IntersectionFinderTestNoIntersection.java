package ru.unn.agile.IntersectionFinder.core;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import java.util.Arrays;
import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class IntersectionFinderTestNoIntersection {
    private Vector3D pointLine;
    private Vector3D vectorLine;
    private Vector3D pointPlane;
    private Vector3D normalPlane;
    private Line line;
    private Plane plane;
    private IntersectionFinder intersectionFinder;
    private enum TypeOfNoIntersection { PlaneXOYAndLineXYWithOffset,
                                        PlaneXOZAndLineZWithOffset,
                                        PlaneAndParallelLine };
    private TypeOfNoIntersection type;

    @Parameterized.Parameters
    public static Iterable<Object[]> typeOfNoIntersection() {
        return Arrays.asList(new Object[][] {
                        {TypeOfNoIntersection.PlaneXOYAndLineXYWithOffset } ,
                        {TypeOfNoIntersection.PlaneXOZAndLineZWithOffset } ,
                        {TypeOfNoIntersection.PlaneAndParallelLine }
                }
        );
    }

    public void setUpPlaneXOYAndLineXYWithOffset() {
        pointLine = new Vector3D(0.0, 0.0, 3.0);
        vectorLine = new Vector3D(1.0, 1.0, 0.0);
        pointPlane = new Vector3D(0.0, 0.0, 0.0);
        normalPlane = new Vector3D(0.0, 0.0, 1.0);
    }

    public void setUpPlaneXOZAndLineZWithOffset() {
        pointLine = new Vector3D(0.0, 2.0, 0.0);
        vectorLine = new Vector3D(0.0, 0.0, 1.0);
        pointPlane = new Vector3D(0.0, 0.0, 0.0);
        normalPlane = new Vector3D(0.0, 1.0, 0.0);
    }

    public void setUpPlaneAndParallelLine() {
        pointLine = new Vector3D(1.0, 2.0, 3.0);
        vectorLine = new Vector3D(1.0, 2.0, 3.0);
        pointPlane = new Vector3D(2.0, 2.0, 3.0);
        normalPlane = new Vector3D(-13.0, 2.0, 3.0);
    }

    @Before
    public void setUp() {
        switch (type) {
            case PlaneAndParallelLine:
                setUpPlaneAndParallelLine();
                break;
            case PlaneXOYAndLineXYWithOffset:
                setUpPlaneXOYAndLineXYWithOffset();
                break;
            case PlaneXOZAndLineZWithOffset:
                setUpPlaneXOZAndLineZWithOffset();
                break;
            default:
                break;
        }
        line = new Line(pointLine, vectorLine);
        plane = new Plane(pointPlane, normalPlane);
        intersectionFinder = new IntersectionFinder(line, plane);
    }

    public IntersectionFinderTestNoIntersection(final TypeOfNoIntersection type) {
        this.type = type;
    }

    @Test
    public void canFindThatNoIntersection() {
        IntersectionFinder.TypeOfIntersection t = intersectionFinder.getTypeOfIntersection();

        assertEquals(t, IntersectionFinder.TypeOfIntersection.NoIntersection);
    }

    @Test(expected = Exception.class)
    public void canCatchExceptionIfNoIntersection() {
        intersectionFinder.getIntersectionPoint();
    }
}
