package org.theleakycauldron.diagonalley.listeners;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.theleakycauldron.diagonalley.commons.DiagonAlleyUtils;
import org.theleakycauldron.diagonalley.dtos.DiagonAlleyKafkaRequestDTO;
import org.theleakycauldron.diagonalley.entities.Outbox;
import org.theleakycauldron.diagonalley.repositories.DiagonAlleyRDBOutboxRepository;

import java.util.concurrent.CompletableFuture;

/**
 * @author: Vijaysurya Mandala
 * @github: github/mandalavijaysurya (<a href="https://www.github.com/mandalavijaysurya"> Github</a>)
 */

@Slf4j
@Component
public class DigaonAlleyOutboxEventListener {
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final DiagonAlleyRDBOutboxRepository outboxRepository;

    public DigaonAlleyOutboxEventListener(
            KafkaTemplate<String, String> kafkaTemplate,
            DiagonAlleyRDBOutboxRepository outboxRepository
    ){
        this.kafkaTemplate = kafkaTemplate;
        this.outboxRepository = outboxRepository;
    }

    @Transactional
    @EventListener
    public void publishOutboxEvent(Outbox outbox){
        try{
            log.info("Listening to outbox event :: {}", outbox.toString());
            DiagonAlleyKafkaRequestDTO kafkaRequestDTO = DiagonAlleyUtils.convertProductToKafkaRequestDTO(outbox.getProduct());
            CompletableFuture<SendResult<String, String>> kafkaMessage = kafkaTemplate.send("diagon-alley", kafkaRequestDTO.toString());
            kafkaMessage.whenComplete((result, ex) -> {
                if(ex != null)  throw new RuntimeException(ex.getMessage());
            });
            outbox.setPersisted(true);
            outboxRepository.save(outbox);
        }catch(Exception ex){
            throw new RuntimeException(ex.getMessage());
        }
    }
}
