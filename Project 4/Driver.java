/************
 * Data Structures Project 4
 * Created on 11/29/2017 by Leo Stevens
 ************/

/**********
 * Driver Class.
 * This class creates the BST and calls the menu.
 *
 * @author Leo Stevens
 * @version 1.0
 *********/
public class Driver {
	public static void main(String[] args) {
		//Create BST
		BinarySearchTree<Integer> scores = new BinarySearchTree<>();
		//Create Menu
		GameMenu menu = new GameMenu(scores);
		//Call show menu
		menu.showMenu();
	}
}
