package oy.tol.tra;

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
}