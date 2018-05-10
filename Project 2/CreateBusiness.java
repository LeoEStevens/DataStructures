/************
 * Data Structures Project 2
 * Created on 10/25/2017 by Leo Stevens
 ************/

//BufferedReader for buffering input stream
import java.io.BufferedReader;
//InputStreamReader for handling input from System.in
import java.io.InputStreamReader;
//IOException for IO handling errors
import java.io.IOException;
import java.util.Random;

/************
 * This class creates new businesses.
 * This class is called by the StockMenu class.
 * This class has one public method which prompts the user, handles user input, creates a business, and adds it to the BusinessList class.
 * The class has three private methods, one for username checking, one for password checking, and one for username generation.
 ***********/

public class CreateBusiness {
        /***********
         * Default Constructor.
         * This constructor is not used.
         * The constructor sets checks to zero and list to null.
         **********/
        public CreateBusiness() {
                this.list = null;
                this.checks = 0;
        }
        /***********
         * Overloaded Constructor.
         * This constructor is used in the StockMenu and Driver classes.
         * This constructor has a single parameter which is the list of current businesses.
         * The constructor sets checks to zero.
         *
         * @param list                  The list of businesses already created.
         **********/
        public CreateBusiness(BusinessList list) {
                this.list = list;
                this.checks = 0;
        }
        /***********
         * This method creates the new business and adds it to the list.
         * The method calls the three private methods in this class.
         * The method first prompts the user to enter a name for the business.
         * Then the method prompts the user for a unique id that is atleast eight characters long for the business and compares it to the businesses which have already been created.
         * If the user can not generate a unique ID after two attempts then one is randomly created by the private generateRandomID method.
         * The method then prompts the user to create an eight character long password.
         * Then the user is prompted for the current stock price for the business.
         * After that the business is created and added to the list.
         * 
         * @return                      The index of the business the user logged in to in the list.
         ***********/
        public int newBusiness() {
                //Set the default index
                int index = -1;
                //Try-Catch to handle IOExceptions
                try {
                        //Open System.in and buffer
                        InputStreamReader reader = new InputStreamReader(System.in);
                        BufferedReader console = new BufferedReader(reader);
                        String username;
                        String password;
                        String name;
                        //Get the name of the business
                        do {
                                System.out.print("Please enter a name for the business: ");
                                name = console.readLine();
                                if(name == null || name.isEmpty() || name.equals(" ")) {
                                        System.out.println("Please enter a valid username!");
                                }
                        } while(name == null || name.isEmpty() || name.equals(" "));
                        //Do-While loop until the username is valid or two errors have occured.
                       do {
                               System.out.print("Please enter a unique identification (8 char min): ");
                               username = console.readLine();
                        } while(!this.usernameCheck(username) && checks < 2);
                       //If there were two errors then create a random ID and give it to the user.
                       if(checks == 2) {
                               username = this.generateRandomID(name);
                               System.out.println("Created a random username: " + username);
                       }

                       //Do-While loop until the password is valid.
                       do {
                                System.out.print("Please enter a password (8 char min): ");
                                password = console.readLine();
                       } while(!this.passwordCheck(password));
                       //Prompt the user for a name for the business
                        boolean parseCheck = false;
                        double price = 0.0;
                        //Do-While loop until the price is valid.
                        do {
                                System.out.print("Please enter the current stock price: ");
                                String priceString = console.readLine();
                                //Try-Catch for parseDouble
                                try {
                                        price = Double.parseDouble(priceString);
                                        parseCheck = false;
                                } catch(NumberFormatException e) {
                                        System.out.println("Error! Please enter a valid stock price");
                                        parseCheck = true;
                                }
                        } while(parseCheck);
                        //Create a new business and add it to the list.
                        Business newBusiness = new Business(username, password, name, price);
                        index = list.add(newBusiness);
               } catch(IOException e) {
                        System.out.println("Error opening stream for input!\nExiting!");
                        System.exit(1);
               }        
               //Return the index of the business the user logged into.
               return index;
        }
        /**************
         * This method checks the validity of the username.
         * The method takes the username to check as a parameter.
         * If the username is less than eight characters or already exists then the method returns false.
         *
         * @param username                The username to check.
         * @return                        If the username is valid or not.
         *************/
        private boolean usernameCheck(String username) {
                //Check if the username is less than eight characters
                if(username.length() < 8) {
                        //Increment checks
                        checks++;
                        //Notify user
                        System.out.println("Error! Username must be 8 characters or greater!");
                        //Return false
                        return false;
                }
                //For loop to check the username against existing usernames
                for(int i = 0; i < list.size(); i++) {
                        //If the usernames are equal
                        if(list.get(i).checkID(username)) {
                                //Notify the user
                                System.out.println("Error! Username must be unique!");
                                //Increment checks
                                checks++;
                                //Return false
                                return false;
                        }
                }
                //The loop has finished and the length is valid so return true.
                return true;
        }
        /*************
         * This method checks if the password is longer than eight characters.
         * 
         * @param String                The password to check.
         * @return                      If the password is valid.
         ************/
        private boolean passwordCheck(String password) {
                //If the password is less than eight characters
                if(password.length() < 8) {
                        //Notify the user
                        System.out.println("Error! Password must be 8 characters or greater!");
                        //Return false
                        return false;
                }
                //Else return true
                return true;
        }
        /****************
         * This method generates a random user id based on the name of the business.
         * The method uses Random to generate random ints.
         * The first random int determines whether or not the character will be a number or letter.
         * If the first random is 1, the method then generates another random which will determine whether or not the letter is capitalized.
         * If the first random is 0 the method generates an int between 0-9.
         *
         * @param businessName          The name of the business.
         * @return                      The randomly generated username.
         ****************/
        private String generateRandomID(String businessName) {
                //Instantiate Random
                Random rand = new Random();
                String username;
                //Get blank username
                do {
                        username = businessName + "-";
                        int j;
                        //If the business name is less than 4, then create the username to the minimum required.
                        if(businessName.length() < 4) {
                                j = 8 - businessName.length();
                        }
                        //Else add four random alphanumerics to the end of the business name
                        else {
                                j = 4;
                        }
                        //For loop to generate random characters
                        for(int i = 0; i < j; i++) {
                                //Get first random
                                int flip = rand.nextInt(2);
                                //If random was 1, then generate a letter
                                if(flip == 1) {
                                        //Get second random
                                        int flipN = rand.nextInt(2); 
                                        //If second random was 1, then generate a lower case letter.
                                        if(flipN == 1) {
                                                //Add the character to username
                                                //Shifts the into character rang by adding the character 'a'
                                                //Casts it as a character and adds it to the string
                                                username += Character.toString((char)(rand.nextInt(26) + 'a'));
                                        } else {
                                                //Else create a capital letter
                                                username += Character.toString((char)(rand.nextInt(26) + 'A'));
                                        }
                                } else {
                                        //Else add a number
                                        username += Integer.toString(rand.nextInt(9));
                                }
                        }
                }
                //Check that the username has not been created before.
                while(!usernameCheck(username));
                //Return the username
                return username;
        }
        /************
         * Variables
         ***********/
        private BusinessList list;                      //The list of business to add the new business to
        private int checks;                             //The number of errors that have been performed in the username creation.
}
