package edu.nibm.booking_service.dao;

import lombok.Data;

@Data
public class CabBookingRequest {
    private long requestId;
    private long userId;
    private long driverId;
    private String start;
    private String destination;


}
