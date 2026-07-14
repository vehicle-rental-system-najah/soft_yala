package com.vehiclerental.model;

/**
 * Represents a system manager who can log in to the system.
 */
public class Manager {

    /**
     * The manager id.
     */
    private int id;

    /**
     * The manager username.
     */
    private String username;

    /**
     * The manager password.
     */
    private String password;

    /**
     * Creates a manager with login information.
     *
     * @param id the manager id
     * @param username the manager username
     * @param password the manager password
     */
    public Manager(int id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    /**
     * Returns the manager id.
     *
     * @return the manager id
     */
    public int getId() {
        return id;
    }

    /**
     * Returns the manager username.
     *
     * @return the manager username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Checks whether the given password matches the manager password.
     *
     * @param password the password to check
     * @return true if the password matches, false otherwise
     */
    public boolean checkPassword(String password) {
        return this.password.equals(password);
    }
}