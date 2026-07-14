package com.vehiclerental.service;

import com.vehiclerental.model.Manager;
import com.vehiclerental.repository.ManagerRepository;

/**
 * Provides authentication operations for system managers.
 */
public class AuthService {

    /**
     * Repository used to search for managers.
     */
    private ManagerRepository managerRepository;

    /**
     * The currently logged-in manager.
     */
    private Manager currentManager;

    /**
     * Creates an authentication service using the given manager repository.
     *
     * @param managerRepository the repository that stores manager data
     */
    public AuthService(ManagerRepository managerRepository) {
        this.managerRepository = managerRepository;
    }

    /**
     * Attempts to log in a manager using username and password.
     *
     * @param username the manager username
     * @param password the manager password
     * @return true if login succeeds, false otherwise
     */
    public boolean login(String username, String password) {
        Manager manager = managerRepository.findByUsername(username);

        if (manager == null) {
            return false;
        }

        if (manager.checkPassword(password)) {
            currentManager = manager;
            return true;
        }

        return false;
    }

    /**
     * Logs out the current manager.
     */
    public void logout() {
        currentManager = null;
    }

    /**
     * Checks whether a manager is currently logged in.
     *
     * @return true if a manager is logged in, false otherwise
     */
    public boolean isLoggedIn() {
        return currentManager != null;
    }

    /**
     * Returns the currently logged-in manager.
     *
     * @return the current manager, or null if no manager is logged in
     */
    public Manager getCurrentManager() {
        return currentManager;
    }
}