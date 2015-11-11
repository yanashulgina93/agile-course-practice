package ru.unn.agile.IntersectionOfSegments.viewmodel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ViewModelTests {
    private ViewModel viewModel;

    @Before
    public void setUp() {
        viewModel = new ViewModel();
    }

    @After
    public void tearDown() {
        viewModel = null;
    }

    @Test
    public void canSetDefaultValues() {
        assertEquals("", viewModel.seg1Point1XProperty().get());
        assertEquals("", viewModel.seg1Point1YProperty().get());
        assertEquals("", viewModel.seg1Point2XProperty().get());
        assertEquals("", viewModel.seg1Point2YProperty().get());

        assertEquals("", viewModel.seg2Point1XProperty().get());
        assertEquals("", viewModel.seg2Point1YProperty().get());
        assertEquals("", viewModel.seg2Point2XProperty().get());
        assertEquals("", viewModel.seg2Point2YProperty().get());

        assertEquals("", viewModel.resultProperty().get());

        assertEquals(Status.WAITING.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void statusIsReadyWhenFieldsAreCorrectFill() {
        setInputData();

        assertEquals(Status.READY.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void statusIsBadFormatWhenSomeFieldInvalid() {
        viewModel.seg1Point1XProperty().set("a");

        assertEquals(Status.BAD_FORMAT.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void statusIsWaitingIfNotEnoughCorrectData() {
        viewModel.seg1Point1XProperty().set("0.0");

        assertEquals(Status.WAITING.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void statusIsSuccessWhenCalculatingSuccessful() {
        setInputData();

        viewModel.calculate();

        assertEquals(Status.SUCCESS.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void statusIsWaitingWhenClearBadValue() {
        viewModel.seg1Point1XProperty().set("a");
        viewModel.seg1Point1XProperty().set("");

        assertEquals(Status.WAITING.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void calculateButtonIsDisabledInitially() {
        assertTrue(viewModel.calculationDisabledProperty().get());
    }

    @Test
    public void calculateButtonIsDisabledWhenFormatIsBad() {
        setInputData();

        viewModel.seg1Point1XProperty().set("a");

        assertTrue(viewModel.calculationDisabledProperty().get());
    }

    @Test
    public void calculateButtonIsDisabledIfNotEnoughCorrectData() {
        viewModel.seg1Point1XProperty().set("0");

        assertTrue(viewModel.calculationDisabledProperty().get());
    }

    @Test
    public void calculateButtonIsEnabledWhenFieldsAreCorrectFill() {
        setInputData();

        assertFalse(viewModel.calculationDisabledProperty().get());
    }

    @Test
    public void findOfIntersectionReturnCorrectResultWhenOneCommonPoint() {
        setFirstSegmentValue("0", "0", "5", "5");
        setSecondSegmentValue("0", "5", "5", "0");

        viewModel.calculate();

        assertEquals("Segments intersection in one point (2.5; 2.5).",
                viewModel.resultProperty().get());
    }

    @Test
    public void findOfIntersectionReturnCorrectResultWhenNotIntersection() {
        setFirstSegmentValue("0", "0", "5", "5");
        setSecondSegmentValue("2", "3", "10", "14");

        viewModel.calculate();

        assertEquals("Segments not intersection.", viewModel.resultProperty().get());
    }

    @Test
    public void findOfIntersectionReturnCorrectResultWhenOnePartOfOther() {
        setFirstSegmentValue("0", "5", "5", "5");
        setSecondSegmentValue("1", "5", "5", "5");

        viewModel.calculate();

        assertEquals("Segments have common part [(1.0; 5.0); (5.0; 5.0)].\nLength = 4.0",
                viewModel.resultProperty().get());
    }

    @Test
    public void findOfIntersectionReturnCorrectResultWhenHaveCommonPart() {
        setFirstSegmentValue("0", "5", "5", "5");
        setSecondSegmentValue("1", "5", "10", "5");

        viewModel.calculate();

        assertEquals("Segments have common part [(1.0; 5.0); (5.0; 5.0)].\nLength = 4.0",
                viewModel.resultProperty().get());
    }

    private void setInputData() {
        setFirstSegmentValue("0", "0", "5", "5");
        setSecondSegmentValue("0", "5", "5", "0");
    }

    private void setSecondSegmentValue(final String startX, final String startY,
                                       final String finishX, final String finishY) {
        viewModel.seg2Point1XProperty().set(startX);
        viewModel.seg2Point1YProperty().set(startY);
        viewModel.seg2Point2XProperty().set(finishX);
        viewModel.seg2Point2YProperty().set(finishY);
    }

    private void setFirstSegmentValue(final String startX, final String startY,
                                      final String finishX, final String finishY) {
        viewModel.seg1Point1XProperty().set(startX);
        viewModel.seg1Point1YProperty().set(startY);
        viewModel.seg1Point2XProperty().set(finishX);
        viewModel.seg1Point2YProperty().set(finishY);
    }
}
