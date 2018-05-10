/****************
 * Data Structures Project 2
 * Created on 10/26/2017 by Leo Stevens.
 ***************/

import java.io.ObjectInputStream;
import java.io.FileInputStream;
import java.io.IOException;

/***************
 * This class reads the data file into an object.
 * The class takes in a business list as a parameter in the constructor.
 * The method opens the file and parses the data.
 *
 * @author Leo Stevens
 * @version 1.0
 **************/

public class BusinessReader {
        /**********
         * Default Constructor.
         * This constructor is not used.
         * The constructor sets the list to null.
         **********/
        public BusinessReader() {
                this.list = null;
        }
        /**********
         * Overloaded Constructor.
         * This constructor is used by the Driver class.
         * 
         * @param list                  The list to read the object into.
         *********/
        public BusinessReader(BusinessList list) {
                this.list = list;
        }
        /***********
         * This method reads the file and returns the list.
         * The method uses FileInputStream to open the file and ObjectInputStream to create the object.
         *
         * @return                      The BusinessList read from the file.
         **********/
        public BusinessList getBusinesses() {
                //Try-Catch for IOException
                try {
                        //Open file
                        FileInputStream file = new FileInputStream(FILENAME);
                        ObjectInputStream stream = new ObjectInputStream(file);
                        //Try-Catch for read object and casting
                        try {
                                //Try to get list
                                list = (BusinessList) stream.readObject();
                        } catch(ClassNotFoundException e) {
                                System.out.println(FILENAME + " is currupted!");
                                System.out.println("Please delete it and reopen program");
                        }
                } catch (IOException e) {
                        return new BusinessList();
                }
                //Return list
                return list;
        }
        /************
         * Variables
         ***********/
        private static final String FILENAME = "Businesses.data";                       //The filename where the list is located.
        private BusinessList list;                                                      //The list to read into.
}


