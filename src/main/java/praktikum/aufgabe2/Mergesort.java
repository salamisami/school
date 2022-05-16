package praktikum.aufgabe2;
import java.util.*;

import static praktikum.aufgabe2.Constants.SIZE_OF_TO_SORT;

public class Mergesort<T> {
    protected int[] tau;

    public static void main(String[] args){
        Mergesort<Integer> myMerge = new Mergesort<>();
        ArrayList<Integer> sortMe = myMerge.randomArrayList();
        Comparator<Integer> comparator = Comparator.comparingInt(o -> o);
        System.out.println("The unsorted list:");
        for (int i = 0; i < sortMe.size(); i++) {
            System.out.printf("%d. %d; ", i+1, sortMe.get(i));
            if ((i+1)%10==0){
                System.out.println();
            }
        }
        List<Integer> result = myMerge.mergeSort(sortMe, comparator, 0, sortMe.size());
        System.out.println("The sorted list:");
        for (int i = 0; i < result.size(); i++) {
            System.out.printf("%d. %d; ", i+1, result.get(i));
            if ((i+1)%10==0){
                System.out.println();
            }
        }
    }
    //Generiert eine ArrayList mit random Integern.
    // In einem vordefenierten Bereich zwischen 0>= randomints<=SIZE_OF_TO_SORT
    private ArrayList<Integer> randomArrayList (){

    ArrayList<Integer> arr = new ArrayList<>();
      for (int i = 0; i < Constants.SIZE_OF_TO_SORT; i++) {
          arr.add((int)Math.floor(Math.random()*(SIZE_OF_TO_SORT*4))); //max,min, +1, min
        //arr.add(rd.nextInt()); // storing random integers in an array
    }
      return  arr;
}
    public void setup (List <T> toSort, Comparator comparator){
        tau = new int[toSort.size()];
        for (int i =0; i<toSort.size(); i++){
            tau[i] = i;
        }
        this.mergeSort(toSort, comparator, 0, toSort.size());
    }
    public List<T> mergeSort(List<T> list, Comparator comparator, int listMinIndex, int listMaxIndex){
        if (listMaxIndex<2){
            return list;
        }
        //TODO: Insitu implementieren. !! Über indexe arbeite
        mergeSort(list, comparator, listMinIndex, listMaxIndex/2);
        mergeSort(list, comparator,(listMaxIndex/2)+1,listMaxIndex);
        return merge(left, right, comparator);
    }


    private void merge(int leftMin, int leftMax, int rightMin, int rightMax, Comparator comparator, List<T> toSort) {
        int counterLeft = 0;
        int counterRight = 0;
        int leftNumbersOver = leftMax - counterLeft; //Wie viele sind unsortiert?
        int rightNumbersOver = rightMax - counterRight;
        while (leftNumbersOver > 0 || rightNumbersOver > 0){
            if (leftNumbersOver < 1){ //"Links" ist leer
                // TODO: Schöner lösen, counters vereinfachen.
                tau[leftMax+counterRight] = rightMin+counterRight;
                counterRight++;//Wie viele sind unsortiert?
                rightNumbersOver = rightMax - counterRight;
                continue;
            }
            if (rightNumbersOver < 1){//"Rechts" ist leer
                tau[rightMax+counterLeft] = leftMin+counterLeft;
                counterLeft++;
                leftNumbersOver = leftMax - counterLeft; //Wie viele sind unsortiert?
                continue;
            }
            int compareResult = comparator.compare(toSort.get(counterLeft+leftMin), toSort.get(counterRight+rightMin));
            if( compareResult <= 0  ){
                tau[counterLeft+counterRight+leftMin]=counterLeft+leftMin;
                counterLeft++;
            }
            else {
                tau[counterLeft+counterRight+leftMin]=counterRight+rightMin;
                counterRight++;
            }
            leftNumbersOver = leftMax - counterLeft; //Wie viele sind unsortiert?
            rightNumbersOver = rightMax - counterRight;
        }
        return ;
    }
    void swap (List <T> list, int i, int j){
        T temp = list.get(i);
        list.add(i, list.get(j));
        list.add(j, temp);
    }

}
