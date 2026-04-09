package br.com.area51.ufoTracker.repository.jpa;

import br.com.area51.ufoTracker.model.jpa.AvistamentoJPA;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;

@Repository
public interface AvistamentoJPARepository extends JpaRepository<AvistamentoJPA, UUID> {
    List<AvistamentoJPA> findByCidade(String cidade);
    Page<AvistamentoJPA> findByCidadeContainingIgnoreCase(String cidade, Pageable pageable);
    List<AvistamentoJPA> findByCidadeAndConfiabilidadeGreaterThanEqual(String cidade, Integer confiabilidadeMin);
    List<AvistamentoJPA> findByDataHoraBetween(OffsetDateTime inicio, OffsetDateTime fim);
    Optional<AvistamentoJPA> findFirstByCidadeOrderByConfiabilidadeDesc(String cidade);

    long countByCidade(String cidade);
    boolean existsByCidadeAndDataHoraBetween(String cidade, OffsetDateTime inicio, OffsetDateTime fim);
    long deleteByDataHoraBefore(OffsetDateTime limite);

    List<AvistamentoJPA> findByLatitudeBetweenAndLongitudeBetween(
            BigDecimal latMin, BigDecimal latMax,
            BigDecimal longMin, BigDecimal longMax
            );

    @Query("select a from AvistamentoJPA a where a.cidade = :cidade")
    Stream<AvistamentoJPA> streamByCidade(@Param("cidade") String cidade);

}

