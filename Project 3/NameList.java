
/******************
 * This class is an adaptation of the ArrayList class written in class.
 *
 * @author Leo Stevens
 * @version 1.0
 ******************/

public class NameList<E> {
        /****************
         * Default Constructor.
         * This constructor is used by the NameList class.
         * Creates an ArrayList of default capacity (10).
         ***************/
        public NameList() {
                this.capacity = INITIAL_CAPACITY;
                this.size = 0;
                myArray = (E[]) new Object[this.capacity];
        }
        /**************
         * Overloaded Constructor.
         * This constructor is not used.
         * Creates an ArrayList of user defined capacity.
         *
         * @param capacity              The capacity of the ArrayList
         *************/
        public NameList(int capacity) {
                this.capacity = capacity;
                this.size = 0;
                myArray = (E[]) new Object[this.capacity];
        }
        /***************
         * This method adds an object to the end of the arraylist.
         * 
         * @param a                     The object to add to the list.
         * @return                      The index of the object that was added.
         **************/
        public int add(E a) {
                //If the object will fit in the ArrayList
                if(size < capacity) {
                        //Add item and increase size of array
                        myArray[size++] = a;
                }
                //Else reallocate and add the item
                else {
                        this.reallocate();
                        this.add(a);
                }
                //Return index of added item
                return size - 1;
        }
        /***************
         * This method doubles the size of the data array.
         * The method creates a temp data array which is twice the size of the old array.
         * Then the method copies over the data.
         * The reference to the data array is then updated to the new array.
         ***************/
        private void reallocate() {
                //Double the capacity
                this.capacity *= 2;
                //Create a new array
                E[] temp = (E[]) new Object[this.capacity];
                //Copy old array into new
                for(int i = 0; i < myArray.length; i++) {
                        temp[i] = myArray[i];
                }
                //Update reference
                this.myArray = temp;
        }
        /**************
         * This method adds an object to a specific index.
         * If the specified index is not at the end of the array the data is shifted over to accomodate the new data.
         *
         * @param a                     The object to add.
         * @param index                 The index to add the object too.
         *************/
        public void add(E a, int index) {
                //Check for index validity
                if (index < 0 || index > size + 1) {
                        System.out.println("Invalid index");
                        return;
                }
                //Special case where adding to the end of the arraylist
                else  if (index == size) {
                        //Call add method
                        this.add(a);
                } else {
                        //Make sure there is space
                        //Then move the elements over
                        if(capacity == size) {
                                this.reallocate();
                        }
                        //Move the data
                        for(int i = size; i > index; i--) {
                                myArray[i] = myArray[i - 1];
                        }
                        //Add new item
                        myArray[index] = a;
                        //Increase size
                        size++;
                }

        }
        /**************
         * This method removes the Business from a specific index.
         * The data is shifted to overwrite the old data.
         * 
         * @param index                 The index of the object to remove.
         * @return                      The object that was removed.
         *************/
        public E remove(int index) {
                //Check for valid index
                if(index < 0 || index >= size) {
                    System.out.println("Invalid Index");
                    return null;
                } else {
                    //Store item to be returned
					E temp =  myArray[index];
                    //Loop to shift elements one place to the left from the index
                    for(int i = index; i < size; i++) {
						myArray[i] = myArray[i + 1];
                    }
                    //Update the size
                    size--;
                    //Return the deleted element
					return temp;
                }
        }
        /*************
         * This method returns the object located at a specific index.
         *
         * @param index                 The index of the object to return.
         * @return                      The object at the specified index.
         ************/
        public E get(int index) {
                //Check for a valid index
                if(index < 0 || index >= size) {
                        //Notify user that the index was invalid.
                        System.out.println("Invalid Index");
                        //Return null
                        return null;
                } 
                //Else return the object at that index
                else {
                         return myArray[index];
                }
        }
        /**************
         * This method updates the object at the given index.
         *
         * @param a                     The object to write into the array.
         * @param index                 The index of the object to overwrite.
         * @return                      If the operation was successful or not.
         *************/
        public boolean set(E a, int index) {
                //Check if index is invalid
                if(index < 0 || index >= size) {
                        System.out.println("Invalid Index");
                        return false;
                }
                //If index was valid then overwrite data
                else {
                       myArray[index] = a;
                       return true;
                }
        }
        /*************
         * This method returns the current size of the ArrayList.
         *
         * @return                      The size of the ArrayList.
         ************/
        public int size() {
                return size;

        }
        /************
         * This method returns the index of a specific object.
         * The method searches through the array using a linear search and utilizes the equals method.
         *
         * @param a                     The object to search for.
         * @return                      The index of the object that matches.
         ***********/
        public int indexOf(E a) {
                //For loop to go through data
                for(int i = 0; i < size; i++) {
                        //Check if the data matches
                        if(myArray[i].equals(a)) {
                                //Return index
                                return i;
                        }
                }
                //Notify the user of no matches
                System.out.println("Item not found");
                return -1;

        }
        /*************
         * This method displays the contents of the ArrayList.
         * The method loops through the array and prints out every object in the array.
         ************/
        public void display() {
                //Loop through data
                for(int i = 0; i < size; i++) {
                        //Print data
                        System.out.print(myArray[i] + ",");
                }
                //Print empty line
                System.out.println();
        }
        /**********
         * Variables
         *********/
        private int size;                                       //The current size of the ArrayList
        private int capacity;                                   //The current maximum size of the data array
        private E[] myArray;									//The array of data
        private static final int INITIAL_CAPACITY = 10;         //The initial size of the ArrayList
}
