package ru.unn.agile.IntegrationMethods.core;

import org.junit.Test;
import static org.junit.Assert.*;

public class IntegratorTest {
    private final double delta = 0.001;

    @Test
    public void canCreateIntegrator() {
        Integrator integrator = new Integrator(1.0, 2.0, "x");
        assertNotNull(integrator);
    }
    @Test
    public void canSetLowerLimit() {
        Integrator integrator = new Integrator(1.0, 2.0, "x");
        assertEquals(1.0, integrator.getLowerLimit(), delta);
    }
    @Test
    public void canSetUpperLimit() {
        Integrator integrator = new Integrator(1.0, 2.0, "x");
        assertEquals(2.0, integrator.getUpperLimit(), delta);
    }
    @Test
    public void canSetStep() {
        Integrator integrator = new Integrator(1.0, 2.0, "x");
        double correctValue = 0.0001;
        assertEquals(correctValue, integrator.getStep(), delta);
    }
    @Test
    public void canUseLeftRectangles() {
        Integrator integrator = new Integrator(1.0, 2.0, "x");
        double integral = integrator.leftRectangles();
        double correctValue = 1.5;
        assertEquals(correctValue, integral, delta);
    }
    @Test
    public void canUseRightRectangles() {
        Integrator integrator = new Integrator(1.0, 2.0, "x");
        double integral = integrator.rightRectangles();
        double correctValue = 1.5;
        assertEquals(correctValue, integral, delta);
    }
    @Test
    public void canUseMidpointRectangles() {
        Integrator integrator = new Integrator(1.0, 2.0, "x");
        double integral = integrator.midpointRectangles();
        double correctValue = 1.5;
        assertEquals(correctValue, integral, delta);
    }
    @Test
    public void canUseTrapezes() {
        Integrator integrator = new Integrator(1.0, 2.0, "x");
        double integral = integrator.trapezes();
        double correctValue = 1.5;
        assertEquals(correctValue, integral, delta);
    }
    @Test
    public void canUseSimpson() {
        Integrator integrator = new Integrator(1.0, 2.0, "x");
        double integral = integrator.simpson();
        double correctValue = 1.5;
        assertEquals(correctValue, integral, delta);
    }
}

