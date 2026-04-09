package br.com.area51.ufoTracker.repository;

import br.com.area51.ufoTracker.model.Avistamento;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
//jpa -> hibernate
//injecao ->
import java.time.LocalDateTime;
import java.util.*;

@Repository
public class AvistamentoRepository  {
    private final List<Avistamento> avistamentos = new ArrayList<>();
    private Random random = new Random();

    public AvistamentoRepository() {
        avistamentos.addAll(List.of(
                novoAvistamento("Roswell, EUA", "Objeto discoide prateado pairando no céu", "Disco", "John Doe", 9),
                novoAvistamento("Nazca, Peru", "Luzes dançantes sobre as linhas de Nazca", "Luz", "Maria Inca", 8),
                novoAvistamento("Área 51, EUA", "Explosão luminosa e som agudo", "Explosão", "Bob Lazar", 10),
                novoAvistamento("Deserto do Atacama, Chile", "Objeto em forma de charuto acelerando", "Charuto", "Pablo Reyes", 7),
                novoAvistamento("Tunguska, Rússia", "Clarão no céu seguido de tremor", "Clarão", "Ivan Petrov", 6),
                novoAvistamento("Vale do Paraíba, Brasil", "Esfera metálica girando silenciosamente", "Esfera", "Ana Silva", 8),
                novoAvistamento("Stonehenge, Inglaterra", "Objeto triangular invisível ao radar", "Triângulo", "Arthur Pendragon", 9),
                novoAvistamento("Monte Fuji, Japão", "Luz azul entrando no vulcão", "Luz", "Hiro Tanaka", 5),
                novoAvistamento("Reykjavik, Islândia", "Objeto flutuante refletindo aurora", "Reflexo", "Elsa Bjork", 6),
                novoAvistamento("Ilha de Páscoa", "Círculo de fogo subindo ao céu", "Fogo", "Moai Testemunha", 10)
        ));
    }
    public List<Avistamento> listarTodos(){
        return avistamentos;
    }
    public Optional<Avistamento> buscarPorId(Long id){
        return  avistamentos.stream()
                .filter(avistamento -> avistamento.getId().equals(id))
               .findFirst();
    }
    public Avistamento salvar(Avistamento avistamento) {
        avistamento.setId(Math.abs(random.nextLong())); // atribui novo ID
        avistamentos.add(avistamento);
        return avistamento;
    }
    public boolean removerPeloId(Long id) {
        return avistamentos.removeIf(avistamento -> avistamento.getId().equals(id));
    }

    private Avistamento novoAvistamento(String local, String descricao, String tipo, String testemunha, int confiabilidade) {
        Avistamento a = new Avistamento();
        a.setId(Math.abs(random.nextLong())); // gera ID aleatório positivo
        a.setLocalizacao(local);
        a.setDescricao(descricao);
        a.setTipoObjeto(tipo);
        a.setTestemunha(testemunha);
        a.setNivelConfiabilidade(confiabilidade);
        a.setDataHora(LocalDateTime.now().minusDays(random.nextInt(100)));
        return a;
    }

}
