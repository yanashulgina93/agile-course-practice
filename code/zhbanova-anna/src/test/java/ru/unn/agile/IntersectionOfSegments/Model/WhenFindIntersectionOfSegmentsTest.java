package ru.unn.agile.IntersectionOfSegments;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class WhenFindIntersectionOfSegmentsTest {
    private IntersectionOfSegments intersectionOfSegments;
    private Intersection intersection;
    private List<Point> pointsForTest;

    public enum TypeOfSegments { not_intersect, parallel, have_common_end, intersect_at_one_point,
        same, one_segment_contained_in_another, have_common_part
    }

    private List<Point> GetPointsForTest(TypeOfSegments typeOfSegments){
        switch (typeOfSegments) {
            case not_intersect:
                 return Arrays.asList(new Point(1.0, 2.0), new Point(5.0, 4.0), new Point(3.0, 1.0), new Point(6.0, 1.0));
            case parallel:
                return Arrays.asList(new Point(0.0, 0.0), new Point(5.0, 5.0), new Point(0.0, 1.0), new Point(5.0, 6.0));
            case have_common_end:
                return Arrays.asList(new Point(5.2, 3.0), new Point(5.0, 5.0), new Point(0.0, 1.0), new Point(5.2, 3.0));
            case intersect_at_one_point:
                return Arrays.asList(new Point(0.0, 0.0), new Point(4.0, 2.0), new Point(0.0, 2.0), new Point(4.0, 0.0));
            case same:
                return Arrays.asList(new Point(0.0, 0.0), new Point(10.0, 10.0), new Point(0.0, 0.0), new Point(10.0, 10.0));
            case one_segment_contained_in_another:
                return Arrays.asList(new Point(0.0, 0.0), new Point(10.0, 10.0), new Point(2.0, 2.0), new Point(5.0, 5.0));
            case have_common_part:
                return Arrays.asList(new Point(0.0, 0.0), new Point(5.0, 5.0), new Point(2.0, 2.0), new Point(8.0, 8.0));
            default:
                return Arrays.asList(new Point(0.0, 0.0), new Point(5.0, 5.0), new Point(2.0, 2.0), new Point(8.0, 8.0));
            }
    }

    private void InitializeIntersection(TypeOfSegments typeOfSegments){
        pointsForTest = GetPointsForTest(typeOfSegments);
        intersection = intersectionOfSegments.Find_intersection(pointsForTest.get(0),
                pointsForTest.get(1), pointsForTest.get(2),pointsForTest.get(3));
    }

    @Before
    public void setUp() {
        intersectionOfSegments = new IntersectionOfSegments();
    }


    @Test
    public void when_segments_not_intersect_can_return_correct_TypeOfIntersection() {
        InitializeIntersection(TypeOfSegments.not_intersect);

        assertEquals(TypeOfIntersection.NotIntersection, intersection.getTypeOfIntersection());
    }

    @Test
    public void when_segments_parallel_can_return_correct_TypeOfIntersection() {
        InitializeIntersection(TypeOfSegments.parallel);

        assertEquals(TypeOfIntersection.NotIntersection, intersection.getTypeOfIntersection());
    }

    @Test
    public void when_segments_have_common_end_can_return_correct_TypeOfIntersection() {
        InitializeIntersection(TypeOfSegments.have_common_end);

        assertEquals(TypeOfIntersection.IntersectionInOnePoint, intersection.getTypeOfIntersection());
    }

    @Test
    public void when_segments_intersect_at_one_point_can_return_correct_TypeOfIntersection() {
        InitializeIntersection(TypeOfSegments.intersect_at_one_point);

        assertEquals(TypeOfIntersection.IntersectionInOnePoint, intersection.getTypeOfIntersection());
    }

    @Test
    public void when_segments_same_can_return_correct_TypeOfIntersection() {
        InitializeIntersection(TypeOfSegments.same);

        assertEquals(TypeOfIntersection.SegmentsSame, intersection.getTypeOfIntersection());
    }

    @Test
    public void when_one_segment_contained_in_another_can_return_correct_TypeOfIntersection() {
        InitializeIntersection(TypeOfSegments.one_segment_contained_in_another);

        assertEquals(TypeOfIntersection.OnePartOfOther, intersection.getTypeOfIntersection());
    }

    @Test
    public void when_segments_have_common_part_can_return_correct_TypeOfIntersection() {
        InitializeIntersection(TypeOfSegments.have_common_part);

        assertEquals(TypeOfIntersection.SegmentsHaveCommonPart, intersection.getTypeOfIntersection());
    }

    /**************************/
    @Test
    public void when_segments_not_intersect_can_return_correct_StartPointOfCommonPart() {
        InitializeIntersection(TypeOfSegments.not_intersect);

        assertEquals(null, intersection.getStart());
    }

    @Test
    public void when_segments_parallel_can_return_correct_StartPointOfCommonPart() {
        InitializeIntersection(TypeOfSegments.parallel);

        assertEquals(null, intersection.getStart());
    }

    @Test
    public void when_segments_have_common_end_can_return_correct_StartPointOfCommonPart() {
        InitializeIntersection(TypeOfSegments.have_common_end);

        assertEquals(pointsForTest.get(0), intersection.getStart());
    }

    @Test
    public void when_segments_intersect_at_one_point_can_return_correct_StartPointOfCommonPart() {
        InitializeIntersection(TypeOfSegments.intersect_at_one_point);

        assertEquals(new Point(2.0, 1.0), intersection.getStart());
    }

    @Test
    public void when_segments_same_can_return_correct_StartPointOfCommonPart() {
        InitializeIntersection(TypeOfSegments.same);

        assertEquals(pointsForTest.get(0), intersection.getStart());
    }

    @Test
    public void when_one_segment_contained_in_another_can_return_correct_StartPointOfCommonPart() {
        InitializeIntersection(TypeOfSegments.one_segment_contained_in_another);

        assertEquals(pointsForTest.get(2), intersection.getStart());
    }

    @Test
    public void when_segments_have_common_part_can_return_correct_StartPointOfCommonPart() {
        InitializeIntersection(TypeOfSegments.have_common_part);

        assertEquals(pointsForTest.get(2), intersection.getStart());
    }
    /**************************/
    @Test
    public void when_segments_not_intersect_can_return_correct_FinishPointOfCommonPart() {
        InitializeIntersection(TypeOfSegments.not_intersect);

        assertEquals(null, intersection.getFinish());
    }

    @Test
    public void when_segments_parallel_can_return_correct_FinishPointOfCommonPart() {
        InitializeIntersection(TypeOfSegments.parallel);

        assertEquals(null, intersection.getFinish());
    }

    @Test
    public void when_segments_have_common_end_can_return_correct_FinishPointOfCommonPart() {
        InitializeIntersection(TypeOfSegments.have_common_end);

        assertEquals(pointsForTest.get(0), intersection.getFinish());
    }

    @Test
    public void when_segments_intersect_at_one_point_can_return_correct_FinishPointOfCommonPart() {
        InitializeIntersection(TypeOfSegments.intersect_at_one_point);

        assertEquals(new Point(2.0, 1.0), intersection.getFinish());
    }

    @Test
    public void when_segments_same_can_return_correct_FinishPointOfCommonPart() {
        InitializeIntersection(TypeOfSegments.same);

        assertEquals(pointsForTest.get(1), intersection.getFinish());
    }

    @Test
    public void when_one_segment_contained_in_another_can_return_correct_FinishPointOfCommonPart() {
        InitializeIntersection(TypeOfSegments.one_segment_contained_in_another);

        assertEquals(pointsForTest.get(3), intersection.getFinish());
    }

    @Test
    public void when_segments_have_common_part_can_return_correct_FinishPointOfCommonPart() {
        InitializeIntersection(TypeOfSegments.have_common_part);

        assertEquals(pointsForTest.get(1), intersection.getFinish());
    }
    /**************************/
    @Test
    public void when_segments_not_intersect_can_return_correct_LengthCommonPart() {
        InitializeIntersection(TypeOfSegments.not_intersect);

        assertEquals(-1, intersection.getLengthOfIntersection(), 0.0);
    }

    @Test
    public void when_segments_parallel_can_return_correct_LengthCommonPart() {
        InitializeIntersection(TypeOfSegments.parallel);

        assertEquals(-1, intersection.getLengthOfIntersection(), 0.0);
    }

    @Test
    public void when_segments_have_common_end_can_return_correct_LengthCommonPart() {
        InitializeIntersection(TypeOfSegments.have_common_end);

        assertEquals(0, intersection.getLengthOfIntersection(), 0.0);
    }

    @Test
    public void when_segments_intersect_at_one_point_can_return_correct_LengthCommonPart() {
        InitializeIntersection(TypeOfSegments.intersect_at_one_point);

        assertEquals(0, intersection.getLengthOfIntersection(), 0.0);
    }

    @Test
    public void when_segments_same_can_return_correct_LengthCommonPart() {
        InitializeIntersection(TypeOfSegments.same);

        assertEquals(Math.sqrt(100 + 100), intersection.getLengthOfIntersection(), 0.0);
    }

    @Test
    public void when_one_segment_contained_in_another_can_return_correct_LengthCommonPart() {
        InitializeIntersection(TypeOfSegments.one_segment_contained_in_another);

        assertEquals(Math.sqrt(9+9), intersection.getLengthOfIntersection(), 0.0);
    }

    @Test
    public void when_segments_have_common_part_can_return_correct_LengthCommonPart() {
        InitializeIntersection(TypeOfSegments.have_common_part);

        assertEquals(Math.sqrt(9+9), intersection.getLengthOfIntersection(), 0.0);
    }
}