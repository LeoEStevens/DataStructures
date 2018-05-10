/**********
 * Data Structures Project 3
 * Created on 11/18/2017 by Leo Stevens
 *********/

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**************
 * Passenger Class.
 * This class holds information about the Passenger.
 * Automatically calculates priority based on passenger class.
 *
 * @author Leo Stevens
 * @version 1.0
 *************/

public class Passenger implements Comparable<Passenger> {
	/********
	 * Default Constructor.
	 * --Not Used--
	 * Sets all values to null
	 *******/
	public Passenger() {
		this.firstName = null;
		this.lastName = null;
		this.dob = null;
		this.bordingCategory = null;
		this.priority = -1;
	}
	/*******
	 * Overloaded Constructor.
	 * Takes first name, last name, dob, and bording category as parameters. Calculates priority based on category.
	 *
	 * @param firstName			The passengers first name.
	 * @param lastName			The passengers last name.
	 * @param dob				The passengers date of birth.
	 * @param bordingCategory	The passengers bording category.
	 *******/
	public Passenger(String firstName, String lastName, LocalDateTime dob, String bordingCategory) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.dob = dob;
		this.bordingCategory = bordingCategory;
		this.priority = this.setPriority();
	}
	/**********
	 * setPriority Method.
	 * This method sets the priority based on bording class.
	 *
	 * @return			The priority.
	 *********/
	private int setPriority() {
		LocalDateTime today = LocalDateTime.now();
		LocalDateTime elderlyCheck = today.minusYears(65);
		LocalDateTime adultCheck = today.minusYears(18);
		if(this.bordingCategory.contains("infant")) {
			return 1;
		}
		else if(this.bordingCategory.contains("Elderly") && (dob.equals(elderlyCheck) || dob.isBefore(elderlyCheck))) {
			return 5;
		} else if(this.bordingCategory.contains("Armed Forces") && (dob.equals(adultCheck) || dob.isBefore(adultCheck))) {
			return 10;
		} else if(this.bordingCategory.contains("First Class")) {
			return 15;
		} else {
			return 20;
		}
	}
	/*********
	 * printPassenger Method.
	 * This method prints out the passenger information.
	 ********/
	public void printPassenger() {
		DateTimeFormatter format = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		System.out.println("Passenger: " + lastName + ", " + firstName + " DOB: " + format.format(dob));
		System.out.println("Bording Class: " + bordingCategory + " Priority: " + priority);
	}
	/**********
	 * toString Method.
	 * This method returns a string for the Passenger.
	 *
	 * @return				The string of passenger information.
	 *********/
	@Override
	public String toString() {
		DateTimeFormatter format = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		return lastName + " [" + priority + "]";
	}
	/*********
	 * getPriority Method.
	 * This method returns the priority of the passenger.
	 *
	 * @return				The priority.
	 ********/
	public int getPriority() {
		return priority;
	}
	/*********
	 * compareTo Method.
	 * This method compares two passenger objects.
	 * @param o				The passenger to compare against.
	 * @return				If the passengers priority is geater than the other passenger.
	 *********/
	@Override
	public int compareTo(Passenger o) {
		return new Integer(priority).compareTo(o.getPriority());
	}
	/********
	 * Variables
	 *******/
	private String firstName;
	private String lastName;
	private LocalDateTime dob;
	private String bordingCategory;
	private int priority;
}
