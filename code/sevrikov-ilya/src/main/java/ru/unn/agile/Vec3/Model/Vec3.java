package ru.unn.agile.Vec3.Model;

public class Vec3 {
    private double x;
    private double y;
    private double z;

    public Vec3() {
        x = 0.0;
        y = 0.0;
        z = 0.0;
    }

    public Vec3(final double theX,
                final double theY,
                final double theZ) {
        x = theX;
        y = theY;
        z = theZ;
    }

    public Vec3(final double[] theArray) {
        x = theArray[0];
        y = theArray[1];
        z = theArray[2];
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

    public boolean isEqual(final double theX,
                           final double theY,
                           final double theZ) {
        return Math.abs(x - theX) < Precision.confusion()
            && Math.abs(y - theY) < Precision.confusion()
            && Math.abs(z - theZ) < Precision.confusion();
    }

    public boolean isEqual(final Vec3 theVec) {
        return isEqual(theVec.x(), theVec.y(), theVec.z());
    }

    public double norm() {
        return Math.sqrt(dot(this));
    }

    public void normalize() {
        final double aNorm = norm();

        if (aNorm < Precision.confusion()) {
            throw new ArithmeticException("Vector's norm is small: " + aNorm);
        }

        x /= aNorm;
        y /= aNorm;
        z /= aNorm;
    }

    public double dot(final Vec3 theVec) {
        return x * theVec.x() + y * theVec.y() + z * theVec.z();
    }

    public Vec3 cross(final Vec3 theVec) {
        return new Vec3(y * theVec.z() - z * theVec.y(),
                        z * theVec.x() - x * theVec.z(),
                        x * theVec.y() - y * theVec.x());
    }
}
