package ru.unn.agile.LeftistHeap.viewmodel;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static ru.unn.agile.LeftistHeap.viewmodel.LeftistHeapRegexMatcher.matches;

public class LeftistHeapViewModelTest {
    private LeftistHeapViewModel viewModel;

    @Before
    public void setUp() {
        FakeLeftistHeapLogger fakeLogger = new FakeLeftistHeapLogger();
        viewModel = new LeftistHeapViewModel(fakeLogger);
    }

    @Test
    public void byDefaultApplyButtonIsDisabled() {
        assertFalse(viewModel.isApplyButtonEnabled());
    }

    @Test
    public void whenInputIntegerToKeyValueFieldInsertButtonIsEnabled() {
        viewModel.setKeyValue("1");

        assertTrue(viewModel.isApplyButtonEnabled());
    }

    @Test
    public void whenClearKeyValueFieldApplyButtonIsDisabled() {
        viewModel.setKeyValue("1");
        viewModel.setKeyValue("");

        assertFalse(viewModel.isApplyButtonEnabled());
    }

    @Test
    public void whenInsertOneIntegerToHeapContentDisplayed() {
        viewModel.setKeyValue("1");
        viewModel.setOperation(LeftistHeapViewModel.Operations.INSERT);
        viewModel.applyOperation();

        assertEquals("[1]", viewModel.getHeapContent());
    }

    @Test
    public void whenInsertTwoIntegersToHeapContentDisplayed() {
        viewModel.setOperation(LeftistHeapViewModel.Operations.INSERT);
        viewModel.setKeyValue("1");
        viewModel.applyOperation();
        viewModel.setKeyValue("2");
        viewModel.applyOperation();

        assertEquals("[1, 2]", viewModel.getHeapContent());
    }

    @Test
    public void whenInputWrongKeyValueErrorDisplayed() {
        viewModel.setKeyValue("afc");

        assertEquals(LeftistHeapViewModel.Errors.FIELD_BAD_FORMAT.getMessage(),
                     viewModel.getErrorText());
    }

    @Test
    public void whenInputWrongValueToKeyFieldApplyButtonIsDisabled() {
        viewModel.setKeyValue("afc");

        assertFalse(viewModel.isApplyButtonEnabled());
    }

    @Test
    public void byDefaultOperationIsInsert() {
        assertEquals(LeftistHeapViewModel.Operations.INSERT.toString(),
                     viewModel.getOperation().toString());
    }

    @Test
    public void canChangeOperation() {
        viewModel.setOperation(LeftistHeapViewModel.Operations.DELETE);

        assertEquals(LeftistHeapViewModel.Operations.DELETE, viewModel.getOperation());
    }

    @Test
    public void whenTryingToDeleteNotExistingElementErrorDisplayed() {
        viewModel.setKeyValue("1");
        viewModel.setOperation(LeftistHeapViewModel.Operations.DELETE);
        viewModel.applyOperation();

        assertEquals(LeftistHeapViewModel.Errors.VALUE_TO_DELETE_NOT_FOUND.getMessage(),
                     viewModel.getErrorText());
    }

    @Test
    public void canDeleteExistingElementFromHeap() {
        viewModel.setOperation(LeftistHeapViewModel.Operations.INSERT);
        viewModel.setKeyValue("1");
        viewModel.applyOperation();
        viewModel.setOperation(LeftistHeapViewModel.Operations.DELETE);
        viewModel.applyOperation();

        assertEquals("[]", viewModel.getHeapContent());
    }

    @Test
    public void whenFixKeyValueFormatErrorHide() {
        viewModel.setKeyValue("afc");
        viewModel.setKeyValue("1");

        assertEquals("", viewModel.getErrorText());
    }

    @Test
    public void canCreateLeftistHeapViewModelWithLogger() {
        FakeLeftistHeapLogger fakeLogger = new FakeLeftistHeapLogger();

        LeftistHeapViewModel viewModel = new LeftistHeapViewModel(fakeLogger);

        assertNotNull(viewModel);
    }

    @Test(expected = NullPointerException.class)
    public void canNotCreateLeftistHeapViewModelWithNullLogger() {
        new LeftistHeapViewModel(null);
    }

    @Test
    public void byDefaultLogIsEmpty() {
        assertEquals(0, viewModel.getLogger().getSize());
    }

    @Test
    public void isEditingKeyValueFieldAddNewMessageToLog() {
        viewModel.setKeyValue("10");

        viewModel.valueFieldFocusLost();

        assertEquals(1, viewModel.getLogger().getSize());
    }

    @Test
    public void isLogContainProperMessageAfterKeyValueFieldEdited() {
        viewModel.setKeyValue("10");

        viewModel.valueFieldFocusLost();
        String logMessage = viewModel.getLogger().getLogMessage(0);

        assertThat(logMessage,
                matches(LeftistHeapViewModel.LogMessages.KEY_VALUE_FIELD_CHANGED.getMessage()
                + "\\d+|-\\d+"));
    }

    @Test
    public void isPressingApplyOperationButtonAddNewMessageToLog() {
        viewModel.setKeyValue("10");

        viewModel.applyOperation();

        assertEquals(1, viewModel.getLogger().getSize());
    }

    @Test
    public void isLogContainProperMessageAfterApplyInsertOperation() {
        viewModel.setKeyValue("10");

        viewModel.applyOperation();
        String logMessage = viewModel.getLogger().getLogMessage(0);

        assertThat(logMessage,
                matches(LeftistHeapViewModel.LogMessages.BUTTON_PRESSED.getMessage()
                        + "Operation: " + LeftistHeapViewModel.Operations.INSERT.toString()
                        + "; Value: \\d+|-\\d+"));
    }

    @Test
    public void isChangingOperationAddNewMessageToLog() {
        viewModel.setOperation(LeftistHeapViewModel.Operations.DELETE);

        assertEquals(1, viewModel.getLogger().getSize());
    }

    @Test
    public void isLogContainProperMessageAfterChangeOperationToDelete() {
        viewModel.setOperation(LeftistHeapViewModel.Operations.DELETE);
        String logMessage = viewModel.getLogger().getLogMessage(0);

        assertThat(logMessage,
                matches(LeftistHeapViewModel.LogMessages.OPERATION_CHANGED.getMessage()
                        + "`" + LeftistHeapViewModel.Operations.DELETE.toString() + "`"));
    }
}
