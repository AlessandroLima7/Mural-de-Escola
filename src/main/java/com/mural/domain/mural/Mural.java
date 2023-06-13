package com.mural.domain.mural;

import com.mural.domain.usuario.Perfil;
import com.mural.domain.usuario.DadosUsuario;
import com.mural.domain.usuario.UsuarioRepository;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

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


    public Mural(DadosCadastroMural dados, UsuarioRepository repository){

        var user = repository.findById(dados.idUsuario());
        this.mensagem = dados.mensagem();
        this.usuario = new DadosUsuario(user.get());
        this.prioridade = dados.prioridade();
        if(user.get().getPerfil().equals(Perfil.PROFESSOR)){
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


}
