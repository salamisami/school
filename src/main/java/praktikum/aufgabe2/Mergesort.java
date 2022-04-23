package praktikum.aufgabe2;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class Mergesort<T> {
    private Comparator comparator;
    public Mergesort(Comparator <T> comparator){
        this.comparator = comparator;
    }
    <T> LinkedList<T> mergeSort(List<T> list){
        LinkedList<T> left = new LinkedList<>();
        LinkedList<T> right = new LinkedList<>();
        int maxLength = list.size() - 1;
        //TODO: Check for even/uneven
        for (int i = 0; i<maxLength/2; i++){
            left.add(list.get(i));
        }
        for (int i=maxLength/2; i<maxLength+1; i++){
            right.add(list.get(i));
        }

        left = mergeSort(left);
        right = mergeSort(right);
        return merge(left, right);
    }

    private <T> LinkedList<T> merge(LinkedList<T> left, LinkedList<T> right) {
        LinkedList<T> result = new LinkedList<>();

        int leftLength = left.size() - 1;
        int rightLength = right.size() - 1;
        int il = 0;
        for (int i = 0; i<leftLength+rightLength+1; i++){
            if (il > leftLength){
                result.add(right.get(i-il));
                continue;
            }
            if (il < i-rightLength){
                result.add(left.get(il));
                il++;
                continue;
            }
            int compareResult = comparator.compare(left.get(il), right.get(i-il));
            if( compareResult <= 0  ){
                result.add(left.get(il));
                il++;
            }
            else {
                result.add(right.get(i-il));
            }
        }
        return result;
    }
}
