package com.mural.controller;

import com.mural.domain.usuario.DadosCadastroUsuario;
import com.mural.domain.usuario.DadosUsuario;
import com.mural.domain.usuario.Perfil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;


@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class UsuarioControllerTest {


    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<DadosCadastroUsuario> dadosCadastroUsuarioJson;

    @Autowired
    private JacksonTester<DadosUsuario> dadosUsuarioJson;

    @Test
    @DisplayName("Deve retornar error 400")
    void criar_cenario1() throws Exception {
        var response = mvc.perform(post("/usuario")).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Deve retornar o código 201")
    void criar_cenario2() throws Exception {
        var response = mvc
                .perform(
                    post("/usuario")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(dadosCadastroUsuarioJson.write(
                            new DadosCadastroUsuario("Alessandro Santos", "Alesson", "123456", Perfil.ADMIN)
                            ).getJson())
                )
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
    }
}