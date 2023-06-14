package com.mural.domain.mural;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


public record DadosCadastroMural(
        @NotBlank
        String mensagem,
        @NotNull
        Prioridade prioridade
) {
}
