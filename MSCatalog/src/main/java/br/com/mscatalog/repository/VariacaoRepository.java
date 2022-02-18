package br.com.mscatalog.repository;

import br.com.mscatalog.models.Variacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository

public interface VariacaoRepository extends JpaRepository<Variacao, UUID> {
}