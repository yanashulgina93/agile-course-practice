package ru.unn.agile.Vec3.Model;

public class Vec3
{
    private double x;
    private double y;
    private double z;

    public Vec3()
    {
        x = 0.0;
        y = 0.0;
        z = 0.0;
    }

    public Vec3(final double theX,
                final double theY,
                final double theZ)
    {
        x = theX;
        y = theY;
        z = theZ;
    }

    public Vec3(final double[] theArray)
    {
        x = theArray[0];
        y = theArray[1];
        z = theArray[2];
    }

    public double X()
    {
        return x;
    }

    public double Y()
    {
        return y;
    }

    public double Z()
    {
        return z;
    }

    public boolean IsEqual(final double theX,
                           final double theY,
                           final double theZ)
    {
        return (Math.abs(x - theX) < Precision.Confusion())
            && (Math.abs(y - theY) < Precision.Confusion())
            && (Math.abs(z - theZ) < Precision.Confusion());
    }

    public boolean IsEqual(final Vec3 theVec)
    {
        return IsEqual(theVec.X(), theVec.Y(), theVec.Z());
    }

    public double Norm()
    {
        return Math.sqrt(Dot(this));
    }

    public void Normalize()
    {
        final double aNorm = Norm();

        if (aNorm < Precision.Confusion())
        {
            throw new ArithmeticException("Vector's norm is small: " + aNorm);
        }

        x /= aNorm;
        y /= aNorm;
        z /= aNorm;
    }

    public double Dot(final Vec3 theVec)
    {
        return x * theVec.X() + y * theVec.Y() + z * theVec.Z();
    }

    public Vec3 Cross(final Vec3 theVec)
    {
        return new Vec3(y * theVec.Z() - z * theVec.Y(),
                        z * theVec.X() - x * theVec.Z(),
                        x * theVec.Y() - y * theVec.X());
    }
}
