package org.jcv.product.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jcv.product.dto.ProductEventDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class EventPublisher {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper mapper  ;
//    mapper.registerModule(new JavaTimeModule());

    @Value("${kafka.topic}")
    private String topic;

    public EventPublisher(KafkaTemplate<String, String> kafkaTemplate, ObjectMapper mapper) {
        this.kafkaTemplate = kafkaTemplate;
        this.mapper = mapper;
    }

    public void publish( ProductEventDto event) {
        try {
            String json = mapper.writeValueAsString(event);
            kafkaTemplate.send( topic, String.valueOf( event.getProductId() ), json);
        } catch ( JsonProcessingException e) {
            throw new RuntimeException("Failed to serialize event", e);
        }
    }
}
