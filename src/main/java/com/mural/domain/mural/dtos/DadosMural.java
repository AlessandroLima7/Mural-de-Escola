package com.mural.domain.mural.dtos;

import com.mural.domain.mural.Comentarios;
import com.mural.domain.mural.Mural;
import com.mural.domain.mural.Prioridade;
import com.mural.domain.usuario.dtos.DadosUsuario;

import java.util.List;

public record DadosMural(
        String id,
        String mensagem,
        Prioridade prioridade,
        DadosUsuario usuario,
        List<Comentarios> comentarios

) {
    public DadosMural(Mural dados) {
        this(dados.getId(), dados.getMensagem(), dados.getPrioridade(), dados.getUsuario(), dados.getComentarios());
    }
}
