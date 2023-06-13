package com.mural.domain.usuario;

import jakarta.validation.constraints.NotBlank;

public record DadosLogin(
        String username,
        String password) {

}
