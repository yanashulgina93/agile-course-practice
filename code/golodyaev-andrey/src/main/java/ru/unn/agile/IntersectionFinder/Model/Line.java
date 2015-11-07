package ru.unn.agile.IntersectionFinder.core;

public class Line {
    private Vector3D point;
    private Vector3D vector;

    public Line(final Vector3D point, final Vector3D vector) {
        this.point = point;
        this.vector = vector;
    }

    public Vector3D getPoint() {
        return point;
    }

    public void setPoint(final Vector3D pointSource) {
        this.point = point;
    }

    public Vector3D getVector() {
        return vector;
    }

    public void setVector(final Vector3D vector) {
        this.vector = vector;
    }
}
