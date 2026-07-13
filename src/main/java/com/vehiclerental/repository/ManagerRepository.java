package com.vehiclerental.repository;

import com.vehiclerental.model.Manager;

public interface ManagerRepository {
    Manager findByUsername(String username);
}