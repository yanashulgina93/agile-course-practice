package ru.unn.agile.Deque.viewmodel;

import org.junit.Before;
import org.junit.Test;
import ru.unn.agile.Deque.model.Deque;

import static org.junit.Assert.*;

public class DequeViewModelTest {
    private DequeViewModel viewModel;

    @Before
    public void initializeDequeViewModel() {
        viewModel = new DequeViewModel();
    }

    @Test
    public void byDefaultPushFrontButtonIsDisabled() {
        assertFalse(viewModel.isPushFrontButtonEnabled());
    }

    @Test
    public void byDefaultPushBackButtonIsDisabled() {
        assertFalse(viewModel.isPushBackButtonEnabled());
    }

    @Test
    public void byDefaultPopFrontButtonIsDisabled() {
        assertFalse(viewModel.isPopFrontButtonEnabled());
    }

    @Test
    public void byDefaultPopBackButtonIsDisabled() {
        assertFalse(viewModel.isPopBackButtonEnabled());
    }

    @Test
    public void byDefaultClearButtonIsDisabled() {
        assertFalse(viewModel.isClearButtonEnabled());
    }

    @Test
    public void byDefaultCheckButtonIsDisabled() {
        assertFalse(viewModel.isCheckButtonEnabled());
    }

    @Test
    public void whenEnteredNumberPushFrontButtonIsEnabled() {
        viewModel.setInputNumber("4");

        assertTrue(viewModel.isPushFrontButtonEnabled());
    }

    @Test
    public void whenEnteredNumberPushBackButtonIsDisabled() {
        viewModel.setInputNumber("8");

        assertTrue(viewModel.isPushBackButtonEnabled());
    }

    @Test
    public void whenDequeIsNotEmptyPopFrontButtonIsEnabled() {
        viewModel.setInputNumber("15");
        viewModel.getOperationsWithDeque().pushFront();

        assertTrue(viewModel.isPopFrontButtonEnabled());
    }

    @Test
    public void whenDequeIsNotEmptyPopBackButtonIsEnabled() {
        viewModel.setInputNumber("16");
        viewModel.getOperationsWithDeque().pushBack();

        assertTrue(viewModel.isPopBackButtonEnabled());
    }

    @Test
    public void whenClearedNumberPushFrontButtonIsDisabled() {
        viewModel.setInputNumber("23");
        viewModel.setInputNumber("");

        assertFalse(viewModel.isPushFrontButtonEnabled());
    }

    @Test
    public void whenClearedNumberPushBackButtonIsDisabled() {
        viewModel.setInputNumber("42");
        viewModel.setInputNumber("");

        assertFalse(viewModel.isPushBackButtonEnabled());
    }

    @Test
    public void whenEnteredNotNumberPushFrontButtonIsDisabled() {
        viewModel.setInputNumber("1a");

        assertFalse(viewModel.isPushFrontButtonEnabled());
    }

    @Test
    public void whenEnteredNotNumberPushBackButtonIsDisabled() {
        viewModel.setInputNumber("b2");

        assertFalse(viewModel.isPushBackButtonEnabled());
    }

    @Test
    public void whenDequeIsEmptyPopFrontButtonIsDisabled() {
        viewModel.setInputNumber("4");
        viewModel.getOperationsWithDeque().pushFront();
        viewModel.getOperationsWithDeque().popFront();

        assertFalse(viewModel.isPopFrontButtonEnabled());
    }

    @Test
    public void whenDequeIsEmptyPopBackButtonIsDisabled() {
        viewModel.setInputNumber("8");
        viewModel.getOperationsWithDeque().pushBack();
        viewModel.getOperationsWithDeque().popBack();

        assertFalse(viewModel.isPopBackButtonEnabled());
    }

    @Test
    public void pushFrontButtonAddsIntegersToDequeFront() {
        viewModel.setInputNumber("15");
        viewModel.getOperationsWithDeque().pushFront();
        viewModel.setInputNumber("16");
        viewModel.getOperationsWithDeque().pushFront();

        Deque<Integer> testDeque = new Deque<>();
        testDeque.pushFront(15);
        testDeque.pushFront(16);

        assertArrayEquals(viewModel.getOperationsWithDeque().toArray(), testDeque.toArray());
    }

