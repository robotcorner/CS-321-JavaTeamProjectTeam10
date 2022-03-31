import java.util.ArrayList;

public class UserList {

    private ArrayList<User> userList;

    public void addUser(String u, String p) {

        User user = new User(u, p);

        userList.add(user);
    }
}
