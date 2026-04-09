package br.com.area51.ufoTracker.controller;

import br.com.area51.ufoTracker.model.Avistamento;
import br.com.area51.ufoTracker.model.AvistamentoFiltro;
import br.com.area51.ufoTracker.repository.AvistamentoRepository;
import br.com.area51.ufoTracker.repository.jpa.AvistamentoJPARepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import java.util.Random;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/avistamento")
@RequiredArgsConstructor@Getter
public class AvistamentoController {
    private final AvistamentoRepository repository;
    private final AvistamentoJPARepository jpaRepo;
    @GetMapping("/paginado")
    public ResponseEntity<List<Avistamento>> listarTodosPaginado(
            @RequestHeader(value = "X-Page", required = false, defaultValue = "0")  int page,
            @RequestHeader(value = "X-Size", required = false, defaultValue = "5") int size){

        List<Avistamento> avistamentos = repository.listarTodos();
        int fromIndex = page * size;
        int toIndex = Math.min(fromIndex + size, avistamentos.size());
        if(fromIndex >= avistamentos.size()){
            return ResponseEntity.noContent().build();
        }
        List<Avistamento> pagina = avistamentos.subList(fromIndex, toIndex);
        int totalPages = (int) Math.ceil((double) avistamentos.size()/size);
        return ResponseEntity.ok()
                .header("X-Total-Count", String.valueOf(avistamentos.size()))
                .header("X-Total-Pages", String.valueOf(totalPages))
                .header("X-Page-Size", String.valueOf(size))
                .body(pagina);
    }

    //GET /avistamento -LISTAR TODOS
    //GET /avistamento?localidade=RJ&confiabilidade=9
    @GetMapping
    public List<Avistamento> listarAvistamentos() {
        return repository.listarTodos();
    }
    @GetMapping("/comFiltros")
    public List<Avistamento> listarComFiltro(@ModelAttribute AvistamentoFiltro f) {
        if(f.isVazio()) return repository.listarTodos();
        Predicate<Avistamento> filtros = a -> true;

        if(f.localizacao() != null && !f.localizacao().isBlank()){
            //Adicionar essa busca
            filtros = filtros.and(a -> a.getLocalizacao().equalsIgnoreCase(f.localizacao()));

        }
        if(f.confiabilidadeMin() != null){
            filtros = filtros.and(a -> a.getNivelConfiabilidade() >= f.confiabilidadeMin());
        }
        if(f.de() != null){
            filtros = filtros.and(a -> !a.getDataHora().isBefore(f.de()));
        }
        if(f.ate() != null){
            filtros = filtros.and(a -> !a.getDataHora().isAfter(f.ate()));
        }
        return repository.listarTodos().stream()
                .filter(filtros).toList();
    }



    //GET /avistamento/id /12
    @GetMapping("/{id}")
    public ResponseEntity<Avistamento> buscarPorId(@PathVariable Long id) {
        return repository.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @GetMapping("/aleatorio")
    public ResponseEntity<Avistamento> buscarAleatorio() {
        List<Avistamento> lista = repository.listarTodos();
        if (lista.isEmpty()) return ResponseEntity.noContent().build();

        Avistamento escolhido = lista.get(new Random().nextInt(lista.size()));
        return ResponseEntity.ok(escolhido);
    }

    // 📍 GET /avistamentos/buscar?localizacao=Roswell
    @GetMapping("/buscar")
    public List<Avistamento> buscarPorLocal(@RequestParam String localizacao) {
        return repository.listarTodos().stream()
                .filter(a -> a.getLocalizacao().toLowerCase().contains(localizacao.toLowerCase()))
                .collect(Collectors.toList());
    }

    // 🔎 GET /avistamentos?confiabilidadeMin=7
    @GetMapping(params = "confiabilidadeMin")
    public List<Avistamento> filtrarPorConfiabilidade(@RequestParam int confiabilidadeMin) {
        return repository.listarTodos().stream()
                .filter(a -> a.getNivelConfiabilidade() >= confiabilidadeMin)
                .collect(Collectors.toList());
    }
}
