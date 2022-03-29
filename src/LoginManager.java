public class LoginManager {

    void createUser(String u, String p) {


    }

    boolean verifyLogIn(String u, String p) {



        return true;
    }

    boolean logInUser() {

        return true;
    }

    String updateUserPermission() {

        if (logInUser() == true)
            return "User";
        else
            return "Guest";
    }
}
