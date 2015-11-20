package ru.unn.agile.VolumesComputer.Model;

import static org.junit.Assert.*;

import org.junit.Test;

public class RightCylinderTest {
    @Test
    public void constructorGoodParams() {
        RightCylinder rightCylinder = new RightCylinder(5.0, 4.0, 3.0);
        assertEquals(rightCylinder.getSemimajorAxis(), 5.0, 0.0);
        assertEquals(rightCylinder.getSemiminorAxis(), 4.0, 0.0);
        assertEquals(rightCylinder.getHeight(), 3.0, 0.0);
    }
    @Test (expected = NegativeParametersException.class)
    public void constructorNegativeParams() {
        new RightCylinder(5.0, 4.0, -1.0);
    }
}
