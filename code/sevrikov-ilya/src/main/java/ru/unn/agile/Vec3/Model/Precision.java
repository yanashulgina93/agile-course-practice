package ru.unn.agile.Vec3.Model;

public final class Precision  {
    private static final double THE_DEFAULT_CONFUSION = 1e-7;

    private Precision() {
        /* none */
    }

    public static double confusion() {
        return THE_DEFAULT_CONFUSION;
    }
}
