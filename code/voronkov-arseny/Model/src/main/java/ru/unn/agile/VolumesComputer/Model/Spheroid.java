package ru.unn.agile.VolumesComputer.Model;

public class Spheroid implements ISpheroid {
    private final double mSemimajorAxis;
    private final double mSemiminorAxis;

    public Spheroid(final double semimajorAxis,
                    final double semiminorAxis) {
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
