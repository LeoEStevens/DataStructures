/*************
 * Data Structures Project 2
 * Created on 10/27/2017 by Leo Stevens
 ************/

import java.util.Random;
import java.io.Serializable;
//import java.lang.Integer;

/************
 * This class hides data about the user password so it is not stored as plaintext in the data file.
 * The user's password is broken up by characters and converted into an int.
 * The int is then shifted a random number.
 * The number that the int is shifted is stored in an array of ints.
 * Serializable so it can be written using ObjectOutputStream and read using ObjectInputStream
 ************/

public class Password implements Serializable {
        /**********
         * Default Constructor.
         * This constructor is not used.
         * Sets all variables to null.
         **********/
        public Password() {
                passArray = null;
                shiftArray = null;
                unshifted = null;
        }
        /***********
         * Overloaded Constructor.
         * This consructor is used in Business class.
         * Creates the password and shift array to the length of the input password.
         * Then the constructor calls the private shift method.
         *
         * @param unshifted                        The unshifted password string.
         ***********/
        public Password(String unshifted) {
                this.unshifted = unshifted;
                this.passArray = new int[unshifted.length()];
                this.shiftArray = new int[unshifted.length()];
                this.shift();
        }
        /************
         * This method converts the plaintext String into a shifted password array.
         * Uses a loop to go through the string, convert it to characters, cast it as an int, and then add a random int to it.
         * The int that is added to the int casted char is stored in the shift array.
         * The int value of the shifted char is added to the passArray;
         ************/
        private void shift() {
                Random rand = new Random();
                for(int i = 0; i < unshifted.length(); i++) {
                        shiftArray[i] = rand.nextInt(Integer.MAX_VALUE - (int)'z');
                        passArray[i] = (int)unshifted.charAt(i) + shiftArray[i];
                }
                this.unshifted = null;
        }
        /************
         * This method checks in the plaintext String matches the shifted password.
         * The method first checks if the String is the same length as the password array, and if it isn't return false.
         * Then the method loops through the password array and checks if the passwords match.
         * If the loop finishes and hasn't returned false then it returns true.
         * 
         * @param pass                  The password to check.
         * @return                      Whether or not the passwords match.
         ************/
        public boolean checkPass(String pass) {
                if(pass.length() != passArray.length) {
                        return false;
                }
                for(int i = 0; i < passArray.length; i++) {
                        if(passArray[i] != ((int)pass.charAt(i) + shiftArray[i])) {
                                return false;
                        }
                }
                return true;
        }
        /*********
         * Variables
         ********/
        private String unshifted;                       //The unshifted password, not null only when the password is first created.
        private int[] passArray;                        //An array of ints which stores the shifted char of the password.
        private int[] shiftArray;                       //The array which holds how many ints the password was shifted over for each character of the password.
}
