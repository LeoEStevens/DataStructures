/********************
 * Data Structures Project 3
 * Created on 11/18/2017 by Leo Stevens
 *******************/
import java.util.Random;
import java.time.LocalDateTime;

/**************
 * CreatePassenger class.
 * This class creates the passengers for use in the queue.
 *
 * @author Leo Stevens
 * @version 1.0
 **************/

public class CreatePassenger {
	/***********
	 * Default Constructor.
	 * This constructor calls NameListBuilder and gets the list of names.
	 **********/
	public CreatePassenger() {
		NameListBuilder nameBuilder = new NameListBuilder();
		nameList = nameBuilder.buildList();
	}
	/***********
	 * create Method.
	 * This method sets all the data for the passenger.
	 *
	 * @return			The passenger created.
	 **********/
	public Passenger create() {
		String bordingClass = new String();
		Random rand = new Random();
		//Get randomly generate dob
		int month = rand.nextInt(11) + 1;
		int day = rand.nextInt(27) + 1;
		int age = rand.nextInt(99) + 1;
		//Get the years since dob
		int year = LocalDateTime.now().getYear() - age;
		LocalDateTime dob = LocalDateTime.of(year, month, day, 12, 01);
		String firstName = new String();
		String lastName = new String();
		//If namelist was not created, then create random names
		if(nameList == null && nameList.size() > 0) {
			int firstNameLength = rand.nextInt(12) + 1;
			for(int i = 0; i < firstNameLength; i++) {
				if(i == 0) {
					//Add the character to username
	                //Shifts the into character rang by adding the character 'a'
		            //Casts it as a character and adds it to the string
			        firstName += Character.toString((char)(rand.nextInt(26) + 'A'));
				} else {
					//Else create a capital letter
	                firstName += Character.toString((char)(rand.nextInt(26) + 'a'));
		        }
			}
			int lastNameLength = rand.nextInt(12) + 1;
			for(int i = 0; i < lastNameLength; i++) {
				if(i == 0) {
					//Add the character to username
	                //Shifts the into character rang by adding the character 'a'
		            //Casts it as a character and adds it to the string
			        lastName += Character.toString((char)(rand.nextInt(26) + 'A'));
				} else {
					//Else create a capital letter
	                lastName += Character.toString((char)(rand.nextInt(26) + 'a'));
		        }
			}
		}
		//Else use nameList to create first and last name
		else {
			String[] name;
			int r = rand.nextInt(nameList.size());
			name = nameList.get(r);
			nameList.remove(r);
			firstName = name[0];
			lastName = name[1];
		}
		//Get random bording class
		int bordingClassGenerator = rand.nextInt(60);
		if(bordingClassGenerator < 3) {
			bordingClass = "Traveling with infant";
		} 
		else if(age >= 65) {
			bordingClass = "Elderly";
		}
		else if(bordingClassGenerator < 10) {
			bordingClass = "Armed Forces";
		}
		else if (bordingClassGenerator < 20) {
			bordingClass = "First Class";
		} else {
			bordingClass = "Economy";
		}
		//Return passenger
		return new Passenger(firstName, lastName, dob, bordingClass);
	}
	/**************
	 * Variables
	 *************/
	private NameList<String[]> nameList;			//The list of names for the passengers
}




