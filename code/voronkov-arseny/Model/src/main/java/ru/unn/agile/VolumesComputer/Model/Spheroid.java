package ru.unn.agile.VolumesComputer.Model;

public class Spheroid implements ISpheroid {
    private double mSemimajorAxis;
    private double mSemiminorAxis;

    public Spheroid(final double semimajorAxis,
                    final double semiminorAxis)
            throws NegativeParametersException {
        if (semimajorAxis < 0.0 || semiminorAxis < 0.0) {
            throw new NegativeParametersException();
        }
        mSemimajorAxis = semimajorAxis;
        mSemiminorAxis = semiminorAxis;
    }
    @Override
    public double getSemimajorAxis() {
        return mSemimajorAxis;
    }
    @Override
    public double getSemiminorAxis() {
        return mSemiminorAxis;
    }
}
