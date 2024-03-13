package oy.tol.tra;

public class Grades {
   
   private Integer [] grades = null;

   public Grades(Integer [] grades) {
      this.grades = new Integer [grades.length];
      for (int counter = 0; counter < grades.length; counter++) {
         this.grades[counter] = grades[counter];
      }
   }

   public void reverse() {
      int i = 0;
      while (i < grades.length/2) {
         int temp = grades[i];
         grades[i] = grades[grades.length-i-1];
         grades[grades.length-i-1] = temp;
         i++;
     }
   }

   public void sort() {
     int n = grades.length;
      boolean sort;
      do{
         sort = false;
         for (int i = 1; i < n; i++){
            if (grades[i-1].compareTo(grades[i])>0){
               int temp = grades[i-1];
               grades[i-1] = grades[i];
               grades[i] = temp;
               sort = true;
            }
         }
         n--;
      }while (sort);
   }

   public Integer [] getArray() {
      return grades;
   }
}
