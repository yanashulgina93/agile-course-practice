package ru.unn.agile.VolumesComputer.ViewModel;

import org.junit.Test;

import static org.junit.Assert.*;

public class FigureNameTest {
    @Test
    public void cuboidName() {
        assertEquals(FigureName.CUBOID.toString(), "Cuboid");
    }
    @Test
    public void spheroidName() {
        assertEquals(FigureName.SPHEROID.toString(), "Spheroid");
    }
    @Test
    public void rightCylinderName() {
        assertEquals(FigureName.RIGHT_CYLINDER.toString(), "Right cylinder");
    }
    @Test
    public void rightCircularConeName() {
        assertEquals(FigureName.RIGHT_CIRCULAR_CONE.toString(),
                "Right circular cone");
    }

    @Test
    public void cuboidParametersCount() {
        assertEquals(FigureName.CUBOID.getParametersCount(), 3);
    }
    @Test
    public void spheroidParametersCount() {
        assertEquals(FigureName.SPHEROID.getParametersCount(), 2);
    }
    @Test
    public void rightCylinderParametersCount() {
        assertEquals(FigureName.RIGHT_CYLINDER.getParametersCount(), 3);
    }
    @Test
    public void rightCircularConeParametersCount() {
        assertEquals(FigureName.RIGHT_CIRCULAR_CONE.getParametersCount(), 2);
    }

    @Test
    public void cuboidParametersNames() {
        assertArrayEquals(FigureName.CUBOID.getParametersNames(),
                new String[] { "Width", "Height", "Length" });
    }
    @Test
    public void spheroidParametersNames() {
        assertArrayEquals(FigureName.SPHEROID.getParametersNames(),
                new String[] { "Semimajor axis", "Semiminor axis" });
    }
    @Test
    public void rightCylinderParametersNames() {
        assertArrayEquals(FigureName.RIGHT_CYLINDER.getParametersNames(),
                new String[] { "Semimajor axis", "Semiminor axis", "Height" });
    }
    @Test
    public void rightCircularParametersNames() {
        assertArrayEquals(FigureName.RIGHT_CIRCULAR_CONE.getParametersNames(),
                new String[] { "Radius", "Height" });
    }
}
