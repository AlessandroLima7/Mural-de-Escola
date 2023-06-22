package com.mural.domain.usuario.dtos;

import com.mural.domain.usuario.Perfil;
import com.mural.domain.usuario.Usuario;

public record DadosUsuario(

        String id,
        String nome,
        String username,
        Perfil perfil

) {

    public DadosUsuario(Usuario usuario){
        this(usuario.getId(), usuario.getNome(), usuario.getUsername(), usuario.getPerfil());
    }
}
