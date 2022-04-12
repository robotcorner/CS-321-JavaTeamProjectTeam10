import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;

public class MovieManager {
    // Used by an Instance
    private ArrayList<Movie> mediaList;

    MovieManager() {
        this.mediaList = new ArrayList<Movie>();
    }

    MovieManager(ArrayList<Movie> medias) {
        this.mediaList = medias;
    }

    /**
     * Should not be used. Adds movie to list.
     * @param movie
     */
    public void add(Movie movie) {
        mediaList.add(movie);
    }

    /**
     * Gets a movie by its imdbID
     * @param imdbID
     * @return Movie or null if not found
     */
    public Movie get(String imdbID) {
        for (Movie movie: mediaList)
            if(movie.getImdbID().equals(imdbID))
                return movie;
        return null;
    }

    /**
     * Getter for mediaList
     * @return reference to the entire media list. Perhaps should return copy?
     */
    public ArrayList<Movie> getMediaList() {
        return mediaList;
    }

    /**
     *  A function that displays every movie title
     */

    public void displayList()
    {
        for (Movie movie: mediaList)
            System.out.println(movie.getTitle());
    }


    /**
     *
     * @return how many movies are in the list
     */
    public int size() {
        return mediaList.size();
    }


    /**
     * Sorts media list by title alphabetically
     */
    public void sortbyName(){
        Collections.sort(mediaList, Movie.sortByName());
    }

    /**
     * Sorts media list by year released
     */
    public void sortbyYear(){
        Collections.sort(mediaList, Movie.sortByYear());
    }

    /**
     * Sees if a movie title is in the media list
     */
    public boolean find(String title){
        if(mediaList.contains(title))
            return true;
        else
            return false;
    }
}
