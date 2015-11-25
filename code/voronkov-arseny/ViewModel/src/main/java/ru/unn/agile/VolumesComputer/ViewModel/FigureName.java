package ru.unn.agile.VolumesComputer.ViewModel;

public enum FigureName {
    CUBOID("Cuboid", new String[] {
            "Width", "Height", "Length"
    }),
    SPHEROID("Spheroid", new String[] {
            "Semimajor axis", "Semiminor axis"
    }),
    RIGHT_CYLINDER("Right cylinder", new String[] {
            "Semimajor axis", "Semiminor axis", "Height"
    }),
    RIGHT_CIRCULAR_CONE("Right circular cone", new String[] {
            "Radius", "Height"
    });

    private final String mName;
    private final int mParametersCount;
    private final String[] mParametersNames;

    FigureName(final String name,
               final String[] parametersNames) {
        mName = name;
        mParametersCount = parametersNames.length;
        mParametersNames = parametersNames.clone();
    }
    @Override
    public String toString() {
        return mName;
    }
    public int getParametersCount() {
        return mParametersCount;
    }
    public String[] getParametersNames() {
        return mParametersNames;
    }
}
