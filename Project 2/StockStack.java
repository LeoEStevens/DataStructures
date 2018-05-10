/******************
 * Data Structures Project 2
 * Created on 10/26/2017 by Leo Stevens
 ******************/

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.io.Serializable;

/***************************
 * This class is a stack of Stock classes modified from the Stack program written in class.
 * This class has push, pop, peek, and empty default stack operations as methods.
 * There is also a method which prints the entire stack following LIFO.
 * This class is called by the Business class and calls the Stock class.
 * Serializable to be read using ObjectInputStream and written using ObjectOutputStream.
 *
 * @author Leo Stevens
 * @version 1.0
 **************************/

public class StockStack implements Serializable {
        /***************
         * Default Constructor.
         * This constructor creates a stack of INITIAL_CAPACITY size which is 10.
         * This constructor is used in the Business class when creating a new Business.
         **************/
        public StockStack() {
                this.data = new Stock[INITIAL_CAPACITY];
                this.size = INITIAL_CAPACITY;
                this.stackPointer = -1;
        }
        /***************
         * Overloaded Constructor.
         * This constructor creates a stack of user defined size.
         * This constructor is not currently used.
         *
         * @param size                  Initial size of stack to be created.
         **************/
        public StockStack(int size) {
                this.data = new Stock[size];
                this.size = size;
                this.stackPointer = -1;
        }
        /***************
         * This method checks if the stack is empty by checking if the stack pointer is -1.
         *
         * @return                      Infomation about whether or not the stack is empty.
         **************/
        public boolean empty() {
                return (stackPointer == -1);
        }
        /*************
         * This method doubles the size of the current data array.
         * The method creates a new data array of twice the size of the current array, then copies over all data into new array.
         * Then the method updates the reference from the old array to the new array.
         *************/
        private void reallocate() {
                //Double the size
                this.size *= 2;
                //Create a new stack of the new size
                Stock[] temp = new Stock[size];
                //Move data into new array
                for(int i = 0; i <= stackPointer; i++) {
                        temp[i] = data[i];
                }
                //Update the reference
                this.data = temp;
        }
        /*************
         * This method returns the top Stock on the stack without changing the stack pointer.
         * The method checks if the stack is empty, and if it is then return null. 
         * If the stack is not empty then the method returns the top Stock on the stack.
         *
         * @return                      The top Stock on the stack.
         ************/
        public Stock peek() {
                if(empty()) {
                        return null;
                } else {
                        return data[stackPointer];
                }
        }
        /************ 
         * This method returns the top Stock on the stack and updates the stack pointer so the value can be overwritten.
         * The method checks if the stack is empty and if it is then it returns null.
         * If the stack is not empty then it returns the top Stock on the stack and post-decriments the stackPointer.
         *
         * @return                      The top Stock on the stack.
         ***********/
        public Stock pop() {
                if(empty()) {
                        System.out.println("Stack Underflow");
                        return null;
                } else {
                        return data[stackPointer--];
                }
        }
        /************
         * Method to add a Stock to the stack.
         * The method checks if the stack has sufficent space and then push the Stock onto the stack.
         * If the stack does not have sufficient space then the method calls reallocate and then pushes the Stock onto the stack.
         *
         * @param obj                   The Stock to push onto the top of the stack.
         ***********/
        public void push(Stock obj) {
                if(stackPointer < size - 1) {
                        this.data[++stackPointer] = obj;
                } else {
                        reallocate();
                        this.data[++stackPointer] = obj;
                }
        }
        /**********
         * This method prints the stack following the LIFO.
         * The method uses a for loop starting at the last item and prints until reaching the first item.
         *********/
        public void printStack() {
                for(int i = stackPointer; i >= 0; i--) {
                        System.out.println(data[i]);
                }
        }
        /******
         * Variables
         *****/
        private Stock[] data;                                   //Array of stock objects to hold the data for stack operations.
        private int size;                                       //The current number of items that the stack can hold.
        private int stackPointer;                               //The current size of the stack.
        private static final int INITIAL_CAPACITY = 10;         //The initial capacity of the stack.
		private static final long serialVersionUID = 1;
}
