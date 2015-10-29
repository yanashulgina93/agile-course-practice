package ru.unn.agile.MergeSort.Model;

import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;
import java.util.Random;

import static org.junit.Assert.assertArrayEquals;

@RunWith(Enclosed.class)
public class WhenSortingPrimitiveDataTypesArray {
    @RunWith(Parameterized.class)
    public static class MergeSortTestWithSmallIntegerArrays {
        public MergeSortTestWithSmallIntegerArrays(final Integer[] correctArray,
                                                   final Integer[] sortingArray) {
            this.correctArray = correctArray;
            this.sortingArray = sortingArray;
        }

        @Parameterized.Parameters
        public static Collection<Object[]> testSmallIntegerArrays() {
            return Arrays.asList(new Object[][]{
                    {new Integer[]{}, new Integer[]{}},
                    {new Integer[]{1}, new Integer[]{1}},
                    {new Integer[]{1, 5}, new Integer[]{5, 1}},
                    {new Integer[]{1, 2, 3, 5, 8}, new Integer[]{2, 1, 5, 8, 3}},
                    {new Integer[]{1, 1, 3, 5, 5}, new Integer[]{5, 5, 1, 1, 3}},
                    {new Integer[]{-6, -3, -1, 0, 6}, new Integer[]{-1, -6, -3, 0, 6}}
            });
        }

        @Test
        public void canSortSmallIntegerArrays() {
            MergeSort.sort(sortingArray);

            assertArrayEquals(correctArray, sortingArray);
        }

        private Integer[] correctArray;
        private Integer[] sortingArray;
    }

    @RunWith(Parameterized.class)
    public static class MergeSortRangeTestWithSmallIntegerArrays {
        public MergeSortRangeTestWithSmallIntegerArrays(final Integer[] correctArray,
                                                        final Integer[] sortingArray,
                                                        final int fromIndex,
                                                        final int toIndex) {
            this.correctArray = correctArray;
            this.sortingArray = sortingArray;
            this.fromIndex = fromIndex;
            this.toIndex = toIndex;
        }

        @Parameterized.Parameters
        public static Collection<Object[]> testSmallIntegerArraysWithRange() {
            return Arrays.asList(new Object[][]{
                    {new Integer[]{-1, -1, 4, 6, -6}, new Integer[]{-1, 6, -1, 4, -6}, 1, 3},
                    {new Integer[]{-1, 6, -1, 4, -6}, new Integer[]{-1, 6, -1, 4, -6}, 1, 1},
                    {new Integer[]{-1, 6, -1, 4, -6}, new Integer[]{-1, 6, -1, 4, -6}, -5, -1}
            });
        }

        @Test
        public void canSortSmallIntegerArraysWithRange() {
            MergeSort.sort(sortingArray, fromIndex, toIndex);

            assertArrayEquals(correctArray, sortingArray);
        }

        private Integer[] correctArray;
        private Integer[] sortingArray;
        private int fromIndex;
        private int toIndex;
    }

    public static class MergeSortSpecialTests {
        @Test
        public void canSortFloatArray() {
            Float[] sortingArray = {5.4f, 8.1f, 1.3f, 2.0f, 3.5f};

            MergeSort.sort(sortingArray);

            assertArrayEquals(new Float[]{1.3f, 2.0f, 3.5f, 5.4f, 8.1f}, sortingArray);
        }

        @Test
        public void canSortDoubleArray() {
            Double[] sortingArray = {8.6, 9.0, 1.1, 5.0, 4.9};

            MergeSort.sort(sortingArray);

            assertArrayEquals(new Double[]{1.1, 4.9, 5.0, 8.6, 9.0}, sortingArray);
        }

        @Test
        public void canSortBigRandomIntegerArray() {
            int arraySize = 10000;
            int randomValue = 0;
            Random randomGenerator = new Random();
            Integer[] sortingArray = new Integer[arraySize];
            Integer[] correctArray = new Integer[arraySize];
            for (int i = 0; i < arraySize; i++) {
                randomValue = randomGenerator.nextInt();
                correctArray[i] = randomValue;
                sortingArray[i] = randomValue;
            }

            Arrays.sort(correctArray);
            MergeSort.sort(sortingArray);

            assertArrayEquals(correctArray, sortingArray);
        }

        @Test
        public void canSortBigRandomDoubleArray() {
            int arraySize = 10000;
            double randomValue = 0;
            Random randomGenerator = new Random();
            Double[] sortingArray = new Double[arraySize];
            Double[] correctArray = new Double[arraySize];
            for (int i = 0; i < arraySize; i++) {
                randomValue = randomGenerator.nextDouble();
                correctArray[i] = randomValue;
                sortingArray[i] = randomValue;
            }

            Arrays.sort(correctArray);
            MergeSort.sort(sortingArray);

            assertArrayEquals(correctArray, sortingArray);
        }
    }
}
