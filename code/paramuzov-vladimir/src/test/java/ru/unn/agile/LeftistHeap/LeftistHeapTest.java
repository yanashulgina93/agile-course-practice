package ru.unn.agile.LeftistHeap;

import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(Enclosed.class)
public class LeftistHeapTest {
    @RunWith(Parameterized.class)
    public static class WhenMergingTwoHeaps {
        public WhenMergingTwoHeaps(final int firstHeapSize,
                                   final int secondHeapSize,
                                   final int resultHeapSize) {
            this.firstHeapSize = firstHeapSize;
            this.secondHeapSize = secondHeapSize;
            this.resultHeapSize = resultHeapSize;
        }

        @Parameterized.Parameters
        public static Collection<Object[]> getHeapSizes() {
            return Arrays.asList(new Object[][]{
                    {0, 0, 0},
                    {0, 1, 1},
                    {1, 0, 1},
                    {1, 1, 2},
                    {3, 5, 8},
                    {250, 260, 510}
            });
        }

        @Test
        public void canMergeTwoHeaps() {
            firstHeap = new LeftistHeap<>();
            secondHeap = new LeftistHeap<>();
            setUpHeap(firstHeap, firstHeapSize);
            setUpHeap(secondHeap, secondHeapSize);

            firstHeap.merge(secondHeap);

            assertEquals(resultHeapSize, firstHeap.getSize());
        }

        private int firstHeapSize;
        private int secondHeapSize;
        private int resultHeapSize;
        private LeftistHeap<Integer> firstHeap;
        private LeftistHeap<Integer> secondHeap;
    }

    @RunWith(Parameterized.class)
    public static class WhenInsertElementToHeap {
        public WhenInsertElementToHeap(final int startHeapSize, final int resultHeapSize) {
            this.startHeapSize = startHeapSize;
            this.resultHeapSize = resultHeapSize;
        }

        @Parameterized.Parameters
        public static Collection<Object[]> getHeapSizes() {
            return Arrays.asList(new Object[][]{
                    {0, 1},
                    {1, 2},
                    {10, 11},
                    {150, 151}
            });
        }

        @Test
        public void canMergeTwoHeaps() {
            heap = new LeftistHeap<>();
            setUpHeap(heap, startHeapSize);

            heap.insert(heapElementToInsert);

            assertEquals(resultHeapSize, heap.getSize());
        }

        private final Integer heapElementToInsert = 10;
        private int startHeapSize;
        private int resultHeapSize;
        private LeftistHeap<Integer> heap;
    }

    @RunWith(Parameterized.class)
    public static class WhenDecreasingKeyOfElement {
        public WhenDecreasingKeyOfElement(final int heapSize, final Integer targetKey) {
            this.heapSize = heapSize;
            this.targetKey = targetKey;
            heap = new LeftistHeap<>();
        }

        @Parameterized.Parameters
        public static Collection<Object[]> getHeapSizesAndKeys() {
            return Arrays.asList(new Object[][]{
                    {1, -1},
                    {10, -5},
                    {100, 0},
                    {150, 0}
            });
        }

        @Test
        public void canDecreaseKeyOfRootElement() {
            setUpHeap(heap, heapSize);

            heap.decreaseKey(heap.getRoot(), targetKey);

            assertEquals(targetKey, heap.getRoot().getElement());
        }

        @Test
        public void canDecreaseKeyOfNotRootElement() {
            setUpHeap(heap, heapSize);
            LeftistHeapNode<Integer> child = getHeapElementFromMiddle();

            heap.decreaseKey(child, targetKey);

            assertEquals(targetKey, heap.getRoot().getElement());
        }

        private LeftistHeapNode<Integer> getHeapElementFromMiddle() {
            LeftistHeapNode<Integer> child = heap.getRoot();
            for (int i = 0; i < heapSize / 2; i++) {
                if (child.hasLeftChild()) {
                    child = child.getLeftChild();
                } else {
                    if (child.hasRightChild()) {
                        child = child.getRightChild();
                    } else {
                        break;
                    }
                }
            }
            return child;
        }
        private int heapSize;
        private Integer targetKey;
        private LeftistHeap<Integer> heap;
    }

    @RunWith(Parameterized.class)
    public static class WhenDeleteRootElementFromHeap {
        public WhenDeleteRootElementFromHeap(final int elementsInHeap,
                                             final Integer returnValue) {
            this.elementsInHeap = elementsInHeap;
            this.returnValue = returnValue;
            heap = new LeftistHeap<>();
        }

        @Parameterized.Parameters
        public static Collection<Object[]> getHeapSizesAndKeys() {
            return Arrays.asList(new Object[][]{
                    {0, null},
                    {1, 0},
                    {5, 0},
                    {150, 0}
            });
        }

        @Test
        public void canDeleteRootElementFromHeap() {
            setUpHeap(heap, elementsInHeap);

            Integer deleteResult = heap.extractMin();

            assertEquals(returnValue, deleteResult);
        }

        private int elementsInHeap;
        private Integer returnValue;
        private LeftistHeap<Integer> heap;
    }

    public static class LeftistHeapSimpleTests {
        public LeftistHeapSimpleTests() {
            heap = new LeftistHeap<>();
        }

        @Test
        public void canCreateEmptyLeftistHeap() {
            setUpHeap(heap, 0);

            assertTrue(heap.isEmpty());
        }

        @Test
        public void heapWorkProperlyWithDates() {
            LeftistHeap<Date> dateHeap = new LeftistHeap<>();
            Date[] dateArray = {new Date(1245624241), new Date(2132524677)};

            dateHeap.insert(dateArray[0]);
            dateHeap.insert(dateArray[1]);

            assertArrayEquals(dateArray, dateHeap.toSortedArray());
        }

        @Test
        public void createdHeapContainProperInformation() {
            setUpHeap(heap, 5);
            Integer[] correctValues = {0, 1, 2, 3, 4};

            assertArrayEquals(correctValues, heap.toSortedArray());
        }

        @Test
        public void canClearNotEmptyHeap() {
            setUpHeap(heap, 5);

            heap.clear();

            assertTrue(heap.isEmpty());
        }

        @Test
        public void isParentFieldInNodeSetsCorrect() {
            setUpHeap(heap, 10);

            LeftistHeapNode<Integer> child = heap.getRoot().getLeftChild();

            assertEquals(child.getParent(), heap.getRoot());
        }

        @Test
        public void canDeleteNotRootElementFromNotEmptyHeap() {
            int elementsInHeap = 5;
            setUpHeap(heap, elementsInHeap);

            heap.delete(heap.getRoot().getRightChild());

            assertEquals(elementsInHeap - 1, heap.getSize());
        }

        private LeftistHeap<Integer> heap;
    }

    private static void setUpHeap(final LeftistHeap<Integer> heap, final int numberOfElements) {
        heap.clear();
        for (int i = 0; i < numberOfElements; i++) {
            heap.insert(i);
        }
    }
}
