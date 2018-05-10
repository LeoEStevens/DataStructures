/************
 * Data Structures Project 2
 * Created on 10/25/2017 by Leo Stevens
 ************/


/*******************
 * This class starts the Stock program.
 * The class starts off by creating a null BusinessList.
 * The class then attempts to instantiate the list by using the BusinessReader class.
 * If the BusinessReader class returns an empty list (no data file or corrupted data file) it prompts the user to create a new Business.
 * Once the list has been loaded or the user has created a new Business it then loads the StockMenu class which handles user input and program flow.
 ******************/
public class Driver {
        public static void main(String[] args) {
                //Initialize a null list
                BusinessList list;
                BusinessReader reader = new BusinessReader();
                //Try to get list from file
                list = reader.getBusinesses();
                //If businesses were loaded from the file then open StockMenu with the list
                if(list.size() != 0) {
                        StockMenu menu = new StockMenu(list);
                        menu.createMenu();
                }
                //Else create a new business and open the StockMenu
                else {
                        CreateBusiness newBusiness = new CreateBusiness(list);
                        int index = newBusiness.newBusiness();
                        StockMenu menu = new StockMenu(list, index);
                        menu.createMenu();
                }
        }
}
