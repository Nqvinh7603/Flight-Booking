package com.flightbooking.infrastructure.repository;

import org.springframework.stereotype.Repository;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Repository
public class FlightRepository {
    private final ConcurrentMap<String, ConcurrentMap<String, Boolean>> flights = new ConcurrentHashMap<>();

    public FlightRepository() {
        // Initialize with one flight and one seat for demonstration
        ConcurrentMap<String, Boolean> seats = new ConcurrentHashMap<>();
        seats.put("A34_S012C", true); // true indicates the seat is available
        flights.put("FA634", seats);
    }

    public synchronized boolean checkSeatAvailability(String flightId, String seatId) {
        return flights.getOrDefault(flightId, new ConcurrentHashMap<>()).getOrDefault(seatId, false);
    }

    public synchronized boolean bookSeat(String flightId, String seatId) {
        ConcurrentMap<String, Boolean> seats = flights.get(flightId);
        if (seats != null && seats.getOrDefault(seatId, false)) {
            seats.put(seatId, false); // Mark the seat as booked
            return true;
        }
        return false;
    }
}