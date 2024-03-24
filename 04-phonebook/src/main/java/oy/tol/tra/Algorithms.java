package oy.tol.tra;

import java.util.Arrays;
import java.util.Comparator;

public class Algorithms {

    public static <T> void reverse(T[] array) {
        int left = 0;
        int right = array.length - 1;
        while (left < right) {
            T temp = array[left];
            array[left] = array[right];
            array[right] = temp;
            left++;
            right--;
        }
    }

    public static <T extends Comparable<T>> void sort(T[] array) {
        boolean sorted;
        do {
            sorted = true;
            for (int i = 1; i < array.length; i++) {
                if (array[i - 1].compareTo(array[i]) > 0) {
                    T temp = array[i - 1];
                    array[i - 1] = array[i];
                    array[i] = temp;
                    sorted = false;
                }
            }
        } while (!sorted);
    }

    public static <T extends Comparable<T>> int binarySearch(T aValue, T[] fromArray, int fromIndex, int toIndex) {
        if (fromIndex <= toIndex) {
            int mid = fromIndex + (toIndex - fromIndex) / 2;
            int compareResult = aValue.compareTo(fromArray[mid]);
            if (compareResult == 0) {
                return mid;
            } else if (compareResult < 0) {
                return binarySearch(aValue, fromArray, fromIndex, mid - 1);
            } else {
                return binarySearch(aValue, fromArray, mid + 1, toIndex);
            }
        }
        return -1;
    }

    public static <E extends Comparable<E>> void fastSort(E[] array) {
        quickSort(array, 0, array.length - 1);
    }

    public static <E extends Comparable<E>> void quickSort(E[] array, int begin, int end) {
        if (begin < end) {
            int k = partition(array, begin, end);
            quickSort(array, begin, k - 1);
            quickSort(array, k + 1, end);
        }

    }

    private static <E extends Comparable<E>> int partition(E[] array, int begin, int end) {

        E z = array[begin]; 
        int left = begin;
        int right = end;

        while (left < right) {
            while (left < right && array[right].compareTo(z) > 0) {
                right--;
            }

            while (left < right && array[left].compareTo(z) <= 0) {
                left++;
            }

            if (left < right) {
                E temp = array[left];
                array[left] = array[right];
                array[right] = temp;
            }
        }

        array[begin] = array[left];
        array[left] = z;
        return left;
    }
    public static <T> int partitionByRule(T [] pairs,int count,Predicate<T> judgeNullPredicate){
        int left = 0;
        int right = count - 1;

        while (left <= right) {
            while (left <= right && !judgeNullPredicate.test(pairs[left])) {
                left++;
            }

            while (left <= right && judgeNullPredicate.test(pairs[right])) {
                right--;
            }

            if (left < right) {
                swap(pairs, left, right);
                left++;
                right--;
            }
        }
        return left;

    }

    public static <T> void sortWithComparator( T[] array, Comparator<? super T> comparator) {
        Arrays.sort(array, comparator);
    }
    public static <T> int partitionByRule(T[] array, int count, Predicate<T> rule) {
      // Find first element rules applies to.
      // Index of that element will be in variable index.
      int index = 0;
      for (; index < count; index++) {
         if (rule.test(array[index])) {
            break;
         }
      }
      // If went to the end, nothing was selected so quit here.
      if (index >= count) {
         return count;
      }
      // Then start finding not selected elements starting from next from index.
      // If the element is not selected, swap it with the selected one.
      int nextIndex = index + 1;
      // Until end of array reached.
      while (nextIndex != count) {
         if (!rule.test(array[nextIndex])) {
            swap(array, index, nextIndex);
            // If swapping was done, add to index since now it has non-selected element.
            index++;
         }
         nextIndex++;
      }
      return index;
   }


}