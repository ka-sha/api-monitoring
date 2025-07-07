package ru.vsavushkin.apimonitoring.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.vsavushkin.apimonitoring.model.ApiDataEntity;
import ru.vsavushkin.apimonitoring.service.ApiDataService;

import java.util.List;

@RestController
@RequestMapping("/data")
@RequiredArgsConstructor
public class ApiDataController {

    private ApiDataService dataService;

    @GetMapping
    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<List<ApiDataEntity>> getData() {
        return ResponseEntity.ok(dataService.getLast10Records());
    }
}
