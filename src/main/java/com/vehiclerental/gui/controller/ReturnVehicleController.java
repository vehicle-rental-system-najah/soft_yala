package com.vehiclerental.gui.controller;

import com.vehiclerental.gui.GuiContext;
import com.vehiclerental.gui.VehicleRentalApp;
import com.vehiclerental.model.Invoice;
import com.vehiclerental.model.Rental;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.util.StringConverter;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

public class ReturnVehicleController {

    @FXML
    private ComboBox<Rental> rentalComboBox;

    @FXML
    private DatePicker returnDatePicker;

    @FXML
    private Label customerValueLabel;

    @FXML
    private Label vehicleValueLabel;

    @FXML
    private Label rentalPeriodValueLabel;

    @FXML
    private Label rentalCostValueLabel;

    @FXML
    private Label latePenaltyValueLabel;

    @FXML
    private Label totalAmountValueLabel;

    @FXML
    private VBox invoiceBox;

    @FXML
    private void initialize() {
        configureRentalComboBox();

        rentalComboBox.valueProperty().addListener(
                (observable, oldValue, newValue) ->
                        updateRentalInformation(newValue)
        );

        returnDatePicker.setValue(
                LocalDate.now(ZoneId.systemDefault())
        );

        invoiceBox.setVisible(false);
        invoiceBox.setManaged(false);

        refreshRentals();
    }

    private void configureRentalComboBox() {
        rentalComboBox.setConverter(new StringConverter<>() {

            @Override
            public String toString(Rental rental) {
                if (rental == null) {
                    return "";
                }

                return "Rental #" + rental.getId()
                        + " - "
                        + rental.getCustomer().getName()
                        + " - "
                        + rental.getVehicle().getBrand()
                        + " "
                        + rental.getVehicle().getModel();
            }

            @Override
            public Rental fromString(String text) {
                return null;
            }
        });
    }

    @FXML
    private void refreshRentals() {
        List<Rental> activeRentals = GuiContext
                .getRentalRepository()
                .findAll()
                .stream()
                .filter(Rental::isActive)
                .toList();

        rentalComboBox.getItems().setAll(activeRentals);

        if (!activeRentals.isEmpty()) {
            rentalComboBox.getSelectionModel().selectFirst();
        } else {
            rentalComboBox.getSelectionModel().clearSelection();
            updateRentalInformation(null);
        }
    }

    private void updateRentalInformation(Rental rental) {
        if (rental == null) {
            customerValueLabel.setText("-");
            vehicleValueLabel.setText("-");
            rentalPeriodValueLabel.setText("-");
            return;
        }

        customerValueLabel.setText(
                rental.getCustomer().getName()
                        + " (ID: "
                        + rental.getCustomer().getId()
                        + ")"
        );

        vehicleValueLabel.setText(
                rental.getVehicle().getBrand()
                        + " "
                        + rental.getVehicle().getModel()
                        + " - "
                        + rental.getVehicle().getPlateNumber()
        );

        rentalPeriodValueLabel.setText(
                rental.getStartDate()
                        + " to "
                        + rental.getEndDate()
        );
    }

    @FXML
    private void handleReturnVehicle() {
        Rental selectedRental = rentalComboBox.getValue();
        LocalDate returnDate = returnDatePicker.getValue();

        if (selectedRental == null) {
            showError(
                    "No Rental Selected",
                    "Please select an active rental."
            );
            return;
        }

        if (returnDate == null) {
            showError(
                    "Invalid Return Date",
                    "Please select the actual return date."
            );
            return;
        }

        if (returnDate.isBefore(selectedRental.getStartDate())) {
            showError(
                    "Invalid Return Date",
                    "The return date cannot be before the rental start date."
            );
            return;
        }

        Invoice invoice = GuiContext
                .getReturnService()
                .returnVehicle(
                        selectedRental.getId(),
                        returnDate
                );

        if (invoice == null) {
            showError(
                    "Return Failed",
                    "The vehicle could not be returned."
            );
            return;
        }

        displayInvoice(invoice);
        refreshRentals();

        showSuccess(
                "Vehicle Returned",
                String.format(
                        "Rental cost: %.2f%n"
                                + "Late penalty: %.2f%n"
                                + "Total amount: %.2f",
                        invoice.getRentalCost(),
                        invoice.getLatePenalty(),
                        invoice.getTotalAmount()
                )
        );
    }

    private void displayInvoice(Invoice invoice) {
        rentalCostValueLabel.setText(
                String.format("%.2f", invoice.getRentalCost())
        );

        latePenaltyValueLabel.setText(
                String.format("%.2f", invoice.getLatePenalty())
        );

        totalAmountValueLabel.setText(
                String.format("%.2f", invoice.getTotalAmount())
        );

        invoiceBox.setVisible(true);
        invoiceBox.setManaged(true);
    }

    @FXML
    private void showDashboard() {
        VehicleRentalApp.showDashboard();
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
    private void handleLogout() {
        GuiContext.getAuthService().logout();
        VehicleRentalApp.showLogin();
    }

    private void showSuccess(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(title);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void showError(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}