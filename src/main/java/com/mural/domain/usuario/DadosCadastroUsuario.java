package com.mural.domain.usuario;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroUsuario(
        @NotBlank(message = "{nome.obrigatorio}")
        String nome,
        @NotBlank
        String username,
        @NotBlank
        String password,
        @NotNull
        Perfil perfil
) {
}
