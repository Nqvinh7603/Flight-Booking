package com.flightbooking.domain.airport.repository;

import com.flightbooking.domain.airport.entity.Airport;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface AirportRepository {
    List<Airport> findAll();
}
