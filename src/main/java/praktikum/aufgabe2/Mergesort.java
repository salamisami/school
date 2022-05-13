package praktikum.aufgabe2;
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
        List<Integer> result = myMerge.mergeSort(sortMe, comparator, 0, sortMe.size());
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
    public void mergeSort(List<T> list, Comparator comparator,int listMinIndex, int listMaxIndex){
        if (listMaxIndex<2){
            return list;
        }
        //TODO: Insitu implementieren. !! Über indexe arbeite
        //LinkedList<T> left = new LinkedList<>();
       // LinkedList<T> right = new LinkedList<>();

        mergeSort(list, comparator, listMinIndex, listMaxIndex/2);
        mergeSort(list, comparator,listMaxIndex/2+1,listMaxIndex);
        return merge(left, right, comparator);
    }


    private <T> LinkedList<T> merge(int leftMin,int leftMax, int rightMin,int rightMax, Comparator comparator) {
        int counterLeft = 0;
        int counterRight = 0;
        int leftNumbersOver = leftMax - counterLeft; //Wie viele sind unsortiert?
        int rightNumbersOver = rightMax - counterRight;
        while (leftNumbersOver > 0 || rightNumbersOver > 0){
            if (leftNumbersOver < 1){// TODO: Schöner lösen, counters vereinfachen.
                swapR.add(listR.get(counterRight));
                counterRight++;//Wie viele sind unsortiert?
                rightNumbersOver = rightMax - counterRight;
                continue;
            }
            if (rightNumbersOver < 1){
                swapR.add(list.get(counterLeft));
                counterLeft++;

                leftNumbersOver = leftMax - counterLeft; //Wie viele sind unsortiert?
                continue;
            }
            int compareResult = comparator.compare(list.get(counterLeft), listR.get(counterRight));
            if( compareResult <= 0  ){
                swapR.add(list.get(counterLeft));
                counterLeft++;
            }
            else {
                swapR.add(listR.get(counterRight));
                counterRight++;
            }
            leftNumbersOver = leftMax - counterLeft; //Wie viele sind unsortiert?
            rightNumbersOver = rightMax - counterRight;
        }
        return swapR;
    }
    void swap (List <T> list, int i, int j){
        T temp = list.get(i);
        list.add(i, list.get(j));
        list.add(j, temp);
    }

}
