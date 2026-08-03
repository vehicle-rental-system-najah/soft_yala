package com.vehiclerental.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.net.URL;
import java.io.IOException;
import java.util.Objects;
/**
 * Starts and manages the JavaFX vehicle rental application.
 */
public class VehicleRentalApp extends Application {

    private static Stage primaryStage;
    /**
     * Stores the primary application stage.
     *
     * @param stage the primary JavaFX stage
     */
    private static void setPrimaryStage(Stage stage) {
        primaryStage = stage;
    }

    /**
     * Initializes and displays the primary application window.
     *
     * @param stage the primary JavaFX stage
     */
    @Override
    public void start(Stage stage) {
        setPrimaryStage(stage);

        primaryStage.setTitle("Vehicle Rental Management System");
        primaryStage.setMinWidth(1050);
        primaryStage.setMinHeight(680);

        showLogin();
        primaryStage.show();
    }
    /**
     * Displays the login page.
     */
    public static void showLogin() {
        setScene("login.fxml", 1180, 720);
    }
    /**
     * Displays the dashboard page.
     */
    public static void showDashboard() {
        setScene("dashboard.fxml", 1280, 760);
    }
    /**
     * Displays the available vehicles page.
     */
    public static void showVehicles() {
        setScene("available-vehicles.fxml", 1280, 760);
    }
    /**
     * Displays the create rental page.
     */
    public static void showCreateRental() {
        setScene("create-rental.fxml", 1280, 760);
    }

    /**
     * Displays the return vehicle page.
     */
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
    /**
     * Launches the JavaFX application.
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}