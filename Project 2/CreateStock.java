/****************
 * Data Structures Project 2
 * Created on 10/26/2017 by Leo Stevens
 ***************/

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;

/******************
 * This class creates stocks and adds them to the stack for a particular business.
 * The class has one method which prompts the user for the current stock price and checks for valid input.
 * The method then creates the Stock and adds it to the business.
 *
 * @author Leo Stevens
 * @version 1.0
 *****************/

public class CreateStock {
        /*************
         * Default Constructor.
         * This constructor is not used.
         * The constuctor sets business to null.
         ************/
        public CreateStock() {
                this.business = null;
        }
        /*************
         * Overloaded Constructor.
         * This constructor is used by the StockMenu class.
         * The constructor takes one parameter which is the business to create the stock for.
         *
         * @param business                      The business to add the stock to.
         ************/
        public CreateStock(Business business) {
                this.business = business;
        }
        /**************
         * This method handles user input for stock price, creates the stock, and adds it to the stack.
         * This method is called by the StockMenu class.
         * The method uses InputStreamReader and BufferedReader to handle user input.
         * The method then converts the String to Integer using parseInt.
         * If the input was valid then the stock is added to the stack.
         * 
         * @return                      If they stock was created sucessfully.
         *************/
        public boolean newStock() {
                //Set price and error flag.
                double price = 0.0;
                boolean error = true;
                //Try-Catch to handle IOExceptions
                try {
                        //Open System.in and setup buffer
                        InputStreamReader reader = new InputStreamReader(System.in);
                        BufferedReader console = new BufferedReader(reader);
                        //Do-While loop until input is valid
                        do {
                                //Prompt the user for the stock price.
                                System.out.println("Please enter current stock price: ");
                                String priceString = console.readLine();
                                //Try-Catch for NumberFormatException from parseDouble
                                try {
                                        price = Double.parseDouble(priceString);
                                        //Create the stock
                                        Stock newStock = new Stock(price, business.getCurrentPrice());
                                        //Add it to the business
                                        business.addStock(newStock);
                                        error = false;
                                } catch (NumberFormatException e) {
                                        System.out.println("Error! Please enter a valid number for price.");
                                }
                        } while(error);
                } catch (IOException e) {
                        System.out.println("Error! Error opening input stream!\nExiting!");
                        error = true;
                        System.exit(1);
                }
                return !error;
        }
        /***********
         * Variables
         **********/
        private Business business;                              //The business to create the stock for.
}

