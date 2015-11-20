package ru.unn.agile.VolumesComputer.Model;

public class RightCircularCone implements IRightCircularConical {
    private double m_radius;
    private double m_height;

    public RightCircularCone(final double radius,
                             final double height)
            throws NegativeParametersException {
        if (radius < 0.0 || height < 0.0) {
            throw new NegativeParametersException();
        }
        m_radius = radius;
        m_height = height;
    }
    @Override
    public double getRadius() {
        return m_radius;
    }
    @Override
    public double getHeight() {
        return m_height;
    }
}
