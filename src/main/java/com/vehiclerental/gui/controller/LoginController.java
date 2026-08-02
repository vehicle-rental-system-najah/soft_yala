package com.vehiclerental.gui.controller;

import com.vehiclerental.gui.GuiContext;
import com.vehiclerental.gui.VehicleRentalApp;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label statusLabel;

    @FXML
    private Button loginButton;

    @FXML
    private void handleLogin() {
        String username = usernameField.getText().trim();
        String password = passwordField.getText();

        clearStatus();

        if (username.isEmpty() || password.isEmpty()) {
            showError("Please enter your username and password.");
            return;
        }

        loginButton.setDisable(true);
        loginButton.setText("Signing in...");

        boolean loggedIn = GuiContext
                .getAuthService()
                .login(username, password);

        loginButton.setDisable(false);
        loginButton.setText("Sign In");

        if (loggedIn) {
            VehicleRentalApp.showDashboard();
            return;
        }

        passwordField.clear();
        showError("Invalid username or password.");
    }

    private void showError(String message) {
        statusLabel.setText(message);
        statusLabel.getStyleClass().setAll(
                "status-label",
                "status-error"
        );
    }

    private void clearStatus() {
        statusLabel.setText("");
        statusLabel.getStyleClass().setAll("status-label");
    }
}