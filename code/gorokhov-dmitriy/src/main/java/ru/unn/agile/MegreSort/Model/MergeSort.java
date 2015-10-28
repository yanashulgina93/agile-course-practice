/**
 * Created by Dmitriy on 28.10.2015.
 */

final class MergeSort {
    public static void sort(final Integer[] sortingArray) {
            Integer[] bufferArray = new Integer[sortingArray.length];

            mergeSort(sortingArray, bufferArray, 0, sortingArray.length - 1);
    }

    public static void sort(final Integer[] sortingArray, final int fromIndex, final int toIndex) {
        if (isSortingRangeBiggerOneElement(fromIndex, toIndex)) {
            Integer[] bufferArray = new Integer[sortingArray.length];

            mergeSort(sortingArray, bufferArray, fromIndex, toIndex);
        }
    }

    private static boolean isSortingRangeBiggerOneElement(final int fromIndex, final int toIndex) {
        if (fromIndex >= 0 && toIndex >= 0) {
            return fromIndex < toIndex;
        } else {
            return false;
        }
    }

    private static void mergeSort(final Integer[] sortingArray, final Integer[] bufferArray,
                                  final int fromIndex, final int toIndex) {
        if (isSortingRangeBiggerOneElement(fromIndex, toIndex)) {
            int middleIndex = fromIndex + (toIndex - fromIndex) / 2;

            mergeSort(sortingArray, bufferArray, fromIndex, middleIndex);
            mergeSort(sortingArray, bufferArray, middleIndex + 1, toIndex);

            merge(sortingArray, bufferArray, fromIndex, middleIndex + 1, toIndex);
        }
    }

    private static void merge(final Integer[] sortingArray, final Integer[] bufferArray,
                              final int leftStartIndex, final int rightStartIndex,
                              final int rightEndIndex) {
        int leftEndIndex = rightStartIndex - 1;
        int currentLeftPos = leftStartIndex;
        int currentRightPos = rightStartIndex;
        int currentBufferPos = leftStartIndex;

        while (currentLeftPos <= leftEndIndex && currentRightPos <= rightEndIndex) {
            if (sortingArray[currentLeftPos].compareTo(sortingArray[currentRightPos]) <= 0) {
                bufferArray[currentBufferPos++] = sortingArray[currentLeftPos++];
            } else {
                bufferArray[currentBufferPos++] = sortingArray[currentRightPos++];
            }
        }

        while (currentLeftPos <= leftEndIndex) {
            bufferArray[currentBufferPos++] = sortingArray[currentLeftPos++];
        }

        while (currentRightPos <= rightEndIndex) {
            bufferArray[currentBufferPos++] = sortingArray[currentRightPos++];
        }

        System.arraycopy(bufferArray, leftStartIndex, sortingArray, leftStartIndex,
                         rightEndIndex + 1 - leftStartIndex);
    }

    private MergeSort() {
    }
}
