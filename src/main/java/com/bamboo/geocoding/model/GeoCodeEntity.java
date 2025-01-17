package com.bamboo.geocoding.model;

public class GeoCodeEntity {

    /**
     * Formatted Address returned from Google API.
     */
    private String formattedAddress;
    /**
     * Cooridinate latitude returned from Google API.
     */
    private String latitude;
    /**
     * Cooridinate longitude returned from Google API.
     */
    private String longitude;

    public GeoCodeEntity(String formattedAddress, String latitude, String longitude) {
        this.formattedAddress = formattedAddress;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getFormattedAddress() {
        return formattedAddress;
    }
    public void setFormattedAddress(String formattedAddress) {
        this.formattedAddress = formattedAddress;
    }
    public String getLatitude() {
        return latitude;
    }
    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }
    public String getLongitude() {
        return longitude;
    }
    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

}
