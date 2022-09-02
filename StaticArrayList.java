import java.util.Arrays;

/**
 * A class that implements a list of objects using a static array. 
 * Entries in a list have positions that begin with 1. 
 * Duplicated entries are allowed.
 * 
 * @author Jing Hua Ye
 * @date Oct/30/2021
 * @version 1.0
 */
 public class StaticArrayList<T> implements ListInterface<T>
 {
/* ============================================================================
                        INSTANCE VARIABLES
   ============================================================================
 */
     //Array of list entries, ignore list[0]
     private T[] list;

     //the number of entries in the list
     private  int numEntries;


     //the default capacity of the list
     private static final int DEFAULT_CAPACITY = 25;

     //the maximum capacity of the list
     private static final int MAX_CAPACITY = 10000;

     private boolean c;         //bool value for checking true or false
/* ============================================================================
                        CONSTRUCTORS
   ============================================================================
 */
     /**
      * A default constructor 
      */
      public StaticArrayList()
      {  this.list = (T[]) new Object[DEFAULT_CAPACITY];        //setting the default capacity of the array
          numEntries=0;         //when the list is created the num of entries is 0
      }

     /**
      * Create a list with a specific capacity
      * @param initialCapacity the capacity of the list
      */
      public StaticArrayList(int initialCapacity)
      {
          if(initialCapacity>0 && initialCapacity<=MAX_CAPACITY) {      //making sure its a positive and lower than the max
              this.list = (T[]) new Object[initialCapacity];          //setting the initial capacity of the array
              numEntries=0;                                           //when the list is created the num of entries is 0
          }
          else{
              System.out.println("Error enter a capacity between 1 and the max of "  +MAX_CAPACITY);
          }
      }
/* =============================================================================
                       INSTANCE METHODS
   =============================================================================
 */
/* ---------------------- Getters --------------------------------------------*/
    /**
     * Gets the length of this list.
     * @return the integer number of entries currently in the list
     */
     public int getLength()
     {
         return numEntries;
     }
/* ---------------------- Other Methods --------------------------------------*/
    /**
     * Add a new entry to the end of this list.
     * Entries currently in the list are unaffected.
     * The list's size is increased by 1.
     *
     * @param newEntry the object to be added as a new entry
     */
     public void add(T newEntry)
     {
         if(numEntries==list.length) {        //if the array is full so we don't have room for the new element
             //now to resize the array by making a new bigger array
             T temp[] = (T[]) new Object[this.list.length +1 ];     //now I have a new array thats 1 space bigger
             for(int i = 0; i<this.list.length;i++){
                 temp[i] = this.list[i];     //copying old array elements into new array
             }
             this.list= temp;        //now pointing to my new temp array
         }

         this.list[this.numEntries] = newEntry;
         numEntries++;        //increased its number of entries by one

     }
    
    /**
     * Adds a new entry at a specified position within this list.
     * Entries originally at and above the specified position are 
     * at the next higher position within the list.
     * The list's size is increased by 1.
     * 
     * @param newPosition an integer that specifies the desired position 
     * of the new entry
     * @param newEntry the object to be added as a new entry
     * @throws IndexOutOfBoundsException if either newPosition < 1 or 
     * mewPosition > getLength() - 1
     */
     public void add(int newPosition, T newEntry)
     {
         if(newPosition>getLength()-1 || newPosition<0){        //if its outside the array or less than zero we will generate an error
             throw new IndexOutOfBoundsException("your position doesn't exist");
         }
// the position entered is within bounds
             if(numEntries==list.length) {        //if the array is full so we don't have room for the new element
                 //now to resize the array by making a new bigger array
                 T temp[] = (T[]) new Object[this.list.length +1 ];     //now I have a new array thats 1 space bigger
                 for(int i = 0; i<this.list.length;i++){
                     temp[i] = this.list[i];     //copying old array elements into new array
                 }
                 this.list= temp;        //now pointing to my new temp array
             }
             //now to make room for the newEntry by shifting all the elements after it by 1
             for(int i=numEntries;i>newPosition;i--){   //so for the number of entries; after the newpoisiton; decerement by 1
                 this.list[i] = this.list[i-1];          //shifting all those after the new position by 1
             }
             list[newPosition] = newEntry;         //now that we have space for the new entry we add it into that space
             numEntries++;

     }
    
    /**
     * Removes the entry at a given position from this list.
     * Entries originally at positions higher than the given position
     * are at the next lower position within the list.
     * 
     * @param givenPosition an integer that indicates the position of the 
     * entry to be removed
     * @return A pointer to the removed entry
     * @throws IndexOutOfBoundsException if either givenPosition < 1 or 
     * givenPosition > getLength()
     */
    public T remove(int givenPosition) {
        if (givenPosition >= 1 && givenPosition <= numEntries)
    {  if (!isEmpty())
    {
        //now to resize the array by making a new smaller array
        T temp[] = (T[]) new Object[this.list.length - 1 ];     //now I have a new array thats 1 space smaller
        for(int i = 0,k = 0; i<this.list.length;i++){
            if(i == givenPosition-1){     //when the index hits the given position continue without copying
                continue;
            }
            temp[k++] = this.list[i];     //copying old array elements into new array
        }
        this.list= temp;        //now moving everything from the new array into the old array and the givenPoisiton will be gone
        numEntries--;           //decrement the number of entries because one got removed
    }
    else
        throw new IndexOutOfBoundsException("Illegal position given to remove operation");
    }
        return list[numEntries];
    }

     /**
     * Replaces the entry at a given position in this list.
     * 
     * @param givenPosition an integer that indicates the position of the entry 
     * to be replaced
     * @param newEntry the object that will replace the entry at the position  
     * givenPosition
     * @throws IndexOutOfBoundsException if either givenPosition < 1 or 
     * givenPosition > getLength()
     */
    public T replace(int givenPosition, T newEntry)
     { if (givenPosition >= 1 && givenPosition <= numEntries)
       {
            for(int i = 0;i<numEntries;i++){            //for loop to traverse through the Array
                if(i==givenPosition-1){               //when the i thats traversing hits the Given position
                    list[i]=newEntry;      //adding the new entry at the given position
                }
            }
       }
       else
         throw new IndexOutOfBoundsException("Illegal position given to replace operation"); 
       return null;
     }
    
    /**
     * Retrieves the entry at a given position in this list.
     * 
     * @param givenPosition an integer that indicates the position of the 
     * desired entry
     * @return A pointer to the indicated entry
     * @throws IndexOutOfBoundsException if either givenPosition < 1 or 
     * givenPosition > getLength()   
     */
     public T getEntry(int givenPosition)
     { if (givenPosition < 1 && givenPosition > numEntries)
       {
           throw new IndexOutOfBoundsException("Illegal position given to getEntry operation");
       }
         return list[givenPosition-1];     //arrays start at zero so i added the -1 to get the correct one
     }

    /**
     * Sees whether this list contains a given entry.
     * 
     * @param anEntry the object that is the desired entry
     * @return true if the list contains anEntry, otherwise false
     */
     public boolean contains(T anEntry)
     {

         for(int i =0,k=0;i<numEntries;i++){
             if(list[i]==anEntry){
                c=true;
             }
             else{
                 c=false;
             }
         }
   return c;
     }

    /**
     * Sees whether this list is empty.
     * @return true if the list is empty, otherwise false
     */
     public boolean isEmpty()
     {
         if(numEntries==0) {
            c=true;
         }
         else {
            c=false;
         }

         return c;
     }

    /**
     * Retrieves all entries that are in this list in the order in which they
     * occur in the list.
     * 
     * @return a newly allocation array of all the entries in the list. If the 
     * list is empty, the returned array is empty.
     */
     public T[] toArray()
     {
         T[] result = (T[])new Object[numEntries];
         for (int index = 0; index < numEntries; index++)
             result[index] = list[index + 1];
         return result;
     }
  
    /**
     * To clear all contents in the list
     */
     public void clear()
     {
         for(int i = 0;i<=numEntries;i++){
            list[i]=null;           //making all of the elements of the array null
        }
         numEntries=0;                  //clearing the number of entries because the list is now empty
     }
/* =============================================================================
                       HELPER METHODS
   =============================================================================
 */
   /**
    * It doubles the size of an array after it becomes full
    */
    private void ensureCapacity()
    {  int capacity = list.length - 1;
       if (numEntries >= capacity)
       {  int newCapacity = 2 * capacity;
          checkCapacity(newCapacity);
          list = Arrays.copyOf(list, newCapacity + 1);
       }
    }
   
   /**
    * Display an error message if the client requests a capacity that is too 
    * large
    * @param capcity the capacity of the list
    */
    private void checkCapacity(int capacity)
    {   if (capacity > MAX_CAPACITY)
           System.out.println("Attempt to create a list whose capacity " + "exceeds allowed maximum of " + MAX_CAPACITY);
    }    
   
   /**
    * Make room for a new entry at newPosition by shifting list entries toward 
    * the end of the array, beginning with the last entry.
    * numEntries is list's length before addition.
    *
    * @param newPosition an integer that specifies the desired position 
    * of the new entry
    */
    private void makeRoom(int newPosition)
    {  if (newPosition >= 1 && newPosition <= numEntries + 1)
          //move each entry to next integer index, starting at end of list and 
          //continue until the entry at newIndex is moved
          for (int index = numEntries; index >= newPosition; index--)
               list[index + 1] = list[index];
    }

   /**
    * Shifts entries that are beyond the entry to be removed to the next lower
    * position.
    * numEntries is list's length before removal.
    *
    * @param givenPosition an integer that specifies the removal position
    */
    private void removeGap(int givenPosition)
    {  if (givenPosition >= 1 && givenPosition < numEntries)
          for (int index = givenPosition; index < numEntries; index++)
             list[index] = list[index + 1];
    }

     @Override
     public String toString() {
         return "StaticArrayList{" +
                 "list=" + Arrays.toString(list) +
                 ", numEntries=" + numEntries +
                 ", initialCapacity=" + list.length +
                 '}';
     }
 }
