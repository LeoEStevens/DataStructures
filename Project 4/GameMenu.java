/***********************
 * Data Structures Project 4
 * Created on 11/29/2017 by Leo Stevens
 **********************/

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Random;

/*****************
 * GameMenu Class.
 * This class prints out the game menu and handles users inputs.
 *
 * @author Leo Stevens
 * @version 1.0
 *****************/

public class GameMenu {
	/**********
	 * Default Constructor.
	 * --Not Used--
	 * Sets scores to null.
	 *********/
	public GameMenu() {
		this.scores = null;
	}
	/***********
	 * Overloaded Constructor.
	 * This constructor takes in the reference for the scores BST.
	 *
	 * @param scores				The BST of scores.
	 **********/
	public GameMenu(BinarySearchTree<Integer> scores) {
		this.scores = scores;
	}
	/***********
	 * showMenu Method.
	 * This method prints out the menu and handles user input.
	 ***********/
	public void showMenu() {
		//Try-Catch for BufferedReader and InputStreamReader
		try {
			//Create new input handler
			BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
			boolean error = true;
			boolean exit = false;
			//Do-While until the user enters exit
			do {
				int selection = 0;
				String input = new String();
				//Do-While until input is valid
				do {
					//Print out menu
					System.out.println("----Project 4----");
					System.out.println("1) Play a game");
					System.out.println("2) Display current leaderboard");
					System.out.println("3) Search for a score");
					System.out.println("4) Delete a particular score");
					System.out.println("5) Quit");
					System.out.println("-----------------");
					System.out.print("Input> ");
					input = console.readLine();
					//Try to parse int from input
					try {
						selection = Integer.parseInt(input);
						if(selection > 0 && selection < 6) {
							error = false;
						}
					}
					//Catch NFE and rerun loop
					catch (NumberFormatException e) {
						System.out.println("Error! Invalid Input!");
						error = true;
					}
				} while(error);
				//Switch off of input
				switch(selection) {
					//Case 1 - Create new score and print out leaderboard.
					case 1:
						//Create random score between 0-100
						Random rand = new Random();
						int newScore = rand.nextInt(101);
						//Add score
						scores.add(newScore);
						//Print out score that was added
						System.out.println("Score " + newScore + " added!");
						//Print out all scores
						scores.print();
						break;
					//Case 2 - Print leaderboard
					case 2:
						//Call scores print method
						scores.print();
						break;
					//Case 3 - Search for a score
					case 3:
						int search = 0;
						//Do-While until input is valid
						do {
							System.out.print("\nPlease enter score to search for: ");
							input = console.readLine();
							try {
								search = Integer.parseInt(input);
							} catch(NumberFormatException e) {
								System.out.println("Error! Invalid Input!");
								error = true;
							}
						} while(error);
						//Search using search method
						scores.search(search);
						break;
					//Case 4 - Delete a score
					case 4:
						int delete = 0;
						//Do-While until input is valid
						do {
							System.out.print("\nPlease enter score to delete: ");
							input = console.readLine();
							try {
								delete = Integer.parseInt(input);
							} catch(NumberFormatException e) {
								System.out.println("Error! Invalid Input!");
								error = true;
							}
						} while(error);
						//Delete using delete method
						scores.delete(delete);
						break;
					//Case 5 - Exit
					case 5:
						//Set exit flag
						exit = true;
						break;
				}
			} while(!exit);
		} catch(IOException e) {
			System.out.println("Error opening stream!");
			e.printStackTrace();
		}
	}
	/***********
	 * Variables
	 ***********/
	private BinarySearchTree<Integer> scores;
}
