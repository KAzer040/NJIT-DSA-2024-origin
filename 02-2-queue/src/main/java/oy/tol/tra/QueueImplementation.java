package oy.tol.tra;

public class QueueImplementation<E> implements QueueInterface<E> {

   private Object[] itemArray;
   private int capacity;
   private int Size = 0;
   private int HEAD = 0;
   private int TAIL = -1;
   // private int currentIndex = -1;
   private static final int DEFAULT_Queue_SIZE = 10;

   public QueueImplementation() throws QueueAllocationException {
      this(DEFAULT_Queue_SIZE);
   }

   public QueueImplementation(int capacity) throws QueueAllocationException {

      if (capacity < 2) {
         throw new QueueAllocationException("Capacity must be at least 2.");
      }

      try {
         itemArray = new Object[capacity];
         this.capacity = capacity;
      } catch (Throwable e) {
         throw new QueueAllocationException("Failed to allocate Queue memory.");
      }

   }

   @Override
   public int capacity() {
      return capacity;

   }

   @Override
   public void enqueue(E element) throws QueueAllocationException, NullPointerException {

      if (size() >= capacity) {
         Object[] NEW = new Object[this.capacity *  5];

         int i = 0;
         int index = HEAD;
         while (i < Size) {
            NEW[i] = itemArray[index];
            index = (index + 1) % capacity;
            i++;
         }

         HEAD = 0;
         TAIL = Size - 1;
         itemArray = NEW;
         capacity = capacity * 5;
      }

         if (element == null) {
            throw new NullPointerException("element is null");
         }

         TAIL = (TAIL + 1) % capacity;
         itemArray[TAIL] = element;
         Size++;
         
   }

   

   @Override
   public E dequeue() throws QueueIsEmptyException {
      E k = element();
      HEAD = (HEAD + 1) % capacity;
      Size--;
      return k;

   }

   @SuppressWarnings("unchecked")
   @Override
   public E element() throws QueueIsEmptyException {
      if (isEmpty()) {
         throw new QueueIsEmptyException("Queue is empty");
      }
      return (E) itemArray[HEAD];

   }

   @Override
   public int size() {

      return Size;

   }

   @Override
   public void clear() {

      Size = 0;
      HEAD = 0;
      TAIL = -1;

   }

   @Override
   public boolean isEmpty() {

      return Size == 0;

   }

   @Override
   public String toString() {
      StringBuilder builder = new StringBuilder("[");
      int index = HEAD;
      int count = 0;
      while (count < Size) {
         builder.append(itemArray[index].toString());
         if (count < Size - 1) {
            builder.append(", ");
         }
         index = (index + 1) % capacity;
         count++;
      }
      builder.append("]");
      return builder.toString();
   }

}

