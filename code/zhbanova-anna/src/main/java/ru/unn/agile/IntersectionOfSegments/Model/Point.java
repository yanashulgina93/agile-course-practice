package ru.unn.agile.IntersectionOfSegments;

public class Point {
    private double x;
    private double y;

    Point(final double x, final double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setX(final double x) {
        this.x = x;
    }

    public void setY(final double y) {
        this.y = y;
    }

    @Override
    public int hashCode() {
        return 0;
    }

        @Override
    public boolean equals(final Object object) {
        Point point = (Point) object;
        return (this.x == point.getX() && this.y == point.getY());
    }
}
