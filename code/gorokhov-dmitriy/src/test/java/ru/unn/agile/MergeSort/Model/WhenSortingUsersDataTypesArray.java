package ru.unn.agile.MergeSort.Model;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertArrayEquals;

@RunWith(Parameterized.class)
public class WhenSortingUsersDataTypesArray {
    public WhenSortingUsersDataTypesArray(final ComplexNumber[] correctArray,
                                          final ComplexNumber[] sortingArray) {
        this.correctArray = correctArray;
        this.sortingArray = sortingArray;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> testComplexNumberArrays() {
        return Arrays.asList(new Object[][]{
            {
                new ComplexNumber[]{new ComplexNumber(0, 0)},
                new ComplexNumber[]{new ComplexNumber(0, 0)}
            },
            {
                new ComplexNumber[]{new ComplexNumber(0, 1), new ComplexNumber(1, 3),
                                    new ComplexNumber(4, 9), new ComplexNumber(5, 1)},
                new ComplexNumber[]{new ComplexNumber(5, 1), new ComplexNumber(4, 9),
                                    new ComplexNumber(1, 3), new ComplexNumber(0, 1)}
            }
        });
    }

    @Test
    public void canSortComplexNumberArrays() {
        MergeSort.sort(sortingArray);

        assertArrayEquals(correctArray, sortingArray);
    }

    private ComplexNumber[] correctArray;
    private ComplexNumber[] sortingArray;
}

