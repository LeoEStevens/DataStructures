/*************
 * Data Structures Project 3
 * Created on 11/18/2017 by Leo Stevens
 ************/

import java.time.LocalDateTime;
import java.util.Random;

/*****************
 * RemoveFromQueue Class
 * This class handles the thread that removes passengers from the queue for boarding.
 *
 * @author Leo Stevens
 * @version 1.0
 ****************/

public class RemoveFromQueue implements Runnable {
	/************
	 * Overloaded Constructor.
	 * Takes queue as parameter.
	 *
	 * @param queue			The queue to remove passengers from.
	 ************/
	public RemoveFromQueue(PriorityQueue<Passenger> queue, Passenger[] boardedOrder) {
		this.queue = queue;
		this.boardedOrder = boardedOrder;
		this.capacity = boardedOrder.length;
	}
	/************
	 * boardedAdd Method.
	 * This method adds passengers to the array as they are boarded.
	 *
	 * @param in			The passenger to add to the list.
	 ***********/
	private void boardedAdd(Passenger in) {
		if(counter >= capacity) {
			capacity *= 2;
			Passenger[] temp = new Passenger[capacity];
			for(int i = 0; i < boardedOrder.length; i++) {
				temp[i] = boardedOrder[i];
			}
			boardedOrder = temp;
		}
		boardedOrder[counter] = in;
	}
	/************
	 * run Method
	 * Starts the thread that removes passengers from the queue.
	 ************/
	@Override
	public void run() {
		System.out.println("RemoveFromQueue Thread Running");
		//Get start time in seconds
		int start = LocalDateTime.now().getSecond();
		//The time that the thread has been running
		int time = 0;
		counter = 0;
		//While time is less than max time, continue removing passengers
		while(time < MAX_TIME) {
			//Check is queue has data
			if(queue.peek() != null) {
				//Clear line
				System.out.print("\033[2K");
				//Remove from queue
				boardedAdd(queue.poll());
				counter++;
				//Print new queue
				System.out.print("\r" + queue);
			} else {
				//Notify of empty queue
				System.out.print("\rThe queue is empty.");
			}
			//Get current time
			int now = LocalDateTime.now().getSecond();
			//If the clock has cycled, then add 59 seconds
			if(now < start) {
				now += 59;
			}
			//Get difference between now and start
			time = now - start;
			//Sleep the thread
			try {
				Thread.sleep(1000); 
			} catch(InterruptedException e) {
				System.out.println("Error! Thread was interrupted!");
				e.printStackTrace();
			}
		}
	}
	/*********
	 * Variables
	 ********/
	private static final int MAX_TIME = 10;				//The max time the thread will run in seconds
	private PriorityQueue<Passenger> queue;				//The queue to process
	private Passenger[] boardedOrder;
	private int capacity;
	private int counter;
}
