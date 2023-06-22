package com.mural.domain.mural.dtos;

import com.mural.domain.mural.Prioridade;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


public record DadosCadastroMural(
        @NotBlank
        String mensagem,
        @NotNull
        Prioridade prioridade
) {
}
