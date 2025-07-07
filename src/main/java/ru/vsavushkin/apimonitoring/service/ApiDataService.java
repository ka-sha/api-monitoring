package ru.vsavushkin.apimonitoring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.vsavushkin.apimonitoring.model.ApiDataEntity;
import ru.vsavushkin.apimonitoring.repository.ApiDataRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ApiDataService {

    private final ApiDataRepository apiDataRepository;

    public List<ApiDataEntity> getLast10Records() {
        return apiDataRepository.findTop10ByOrderByCreatedDateAtDesc();
    }
}
