package Views;

import Controllers.LoginManager;

import javax.swing.*;
import java.awt.*;

public class LoginView {

    private final LoginManager loginManager;
    SimpleMessage s = new SimpleMessage();

    /**
     * Instantiates the view that allows a user to login or sign up
     * @param loginManager controller that handles all login functionality
     */
    public LoginView(LoginManager loginManager) {
        this.loginManager = loginManager;
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
        JPasswordField passwordTextField = new JPasswordField(FIELD_WIDTH);

        JLabel verifyUserText = new JLabel("Re-Enter your username and password.");

        JTextField verifyUserTextField = new JTextField(FIELD_WIDTH);
        JPasswordField verifyPasswordTextField = new JPasswordField(FIELD_WIDTH);

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
            char[] password1 = passwordTextField.getPassword();
            String passwordString1 = new String(password1);
            char[] password2 = verifyPasswordTextField.getPassword();
            String passwordString2 = new String(password2);

            if (userTextField.getText().equals(verifyUserTextField.getText()) && passwordString1.equals(passwordString2)) {

                String username = userTextField.getText();

                if(loginManager.signup(username, passwordString1)) {
                    s.message("New Models.User Created", "Press 'OK' to proceed.");
                    loginManager.save();
                    MovieManagerView.updateLoginSection();
                    MovieManagerView.updateMoviePanel("");
                } else {
                    s.message("Error", "Models.User already exists.");
                }

                newUserView.setVisible(false);
            }

            else if (userTextField.getText().equals(verifyUserTextField.getText()) && !passwordString1.equals(passwordString2)) {

                s.message("Error", "Password does not match.");
            }

            else if (!userTextField.getText().equals(verifyUserTextField.getText()) && passwordString1.equals(passwordString2)) {

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