/************
 * Data Structures Project 2
 * Created on 10/25/2017 by Leo Stevens
 ************/

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**************
 * This class holds all of the information about the Business.
 * The class has methods to handle various operations with the stack as well as data for the business.
 * Serializable to be read by ObjectInputStream and written by ObjectOutputStream.
 *
 * @author Leo Stevens
 * @version 1.0
 *************/

public class Business implements Serializable {
        /***********
         * Default Constructor.
         * This constructor is not used.
         * The constructor creates a new StockStack and sets all other variables to null.
         **********/
        public Business() {
                this.stack = new StockStack();
                this.iD = null;
                this.password = null;
                this.name = null;
        }
        /***********
         * Overloaded Constructor.
         * This constructor is used by the CreateBusiness class.
         * The constructor uses the stock price to create a new stock and add it to the stack.
         *
         * @param iD                    The ID of the business.
         * @param password              The password of the business.
         * @param name                  The name of the business.
         * @param stockPrice            The current stock price.
         **********/
        public Business(String iD, String password, String name, double stockPrice) {
                this.iD = iD;
                this.password = new Password(password);
                this.name = name;
                this.stack = new StockStack();
                Stock temp = new Stock(stockPrice, 0);
                stack.push(temp);
        }
        /************
         * This method adds a stock to the businesses StockStack.
         * The method uses the StockStack method push.
         *
         * @param inStock               The stock to add to the stack.
         ***********/
        public void addStock(Stock inStock) {
                stack.push(inStock);
        }
        /*************
         * This method gets the previous price of the stock on the top of the stack.
         * The method uses the StockStack method peek and the Stock method getPrevPrice.
         *
         * @return                      The previous price for the stock that is on the top of the stack.
         ************/
        public double getPrevPrice() {
                return stack.peek().getPrevPrice();
        }
        /************
         * This method prints out the entire stack in LIFO order.
         * The method uses the StockStack method printStack.
         ***********/
        public void printStocks() {
                stack.printStack();
        }
        /***********
         * This method compares the business ID to a user entered ID.
         * This method is used in the Login class for logging in to a business.
         *
         * @param inID                  The user entered id.
         * @return                      If the IDs match.
         ***********/
        public boolean checkID(String inID) {
                return (iD.equals(inID));
        }
        /************
         * This method compares the password to a user entered password.
         * The method uses the Password class checkPassword method.
         *
         * @param inPassword            The user entered password.
         * @return                      If the passwords match.
         ***********/
        public boolean checkPassword(String inPassword) {
                return (password.checkPass(inPassword));
        }
        /***********
         * This method returns the name of the business.
         *
         * @return                      The name of the business.
         **********/
        public String getName() {
                return name;
        }
        /**********
         * This method returns the current stock price for the business.
         * The method uses the StockStack method peek and the Stock classes getPrice methods.
         *
         * @return                      The current stock price.
         *********/
        public double getCurrentPrice() {
                return stack.peek().getPrice();
        }
        /*********
         * This method prints the current stock.
         * The method uses the printStock method from the Stock class.
         ********/
        public void printCurrentStock() {
                stack.peek().printStock();
        }
        /********
         * This method returns the date of the stock at top of the stack.
         * The method uses the getDay method from the Stock class.
         *
         * @return                      The day for the current top of the stack
         *******/
        public LocalDateTime getLastStockDate() {
                return stack.peek().getDay();
        }
        /*************
         * This method recusively searches the stack, printing out all stacks for the current date..
         * The recursion ends when the stack is empty or the day does not match today.
         * The stocks are printed in the order that they were added to the stack (LIFO).
         ************/
        public void getTodaysStocks() {
                //If the stack isnt null then continue
                if(stack.peek() != null) {
                        //Get the current date
                        DateTimeFormatter format = DateTimeFormatter.ofPattern("MM/dd/yyyy");
                        LocalDate todayD = LocalDate.now();
                        //Format the current date
                        String today = format.format(todayD);
                        //If the today matches the day of the stock
                        if(today.equals(format.format(stack.peek().getDay()))) {
                                //Pop the stock out of the stack
                                Stock temp = stack.pop();
                                //Print the stock
                                temp.printStock();
                                //Recursive call
                                getTodaysStocks(today, format);
                                //Push the stock back onto the stack.
                                stack.push(temp);
                        }
                }
        }
        /*************
         * This method recusively searches the stack, printing out all stacks for the current date..
         * The recursion ends when the stack is empty or the day does not match today.
         * The stocks are printed in the order that they were added to the stack (LIFO).
         ************/
        private void getTodaysStocks(String today, DateTimeFormatter format) {
                //If the stack isnt null then continue
                if(stack.peek() != null) {
                        //If the today matches the day of the stock
                        if(today.equals(format.format(stack.peek().getDay()))) {
                                //Pop the stock out of the stack
                                Stock temp = stack.pop();
                                //Print the stock
                                temp.printStock();
                                //Recursive call
                                getTodaysStocks(today, format);
                                //Push the stock back onto the stack.
                                stack.push(temp);
                        }
                }
        }

