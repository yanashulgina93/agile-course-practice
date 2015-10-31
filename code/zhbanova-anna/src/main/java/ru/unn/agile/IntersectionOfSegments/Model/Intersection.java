package ru.unn.agile.IntersectionOfSegments;

public class Intersection {
    private final TypeOfIntersection typeOfIntersection;
    private final Segment segment;

    Intersection(final TypeOfIntersection typeOfIntersection, final Segment segment) {
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
