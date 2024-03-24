package oy.tol.tra;

import java.util.function.Predicate;

public class Algorithms {

    public static <T extends Comparable<T>> void sort(T[] array) {
        int n = array.length;
        boolean exchange;

        do {
            exchange = false;
            for (int i = 1; i < n; i++) {
                if (array[i - 1].compareTo(array[i]) > 0) {
                    T temp = array[i - 1];
                    array[i - 1] = array[i];
                    array[i] = temp;
                    exchange = true;
                }
            }
            n--;
        } while (exchange);

    }

    public static <T> void reverse(T[] array) {
        // implementation here...
        int i = 0;
        int x = array.length - 1;
        while (i <= x / 2) {
            T temp = array[i];
            array[i] = array[x - i];
            array[x - i] = temp;
            i++;
        }
    }

    public static <T extends Comparable<T>> int binarySearch(T aValue, T[] fromArray, int fromIndex, int toIndex) {

        for (int i = 0; i < fromArray.length; i++) {
            int mid = (fromIndex + toIndex) / 2;
            if (aValue.compareTo(fromArray[mid]) > 0) {
                fromIndex = mid + 1;
            } else if (aValue.compareTo(fromArray[mid]) < 0) {
                toIndex = mid - 1;
            } else {
                return mid;
            }

        }
        return -1;

    }

    public static <T> void swap(T[] array,int i,int j){
        T temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static <E extends Comparable<E>> void fastSort(E[] array) {
        quickSort(array, 0, array.length - 1);
    }

    public static <E extends Comparable<E>> void quickSort(E[] array, int begin, int end) {
        // implement Quicksort here...
        if (begin < end) {
            int result = partition(array, begin, end);
            quickSort(array, begin, result - 1);
            quickSort(array, result + 1, end);
        }

    }

    private static <E extends Comparable<E>> int partition(E[] array, int begin, int end) {
        int head = begin;
        int tail = end;
        sort(array);

        while (head <= tail) {
            E mid = array[begin + (end - begin) / 2];
            while (array[head].compareTo(mid) < 0) {
                head++;
            }

            while (array[tail].compareTo(mid) > 0) {
                tail--;
            }

            if (head <= tail) {
                E temp = array[head];
                array[head] = array[tail];
                array[tail] = temp;
                head++;
                tail--;
            }
        }

        return head;
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
  












