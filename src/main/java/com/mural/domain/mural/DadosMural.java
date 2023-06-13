package com.mural.domain.mural;

import com.mural.domain.usuario.DadosUsuario;

public record DadosMural(
        String id,
        String mensagem,
        Prioridade prioridade,
        DadosUsuario usuario

) {
    public DadosMural(Mural dados) {
        this(dados.getId(), dados.getMensagem(), dados.getPrioridade(), dados.getUsuario());
    }
}
