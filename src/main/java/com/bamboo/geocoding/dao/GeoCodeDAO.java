package com.bamboo.geocoding.dao;

import java.util.List;

import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import com.bamboo.geocoding.model.GeoCodeEntity;

@Repository
public class GeoCodeDAO {
    
    JdbcClient jdbcClient;

    public GeoCodeDAO(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    public void insertGeoCode(GeoCodeEntity data) {
        jdbcClient.sql("INSERT INTO GEO_CODE_ENTITY VALUES (:formattedAddress, :latitude, :longitude)")
        .param("formattedAddress",data.getFormattedAddress())
        .param("latitude",data.getLatitude())
        .param("longitude",data.getLongitude())
        .update();
    }

    public List<GeoCodeEntity> getAllGeoCodes () {
        List<GeoCodeEntity> allGeoCodes = jdbcClient
        .sql("SELECT * from GEO_CODE_ENTITY")
        .query(
            new GeoCodeEntityExtractor()
        );
        return allGeoCodes;
    }
}
