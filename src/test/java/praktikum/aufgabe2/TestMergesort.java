package praktikum.aufgabe2;

import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestMergesort {
    @Test
    public void testInteger(){
        Mergesort<Integer> mergesort = new Mergesort<>();
        //TODO: add tests for list of integer, custom classes, strings...
        Comparator<Integer> comparator = Comparator.comparingInt(o -> o);
        List<Integer> myList = mergesort.randomArrayList();
        mergesort.setup(myList, comparator);
        int[] tau = mergesort.sort();
        for (int i=0; i<Constants.SIZE_OF_TO_SORT-1;i++){
            assertTrue(myList.get(tau[i])<= myList.get(tau[i]));
        }
    }
}
