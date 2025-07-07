package ru.vsavushkin.apimonitoring.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
@RequiredArgsConstructor
@Slf4j
public class ErrorKafkaProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private static final String TOPIC = "api-errors";

    public void sendError(String errorMessage) {
        String payload = "{\"timestamp\":\"" + Instant.now() + "\",\"error\":\"" + errorMessage + "\"}";
        kafkaTemplate.send(TOPIC, payload);
        log.error("Error message {} send to Kafka topic: [{}]", errorMessage, TOPIC);
    }
}
