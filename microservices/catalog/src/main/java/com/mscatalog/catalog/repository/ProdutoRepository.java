package com.mscatalog.catalog.repository;

import com.mscatalog.catalog.entity.Produto;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdutoRepository extends MongoRepository<Produto, Integer> {

    List<Produto> findProdutoByActiveIsTrue();
}
