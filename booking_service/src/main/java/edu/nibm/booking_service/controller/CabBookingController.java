package edu.nibm.booking_service.controller;

import edu.nibm.booking_service.dao.CabBookingRequest;
import edu.nibm.booking_service.dao.CabBookingResponse;
import edu.nibm.booking_service.service.CabService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/cab")
public class CabBookingController {

    @Autowired
    private CabService cabBookingService;

    @PostMapping("/book")
    public ResponseEntity<String> bookCab(@RequestBody CabBookingRequest rideRequest) {
        // Generate a unique Ride ID
        long rideId = Integer.parseInt(UUID.randomUUID().toString());
        rideRequest.setRequestId(rideId);

        // Publish the ride request to Kafka
        cabBookingService.publishRideRequest(rideRequest);

        // Wait for a driver response (with 5 minutes timeout)
        CabBookingResponse driverResponse = null;
        driverResponse = cabBookingService.waitForDriverResponse(rideId);

        // Check if a driver responded or if the request timed out
        if (driverResponse != null) {
            return ResponseEntity.ok("Ride accepted by driver ID: " + driverResponse.getRequestId());
        } else {
            return ResponseEntity.status(HttpStatus.REQUEST_TIMEOUT).body("No drivers available at the moment.");
        }
    }

    @MessageMapping("/ride/{rideId}")
    @SendTo("/topic/rideStatus/{rideId}")
    public CabBookingResponse simulateDriverResponse(@DestinationVariable String rideId, CabBookingResponse driverResponse) {
        // Simulate a driver accepting the ride
        driverResponse.setStatus(true);
        return driverResponse;
    }
}