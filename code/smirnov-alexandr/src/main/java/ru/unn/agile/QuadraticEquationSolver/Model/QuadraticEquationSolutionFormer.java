package ru.unn.agile.QuadraticEquationSolver.Model;

import java.util.ArrayList;

public final class QuadraticEquationSolutionFormer {
    private QuadraticEquationSolutionFormer() {
    }

    public static float [] form(final ArrayList<Float> roots) {
        if (roots == null) {
            return null;
        }
        float [] ans;
        int length = roots.size();
        ans = new float[length];
        for (int i = 0; i < length; i++) {
            ans[i] = roots.get(i);
        }
        return ans;
    }
}
