package br.com.area51.ufoTracker.repository.jpa;

import br.com.area51.ufoTracker.model.jpa.CasoUFO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CasoUFORepository extends JpaRepository<CasoUFO, UUID> {
    //JPQL

    @Query("""
        select c from CasoUFO c 
                where lower(c.cidade) = lower(:cidade) 
                and c.dataInicio between :init and :fim
                order by  c.dataInicio desc 
        """)
    List<CasoUFO> buscarPorCidadeEPeriodo(@Param("cidade") String cidade,
                                          @Param("inicio") OffsetDateTime inicio,
                                          @Param("final") OffsetDateTime fim
    );
    @Query("""
            select c from CasoUFO c
            join c.relatos r
            group by c
            having avg (r.confiabilidade) >= : minMedia
            """)
    List<CasoUFO> casosComBoaConfiabilidade(@Param("minMedia") double minMedia);

}
