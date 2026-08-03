package com.vehiclerental.gui.controller;

import com.vehiclerental.gui.GuiContext;
import com.vehiclerental.gui.VehicleRentalApp;
import com.vehiclerental.model.Vehicle;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class AvailableVehiclesController {

    @FXML
    private TextField searchField;

    @FXML
    private Label vehicleCountLabel;

    @FXML
    private TableView<Vehicle> vehicleTable;

    @FXML
    private TableColumn<Vehicle, Integer> idColumn;

    @FXML
    private TableColumn<Vehicle, String> typeColumn;

    @FXML
    private TableColumn<Vehicle, String> brandColumn;

    @FXML
    private TableColumn<Vehicle, String> modelColumn;

    @FXML
    private TableColumn<Vehicle, String> plateColumn;

    @FXML
    private TableColumn<Vehicle, String> rateColumn;

    @FXML
    private TableColumn<Vehicle, String> statusColumn;

    private final ObservableList<Vehicle> vehicles =
            FXCollections.observableArrayList();

    private FilteredList<Vehicle> filteredVehicles;

    @FXML
    private void initialize() {
        configureColumns();

        filteredVehicles = new FilteredList<>(vehicles, vehicle -> true);
        vehicleTable.setItems(filteredVehicles);

        searchField.textProperty().addListener(
                (observable, oldValue, newValue) ->
                        filterVehicles(newValue)
        );

        refreshVehicles();
    }

    private void configureColumns() {
        idColumn.setCellValueFactory(
                data -> new ReadOnlyObjectWrapper<>(
                        data.getValue().getId()
                )
        );

        typeColumn.setCellValueFactory(
                data -> new ReadOnlyStringWrapper(
                        data.getValue().getType()
                )
        );

        brandColumn.setCellValueFactory(
                data -> new ReadOnlyStringWrapper(
                        data.getValue().getBrand()
                )
        );

        modelColumn.setCellValueFactory(
                data -> new ReadOnlyStringWrapper(
                        data.getValue().getModel()
                )
        );

        plateColumn.setCellValueFactory(
                data -> new ReadOnlyStringWrapper(
                        data.getValue().getPlateNumber()
                )
        );

        rateColumn.setCellValueFactory(
                data -> new ReadOnlyStringWrapper(
                        String.format(
                                "%.2f",
                                data.getValue().getDailyRate()
                        )
                )
        );

        statusColumn.setCellValueFactory(
                data -> new ReadOnlyStringWrapper(
                        data.getValue().getStatus().toString()
                )
        );
    }

    @FXML
    private void refreshVehicles() {
        vehicles.setAll(
                GuiContext
                        .getVehicleCatalogService()
                        .getAvailableVehicles()
        );

        updateVehicleCount();
    }

    private void filterVehicles(String searchText) {
        String query = searchText == null
                ? ""
                : searchText.trim().toLowerCase();

        filteredVehicles.setPredicate(vehicle -> {
            if (query.isEmpty()) {
                return true;
            }

            return String.valueOf(vehicle.getId()).contains(query)
                    || vehicle.getType().toLowerCase().contains(query)
                    || vehicle.getBrand().toLowerCase().contains(query)
                    || vehicle.getModel().toLowerCase().contains(query)
                    || vehicle.getPlateNumber().toLowerCase().contains(query)
                    || vehicle.getStatus()
                    .toString()
                    .toLowerCase()
                    .contains(query);
        });

        updateVehicleCount();
    }

    private void updateVehicleCount() {
        int count = filteredVehicles == null
                ? vehicles.size()
                : filteredVehicles.size();

        vehicleCountLabel.setText(
                count + (count == 1 ? " vehicle" : " vehicles")
        );
    }

    @FXML
    private void showDashboard() {
        VehicleRentalApp.showDashboard();
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

    private void showInformation(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}