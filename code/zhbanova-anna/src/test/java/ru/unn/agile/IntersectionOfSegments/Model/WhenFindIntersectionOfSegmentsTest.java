package ru.unn.agile.IntersectionOfSegments;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class WhenFindIntersectionOfSegmentsTest {
    private enum TypeOfSegments { not_intersect, parallel, have_common_end, intersect_at_one_point,
        same, one_segment_contained_in_another, have_common_part
    }

    public  WhenFindIntersectionOfSegmentsTest(final TypeOfSegments typeOfSegments) {
        this.typeOfSegments = typeOfSegments;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> testTypeOfSegments() {
        return Arrays.asList(new Object[][]{
                {TypeOfSegments.not_intersect},
                {TypeOfSegments.parallel},
                {TypeOfSegments.have_common_end},
                {TypeOfSegments.intersect_at_one_point},
                {TypeOfSegments.same},
                {TypeOfSegments.one_segment_contained_in_another},
                {TypeOfSegments.have_common_part}
        });
    }

    @Test
    public void canReturnCorrectTypeOfIntersection() {
        initializeTestData(typeOfSegments);

        assertEquals(correctResult.getTypeOfIntersection(), resultIsIntersectedWith.getTypeOfIntersection());
    }

    @Test
    public void canReturnCorrectStartPointOfCommonPart() {
        initializeTestData(typeOfSegments);

        assertEquals(correctResult.getSegment().getStart(), resultIsIntersectedWith.getSegment().getStart());
    }

    @Test
    public void canReturnCorrectFinishPointOfCommonPart() {
        initializeTestData(typeOfSegments);

        assertEquals(correctResult.getSegment().getFinish(), resultIsIntersectedWith.getSegment().getFinish());
    }

    @Test
    public void canReturnCorrectLengthCommonPart() {
        initializeTestData(typeOfSegments);

        assertEquals(correctLengthOfCommonPart, resultIsIntersectedWith.getSegment().getLengthSegment(), 0.0);
    }

    private void initializeTestData(TypeOfSegments typeOfSegments){
        switch (typeOfSegments) {
            case not_intersect: {
                testSegment1 = new Segment(new Point(1.0, 2.0), new Point(5.0, 4.0));
                testSegment2 = new Segment(new Point(3.0, 1.0), new Point(6.0, 1.0));
                correctResult = new Intersection(TypeOfIntersection.NotIntersection, new Segment(null, null));
                correctLengthOfCommonPart = -1;
                break;
            }
            case parallel: {
                testSegment1 = new Segment(new Point(0.0, 0.0), new Point(5.0, 5.0));
                testSegment2 = new Segment(new Point(0.0, 1.0), new Point(5.0, 6.0));
                correctResult = new Intersection(TypeOfIntersection.NotIntersection, new Segment(null, null));
                correctLengthOfCommonPart = -1;
                break;
            }
            case have_common_end: {
                testSegment1 = new Segment(new Point(5.2, 3.0), new Point(5.0, 5.0));
                testSegment2 = new Segment(new Point(0.0, 1.0), new Point(5.2, 3.0));
                correctResult = new Intersection(TypeOfIntersection.IntersectionInOnePoint, new Segment(testSegment1.getStart(), testSegment1.getStart()));
                correctLengthOfCommonPart = 0;
                break;
            }
            case intersect_at_one_point: {
                testSegment1 = new Segment(new Point(0.0, 0.0), new Point(4.0, 2.0));
                testSegment2 = new Segment(new Point(0.0, 2.0), new Point(4.0, 0.0));
                correctResult = new Intersection(TypeOfIntersection.IntersectionInOnePoint, new Segment(new Point(2.0, 1.0), new Point(2.0, 1.0)));
                correctLengthOfCommonPart = 0;
                break;
            }
            case same: {
                testSegment1 = new Segment(new Point(5.0, 10.0), new Point(10.0, 10.0));
                testSegment2 = new Segment(new Point(5.0, 10.0), new Point(10.0, 10.0));
                correctResult = new Intersection(TypeOfIntersection.SegmentsHaveCommonPart, testSegment1);
                correctLengthOfCommonPart = 5;
                break;
            }
            case one_segment_contained_in_another: {
                testSegment1 = new Segment(new Point(0.0, 0.0), new Point(10.0, 10.0));
                testSegment2 = new Segment(new Point(2.0, 2.0), new Point(5.0, 5.0));
                correctResult = new Intersection(TypeOfIntersection.OnePartOfOther, testSegment2);
                correctLengthOfCommonPart = Math.sqrt(18);
                break;
            }
            case have_common_part: {
                testSegment1 = new Segment(new Point(0.0, 0.0), new Point(5.0, 5.0));
                testSegment2 = new Segment(new Point(2.0, 2.0), new Point(8.0, 8.0));
                correctResult = new Intersection(TypeOfIntersection.SegmentsHaveCommonPart,  new Segment(testSegment2.getStart(), testSegment1.getFinish()));
                correctLengthOfCommonPart = Math.sqrt(18);
                break;
            }
        }

        resultIsIntersectedWith = testSegment1.isIntersectedWith(testSegment2);
    }

    private TypeOfSegments typeOfSegments;
    private Segment testSegment1;
    private Segment testSegment2;
    private Intersection resultIsIntersectedWith;
    private Intersection correctResult;
    private double correctLengthOfCommonPart;
}
