package com.flightbooking.infrastructure.repository;

import com.flightbooking.domain.airport.entity.Airport;
import com.flightbooking.domain.airport.repository.AirportRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

@Repository
public class AirportRepositoryImpl implements AirportRepository {

    @Override
    @Cacheable("airports")
    public List<Airport> findAll() {
        return Arrays.asList(
                new Airport("LAX", "Los Angeles International Airport"),
                new Airport("JFK", "John F. Kennedy International Airport")
        );
    }
}
