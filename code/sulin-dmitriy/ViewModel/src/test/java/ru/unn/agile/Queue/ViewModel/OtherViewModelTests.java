package ru.unn.agile.Queue.ViewModel;

import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.*;




public class OtherViewModelTests {

    private LabQueueViewModel viewModel;
    @Before
    public void setUpViewModel() {
        viewModel = new LabQueueViewModel();
    }

    @Test
    public void canPushElement() {
        final String value = "Element";

        viewModel.setDataInputField(value);
        viewModel.pushElement();
        viewModel.updateHeadElementField();

        assertEquals(value, viewModel.getHeadElementField());
    }

    @Test
    public void whenQueueIsNotEmptyPopButtonIsEnabled() {
        final String value = "Element";

        viewModel.setDataInputField(value);
        viewModel.pushElement();

        assertTrue(viewModel.isPopButtonEnabled());
    }

    @Test
    public void whenQueueIsNotEmptyFindButtonIsEnabled() {
        final String value = "Element";

        viewModel.setDataInputField(value);
        viewModel.pushElement();

        assertTrue(viewModel.isFindButtonEnabled());
    }

    @Test
    public void whenQueueIsEmptyPopButtonIsDisabled() {
        final String value = "Element";

        viewModel.setDataInputField(value);
        viewModel.pushElement();
        viewModel.popElement();

        assertFalse(viewModel.isPopButtonEnabled());
    }

    @Test
    public void whenQueueIsEmptyFindButtonIsDisabled() {
        final String value = "Element";

        viewModel.setDataInputField(value);
        viewModel.pushElement();
        viewModel.popElement();

        assertFalse(viewModel.isFindButtonEnabled());
    }

    @Test
    public void canPopElement() {
        final String value = "Element";

        viewModel.setDataInputField(value);
        viewModel.pushElement();
        viewModel.popElement();

        assertEquals(value, viewModel.getDataOutputField());
    }

    @Test
    public void canFindElement() {
        final String value1 = "Element";
        final String value2 = "NewElement";

        viewModel.setDataInputField(value1);
        viewModel.pushElement();
        viewModel.setDataInputField(value2);
        viewModel.pushElement();
        viewModel.findElement();

        assertEquals("2", viewModel.getDataOutputField());
    }

    @Test
    public void cantFindElement() {
        final String value1 = "Element";
        final String value2 = "NewElement";

        viewModel.setDataInputField(value1);
        viewModel.pushElement();
        viewModel.setDataInputField(value2);
        viewModel.findElement();

        assertEquals("Element Not Found", viewModel.getDataOutputField());
    }

    @Test
    public void canFindRandomElement() throws Exception {
        final int size = 10;

        for (int i = size; i > 0; i--) {
            viewModel.setDataInputField(Integer.toString(i));
            viewModel.pushElement();
        }

        Random rand = new Random();
        final int value1 = 1 + rand.nextInt(size);
        viewModel.setDataInputField(Integer.toString(value1));
        viewModel.findElement();
        final int result = Integer.valueOf(viewModel.getDataOutputField());
        final int realResult = size - value1 + 1;
        assertEquals(realResult, result);
    }
}
