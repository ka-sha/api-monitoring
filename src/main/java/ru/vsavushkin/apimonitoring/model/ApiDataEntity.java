package ru.vsavushkin.apimonitoring.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "api_data")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApiDataEntity {

    @Id
    private UUID id;

    @Column(nullable = false, updatable = false)
    private Instant createdAt;

    @Column(nullable = false)
    private boolean success;

    @Lob
    @Column
    private String payload;

    @PrePersist
    public void prePersist() {
        if (Objects.isNull(this.createdAt)) {
            this.createdAt = Instant.now();
        }
    }
}
