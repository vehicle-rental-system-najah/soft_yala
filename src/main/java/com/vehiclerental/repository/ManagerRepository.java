package com.vehiclerental.repository;

import com.vehiclerental.model.Manager;

/**
 * Defines operations for accessing manager data.
 */
public interface ManagerRepository {

    /**
     * Finds a manager by username.
     *
     * @param username the manager username
     * @return the matching manager, or null if not found
     */
    Manager findByUsername(String username);
}