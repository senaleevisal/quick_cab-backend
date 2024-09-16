package edu.nibm.booking_service.service.IMPL;

import edu.nibm.booking_service.dao.CabBookingRequest;
import edu.nibm.booking_service.dao.CabBookingResponse;
import edu.nibm.booking_service.service.CabService;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Service
public class CabServiceIMPL implements CabService {

    @Autowired
    private KafkaTemplate<String, CabBookingRequest> kafkaRideRequestTemplate;

    @Autowired
    private KafkaTemplate<String, CabBookingResponse> kafkaDriverResponseTemplate;

    @Autowired
    private SimpMessagingTemplate messagingTemplate; // WebSocket template

    private ConcurrentHashMap<String, CabBookingResponse> responseMap = new ConcurrentHashMap<>();


    // Method to publish ride request to Kafka
    public void publishRideRequest(CabBookingRequest rideRequest) {
        kafkaRideRequestTemplate.send("ride_request_topic", String.valueOf(rideRequest.getRequestId()), rideRequest);
        // Notify drivers via WebSocket
        messagingTemplate.convertAndSend("/topic/rideRequests", rideRequest);
    }

    // Method to handle driver responses
    @KafkaListener(topics = "ride_response_topic")
    public void handleDriverResponse(ConsumerRecord<String, CabBookingResponse> record) {
        String rideId = record.key();
        CabBookingResponse driverResponse = record.value();

        // Save response for lookup in booking flow
        responseMap.put(rideId, driverResponse);

        // Notify the passenger via WebSocket
        messagingTemplate.convertAndSend("/topic/rideStatus/" + rideId, driverResponse);
    }

    @Override
    // Wait for a driver response with a 5-minute timeout
    public CabBookingResponse waitForDriverResponse(long rideId) {
        CompletableFuture<CabBookingResponse> futureResponse = new CompletableFuture<>();

        // Poll every second for up to 5 minutes (300 seconds)
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.submit(() -> {
            int waitTime = 0;
            while (waitTime < 300000) { // 5 minutes in milliseconds
                if (responseMap.containsKey(rideId)) {
                    futureResponse.complete(responseMap.get(rideId));
                    return;
                }
                try {
                    Thread.sleep(1000); // Poll every second
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                waitTime += 1000;
            }
            futureResponse.complete(null); // Timeout after 5 minutes
        });

        try {
            return futureResponse.get(5, TimeUnit.MINUTES);
        } catch (Exception e) {
            return null;
        }
    }
}