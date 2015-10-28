package ru.unn.agile.IntersectionOfSegments;

enum TypeOfIntersection{
    NotIntersection, IntersectionInOnePoint, OnePartOfOther, SegmentsHaveCommonPart, SegmentsSame
}

public class IntersectionOfSegments {

    public Intersection Find_intersection(Point p1, Point p2, Point p3, Point p4) {

        TypeOfIntersection typeOfIntersection;
        Point startPointOfIntersection;
        Point finishPointOfIntersection;


        if(!IsIntersection(p1, p2, p3, p4)) {
            typeOfIntersection = TypeOfIntersection.NotIntersection;
            startPointOfIntersection = null;
            finishPointOfIntersection = null;
        }
        else {
            double k1 = Calculate_slope(p1, p2);
            double k2 = Calculate_slope(p3, p4);

            double b1 = p1.getY() - k1 * p1.getX();
            double b2 = p3.getY() - k2 * p3.getX();

            if(k1 != k2) {
                double x = (b2 - b1) / (k1 - k2);
                double y = k1 * x + b1;
                typeOfIntersection = TypeOfIntersection.IntersectionInOnePoint;
                startPointOfIntersection = finishPointOfIntersection = new Point(x, y);
            }
            else {
                Make_first_Point_with_less_x(p1, p2);
                Make_first_Point_with_less_x(p3, p4);

                if(p1.equals(p3) && p2.equals(p4)) {
                    typeOfIntersection = TypeOfIntersection.SegmentsSame;
                    startPointOfIntersection = p1;
                    finishPointOfIntersection = p2;
                }
                else if(p1.getX() < p3.getX() && p2.getX() > p4.getX()) {
                    typeOfIntersection = TypeOfIntersection.OnePartOfOther;
                    startPointOfIntersection = p3;
                    finishPointOfIntersection = p4;
                }
                else if(p1.getX() > p3.getX() && p2.getX() < p4.getX()) {
                    typeOfIntersection = TypeOfIntersection.OnePartOfOther;
                    startPointOfIntersection = p1;
                    finishPointOfIntersection = p2;
                }
                else if(p2.getX() > p3.getX()) {
                    typeOfIntersection = TypeOfIntersection.SegmentsHaveCommonPart;
                    startPointOfIntersection = p3;
                    finishPointOfIntersection = p2;
                }
                else {
                    typeOfIntersection = TypeOfIntersection.SegmentsHaveCommonPart;
                    startPointOfIntersection = p1;
                    finishPointOfIntersection = p4;
                }
            }
        }
        return new Intersection(typeOfIntersection, startPointOfIntersection, finishPointOfIntersection);
    }

    private boolean IsIntersection(Point p1, Point p2, Point p3, Point p4) {
        //[1, 2, 3] * [1, 2, 4] < 0 è [3, 4, 1] * [3, 4, 2] < 0
        if((Vector_multiplication(p1, p2, p3) * Vector_multiplication(p1, p2, p4) <= 0) &&
                (Vector_multiplication(p3, p4, p1) * Vector_multiplication(p3, p4, p2)<= 0))
            return true;
        return false;
    }

    private double Vector_multiplication(Point p1, Point p2, Point p3) {
        return (p1.getX() - p3.getX()) * (p2.getY() - p3.getY()) - (p1.getY() - p3.getY()) * (p2.getX() - p3.getX());
    }

    private double Calculate_slope(Point p1, Point p2) {
        if(p1.getY() == p2.getY())
            return 0;
        return (p2.getY() - p1.getY()) / (p2.getX() - p1.getX());
    }

    private void Make_first_Point_with_less_x(Point p1, Point p2) {
        if(p1.getX() >= p2.getX())
            ChangePoints(p1, p2);
    }

    private void ChangePoints(Point p1, Point p2) {
        double tmpCoordinate = p1.getX();
        p1.setX(p2.getX());
        p2.setX(tmpCoordinate);
        tmpCoordinate = p1.getY();
        p1.setY(p2.getY());
        p2.setY(tmpCoordinate);
    }
}
