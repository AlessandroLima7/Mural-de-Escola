package com.mural.domain.usuario.dtos;

import com.mural.domain.usuario.Perfil;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizarUsuario(
        @NotNull
        String id,

        String nome,

        String password,

        String username,

        Perfil perfil
) {
}
