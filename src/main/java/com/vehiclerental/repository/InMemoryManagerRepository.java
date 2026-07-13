package com.vehiclerental.repository;

import com.vehiclerental.model.Manager;
import java.util.ArrayList;
import java.util.List;

/**
 * Stores manager data in memory.
 */
public class InMemoryManagerRepository implements ManagerRepository {

    /**
     * List of managers stored in memory.
     */
    private List<Manager> managers;

    /**
     * Creates an in-memory manager repository with sample data.
     */
    public InMemoryManagerRepository() {
        managers = new ArrayList<>();
        managers.add(new Manager(1, "admin", "1234"));
    }

    /**
     * Finds a manager by username.
     *
     * @param username the manager username
     * @return the matching manager, or null if not found
     */
    @Override
    public Manager findByUsername(String username) {
        for (Manager manager : managers) {
            if (manager.getUsername().equals(username)) {
                return manager;
            }
        }
        return null;
    }
}