package ru.unn.agile.MergeSort.ViewModel;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class MergeSortViewModelTests {
    @Test
    public void isDefaultValueSetAtStart() {
        MergeSortViewModel viewModel = new MergeSortViewModel();

        assertEquals("", viewModel.getSortingArray());
    }

    @Test
    public void isSortButtonDisabledByDefault() {
        MergeSortViewModel viewModel = new MergeSortViewModel();

        assertFalse(viewModel.isSortButtonEnabled());
    }

    @Test
    public void isSortButtonEnabledWhenSourceArrayEntered() {
        MergeSortViewModel viewModel = new MergeSortViewModel();

        viewModel.setSortingArray("1");

        assertTrue(viewModel.isSortButtonEnabled());
    }
}
