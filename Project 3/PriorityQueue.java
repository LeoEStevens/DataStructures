/*************
 * Data Structures Project 3
 * Created on 11/18/2017 by Leo Stevens
 ************/

import java.util.NoSuchElementException;

/**************
 * PriorityQueue Class.
 * This class holds the data in a heap and processes it using the methods of the Priority Queue.
 *
 * @author Leo Stevens
 * @version 1.0
 *************/

public class PriorityQueue<E extends Comparable<E>> {
	/***********
	 * Default Constructor.
	 * Creates the Priority Queue of default size.
	 ***********/
	@SuppressWarnings("unchecked")
	public PriorityQueue() {
		this.capacity = INITIAL_CAPACITY;
		this.data = (E[]) new Comparable[INITIAL_CAPACITY];
		this.size = 0;
	}
	/*********
	 * Overloaded Constructor.
	 * Creates the Priority Queue of user defined size.
	 *
	 * @param capacity				The capacity of the Priority Queue.
	 ********/
	@SuppressWarnings("unchecked")
	public PriorityQueue(int capacity) {
		this.capacity = capacity;
		this.size = 0;
		this.data = (E[]) new Comparable[capacity];
	}
	/*********
	 * toString method.
	 * This method overrides the default toString method to print out data from the queue.
	 *
	 * @return					The data to print.
	 ********/
	@Override
	public synchronized String toString() {
		String temp = new String();
		for(int i = 0; i < size; i++) {
			temp += data[i] + " ";
		}
		return temp;
	}
	/********
	 * offer Method.
	 * This method adds the object to the priority queue.
	 *
	 * @param obj					The data to add to the queue.
	 * @return						If the data was added.
	 *********/
	public synchronized boolean offer(E obj) {
		//If size is under capacity, continue adding
		if(size < capacity) {
			//Put object in the last position in heap
			data[size] = obj;
			//Set the new object as child
			int child = size;
			//Get the parent for the child
			int parent = (child - 1) / 2;
			//While the parent is the root or less and while the parent is less than the child swap the child and parent
			while(parent >= 0 && data[parent].compareTo(data[child]) > 0) {
				E temp = data[parent];
				data[parent] = data[child];
				data[child] = temp;
				//Set child to parent
				child = parent;
				//Get new parent
				parent = (child - 1) / 2;
			}
			//Increase size
			size++;
			//Return true
			return true;
		}
		//Else reallocate and recall offer
		else {
			this.reallocate();
			return this.offer(obj);
		}
	}
	/************
	 * poll Method.
	 * This method returns the data at the top of the heap.
	 *
	 * @return						The smallest element in the heap.
	 ***********/
	public synchronized E poll() {
		//If heap is empty return null
		if(data[0] == null) {
			return null;
		}
		//Else return the smallest item in the heap
		else {
			//Set temp to the smallest item in the heap.
			E temp = data[0];			
			//Set new smallest item to the largest item in the heap.
			data[0] = data[size - 1];
			//Remove the second instance of the object
			data[size - 1] = null;
			//Decriment size
			size--;
			//Set parent as top of the heap.
			int parent = 0;
			//Call heapify method
			heapify(parent);
			//Return the smallest item
			return temp;
		}
	}
	/*********
	 * remove Method.
	 * This method removed the smallest element from the heap.
	 *
	 * @return							The smallest element in the heap.
	 * @throws NoSuchElementException	Throws NoSuchElementException if queue is empty.
	 *********/
	public synchronized E remove() {
		//If heap is empty throw NoSuchElementException
		if(data[0] == null) {
			throw new NoSuchElementException();
		}
		//Else return smallest item in heap.
		else {
			//Set temp to smallest element.
			E temp = data[0];			
			//Set new smallest element to largest element.
			data[0] = data[size - 1];
			//Remove second instance of largest element.
			data[size - 1] = null;
			//Decriment size
			size--;
			//Set parent to top of the heap.
			int parent = 0;
			//Call heapify method
			heapify(parent);
			//Return smallest element
			return temp;
		}
	}
	/***********
	 * heapify Method.
	 * This method sorts the data in the heap.
	 *
	 * @param parent				The node to sort from.
	 **********/
	private void heapify(int parent) {
		//Left Child
		int childL = (parent * 2) + 1;
		//Right Child
		int childR = (parent * 2) + 2;
		//If the child is smaller then the size
		if(childR <= size) {
			//If the child exists
			if(data[childR] != null) {
				//If the parent is greater than the child then swap
				if(data[parent].compareTo(data[childR]) == 1) {
					E tempS = data[childR];
					data[childR] = data[parent];
					data[parent] = tempS;
					heapify(childR);
				}
			}
			//If the child exists
			if(data[childL] != null) {
				//If the parent is greater than the child then swap
				if(data[parent].compareTo(data[childL]) == 1) {
					E tempS = data[childL];
					data[childL] = data[parent];
					data[parent] = tempS;
					heapify(childL);
				}
			}
		}
	}
	/***********
	 * peek Method.
	 * This method returns the smallest item in the heap without removing it.
	 *
	 * @return				The smallest element.
	 **********/
	public E peek() {
		return data[0];
	}
	/***********
	 * element Method.
	 * This method returns the smallest item in the heap without removing it.
	 *
	 * @return							The smallest element.
	 * @throws NoSuchElementException
	 **********/
	public E element() {
		if(data[0] == null) {
			throw new NoSuchElementException();
		} else {
			return data[0];
		}
	}
	/**********
	 * printQueue Method.
	 * This method prints the queue.
	 *********/
	public synchronized void printQueue() {
		System.out.println();
		int parent = 0;
		if((parent * 2) + 2 < data.length && data[parent] != null) {
			int childL = (parent * 2) + 1;
			int childR = (parent * 2) + 2;
			System.out.println("Root [" + data[parent] + "]\tLeft Child [" + data[childL] + "]\tRight Child [" + data[childR] + "]");
			printQueue(childL);
			printQueue(childR);
		}
	}
	/**********
	 * Overloaded printQueue Method.
	 * This method prints the queue.
	 *
	 * @param parent		The node to print.
	 *********/
	public synchronized void printQueue(int parent) {
		if((parent * 2) + 2 < data.length && data[parent] != null) {
			int childL = (parent * 2) + 1;
			int childR = (parent * 2) + 2;
			System.out.println("Root [" + data[parent] + "]\tLeft Child [" + data[childL] + "]\tRight Child [" + data[childR] + "]");
			printQueue(childL);
			printQueue(childR);
		}
	}
	/***********
	 * linearPrint method.
	 * This method prints the data in the data array.
	 **********/
	public void linearPrint() {
		for(int i = 0; i < size; i++) {
			System.out.print(" [" + data[i] + "] ");
		}
		System.out.println();
	}
	/**********
	 * reallocate Method.
	 * This method increases the size of the heap.
	 *********/
	@SuppressWarnings("unchecked")
	private void reallocate() {
		this.capacity = (capacity * 2) + 1;
		E[] temp = (E[]) new Comparable[capacity];
		for(int i = 0; i < data.length; i++) {
			if(data[i] != null) {
				temp[i] = data[i];
			}
		}
		data = temp;
	}
	/**********
	 * Variables
	 **********/
	private E[] data;
	private int capacity;
	private int size;
	private static final int INITIAL_CAPACITY = 1;
}

