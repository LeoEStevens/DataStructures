/***************
 * Data Structures Project 3
 * Created on 11/18/2017 by Leo Stevens
 **************/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileNotFoundException;

/*************
 * NameListBuilder Class.
 * This class creates a NameList of names to be used by passengers.
 *
 * @author Leo Stevens
 * @version 1.0
 ***********/

public class NameListBuilder {
	/**********
	 * buildList method.
	 * This method reads the Names.txt and creates a list of Strings.
	 *
	 * @return				The NameList of names.
	 *********/
	public NameList<String[]> buildList() {
		//Create new NameList
		NameList<String[]> nameList = new NameList<>();
		//Read Names.txt file
		BufferedReader reader;
		try { 
			reader = new BufferedReader(new FileReader("Names.txt"));
		} catch(FileNotFoundException e) {
			return null;
		}
		//While there are still items in the text file add them to the list.
		String line;
		try {
			while((line = reader.readLine()) != null) {
				String[] name = line.split(" ");
				if(name.length == 2) {
					if(name[0] != null && !name[0].isEmpty() && name[1] != null && !name[1].isEmpty()) {
						nameList.add(name);
					}
				}
			}
		} catch (IOException e) {
			return null;
		}
		//Return name list
		return nameList;
	}
}


