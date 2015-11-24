package ru.unn.agile.VolumesComputer.ViewModel;

public enum FigureName {
    CUBOID("Cuboid", 3),
    SPHEROID("Spheroid", 2),
    RIGHT_CYLINDER("RightCylinder", 3),
    RIGHT_CIRCULAR_CONE("RightCircularCone", 2);

    private final String m_name;
    private final int m_parametersCount;

    FigureName(final String name, final int parametersCount) {
        m_name = name;
        m_parametersCount = parametersCount;
    }
    @Override
    public String toString() {
        return m_name;
    }
    public int getParametersCount() {
        return m_parametersCount;
    }
}
