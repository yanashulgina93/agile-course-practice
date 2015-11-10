package ru.unn.agile.AreaConverter.viewmodel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.unn.agile.AreaConverter.model.AreaMeasure;

import static org.junit.Assert.assertEquals;

public class ViewModelTest {
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
        assertEquals("", viewModel.getInputArea());
        assertEquals("", viewModel.getResultArea());
        assertEquals(Status.WAITING, viewModel.getStatus());
        assertEquals(AreaMeasure.SQUARE_METER, viewModel.getFrom());
        assertEquals(AreaMeasure.SQUARE_KILOMETER, viewModel.getTo());
        assertEquals(false, viewModel.isConvertButtonEnabled());
    }

    @Test
    public void canSetInputArea() {
        viewModel.setInputArea("10.0");
        assertEquals("10.0", viewModel.getInputArea());
    }

    @Test
    public void canSetStatusReadyWhenInputAreaIsFilled() {
        viewModel.setInputArea("1.0");
        viewModel.parseInput();
        assertEquals(Status.READY, viewModel.getStatus());
    }

    @Test
    public void isConvertButtonEnableWhenInputAreaIsFilled() {
        viewModel.setInputArea("5.0");
        viewModel.parseInput();
        assertEquals(true, viewModel.isConvertButtonEnabled());
    }

    @Test
    public void canSetStatusWaitingWhenInputAreaIsNotFilled() {
        viewModel.setInputArea("");
        viewModel.parseInput();
        assertEquals(Status.WAITING, viewModel.getStatus());
    }

    @Test
    public void isConvertButtonDisableWhenInputAreaIsNotFilled() {
        viewModel.setInputArea("");
        viewModel.parseInput();
        assertEquals(false, viewModel.isConvertButtonEnabled());
    }

    @Test
    public void canSetStatusWrongFormatWhenInputAreaIsIncorrect() {
        viewModel.setInputArea("z");
        viewModel.parseInput();
        assertEquals(Status.WRONG_FORMAT, viewModel.getStatus());
    }

    @Test
    public void isConvertButtonDisableWhenInputAreaIsIncorrect() {
        viewModel.setInputArea("m");
        viewModel.parseInput();
        assertEquals(false, viewModel.isConvertButtonEnabled());
    }
}
