package ru.unn.agile.Vec3.Model;

import java.util.Random;

public class Vec3 {
    private double myCoordX;
    private double myCoordY;
    private double myCoordZ;

    private static final int THE_HASH_FACTOR = 31;

    public Vec3() {
        //
    }

    public Vec3(final double theX,
                final double theY,
                final double theZ) {
        myCoordX = theX;
        myCoordY = theY;
        myCoordZ = theZ;
    }

    public Vec3(final double[] theArray) {
        myCoordX = theArray[0];
        myCoordY = theArray[1];
        myCoordZ = theArray[2];
    }

    public double x() {
        return myCoordX;
    }

    public double y() {
        return myCoordY;
    }

    public double z() {
        return myCoordZ;
    }

    @Override
    public int hashCode() {
        final long aHashX = Double.doubleToLongBits(myCoordX);
        final long aHashY = Double.doubleToLongBits(myCoordY);
        final long aHashZ = Double.doubleToLongBits(myCoordZ);

        final Random aRNG = new Random();

        return aRNG.nextInt()
                + THE_HASH_FACTOR * Long.hashCode(aHashX)
                + THE_HASH_FACTOR * Long.hashCode(aHashY)
                + THE_HASH_FACTOR * Long.hashCode(aHashZ);
    }

    @Override
    public boolean equals(final Object theObj) {
        if (theObj instanceof Vec3) {
            return equals((Vec3) theObj);
        }

        return false;
    }

    public boolean equals(final double theX,
                           final double theY,
                           final double theZ) {
        return Math.abs(myCoordX - theX) < Precision.confusion()
            && Math.abs(myCoordY - theY) < Precision.confusion()
            && Math.abs(myCoordZ - theZ) < Precision.confusion();
    }

    public boolean equals(final Vec3 theVec) {
        return equals(theVec.x(), theVec.y(), theVec.z());
    }

    public double norm() {
        return Math.sqrt(dot(this));
    }

    public void normalize() {
        final double aNorm = norm();

        if (aNorm < Precision.confusion()) {
            throw new ArithmeticException("Norm of vector is small: " + aNorm);
        }

        myCoordX /= aNorm;
        myCoordY /= aNorm;
        myCoordZ /= aNorm;
    }

    public double dot(final Vec3 theVec) {
        return myCoordX * theVec.x() + myCoordY * theVec.y() + myCoordZ * theVec.z();
    }

    public Vec3 cross(final Vec3 theVec) {
        return new Vec3(myCoordY * theVec.z() - myCoordZ * theVec.y(),
                        myCoordZ * theVec.x() - myCoordX * theVec.z(),
                        myCoordX * theVec.y() - myCoordY * theVec.x());
    }
}
