package ru.unn.agile.IntersectionFinder.core;

public class Vector3D {
    private double x;
    private double y;
    private double z;

    public Vector3D(final double x, final double y, final double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public int hashCode() {
        final int a = 7;
        final int b = 71;
        final int c = 713;
        long temp = Double.doubleToLongBits(x);
        int result = (int) (a + temp);
        temp = Double.doubleToLongBits(y);
        result = (int) (result * b + temp);
        temp = Double.doubleToLongBits(z);
        result = (int) (result * c + temp);
        return result;
    }

    @Override
    public boolean equals(final Object object) {
        Vector3D vector = (Vector3D) object;
        return x == vector.x && y == vector.y && z == vector.z;
    }

    public double dot(final Vector3D a) {
        return x * a.x + y * a.y + z * a.z;
    }

    public double getX() {
        return x;
    }

    public void setX(final double vectorX) {
        x = vectorX;
    }

    public double getY() {
        return y;
    }

    public void setY(final double vectorY) {
        y = vectorY;
    }

    public double getZ() {
        return z;
    }

    public void setZ(final double vectorZ) {
        z = vectorZ;
    }

    public Vector3D mul(final double alpha) {
        return new Vector3D(x * alpha, y * alpha, z * alpha);
    }

    public Vector3D add(final Vector3D vector) {
        return new Vector3D(x + vector.x, y + vector.y, z + vector.z);
    }
}
