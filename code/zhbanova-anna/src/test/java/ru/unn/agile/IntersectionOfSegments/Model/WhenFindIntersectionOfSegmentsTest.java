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
    private Intersection correctValueIntersection;

    public enum TypeOfSegments { not_intersect, parallel, have_common_end, intersect_at_one_point,
        same, one_segment_contained_in_another, have_common_part
    }

    private void InitializeTestData(TypeOfSegments typeOfSegments){
        switch (typeOfSegments) {
            case not_intersect: {
                pointsForTest = Arrays.asList(new Point(1.0, 2.0), new Point(5.0, 4.0), new Point(3.0, 1.0), new Point(6.0, 1.0));
                correctValueIntersection = new Intersection(TypeOfIntersection.NotIntersection, null, null);
                correctValueIntersection.setLength_of_intersection(-1);
                break;
            }
            case parallel: {
                pointsForTest = Arrays.asList(new Point(0.0, 0.0), new Point(5.0, 5.0), new Point(0.0, 1.0), new Point(5.0, 6.0));
                correctValueIntersection = new Intersection(TypeOfIntersection.NotIntersection, null, null);
                correctValueIntersection.setLength_of_intersection(-1);
                break;
            }
            case have_common_end: {
                pointsForTest = Arrays.asList(new Point(5.2, 3.0), new Point(5.0, 5.0), new Point(0.0, 1.0), new Point(5.2, 3.0));
                correctValueIntersection = new Intersection(TypeOfIntersection.IntersectionInOnePoint, new Point(5.2, 3.0), new Point(5.2, 3.0));
                correctValueIntersection.setLength_of_intersection(0);
                break;
            }
            case intersect_at_one_point: {
                pointsForTest = Arrays.asList(new Point(0.0, 0.0), new Point(4.0, 2.0), new Point(0.0, 2.0), new Point(4.0, 0.0));
                correctValueIntersection = new Intersection(TypeOfIntersection.IntersectionInOnePoint, new Point(2.0, 1.0), new Point(2.0, 1.0));
                correctValueIntersection.setLength_of_intersection(0);
                break;
            }
            case same: {
                pointsForTest = Arrays.asList(new Point(5.0, 10.0), new Point(10.0, 10.0), new Point(5.0, 10.0), new Point(10.0, 10.0));
                correctValueIntersection = new Intersection(TypeOfIntersection.SegmentsSame, new Point(5.0, 10.0), new Point(10.0, 10.0));
                correctValueIntersection.setLength_of_intersection(5);
                break;
            }
            case one_segment_contained_in_another: {
                pointsForTest = Arrays.asList(new Point(0.0, 0.0), new Point(10.0, 10.0), new Point(2.0, 2.0), new Point(5.0, 5.0));
                correctValueIntersection = new Intersection(TypeOfIntersection.OnePartOfOther, new Point(2.0, 2.0), new Point(5.0, 5.0));
                correctValueIntersection.setLength_of_intersection(Math.sqrt(18));
                break;
            }
            case have_common_part: {
                pointsForTest = Arrays.asList(new Point(0.0, 0.0), new Point(5.0, 5.0), new Point(2.0, 2.0), new Point(8.0, 8.0));
                correctValueIntersection = new Intersection(TypeOfIntersection.SegmentsHaveCommonPart, new Point(2.0, 2.0), new Point(5.0, 5.0));
                correctValueIntersection.setLength_of_intersection(Math.sqrt(18));
                break;
            }
            }

        intersection = intersectionOfSegments.Find_intersection(pointsForTest.get(0),
                pointsForTest.get(1), pointsForTest.get(2), pointsForTest.get(3));
    }

    @Before
    public void setUp() {
        intersectionOfSegments = new IntersectionOfSegments();
    }


    @Test
    public void when_segments_not_intersect_can_return_correct_TypeOfIntersection() {
        InitializeTestData(TypeOfSegments.not_intersect);

        assertEquals(correctValueIntersection.getTypeOfIntersection(), intersection.getTypeOfIntersection());
    }

    @Test
    public void when_segments_parallel_can_return_correct_TypeOfIntersection() {
        InitializeTestData(TypeOfSegments.parallel);

        assertEquals(correctValueIntersection.getTypeOfIntersection(), intersection.getTypeOfIntersection());
    }

    @Test
    public void when_segments_have_common_end_can_return_correct_TypeOfIntersection() {
        InitializeTestData(TypeOfSegments.have_common_end);

        assertEquals(correctValueIntersection.getTypeOfIntersection(), intersection.getTypeOfIntersection());
    }

    @Test
    public void when_segments_intersect_at_one_point_can_return_correct_TypeOfIntersection() {
        InitializeTestData(TypeOfSegments.intersect_at_one_point);

        assertEquals(correctValueIntersection.getTypeOfIntersection(), intersection.getTypeOfIntersection());
    }

    @Test
    public void when_segments_same_can_return_correct_TypeOfIntersection() {
        InitializeTestData(TypeOfSegments.same);

        assertEquals(correctValueIntersection.getTypeOfIntersection(), intersection.getTypeOfIntersection());
    }

    @Test
    public void when_one_segment_contained_in_another_can_return_correct_TypeOfIntersection() {
        InitializeTestData(TypeOfSegments.one_segment_contained_in_another);

        assertEquals(correctValueIntersection.getTypeOfIntersection(), intersection.getTypeOfIntersection());
    }

    @Test
    public void when_segments_have_common_part_can_return_correct_TypeOfIntersection() {
        InitializeTestData(TypeOfSegments.have_common_part);

        assertEquals(correctValueIntersection.getTypeOfIntersection(), intersection.getTypeOfIntersection());
    }

    /**************************/
    @Test
    public void when_segments_not_intersect_can_return_correct_StartPointOfCommonPart() {
        InitializeTestData(TypeOfSegments.not_intersect);

        assertEquals(correctValueIntersection.getStart(), intersection.getStart());
    }

    @Test
    public void when_segments_parallel_can_return_correct_StartPointOfCommonPart() {
        InitializeTestData(TypeOfSegments.parallel);

        assertEquals(correctValueIntersection.getStart(), intersection.getStart());
    }

    @Test
    public void when_segments_have_common_end_can_return_correct_StartPointOfCommonPart() {
        InitializeTestData(TypeOfSegments.have_common_end);

        assertEquals(correctValueIntersection.getStart(), intersection.getStart());
    }

    @Test
    public void when_segments_intersect_at_one_point_can_return_correct_StartPointOfCommonPart() {
        InitializeTestData(TypeOfSegments.intersect_at_one_point);

        assertEquals(correctValueIntersection.getStart(), intersection.getStart());
    }

    @Test
    public void when_segments_same_can_return_correct_StartPointOfCommonPart() {
        InitializeTestData(TypeOfSegments.same);

        assertEquals(correctValueIntersection.getStart(), intersection.getStart());
    }

    @Test
    public void when_one_segment_contained_in_another_can_return_correct_StartPointOfCommonPart() {
        InitializeTestData(TypeOfSegments.one_segment_contained_in_another);

        assertEquals(correctValueIntersection.getStart(), intersection.getStart());
    }

    @Test
    public void when_segments_have_common_part_can_return_correct_StartPointOfCommonPart() {
        InitializeTestData(TypeOfSegments.have_common_part);

        assertEquals(correctValueIntersection.getStart(), intersection.getStart());
    }
    /**************************/
    @Test
    public void when_segments_not_intersect_can_return_correct_FinishPointOfCommonPart() {
        InitializeTestData(TypeOfSegments.not_intersect);

        assertEquals(correctValueIntersection.getFinish(), intersection.getFinish());
    }

    @Test
    public void when_segments_parallel_can_return_correct_FinishPointOfCommonPart() {
        InitializeTestData(TypeOfSegments.parallel);

        assertEquals(correctValueIntersection.getFinish(), intersection.getFinish());
    }

    @Test
    public void when_segments_have_common_end_can_return_correct_FinishPointOfCommonPart() {
        InitializeTestData(TypeOfSegments.have_common_end);

        assertEquals(correctValueIntersection.getFinish(), intersection.getFinish());
    }

    @Test
    public void when_segments_intersect_at_one_point_can_return_correct_FinishPointOfCommonPart() {
        InitializeTestData(TypeOfSegments.intersect_at_one_point);

        assertEquals(correctValueIntersection.getFinish(), intersection.getFinish());
    }

    @Test
    public void when_segments_same_can_return_correct_FinishPointOfCommonPart() {
        InitializeTestData(TypeOfSegments.same);

        assertEquals(correctValueIntersection.getFinish(), intersection.getFinish());
    }

    @Test
    public void when_one_segment_contained_in_another_can_return_correct_FinishPointOfCommonPart() {
        InitializeTestData(TypeOfSegments.one_segment_contained_in_another);

        assertEquals(correctValueIntersection.getFinish(), intersection.getFinish());
    }

    @Test
    public void when_segments_have_common_part_can_return_correct_FinishPointOfCommonPart() {
        InitializeTestData(TypeOfSegments.have_common_part);

        assertEquals(correctValueIntersection.getFinish(), intersection.getFinish());
    }
    /**************************/
    @Test
    public void when_segments_not_intersect_can_return_correct_LengthCommonPart() {
        InitializeTestData(TypeOfSegments.not_intersect);

        assertEquals(correctValueIntersection.getLengthOfIntersection(), intersection.getLengthOfIntersection(), 0.0);
    }

    @Test
    public void when_segments_parallel_can_return_correct_LengthCommonPart() {
        InitializeTestData(TypeOfSegments.parallel);

        assertEquals(correctValueIntersection.getLengthOfIntersection(), intersection.getLengthOfIntersection(), 0.0);
    }

    @Test
    public void when_segments_have_common_end_can_return_correct_LengthCommonPart() {
        InitializeTestData(TypeOfSegments.have_common_end);

        assertEquals(correctValueIntersection.getLengthOfIntersection(), intersection.getLengthOfIntersection(), 0.0);
    }

    @Test
    public void when_segments_intersect_at_one_point_can_return_correct_LengthCommonPart() {
        InitializeTestData(TypeOfSegments.intersect_at_one_point);

        assertEquals(correctValueIntersection.getLengthOfIntersection(), intersection.getLengthOfIntersection(), 0.0);
    }

    @Test
    public void when_segments_same_can_return_correct_LengthCommonPart() {
        InitializeTestData(TypeOfSegments.same);

        assertEquals(correctValueIntersection.getLengthOfIntersection(), intersection.getLengthOfIntersection(), 0.0);
    }

    @Test
    public void when_one_segment_contained_in_another_can_return_correct_LengthCommonPart() {
        InitializeTestData(TypeOfSegments.one_segment_contained_in_another);

        assertEquals(correctValueIntersection.getLengthOfIntersection(), intersection.getLengthOfIntersection(), 0.0);
    }

    @Test
    public void when_segments_have_common_part_can_return_correct_LengthCommonPart() {
        InitializeTestData(TypeOfSegments.have_common_part);

        assertEquals(correctValueIntersection.getLengthOfIntersection(), intersection.getLengthOfIntersection(), 0.0);
    }
}
