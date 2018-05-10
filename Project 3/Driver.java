/**************
 * Data Structures Project 3
 * Created on 11/18/2017 by Leo Stevens
 *************/

/*************
 * Driver class.
 * This class starts the Passenger queue.
 *
 * @author Leo Stevens
 * @version 1.0
 ************/

public class Driver {
	public static void main(String[] args) {
		Passenger[] arrivedOrder = new Passenger[100];
		Passenger[] boardedOrder = new Passenger[100];
		//Create a new priority queue
		PriorityQueue<Passenger> queue = new PriorityQueue<>();
		//Create add thread
		Thread addThread = new Thread(new AddToQueue(queue, arrivedOrder));
		//Create remove thread
		Thread removeThread = new Thread(new RemoveFromQueue(queue, boardedOrder));
		//Start threads
		addThread.start();
		removeThread.start();
		//Wait till threads end
		try {
			addThread.join(); 
			removeThread.join();
		} catch (InterruptedException e) {
			System.out.println("Error! Thread was interrupted!");
			e.printStackTrace();
		}
		//Print queue
		queue.printQueue();
		queue.linearPrint();
		System.out.println("Order in which the passengers arrived: ");
		for(int i = 0; i < arrivedOrder.length && arrivedOrder[i] != null; i++) {
			System.out.println(i + " - " + arrivedOrder[i]);
		}
		System.out.println("Order in which passengers were boarded: ");
		for(int i = 0; i < boardedOrder.length && boardedOrder[i] != null; i++) {
			System.out.println(i + " - " + boardedOrder[i]);
		}

	}
}
