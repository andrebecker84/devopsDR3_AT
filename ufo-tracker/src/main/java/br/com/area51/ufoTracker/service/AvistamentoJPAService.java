package br.com.area51.ufoTracker.service;

import br.com.area51.ufoTracker.model.jpa.AvistamentoJPA;
import br.com.area51.ufoTracker.repository.jpa.AvistamentoJPARepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
public class AvistamentoJPAService {
    private static final ZoneId ZONE_ZP = ZoneId.of("America/Sao_Paulo");
    private final AvistamentoJPARepository repo;
    public AvistamentoJPA save(AvistamentoJPA avistamento) {
        if(avistamento.getDataHora() == null){
            avistamento.setDataHora(OffsetDateTime.now(ZONE_ZP));
        }
        return repo.save(avistamento);
    }
    public Page<AvistamentoJPA> listas(Integer page, Integer size){
        int p = page == null ? 0 : Math.max(0, page);
        int s = size == null ? 0 : Math.max(1, size);
        PageRequest pageRequest = PageRequest.of(p, s);
        return repo.findAll(pageRequest);
    }
    public Optional<AvistamentoJPA> getByID(UUID uuid){
        return repo.findById(uuid);
    }

    private void avistamentoExiste(UUID id) {
        if (!repo.existsById(id)) {
            throw new NoSuchElementException("Avistamento não encontrado: " + id);
        }
    }
    @Transactional(readOnly = true)
    public Optional<AvistamentoJPA> obter(UUID id) {
        return repo.findById(id);
    }
    @Transactional
    public AvistamentoJPA atualizar(UUID id, AvistamentoJPA dados) {
        Optional<AvistamentoJPA> obter = obter(id);
        AvistamentoJPA existente = obter.orElseThrow(() ->
                new NoSuchElementException("Avistamento não encontrado: " + id));

        if (dados.getDataHora() != null) existente.setDataHora(dados.getDataHora());
        if (dados.getLatitude() != null)  existente.setLatitude(dados.getLatitude());
        if (dados.getLongitude() != null) existente.setLongitude(dados.getLongitude());
        if (dados.getCidade() != null)    existente.setCidade(dados.getCidade());
        if (dados.getDescricao() != null) existente.setDescricao(dados.getDescricao());

        return repo.save(existente);
    }
}
