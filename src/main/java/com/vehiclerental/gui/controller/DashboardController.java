package com.vehiclerental.gui.controller;

import com.vehiclerental.gui.GuiContext;
import com.vehiclerental.gui.VehicleRentalApp;
import com.vehiclerental.model.Rental;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
/**
 * Controls the main dashboard page.
 */
public class DashboardController {

    @FXML
    private Label pageTitle;

    @FXML
    private Label pageDescription;

    @FXML
    private Label availableVehiclesCountLabel;

    @FXML
    private Label activeRentalsCountLabel;

    @FXML
    private Label reminderCountLabel;

    @FXML
    private void initialize() {
        refreshDashboardStats();
    }

    private void refreshDashboardStats() {
        int availableCount = GuiContext
                .getVehicleCatalogService()
                .getAvailableVehicles()
                .size();

        List<Rental> rentals = GuiContext
                .getRentalRepository()
                .findAll();

        long activeCount = rentals.stream()
                .filter(Rental::isActive)
                .count();

        LocalDate today = LocalDate.now(ZoneId.systemDefault());

        long reminderCount = rentals.stream()
                .filter(Rental::isActive)
                .map(Rental::getEndDate)
                .filter(endDate -> endDate != null)
                .filter(endDate ->
                        !endDate.isBefore(today)
                                && !endDate.isAfter(today.plusDays(3))
                )
                .count();

        availableVehiclesCountLabel.setText(
                String.valueOf(availableCount)
        );

        activeRentalsCountLabel.setText(
                String.valueOf(activeCount)
        );

        reminderCountLabel.setText(
                String.valueOf(reminderCount)
        );
    }

    @FXML
    private void showDashboard() {
        pageTitle.setText("Dashboard");
        pageDescription.setText(
                "Overview of your vehicle rental operations."
        );

        refreshDashboardStats();
    }

    @FXML
    private void showVehicles() {
        VehicleRentalApp.showVehicles();
    }

    @FXML
    private void showRentals() {
        VehicleRentalApp.showCreateRental();
    }

    @FXML

    private void showReturns() {
        VehicleRentalApp.showReturnVehicle();
    }

    @FXML
    private void handleLogout() {
        GuiContext.getAuthService().logout();
        VehicleRentalApp.showLogin();
    }
}