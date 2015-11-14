package ru.unn.agile.MergeSort.ViewModel;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class MergeSortViewModelTests {
    @Before
    public void setUp() {
        viewModel = new MergeSortViewModel();
    }

    @Test
    public void isDefaultValueSetAtStart() {
        assertEquals("", viewModel.getSortingArray());
    }

    @Test
    public void isSortButtonDisabledByDefault() {
        assertFalse(viewModel.isSortButtonEnabled());
    }

    @Test
    public void isSortButtonEnabledWhenSourceArrayEntered() {
        viewModel.setSortingArray("0");

        assertTrue(viewModel.isSortButtonEnabled());
    }

    @Test
    public void isSortButtonDisabledWhenSourceArrayCleared() {
        viewModel.setSortingArray("0");
        viewModel.setSortingArray("");

        assertFalse(viewModel.isSortButtonEnabled());
    }

    @Test
    public void isSortButtonDisabledWhenInvalidSourceArrayEntered() {
        viewModel.setSortingArray("5 3 tg");

        assertFalse(viewModel.isSortButtonEnabled());
    }

    @Test
    public void isSortButtonEnabledWhenSourceArrayCorrected() {
        viewModel.setSortingArray("5 3 tg");
        viewModel.setSortingArray("5 3 7");

        assertTrue(viewModel.isSortButtonEnabled());
    }

    @Test
    public void isSortingOrderSetAscendingByDefault() {
        assertEquals(MergeSortViewModel.SortingOrder.ASCENDING, viewModel.getSortingOrder());
    }

    @Test
    public void canConvertSortingOrderToString() {
        assertEquals("Ascending", viewModel.getSortingOrder().toString());
    }

    @Test
    public void canChangeSortingOrder() {
        viewModel.setSortingOrder(MergeSortViewModel.SortingOrder.DESCENDING);

        assertEquals(MergeSortViewModel.SortingOrder.DESCENDING, viewModel.getSortingOrder());
    }

    @Test
    public void isResultArrayEmptyWhenInvalidSourceArrayEntered() {
        viewModel.setSortingArray("we fd 132");

        assertEquals("", viewModel.getSortingArray());
    }

    @Test
    public void isResultArrayCorrectWhenSortingIntegerSourceArray() {
        viewModel.setSortingArray("34 -43 11 43 34");
        viewModel.sort();

        assertEquals("-43.0 11.0 34.0 34.0 43.0", viewModel.getSortingArray());
    }

    @Test
    public void isResultArrayCorrectWhenSortingFloatingPointSourceArray() {
        viewModel.setSortingArray("34.5 -43.2 11.2 43.4 34.1");
        viewModel.sort();

        assertEquals("-43.2 11.2 34.1 34.5 43.4", viewModel.getSortingArray());
    }

    @Test
    public void isResultArrayCorrectWhenSortingSourceArrayByDescendingOrder() {
        viewModel.setSortingArray("5 -3 -3 0 1");
        viewModel.setSortingOrder(MergeSortViewModel.SortingOrder.DESCENDING);
        viewModel.sort();

        assertEquals("5.0 1.0 0.0 -3.0 -3.0", viewModel.getSortingArray());
    }

    @Test
    public void isSourceArrayStatusSetEmptyByDefault() {
        assertEquals(MergeSortViewModel.SortingArrayStatus.EMPTY.toString(),
                     viewModel.getSortingArrayStatus());
    }

    @Test
    public void isSourceArrayStatusSetWrongFormatWhenSourceArrayInvalid() {
        viewModel.setSortingArray("12 ds");

        assertEquals(MergeSortViewModel.SortingArrayStatus.WRONG_FORMAT.toString(),
                     viewModel.getSortingArrayStatus());
    }

    @Test
    public void isSourceArrayStatusSetValidFormatWhenSourceArrayCorrect() {
        viewModel.setSortingArray("56.2 123.1");

        assertEquals(MergeSortViewModel.SortingArrayStatus.VALID_FORMAT.toString(),
                     viewModel.getSortingArrayStatus());
    }

    private MergeSortViewModel viewModel;
}
