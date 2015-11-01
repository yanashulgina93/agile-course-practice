package ru.unn.agile.IntersectionFinder.core;

public class IntersectionFinder {
    private Line line;
    private Plane plane;
    private static final double EPS = 0.00000001;
    public static enum TypeOfIntersection { NoIntersection, OneIntersection, LineOnThePlane };

    public IntersectionFinder(final Line line, final Plane plane) {
        this.line = line;
        this.plane = plane;
    }

    public Line getLine() {
        return line;
    }

    public void setLine(final Line lineSource) {
        line = lineSource;
    }

    public Plane getPlane() {
        return plane;
    }

    public void setPlane(final Plane planeSource) {
        plane = planeSource;
    }

    public TypeOfIntersection getTypeOfIntersection() {
        if (Math.abs(line.getVector().dot(plane.getNormal())) < EPS) {
            if (plane.isPointOnPlane(line.getPoint())) {
                return TypeOfIntersection.LineOnThePlane;
            }
            return TypeOfIntersection.NoIntersection;
        }
        return TypeOfIntersection.OneIntersection;
    }

    public Vector3D getIntersectionPoint() {
        double dotNormalAndPointPlane = plane.getNormal().dot(plane.getPoint());
        double dotNormalAndPointLine = plane.getNormal().dot(line.getPoint());
        double dotNormalAndVectorLine = plane.getNormal().dot(line.getVector());
        if (Math.abs(dotNormalAndVectorLine) < EPS) {
            throw new IllegalArgumentException("No intersection or plane contains line");
        }
        double alpha = (dotNormalAndPointPlane - dotNormalAndPointLine) / dotNormalAndVectorLine;
        return line.getPoint().add(line.getVector().mul(alpha));
    }
}
