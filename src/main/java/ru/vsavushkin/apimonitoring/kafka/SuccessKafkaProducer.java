package ru.vsavushkin.apimonitoring.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class SuccessKafkaProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private static final String TOPIC = "api-data";

    public void send(String message) {
        kafkaTemplate.send(TOPIC, message);
        log.info("Success send message {} to Kafka topic: [{}]", message, TOPIC);
    }
}
