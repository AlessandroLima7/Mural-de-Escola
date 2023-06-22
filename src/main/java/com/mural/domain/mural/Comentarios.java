package com.mural.domain.mural;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Comentarios {

    private String comentario;
    private String usuario;

    public Comentarios(String comentario, String usuario){
       this.comentario = comentario;
       this.usuario = usuario;

    }

}
