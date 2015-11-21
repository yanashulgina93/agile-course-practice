package ru.unn.agile.Deque.viewmodel;

import org.junit.Before;
import org.junit.Test;
import ru.unn.agile.Deque.model.Deque;
import ru.unn.agile.Deque.viewmodel.DequeViewModel.Action;

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
        viewModel.setAction(Action.PushFront);
        viewModel.doAction();

        assertTrue(viewModel.isPopFrontButtonEnabled());
    }

    @Test
    public void whenDequeIsNotEmptyPopBackButtonIsEnabled() {
        viewModel.setInputNumber("16");
        viewModel.setAction(Action.PushBack);
        viewModel.doAction();

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
        viewModel.setAction(Action.PushFront);
        viewModel.doAction();
        viewModel.setAction(Action.PopFront);
        viewModel.doAction();

        assertFalse(viewModel.isPopFrontButtonEnabled());
    }

    @Test
    public void whenDequeIsEmptyPopBackButtonIsDisabled() {
        viewModel.setInputNumber("8");
        viewModel.setAction(Action.PushBack);
        viewModel.doAction();
        viewModel.setAction(Action.PopBack);
        viewModel.doAction();

        assertFalse(viewModel.isPopBackButtonEnabled());
    }

    @Test
    public void pushFrontButtonAddsIntegersToDequeFront() {
        viewModel.setInputNumber("15");
        viewModel.setAction(Action.PushFront);
        viewModel.doAction();
        viewModel.setInputNumber("16");
        viewModel.doAction();

        Deque<Integer> testDeque = new Deque<>();
        testDeque.pushFront(15);
        testDeque.pushFront(16);

        assertArrayEquals(viewModel.getOperationsWithDeque().toArray(), testDeque.toArray());
    }

    @Test
    public void pushBackButtonAddsIntegersToDequeBack() {
        viewModel.setInputNumber("23");
        viewModel.setAction(Action.PushBack);
        viewModel.doAction();
        viewModel.setInputNumber("42");
        viewModel.doAction();

        Deque<Integer> testDeque = new Deque<>();
        testDeque.pushBack(23);
        testDeque.pushBack(42);

        assertArrayEquals(viewModel.getOperationsWithDeque().toArray(), testDeque.toArray());
    }

    @Test
     public void popBackButtonReturnsBackInteger() {
        viewModel.setInputNumber("4");
        viewModel.setAction(Action.PushBack);
        viewModel.doAction();
        viewModel.setInputNumber("8");
        viewModel.doAction();

        viewModel.setAction(Action.PopBack);
        viewModel.doAction();

        assertEquals("8", viewModel.getOutput());
    }

    @Test
    public void popFrontButtonReturnsFrontInteger() {
        viewModel.setInputNumber("15");
        viewModel.setAction(Action.PushFront);
        viewModel.doAction();
        viewModel.setInputNumber("16");
        viewModel.doAction();

        viewModel.setAction(Action.PopFront);
        viewModel.doAction();

        assertEquals("16", viewModel.getOutput());
    }

    @Test
    public void clearButtonClearsDeque() {
        viewModel.setInputNumber("4");
        viewModel.setAction(Action.PushFront);
        viewModel.doAction();

        viewModel.setAction(Action.Clear);
        viewModel.doAction();

        assertTrue(viewModel.getOperationsWithDeque().isEmpty());
    }

    @Test
    public void whenDequeIsClearedClearButtonIsDisabled() {
        viewModel.setAction(Action.Clear);
        viewModel.doAction();

        assertFalse(viewModel.isClearButtonEnabled());
    }

    @Test
    public void whenDequeIsEmptyAfterPopClearButtonIsDisabled() {
        viewModel.setInputNumber("15162342");
        viewModel.setAction(Action.PushFront);
        viewModel.doAction();

        viewModel.setAction(Action.PopFront);
        viewModel.doAction();

        assertFalse(viewModel.isClearButtonEnabled());
    }

    @Test
    public void whenDequeIsClearedPopBackButtonIsDisabled() {
        viewModel.setInputNumber("4");
        viewModel.setAction(Action.PushFront);
        viewModel.doAction();

        viewModel.setAction(Action.Clear);
        viewModel.doAction();

        assertFalse(viewModel.isPopBackButtonEnabled());
    }

    @Test
    public void whenDequeIsClearedPopFrontButtonIsDisabled() {
        viewModel.setInputNumber("4");
        viewModel.setAction(Action.PushFront);
        viewModel.doAction();

        viewModel.setAction(Action.Clear);
        viewModel.doAction();

        assertFalse(viewModel.isPopFrontButtonEnabled());
    }

    @Test
    public void dequeContains4() {
        viewModel.setInputNumber("4");
        viewModel.setAction(Action.PushFront);
        viewModel.doAction();

        viewModel.setAction(Action.Check_if_contains);
        viewModel.doAction();

        assertEquals("true", viewModel.getOutput());
    }

    @Test
    public void dequeDoesNotContains8() {
        viewModel.setInputNumber("15");
        viewModel.setAction(Action.PushFront);
        viewModel.doAction();
        viewModel.setInputNumber("8");

        viewModel.setAction(Action.Check_if_contains);
        viewModel.doAction();

        assertEquals("false", viewModel.getOutput());
    }

    @Test
    public void whenDequeIsClearedCheckButtonIsDisabled() {
        viewModel.setInputNumber("4");
        viewModel.setAction(Action.PushFront);
        viewModel.doAction();

        viewModel.setAction(Action.Clear);
        viewModel.doAction();

        assertFalse(viewModel.isCheckButtonEnabled());
    }

    @Test
    public void whenDequeIsEmptyAfterPopCheckButtonIsDisabled() {
        viewModel.setInputNumber("15162342");
        viewModel.setAction(Action.PushFront);
        viewModel.doAction();

        viewModel.setAction(Action.PopFront);
        viewModel.doAction();

        assertFalse(viewModel.isCheckButtonEnabled());
    }

    @Test
    public void dequeSizeIs3() {
        viewModel.setInputNumber("4");
        viewModel.setAction(Action.PushFront);
        viewModel.doAction();
        viewModel.setInputNumber("8");
        viewModel.doAction();
        viewModel.setInputNumber("15");
        viewModel.doAction();

        assertEquals(3, viewModel.getOperationsWithDeque().getSize());
    }

    @Test
    public void byDefaultDequeIsEmpty() {
        assertTrue(viewModel.getOperationsWithDeque().isEmpty());
    }

    @Test
    public void byDefaultDoActionButtonIsDisabled() {
        assertFalse(viewModel.isDoActionButtonEnabled());
    }

    @Test
    public void whenPushBackIsSelectedActionIsPushBack() {
        viewModel.setAction(Action.PushBack);

        assertEquals(Action.PushBack, viewModel.getAction());
    }

    @Test
    public void whenPopFrontIsSelectedActionIsPopFront() {
        viewModel.setAction(Action.PopFront);

        assertEquals(Action.PopFront, viewModel.getAction());
    }

    @Test
    public void whenPopBackIsSelectedActionIsPopBack() {
        viewModel.setAction(Action.PopBack);

        assertEquals(Action.PopBack, viewModel.getAction());
    }

    @Test
    public void whenClearIsSelectedActionIsClear() {
        viewModel.setAction(Action.Clear);

        assertEquals(Action.Clear, viewModel.getAction());
    }

    @Test
    public void whenCheckIsSelectedActionIsCheck() {
        viewModel.setAction(Action.Check_if_contains);

        assertEquals(Action.Check_if_contains, viewModel.getAction());
    }

    @Test
    public void whenActionIndexIs1ActionIsPushBack() {
        viewModel.setAction(1);

        assertEquals(Action.PushBack, viewModel.getAction());
    }

    @Test
    public void whenActionIndexIs3ActionIsPopBack() {
        viewModel.setAction(3);

        assertEquals(Action.PopBack, viewModel.getAction());
    }
}
