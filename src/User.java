import java.lang.reflect.Array;
import java.util.*;

public class User {

    private String username;
    private String password;
    private ArrayList<MovieCollection> collections = new ArrayList<MovieCollection>();

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

    public void newCollection(String name) {
        collections.add(new MovieCollection(name));
    }

    public MovieCollection getCollection(String name) {
        for (MovieCollection c: collections) {
            if(c.getName() == name) {
                return c;
            }
        }
        return null;
    }

    // run this when a user logs in
    public void buildCollections(ArrayList<Movie> movieList) {
        for (MovieCollection c: collections) {
            c.buildMovieCollectionFromIds(movieList);
        }
    }
}
