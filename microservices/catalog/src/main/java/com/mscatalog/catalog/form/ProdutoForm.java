package com.mscatalog.catalog.form;

import com.mscatalog.catalog.entity.Categoria;
import com.mscatalog.catalog.entity.Produto;
import com.mscatalog.catalog.repository.CategoriaRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.Optional;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoForm {


    private Integer id;
    @NotBlank
    private String name;
    @NotBlank
    private String description;
    private Boolean active;
    private Integer category_id;

    public Produto converter(ProdutoForm produtoForm, CategoriaRepository categoriaRepository) {
        Produto produto = new Produto();
        produto.setName(produtoForm.getName());
        produto.setDescription(produtoForm.getDescription());
        produto.setActive(produtoForm.getActive());
        Categoria categoria = inicia(produtoForm, categoriaRepository);
        produto.setCategoria(categoria);
        return produto;
    }

    public Categoria inicia(ProdutoForm produtoForm, CategoriaRepository categoriaRepository) {
        Optional<Categoria> optional = categoriaRepository.findById(produtoForm.getCategory_id());
        return optional.orElseGet(() -> new Categoria(1, "PRODUTO INVÁLIDO PARA TESTE", true));

    }


}

