package br.com.area51.ufoTracker.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter@Setter@ToString
public class Avistamento {
    private Long id;
    private String localizacao;
    private String descricao;
    private String tipoObjeto;
    private String testemunha;
    private int nivelConfiabilidade;
    private LocalDateTime dataHora;
}
//Nivel 0 The Swamp of POX - USA HTTP Transporte
// /api
/*
    POST /api {
    "action": "getUser"
    "id": 12

    }
 */
// Nivel 1 - Recursos
// GET /alunos/getUser
// POST /alunos/salvar
// POST /alunos/atualizar
// GET / professores/GETUSer

// Nivel 2 - Verbos HTTP Status CODE
// GET /aluno/ -> Listar TOdos
// GET /aluno/1 -> GetByID -> 200, 404
// POST /aluno {corpo}
// RESPONSE {message: "adicionado com sucesso"} 203 CREATED

/*
    http.get("http://localhost:8080/aluno/12)
      .corpo -> mensagem == "das
      .onSuccess()
      .onError()

 */

// POST /professor {corpo}
//RESPONSE {message: "adicionado sucesso"}
// Nível 3 HATEOAS Hypermedia AS The Engine of Application
// _links
/*
   id: 12
   nome: Leo
   _links {
       self: {href: /alunos/12}
       delete: {href: usuarios/12}

   }

 */





