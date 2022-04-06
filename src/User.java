import java.lang.reflect.Array;
import java.util.*;

public class User {

    private String username;
    private String password;
    private ArrayList<MovieCollection> collections = new ArrayList<MovieCollection>();

    /**
     * Parametrized constructor for User
     * @param u username goes here
     * @param p password goes here
     */
    User(String u, String p) {

        this.username = u;
        this.password = p;
    }

    /**
     * Gets the username
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets the password
     * @return password
     */
    public String getPassword () {
        return password;
    }

    /**
     * Sets the password
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Add new collection. Must have different names
     * @param name
     */
    public void newCollection(String name) {
        for (MovieCollection c: collections) {
            if(name.equals(c.getName())) return;
        }
        collections.add(new MovieCollection(name));
    }

    /**
     * Gets collection list
     */
    public ArrayList<MovieCollection> getallCollections(){
        return collections;
    }

    /**
     * Get collection by name
     * @param name
     */
    public MovieCollection getCollection(String name) {
        for (MovieCollection c: collections) {
            if(c.getName().equals(name)) {
                return c;
            }
        }
        return null;
    }

    /**
     * builds the collection of a user when they log into their account
     * @param movieList
     */
    public void buildCollections(ArrayList<Movie> movieList) {
        for (MovieCollection c: collections) {
            c.buildMovieCollectionFromIds(movieList);
        }
    }
}
