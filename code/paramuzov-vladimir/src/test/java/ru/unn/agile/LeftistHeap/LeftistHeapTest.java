package ru.unn.agile.LeftistHeap;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Arrays;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(JUnitParamsRunner.class)
public class LeftistHeapTest {
    @Before
    public void initializeHeaps(){
        firstHeap = new LeftistHeap<>();
        secondHeap = new LeftistHeap<>();
    }
    @Test
    public void canCreateEmptyLeftistHeap(){
        setUpHeap(firstHeap, 0);

        assertTrue(firstHeap.isEmpty());
    }
    @Test
    public void heapWorkProperlyWithDates(){
        LeftistHeap<Date> dateHeap = new LeftistHeap<>();
        Date[] dateArray = {new Date(1245624241), new Date(2132524677)};

        dateHeap.insert(dateArray[0]);
        dateHeap.insert(dateArray[1]);

        assertTrue(Arrays.equals(dateArray,dateHeap.toSortedArray()));
    }
    @Test
    @Parameters({"0,0,0","0,1,1","1,0,1","1,1,2","3,5,8","250,260,510"})
    public void canMergeTwoHeaps(int firstHeapSize, int secondHeapSize, int resultHeapSize){
        setUpTwoHeaps(firstHeapSize, secondHeapSize);

        firstHeap.merge(secondHeap);

        assertEquals(resultHeapSize,firstHeap.getSize());
    }
    @Test
    @Parameters ({"0,1","1,2","10,11"})
    public void canInsertOneComparableElementToHeap(int startHeapSize, int resultHeapSize){
        setUpHeap(firstHeap, startHeapSize);

        firstHeap.insert(heapElement);

        assertEquals(resultHeapSize, firstHeap.getSize());
    }
    @Test
    public void createdHeapContainProperInformation(){
        setUpHeap(firstHeap,5);
        Integer[] arrayOfValues = {0,1,2,3,4};

        assertTrue(Arrays.equals(arrayOfValues, firstHeap.toSortedArray()));
    }
    @Test
    @Parameters(method = "deleteFromHeapSizesAndResults")
    public void canDeleteMinElementFromHeap(int elementsInHeap, Integer returnValue){
        setUpHeap(firstHeap, elementsInHeap);

        Integer deleteResult = firstHeap.extractMin();

        assertEquals(returnValue, deleteResult);
    }
    @Test
    public void canClearNotEmptyHeap(){
        setUpHeap(firstHeap, 5);

        firstHeap.clear();

        assertEmptyHeap(firstHeap);
    }
    @Test
    public void canDeleteNotRootElementFromNotEmptyHeap(){
        setUpHeap(firstHeap, 10);

        firstHeap.delete(firstHeap.getRoot().rightChild);

        assertEquals(9, firstHeap.getSize());
    }
    @Test
    @Parameters({"1","2","3","10"})
    public void canDecreaseKeyOfRootElement(int heapSize){
        setUpHeap(firstHeap, heapSize);

        firstHeap.decreaseKey(firstHeap.getRoot(),smallestKey);

        assertEquals(smallestKey, firstHeap.getRoot().element);
    }
    @Test
    @Parameters({"1","2","3","10"})
    public void canDecreaseKeyOfNotRootElement(int heapSize){
        setUpHeap(firstHeap, heapSize);
        LeftistHeapNode<Integer> child = getHeapElementFromMiddle(heapSize);

        firstHeap.decreaseKey(child, smallestKey);

        assertEquals(smallestKey,firstHeap.getRoot().element);
    }
    @Test
    public void IsParentFieldInNodeSetsCorrect(){
        setUpHeap(firstHeap, 10);

        LeftistHeapNode<Integer> child = firstHeap.getRoot().leftChild;

        assertEquals(child.parent,firstHeap.getRoot());
    }
    @SuppressWarnings("unused")
    private Object[] deleteFromHeapSizesAndResults(){
        return new Object[]{
                new Object[]{0, null},
                new Object[]{1,0},
                new Object[]{5,0}
        };
    }
    private LeftistHeapNode<Integer> getHeapElementFromMiddle(int heapSize){
        LeftistHeapNode<Integer> child = firstHeap.getRoot();
        for (int i = 0; i < heapSize/2; i++) {
            if(child.leftChild != null)
                child = child.leftChild;
            else if(child.rightChild != null)
                child = child.rightChild;
            else
                break;
        }
        return child;
    }
    private void assertEmptyHeap(LeftistHeap<Integer> heap){
        assertTrue(heap.isEmpty());
        assertEquals(0, heap.getSize());
    }
    private void setUpHeap(LeftistHeap heap, int numberOfElements){
        heap.clear();
        for(int i = 0; i < numberOfElements; i++)
            heap.insert(i);
    }
    private void setUpTwoHeaps(int elementsInFirstHeap, int elementsInSecondHeap){
        setUpHeap(firstHeap, elementsInFirstHeap);
        setUpHeap(secondHeap, elementsInSecondHeap);
    }
    private static final Integer heapElement = 10;
    private LeftistHeap<Integer> firstHeap;
    private LeftistHeap<Integer> secondHeap;
    private static final Integer smallestKey = -1;
}
