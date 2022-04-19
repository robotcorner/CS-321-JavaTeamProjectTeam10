package Controllers;

import Models.Movie;

import java.util.ArrayList;

public class MovieManager {
    // Used by an Instance
    private final ArrayList<Movie> mediaList;

    MovieManager() {
        this.mediaList = new ArrayList<Movie>();
    }

    public MovieManager(ArrayList<Movie> medias) {
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
     * @return Models.Movie or null if not found
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
     *
     * @return how many movies are in the list
     */
    public int size() {
        return mediaList.size();
    }

    /**
     * Returns if a movie title is in the media list
     */
    public boolean find(String title){
        return mediaList.contains(title);
    }
}
