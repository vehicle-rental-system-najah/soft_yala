package com.vehiclerental.model;

/**
 * Represents a customer driving license.
 */
public class License {

    /**
     * The license number.
     */
    private String licenseNumber;

    /**
     * The type of the license.
     */
    private LicenseType type;

    /**
     * Creates a license with a number and type.
     *
     * @param licenseNumber the license number
     * @param type the license type
     */
    public License(String licenseNumber, LicenseType type) {
        this.licenseNumber = licenseNumber;
        this.type = type;
    }

    /**
     * Returns the license number.
     *
     * @return the license number
     */
    public String getLicenseNumber() {
        return licenseNumber;
    }

    /**
     * Returns the license type.
     *
     * @return the license type
     */
    public LicenseType getType() {
        return type;
    }
}