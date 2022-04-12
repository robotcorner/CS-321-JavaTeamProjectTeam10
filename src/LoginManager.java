import java.util.ArrayList;

public class LoginManager {
    private ArrayList<User> userList;
    private ArrayList<Movie> movieList;
    private User currentUser;
    private MovieJsonOperator jsonOperator;

    /**
     * sets the instances of UserList, MovieList, and MovieJsonOperator so that the info
     * in those json files can be used here
     * @param userList
     * @param movieList
     * @param jsonOperator
     */
    LoginManager(ArrayList<User> userList, ArrayList<Movie> movieList, MovieJsonOperator jsonOperator) {
        this.userList = userList;
        this.jsonOperator = jsonOperator;
        this.movieList = movieList;
    }

    /**
     * Creates a new user account and logs him in
     * @param username
     * @param password
     * @return true if logged in, false if error occurred
     */
    public boolean signup(String username, String password) {
        // return false if username already taken
        for (User u : userList) {
            if (username.equals(u.getUsername())) {
                return false;
            }
        }

        // sign up - create new user, set current user, and add to userlist
        currentUser = new User(username, password);
        userList.add(currentUser);
        return true;
    }

    /**
     * Log a user into his account
     * @param username
     * @param password
     * @return true if logged in, false if error occurred
     */
    public boolean login(String username, String password) {
        // return true if correct credentials provided
        for (User u: userList) {
            if(username.equals(u.getUsername()) && password.equals(u.getPassword())) {
                currentUser = u;
                currentUser.buildCollections(movieList);
                return true;
            }
        }

        // return false if unsuccessful login
        return false;
    }

    /**
     * Log out
     * @return true if success, false if already logged out
     */
    public boolean logout() {
        if(currentUser == null)
            return false;

        currentUser = null;
        return true;
    }

    /**
     * Save user data back to the json file
     * @return true if success, false if error
     */
    public boolean save() {
        return jsonOperator.SaveAllUsers(userList);
    }

    /**
     * Getter for whatever account is logged in
     * @return currentUser
     */
    public User getCurrentUser() {
        return currentUser;
    }

    /**
     * Check if the user is logged in
     * @return true if logged in, false if not
     */
    public boolean verifyLogin() {
        if (currentUser == null)
            return false; // guest
        else
            return true;
    }
}
