package ru.unn.agile.VolumesComputer.Model;

public class Spheroid implements ISpheroid {
    private double m_semimajorAxis;
    private double m_semiminorAxis;

    public Spheroid(final double semimajorAxis,
                    final double semiminorAxis)
            throws NegativeParametersException {
        if (semimajorAxis < 0.0 || semiminorAxis < 0.0) {
            throw new NegativeParametersException();
        }
        m_semimajorAxis = semimajorAxis;
        m_semiminorAxis = semiminorAxis;
    }
    @Override
    public double getSemimajorAxis() {
        return m_semimajorAxis;
    }
    @Override
    public double getSemiminorAxis() {
        return m_semiminorAxis;
    }
}
