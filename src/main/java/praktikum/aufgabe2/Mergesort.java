package praktikum.aufgabe2;
import java.util.*;

import static praktikum.aufgabe2.Constants.SIZE_OF_TO_SORT;

public class Mergesort<T> {
    protected int[] tau;
    private List<T> toSort;
    private Comparator<T> comparator;

    public static void main(String[] args){
        Mergesort<Integer> myMerge = new Mergesort<>();

        //List<Integer> sortMe = Arrays.asList(4,3,2,1,0);
        List<Integer> sortMe = myMerge.randomArrayList();
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
    private ArrayList<Integer> randomArrayList (){

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
        return;
    }


    private void merge(int leftMin, int leftMax, int rightMin, int rightMax, Comparator<T> comparator, List<T> toSort) {

        //TODO: If size <d_insitu -> insertionSort (In-situ)
        int counterLeft = 0;
        int counterRight = 0;
        int leftNumbersOver = leftMax - leftMin; //Wie viele sind unsortiert?
        int rightNumbersOver = rightMax - rightMin;
        int tauTemp[] = new int[leftNumbersOver+rightNumbersOver];
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
        for (int i = 0; i<tauTemp.length; i++){ // Temporary Resultat in Ergebnis übertragen.
            tau[leftMin+i] = tauTemp[i];
        }
    }
    void swap (List <T> list, int i, int j){
        T temp = list.get(i);
        list.add(i, list.get(j));
        list.add(j, temp);
    }
    private void insertionSort (List<T> toSort, Comparator<T> comparator){
        int length = toSort.size();
        for (int i=1; i<length; ++i){
            T key = toSort.get(i);
            int j = i-1;
            while (j>= 0 && comparator.compare(toSort.get(j), key) >0){
                swap(toSort, j+1, j);
                j = j-1;
            }
        }
    }
}
