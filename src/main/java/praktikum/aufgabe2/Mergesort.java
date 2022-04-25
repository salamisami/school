package praktikum.aufgabe2;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class Mergesort<T> {
    <T> List<T> mergeSort(List<T> list, Comparator comparator){
        int maxLength = list.size() ;
        if (maxLength<2){
            return list;
        }
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
