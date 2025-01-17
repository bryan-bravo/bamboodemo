package com.bamboo.geocoding.service;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.bamboo.geocoding.dao.GeoCodeDAO;
import com.bamboo.geocoding.model.GeoCodeEntity;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.GeocodingApiRequest;
import com.google.maps.model.GeocodingResult;

/**
 * This class serves as a mediator between Google GeoCoding API and In Memory H2Database
 * Created  to serve data back to outer layers(controller) of application.
 */
@Service
public class GeoCodeService {
    /**
     * Singleton Context object that contains context when reaching out to Java API
     */
    GeoApiContext geoContext;
    /**
     * Data access object for updateing/reading from data source 
     */
    GeoCodeDAO geoCodeDAO;

    // construction autowiring
    public GeoCodeService(GeoApiContext geoApiContext,  GeoCodeDAO geoCodeDAO) {
        this.geoContext = geoApiContext;
        this.geoCodeDAO = geoCodeDAO;
    }

    /**
     * GeoCode an address.
     * @param address Address to geocode
     * @return GeoCodeEntity formatted address, lat, lng.
     */
    public GeoCodeEntity geoCode(String address) {
        
        // intialize working variables
        GeocodingApiRequest request =  GeocodingApi.geocode(geoContext,address);
        GeoCodeEntity serviceResponse = null;
        try {

            // invoke google api
            GeocodingResult[] apiResponse = request.await();
            
            // map response to working entity
            serviceResponse = mapGeoCodingResultToEntity(apiResponse);
            
            // insert response into database
            geoCodeDAO.insertGeoCode(serviceResponse);

        } catch (Exception e) {
            // handle exception as needed.
            e.printStackTrace();
        }

        // return
        return serviceResponse;

    }

    /**
     * Map Response from Google GeoCoding API to working VO(Value Object) which can be utilized in DAO layer of application.
     * TODO: as application matures move this to its own class
     * @param response Response from Google GeoCoding API
     * @return GeoCodeEntity Working value object
     */
    private GeoCodeEntity mapGeoCodingResultToEntity(GeocodingResult[] response) {
        /**
          Extract data from API response.
        Obtain first entry in collection. One address can have multiple geocoded representions. Ex: AVE <-> Avenue.
        */
        GeocodingResult res1 = response[0];
        double lat = res1.geometry.location.lat;
        double lng = res1.geometry.location.lng;
        // build Working object
        GeoCodeEntity geoCodeEntity = new GeoCodeEntity(res1.formattedAddress, String.valueOf(lat), String.valueOf(lng));
        return geoCodeEntity;
    }

    /**
     * Retrieve all GeoCode entries stored in database.
     * @return List<GeoCodeEntity> entities stored in database.
     */
    public List<GeoCodeEntity> retrieveAllGeoCodes() {
        return geoCodeDAO.getAllGeoCodes();
    }
}
