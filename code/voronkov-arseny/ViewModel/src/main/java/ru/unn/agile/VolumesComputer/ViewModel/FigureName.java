package ru.unn.agile.VolumesComputer.ViewModel;

public enum FigureName {
    CUBOID("Cuboid", new String[] {
            "Width", "Height", "Length"
    }),
    SPHEROID("Spheroid", new String[] {
            "Semimajor axis", "Semiminor axis"
    }),
    RIGHT_CYLINDER("RightCylinder", new String[] {
            "Semimajor axis", "Semiminor axis", "Height"
    }),
    RIGHT_CIRCULAR_CONE("RightCircularCone", new String[] {
            "Radius", "Height"
    });

    private final String m_name;
    private final int m_parametersCount;
    private final String[] m_parametersNames;

    FigureName(final String name,
               final String[] parametersNames) {
        m_name = name;
        m_parametersCount = parametersNames.length;
        m_parametersNames = parametersNames.clone();
    }
    @Override
    public String toString() {
        return m_name;
    }
    public int getParametersCount() {
        return m_parametersCount;
    }
    public String[] getParametersNames() {
        return m_parametersNames;
    }
}
