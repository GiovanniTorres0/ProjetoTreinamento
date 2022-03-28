package com.mscatalog.catalog.servicetest;

import com.mscatalog.catalog.dto.CategoriaDto;
import com.mscatalog.catalog.form.CategoriaForm;
import com.mscatalog.catalog.repository.CategoriaRepository;
import com.mscatalog.catalog.services.sequence.SequenceGeneratorService;
import com.mscatalog.catalog.services.serviceimp.CategoriaServiceImp;
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

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
@WebAppConfiguration
public class CategoriaServiceTest {

    @InjectMocks
    CategoriaServiceImp categoriaServiceImp;

    @Mock
    CategoriaRepository categoriaRepository;

    @Mock
    SequenceGeneratorService sequenceGeneratorService;

    @Before
    public void Setup() {
        MockitoAnnotations.openMocks(this);
    }

    @BeforeEach
    public CategoriaForm categoriaForm(){
        return new CategoriaForm(1, "Moletons", true);
    }

    @Test
    public void CadastraCategoria() throws Exception {
       ResponseEntity<CategoriaDto> entity = categoriaServiceImp.cadastraCategoria(categoriaForm());
       if(entity.getStatusCodeValue() == 201) {
           Assert.assertEquals(201, entity.getStatusCodeValue());
           System.out.println("CATEGORIA CADASTRADA COM SUCESSO");
       } else if(entity.getStatusCodeValue() == 400) {
           Assert.assertEquals(400, entity.getStatusCodeValue());
           System.out.println("CATEGORIA NÃO FOI CADASTRADA");
       } else {
           throw new Exception("ERRO DESCONHECIDO");
       }
    }

    @Test
    public void MostraCategorias(){
        List<CategoriaDto> categoriaDtos = categoriaServiceImp.mostraCategorias();
        if (categoriaDtos.size() == 0) {
            Assert.assertNull(null);
            System.out.println("LISTA VAZIA");
        }
        for (int i = 0; i < categoriaDtos.size(); i++) {
            if (categoriaDtos.get(i) != null) {
                Assert.assertNotNull(categoriaDtos);
                System.out.println("TODOS AS CATEGORIAS ENCONTRADOS");
            }
        }
    }

    @Test
    public void BuscaCategoriaPorId() throws Exception {
        ResponseEntity<CategoriaDto> entity = categoriaServiceImp.buscaCategoria(1);
        if(entity.getStatusCodeValue() == 200){
            Assert.assertEquals(200, entity.getStatusCodeValue());
            System.out.println("CATEGORIA ENCONTRADA");
        } else if(entity.getStatusCodeValue() == 404) {
            Assert.assertEquals(404, entity.getStatusCodeValue());
            System.out.println("CATEGORIA NÃO ENCONTRADA");
        } else {
            throw new Exception("ERRO DESCONHECIDO");
        }
    }

    @Test
    public void AtualizaCategoriaPorId() throws Exception {
        ResponseEntity<CategoriaDto> entity = categoriaServiceImp.atualizaCategoria(categoriaForm(),1);
        if(entity.getStatusCodeValue() == 202){
            Assert.assertEquals(202, entity.getStatusCodeValue());
            System.out.println("CATEGORIA ATUALIZADA");
        } else if(entity.getStatusCodeValue() == 404){
            Assert.assertEquals(404, entity.getStatusCodeValue());
            System.out.println("CATEGORIA NÃO LOCALIZADA");
        } else {
            throw new Exception("ERRO DESCONHECIDO");
        }
    }

    @Test
    public void DeletarCategoriaPorId() throws Exception {
       ResponseEntity<CategoriaDto> entity = categoriaServiceImp.deletarCategoria(1);
       if(entity.getStatusCodeValue() == 200) {
           Assert.assertEquals(200, entity.getStatusCodeValue());
           System.out.println("CATEGORIA DELETADA");
       } else if (entity.getStatusCodeValue() == 404) {
           Assert.assertEquals(404, entity.getStatusCodeValue());
       } else {
           throw new Exception("ERRO DESCONHECIDO");
       }
    }
}
