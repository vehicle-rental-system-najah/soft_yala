package com.vehiclerental.gui;

import com.vehiclerental.repository.InMemoryManagerRepository;
import com.vehiclerental.repository.InMemoryVehicleRepository;
import com.vehiclerental.repository.ManagerRepository;
import com.vehiclerental.repository.VehicleRepository;
import com.vehiclerental.service.AuthService;
import com.vehiclerental.service.VehicleCatalogService;

public final class GuiContext {

    private static final ManagerRepository MANAGER_REPOSITORY =
            new InMemoryManagerRepository();

    private static final VehicleRepository VEHICLE_REPOSITORY =
            new InMemoryVehicleRepository();

    private static final AuthService AUTH_SERVICE =
            new AuthService(MANAGER_REPOSITORY);

    private static final VehicleCatalogService VEHICLE_CATALOG_SERVICE =
            new VehicleCatalogService(VEHICLE_REPOSITORY);

    private GuiContext() {
    }

    public static AuthService getAuthService() {
        return AUTH_SERVICE;
    }

    public static VehicleRepository getVehicleRepository() {
        return VEHICLE_REPOSITORY;
    }

    public static VehicleCatalogService getVehicleCatalogService() {
        return VEHICLE_CATALOG_SERVICE;
    }
}