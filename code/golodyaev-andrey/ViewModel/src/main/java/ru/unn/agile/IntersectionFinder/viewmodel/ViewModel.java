package ru.unn.agile.IntersectionFinder.viewmodel;

import ru.unn.agile.IntersectionFinder.model.*;

public class ViewModel {
    private String pointLineStr;
    private String vectorLineStr;
    private String pointPlaneStr;
    private String normalPlaneStr;
    private String result;
    private String error;
    private boolean isFinderButtonEnabled;

    public ViewModel() {
        pointLineStr = "";
        vectorLineStr = "";
        pointPlaneStr = "";
        normalPlaneStr = "";
        result = "";
        error = ErrorStatus.EMPTY_FIELDS;
        isFinderButtonEnabled = false;
    }

    public boolean isFinderButtonEnabled() {
        return isFinderButtonEnabled;
    }

    private boolean isInputEmpty() {
        return pointLineStr.isEmpty() || vectorLineStr.isEmpty()
                || pointPlaneStr.isEmpty() || normalPlaneStr.isEmpty();
    }

    private boolean isInputContainsIllegalData() {
        try {
            Vector3D tmpVector = new Vector3D(0.0, 0.0, 0.0);
            if (!pointLineStr.isEmpty()) {
                tmpVector.parse(pointLineStr);
            }
            if (!vectorLineStr.isEmpty()) {
                tmpVector.parse(vectorLineStr);
            }
            if (!pointPlaneStr.isEmpty()) {
                tmpVector.parse(pointPlaneStr);
            }
            if (!normalPlaneStr.isEmpty()) {
                tmpVector.parse(normalPlaneStr);
            }
        } catch (Exception e) {
            return true;
        }
        return false;
    }

    public boolean parseInput() {
        if (isInputContainsIllegalData()) {
            isFinderButtonEnabled = false;
            error = ErrorStatus.INCORRECT_DATA;
            return false;
        }
        if (isInputEmpty()) {
            isFinderButtonEnabled = false;
            error = ErrorStatus.EMPTY_FIELDS;
            return false;
        }
        isFinderButtonEnabled = true;
        error = ErrorStatus.NO_ERROR;
        return true;
    }

    public void findIntersection() {
        Vector3D pointLine = new Vector3D(pointLineStr);
        Vector3D vectorLine = new Vector3D(vectorLineStr);
        Vector3D pointPlane = new Vector3D(pointPlaneStr);
        Vector3D normalPlane = new Vector3D(normalPlaneStr);
        Line line = new Line(pointLine, vectorLine);
        Plane plane = new Plane(pointPlane, normalPlane);
        IntersectionFinder intersectionFinder = new IntersectionFinder(line, plane);

        IntersectionFinder.TypeOfIntersection type = intersectionFinder.getTypeOfIntersection();
        switch (type) {
            case NoIntersection:
                error = ErrorStatus.NO_INTERSECTION;
                result = "";
                break;
            case LineOnThePlane:
                error = ErrorStatus.PLANE_CONTAINS_LINE;
                result = "";
                break;
            case OneIntersection:
                result = intersectionFinder.getIntersectionPoint().toString();
                break;
            default:
                break;
        }
    }

    public String getError() {
        return error;
    }

    public String getResult() {
        return result;
    }

    public String getPointLine() {
        return pointLineStr;
    }

    public void setPointLine(final String pointLineStr) {
        if (pointLineStr.equals(this.pointLineStr)) {
            return;
        }

        this.pointLineStr = pointLineStr;
    }

    public String getVectorLine() {
        return vectorLineStr;
    }

    public void setVectorLine(final String vectorLineStr) {
        if (vectorLineStr.equals(this.vectorLineStr)) {
            return;
        }

        this.vectorLineStr = vectorLineStr;
    }

    public String getPointPlane() {
        return pointPlaneStr;
    }

    public void setPointPlane(final String pointPlaneStr) {
        if (pointPlaneStr.equals(this.pointPlaneStr)) {
            return;
        }

        this.pointPlaneStr = pointPlaneStr;
    }

    public String getNormalPlane() {
        return normalPlaneStr;
    }

    public void setNormalPlane(final String normalPlane) {
        if (normalPlane.equals(this.normalPlaneStr)) {
            return;
        }

        this.normalPlaneStr = normalPlane;
    }

    public static class ErrorStatus {
        public static final String NO_ERROR = "";
        public static final String NO_INTERSECTION = "No Intersection!";
        public static final String PLANE_CONTAINS_LINE = "Plane contains line!";
        public static final String EMPTY_FIELDS = "Some input fields are empty!";
        public static final String INCORRECT_DATA = "Some fields contain incorrect data!";
    }
}
