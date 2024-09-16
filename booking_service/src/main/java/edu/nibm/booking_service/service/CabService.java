package edu.nibm.booking_service.service;

import edu.nibm.booking_service.dao.CabBookingRequest;
import edu.nibm.booking_service.dao.CabBookingResponse;
import org.apache.coyote.Request;

public interface CabService {
    void publishRideRequest(CabBookingRequest rideRequest);

    CabBookingResponse waitForDriverResponse(long rideId);
}
