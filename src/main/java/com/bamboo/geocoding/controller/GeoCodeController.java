package com.bamboo.geocoding.controller;

import java.util.List;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bamboo.geocoding.model.GeoCodeEntity;
import com.bamboo.geocoding.model.GeoCodeRequest;
import com.bamboo.geocoding.service.GeoCodeService;

/**
 * This class serves as the entry point for Demo geocoder
 */
@RestController
public class GeoCodeController {
    
    /**
     * Service that facilitates the geocoding
     */
    GeoCodeService geoCodeService;

    public GeoCodeController(GeoCodeService geoCodeService) {
        this.geoCodeService = geoCodeService;
    }

    /**
     * Geocodes address provided in body
     * @param body request containing address
     * @return GeoCodeEntity contain ip cooridinates
     */
    @PostMapping(path = "/geocode/address", consumes = {"application/json"})
    public GeoCodeEntity geoCodeAddress(@Validated @RequestBody GeoCodeRequest body) {
        // accept input
        String inputAddress = body.getAddress();
        // invoke/return services
        return geoCodeService.geoCode(inputAddress);
    }

    /**
     * Retrieve all geocoded entries stored in database
     * @return List of geocoded entities
     */
    @GetMapping(path = "/geocode/retrieve", produces = {"application/json"})
    public List<GeoCodeEntity> geoCodeAddress() {
        return geoCodeService.retrieveAllGeoCodes();
    }
}
