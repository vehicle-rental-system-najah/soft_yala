package com.vehiclerental.service;

import com.vehiclerental.repository.InMemoryManagerRepository;
import com.vehiclerental.repository.ManagerRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AuthServiceTest {

    @Test
    void loginShouldPassWithValidCredentials() {
        ManagerRepository managerRepository = new InMemoryManagerRepository();
        AuthService authService = new AuthService(managerRepository);

        boolean result = authService.login("admin", "1234");

        assertTrue(result);
        assertTrue(authService.isLoggedIn());
    }

    @Test
    void loginShouldFailWithInvalidPassword() {
        ManagerRepository managerRepository = new InMemoryManagerRepository();
        AuthService authService = new AuthService(managerRepository);

        boolean result = authService.login("admin", "wrong");

        assertFalse(result);
        assertFalse(authService.isLoggedIn());
    }

    @Test
    void logoutShouldClearCurrentManager() {
        ManagerRepository managerRepository = new InMemoryManagerRepository();
        AuthService authService = new AuthService(managerRepository);

        authService.login("admin", "1234");
        authService.logout();

        assertFalse(authService.isLoggedIn());
        assertNull(authService.getCurrentManager());
    }
}