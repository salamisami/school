package praktikum.aufgabe2;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import static org.junit.jupiter.api.Assertions.*;

public class TestMergesort {
    @Test
    public void testIntegerLinkedList(){
        Mergesort<Integer> mergesort = new Mergesort<>();
                //TODO: add tests for list of integer, custom classes, strings...
        ArrayList<Integer> myList = new ArrayList<>(10);
        for(int i=0; i<10; i++){
            myList.add((int) (Math.random()*100));
        }
        Comparator<Integer> comparator = Comparator.comparingInt(o -> o);
        mergesort.mergeSort(myList, comparator, 0, myList.size());
        for (int i=0; i<10-1;i++){
            assertTrue(myList.get(i)<= myList.get(i+1));
        }
    }
}
