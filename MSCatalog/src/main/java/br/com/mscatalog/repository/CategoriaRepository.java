package br.com.mscatalog.repository;

import br.com.mscatalog.models.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository

public interface CategoriaRepository extends JpaRepository<Categoria, UUID> {
}