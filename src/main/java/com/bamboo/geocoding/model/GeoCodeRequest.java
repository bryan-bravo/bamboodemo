package com.bamboo.geocoding.model;

import org.jetbrains.annotations.NotNull;

/**
 * Request payload object utilized at time of form submission.
 */
public class GeoCodeRequest {

    /**
     * Address input field from HTML
     */
    @NotNull
    private String address;

    public GeoCodeRequest() {
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    
}
