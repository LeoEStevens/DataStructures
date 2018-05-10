//Import for DecimalFormat to display rating with precision of 2
import java.text.DecimalFormat;

/***********************
 * Movie Class <br>
 *  Holds variables for name, year, time, genre, and rating <br>
 *  Has methods for name comparison, display the movie, and getters for the variables (used in sorts)
 *
 * @author Leo Stevens
 * @since 9/10/2017
 * @version 1.0
 *
 * Called By: BuildMovies, MovieMenu, MovieSort
 **********************/

public class Movie {
        /********************
         * Default Constructor <br>
         * --Not Used-- <br>
         * Sets variables to null or 0
         *******************/
        public Movie() {
                name = null;
                year = 0;
                time = 0;
                genre = null;
                rating = 0.0;
        }

        /********************
         * Overloaded Constructor<br>
         * ------------------<br>
         * Description:<br>
         *  Takes parameters in to build movie object.
         * @param inName (Required) String to hold the name of the movie.
         * @param inYear (Required) Int to hold the year movie was released.
         * @param inTime (Required) Int to hold the running time.
         * @param inGenre (Required) String to hold the genre.
         * @param inRating (Required) Double to hold the rating.
         *******************/
        public Movie(String inName, int inYear, int inTime, String inGenre, double inRating) {
                name = inName;
                year = inYear;
                time = inTime;
                genre = inGenre;
                rating = inRating;
        }

        /********************
         * displayMovie Method <br>
         * ------------------ <br>
         * Description: <br>
         *  Prints the information about the movie
         *******************/
        public void displayMovie() {
                System.out.println("\n---------------------------------------------------------");
                System.out.println("                    Name: " + name);
                System.out.println("Release Year: " + year + "\t\tRunning time: " + time);
                DecimalFormat format = new DecimalFormat("#0.00");
                System.out.println("Rating: " + format.format(rating) + "\t\t\tGenre: " + genre);
                System.out.println("---------------------------------------------------------");
        }

        /*******************
         * nameCheck Method <br>
         * ----------------- <br>
         * Description: <br>
         *  Used for searching for a movie by name
	 *
         * @param check (Required) String to check name against
         * @return (boolean) True if Strings match else false
         ******************/
        public boolean nameCheck(String check) {
                if(name.equals(check)) {
                        return true;
                } else {
                        return false;
                }
        }
        /*******************
         * genreCheck Method <br>
         * ----------------- <br>
         * Description: <br>
         *  Used for searching for a movie by genre
         *
         *  @param check (Required) String to check genre against
         *  @return (boolean) True if genres match else false
         ******************/
        public boolean genreCheck(String check) {
                if(genre.equals(check)) {
                        return true;
                } else {
                        return false;
                }
        }

        /*****************
         * getName Method
         * @return (String) Name of movie
         ****************/
        public String getName() {
                return name;
        }

        /*****************
         * getYear Method
         * @return (int) Year of movie
         ****************/
        public int getYear() {
                return year;
        }

        /****************
         * getTime Method
         * @return (int) Running time of movie
         ***************/
        public int getTime() {
                return time;
        }

        /****************
         * getGenre Method
         * @return (String) Genre of movie
         ***************/
        public String getGenre() {
                return genre;
        }

        /****************
         * getRating Method
         * @return (Double) Rating of movie
         ***************/
        public double getRating() {
                return rating;
        }


        /****************
         * Variables
         * name (String) - The name of the movie
         * year (int) - The year the movie was released
         * time (int) - Running time of the movie
         * genre (String) - Genre of the movie
         * rating (double) - Rating of the movie
         ***************/
        private String name;
        private int year;
        private int time;
        private String genre;
        private double rating;
}


