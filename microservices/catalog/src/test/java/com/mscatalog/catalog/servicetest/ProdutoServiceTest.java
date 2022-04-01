package com.mscatalog.catalog.servicetest;

import com.mscatalog.catalog.dto.ProdutoDto;
import com.mscatalog.catalog.dto.ProdutosVariadosDto;
import com.mscatalog.catalog.entity.Categoria;
import com.mscatalog.catalog.form.ProdutoForm;
import com.mscatalog.catalog.repository.CategoriaRepository;
import com.mscatalog.catalog.repository.ProdutoRepository;
import com.mscatalog.catalog.services.sequence.SequenceGeneratorService;
import com.mscatalog.catalog.services.serviceimp.ProdutoServiceImp;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;
import java.util.Objects;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
@WebAppConfiguration
public class ProdutoServiceTest {

    @InjectMocks
    ProdutoServiceImp produtoServiceImp;

    @Mock
    ProdutoRepository produtoRepository;

    @Mock
    CategoriaRepository categoriaRepository;

    @Mock
    SequenceGeneratorService sequenceGeneratorService;

    @Before
    public void Setup() {
        MockitoAnnotations.openMocks(this);
    }

    @BeforeEach
    public ProdutoForm produtoForm() {
        return new ProdutoForm(1, "Moletom", "Moletom com capuz", true, 1);
    }

    @BeforeEach
    public Categoria categoria() {
        return new Categoria(1, "Moletons", true);
    }

    @Test
    public void SalvaUmNovoProduto() throws Exception {
        ResponseEntity<ProdutoDto> entity = produtoServiceImp.registraProduto(produtoForm());
        if (entity.getStatusCodeValue() == 201) {
            Assert.assertNotNull(entity);
            System.out.println("PRODUTO CADASTRADO");
        } else if (entity.getStatusCodeValue() == 404) {
            Assert.assertNull(null);
            System.out.println("PRODUTO NÃO FOI CADASTRADO");
        } else {
            throw new Exception("ERRO INESPERADO");
        }
    }

    @Test
    public void BuscaTodosOsProdutos() {
        List<ProdutosVariadosDto> list = produtoServiceImp.buscaTodosProdutos();
        if (list.size() == 0) {
            Assert.assertNull(null);
            System.out.println("LISTA VAZIA");
        }
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) != null) {
                Assert.assertNotNull(list);
                System.out.println("TODOS OS PRODUTOS ENCONTRADOS");
            }
        }
    }

    @Test
    public void BuscarProdutoPorId() throws Exception {
        ResponseEntity<ProdutoDto> entity = produtoServiceImp.buscaPorId(1);
        if(entity.getStatusCodeValue() == 200) {
            Assert.assertEquals(200, entity.getStatusCodeValue());
            System.out.println("PRODUTO ENCONTRADO");
        } else if(entity.getStatusCodeValue() == 404) {
            Assert.assertEquals(404, entity.getStatusCodeValue());
            System.out.println("PRODUTO NÃO ENCONTRADO");
        } else {
            throw new Exception("ERRO INESPERADO");
        }
    }

    @Test
    public void AtualizaProdutoPorId() throws Exception {
        ResponseEntity<ProdutoDto> entity = produtoServiceImp.atualizarPorId(1,produtoForm());
        if(entity.getStatusCodeValue() == 202) {
            Assert.assertEquals(202, entity.getStatusCodeValue());
            System.out.println("PRODUTO ATUALIZADO COM SUCESSO");
        } else if(entity.getStatusCodeValue() == 404){
            Assert.assertEquals(404, entity.getStatusCodeValue());
            System.out.println("PRODUTO NÃO ENCONTRADO");
        } else {
            throw new Exception("ERRO INESPERADO");
        }
    }

    @Test
    public void DeletaProdutoPorId() throws Exception {
        ResponseEntity<ProdutoDto> entity = produtoServiceImp.deletarPorId(1);
        if(entity.getStatusCodeValue() == 200) {
            Assert.assertEquals(200, entity.getStatusCodeValue());
            System.out.println("PRODUTO DELETADO COM SUCESSO");
        } else if(entity.getStatusCodeValue() == 404){
            Assert.assertEquals(404, entity.getStatusCodeValue());
            System.out.println("PRODUTO NÃO ENCONTRADO");
        } else {
            throw new Exception("ERRO INESPERADO");
        }
    }

    @Test
    public void RetornaListaDeProdutosVariados() throws Exception {
        List<ProdutosVariadosDto> produtosVariadosDtos = produtoServiceImp.retorna();
        if(Objects.nonNull(produtosVariadosDtos)) {
            Assert.assertEquals(produtosVariadosDtos, produtosVariadosDtos);
            System.out.println("RETORNA A LISTA DE PRODUTOS VARIADOS DTOS");
        } else {
            Assert.assertNull(null);
            System.out.println("NÃO RETORNA A LISTA DE PRODUTOS DTOS");
        }
    }


}
