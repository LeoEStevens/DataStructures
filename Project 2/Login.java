/************
 * Data Structures Project 2
 * Created on 10/26/2017 by Leo Stevens
 ***********/

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/*****************
 * This class logs a user in to a Business.
 * This class has one public method which gets the username and password entered and checks it against the list of businesses.
 * This class is called by the StockMenu class.
 *
 * @author Leo Stevens
 * @version 1.0
 ****************/

public class Login {
        /***********
         * Default Constuctor.
         * This constructor is not used.
         * The contructor sets the list to null.
         **********/
        public Login() {
                this.list = null;
        }
        /***********
         * Overloaded Constructor.
         * This constructor takes the list as a parameter.
         *
         * @param list                  The BusinessList which contains the businesses to log in to.
         **********/
        public Login(BusinessList list) {
                this.list = list;
        }
        /************
         * This method prompts the user for username and password and checks them against the list of businesses.
         * Uses InputStreamReader and BufferedReader to read in users input.
         * Then uses the private loginCheck method to compare them to the log in information contained in the list of businesses.
         *
         * @return                      An the index of the business the user logged in to.
         ***********/
        public int newLogin() {
                int business = -1;
                //Try-Catch loop for IOExceptions from InputStreamReader.
                try {
                        //Open System.in and set BufferedReader to read input.
                        InputStreamReader reader = new InputStreamReader(System.in);
                        BufferedReader console = new BufferedReader(reader);
                        //Strings for username and password
                        String username;
                        String password;
                        //Do-While loop until the user is logged in.
                        do {
                                //Get username
                                System.out.println("-----Login-----");
                                System.out.println("Type exit in the username exit to exit to the menu.");
                               System.out.print("Please enter username: ");
                               username = console.readLine();
                               //Get password
                               if(!username.equals("exit")) {
                                        System.out.print("Please enter password: ");
                                        password = console.readLine();
                                        //Check the login info and get business
                                        business = this.loginCheck(username, password);  
                               }
                        } while(business == -1 && !username.equals("exit"));
                } catch (IOException e) {
                        System.out.println("Error opening input stream!\nExiting!");
                        System.exit(1);
                }
                //Return the business the user logged in to
                return business;
        }
        /***************
         * This method checks the user inputted username and password against the list of businesses.
         * Uses Business.checkID and Business.checkPassword methods which return true/false if the login information is correct.
         * If they are both correct then print out the business name and the latest stock price.
         * If the latest stock is not from today the method will create a new stock from today at 10:00 am and add that to the stack.
         * The method then prints the information about the latest stock.
         *
         * @param String                        The username entered by the user.
         * @param String                        The password entered by the user.
         * @return                              The index of the business the user logged in to.
         *************/
        private int loginCheck(String username, String password) {
                //For loop to go through the list of businesses
                for(int i = 0; i < list.size(); i++) {
                        //Check if the username is equal
                        if(list.get(i).checkID(username)) {
                                //Check if the password is equal
                                if(list.get(i).checkPassword(password)) {
                                        //Print name of business the user logged in to
                                        System.out.println("\nLogged in to " + list.get(i).getName() + "!");
                                        //Check if the stack contains any Stocks
                                        if(list.get(i).getLastStockDate() != null) {
                                                //Print the latest stock information
                                                System.out.println("Latest stock price: ");
                                                //Get the time and date
                                                LocalDate todayD = LocalDate.now();
                                                //Set the format for the date, ignoring the time
                                                DateTimeFormatter format = DateTimeFormatter.ofPattern("MM/dd/yyyy");
                                                //format the date
                                                String today = format.format(todayD);
                                                //Compare the current day to the last stock entered on the stack
                                                //If they are not equal then create a new stock with todays date and the time set to 10:00 am
                                                if(!today.equals(format.format(list.get(i).getLastStockDate()))) {
                                                        list.get(i).addStock(
                                                                        new Stock(list.get(i).getCurrentPrice(), 
                                                                                list.get(i).getPrevPrice(), 
                                                                                LocalDateTime.now().withHour(10).withMinute(00).withSecond(00)));
                                                }
                                                //Print information about the last stock added to the stack
                                                list.get(i).printCurrentStock();
                                        }
                                        System.out.println();
                                        //Return index of business user logged in to
                                        return i;
                                }
                        }
                }
                //If the username or password didn't match any in the list then notify the user and return -1
                System.out.println("Error! Invalid username or password!");
                return -1;
        }
        /**********
         * Variables
         *********/
        private BusinessList list;                      //A list of businesses that have been created.
}
