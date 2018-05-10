/**********
 * Data Structures Project 2
 * Created on 10/26/2017 by Leo Stevens.
 *********/

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;

/*******************
 * A Class to print the menu and handle input for the Stock program. 
 * The class has a single method which prints the menu and handles all of the input.
 * This class is called by the Driver class.
 * This class calls the CreateBusiness, Login, CreateStock, and BusinessWriter classes.
 *
 * @author Leo Stevens
 * @version 1.0
 *******************/

public class StockMenu {
        /************
         * Default Constructor.<br>
         * This constructor is not used.
         * The constructor sets list to null and currentBusiness to -1.
         ***********/
        public StockMenu() {
                this.list = null;
                this.currentBusiness = -1;
        }
        /************
         * Overloaded Constructor.<br>
         * This constructor is called when BusinessList exists but user has not logged in to a business.
         * The constructor sets the currentBusiness to -1.
         *
         * @param list                  BusinessList of businesses which have been created or read in from file.
         ***********/
        public StockMenu(BusinessList list) {
                this.list = list;
                this.currentBusiness = -1;
        }
        /***********
         * Overloaded Constructor.<br>
         * This constructor is called only on first run when the first Business is created.
         * The user is automatically logged in to that business.
         *
         * @param list                  BusinessList of businesses which have been created or read in from file.
         * @param currentBusiness       Index of the business which the user had logged in to. 
         ***********/
        public StockMenu(BusinessList list, int currentBusiness) {
                this.list = list;
                this.currentBusiness = currentBusiness;
        }
        /*********
         * This method creates and prints the menu.
         * The method reads user input using InputStreamReader and BufferedReader.
         * Converts user input from String to int using Integer.parseInt.
         * Then the method uses a switch statement to call the approriate class and method.
         * The method loops until user has entered seven, then the method saves the data using the BusinessWriter class and exits.
         *********/
        public void createMenu() {
                //Try-Catch Loop for BufferedReader and InputStreamReader Exceptions
                try {
                        InputStreamReader reader = new InputStreamReader(System.in);
                        BufferedReader console = new BufferedReader(reader);
                        //Boolean for menu exit
                        boolean exit = false;
                        //Do-While loop to print the menu until 7 is entered
                        do {
                                //Print menu
                                System.out.println();
                                System.out.println("-----Stock Program-----");
                                System.out.println("1) Create a new business");
                                System.out.println("2) Login to an existing business");
                                //If not logged in to a business then dont print business related functions
                                if(currentBusiness != -1) {
                                        System.out.println("3) Add stock price to business");
                                        System.out.println("4) Display prices for current day");
                                        System.out.println("5) Display highest stock price for the day");
                                        System.out.println("6) Display lowest stock price for the day");
                                }
                                System.out.println("7) Quit");
                                System.out.println("-----------------------");
                                System.out.print("Input> ");
                                //Read in input
                                String inputString = console.readLine();
                                int input;
                                //Try-Catch to handle integer parsing
                                try {
                                        input = Integer.parseInt(inputString);
                                } catch (NumberFormatException e) {
                                        System.out.println("Error! Invalid Input");
                                        continue;
                                }
                                //If not logged into a business, then 1, 2, and 7 are valid inputs
                                //If anything other than those numbers are entered then print error message and continue loop
                                if(currentBusiness == -1) {
                                        if(input < 1 || input > 2 && input != 7) {
                                                System.out.println("Error! Invalid Input");
                                                continue;
                                        }
                                }
                                //Else 1-7 are valid inputs
                                //If anything other than those numbers are entered then print error message and continue loop
                                else {
                                        if(input < 1 || input > 7) {
                                                System.out.println("Error! Invalid Input");
                                                continue;
                                        }
                                }
                                //Switch off of input
                                switch (input) {
                                                //Case 1 - Create a new Business using CreateBusiness class
                                                case 1:
                                                        CreateBusiness newBusiness = new CreateBusiness(list);
                                                        currentBusiness = newBusiness.newBusiness();
                                                        break;
                                                //Case 2 - Login to an existing business using Login class
                                                case 2:
                                                        Login newLogin = new Login(list);
                                                        currentBusiness = newLogin.newLogin();
                                                        break;
                                                //Case 3 - Add a stock price to current business using CreateStock class
                                                case 3:
                                                        System.out.println("Add a stock price to " + list.get(currentBusiness).getName());
                                                        CreateStock newStock = new CreateStock(list.get(currentBusiness));
                                                        if(newStock.newStock()) {
                                                                BusinessWriter writer = new BusinessWriter(list);
                                                                if(writer.write()) {
                                                                        System.out.println("Stock prices have been saved!");
                                                                } else {
                                                                        System.out.println("Failed to save stock prices...");
                                                                }
                                                        }
                                                        break;
                                                //Case 4 - Print out todays stock prices for current business using GetCurrentPrices class
                                                case 4:
                                                        System.out.println("------Todays Stocks-----");
                                                        list.get(currentBusiness).getTodaysStocks();
                                                        System.out.println();
                                                        System.out.print("Press <Enter> to continue...");
                                                        console.readLine();
                                                        break;
                                                //Case 5 - Print out todays highest stock price for current business using GetTodaysHigh class
                                                case 5:
                                                        System.out.println("-----Todays Highest Stock Price-----");
                                                        list.get(currentBusiness).printTodaysHighOrLow(1);
                                                        System.out.println();
                                                        System.out.print("Press <Enter> to continue...");
                                                        console.readLine();
                                                        break;
                                                //Case 6 - Print out todays lowest stock price for current business using GetTodaysLow class
                                                case 6:
                                                        System.out.println("-----Todays Lowest Stock Price-----");
                                                        list.get(currentBusiness).printTodaysHighOrLow(0);
                                                        System.out.println();
                                                        System.out.print("Press <Enter> to continue...");
                                                        console.readLine();
                                                        break;
                                                //Case 7 - Write the BusinessList object using BusinessWriter class and exit
                                                case 7:
                                                        BusinessWriter writer = new BusinessWriter(list);
                                                        //If write was successfull then exit
                                                        if(writer.write()) {
                                                                System.out.println("Stock prices have been saved!");
                                                                System.out.println("Goodbye.");
                                                                exit = true;
                                                        }
                                                        //Else inform user and ask if they want to exit anyway
                                                        else {
                                                                System.out.println("Failed to save stock prices...");
                                                                System.out.println("Exit anyway? (Y/n");
                                                                String exitCheck = console.readLine();
                                                                if(exitCheck.equals("n") || exitCheck.equals("N")) {
                                                                        exit = true;
                                                                        break;
                                                                }
                                                        }
                                                        break;
                                        
                                }
                        } while(!exit);
                } catch (IOException e) {
                        System.out.println("Error! Error opening Input/Output stream!\nExiting!");
                        e.printStackTrace();
                        System.exit(1);
                }
        }
        /********
         * Variables
         ********/
        private BusinessList list;                      //The list of businesses that have been created.
        private int currentBusiness;                    //The current business that the user is logged in to.
}
