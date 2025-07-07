package ru.vsavushkin.apimonitoring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.vsavushkin.apimonitoring.model.ApiDataEntity;

import java.util.List;
import java.util.UUID;

public interface ApiDataRepository extends JpaRepository<ApiDataEntity, UUID> {

    @Query("SELECT data FROM ApiDataEntity data ORDER BY data.createdAt DESC LIMIT 10")
    List<ApiDataEntity> findTop10ByOrderByCreatedDateAtDesc();
}
