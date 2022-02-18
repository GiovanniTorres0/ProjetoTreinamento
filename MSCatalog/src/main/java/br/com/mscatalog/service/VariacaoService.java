package br.com.mscatalog.service;

import br.com.mscatalog.dto.VariacaoDto;
import br.com.mscatalog.form.VariacaoForm;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.UUID;

public interface VariacaoService {
    ResponseEntity<VariacaoDto> cadastrarVariacao(@RequestBody VariacaoForm variacaoForm);

    ResponseEntity<VariacaoDto> atualizarVariacao(@PathVariable UUID id, @Valid @RequestBody VariacaoForm variacaoForm);

    ResponseEntity<VariacaoDto> deletarVariacao(@PathVariable UUID id);
}
