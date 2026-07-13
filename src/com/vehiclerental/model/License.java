package com.vehiclerental.model;

public class License {
    private String licenseNumber;
    private LicenseType type;

    public License(String licenseNumber, LicenseType type) {
        this.licenseNumber = licenseNumber;
        this.type = type;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public LicenseType getType() {
        return type;
    }
}