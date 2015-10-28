package ru.unn.agile.IntersectionOfSegments;

public class Intersection {
    private TypeOfIntersection typeOfIntersection;
    private Point start;
    private Point finish;
    private double length_of_intersection;

    Intersection(TypeOfIntersection typeOfIntersection, Point start, Point finish)
    {
        this.typeOfIntersection = typeOfIntersection;
        this.start = start;
        this.finish = finish;
        this.length_of_intersection = CalculationLengthOfIntersection();
    }

    private double CalculationLengthOfIntersection() {
        if(typeOfIntersection == TypeOfIntersection.NotIntersection)
            return -1.0;
        return Math.sqrt(Math.pow(start.getX() - finish.getX(), 2) + Math.pow(start.getY() - finish.getY(), 2));
    }

    public double getLengthOfIntersection() {
        return length_of_intersection;
    }

    public TypeOfIntersection getTypeOfIntersection() {
        return typeOfIntersection;
    }

    public Point getFinish() {
        return finish;
    }

    public Point getStart() {
        return start;
    }
}
