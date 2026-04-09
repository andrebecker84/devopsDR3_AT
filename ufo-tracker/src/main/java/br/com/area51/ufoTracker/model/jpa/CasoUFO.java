package br.com.area51.ufoTracker.model.jpa;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data@AllArgsConstructor@NoArgsConstructor@Builder
@Entity
@Table(name = "casos_ufo",
        indexes = {
                @Index(name="idx_caso_data_inicio", columnList = "dataInicio"),
                @Index(name="idx_caso_cidade", columnList = "cidade")
        })
public class CasoUFO {
    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false, length = 60)
    private String cidade;

    @Column(nullable = false)
    private OffsetDateTime dataInicio;

    private OffsetDateTime dataFim;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private StatusCaso status = StatusCaso.ABERTO;

    // 1 Caso -> N Relatos
    @OneToMany(mappedBy = "caso", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Relato> relatos = new ArrayList<>();

    // getters/setters…
    // helpers:
    public void addRelato(Relato r) { r.setCaso(this); relatos.add(r); }
    public void removeRelato(Relato r) { r.setCaso(null); relatos.remove(r); }

    public enum StatusCaso { ABERTO, EM_ANALISE, ENCERRADO }
}