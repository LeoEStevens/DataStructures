//Random is used for year, time, genre and rating
import java.util.Random;

/*********************************
 * BuildMovies class<br>
 *
 *  Initializes the array of Movie objects <br>
 *  Uses a counter to set the name of movies <br> 
 *  Uses Random to set the year, time, genre, and rating for each movie <br>
 *  Then adds movie to array which is passed in constructor <br>
 *
 * @author Leo Stevens
 * @since 9/10/2017
 * @version 1.0
 *
 * Called by: useMovie
 * Calls: Movie
 *********************************/

public class BuildMovies {
        /******************
         * Default Constructor<br>
         * --Not Used--<br>
         * Instantiates an empty array of new movies
         *****************/
        public BuildMovies() {
                movieArray = new Movie[35];
        }

        /******************
         * Overloaded Constructor<br>
         * ----------------<br>
         * Description:<br>
         *  Takes movieArray as a parameter for use in buildList method
         * @param movieArray (required) Array of Movies to initialize
         *****************/
        public BuildMovies(Movie[] movieArray) {
                this.movieArray = movieArray;
        }

        /******************
         * buildList Method. <br>
         * ---------------- <br>
         * Description: <br>
         *  Method to initialize the Movies in the array. <br>
         *  Loops through array 30 times starting at 0. <br>
         *  Name of the movie is set using Movie + loop-counter. <br>
         *  Uses java collections rand to set a random year, time, genre and rating.
         *****************/
        public void buildList() {
                //Instantiate Random object
                Random rand = new Random();
                boolean loop = true;
                int genres[] = new int[5];
                //Loop to initialize the array
                for(int i = 0; i < 30; i++) {
                        //Set the name of the Movie object
                        //Uses String.format to format int into two digits (for name sorting purposes)
                        String name = "Movie" + String.format("%02d", i);
                        //Random int for the year of the movie (Range: 1920 - 2017)
                        int year;
                        int count = 0;
                        //Loop to check that the year is unique
                        do {
                                //Get random int
                                year = rand.nextInt(97) + 1920;
                                //Check that it doesn't already exist
                                for(int j = i - 1; j >= 0; j--) {
                                      if(movieArray[j].getYear() == year) {
                                              count++;
                                              break;
                                      }
                                }
                                //If it doesn't exists then break the loop
                                if(count == 0) {
                                        loop = false;
                                }
                                //Else get new random number and restart loop
                                else {
                                        rand = new Random();
                                        year = rand.nextInt(97) + 1920;
                                        count = 0;
                                        loop = true;
                                }
                        } while(loop == true);
                        //Random int for the running time of the movie (Range: 0 - 300)
                        int time = rand.nextInt(240) + 60;
                        //Random int for the genre of the movie (Range 0 - 6)
                        int genreI;
                        //Loop to make sure the genre count is less than 10
                        do {
                                //Get random int
                                genreI = rand.nextInt(5);
                                //If there are less than 10 of that genre then end loop and increase genre counter
                                if(genres[genreI] < 10) {
                                        loop = false;
                                        genres[genreI]++;
                                }
                                //Else get new random int and restart loop
                                else {
                                        rand = new Random();
                                        genreI = rand.nextInt(5);
                                        loop = true;
                                }
                        } while(loop == true);
                        //String to hold genre
                        String genre;
                        //Switch to set the genre from int to String
                        switch(genreI) {
                                case 1:
                                        genre = "Comedy";
                                        break;
                                case 2:
                                        genre = "Action";
                                        break;
                                case 3:
                                        genre = "Drama";
                                        break;
                                case 4:
                                        genre = "Sci-Fi";
                                        break;
                                default:
                                        genre = "Documentary";
                        }
                        //Random double for the rating of the movie (Range: 0.0 - 10.0)
                        double rating = rand.nextDouble() * 10.0;
                        //Instantiate new Movie using non-default constructor and initialize it
                        movieArray[i] = new Movie(name, year, time, genre, rating);
                }
        }

        /**************
         * Variables
         * movieArray an array of Movie objects
         *************/
        private Movie[] movieArray;
}
