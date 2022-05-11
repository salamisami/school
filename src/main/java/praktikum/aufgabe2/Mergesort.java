package praktikum.aufgabe2;

import java.lang.reflect.Array;
import java.sql.SQLOutput;
import java.util.*;

import static praktikum.aufgabe2.Constants.SIZE_OF_TO_SORT;

public class Mergesort<T> {

    public static void main(String[] args){
        Mergesort myMerge = new Mergesort<Integer>();
        ArrayList<Integer> sortMe = myMerge.randomArrayList(SIZE_OF_TO_SORT);
        Comparator<Integer> comparator = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1-o2;
            }
        };
        System.out.println("The unsorted list:");
        for (int i = 0; i < sortMe.size(); i++) {
            System.out.print(String.format("%d. %d; ", i+1, sortMe.get(i)));
            if ((i+1)%10==0){
                System.out.println();
            }
        }
        System.out.println("");
        List<Integer> result = myMerge.mergeSort(sortMe, comparator);
        System.out.println("The sorted list:");
        for (int i = 0; i < result.size(); i++) {
            System.out.print(String.format("%d. %d; ", i+1, result.get(i)));
            if ((i+1)%10==0){
                System.out.println();
            }
        }
    }
    private ArrayList<Integer> randomArrayList (int size){
        Random rd = new Random();

    ArrayList<Integer> arr = new ArrayList<Integer>();
      for (int i = 0; i < size; i++) {
          arr.add((int)Math.floor(Math.random()*(SIZE_OF_TO_SORT*4))); //max,min, +1, min
        //arr.add(rd.nextInt()); // storing random integers in an array
    }
      return  arr;
}
    <T> List<T> mergeSort(List<T> list, Comparator comparator){
        int maxLength = list.size() ;
        if (maxLength<2){
            return list;
        }
        //TODO: Insitu implementieren.
        LinkedList<T> left = new LinkedList<>();
        LinkedList<T> right = new LinkedList<>();
        int midIndex = maxLength/2;
        //TODO: Check for even/uneven
        for (int i = 0; i<midIndex; i++){
            left.add(list.get(i));
        }
        for (int i=midIndex; i<maxLength; i++){
            right.add(list.get(i));
        }

        left = (LinkedList<T>) mergeSort(left, comparator);
        right = (LinkedList<T>) mergeSort(right, comparator);
        return merge(left, right, comparator);
    }

    private <T> LinkedList<T> merge(List<T> left, List<T> right, Comparator comparator) {
        LinkedList<T> result = new LinkedList<>();

        int leftLength = left.size();
        int rightLength = right.size();
        int counterLeft = 0;
        int counterRight = 0;
        int leftNumbersOver = leftLength - counterLeft; //Wie viele sind unsortiert?
        int rightNumbersOver = rightLength - counterRight;
        while (leftNumbersOver > 0 || rightNumbersOver > 0){
            if (leftNumbersOver < 1){// TODO: Schöner lösen, counters vereinfachen.
                result.add(right.get(counterRight));
                counterRight++;//Wie viele sind unsortiert?
                rightNumbersOver = rightLength - counterRight;
                continue;
            }
            if (rightNumbersOver < 1){
                result.add(left.get(counterLeft));
                counterLeft++;
                leftNumbersOver = leftLength - counterLeft; //Wie viele sind unsortiert?
                continue;
            }
            int compareResult = comparator.compare(left.get(counterLeft), right.get(counterRight));
            if( compareResult <= 0  ){
                result.add(left.get(counterLeft));
                counterLeft++;
            }
            else {
                result.add(right.get(counterRight));
                counterRight++;
            }
            leftNumbersOver = leftLength - counterLeft; //Wie viele sind unsortiert?
            rightNumbersOver = rightLength - counterRight;
        }
        return result;
    }
}
