package ru.unn.agile.VolumesComputer.Model;

public class VolumesComputer {
    public static double cuboid(double width, double height, double length) {
        if(width >= 0.0 && height >= 0.0 && length >= 0.0)
            return width * height * length;
        else
            throw new IllegalArgumentException(m_negativeParamsError);
    }

    public static double spheroid(double a, double b) {
        if(a >= 0.0 && b >= 0.0)
            return 4.0 / 3.0 * Math.PI * a * b * b;
        else
            throw new IllegalArgumentException(m_negativeParamsError);
    }

    public static double rightCylinder(double a, double b, double h) {
        if(a >= 0.0 && b >= 0.0 && h >= 0.0)
            return Math.PI * a * b * h;
        else
            throw new IllegalArgumentException(m_negativeParamsError);
    }

    private static final String m_negativeParamsError =
            "Parameters can not be negative.";
}
