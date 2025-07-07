package ru.vsavushkin.apimonitoring.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.management.ManagementFactory;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(name = "/status")
public class ApiStatusController {

    @GetMapping
    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    public ResponseEntity<Map<String, Object>> getStatus() {
        Map<String, Object> response = new HashMap<>();
        long uptimeMillis = ManagementFactory.getRuntimeMXBean().getUptime();

        response.put("status", "OK");
        response.put("uptimeSeconds", uptimeMillis / 1000);
        return ResponseEntity.ok(response);
    }
}
