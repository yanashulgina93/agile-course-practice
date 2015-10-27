package ru.unn.agile.QuadraticEquationSolver.Model;

public class QuadraticEquationSolver {
    private static final float COEFFICIENT_IN_FORMULA_DISCRIMINANT = 4.0f;
    private static final float COEFFICIENT_IN_FORMULA_ROOTS = 2.0f;

    public String solve(final float a, final float b, final float c) {
        if (isQuadraticEquation(a)) {
            boolean isSecondCoefficientEqualZero = Math.signum(b) == 0;
            boolean isThirdCoefficientEqualZero = Math.signum(c) == 0;
            if (isSecondCoefficientEqualZero && isThirdCoefficientEqualZero) {
                return getOneRoot(0);
            }
            if (isSecondCoefficientEqualZero) {
                return getSolutionWithoutUsingDiscriminant(a, c);
            }
            return getSolutionUsingDiscriminant(a, b, c);
        }
        return getDefaultSolution();
    }

    private String getSolutionUsingDiscriminant(final float a, final float b, final float c) {
        float discriminant = calcDiscriminant(a, b, c);
        if (discriminant < 0) {
            return getDefaultSolution();
        }
        if (Math.signum(discriminant) == 0) {
            return calcOneRootByFormula(a, b);
        }
        return calcTwoRootsByFormula(a, b, discriminant);
    }

    private float calcDiscriminant(final float a, final float b, final float c) {
        return b * b - COEFFICIENT_IN_FORMULA_DISCRIMINANT * a * c;
    }

    private String calcOneRootByFormula(final float a, final float b) {
        return getOneRoot((-b) / (COEFFICIENT_IN_FORMULA_ROOTS * a));
    }

    private String calcTwoRootsByFormula(final float a, final float b, final float discriminant) {
        float discriminantSqrt = (float) Math.sqrt(discriminant);
        float divider = COEFFICIENT_IN_FORMULA_ROOTS * a;
        float firstTerm = (-b) / divider;
        float secondTerm = discriminantSqrt / divider;

        float root1 = firstTerm - secondTerm;
        float root2 = firstTerm + secondTerm;
        if (root1 > root2) {
            return getTwoRoots(root2, root1);
        }
        return getTwoRoots(root1, root2);
    }

    private String getSolutionWithoutUsingDiscriminant(final float a, final float c) {
        float rightPart = (-c) / a;
        if (rightPart < 0) {
            return getDefaultSolution();
        }
        float root1 = (float) (Math.sqrt(rightPart));
        return getTwoRoots(-root1, root1);
    }

    private boolean isQuadraticEquation(final float a) {
        return Math.signum(a) != 0;
    }

    private String getDefaultSolution() {
        return "";
    }

    private String getOneRoot(final float root) {
        return Float.toString(root);
    }

    private String getTwoRoots(final float root1, final float root2) {
        return String.format("%s;%s", root1, root2);
    }
}
