package br.com.area51.ufoTracker.model;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

public record AvistamentoFiltro(
        String localizacao,
        Integer confiabilidadeMin,
        Integer confiabilidadeMax,
        String tipoObjeto,
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
        LocalDateTime de,
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
        LocalDateTime ate,
        String testemunha
) {
    public boolean isVazio() {
        return !hasText(localizacao)
                && confiabilidadeMin == null
                && confiabilidadeMax == null
                && !hasText(tipoObjeto)
                && de == null
                && ate == null
                && !hasText(testemunha);
    }

    public boolean parametrosInvalidos() {
        return (confiabilidadeMin != null && confiabilidadeMax != null && confiabilidadeMin > confiabilidadeMax)
                || (de != null && ate != null && de.isAfter(ate));
    }
    private static boolean hasText(String s) {
        return s != null && !s.trim().isEmpty();
    }

}
