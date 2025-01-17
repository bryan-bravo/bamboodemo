package com.bamboo.geocoding.dao;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.bamboo.geocoding.model.GeoCodeEntity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GeoCodeEntityExtractor implements ResultSetExtractor<List<GeoCodeEntity>> {

    @Override
    public List<GeoCodeEntity> extractData(ResultSet rs) throws SQLException, DataAccessException {
        List<GeoCodeEntity> geoCodeEntities = new ArrayList<>();
        while (rs.next()) {
            String formattedAddress = rs.getString("formattedAddress");
            String latitude = rs.getString("latitude");
            String longitude = rs.getString("longitude");
            GeoCodeEntity geoCodeEntity = new GeoCodeEntity(formattedAddress, latitude, longitude);
            geoCodeEntities.add(geoCodeEntity);
        }
        return geoCodeEntities;
    }
}
