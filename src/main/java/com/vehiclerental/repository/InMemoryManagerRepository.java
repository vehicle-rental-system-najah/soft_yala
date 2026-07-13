package com.vehiclerental.repository;

import com.vehiclerental.model.Manager;
import java.util.ArrayList;
import java.util.List;

public class InMemoryManagerRepository implements ManagerRepository {
    private List<Manager> managers;

    public InMemoryManagerRepository() {
        managers = new ArrayList<>();
        managers.add(new Manager(1, "admin", "1234"));
    }

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