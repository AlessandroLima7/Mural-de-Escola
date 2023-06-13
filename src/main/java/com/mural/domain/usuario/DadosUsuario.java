package com.mural.domain.usuario;

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
