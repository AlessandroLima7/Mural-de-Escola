package com.mural.domain.usuario;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface UsuarioRepository extends MongoRepository<Usuario, String> {
    Page<Usuario> findAllByAtivoTrue(Pageable pagina);

    Optional<Usuario> findByIdAndAtivoTrue(String id);

    UserDetails findByUsername(String username);
}
