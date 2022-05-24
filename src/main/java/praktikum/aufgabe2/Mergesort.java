package praktikum.aufgabe2;
import java.util.*;

import static praktikum.aufgabe2.Constants.SIZE_OF_TO_SORT;

public class Mergesort<T> {
    private static int[] tau;
    private List<T> toSort;
    private Comparator<T> comparator;
    private static long[] testResults = new long[Constants.TEST_RUNTIME];
    //Test setup to measure insitu insertion vs just mergesort.... I couldnt really see any differences.

    //45660.7, 46534.6  10000000 size und 10 tests just merge
    //Insertion +Merge: 1st run:46913.1   2nd run 40023.7  10000000 size und 10 tests

    //DINSITU == 0; 167.1; D INSITU ==1000; 353.9;
    public static void main(String[] args){
        long startTime = System.nanoTime();
        for (int i = 0; i<Constants.TEST_RUNTIME; i++){
            Mergesort mergesort = new Mergesort();
            long endTime = System.nanoTime();
            mergesort.run();
            long duration = (endTime - startTime)/Constants.NANO_TO_MILISEC;  //divide by 1000000 to get milliseconds.
            testResults[i]=duration;
        }
        OptionalDouble average = Arrays.stream(testResults).average();
        System.out.println(average.toString());
    }
    private static OptionalDouble calculateAverage(long[] results){
        return Arrays.stream(results).average();
    }
    public static void run(){
        Mergesort<Integer> myMerge = new Mergesort<>();

        //List<Integer> sortMe = Arrays.asList(4,3,2,1,0); //For testing purposes to have a nice consistent list to sort.
        List<Integer> sortMe = myMerge.randomArrayList();
        Comparator<Integer> comparator = Comparator.comparingInt(o -> o);
        /*System.out.println("The unsorted list:");
        for (int i = 0; i < sortMe.size(); i++) {
            System.out.printf("%d, ", sortMe.get(i));
            if ((i+1)%10==0){
                System.out.println();
            }
        }*/
        myMerge.setup(sortMe, comparator);

        int[] tau = myMerge.sort();
        /*System.out.println("The sorted list:");
        for (int i = 0; i < sortMe.size(); i++) {
            System.out.printf("%d, ", sortMe.get(tau[i]));
            if ((i+1)%10==0){
                System.out.println();
            }
        }*/
    }
    //Generiert eine ArrayList mit random Integern.
    // In einem vordefenierten Bereich zwischen 0>= randomints<=SIZE_OF_TO_SORT
    public ArrayList<Integer> randomArrayList (){

    ArrayList<Integer> arr = new ArrayList<>();
      for (int i = 0; i < Constants.SIZE_OF_TO_SORT; i++) {
          arr.add((int)Math.floor(Math.random()*(SIZE_OF_TO_SORT*4)));
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
        if (size < Constants.D_INSITU ){
            insertionSort(toSort, listMinIndex, listMaxIndex, comparator);
            return;
        }
        int m = listMinIndex + (size/2);// Beispiel: size = 5. 0 +5/2 = 2
        mergeSort(list, comparator, listMinIndex, m); //left
        mergeSort(list, comparator,m,listMaxIndex);//right
        merge(listMinIndex, m, m, listMaxIndex, comparator, list);
    }


    private void merge(int leftMin, int leftMax, int rightMin, int rightMax, Comparator<T> comparator, List<T> toSort) {
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
    private void swap (int i, int j){
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