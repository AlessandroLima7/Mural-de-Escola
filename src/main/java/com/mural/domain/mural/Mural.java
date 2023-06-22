package com.mural.domain.mural;

import com.mural.domain.mural.dtos.DadosCadastroMural;
import com.mural.domain.usuario.Perfil;
import com.mural.domain.usuario.dtos.DadosUsuario;
import com.mural.domain.usuario.Usuario;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "mural")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Mural {

    @Id
    private String id;
    private String mensagem;
    private Boolean mostrar;
    private DadosUsuario usuario;
    private Prioridade prioridade;
    private List<Comentarios> comentarios = new ArrayList<Comentarios>();


    public Mural(DadosCadastroMural dados, Usuario logado){
        this.mensagem = dados.mensagem();
        this.usuario = new DadosUsuario(logado);
        this.prioridade = dados.prioridade();
        if(logado.getPerfil().equals(Perfil.PROFESSOR)){
            this.mostrar = true;
        }
        else{
            this.mostrar = false;
        }
    }

    public String exibirNoMural(DadosUsuario usuario){
        if(!usuario.perfil().equals(Perfil.ALUNO)){
            this.mostrar = true;
            return "Post publicado no mural com sucesso1";
        }
        else {
            return "Alunos não tem autorização de publicar no mural.";
        }

    }

    public boolean tirarDoMural(DadosUsuario usuario) {
        if(!usuario.perfil().equals(Perfil.ALUNO)) {
            this.mostrar = false;
            return true;
        }
        else {
            return false;
        }
    }

    public ResponseEntity comentar(Comentarios comentario){
        this.comentarios.add(comentario);
        return ResponseEntity.ok(String.format("Comentário inserido com sucesso!: \n\n  %s", comentario.getComentario()));
    }


}
