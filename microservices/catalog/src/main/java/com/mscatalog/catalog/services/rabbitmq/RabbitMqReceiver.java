package com.mscatalog.catalog.services.rabbitmq;

import com.mscatalog.catalog.entity.Variacao;
import com.mscatalog.catalog.repository.VariacaoRepository;
import com.mscheckout.checkout.form.PurchaseForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;


@Component
public class RabbitMqReceiver implements RabbitListenerConfigurer {

    @Autowired
    VariacaoRepository variacaoRepository;

    private static final Logger logger = LoggerFactory.getLogger(RabbitMqReceiver.class);

    @Override
    public void configureRabbitListeners(RabbitListenerEndpointRegistrar rabbitListenerEndpointRegistrar) {
    }

    @RabbitListener(queues = "${spring.rabbitmq.queue}")
    public void receivedMessage(PurchaseForm purchaseForm) {
        logger.info("Purchase Details Received is.. " + purchaseForm);
        for (int i = 0; i < purchaseForm.getCart().size(); i++) {
            int idV = purchaseForm.getCart().get(i).getVariant_id();
            Optional<Variacao> optional = variacaoRepository.findById(idV);
            if (optional.isPresent()) {
                optional.get().setQuantity(purchaseForm.getCart().get(i).getQuantity());
                variacaoRepository.save(optional.get());
            }
            logger.info("Quantidade atualizada", optional.get());
        }
    }
}





