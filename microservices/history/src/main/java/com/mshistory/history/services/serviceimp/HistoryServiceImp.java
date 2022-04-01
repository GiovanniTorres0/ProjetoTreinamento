package com.mshistory.history.services.serviceimp;

import com.mscatalog.catalog.dto.ProdutosVariadosDto;
import com.mscatalog.catalog.entity.Variacao;
import com.mscheckout.checkout.dto.PaymentDto;
import com.mscustomer.customer.dto.UsuarioDto;
import com.mshistory.history.dto.HistoryDto;
import com.mshistory.history.entity.History;
import com.mshistory.history.repository.HistoryRepository;
import com.mshistory.history.services.service.HistoryService;
import com.mshistory.history.services.service.RequestService;
import com.mshistory.history.services.sequence.SequenceGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static com.mshistory.history.entity.History.SEQUENCE_NAME;

@Service
public class HistoryServiceImp implements HistoryService {

    @Autowired
    RequestService.RequestCostumer requestCostumer;
    @Autowired
    RequestService.ResquestCatalogService resquestCatalogService;
    @Autowired
    RequestService.RequestCheckout requestCheckout;
    @Autowired
    HistoryRepository historyRepository;
    @Autowired
    SequenceGeneratorService sequenceGeneratorService;


    @Override
    public ResponseEntity<HistoryDto> buscaHistoricoUsuario(@PathVariable Integer id) {
        try {
            HistoryDto historyDto = new HistoryDto();
            double valor = 0;
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDateTime agora = LocalDateTime.now();
            ResponseEntity<UsuarioDto> usuarioDtoResponseEntity = requestCostumer.buscaUsuarioPorId(id);
            List<PaymentDto> paymentDtoList = requestCheckout.buscaTodosOsPagamentos();
            List<ProdutosVariadosDto> produtosVariadosDtos = resquestCatalogService.buscaProdutos();
            historyDto.setUser(usuarioDtoResponseEntity.getBody());
            historyDto.setPayments(paymentDtoList);
            historyDto.setProdutosVariadosDto(produtosVariadosDtos);
            historyDto.setData(agora.format(formatter));
            for (ProdutosVariadosDto produtosVariadosDto : produtosVariadosDtos) {
                for (Variacao variacao : produtosVariadosDto.getVariacoes()) {
                    valor += variacao.getPrice();
                }
            }
            historyDto.setTotal(valor);
            History history = historyDto.converte(historyDto);
            history.setId(sequenceGeneratorService.getSequenceNumber(SEQUENCE_NAME));
            historyRepository.save(history);
            return new ResponseEntity<>(historyDto, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.noContent().build();
    }

}
