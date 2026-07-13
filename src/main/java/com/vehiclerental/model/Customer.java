package com.vehiclerental.model;

public class Customer {
    private int id;
    private String name;
    private String phone;
    private String email;
    private int age;
    private License license;

    public Customer(int id, String name, String phone, int age) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = "";
        this.age = age;
        this.license = null;
    }

    public Customer(int id, String name, String phone, String email, int age) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.age = age;
        this.license = null;
    }

    public Customer(int id, String name, String phone, String email, int age, License license) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.age = age;
        this.license = license;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public int getAge() {
        return age;
    }

    public License getLicense() {
        return license;
    }

    public void setLicense(License license) {
        this.license = license;
    }
}