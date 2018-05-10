/**************
 * Data Structures Project 2
 * Created on 10/26/2017 by Leo Stevens
 *************/

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;

/*****************
 * This class writes the BusinessList to a file using ObjectOutputStream.
 * The class takes in the BusinessList through the constructor and then writes the object using FileOutputStream and ObjectOutputStream.
 *
 * @author Leo Stevens
 * @version 1.0
 *****************/

public class BusinessWriter {
        /*********
         * Default Constuctor.
         * This constructor is not used.
         * The constructor sets list to null
         ********/
        public BusinessWriter() {
                this.list = null;
        }
        /***********
         * Overloaded Constructor.
         * This constructor is used in the StockMenu class.
         * The constructor sets the list from the parameter.
         *
         * @param list                  The list to write into file.
         ***********/
        public BusinessWriter(BusinessList list) {
                this.list = list;
        }
        /***********
         * This method writes the object into a file.
         * The method opens a FileOutputStream and ObjectOutputStream and writes the file.
         *
         * @return                      If the file was written sucesfully.
         **********/
        public boolean write() {
                //Try-Catch for IOExceptions
                try {
                        //Prepare stream for writing object.
                        FileOutputStream out = new FileOutputStream(FILENAME);
                        ObjectOutputStream outStream = new ObjectOutputStream(out);
                        //Write the object
                        outStream.writeObject(list);
                        //Close the stream
                        outStream.close();
                        //Return true
                        return true;
                }
                //Catch IOExceptions
                catch (IOException e) {
                        //Notify the user
                        System.out.println("Failed to write to " + FILENAME);
                        //Print the stack
                        e.printStackTrace();
                        //return false
                        return false;
                }
        }
        /***********
         * Variables
         **********/
        private static final String FILENAME = "Businesses.data";                       //The file to write to.
        private BusinessList list;                                                      //The list to write.
}

