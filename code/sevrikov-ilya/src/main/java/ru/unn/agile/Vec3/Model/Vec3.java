package ru.unn.agile.Vec3.Model;

import java.util.Random;

public class Vec3 {
    private double x;
    private double y;
    private double z;

    private static final int HASH_FACTOR = 31;

    public Vec3() {
        //
    }

    public Vec3(final double x,
                final double y,
                final double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vec3(final double[] array) {
        x = array[0];
        y = array[1];
        z = array[2];
    }

    public double x() {
        return x;
    }

    public double y() {
        return y;
    }

    public double z() {
        return z;
    }

    @Override
    public int hashCode() {
        final long hashX = Double.doubleToLongBits(x);
        final long hashY = Double.doubleToLongBits(y);
        final long hashZ = Double.doubleToLongBits(z);

        final Random rng = new Random();

        return rng.nextInt()
                + HASH_FACTOR * Long.hashCode(hashX)
                + HASH_FACTOR * Long.hashCode(hashY)
                + HASH_FACTOR * Long.hashCode(hashZ);
    }

    @Override
    public boolean equals(final Object obj) {
        if (obj instanceof Vec3) {
            return equals((Vec3) obj);
        }

        return false;
    }

    public boolean equals(final double x,
                          final double y,
                          final double z) {
        return Math.abs(this.x - x) < Precision.confusion()
            && Math.abs(this.y - y) < Precision.confusion()
            && Math.abs(this.z - z) < Precision.confusion();
    }

    public boolean equals(final Vec3 vec) {
        return equals(vec.x(), vec.y(), vec.z());
    }

    public double norm() {
        return Math.sqrt(dot(this));
    }

    public void normalize() {
        final double norm = norm();

        if (norm < Precision.confusion()) {
            throw new ArithmeticException("Norm of vector is small: " + norm);
        }

        x /= norm;
        y /= norm;
        z /= norm;
    }

    public double dot(final Vec3 vec) {
        return x * vec.x() + y * vec.y() + z * vec.z();
    }

    public Vec3 cross(final Vec3 vec) {
        return new Vec3(y * vec.z() - z * vec.y(),
                        z * vec.x() - x * vec.z(),
                        x * vec.y() - y * vec.x());
    }
}
