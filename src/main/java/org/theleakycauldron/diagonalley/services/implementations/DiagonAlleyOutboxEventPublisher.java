package org.theleakycauldron.diagonalley.services.implementations;

import org.hibernate.validator.internal.constraintvalidators.bv.number.bound.decimal.DecimalMaxValidatorForDouble;
import org.springframework.boot.autoconfigure.dao.PersistenceExceptionTranslationAutoConfiguration;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.theleakycauldron.diagonalley.commons.DiagonAlleyUtils;
import org.theleakycauldron.diagonalley.dtos.DiagonAlleyKafkaRequestDTO;
import org.theleakycauldron.diagonalley.entities.Outbox;
import org.theleakycauldron.diagonalley.entities.Product;
import org.theleakycauldron.diagonalley.exceptions.OutboxNotExistsException;
import org.theleakycauldron.diagonalley.repositories.DiagonAlleyRDBOutboxRepository;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * @author: Vijaysurya Mandala
 * @github: github/mandalavijaysurya (<a href="https://www.github.com/mandalavijaysurya"> Github</a>)
 */

@Component
public class DiagonAlleyOutboxEventPublisher implements ApplicationEventPublisherAware {

    private ApplicationEventPublisher applicationEventPublisher;
    private final DiagonAlleyRDBOutboxRepository diagonAlleyRDBOutboxRepository;

    public DiagonAlleyOutboxEventPublisher(
            DiagonAlleyRDBOutboxRepository diagonAlleyRDBOutboxRepository
    ) {
        this.diagonAlleyRDBOutboxRepository = diagonAlleyRDBOutboxRepository;
    }
    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public void publishOutboxEvent(Outbox outbox){

        Optional<Outbox> persistedOutbox = diagonAlleyRDBOutboxRepository.findById(outbox.getId());
        if(persistedOutbox.isEmpty()){
            throw new OutboxNotExistsException("Outbox with id: "+outbox.getId()+" does not exist");
        }
        Outbox savedOutbox = persistedOutbox.get();
        applicationEventPublisher.publishEvent(savedOutbox);
    }

}
