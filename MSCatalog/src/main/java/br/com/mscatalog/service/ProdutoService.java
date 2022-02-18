package br.com.mscatalog.service;

import br.com.mscatalog.dto.ProdutoDto;
import br.com.mscatalog.form.ProdutoForm;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

public interface ProdutoService {
    ResponseEntity<ProdutoDto> salvarProduto(@RequestBody @Valid ProdutoForm produtoForm);

    List<ProdutoDto> ListarProdutos();

    ResponseEntity<ProdutoDto> buscarPorId (@PathVariable UUID id);

    ResponseEntity<ProdutoDto> deletarPorId(@PathVariable UUID id);

    ResponseEntity<ProdutoDto> atualizarPorId(@PathVariable UUID id, ProdutoForm produtoForm);
}
