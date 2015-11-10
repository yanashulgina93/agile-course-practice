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
}
