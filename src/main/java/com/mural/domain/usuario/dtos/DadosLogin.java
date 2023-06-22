package com.mural.domain.usuario.dtos;

import jakarta.validation.constraints.NotBlank;

public record DadosLogin(
        String username,
        String password) {

}
