import javax.swing.*;
import java.awt.*;

public class LoginView {

    private LoginManager loginManager;
    private MovieManagerView movieManagerView;
    SimpleMessage s = new SimpleMessage();

    /**
     * sets the instances of LoginManager and MovieManagerView so that the methods
     * in those classes can be used here
     * @param loginManager
     * @param movieManagerView
     */
    public LoginView(LoginManager loginManager, MovieManagerView movieManagerView) {
        this.loginManager = loginManager;
        this.movieManagerView = movieManagerView;
    }

    /**
     * Opens the log in view when someone tries to log in
     */
    public void openLogInView() {

        JFrame userView = new JFrame("Log In");

        JLabel userText = new JLabel("Please enter your username and password.");

        final int FIELD_WIDTH = 25;
        JTextField userTextField = new JTextField(FIELD_WIDTH);
        JPasswordField passwordTextField = new JPasswordField(FIELD_WIDTH);

        JButton logIn = new JButton("Log In");
        JButton cancel = new JButton("Cancel");
        JButton signUp = new JButton("Sign Up");

        JLabel signInText = new JLabel("Don't have an account? Press the Sign Up button.");

        logIn.addActionListener(event -> {

            String username = userTextField.getText();
            char[] password = passwordTextField.getPassword();
            String passwordString = new String(password);

            if (loginManager.login(username, passwordString) == false) {
                s.message("Error", "Password and/or username is incorrect. Please try again.");
            } else {
                MovieManagerView.updateLoginSection();
                userView.setVisible(false);
                MovieManagerView.updateMoviePanel("");
            }
        });

        cancel.addActionListener(event -> userView.setVisible(false));

        signUp.addActionListener(event -> openSignUpView());

        userView.setLayout(new FlowLayout());

        userView.add(userText);
        userView.add(userTextField);
        userView.add(passwordTextField);
        userView.add(logIn);
        userView.add(cancel);
        userView.add(signInText);
        userView.add(signUp);
        userView.pack();
        userView.setBounds(0,0,340, 280);
        userView.setLocationRelativeTo(null);
        userView.setVisible(true);
    }

    /**
     * Opens the sign up view when someone tries to sign up
     */
    public void openSignUpView() {

        JFrame newUserView = new JFrame("Sign Up Page");

        JLabel userText = new JLabel("Enter your desired username and password.");

        final int FIELD_WIDTH = 25;
        JTextField userTextField = new JTextField(FIELD_WIDTH);
        JTextField passwordTextField = new JTextField(FIELD_WIDTH);

        JLabel verifyUserText = new JLabel("Re-Enter your username and password.");

        JTextField verifyUserTextField = new JTextField(FIELD_WIDTH);
        JTextField verifyPasswordTextField = new JTextField(FIELD_WIDTH);

        JButton signUp = new JButton("Sign Up");
        JButton cancel = new JButton("Cancel");

        newUserView.setLayout(new FlowLayout());

        newUserView.add(userText);
        newUserView.add(userTextField);
        newUserView.add(passwordTextField);
        newUserView.add(verifyUserText);
        newUserView.add(verifyUserTextField);
        newUserView.add(verifyPasswordTextField);
        newUserView.add(signUp);
        newUserView.add(cancel);

        signUp.addActionListener(event -> {

            if (userTextField.getText().equals(verifyUserTextField.getText()) && passwordTextField.getText().equals(verifyPasswordTextField.getText())) {

                String username = userTextField.getText();
                String password = passwordTextField.getText();

                if(loginManager.signup(username, password)) {
                    s.message("New User Created", "Press 'OK' to proceed.");
                    loginManager.save();
                    movieManagerView.updateLoginSection();
                } else {
                    s.message("Error", "User already exists.");
                }

                newUserView.setVisible(false);
            }

            else if (userTextField.getText().equals(verifyUserTextField.getText()) && !passwordTextField.getText().equals(verifyPasswordTextField.getText())) {

                s.message("Error", "Password does not match.");
            }

            else if (!userTextField.getText().equals(verifyUserTextField.getText()) && passwordTextField.getText().equals(verifyPasswordTextField.getText())) {

                s.message("Error", "Username does not match.");
            }

            else {

                s.message("Error", "Username and password do not match.");
            }
        });

        cancel.addActionListener(event -> newUserView.setVisible(false));


        newUserView.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));
        newUserView.add(userText);
        newUserView.add(userTextField);
        newUserView.add(passwordTextField);
        newUserView.add(verifyUserText);
        newUserView.add(verifyUserTextField);
        newUserView.add(verifyPasswordTextField);
        newUserView.add(signUp);
        newUserView.add(cancel);
        newUserView.pack();
        newUserView.setBounds(0,0,340, 280);
        newUserView.setLocationRelativeTo(null);
        newUserView.setVisible(true);
    }
}