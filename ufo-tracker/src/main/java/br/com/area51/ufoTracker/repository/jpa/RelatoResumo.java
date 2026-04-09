package br.com.area51.ufoTracker.repository.jpa;

import java.time.OffsetDateTime;
import java.util.UUID;

public interface RelatoResumo {
    UUID getId();
    String getTestemunha();
    OffsetDateTime getDataHora();
    Integer getConfiabilidade();
}
