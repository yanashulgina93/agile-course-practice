package ru.unn.agile.IntersectionOfSegments;

import javafx.util.Pair;

enum TypeOfIntersection{
    NotIntersection, IntersectionInOnePoint, OnePartOfOther, SegmentsHaveCommonPart
}

public class Segment {
    private Point start;
    private Point finish;
    private double lengthSegment;

    Segment(Point start, Point finish) {
        this.start = start;
        this.finish = finish;
    }

    public void setStart(Point start) {
        this.start = start;
    }

    public void setFinish(Point finish) {
        this.finish = finish;
    }

    public Point getStart() {
        return start;
    }

    public Point getFinish() {
        return finish;
    }

    public double getStartX() {
        return start.getX();
    }

    public double getFinishX() {
        return finish.getX();
    }

    public double getStartY() {
        return start.getY();
    }

    public double getFinishY() {
        return finish.getY();
    }

    public double getLengthSegment() {
        return calculationLengthOfSegment();
    }

    @Override
    public boolean equals(final Object object) {
        Segment segment = (Segment) object;
        return (this.getStart() == segment.getStart() && this.getFinish() == segment.getFinish());
    }

    public Pair<TypeOfIntersection, Segment> isIntersectedWith(Segment segment) {
        return new IntersectionOfSegments().findIntersection(this, segment);
    }


    private class IntersectionOfSegments {

        public Pair<TypeOfIntersection, Segment> findIntersection(Segment segment1, Segment segment2) {

            TypeOfIntersection typeOfIntersection;
            Point startPointOfIntersection;
            Point finishPointOfIntersection;


            if(!isIntersection(segment1, segment2)) {
                typeOfIntersection = TypeOfIntersection.NotIntersection;
                startPointOfIntersection = null;
                finishPointOfIntersection = null;
            }
            else {
                double k1 = calculateSlope(segment1);
                double k2 = calculateSlope(segment2);

                double b1 = segment1.getStartY() - k1 * segment1.getStartX();
                double b2 = segment2.getStartY() - k2 * segment2.getStartX();

                if(k1 != k2) {
                    double x = (b2 - b1) / (k1 - k2);
                    double y = k1 * x + b1;
                    typeOfIntersection = TypeOfIntersection.IntersectionInOnePoint;
                    startPointOfIntersection = new Point(x, y);
                    finishPointOfIntersection = new Point(x, y);
                }
                else {
                    makeFirstPointWithLessX(segment1);
                    makeFirstPointWithLessX(segment2);

                    if(segment1.getStartX() < segment2.getStartX() && segment1.getFinishX() > segment2.getFinishX()) {
                        typeOfIntersection = TypeOfIntersection.OnePartOfOther;
                        startPointOfIntersection = segment2.getStart();
                        finishPointOfIntersection = segment2.getFinish();
                    }
                    else if(segment1.getStartX() > segment2.getStartX() && segment1.getFinishX() < segment2.getFinishX()) {
                        typeOfIntersection = TypeOfIntersection.OnePartOfOther;
                        startPointOfIntersection = segment1.getStart();
                        finishPointOfIntersection = segment1.getFinish();
                    }
                    else if(segment1.getFinishX() > segment2.getStartX()) {
                        typeOfIntersection = TypeOfIntersection.SegmentsHaveCommonPart;
                        startPointOfIntersection = segment2.getStart();
                        finishPointOfIntersection = segment1.getFinish();
                    }
                    else {
                        typeOfIntersection = TypeOfIntersection.SegmentsHaveCommonPart;
                        startPointOfIntersection = segment1.getStart();
                        finishPointOfIntersection = segment2.getFinish();
                    }
                }
            }
            return new Pair<TypeOfIntersection, Segment>(typeOfIntersection, new Segment(startPointOfIntersection, finishPointOfIntersection));
        }

        private boolean isIntersection(Segment segment1, Segment segment2) {
            //[1, 2, 3] * [1, 2, 4] < 0 è [3, 4, 1] * [3, 4, 2] < 0
            if((vectorMultiplication(segment1.getStart(), segment1.getFinish(), segment2.getStart()) *
                    vectorMultiplication(segment1.getStart(), segment1.getFinish(), segment2.getFinish()) <= 0) &&
                    (vectorMultiplication(segment2.getStart(), segment2.getFinish(), segment1.getStart()) *
                            vectorMultiplication(segment2.getStart(), segment2.getFinish(), segment1.getFinish()) <= 0))
                return true;
            return false;
        }

        private double vectorMultiplication(Point p1, Point p2, Point p3) {
            return (p1.getX() - p3.getX()) * (p2.getY() - p3.getY()) - (p1.getY() - p3.getY()) * (p2.getX() - p3.getX());
        }

        private double calculateSlope(Segment segment) {
            if(segment.getStartY() == segment.getFinishY())
                return 0;
            return (segment.getFinishY() - segment.getStartY()) / (segment.getFinishX() - segment.getStartX());
        }

        private void makeFirstPointWithLessX(Segment segment) {
            if(segment.getStartX() >= segment.getFinishX())
                changePoints(segment.getStart(), segment.getFinish());
        }

        private void changePoints(Point p1, Point p2) {
            double tmpCoordinate = p1.getX();
            p1.setX(p2.getX());
            p2.setX(tmpCoordinate);
            tmpCoordinate = p1.getY();
            p1.setY(p2.getY());
            p2.setY(tmpCoordinate);
        }
    }

    private double calculationLengthOfSegment() {
        if(start == null || finish == null)
            return -1.0;
        return Math.sqrt(Math.pow(start.getX() - finish.getX(), 2) + Math.pow(start.getY() - finish.getY(), 2));
    }
}
