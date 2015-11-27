package ru.unn.agile.Queue.ViewModel;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Random;

public class OtherViewModelTests {

    private LabQueueViewModel viewModel;
    @Before
    public void setUpViewModel() {
        viewModel = new LabQueueViewModel();
    }

    @Test
    public void canPushElement() {
        final String value = "Element";

        viewModel.setFieldForDataInput(value);
        viewModel.pushElement();
        viewModel.updateFieldForHeadElement();

        assertEquals(value, viewModel.getFieldForHeadElement());
    }

    @Test
    public void whenQueueIsNotEmptyPopButtonIsEnabled() {
        final String value = "Element";

        viewModel.setFieldForDataInput(value);
        viewModel.pushElement();

        assertTrue(viewModel.isPopButtonEnabled());
    }

    @Test
    public void whenQueueIsNotEmptyFindButtonIsEnabled() {
        final String value = "Element";

        viewModel.setFieldForDataInput(value);
        viewModel.pushElement();

        assertTrue(viewModel.isFindButtonEnabled());
    }

    @Test
    public void canPopElement() {
        final String value = "Element";

        viewModel.setFieldForDataInput(value);
        viewModel.pushElement();
        viewModel.popElement();

        assertEquals(value, viewModel.getFieldForDataOutput());
    }

    @Test
    public void canFindElement() {
        final String value1 = "Element";
        final String value2 = "NewElement";

        viewModel.setFieldForDataInput(value1);
        viewModel.pushElement();
        viewModel.setFieldForDataInput(value2);
        viewModel.pushElement();
        viewModel.findElement();

        assertEquals("1", viewModel.getFieldForDataOutput());
    }

    @Test
    public void cantFindElement() {
        final String value1 = "Element";
        final String value2 = "NewElement";

        viewModel.setFieldForDataInput(value1);
        viewModel.pushElement();
        viewModel.setFieldForDataInput(value2);
        viewModel.findElement();

        assertEquals("Element Not Found", viewModel.getFieldForDataOutput());
    }

    @Test
    public void canFindRandomElement() throws Exception {
        final int size = 10;

        for (int i = size; i > 0; i--) {
            viewModel.setFieldForDataInput(Integer.toString(i));
            viewModel.pushElement();
        }

        Random rand = new Random();
        final int value1 = 1 + rand.nextInt(size);
        viewModel.setFieldForDataInput(Integer.toString(value1));
        viewModel.findElement();
        final int result = Integer.valueOf(viewModel.getFieldForDataOutput());

        assertEquals(size - value1, result);
    }
}
