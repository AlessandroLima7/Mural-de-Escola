package com.mural.controller;

import com.mural.domain.mural.DadosCadastroMural;
import com.mural.domain.mural.DadosMural;
import com.mural.domain.mural.Mural;
import com.mural.domain.mural.MuralRepository;
import com.mural.domain.usuario.DadosUsuario;
import com.mural.domain.usuario.Perfil;
import com.mural.domain.usuario.Usuario;
import com.mural.domain.usuario.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/mural")
public class MuralController {

    @Autowired
    private MuralRepository muralRepository;
    @Autowired
    protected UsuarioRepository usuarioRepository;

    @GetMapping
    public ResponseEntity<Page<DadosMural>> exibirTodos(@AuthenticationPrincipal Usuario usuario, @PageableDefault(size = 10, sort = "nome") Pageable pagina){

        if(usuario.getPerfil().equals(Perfil.PROFESSOR) || usuario.getPerfil().equals(Perfil.ADMIN)){
            Page<DadosMural> publicacoes = muralRepository.findAll(pagina).map(DadosMural::new);
            return ResponseEntity.ok(publicacoes);
        }
        else {
            Page<DadosMural> puclicacoes = muralRepository.findByMostrarTrue(pagina).map(DadosMural::new);
            return ResponseEntity.ok(puclicacoes);
        }

    }

    @PostMapping
    @Transactional
    public ResponseEntity<DadosMural> cadastrar(@RequestBody @Valid DadosCadastroMural dados, @AuthenticationPrincipal Usuario logado,  UriComponentsBuilder uriBuilder) {
        Mural mural = new Mural(dados, logado);
        muralRepository.save(mural);
        var uri = uriBuilder.path("/mural/{id}").buildAndExpand(mural.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosMural(mural));
    }

    @PutMapping()
    @Transactional
    public ResponseEntity<String> publicarMural(@AuthenticationPrincipal Usuario logado, @RequestBody DadosMural dados) {
        Mural mural = muralRepository.findById(dados.id()).get();
        String resposta = mural.exibirNoMural(new DadosUsuario(logado));
        muralRepository.save(mural);
        return ResponseEntity.ok(resposta+mural);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity tirarDoMural(@AuthenticationPrincipal Usuario logado, @PathVariable String id) {
        Mural mural = muralRepository.findById(id).get();
        boolean resposta = mural.tirarDoMural(new DadosUsuario(logado));
        if(resposta){
            muralRepository.save(mural);
            return ResponseEntity.noContent().build();
        }
        else {
            return ResponseEntity.badRequest().body("Não autorizada a exclusão do post no Mural.");
        }


    }
}
