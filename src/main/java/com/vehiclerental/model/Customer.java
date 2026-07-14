package com.vehiclerental.model;

/**
 * Represents a customer who can rent vehicles.
 */
public class Customer {

    /**
     * The customer id.
     */
    private int id;

    /**
     * The customer name.
     */
    private String name;

    /**
     * The customer phone number.
     */
    private String phone;

    /**
     * The customer email address.
     */
    private String email;

    /**
     * The customer age.
     */
    private int age;

    /**
     * The customer driving license.
     */
    private License license;

    /**
     * Creates a customer without email and license information.
     *
     * @param id the customer id
     * @param name the customer name
     * @param phone the customer phone number
     * @param age the customer age
     */
    public Customer(int id, String name, String phone, int age) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = "";
        this.age = age;
        this.license = null;
    }

    /**
     * Creates a customer with email information.
     *
     * @param id the customer id
     * @param name the customer name
     * @param phone the customer phone number
     * @param email the customer email address
     * @param age the customer age
     */
    public Customer(int id, String name, String phone, String email, int age) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.age = age;
        this.license = null;
    }

    /**
     * Creates a customer with email and license information.
     *
     * @param id the customer id
     * @param name the customer name
     * @param phone the customer phone number
     * @param email the customer email address
     * @param age the customer age
     * @param license the customer driving license
     */
    public Customer(int id, String name, String phone, String email, int age, License license) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.age = age;
        this.license = license;
    }

    /**
     * Returns the customer id.
     *
     * @return the customer id
     */
    public int getId() {
        return id;
    }

    /**
     * Returns the customer name.
     *
     * @return the customer name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the customer phone number.
     *
     * @return the customer phone number
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Returns the customer email address.
     *
     * @return the customer email address
     */
    public String getEmail() {
        return email;
    }

    /**
     * Returns the customer age.
     *
     * @return the customer age
     */
    public int getAge() {
        return age;
    }

    /**
     * Returns the customer license.
     *
     * @return the customer license
     */
    public License getLicense() {
        return license;
    }

    /**
     * Updates the customer license.
     *
     * @param license the new license
     */
    public void setLicense(License license) {
        this.license = license;
    }
}