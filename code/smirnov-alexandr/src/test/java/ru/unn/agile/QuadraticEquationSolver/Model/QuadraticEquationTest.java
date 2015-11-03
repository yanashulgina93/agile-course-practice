package ru.unn.agile.QuadraticEquationSolver.Model;

import org.junit.Test;
import static org.junit.Assert.*;

public class QuadraticEquationTest {
    private final float delta = 0.0001f;

    @Test
    public void canGetNullFromEquationWithFirstZeroCoefficient() {
        float [] answer = getSolutionOfQuadraticEquation(0, 2, 3);
        assertNull(answer);
    }

    @Test
    public void canGetNullFromEquationWithAllZeroCoefficients() {
        float [] answer = getSolutionOfQuadraticEquation(0, 0, 0);
        assertNull(answer);
    }

    @Test
    public void canGetOneSimpleRootEqualZero() {
        float [] answer = getSolutionOfQuadraticEquation(1, 0, 0);
        assertArrayEquals(answer, new float[]{0.0f}, delta);
    }

    @Test
    public void canGetTwoNonZeroRootsFromEquationWithSecondCoefficientEqualZero() {
        float [] answer = getSolutionOfQuadraticEquation(1, 0, -4);
        assertArrayEquals(answer, new float[]{-2.0f, 2.0f}, delta);
    }

    @Test
    public void canGetEmptyAnswerFromEquationWithNegativeDiscriminant() {
        float [] answer = getSolutionOfQuadraticEquation(1, 2, 3);
        assertArrayEquals(answer, new float[]{}, delta);
    }

    @Test
    public void canGetOneRootOfEquationWithZeroDiscriminant() {
        float [] answer = getSolutionOfQuadraticEquation(1, -2, 1);
        assertArrayEquals(answer, new float[]{1.0f}, delta);
    }

    @Test
    public void canGetTwoSimpleRootsOfEquationWithNonZeroDiscriminant() {
        float [] answer = getSolutionOfQuadraticEquation(1, -5, 6);
        assertArrayEquals(answer, new float[]{2.0f, 3.0f}, delta);
    }

    @Test
    public void canGetTwoRootsOfEquationWithNonZeroDiscriminant() {
        float[] answer = getSolutionOfQuadraticEquation(2, 4, -3);
        assertArrayEquals(answer, new float[]{-2.5811f, 0.5811f}, delta);
    }

    @Test
    public void canGetTwoRootsOfEquationWithNonZeroDiscriminantAndSmallCoefficients() {
        float [] answer = getSolutionOfQuadraticEquation(0.00000234f, 0.0000954f, 0.00000672f);
        assertArrayEquals(answer, new float[]{-40.69866f, -0.07056f}, delta);
    }

    @Test
    public void canGetTwoRootsOfEquationWithNonZeroDiscriminantAndLargeCoefficients() {
        float [] answer = getSolutionOfQuadraticEquation(12340552.47f, 98453552.07f, 45240552.29f);
        assertArrayEquals(answer, new float[]{-7.48849f, -0.48955f}, delta);
    }

    private float[] getSolutionOfQuadraticEquation(final float a, final float b, final float c) {
        return QuadraticEquationSolver.solve(a, b, c);
    }
}
