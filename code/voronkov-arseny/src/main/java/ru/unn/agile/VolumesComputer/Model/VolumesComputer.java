package ru.unn.agile.VolumesComputer.Model;

public final class VolumesComputer {
    private static final String NEGATIVE_PARAMS_ERROR =
            "Parameters may not be negative.";

    public static double cuboid(final double width,
                                final double height,
                                final double length) {
        if (width >= 0.0 && height >= 0.0 && length >= 0.0) {
            return width * height * length;
        } else {
            throw new IllegalArgumentException(NEGATIVE_PARAMS_ERROR);
        }
    }
    public static double spheroid(final double a, final double b) {
        if (a >= 0.0 && b >= 0.0) {
            final double four = 4.0;
            final double three = 3.0;
            return four / three * Math.PI * a * b * b;
        } else {
            throw new IllegalArgumentException(NEGATIVE_PARAMS_ERROR);
        }
    }
    public static double rightCylinder(final double a,
                                       final double b,
                                       final double h) {
        if (a >= 0.0 && b >= 0.0 && h >= 0.0) {
            return Math.PI * a * b * h;
        } else {
            throw new IllegalArgumentException(NEGATIVE_PARAMS_ERROR);
        }
    }
    public static double rightCircularCone(final double r, final double h) {
        if (r >= 0.0 && h >= 0.0) {
            final double three = 3.0;
            return Math.PI / three * r * r * h;
        } else {
            throw new IllegalArgumentException(NEGATIVE_PARAMS_ERROR);
        }
    }
    private VolumesComputer() {
    }
}
