package ru.unn.agile.IntersectionOfSegments;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(JUnitParamsRunner.class)
public class WhenFindIntersectionOfSegmentsTest {

    public enum TypeOfSegments { not_intersect, parallel, have_common_end, intersect_at_one_point,
        same, one_segment_contained_in_another, have_common_part
    }

    @Before
    public void setUp() {
        intersectionOfSegments = new IntersectionOfSegments();
    }

    @Test
    @Parameters({"not_intersect", "parallel", "have_common_end", "intersect_at_one_point", "same",
            "one_segment_contained_in_another", "have_common_part"})
    public void can_return_correct_TypeOfIntersection(String typeOfSegments) {
        InitializeTestData(TypeOfSegments.valueOf(typeOfSegments));

        assertEquals(correctValueIntersection.getTypeOfIntersection(), intersection.getTypeOfIntersection());
    }

    @Test
    @Parameters({"not_intersect", "parallel", "have_common_end", "intersect_at_one_point", "same",
            "one_segment_contained_in_another", "have_common_part"})
    public void can_return_correct_StartPointOfCommonPart(String typeOfSegments) {
        InitializeTestData(TypeOfSegments.valueOf(typeOfSegments));

        assertEquals(correctValueIntersection.getStart(), intersection.getStart());
    }

    @Test
    @Parameters({"not_intersect", "parallel", "have_common_end", "intersect_at_one_point", "same",
            "one_segment_contained_in_another", "have_common_part"})
    public void can_return_correct_FinishPointOfCommonPart(String typeOfSegments) {
        InitializeTestData(TypeOfSegments.valueOf(typeOfSegments));

        assertEquals(correctValueIntersection.getFinish(), intersection.getFinish());
    }

    @Test
    @Parameters({"not_intersect", "parallel", "have_common_end", "intersect_at_one_point", "same",
            "one_segment_contained_in_another", "have_common_part"})
    public void when_segments_not_intersect_can_return_correct_LengthCommonPart(String typeOfSegments) {
        InitializeTestData(TypeOfSegments.valueOf(typeOfSegments));

        assertEquals(correctValueIntersection.getLengthOfIntersection(), intersection.getLengthOfIntersection(), 0.0);
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

    private IntersectionOfSegments intersectionOfSegments;
    private Intersection intersection;

    private List<Point> pointsForTest;
    private Intersection correctValueIntersection;
}
