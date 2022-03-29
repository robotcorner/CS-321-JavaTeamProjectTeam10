import javax.swing.*;
import java.awt.*;

public class UserView {

    LoginManager m = new LoginManager();
    SimpleMessage s = new SimpleMessage();

    public void openLogInView() {

        JFrame userView = new JFrame("Log In");

        JTextArea userText = new JTextArea("Please enter your username and password.");

        final int FIELD_WIDTH = 30;
        JTextField userTextField = new JTextField(FIELD_WIDTH);
        JTextField passwordTextField = new JTextField(FIELD_WIDTH);

        JButton logIn = new JButton("Log In");
        JButton cancel = new JButton("Cancel");
        JButton signUp = new JButton("Sign Up");

        JTextArea signInText = new JTextArea("Don't have an account? Press the Sign Up button.");

        logIn.addActionListener(event -> {

            String username = userTextField.getText();
            String password = passwordTextField.getText();

            if (m.verifyLogIn(username, password) == false) {

                s.message("Error", "Password or username is incorrect. Please try again.");
            }
        });

        cancel.addActionListener(event -> userView.setVisible(false));

        signUp.addActionListener(event -> openSignUpView());

        userView.setLayout(new FlowLayout());

        userView.add(userText);
        userView.add(logIn);
        userView.add(cancel);
        userView.add(signInText);
        userView.add(signUp);
    }

    public void openSignUpView() {

        JFrame newUserView = new JFrame("Sign Up");

        JTextArea userText = new JTextArea("Please enter your username and password.");

        final int FIELD_WIDTH = 30;
        JTextField userTextField = new JTextField(FIELD_WIDTH);
        JTextField passwordTextField = new JTextField(FIELD_WIDTH);

        JTextArea verifyUserText = new JTextArea("Verify your username and password.");

        JTextField verifyUserTextField = new JTextField(FIELD_WIDTH);
        JTextField verifyPasswordTextField = new JTextField(FIELD_WIDTH);

        JButton signUp = new JButton("Sign Up");
        JButton cancel = new JButton("Cancel");

        signUp.addActionListener(event -> {

            if (userTextField.getText().equals(verifyUserTextField.getText()) && passwordTextField.getText().equals(verifyPasswordTextField.getText())) {

                String username = userTextField.getText();
                String password = passwordTextField.getText();

                m.createUser(username, password);

                s.message("New User Created", "Press 'OK' to proceed.");

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

        newUserView.setLayout(new FlowLayout());

        newUserView.add(userText);
        newUserView.add(userTextField);
        newUserView.add(passwordTextField);
        newUserView.add(verifyUserText);
        newUserView.add(verifyUserTextField);
        newUserView.add(verifyPasswordTextField);
        newUserView.add(signUp);
        newUserView.add(cancel);
    }
}