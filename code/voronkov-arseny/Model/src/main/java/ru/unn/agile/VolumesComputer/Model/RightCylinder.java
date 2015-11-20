package ru.unn.agile.VolumesComputer.Model;

public class RightCylinder implements IRightCylindrical {
    private double m_semimajorAxis;
    private double m_semiminorAxis;
    private double m_height;

    public RightCylinder(final double semimajorAxis,
                         final double semiminorAxis,
                         final double height)
            throws NegativeParametersException {
        if (semimajorAxis < 0.0 || semiminorAxis < 0.0 || height < 0.0) {
            throw new NegativeParametersException();
        }
        m_semimajorAxis = semimajorAxis;
        m_semiminorAxis = semiminorAxis;
        m_height = height;
    }
    @Override
    public double getSemimajorAxis() {
        return m_semimajorAxis;
    }
    @Override
    public double getSemiminorAxis() {
        return m_semiminorAxis;
    }
    @Override
    public double getHeight() {
        return m_height;
    }
}
