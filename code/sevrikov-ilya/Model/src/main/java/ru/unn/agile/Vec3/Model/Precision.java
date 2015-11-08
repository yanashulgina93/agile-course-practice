package ru.unn.agile.Vec3.Model;

public final class Precision  {
    private static final double DEFAULT_CONFUSION = 1e-7;

    private Precision() {
    }

    public static double confusion() {
        return DEFAULT_CONFUSION;
    }
}
