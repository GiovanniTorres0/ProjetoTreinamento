package com.mscatalog.catalog.repository;

import com.mscatalog.catalog.entity.Categoria;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepository extends MongoRepository<Categoria, Integer> {
}
