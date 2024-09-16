package edu.nibm.booking_service.config;

import edu.nibm.booking_service.dao.CabBookingRequest;
import edu.nibm.booking_service.dao.CabBookingResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

@Configuration
@EnableKafka
public class KafkaConfig {

    @Bean
    public KafkaTemplate<String, CabBookingRequest> kafkaRideRequestTemplate(ProducerFactory<String, CabBookingRequest> producerFactory) {
        return new KafkaTemplate<>(producerFactory);
    }

    @Bean
    public KafkaTemplate<String, CabBookingResponse> kafkaDriverResponseTemplate(ProducerFactory<String, CabBookingResponse> producerFactory) {
        return new KafkaTemplate<>(producerFactory);
    }
}