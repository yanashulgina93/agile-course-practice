package ru.unn.agile.VolumesComputer.Model;

public class Cuboid implements ICubiform {
    private double m_width;
    private double m_height;
    private double m_length;

    public Cuboid(final double width,
                  final double height,
                  final double length) throws NegativeParametersException {
        if (width < 0.0 || height < 0.0 || length < 0.0) {
            throw new NegativeParametersException();
        }
        m_width = width;
        m_height = height;
        m_length = length;
    }
    @Override
    public double getHeight() {
        return m_height;
    }
    @Override
    public double getLength() {
        return m_length;
    }
    @Override
    public double getWidth() {
        return m_width;
    }
}
