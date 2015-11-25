package ru.unn.agile.VolumesComputer.Model;

public class RightCircularCone implements IRightCircularConical {
    private double mRadius;
    private double mHeight;

    public RightCircularCone(final double radius,
                             final double height)
            throws NegativeParametersException {
        if (radius < 0.0 || height < 0.0) {
            throw new NegativeParametersException();
        }
        mRadius = radius;
        mHeight = height;
    }
    @Override
    public double getRadius() {
        return mRadius;
    }
    @Override
    public double getHeight() {
        return mHeight;
    }
}
