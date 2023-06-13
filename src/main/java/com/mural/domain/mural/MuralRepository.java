package com.mural.domain.mural;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MuralRepository extends MongoRepository<Mural, String> {
    Page<Mural> findByMostrarTrue(Pageable pagina);
}
