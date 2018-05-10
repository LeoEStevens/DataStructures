/******************
 * Data Stuctures Project 2
 * Created on 10/26/2017 by Leo Stevens
 *****************/

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.text.NumberFormat;

/***************
 * Stock Class
 * @author Leo Stevens
 * @version 1.0
 *
 * Object to hold information about a stock.
 * Holds information about the day and time, current price, previous price, and and price difference between the two prices in variables. 
 * The information about the day and time are stored in a LocalDateTime Object.
 * The class has a method to print out information about the stock using system.out.println with the day and time being formatted by DateTimeFormatter and price formatted by DecimalFormat.
 * The class also has getters for the day, current price, and previous price.
 * Also holds date and time for the day it was created.
 * Serializable to be stored using ObjectOutputStream and read by ObjectInputStream.
 **************/

public class Stock implements Serializable {
        /***************
         * Default Constuctor.<br>
         * This constructor is not used.
         * Sets all prices in the object to zero and sets the day to null.
         **************/
        public Stock() {
                this.prevPrice = 0.0;
                this.currentPrice = 0.0;
                this.priceDiff = 0.0;
                this.day = null;
        }
        /**************
         * Overloaded Constructor.<br>
         * This constructor takes the current and previous price in as paramters. 
         * Used in CreateStock and Business classes to create new stocks.
         * The constructor gets the current date and time information from LocalDateTime.now(). 
         * Then that the constuctor calculates the price difference between the two prices.
         *
         * @param currentPrice           The current price of the stock.
         * @param prevPrice              The previous price of the stock.
         *************/
        public Stock(double currentPrice, double prevPrice) {
                this.day = LocalDateTime.now();
                this.currentPrice = currentPrice;
                this.prevPrice = prevPrice;
                this.priceDiff = currentPrice - prevPrice;
        }
        /*************
         * Overloaded Constructor.<br>
         * This overloaded contstructor is only called when copying over the price from the previous day into the current day.
         * Used in the Login class when the top stock of the current businesses stack is not the day that the program is being run.
         * The stock's LocalDateTime is set to 10:00 am of the current day.
         * This constructor takes the current price, previous price, and day as paramters.
         * The constructor calculates the price difference between the two prices.
         *
         * @param currentPrice          The current price of the stock.
         * @param prevPrice             The previous price of the stock.
         * @param day                   The LocalDateTime object for the day of the stock.
         *************/
        public Stock(double currentPrice, double prevPrice, LocalDateTime day) {
                this.day = day;
                this.currentPrice = currentPrice;
                this.prevPrice = prevPrice;
                this.priceDiff = currentPrice - prevPrice;
        }
        /***************
         * This method prints about the current information about the stock. 
         * The prices and the difference between the prices is formatted using DecimalFormat.
         * The date and time information is formatted using DateTimeFormatter.ofPattern.
         ***************/
        public void printStock() {
                System.out.println();
                NumberFormat moneyFormat = NumberFormat.getCurrencyInstance();
                DateTimeFormatter format = DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm a");
                System.out.println("Date Created: " + format.format(day));
                System.out.println("Current Price: " + moneyFormat.format(currentPrice));
                System.out.println("Price Change: " + moneyFormat.format(priceDiff));
        }
        /****************
         * This method returns the information about the current price.
         * This method is used in Business, Login, GetCurrentPrices, GetTodaysHigh, and GetTodaysLow classes.
         * This method is needed for comparisons, setting the price difference between stocks, and setting information about new stocks. 
         *
         * @return                     The current price of the stock.
         ***************/
        public double getPrice() {
                return currentPrice;
        }
        /***************
         * This method returns the information about the previous price.
         * This method is used in the Login class for creating a new stock.
         *
         * @return                      The previous price of the stock.
         ***************/
        public double getPrevPrice() {
                return prevPrice;
        }
        /**************
         * This method returns the LocalDateTime object for the day.
         * This method is used in the Login, GetCurrentPrices, GetTodaysHigh, and GetTodaysLow classes.
         * This method is needed for checking if the stock was created today.
         *
         * @return                      The information about the date and time the stock was created.
         *************/
        public LocalDateTime getDay() {
                return day;
        }
        /***********
         * Variables
         **********/
        private double prevPrice;                       //The previous price of the stock.
        private double currentPrice;                    //The current price of the stock.
        private double priceDiff;                       //The difference in price between the current and previous prices.
        private LocalDateTime day;                      //The day and time formation for when the stock was created.
}
