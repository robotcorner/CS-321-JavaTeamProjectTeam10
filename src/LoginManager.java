import java.util.ArrayList;

public class LoginManager {
    private ArrayList<User> userList;
    private ArrayList<Movie> movieList;
    private User currentUser;
    private MovieJsonOperator jsonOperator;

    LoginManager(ArrayList<User> userList, ArrayList<Movie> movieList, MovieJsonOperator jsonOperator) {
        this.userList = userList;
        this.jsonOperator = jsonOperator;
        this.movieList = movieList;
    }

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

    public boolean logout() {
        if(currentUser == null)
            return false;

        jsonOperator.SaveAllUsers(userList);
        currentUser = null;
        return true;
    }

    public boolean save() {
        if(currentUser == null)
            return false;

        jsonOperator.SaveAllUsers(userList);
        return true;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public boolean verifyLogin() {
        if (currentUser != null)
            return true;
        else
            return false; // guest
    }
}
