package br.com.area51.ufoTracker.model.jpa;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

@Data@AllArgsConstructor@NoArgsConstructor@Builder
@Entity
@Table(name = "relatos",
        indexes = { @Index(name="idx_relato_datahora", columnList = "dataHora") })
public class Relato {
    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "caso_id")
    private CasoUFO caso;

    @Column(nullable = false, length = 80)
    private String testemunha;

    @Column(nullable = false)
    private OffsetDateTime dataHora;

    @Column(nullable = false, precision = 9, scale = 6)
    private BigDecimal latitude;

    @Column(nullable = false, precision = 9, scale = 6)
    private BigDecimal longitude;

    @Column(columnDefinition = "text")
    private String descricao;

    // 0–100 (confiabilidade do relato)
    @Column(nullable = false)
    private Integer confiabilidade;

    // getters/setters…
}