package com.vehiclerental.gui.controller;

import com.vehiclerental.gui.GuiContext;
import com.vehiclerental.gui.VehicleRentalApp;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class DashboardController {

    @FXML
    private Label pageTitle;

    @FXML
    private Label pageDescription;

    @FXML
    private void showDashboard() {
        pageTitle.setText("Dashboard");
        pageDescription.setText(
                "Overview of your vehicle rental operations."
        );
    }

    @FXML
    private void showVehicles() {
        VehicleRentalApp.showVehicles();
    }

    @FXML
    private void showRentals() {
        pageTitle.setText("Create Rental");
        pageDescription.setText(
                "Create and validate a new vehicle rental."
        );
    }

    @FXML
    private void showReturns() {
        pageTitle.setText("Return Vehicle");
        pageDescription.setText(
                "Return vehicles and generate customer invoices."
        );
    }

    @FXML
    private void handleLogout() {
        GuiContext.getAuthService().logout();
        VehicleRentalApp.showLogin();
    }
}