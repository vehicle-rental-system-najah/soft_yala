package com.vehiclerental.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.net.URL;
import java.io.IOException;
import java.util.Objects;

public class VehicleRentalApp extends Application {

    private static Stage primaryStage;

    @Override
    public void start(Stage stage) {
        primaryStage = stage;
        primaryStage.setTitle("Vehicle Rental Management System");
        primaryStage.setMinWidth(1050);
        primaryStage.setMinHeight(680);
        showLogin();
        primaryStage.show();
    }

    public static void showLogin() {
        setScene("login.fxml", 1180, 720);
    }

    public static void showDashboard() {
        setScene("dashboard.fxml", 1280, 760);
    }

    public static void showVehicles() {
        setScene("available-vehicles.fxml", 1280, 760);
    }
    public static void showCreateRental() {
        setScene("create-rental.fxml", 1280, 760);
    }
    public static void showReturnVehicle() {
        setScene("return-vehicle.fxml", 1280, 760);
    }
    private static void setScene(String fileName, double width, double height) {
        try {
            URL resource = VehicleRentalApp.class.getResource(
                    "/com/vehiclerental/gui/" + fileName
            );

            if (resource == null) {
                throw new IllegalStateException(
                        "FXML file not found: " + fileName
                );
            }

            FXMLLoader loader = new FXMLLoader(resource);

            Parent root = loader.load();
            Scene scene = new Scene(root, width, height);

            scene.getStylesheets().add(
                    Objects.requireNonNull(
                            VehicleRentalApp.class.getResource(
                                    "/com/vehiclerental/gui/styles.css"
                            )
                    ).toExternalForm()
            );

            primaryStage.setScene(scene);
            primaryStage.centerOnScreen();
        } catch (IOException exception) {
            throw new IllegalStateException(
                    "Unable to load " + fileName,
                    exception
            );
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}