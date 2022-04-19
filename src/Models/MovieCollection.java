package Models;

import java.util.ArrayList;

/**
 * This holds a movie collection that the user has
 * The id ArrayList is from the json file
 * The Models.Movie ArrayList is built from the id ArrayList
 * (use the Models.Movie arraylist for everything)
 */
public class MovieCollection extends Searchable {
    private ArrayList<String> ids = new ArrayList<>(); // array of movie IDs
    private transient ArrayList<Movie> mediaList = new ArrayList<>(); // array of movies
    private String name;

    /**
     * Create empty collection
     * @param name the title of the collection
     */
    public MovieCollection(String name) {
        this.name = name;
    }

    /**
     * Create collection populated with movies from list
     * @param m list of movies
     */
    public MovieCollection(ArrayList<Movie> m) {
        mediaList = m;
        m.forEach(e -> ids.add(e.getImdbID()));
    }

    /**
     * Add movie to both lists
     * @param m movie
     */
    public void addMovie(Movie m) {
        if(!ids.contains(m.getImdbID()))
            ids.add(m.getImdbID());
        if(!mediaList.contains(m))
            mediaList.add(m);
    }

    /**
     * Remove movie from both lists
     * @param m movie
     */
    public void removeMovie(Movie m) {
        if(ids.contains(m.getImdbID()))
            ids.remove(m.getImdbID());
        if(mediaList.contains(m))
            mediaList.remove(m);
    }

    /**
     * Returns whether or not a movie is in this collection
     * @param imdbID id of movie
     * @return true if movie in collection. false otherwise
     */
    public boolean hasID(String imdbID) {
        if(ids.contains(imdbID))
            return true;
        return false;
    }

    /**
     * This function allows us to store IDs in JSON and load movielist into memory
     * @param allMovies canonical list of movies
     */
    public void buildMovieCollectionFromIds(ArrayList<Movie> allMovies) {
        mediaList = new ArrayList<>();
        for (String id: ids) {
            for (Movie m: allMovies) {
                if(id.equals(m.getImdbID())) {
                    mediaList.add(m);
                }
            }
        }
    }

    /**
     * inherited from Models.Searchable
     * @param term
     * @return
     */
    public ArrayList<Movie> search(String term) {
        return super.search(mediaList, term);
    }

    /**
     * inherited from Models.Searchable
     * @param term
     * @return
     */
    public ArrayList<Movie> searchTitle(String term) {
        return super.searchTitle(mediaList, term);
    }

    /**
     * inherited from Models.Searchable
     * @param term
     * @return
     */
    public ArrayList<Movie> searchGenre(String term) {
        return super.searchGenre(mediaList, term);
    }

    /**
     * inherited from Models.Searchable
     * @param term
     * @return
     */
    public ArrayList<Movie> searchCast(String term) {
        return super.searchCast(mediaList, term);
    }

    /**
     * inherited from Models.Searchable
     * @param term
     * @return
     */
    public ArrayList<Movie> searchDirector(String term) {
        return super.searchDirector(mediaList, term);
    }

    /**
     *
     * @return list of movies in collection
     */
    public ArrayList<Movie> getMovieList() {
        return mediaList;
    }


    /**
     *
     * @return name of collection
     */
    public String getName() {
        return name;
    }

    /**
     * Unused - sets name of collection to name passed in
     * @param n name
     */
    public void setName(String n) { name = n; }
}
