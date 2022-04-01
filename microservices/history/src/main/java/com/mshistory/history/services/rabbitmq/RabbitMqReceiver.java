package com.mshistory.history.services.rabbitmq;


import com.mscheckout.checkout.form.PurchaseForm;
import com.mshistory.history.entity.History;
import com.mshistory.history.repository.HistoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class RabbitMqReceiver implements RabbitListenerConfigurer {

    private static final Logger logger = LoggerFactory.getLogger(RabbitMqReceiver.class);
    @Autowired
    HistoryRepository historyRepository;

    @Override
    public void configureRabbitListeners(RabbitListenerEndpointRegistrar rabbitListenerEndpointRegistrar) {
    }

    @RabbitListener(queues = "${spring.rabbitmq.queue}")
    public void receivedMessage(PurchaseForm purchaseForm) {
        try {
            logger.info("Purchase Details Received is.. " + purchaseForm);
            History history = historyRepository.findHistoryByUserId(Integer.parseInt(purchaseForm.getUser_id()));
            historyRepository.save(history);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}







