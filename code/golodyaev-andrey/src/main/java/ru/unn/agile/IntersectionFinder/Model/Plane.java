package ru.unn.agile.IntersectionFinder.core;

public class Plane {
    private Vector3D point;
    private Vector3D normal;
    private static final double EPS = 0.00000001;

    public Plane(final Vector3D point, final Vector3D normal) {
        this.point = point;
        this.normal = normal;
    }

    public Vector3D getNormal() {
        return normal;
    }

    public void setNormal(final Vector3D normalSource) {
        normal = normalSource;
    }

    public Vector3D getPoint() {
        return point;
    }

    public void setPoint(final Vector3D pointSource) {
        point = pointSource;
    }

    public boolean isPointOnPlane(final Vector3D p) {
        if (normal.dot(p) - normal.dot(point) < EPS) {
            return true;
        }
        return false;
    }
}
