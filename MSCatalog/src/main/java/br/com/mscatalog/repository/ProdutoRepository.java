package br.com.mscatalog.repository;

import br.com.mscatalog.models.Produto;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface ProdutoRepository extends MongoRepository<Produto, UUID> {



}
