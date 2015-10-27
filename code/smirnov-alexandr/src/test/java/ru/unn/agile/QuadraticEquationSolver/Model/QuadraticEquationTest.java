package ru.unn.agile.QuadraticEquationSolver.Model;

import org.junit.Test;
import static org.junit.Assert.*;

public class QuadraticEquationTest {
    private final float delta = 0.0001f;

    @Test
    public void canGetEmptyAnswerFromEquationWithFirstZeroCoefficient() {
        String answer = getSolutionOfQuadraticEquation(0, 2, 3);
        assertEquals("", answer);
    }

    @Test
    public void canGetEmptyAnswerFromEquationWithAllZeroCoefficients() {
        String answer = getSolutionOfQuadraticEquation(0, 0, 0);
        assertEquals("", answer);
    }

    @Test
    public void canGetOneSimpleRootEqualZero() {
        String answer = getSolutionOfQuadraticEquation(1, 0, 0);
        assertEquals("0.0", answer);
    }

    @Test
    public void canGetTwoNonZeroRootsFromEquationWithSecondCoefficientEqualZero() {
        String answer = getSolutionOfQuadraticEquation(1, 0, -4);
        assertEquals("-2.0;2.0", answer);
    }

    @Test
    public void canGetEmptyAnswerFromEquationWithNegativeDiscriminant() {
        String answer = getSolutionOfQuadraticEquation(1, 2, 3);
        assertEquals("", answer);
    }

    @Test
    public void canGetOneRootOfEquationWithZeroDiscriminant() {
        String answer = getSolutionOfQuadraticEquation(1, -2, 1);
        assertEquals("1.0", answer);
    }

    @Test
    public void canGetTwoSimpleRootsOfEquationWithNonZeroDiscriminant() {
        String answer = getSolutionOfQuadraticEquation(1, -5, 6);
        assertEquals("2.0;3.0", answer);
    }

    @Test
    public void canGetTwoRootsOfEquationWithNonZeroDiscriminant() {
        String answer = getSolutionOfQuadraticEquation(2, 4, -3);
        assertArrayEquals(convertAnswerToArray(answer), new float[]{-2.5811f, 0.5811f}, delta);
    }

    @Test
    public void canGetTwoRootsOfEquationWithNonZeroDiscriminantAndSmallCoefficients() {
        String answer = getSolutionOfQuadraticEquation(0.00000234f, 0.0000954f, 0.00000672f);
        assertArrayEquals(convertAnswerToArray(answer), new float[]{-40.69866f, -0.07056f} , delta);
    }

    @Test
    public void canGetTwoRootsOfEquationWithNonZeroDiscriminantAndLargeCoefficients() {
        String answer = getSolutionOfQuadraticEquation(12340552.475f, 98453552.078f, 45240552.294f);
        assertArrayEquals(convertAnswerToArray(answer), new float[]{-7.48849f, -0.48955f}, delta);
    }

    private String getSolutionOfQuadraticEquation(final float a, final float b, final float c) {
        QuadraticEquationSolver solver = new QuadraticEquationSolver();
        return solver.solve(a, b, c);
    }

    private float [] convertAnswerToArray(final String answer) {
        String [] lines = answer.split(";");
        float [] convertedAnswer = new float[lines.length];
        for (int i = 0; i < lines.length; i++) {
            convertedAnswer[i] = Float.parseFloat(lines[i]);
        }
        return convertedAnswer;
    }
}
