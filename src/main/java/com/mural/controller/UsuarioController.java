package com.mural.controller;

import com.mural.domain.usuario.*;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.authentication.configurers.userdetails.DaoAuthenticationConfigurer;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;

@RestController
@RequestMapping("/usuario")
@SecurityRequirement(name = "bearer-key")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;


    @GetMapping
    public ResponseEntity<Page<DadosUsuario>> buscar(@PageableDefault(size = 10, sort = "nome") Pageable pagina, @AuthenticationPrincipal Usuario logado){
        Page<DadosUsuario> usuarios;
        if(logado.getPerfil().equals(Perfil.ADMIN)){
            usuarios = usuarioRepository.findAll(pagina).map(DadosUsuario::new);
        }
        else {
            usuarios = usuarioRepository.findAllByAtivoTrue(pagina).map(DadosUsuario::new);
        }

        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosUsuario> buscarUm(@PathVariable String id) {

        var user = usuarioRepository.findByIdAndAtivoTrue(id);
        return ResponseEntity.ok(new DadosUsuario(user.get()));

    }


    @PostMapping
    @Transactional
    public ResponseEntity criar(@RequestBody @Valid DadosCadastroUsuario dados, UriComponentsBuilder uriBuilder){
        Usuario usuario = new Usuario(dados);
        usuarioRepository.save(usuario);
        var uri = uriBuilder.path("/usuario/{id}").buildAndExpand(usuario.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosUsuario(usuario));
    }

    @PutMapping("/incluir/{id}")
    @Transactional
    public ResponseEntity incluir(@PathVariable String id, @AuthenticationPrincipal Usuario logado){
        Usuario usuario = usuarioRepository.findById(id).get();
        ResponseEntity resposta = usuario.incluir(new DadosUsuario(logado), usuario.getNome());
        usuarioRepository.save(usuario);
       return resposta;
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DadosUsuario> editar(@RequestBody DadosAtualizarUsuario usuario) {
        Optional<Usuario> user = usuarioRepository.findById(usuario.id());
        Usuario usuario1 = user.get();
        usuario1.atualizarInformacoes(usuario);
        usuarioRepository.save(usuario1);
        return ResponseEntity.ok(new DadosUsuario(usuario1));
    }


    @DeleteMapping("/excluir/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable String id, @AuthenticationPrincipal Usuario logado){
        var usuario = usuarioRepository.findById(id).get();

        ResponseEntity resposta = usuario.excluir(new DadosUsuario(logado), usuario.getNome());
        usuarioRepository.save(usuario);
        return resposta;
    }



}
