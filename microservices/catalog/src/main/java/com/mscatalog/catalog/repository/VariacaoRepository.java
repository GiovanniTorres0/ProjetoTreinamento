package com.mscatalog.catalog.repository;

import com.mscatalog.catalog.entity.Variacao;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VariacaoRepository extends MongoRepository<Variacao, Integer> {
}
