package ru.vsavushkin.apimonitoring.scheduler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.vsavushkin.apimonitoring.kafka.ErrorKafkaProducer;
import ru.vsavushkin.apimonitoring.service.ApiMonitorService;

@Service
@RequiredArgsConstructor
@Slf4j
public class ApiPollingScheduler {

    private final ApiMonitorService apiMonitorService;
    private final ErrorKafkaProducer errorKafkaProducer;

    @Scheduled(fixedRate = 60_000)
    public void pollApi() {
        try {
            apiMonitorService.fetchAndProcessData();
        } catch (Exception e) {
            log.error("External API polling failed permanently: {}", e.getMessage());
            errorKafkaProducer.sendError("Poll API failure: " + e.getMessage());
        }
    }
}
