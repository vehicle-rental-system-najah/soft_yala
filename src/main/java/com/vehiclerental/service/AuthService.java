package com.vehiclerental.service;

import com.vehiclerental.model.Manager;
import com.vehiclerental.repository.ManagerRepository;

public class AuthService {
    private ManagerRepository managerRepository;
    private Manager currentManager;

    public AuthService(ManagerRepository managerRepository) {
        this.managerRepository = managerRepository;
    }

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

    public void logout() {
        currentManager = null;
    }

    public boolean isLoggedIn() {
        return currentManager != null;
    }

    public Manager getCurrentManager() {
        return currentManager;
    }
}