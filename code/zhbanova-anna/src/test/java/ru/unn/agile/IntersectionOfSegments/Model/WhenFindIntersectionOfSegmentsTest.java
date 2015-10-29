package ru.unn.agile.IntersectionOfSegments;

import javafx.util.Pair;
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

    @Test
    @Parameters({"not_intersect", "parallel", "have_common_end", "intersect_at_one_point", "same",
            "one_segment_contained_in_another", "have_common_part"})
    public void canReturnCorrectTypeOfIntersection(String typeOfSegments) {
        initializeTestData(TypeOfSegments.valueOf(typeOfSegments));

        assertEquals(correctResult.getKey(), resultIsIntersectedWith.getKey());
    }

    @Test
    @Parameters({"not_intersect", "parallel", "have_common_end", "intersect_at_one_point", "same",
            "one_segment_contained_in_another", "have_common_part"})
    public void canReturnCorrectStartPointOfCommonPart(String typeOfSegments) {
        initializeTestData(TypeOfSegments.valueOf(typeOfSegments));

        assertEquals(correctResult.getValue().getStart(), resultIsIntersectedWith.getValue().getStart());
    }

    @Test
    @Parameters({"not_intersect", "parallel", "have_common_end", "intersect_at_one_point", "same",
            "one_segment_contained_in_another", "have_common_part"})
    public void canReturnCorrectFinishPointOfCommonPart(String typeOfSegments) {
        initializeTestData(TypeOfSegments.valueOf(typeOfSegments));

        assertEquals(correctResult.getValue().getFinish(), resultIsIntersectedWith.getValue().getFinish());
    }

    @Test
    @Parameters({"not_intersect", "parallel", "have_common_end", "intersect_at_one_point", "same",
            "one_segment_contained_in_another", "have_common_part"})
    public void canReturnCorrectLengthCommonPart(String typeOfSegments) {
        initializeTestData(TypeOfSegments.valueOf(typeOfSegments));

        assertEquals(correctLengthOfCommonPart, resultIsIntersectedWith.getValue().getLengthSegment(), 0.0);
    }

    private void initializeTestData(TypeOfSegments typeOfSegments){
        switch (typeOfSegments) {
            case not_intersect: {
                testSegment1 = new Segment(new Point(1.0, 2.0), new Point(5.0, 4.0));
                testSegment2 = new Segment(new Point(3.0, 1.0), new Point(6.0, 1.0));
                correctResult = new Pair<TypeOfIntersection, Segment>(TypeOfIntersection.NotIntersection, new Segment(null, null));
                correctLengthOfCommonPart = -1;
                break;
            }
            case parallel: {
                testSegment1 = new Segment(new Point(0.0, 0.0), new Point(5.0, 5.0));
                testSegment2 = new Segment(new Point(0.0, 1.0), new Point(5.0, 6.0));
                correctResult = new Pair<TypeOfIntersection, Segment>(TypeOfIntersection.NotIntersection, new Segment(null, null));
                correctLengthOfCommonPart = -1;
                break;
            }
            case have_common_end: {
                testSegment1 = new Segment(new Point(5.2, 3.0), new Point(5.0, 5.0));
                testSegment2 = new Segment(new Point(0.0, 1.0), new Point(5.2, 3.0));
                correctResult = new Pair<TypeOfIntersection, Segment>(TypeOfIntersection.IntersectionInOnePoint, new Segment(testSegment1.getStart(), testSegment1.getStart()));
                correctLengthOfCommonPart = 0;
                break;
            }
            case intersect_at_one_point: {
                testSegment1 = new Segment(new Point(0.0, 0.0), new Point(4.0, 2.0));
                testSegment2 = new Segment(new Point(0.0, 2.0), new Point(4.0, 0.0));
                correctResult = new Pair<TypeOfIntersection, Segment>(TypeOfIntersection.IntersectionInOnePoint, new Segment(new Point(2.0, 1.0), new Point(2.0, 1.0)));
                correctLengthOfCommonPart = 0;
                break;
            }
            case same: {
                testSegment1 = new Segment(new Point(5.0, 10.0), new Point(10.0, 10.0));
                testSegment2 = new Segment(new Point(5.0, 10.0), new Point(10.0, 10.0));
                correctResult = new Pair<TypeOfIntersection, Segment>(TypeOfIntersection.SegmentsHaveCommonPart, testSegment1);
                correctLengthOfCommonPart = 5;
                break;
            }
            case one_segment_contained_in_another: {
                testSegment1 = new Segment(new Point(0.0, 0.0), new Point(10.0, 10.0));
                testSegment2 = new Segment(new Point(2.0, 2.0), new Point(5.0, 5.0));
                correctResult = new Pair<TypeOfIntersection, Segment>(TypeOfIntersection.OnePartOfOther, testSegment2);
                correctLengthOfCommonPart = Math.sqrt(18);
                break;
            }
            case have_common_part: {
                testSegment1 = new Segment(new Point(0.0, 0.0), new Point(5.0, 5.0));
                testSegment2 = new Segment(new Point(2.0, 2.0), new Point(8.0, 8.0));
                correctResult = new Pair<TypeOfIntersection, Segment>(TypeOfIntersection.SegmentsHaveCommonPart,  new Segment(testSegment2.getStart(), testSegment1.getFinish()));
                correctLengthOfCommonPart = Math.sqrt(18);
                break;
            }
        }

        resultIsIntersectedWith = testSegment1.isIntersectedWith(testSegment2);
    }

    private Segment testSegment1;
    private Segment testSegment2;
    private Pair<TypeOfIntersection, Segment> resultIsIntersectedWith;
    private Pair<TypeOfIntersection, Segment> correctResult;
    private double correctLengthOfCommonPart;
}
