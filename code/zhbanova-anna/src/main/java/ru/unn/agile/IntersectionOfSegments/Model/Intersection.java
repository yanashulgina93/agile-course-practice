package ru.unn.agile.IntersectionOfSegments;

public class Intersection {
    private TypeOfIntersection typeOfIntersection;
    private Segment segment;

    Intersection(TypeOfIntersection typeOfIntersection, Segment segment) {
        this.typeOfIntersection = typeOfIntersection;
        this.segment = segment;
    }

    TypeOfIntersection getTypeOfIntersection() {
        return typeOfIntersection;
    }

    Segment getSegment() {
        return segment;
    }
}