    @Test
    public void pushBackButtonAddsIntegersToDequeBack() {
        viewModel.setInputNumber("23");
        viewModel.getOperationsWithDeque().pushBack();
        viewModel.setInputNumber("42");
        viewModel.getOperationsWithDeque().pushBack();

        Deque<Integer> testDeque = new Deque<>();
        testDeque.pushBack(23);
        testDeque.pushBack(42);

        assertArrayEquals(viewModel.getOperationsWithDeque().toArray(), testDeque.toArray());
    }

    @Test
     public void popBackButtonReturnsBackInteger() {
        viewModel.setInputNumber("4");
        viewModel.getOperationsWithDeque().pushBack();
        viewModel.setInputNumber("8");
        viewModel.getOperationsWithDeque().pushBack();

        viewModel.getOperationsWithDeque().popBack();

        assertEquals("8", viewModel.getOutput());
    }

    @Test
    public void popFrontButtonReturnsFrontInteger() {
        viewModel.setInputNumber("15");
        viewModel.getOperationsWithDeque().pushFront();
        viewModel.setInputNumber("16");
        viewModel.getOperationsWithDeque().pushFront();

        viewModel.getOperationsWithDeque().popFront();

        assertEquals("16", viewModel.getOutput());
    }

    @Test
    public void clearButtonClearsDeque() {
        viewModel.setInputNumber("4");
        viewModel.getOperationsWithDeque().pushFront();

        viewModel.getOperationsWithDeque().clear();

        assertTrue(viewModel.getOperationsWithDeque().isEmpty());
    }

    @Test
    public void whenDequeIsClearedClearButtonIsDisabled() {
        viewModel.getOperationsWithDeque().clear();

        assertFalse(viewModel.isClearButtonEnabled());
    }

    @Test
    public void whenDequeIsEmptyAfterPopClearButtonIsDisabled() {
        viewModel.setInputNumber("15162342");
        viewModel.getOperationsWithDeque().pushFront();

        viewModel.getOperationsWithDeque().popFront();

        assertFalse(viewModel.isClearButtonEnabled());
    }

    @Test
    public void whenDequeIsClearedPopBackButtonIsDisabled() {
        viewModel.setInputNumber("4");
        viewModel.getOperationsWithDeque().pushFront();

        viewModel.getOperationsWithDeque().clear();

        assertFalse(viewModel.isPopBackButtonEnabled());
    }

    @Test
    public void whenDequeIsClearedPopFrontButtonIsDisabled() {
        viewModel.setInputNumber("4");
        viewModel.getOperationsWithDeque().pushFront();

        viewModel.getOperationsWithDeque().clear();

        assertFalse(viewModel.isPopFrontButtonEnabled());
    }

    @Test
    public void dequeContains4() {
        viewModel.setInputNumber("4");
        viewModel.getOperationsWithDeque().pushFront();

        viewModel.getOperationsWithDeque().contains();

        assertEquals("true", viewModel.getOutput());
    }

    @Test
    public void dequeDoesNotContains8() {
        viewModel.setInputNumber("15");
        viewModel.getOperationsWithDeque().pushFront();
        viewModel.setInputNumber("8");

        viewModel.getOperationsWithDeque().contains();

        assertEquals("false", viewModel.getOutput());
    }

    @Test
    public void whenDequeIsClearedCheckButtonIsDisabled() {
        viewModel.setInputNumber("4");
        viewModel.getOperationsWithDeque().pushFront();

        viewModel.getOperationsWithDeque().clear();

        assertFalse(viewModel.isCheckButtonEnabled());
    }

    @Test
    public void whenDequeIsEmptyAfterPopCheckButtonIsDisabled() {
        viewModel.setInputNumber("15162342");
        viewModel.getOperationsWithDeque().pushFront();

        viewModel.getOperationsWithDeque().popFront();

        assertFalse(viewModel.isCheckButtonEnabled());
    }

    @Test
    public void dequeSizeIs3() {
        viewModel.setInputNumber("4");
        viewModel.getOperationsWithDeque().pushFront();
        viewModel.setInputNumber("8");
        viewModel.getOperationsWithDeque().pushFront();
        viewModel.setInputNumber("15");
        viewModel.getOperationsWithDeque().pushFront();

        assertEquals(3, viewModel.getOperationsWithDeque().getSize());
    }

    @Test
    public void byDefaultDequeIsEmpty() {
        assertTrue(viewModel.getOperationsWithDeque().isEmpty());
    }
}
