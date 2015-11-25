package ru.unn.agile.VolumesComputer.ViewModel;

import ru.unn.agile.VolumesComputer.Model.*;

public class ComputerViewModel {
    public static final String BAD_VOLUME_STRING
            = "I can't solve volume for this strange figure!";
    public static final String EMPTY_VOLUME_STRING = "";
    public static final String DISABLE_PARAMETER_STRING = "No parameter";
    private static final int THREE = 3;
    private String mParameter1str;
    private String mParameter2str;
    private String mParameter3str;
    private double mParameter1;
    private double mParameter2;
    private double mParameter3;
    private FigureName mFigureName;
    private boolean mInputCorrect;
    private boolean mParsed;
    private String mVolumeStr;

    public ComputerViewModel() {
        mParameter1str = "";
        mParameter2str = "";
        mParameter3str = "";
        mParameter1 = 0.0;
        mParameter2 = 0.0;
        mParameter3 = 0.0;
        mFigureName = FigureName.CUBOID;
        mInputCorrect = false;
        mParsed = false;
        mVolumeStr = EMPTY_VOLUME_STRING;
    }
    public void setParameter1(final String parameterString) {
        mParsed = mParameter1str.equals(parameterString);
        mParameter1str = parameterString;
    }
    public void setParameter2(final String parameterString) {
        mParsed = mParameter2str.equals(parameterString);
        mParameter2str = parameterString;
    }
    public void setParameter3(final String parameterString) {
        mParsed = mParameter3str.equals(parameterString);
        mParameter3str = parameterString;
    }
    public void setFigure(final FigureName figureName) {
        mParsed = mFigureName.equals(figureName);
        mFigureName = figureName;
    }
    public String getParameter1() {
        return mParameter1str;
    }
    public String getParameter2() {
        return mParameter2str;
    }
    public String getParameter3() {
        return mParameter3str;
    }
    public FigureName getFigure() {
        return mFigureName;
    }
    public boolean isParameter1enabled() {
        return mFigureName.getParametersCount() >= 1;
    }
    public boolean isParameter2enabled() {
        return mFigureName.getParametersCount() >= 2;
    }
    public boolean isParameter3enabled() {
        return mFigureName.getParametersCount() >= THREE;
    }
    public boolean isInputCorrect() {
        if (!mParsed) {
            parse();
        }
        return mInputCorrect;
    }
    public String getVolume() {
        return mVolumeStr;
    }
    public String getParameter1name() {
        return getParameterName(1);
    }
    public String getParameter2name() {
        return getParameterName(2);
    }
    public String getParameter3name() {
        return getParameterName(THREE);
    }
    public void parse() {
        mParsed = true;
        final int paramsCount = mFigureName.getParametersCount();
        mInputCorrect = true;
        if (paramsCount < 1) {
            return;
        }
        if (paramsCount > THREE) {
            mInputCorrect = false;
            return;
        }
        mParameter1 = parseParameter(mParameter1str);
        if (mInputCorrect && paramsCount > 1) {
            mParameter2 = parseParameter(mParameter2str);
        }
        if (mInputCorrect && paramsCount > 2) {
            mParameter3 = parseParameter(mParameter3str);
        }
    }
    public void solve() {
        if (!mParsed) {
            parse();
        }
        if (mInputCorrect) {
            try {
                if (mFigureName == FigureName.CUBOID) {
                    solveCuboid();
                } else if (mFigureName == FigureName.SPHEROID) {
                    solveSpheroid();
                } else if (mFigureName == FigureName.RIGHT_CYLINDER) {
                    solveRightCylinder();
                } else if (mFigureName == FigureName.RIGHT_CIRCULAR_CONE) {
                    solveRightCircularCone();
                } else {
                    mVolumeStr = BAD_VOLUME_STRING;
                }
            } catch (NegativeParametersException e) {
                mVolumeStr = BAD_VOLUME_STRING;
            }
        } else {
            mVolumeStr = EMPTY_VOLUME_STRING;
        }
    }

    private double parseParameter(final String parameterString) {
        double res = 0.0;
        try {
            res = Double.valueOf(parameterString);
        } catch (NumberFormatException e) {
            mInputCorrect = false;
        }
        return res;
    }
    private String getParameterName(final int parameterIndex) {
        return mFigureName.getParametersCount() >= parameterIndex
                ? mFigureName.getParametersNames()[parameterIndex - 1]
                : DISABLE_PARAMETER_STRING;
    }
    private void solveCuboid() {
        mVolumeStr = String.valueOf(VolumesComputer.solve(new Cuboid(
                mParameter1, mParameter2, mParameter3)));
    }
    private void solveSpheroid() {
        mVolumeStr = String.valueOf(VolumesComputer.solve(
                new Spheroid(mParameter1, mParameter2)));
    }
    private void solveRightCylinder() {
        mVolumeStr = String.valueOf(VolumesComputer.solve(new RightCylinder(
                mParameter1, mParameter2, mParameter3)));
    }
    private void solveRightCircularCone() {
        mVolumeStr = String.valueOf(VolumesComputer.solve(
                new RightCircularCone(mParameter1, mParameter2)));
    }
}
