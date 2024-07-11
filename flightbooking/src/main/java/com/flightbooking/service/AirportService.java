package com.flightbooking.service;

import com.flightbooking.model.Airport;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class AirportService {

    @Autowired
    private CacheManager cacheManager;

    @PostConstruct
    private void loadAirports() {
        List<Airport> airports = Arrays.asList(
                new Airport("LAX", "Los Angeles International Airport"),
                new Airport("JFK", "John F. Kennedy International Airport")
                // Add more airports as needed
        );
        cacheManager.getCache("airports").put("airportList", airports);
    }

    public List<Airport> getAirports() {
        return Optional.ofNullable(cacheManager.getCache("airports").get("airportList", List.class))
                .orElseGet(() -> {
                    // This block is for demonstration. Normally, you'd fetch from a DB or external service.
                    List<Airport> defaultAirports = Arrays.asList(
                            new Airport("DUMMY", "Dummy Airport")
                    );
                    cacheManager.getCache("airports").put("airportList", defaultAirports);
                    return defaultAirports;
                });
    }
}