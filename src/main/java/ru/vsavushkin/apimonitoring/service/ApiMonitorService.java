package ru.vsavushkin.apimonitoring.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import ru.vsavushkin.apimonitoring.kafka.SuccessKafkaProducer;
import ru.vsavushkin.apimonitoring.model.ApiDataEntity;
import ru.vsavushkin.apimonitoring.repository.ApiDataRepository;

import java.time.Instant;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class ApiMonitorService {
    // вот это переименовываем в ApiMonitorService

    private final RestTemplate restTemplate;
    private final ApiDataRepository apiDataRepository;
    private final SuccessKafkaProducer successKafkaProducer;

    private static final String API_URL = "https://fakestoreapi.com/products/1";

    @Retryable(
            maxAttempts = 3,
            backoff = @Backoff(delay = 2000, multiplier = 2)
    )
    @Transactional
    public void fetchAndProcessData() {
        log.info("Start call to external API");
        String response = restTemplate.getForObject(API_URL, String.class);

        ApiDataEntity dataEntity = ApiDataEntity.builder()
                .id(UUID.randomUUID())
                .createdAt(Instant.now())
                .success(true)
                .payload(response)
                .build();

        apiDataRepository.save(dataEntity);
        successKafkaProducer.send(response);

        log.info("Finish success call to external API");
    }
}
