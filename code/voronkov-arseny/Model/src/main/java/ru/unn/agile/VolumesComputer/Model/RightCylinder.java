package ru.unn.agile.VolumesComputer.Model;

public class RightCylinder implements IRightCylindrical {
    private double mSemimajorAxis;
    private double mSemiminorAxis;
    private double mHeight;

    public RightCylinder(final double semimajorAxis,
                         final double semiminorAxis,
                         final double height) {
        if (semimajorAxis < 0.0 || semiminorAxis < 0.0 || height < 0.0) {
            throw new NegativeParametersException();
        }
        mSemimajorAxis = semimajorAxis;
        mSemiminorAxis = semiminorAxis;
        mHeight = height;
    }
    @Override
    public double getSemimajorAxis() {
        return mSemimajorAxis;
    }
    @Override
    public double getSemiminorAxis() {
        return mSemiminorAxis;
    }
    @Override
    public double getHeight() {
        return mHeight;
    }
}
