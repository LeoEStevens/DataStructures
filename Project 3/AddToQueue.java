/************
 * Data Structures Project 3
 * Created on 11/18/2017 by Leo Stevens
 ***********/
import java.time.LocalDateTime;
import java.util.Random;
/***********
 * AddToQueue Class.
 * This class adds passengers to the queue.
 *
 * @author Leo Stevens
 * @version 1.0
 **********/

public class AddToQueue implements Runnable {
	/*********
	 * Overloaded Constructor.
	 * Takes the queue as a parameter to add passengers into.
	 *
	 * @param queue				The queue of passengers.
	 *********/
	public AddToQueue(PriorityQueue<Passenger> queue, Passenger[] arrivedOrder) {
		this.queue = queue;
		this.arrivedOrder = arrivedOrder;
		this.capacity = arrivedOrder.length;
	}
	/************
	 * arrivedAdd method.
	 * This method adds passengers to the array as they arrive.
	 *
	 * @param in				The passenger to add to the array.
	 ***********/
	private void arrivedAdd(Passenger in) {
		if(counter >= capacity) {
			capacity *= 2;
			Passenger[] temp = new Passenger[capacity];
			for(int i = 0; i < arrivedOrder.length; i++) {
				temp[i] = arrivedOrder[i];
			}
			arrivedOrder = temp;
		}
		arrivedOrder[counter] = in;
	}
	/********
	 * run Method.
	 * This method starts the AddToQueue thread.
	 *******/
	@Override
	public void run() {
		System.out.println("AddToQueue Thread Running");
		//Create passenger creator
		CreatePassenger creator = new CreatePassenger();
		//Start time
		int start = LocalDateTime.now().getSecond();
		int time = 0;
		counter = 0;
		//Loop until counter reaches max passengers or time equals max time
		while(counter <= MAX_PASSENGERS && time < MAX_TIME) {
			//Create new passenger
			Passenger arrived = creator.create();
			//Add passenger to order array
			this.arrivedAdd(arrived);
			//Clear line
			System.out.print("\033[2K");
			//Add passenger to queue
			queue.offer(arrived);
			//Incremement counter
			counter++;
			//Print out new queue
			System.out.print("\r" + queue);
			int now = LocalDateTime.now().getSecond();
			if(now >= start) {
					time = now - start;
			} else {
				time = (now + 59) - start;
			}
			try {
				Thread.sleep(300); 
			} catch(InterruptedException e) {
				System.out.println("Error! Thread was interrupted!");
				e.printStackTrace();
			}
		}
	}
	/**********
	 * Variables
	 *********/
	private PriorityQueue<Passenger> queue;
	private static final int MAX_PASSENGERS = 15;
	private static final int MAX_TIME = 10;
	private Passenger[] arrivedOrder;
	private static final int INITIAL_CAPACITY = 10;
	private int capacity;
	private int counter;
}
