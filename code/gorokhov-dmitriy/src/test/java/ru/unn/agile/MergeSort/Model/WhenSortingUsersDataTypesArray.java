/**
 * Created by Dmitriy on 29.10.2015.
 */

import org.junit.Test;
import static org.junit.Assert.assertArrayEquals;

public class WhenSortingUsersDataTypesArray {
    @Test
    public void canSortOneElementArray() {
        ComplexNumber[] sortableArray = {new ComplexNumber(0, 0)};

        MergeSort.sort(sortableArray);

        assertArrayEquals(new ComplexNumber[]{new ComplexNumber(0, 0)}, sortableArray);
    }

    @Test
    public void canSortTwoElementsArray() {
        ComplexNumber[] sortableArray = {new ComplexNumber(5, 4),
                                         new ComplexNumber(5, 1)};

        MergeSort.sort(sortableArray);

        assertArrayEquals(new ComplexNumber[]{new ComplexNumber(5, 1),
                                              new ComplexNumber(5, 4)}, sortableArray);
    }
}
