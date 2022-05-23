package praktikum.aufgabe2;
import java.util.*;

import static praktikum.aufgabe2.Constants.SIZE_OF_TO_SORT;

public class Mergesort<T> {
    private static int[] tau;
    private List<T> toSort;
    private Comparator<T> comparator;

    public static void main(String[] args){
        Mergesort<Integer> myMerge = new Mergesort<>();

        List<Integer> sortMe = Arrays.asList(4,3,2,1,0);
        //List<Integer> sortMe = myMerge.randomArrayList();
        Comparator<Integer> comparator = Comparator.comparingInt(o -> o);
        System.out.println("The unsorted list:");
        for (int i = 0; i < sortMe.size(); i++) {
            System.out.printf("%d, ", sortMe.get(i));
            if ((i+1)%10==0){
                System.out.println();
            }
        }
        myMerge.setup(sortMe, comparator);

        int[] tau = myMerge.sort();
        //myMerge.insertionSort(sortMe,0,sortMe.size(), comparator);
        System.out.println("The sorted list:");
        for (int i = 0; i < sortMe.size(); i++) {
            System.out.printf("%d, ", sortMe.get(tau[i]));
            if ((i+1)%10==0){
                System.out.println();
            }
        }
    }
    //Generiert eine ArrayList mit random Integern.
    // In einem vordefenierten Bereich zwischen 0>= randomints<=SIZE_OF_TO_SORT
    public ArrayList<Integer> randomArrayList (){

    ArrayList<Integer> arr = new ArrayList<>();
      for (int i = 0; i < Constants.SIZE_OF_TO_SORT; i++) {
          arr.add((int)Math.floor(Math.random()*(SIZE_OF_TO_SORT*4))); //max,min, +1, min
        //arr.add(rd.nextInt()); // storing random integers in an array
    }
      return  arr;
}
    public void setup (List <T> toSort, Comparator<T> comparator){
        tau = new int[toSort.size()];
        this.comparator = comparator;
        this.toSort = toSort;
        for (int i =0; i<toSort.size(); i++){
            tau[i] = i;
        }
    }
    public int[] sort(){
        this.mergeSort(toSort, comparator, 0, toSort.size());
        return tau;
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
        merge(listMinIndex, m, m, listMaxIndex, comparator, list);
    }


    private void merge(int leftMin, int leftMax, int rightMin, int rightMax, Comparator<T> comparator, List<T> toSort) {
       if (rightMax-leftMin < Constants.D_INSITU ){
            insertionSort(toSort, leftMin, rightMax, comparator);
            return;
        }
        int counterLeft = 0;
        int counterRight = 0;
        int leftNumbersOver = leftMax - leftMin; //Wie viele sind unsortiert?
        int rightNumbersOver = rightMax - rightMin;
        int[] tauTemp = new int[leftNumbersOver+rightNumbersOver];
        //Tau Temp um die Zwischenergebnisse der Vergleiche zu speichern ohne Indexe in Tau Original
        while (leftNumbersOver > 0 || rightNumbersOver > 0){
            if (leftNumbersOver < 1){ //"Links" ist leer
                // TODO: Schöner lösen, counters vereinfachen.
                tauTemp[counterLeft+counterRight]=tau[counterRight+rightMin];
                counterRight++;//Wie viele sind unsortiert?
                rightNumbersOver--;
                continue;
            }
            if (rightNumbersOver < 1){//"Rechts" ist leer
                tauTemp[counterLeft+counterRight]=tau[counterLeft+leftMin];
                counterLeft++;
                leftNumbersOver--;
                continue;
            }
            int compareResult = comparator.compare(toSort.get(tau[counterLeft+leftMin]), toSort.get(tau[counterRight+rightMin]));
            if( compareResult <= 0  ){
                tauTemp[counterLeft+counterRight]=tau[counterLeft+leftMin];
                counterLeft++;
                leftNumbersOver--;
            }
            else {
                tauTemp[counterLeft+counterRight]=tau[counterRight+rightMin];
                counterRight++;
                rightNumbersOver--;
            }
        }
        System.arraycopy(tauTemp,0, tau, leftMin, tauTemp.length);
    }
    void swap (int i, int j){
        int temp = tau[i];
        tau[i]=tau[j];
        tau[j]=temp;
    }
    private void insertionSort (List<T> toSort, int minIndex, int maxIndex, Comparator<T> comparator){
        for (int i=minIndex; i<maxIndex; ++i){
            int j = i;
            while (j> minIndex && (comparator.compare(toSort.get(tau[j-1]), toSort.get(tau[j])) >0)){
                swap(j-1, j);
                j = j-1;
            }
        }
    }
}