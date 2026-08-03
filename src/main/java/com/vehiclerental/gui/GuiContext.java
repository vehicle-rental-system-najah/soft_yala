package com.vehiclerental.gui;

import com.vehiclerental.pricing.DefaultLatePenaltyStrategy;
import com.vehiclerental.pricing.DefaultPricingStrategy;
import com.vehiclerental.repository.InMemoryManagerRepository;
import com.vehiclerental.repository.InMemoryRentalRepository;
import com.vehiclerental.repository.InMemoryVehicleRepository;
import com.vehiclerental.repository.ManagerRepository;
import com.vehiclerental.repository.RentalRepository;
import com.vehiclerental.repository.VehicleRepository;
import com.vehiclerental.service.AuthService;
import com.vehiclerental.service.BillingService;
import com.vehiclerental.service.RentalService;
import com.vehiclerental.service.ReturnService;
import com.vehiclerental.service.VehicleCatalogService;
import com.vehiclerental.validation.AvailabilityValidationStrategy;
import com.vehiclerental.validation.DurationValidationStrategy;
import com.vehiclerental.validation.ElectricBatteryValidationStrategy;
import com.vehiclerental.validation.MotorcycleAgeValidationStrategy;
import com.vehiclerental.validation.RentalValidationStrategy;
import com.vehiclerental.validation.TruckLicenseValidationStrategy;

import java.util.List;

/**
 * Provides shared repositories and services for the JavaFX application.
 */
public final class GuiContext {

    /**
     * Repository containing manager data.
     */
    private static final ManagerRepository MANAGER_REPOSITORY =
            new InMemoryManagerRepository();

    /**
     * Repository containing vehicle data.
     */
    private static final VehicleRepository VEHICLE_REPOSITORY =
            new InMemoryVehicleRepository();

    /**
     * Repository containing rental records.
     */
    private static final RentalRepository RENTAL_REPOSITORY =
            new InMemoryRentalRepository();

    /**
     * Authentication service used by the GUI.
     */
    private static final AuthService AUTH_SERVICE =
            new AuthService(MANAGER_REPOSITORY);

    /**
     * Vehicle catalogue service used by the GUI.
     */
    private static final VehicleCatalogService VEHICLE_CATALOG_SERVICE =
            new VehicleCatalogService(VEHICLE_REPOSITORY);

    /**
     * Validation rules applied before creating a rental.
     */
    private static final List<RentalValidationStrategy> RENTAL_VALIDATIONS =
            List.of(
                    new AvailabilityValidationStrategy(),
                    new DurationValidationStrategy(),
                    new TruckLicenseValidationStrategy(),
                    new MotorcycleAgeValidationStrategy(),
                    new ElectricBatteryValidationStrategy()
            );

    /**
     * Rental service used to create rental records.
     */
    private static final RentalService RENTAL_SERVICE =
            new RentalService(
                    VEHICLE_REPOSITORY,
                    RENTAL_REPOSITORY,
                    RENTAL_VALIDATIONS
            );

    /**
     * Billing service used to calculate rental costs and penalties.
     */
    private static final BillingService BILLING_SERVICE =
            new BillingService(
                    new DefaultPricingStrategy(),
                    new DefaultLatePenaltyStrategy()
            );

    /**
     * Return service used to return vehicles and generate invoices.
     */
    private static final ReturnService RETURN_SERVICE =
            new ReturnService(
                    RENTAL_REPOSITORY,
                    BILLING_SERVICE
            );

    /**
     * Prevents creating instances of this utility class.
     */
    private GuiContext() {
    }

    /**
     * Returns the authentication service.
     *
     * @return the authentication service
     */
    public static AuthService getAuthService() {
        return AUTH_SERVICE;
    }

    /**
     * Returns the vehicle repository.
     *
     * @return the vehicle repository
     */
    public static VehicleRepository getVehicleRepository() {
        return VEHICLE_REPOSITORY;
    }

    /**
     * Returns the rental repository.
     *
     * @return the rental repository
     */
    public static RentalRepository getRentalRepository() {
        return RENTAL_REPOSITORY;
    }

    /**
     * Returns the vehicle catalogue service.
     *
     * @return the vehicle catalogue service
     */
    public static VehicleCatalogService getVehicleCatalogService() {
        return VEHICLE_CATALOG_SERVICE;
    }

    /**
     * Returns the rental service.
     *
     * @return the rental service
     */
    public static RentalService getRentalService() {
        return RENTAL_SERVICE;
    }

    /**
     * Returns the billing service.
     *
     * @return the billing service
     */
    public static BillingService getBillingService() {
        return BILLING_SERVICE;
    }

    /**
     * Returns the return service.
     *
     * @return the return service
     */
    public static ReturnService getReturnService() {
        return RETURN_SERVICE;
    }
}