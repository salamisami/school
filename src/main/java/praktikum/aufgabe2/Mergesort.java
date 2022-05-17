package praktikum.aufgabe2;
import java.util.*;

import static praktikum.aufgabe2.Constants.SIZE_OF_TO_SORT;

public class Mergesort<T> {

    private int[] tau;
    private Comparator<T> comparator;
    private List<T> toSort;

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
        myMerge.setup(sortMe, comparator);
        int[] resultTau = myMerge.sort();
        System.out.println("The sorted list permutation array:");
        for (int i = 0; i < resultTau.length; i++) {
            System.out.print(String.format("%d. %d; ", i+1, resultTau[i]));
            if ((i+1)%10==0){
                System.out.println();
            }
        }
    }
    public void setup(List<T> sortMe, Comparator<T> comparator){
        tau = new int[sortMe.size()];
        for (int i=0; i<tau.length;i++){
            tau[i]=i;
        }
        this.toSort = sortMe;
        this.comparator = comparator;
    }
    public int[] sort(){
        this.mergeSort(toSort, comparator, 0, toSort.size());
        return tau;
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

    private void mergeSort(List<T> list, Comparator<T> comparator, int listMinIndex, int listMaxIndex){
        int size = listMaxIndex-listMinIndex;
        if ((size)<2){
            return ;
        }
        int m = listMinIndex + (size/2);// size = 5. 0 +5/2 = 2
        //TODO: Insitu implementieren. !! Über indexe arbeite
        mergeSort(list, comparator, listMinIndex, m); //left
        mergeSort(list, comparator,m,listMaxIndex);//right
        if (listMaxIndex-listMinIndex<Constants.d_insitu){
            insertionSort(list, comparator);
        }
        else{
        merge(listMinIndex, m, m, listMaxIndex, comparator, list);
        }
        return;
    }

    /*private List<T> mergeSort(List<T> list, Comparator comparator){
        int maxLength = list.size() ;
        if (maxLength<2){
            return list;
        }
        //TODO: Insitu implementieren. !! Über indexe arbeite
        List<T> left = new LinkedList<>();
        List<T> right = new LinkedList<>();
        int midIndex = maxLength/2;
        for (int i = 0; i<midIndex; i++){
            left.add(list.get(i));
        }
        for (int i=midIndex; i<maxLength; i++){
            right.add(list.get(i));
        }

        left = mergeSort(left, comparator);
        right = mergeSort(right, comparator);
        merge(left, right, comparator, );
        return;
    }*/


    private void merge(int leftMin, int leftMax, int rightMin, int rightMax, Comparator<T> comparator, List<T> toSort) {
        List<T> left = toSort.subList(leftMin, leftMax);
        List<T> right = toSort.subList(rightMin, rightMax);
        int counterLeft = 0;
        int counterRight = 0;
        int leftNumbersOver = leftMax - leftMin; //Wie viele sind unsortiert?
        int rightNumbersOver = rightMax - rightMin;
        while (leftNumbersOver > 0 || rightNumbersOver > 0){
            if (leftNumbersOver < 1){ //"Links" ist leer
                // TODO: Schöner lösen, counters vereinfachen.
                tau[leftMin+counterLeft] = rightMin+counterRight;
                counterRight++;//Wie viele sind unsortiert?
                rightNumbersOver--;
                continue;
            }
            if (rightNumbersOver < 1){//"Rechts" ist leer
                tau[leftMin+counterRight] = leftMin+counterLeft;
                counterLeft++;
                leftNumbersOver--; //Wie viele sind unsortiert?
                continue;
            }
            int compareResult = comparator.compare(toSort.get(counterLeft+leftMin), toSort.get(counterRight+rightMin));
            if( compareResult <= 0  ){
                tau[counterLeft+counterRight+leftMin]=counterLeft+leftMin;
                counterLeft++;
                leftNumbersOver--;
            }
            else {
                tau[counterLeft+counterRight+leftMin]=counterRight+rightMin;
                counterRight++;
                rightNumbersOver--;
            }
        }
    }
    void swap (List <T> list, int i, int j){
        T temp = list.get(i);
        list.add(i, list.get(j));
        list.add(j, temp);
    }
    void swap (int[] arr, int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    private void insertionSort (List<T> toSort, Comparator<T> comparator){
        int length = toSort.size();
        for (int i=1; i<length; ++i){
            T key = toSort.get(i);
            int j = i-1;
            while (j>= 0 && comparator.compare(toSort.get(j), key) >0){
                tau[j+1] = tau[j];
                j = j-1;
            }
        }
    }

}