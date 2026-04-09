package br.com.area51.ufoTracker.repository.jpa;

import br.com.area51.ufoTracker.model.jpa.Relato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface RelatoRepository  extends JpaRepository<Relato, UUID> {
    @Query("""
        select r 
            from Relato r
             where r.caso.id = :casoId
             order by r.dataHora desc 
    """)
    List<Relato> listarPorCaso(@Param("casoId") UUID casoId);

    @Query("""
        select r.id as id, r.testemunha as testemunha, r.dataHora as dataHora, r.confiabilidade as confiabilidade
        from Relato r
        where r.caso.id = :casoId
        order by r.dataHora desc
        
""")
    List<RelatoResumo> listarResumo(UUID casoId);

    @Query(value =
            """
        select * from avistamentos;
            """, nativeQuery = true)
    void rodandoSqlNativo();

}
