package br.com.area51.ufoTracker.model.jpa;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "avistamentos")
@Getter@Setter@NoArgsConstructor
public class AvistamentoJPA {
    @Id@GeneratedValue()
    private UUID id;
    @Column(nullable = false, name = "datahora") //data_hora
    private OffsetDateTime dataHora;
    @Column(nullable = false, precision = 9, scale = 6)
    private BigDecimal latitude;
    @Column(nullable = false, precision = 9, scale = 6)
    private BigDecimal longitude;
    @Column(length = 20,name = "city" , nullable = false)
    private String cidade;
    private String descricao;
    private Integer confiabilidade;

    @Override
    public String toString() {
        return "AvistamentoJPA{" +
                "id=" + id +
                ", cidade='" + cidade + '\'' +
                ", descricao='" + descricao + '\'' +
                ", confiabilidade=" + confiabilidade +
                '}';
    }
}
// PRIMARY KEY
// POJO -> PLain Old Java Object
// atributos privados, Getters e setters contrutor default