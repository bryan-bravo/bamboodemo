package com.bamboo.geocoding.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.maps.GeoApiContext;

@Configuration
public class BeanConfig {

    /*
     * Singleton Google API context 
     */
    @Bean
    public GeoApiContext geoApiContext(@Value("${google.api.key}") String apiKey) {
        GeoApiContext context = new GeoApiContext.Builder()
                .apiKey(apiKey)
                .build();

        return context;
    }

}
