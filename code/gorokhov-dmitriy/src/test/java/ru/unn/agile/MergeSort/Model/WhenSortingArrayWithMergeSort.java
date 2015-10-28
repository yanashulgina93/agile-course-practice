/**
 * Created by Dmitriy on 28.10.2015.
 */

import org.junit.Test;
import static org.junit.Assert.assertArrayEquals;

public class WhenSortingArrayWithMergeSort {
    @Test
    public void canSortEmptyArray() {
        Integer[] sortableArray = {};

        MergeSort.sort(sortableArray);

        assertArrayEquals(new Integer[]{}, sortableArray);
    }

    @Test
    public void canSortOneElementArray() {
        Integer[] sortableArray = {1};

        MergeSort.sort(sortableArray);

        assertArrayEquals(new Integer[]{1}, sortableArray);
    }

    @Test
    public void canSortTwoElementsArray() {
        Integer[] sortableArray = {5, 1};

        MergeSort.sort(sortableArray);

        assertArrayEquals(new Integer[]{1, 5}, sortableArray);
    }


    @Test
    public void canSortSmallArray() {
        Integer[] sortableArray = {2, 1, 5, 8, 3};

        MergeSort.sort(sortableArray);

        assertArrayEquals(new Integer[]{1, 2, 3, 5, 8}, sortableArray);
    }

    @Test
    public void canSortArrayWithRepeatedElements() {
        Integer[] sortableArray = {5, 5, 1, 1, 3};

        MergeSort.sort(sortableArray);

        assertArrayEquals(new Integer[]{1, 1, 3, 5, 5}, sortableArray);
    }

    @Test
    public void canSortArrayWithNegativeElements() {
        Integer[] sortableArray = {-1, -6, -3, 0, 6};

        MergeSort.sort(sortableArray);

        assertArrayEquals(new Integer[]{-6, -3, -1, 0, 6}, sortableArray);
    }

    @Test
    public void canSortArrayWithRangeParameters() {
        Integer[] sortableArray = {-1, 6, -1, 4, -6};

        MergeSort.sort(sortableArray, 1, 3);

        assertArrayEquals(new Integer[]{-1, -1, 4, 6, -6}, sortableArray);
    }

    @Test
    public void canSortArrayWithZeroRange() {
        Integer[] sortableArray = {-1, 6, -1, 4, -6};

        MergeSort.sort(sortableArray, 1, 1);

        assertArrayEquals(new Integer[]{-1, 6, -1, 4, -6}, sortableArray);
    }

    @Test
    public void canSortArrayWithNegativeRange() {
        Integer[] sortableArray = {-1, 6, -1, 4, -6};

        MergeSort.sort(sortableArray, -5, -1);

        assertArrayEquals(new Integer[]{-1, 6, -1, 4, -6}, sortableArray);
    }
}
