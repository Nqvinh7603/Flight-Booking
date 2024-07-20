package com.flightbooking.repository;

import com.flightbooking.model.Airport;
import java.util.List;

public interface AirportRepository {
    List<Airport> findAll();
}