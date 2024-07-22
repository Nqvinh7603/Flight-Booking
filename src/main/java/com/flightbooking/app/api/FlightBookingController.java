package com.flightbooking.app.api;

import com.flightbooking.infrastructure.repository.FlightRepository;
import com.flightbooking.infrastructure.service.LockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FlightBookingController {

    private final LockService lockService;
    private final FlightRepository flightRepository;

    @Autowired
    public FlightBookingController(LockService lockService, FlightRepository flightRepository) {
        this.lockService = lockService;
        this.flightRepository = flightRepository;
    }

    @GetMapping("/book/{flightId}/{seatId}")
    public String bookSeat(@PathVariable String flightId, @PathVariable String seatId) {
        String lockKey = flightId + "_" + seatId + "_lock";
        long expirationTime = 10000; // Example expiration time in milliseconds
        if (lockService.acquireLock(lockKey, expirationTime)) {
            try {
                if (flightRepository.checkSeatAvailability(flightId, seatId)) {
                    if (flightRepository.bookSeat(flightId, seatId)) {
                        return "Booking successful for seat " + seatId + " on flight " + flightId;
                    }
                }
                return "Seat " + seatId + " on flight " + flightId + " is not available.";
            } finally {
                lockService.releaseLock(lockKey);
            }
        } else {
            return "Seat " + seatId + " on flight " + flightId + " is currently being booked by another process.";
        }
    }
}