        /************
         * This method prints the current lowest or highest stock price for the day.
         * The method creates the formatter and string for today and passes on the references to the overloaded method which is used in the recursion.
         * The first item is assumed to be the low or high and placed in a seperate reference then it is popped off the stack in to a second reference.
         * The appropriate method is then called to continue searching down the stack.
         * Once the getLow or getHigh method is finished, then the stock which was initially popped off the stack is pushed back on to the stack.
         *
         * @param highOrLow             A flag of what to search for, the highest(highOrLow == 1) or lowest(highOrLow == 0) stock.
         ***********/
	public void printTodaysHighOrLow(int highOrLow) {
                //Check if stack is null
                if(stack.peek() != null) {
                        //Set the formatter pattern
                        DateTimeFormatter format = DateTimeFormatter.ofPattern("MM/dd/yyyy");
                        //Get todays date
                        LocalDate todayD = LocalDate.now();
                        //String to hold formatted todays date
                        String today = format.format(todayD);
                        //Check if today is equal to the first item on the stacks day
                        if(today.equals(format.format(stack.peek().getDay()))) {
                                //Set that first item on the stack as the low
                                Stock top = stack.peek();
                                //Pop the item off the stack
                                Stock temp = stack.pop();
                                if(highOrLow == 0) {
                                        //Call overloaded getLow method
                                        getLow(today, format, top);
                                } else {
                                        getHigh(today,format, top);
                                }
                               //Push the item back on the stack
                                stack.push(temp);
                        }
                        //No stocks were created today
                        else {
                                System.out.println("Error! No stocks created today.");
                        }
                }
                //No stocks created ever
                else {
                        System.out.println("Error! No stocks created.");
                }
        }
        /***********
         * This method is the recursive method to print the lowest stock.
         * The method continues down the stack until the end of the stack is reached or we have run into a different day, then the method prints the lowest stock found.
         *
         * @param today                 The string of todays date, formatted.
         * @param format                The formatter used to format the dates.
         * @param currentLow            The current lowest stock found.
         **********/
        private void getLow(String today, DateTimeFormatter format, Stock currentLow) {
                //Check if stack is null
                if(stack.peek() != null) {
                        //Check if the dates are equal
                        if(today.equals(format.format(stack.peek().getDay()))) {
                                //Pop the top of the stack into a temp Stock
                                Stock temp = stack.pop();
                                //If the temp stock is lower than the current low then set it as the new low
                                if(temp.getPrice() < currentLow.getPrice()) {
                                        currentLow = temp;
                                }
                                //Recursion call
                                getLow(today, format, currentLow);
                                //Push item back on stack
                                stack.push(temp);
                        }
                        //The method has run into a different day so print the current low
                        else {
                                currentLow.printStock();
                        }
                }
                //The method has reached the bottom of the stack so print the current low
                else {
                        currentLow.printStock();
                }
        }
        /**********
         * This method is the recursive to print the highest stock.
         * The method continues down the stack until the end of the stack is reached or we have run into a different day, then the method prints the highest stock found.
         *
         * @param today                 The string of todays date, formatted.
         * @param format                The formatter used to format the dates.
         * @param currentHigh            The current highest stock found.
         *********/
        private void getHigh(String today, DateTimeFormatter format, Stock currentHigh) {
                //Check if the stack is not empty
                if(!stack.empty()) {
                        //Check if today and the top of the stacks formatted day are equal
                        if(today.equals(format.format(stack.peek().getDay()))) {
                                //Pop an item off the stack
                                Stock temp = stack.pop();
                                //If it is higher than the current high then update the reference
                                if(temp.getPrice() > currentHigh.getPrice()) {
                                        currentHigh = temp;
                                }
                                //Recursive call
                                getHigh(today, format, currentHigh);
                                //Push item back on the stack
                                stack.push(temp);
                        }
                        //The method has reached a stock which is not today so print the current high
                        else {
                                currentHigh.printStock();
                        }
                }
                //The method has reached the end of the stack so print the current high
                else {
                        currentHigh.printStock();
                }
        }

        /*******
        * Variables
        ********/
        private StockStack stack;                       //The stack of stocks.
        private String iD;                              //The businesses unique ID
        private Password password;                      //The password for the business.
        private String name;                            //The name of the business.
}
