package com.vehiclerental.gui.controller;

import com.vehiclerental.gui.GuiContext;
import com.vehiclerental.gui.VehicleRentalApp;
import com.vehiclerental.model.Customer;
import com.vehiclerental.model.License;
import com.vehiclerental.model.LicenseType;
import com.vehiclerental.model.Rental;
import com.vehiclerental.model.Vehicle;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.util.StringConverter;
import java.time.temporal.ChronoUnit;
import java.time.LocalDate;
import java.time.ZoneId;
import com.vehiclerental.model.ElectricVehicle;
import com.vehiclerental.model.Motorcycle;
import com.vehiclerental.model.Truck;
import java.time.temporal.ChronoUnit;
public class CreateRentalController {

    @FXML
    private TextField rentalIdField;

    @FXML
    private TextField customerIdField;

    @FXML
    private TextField customerNameField;

    @FXML
    private TextField phoneField;

    @FXML
    private TextField emailField;

    @FXML
    private TextField ageField;

    @FXML
    private TextField licenseNumberField;

    @FXML
    private ComboBox<LicenseType> licenseTypeComboBox;

    @FXML
    private ComboBox<Vehicle> vehicleComboBox;

    @FXML
    private DatePicker startDatePicker;

    @FXML
    private DatePicker endDatePicker;

    @FXML
    private void initialize() {
        configureVehicleComboBox();

        licenseTypeComboBox.getItems().setAll(LicenseType.values());
        licenseTypeComboBox.setValue(LicenseType.REGULAR);

        LocalDate today = LocalDate.now(ZoneId.systemDefault());
        startDatePicker.setValue(today);
        endDatePicker.setValue(today.plusDays(1));

        refreshVehicles();
    }

    private void configureVehicleComboBox() {
        vehicleComboBox.setConverter(new StringConverter<>() {

            @Override
            public String toString(Vehicle vehicle) {
                if (vehicle == null) {
                    return "";
                }

                return vehicle.getId()
                        + " - "
                        + vehicle.getType()
                        + " - "
                        + vehicle.getBrand()
                        + " "
                        + vehicle.getModel()
                        + " - "
                        + vehicle.getPlateNumber();
            }

            @Override
            public Vehicle fromString(String text) {
                return null;
            }
        });
    }

    @FXML
    private void refreshVehicles() {
        vehicleComboBox.getItems().setAll(
                GuiContext
                        .getVehicleCatalogService()
                        .getAvailableVehicles()
        );

        if (!vehicleComboBox.getItems().isEmpty()) {
            vehicleComboBox.getSelectionModel().selectFirst();
        }
    }

    @FXML
    private void handleCreateRental() {
        try {
            int rentalId = parsePositiveInteger(
                    rentalIdField.getText(),
                    "Rental ID"
            );

            int customerId = parsePositiveInteger(
                    customerIdField.getText(),
                    "Customer ID"
            );

            int customerAge = parsePositiveInteger(
                    ageField.getText(),
                    "Customer age"
            );

            String customerName = requireText(
                    customerNameField.getText(),
                    "Customer name"
            );

            String customerPhone = requireText(
                    phoneField.getText(),
                    "Phone number"
            );

            String customerEmail = requireText(
                    emailField.getText(),
                    "Email address"
            );

            Vehicle selectedVehicle = vehicleComboBox.getValue();
            LocalDate startDate = startDatePicker.getValue();
            LocalDate endDate = endDatePicker.getValue();

            if (selectedVehicle == null) {
                throw new IllegalArgumentException(
                        "Please select an available vehicle."
                );
            }

            if (startDate == null || endDate == null) {
                throw new IllegalArgumentException(
                        "Please select the rental start and end dates."
                );
            }
            if (!endDate.isAfter(startDate)) {
                throw new IllegalArgumentException(
                        "End date must be after start date."
                );
            }

            long rentalDays = ChronoUnit.DAYS.between(startDate, endDate);

            if (rentalDays > 30) {
                throw new IllegalArgumentException(
                        "Rental duration cannot exceed 30 days."
                );
            }

            if (GuiContext.getRentalRepository().findById(rentalId) != null) {
                throw new IllegalArgumentException(
                        "A rental with this ID already exists."
                );
            }

            License license = createLicense();
            if (selectedVehicle instanceof Truck
                    && (license == null
                    || license.getType() != LicenseType.TRUCK)) {

                throw new IllegalArgumentException(
                        "Truck rental requires a TRUCK license."
                );
            }

            if (selectedVehicle instanceof Motorcycle
                    && customerAge < 18) {

                throw new IllegalArgumentException(
                        "Customer must be at least 18 years old to rent a motorcycle."
                );
            }

            if (selectedVehicle instanceof ElectricVehicle) {
                ElectricVehicle electricVehicle =
                        (ElectricVehicle) selectedVehicle;

                if (!electricVehicle.isBatteryChecked()
                        || electricVehicle.getBatteryLevel() < 30) {

                    throw new IllegalArgumentException(
                            "Electric vehicle battery check failed."
                    );
                }
            }

            Customer customer = new Customer(
                    customerId,
                    customerName,
                    customerPhone,
                    customerEmail,
                    customerAge,
                    license
            );

            Rental rental = GuiContext
                    .getRentalService()
                    .rentVehicle(
                            rentalId,
                            customer,
                            selectedVehicle.getId(),
                            startDate,
                            endDate
                    );

            if (rental == null) {
                showError(
                        "Rental Rejected",
                        "Check the vehicle availability, rental dates, "
                                + "customer age, license type, and battery status."
                );
                return;
            }

            showSuccess(
                    "Rental Created",
                    "Rental #" + rental.getId()
                            + " was created successfully for "
                            + customer.getName()
                            + "."
            );

            clearForm();
            refreshVehicles();

        } catch (IllegalArgumentException exception) {
            showError("Invalid Information", exception.getMessage());
        }
    }

    private License createLicense() {
        String licenseNumber = licenseNumberField.getText();

        if (licenseNumber == null || licenseNumber.isBlank()) {
            return null;
        }

        LicenseType licenseType = licenseTypeComboBox.getValue();

        if (licenseType == null) {
            licenseType = LicenseType.REGULAR;
        }

        return new License(licenseNumber.trim(), licenseType);
    }

    private int parsePositiveInteger(String text, String fieldName) {
        try {
            int value = Integer.parseInt(requireText(text, fieldName));

            if (value <= 0) {
                throw new IllegalArgumentException(
                        fieldName + " must be greater than zero."
                );
            }

            return value;
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException(
                    fieldName + " must contain a valid number."
            );
        }
    }

    private String requireText(String text, String fieldName) {
        if (text == null || text.isBlank()) {
            throw new IllegalArgumentException(
                    fieldName + " is required."
            );
        }

        return text.trim();
    }

    private void clearForm() {
        rentalIdField.clear();
        customerIdField.clear();
        customerNameField.clear();
        phoneField.clear();
        emailField.clear();
        ageField.clear();
        licenseNumberField.clear();

        licenseTypeComboBox.setValue(LicenseType.REGULAR);

        LocalDate today = LocalDate.now(ZoneId.systemDefault());
        startDatePicker.setValue(today);
        endDatePicker.setValue(today.plusDays(1));
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
    private void showReturns() {
        VehicleRentalApp.showReturnVehicle();
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