package com.mscatalog.catalog.servicetest;

import com.mscatalog.catalog.dto.VariacaoDto;
import com.mscatalog.catalog.entity.Categoria;
import com.mscatalog.catalog.entity.Produto;
import com.mscatalog.catalog.entity.Variacao;
import com.mscatalog.catalog.enums.Size;
import com.mscatalog.catalog.form.VariacaoForm;
import com.mscatalog.catalog.repository.ProdutoRepository;
import com.mscatalog.catalog.repository.VariacaoRepository;
import com.mscatalog.catalog.services.sequence.SequenceGeneratorService;
import com.mscatalog.catalog.services.serviceimp.VariacaoServiceImp;
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

import java.util.LinkedList;
import java.util.List;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
@WebAppConfiguration
public class VariacaoServiceTest {


    @InjectMocks
    VariacaoServiceImp variacaoServiceImp;

    @Mock
    VariacaoRepository variacaoRepository;

    @Mock
    ProdutoRepository produtoRepository;

    @Mock
    SequenceGeneratorService sequenceGeneratorService;

    @Before
    public void Setup() {
        MockitoAnnotations.openMocks(this);
    }

    @BeforeEach
    public List<Variacao> variacao() {
        Variacao variacao = new Variacao(1, "azul", Size.G, 22.50, 10, 1);
        Variacao variacao1 = new Variacao(2, "roxo", Size.M, 30.55, 3, 1);
        List<Variacao> variacoes = new LinkedList<>();
        variacoes.add(variacao);
        variacoes.add(variacao1);
        return variacoes;
    }

    @BeforeEach
    public List<VariacaoForm> variacaoForms() {
        VariacaoForm variacaoForm = new VariacaoForm(1, "azul", Size.G, 22.50, 10, 1);
        VariacaoForm variacaoForm1 = new VariacaoForm(2, "roxo", Size.M, 30.55, 3, 1);
        List<VariacaoForm> variacaoForms = new LinkedList<>();
        variacaoForms.add(variacaoForm);
        variacaoForms.add(variacaoForm1);
        return variacaoForms;
    }

    @BeforeEach
    public Categoria categoria() {
        return new Categoria(1, "camisetas", true);
    }

    @BeforeEach
    public Produto produto() {
        return new Produto(1, "Camiseta", "Camise de time", true, categoria(), variacao());
    }

    @Test
    public void CadastraVariacao() throws Exception {
       ResponseEntity<VariacaoDto> entity = variacaoServiceImp.cadastraVariacao(variacaoForms());
       if(entity.getStatusCodeValue() == 200) {
           Assert.assertEquals(200, entity.getStatusCodeValue());
           System.out.println("VARIACAO CADASTRADA COM SUCESSO");
       } else if(entity.getStatusCodeValue() == 404){
           Assert.assertEquals(404, entity.getStatusCodeValue());
           System.out.println("VARIACAO OU PRODUTO DE VINCULO NÃO ENCONTRADO");
       } else {
           throw new Exception("ERRO DESCONHECIDO");
       }
    }

    @Test
    public void AtualizarVariacao() throws Exception {
        ResponseEntity<VariacaoDto> entity = variacaoServiceImp.atualizaVariacao(variacao(),1);
        if(entity.getStatusCodeValue() == 200){
            Assert.assertEquals(200, entity.getStatusCodeValue());
            System.out.println("VARIACAO ATUALIZADA COM SUCESSO");
        } else if(entity.getStatusCodeValue() == 400) {
            Assert.assertEquals(400, entity.getStatusCodeValue());
            System.out.println("VARIACAO NÃO ENCONTRADA PARA ATUALIZAR");
        } else {
            throw new Exception("ERRO DESCONHECIDO");
        }
    }


    @Test
    public void DelestarVariacaoPorId() throws Exception {
        ResponseEntity<VariacaoDto> entity = variacaoServiceImp.deletarVariacao(1);
        if(entity.getStatusCodeValue() == 200) {
            Assert.assertEquals(200, entity.getStatusCodeValue());
            System.out.println("VARIACAO DELETADA COM SUCESSO");
        } else if(entity.getStatusCodeValue() == 404) {
            Assert.assertEquals(404, entity.getStatusCodeValue());
            System.out.println("VARIACAO NÃO ENCONTRADA");
        } else {
            throw new Exception("ERRO DESCONHECIDO");
        }
     }

}
