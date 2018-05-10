/************
 * Data Sturctures Project 4
 * Created on 11/29/2017 by Leo Stevens
 ***********/

/*****************
 * BinarySearchTree Class.
 * This class holds the data structure for the project.
 * A BinarySearchTree is created from the subclass Node which holds the data and references.
 * The BinarySearchTree class performs operations on the data.
 *
 * @author Leo Stevens
 * @version 1.0
 ****************/

public class BinarySearchTree<E extends Comparable<E>> {
	/************
	 * Node Class.
	 * The Node class holds the data as well as the references to left, right, and root.
	 ***********/
	private class Node<E> {
		/********
		 * Default Constructor.
		 * --Not Used--
		 *  Sets all values to null.
		 ********/
		private Node() {
			this.data = null;
			this.left = null;
			this.right = null;
			this.root = null;
		}
		/**********
		 * Overloaded Constructor.
		 * This constructor is used in the creation of the root node.
		 *
		 * @param data				The data to hold in the node.
		 *********/
		private Node(E data) {
			this.data = data;
			this.left = null;
			this.right = null;
			this.root = null;
		}
		/**********
		 * Overloaded Constructor.
		 * This constructor is used in creating leaf nodes.
		 *
		 * @param data				The data to hold in the node.
		 * @param root				The root for the leaf node.
		 **********/
		private Node(E data, Node<E> root) {
			this.data = data;
			this.left = null;
			this.right = null;
			this.root = root;
		}
		/********
		 * Node Variables
		 ********/
		private E data;					// The data to hold
		private Node<E> left;			// Left node reference
		private Node<E> right;			// Right node reference
		private Node<E> root;			// Root node reference
	}
	/***********
	 * Default Constructor.
	 * Sets root to null.
	 **********/
	public BinarySearchTree() {
		this.root = null;
	}
	/***********
	 * add Method.
	 * This method is called to add data into the BST.
	 * The method creates the root node if it doesnt exist, 
	 * otherwise it calls the overloaded add method.
	 *
	 * @param data				The data to add.
	 **********/
	public void add(E data) {
		//Check if the root exists
		if(root == null) {
			//Create new root node
			root = new Node<E>(data);
		}
		//Else call overloaded add method
		else {
			this.add(data, root);
		}
	}
	/**********
	 * Overloaded add Method.
	 * This method adds data to the BST.
	 * The method moves down the BST and adds the data where appropriate.
	 *
	 * @param data				The data to add.
	 * @param node				The node which is being evaluated.
	 *********/
	private void add(E data, Node<E> node) {
		//If the data in the node is greater than the data to add
		if(node.data.compareTo(data) == 1) {
			//If the left node is not empty
			if(node.left != null) {
				//Recursive call
				add(data, node.left);
			}
			//Else create a new node for the left node with the data
			else {
				node.left = new Node<E>(data, node);
			}
		}
		//Else if the data in the node is less than the data to add
		else if(node.data.compareTo(data) == -1) {
			//If the right node is not empty
			if(node.right != null) {
				//Recursive call
				add(data, node.right);
			}
			//Else create a new node for the right node with the data
			else {
				node.right = new Node<E>(data, node);
			}
		}
	}
	/***********
	 * getMin Method.
	 * This method gets the minimum value for a particular node.
	 * The method is a helper method for the delete method.
	 *
	 * @param node				The node to evaluate.
	 * @return					The minimum data value for a BST under the node.
	 ***********/
	private E getMin(Node<E> node) {
		//The minimum value
		E min = node.data;
		//Move to the left most node
		while(node.left != null) {
			//Set the minimum
			min = node.left.data;
			//Advance the reference
			node = node.left;
		}
		//Return minimum
		return min;
	}
	/***********
	 * delete Method.
	 * This method is called to delete data from the BST.
	 * The method calls the overloaded method of delete which updates the BST.
	 * This method and the overloaded method were adapted from the tutorials found online at:
	 * http://www.geeksforgeeks.org/binary-search-tree-set-2-delete/
	 *
	 * @param data				The data to delete from the BST.
	 ***********/
	public void delete(E data) {
		this.root = delete(data, root);
	}
	/***********
	 * Overloaded delete Method.
	 * This method moves down the BST finding the data to be deleted.
	 * The method updates the references for the BST and returns the nodes.
	 *
	 * @param data				The data to delete from the BST.
	 * @param node				The node which is being evaluated.
	 * @return					The updated node.
	 **********/
	private Node<E> delete(E data, Node<E> node) {
		//If the node is null then return null
		if(node == null) {
			return null;
		}
		//If the data in the node is greater than the data to delete
		if(node.data.compareTo(data) > 0) {
			//Recursive call to update references
			node.left = delete(data, node.left);
		}
		//Else if the data in the node is less than the data to delete
		else if(node.data.compareTo(data) < 0) {
			//Recursive call to update references
			node.right = delete(data, node.right);
		}
		//Else the data in the node is equal to the data to delete
		else {
			//If the left node is null then return the right node
			if(node.left == null) {
				return node.right;
			}
			//Else if the right node is null return the left node
			else if (node.right == null) {
				return node.left;
			}
			//Else both nodes are full so replace the data with the in order successor
			node.data = getMin(node.right);
			//Delete in order successor
			node.right = delete(node.data, node.right);
		}
		//Return updated reference
		return node;
	}
	/**************
	 * print Method.
	 * This method uses post order traversal to print the BST from greatest to smallest.
	 * The method uses a static counter which provides rank on the leaderboard.
	 *************/
	public void print() {
		//Reset counter
		counter = 0;
		//If root is not null
		if(root != null) {
			//Call post order traversal
			this.postOrderTraversal(root);
		}
		//Else notify user of empty leaderboard.
		else {
			System.out.println("The leaderboard is empty.");
		}
	}
	/************
	 * search Method.
	 * This method searches the BST for data.
	 * The method recursively searches the BST using the overloaded search method.
	 *
	 * @param data				The data to search for
	 ***********/
	public void search(E data) {
		//If root is null then notify user of empty leaderboard
		if(root == null) {
			System.out.println("The leaderboard is empty.");
			return;
		}
		//Reset counter
		counter = 0;
		//Search for data
		int found = this.search(data, root);
		//No matches
		if(found == 0) {
			System.out.println("Value not found.");
		}
		//A match
		else {
			System.out.println("--" + found + " score found!--");
		}
	}
	/**************
	 * Overloaded search Method.
	 * This method recursively moves down the BST searching for data.
	 *
	 * @param data				The data to search for
	 * @param node				The node which is being evaluated
	 * @return					The number of matches
	 *************/
	private int search(E data, Node<E> node) {
		int found = 0;
		//If right node is not null and greater than the data to search for
		if(node.right != null) {
			//Recursive call
			found += search(data, node.right);
		}
		//Inc counter
		counter++;
		//Else if the node data equals the search data 
		if(node.data.equals(data)) {
			//Print out information
			System.out.println(counter + ": " + node.data);
			found++;
		}
		//Else if left node is not null and less than the data to search for
		if(node.left != null) {
			//Recursive call
			found += search(data, node.left);
		}
		//Return found
		return found;
	}
	/**************
	 * postOrderTraversal Method.
	 * This method prints out the top 10 elements from the BST from greatest to smallest.
	 *
	 * @param node					The node to print.
	 *************/
	private void postOrderTraversal(Node<E> node) {
		//If right node is not null
		if(node.right != null) {
			//Recursive call on right node
			postOrderTraversal(node.right);
		}
		//Inc counter
		counter++;
		//If counter is 10 or less then print the node data
		if(counter <= 10) {
			System.out.println(counter + ": " + node.data);
		}
		//Else if the counter is greater than 25, remove the data
		else if(counter > 25) {
			this.delete(node.data);
		}
		//If left node is not null
		if(node.left != null) {
			//Recursive call on left node
			postOrderTraversal(node.left);
		}
	}
	/**************
	 * inOrderTraversal Method.
	 * This method prints out the top 10 elements from the BST from smallest to largest.
	 *
	 * @param node					The node to print.
	 *************/
	private void inOrderTraversal(Node<E> node) {
		//If left node is not null
		if(node.left != null) {
			//Recursive call on left node
			inOrderTraversal(node.left);
		}
		//Inc counter
		counter++;
		//If counter is 10 or less then print the node data
		if(counter <= 10) {
			System.out.println(counter + ": " + node.data);
		}
		//If right node is not null
		if(node.right != null) {
			//Recursive call on right node
			inOrderTraversal(node.right);
		}
	}
	/************
	 * BST Variables
	 ***********/
	private static int counter;					//A static counter used in numbering elements
	private Node<E> root;						//The root node
}
