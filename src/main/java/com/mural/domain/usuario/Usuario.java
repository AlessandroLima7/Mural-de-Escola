package com.mural.domain.usuario;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Collection;
import java.util.List;

@Document(collection = "usuario")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Usuario implements UserDetails {

    @Id
    private String id;
    private String nome;

    @Indexed(unique = true)
    private String username;
    private String password;
    private Perfil perfil;
    private Boolean ativo;

    public Usuario(DadosCadastroUsuario usuario){
        this.nome = usuario.nome();
        this.username = usuario.username();
        this.password = new BCryptPasswordEncoder().encode(usuario.password());
        this.perfil = usuario.perfil();
        this.ativo = true;
    }

    public void atualizarInformacoes(DadosAtualizarUsuario usuario){

        if(usuario.nome() != null) {
            this.nome = usuario.nome();
        }
        if(usuario.username() != null) {
            this.username = usuario.username();
        }
        if(usuario.password() != null) {
            this.password = usuario.password();
        }
        if(usuario.perfil() != null) {
            this.perfil = usuario.perfil();
        }

    }


    public void excluir() {
        this.ativo = false;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
